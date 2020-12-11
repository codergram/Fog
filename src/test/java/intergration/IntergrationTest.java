/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package intergration;

import api.Api;
import api.EmailService;
import domain.carport.Carport;
import domain.carport.Carport.Roof;
import domain.carport.shed.Shed;
import domain.customer.Customer;
import domain.material.Material;
import domain.material.Options;
import domain.material.Tree;
import domain.order.Order;
import domain.order.Order.Status;
import domain.order.exceptions.OrderException;
import domain.order.exceptions.OrderNotFound;
import domain.partslist.Part;
import domain.partslist.Partslist;
import domain.svg.SVGSide;
import domain.svg.SVGTop;
import domain.user.User;
import domain.user.exceptions.InvalidPassword;
import domain.user.exceptions.UserExists;
import domain.user.exceptions.UserNotFound;
import infrastructure.DBCustomer;
import infrastructure.DBMaterial;
import infrastructure.DBOrder;
import infrastructure.DBUser;
import infrastructure.JavaXEmailService;
import infrastructure.LocalSVG;
import infrastructure.PDFService;
import infrastructure.exceptions.DBException;
import infrastructure.Database;
import java.sql.Timestamp;
import java.util.List;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import web.admin.Materials;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("intergration-test")
public class IntergrationTest {

  Api api;

  /**
   * Before you run this script create a user 'cupcaketest' and grant access to the database:
   *
   * <pre>
   * DROP USER IF EXISTS cupcaketest@localhost;
   * CREATE USER cupcaketest@localhost;
   * GRANT ALL PRIVILEGES ON cupcaketest.* TO cupcaketest@localhost;
   * </pre>
   */
  static void resetTestDatabase() {
    String URL =
        "jdbc:mysql://localhost/fogtest?serverTimezone=Europe/Copenhagen&allowPublicKeyRetrieval=true";
    String USER = "fogtest";
    String PASS = "codergram";

    InputStream stream = IntergrationTest.class.getClassLoader().getResourceAsStream("init.sql");
    if (stream == null) throw new RuntimeException("init.sql");
    try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
      conn.setAutoCommit(false);
      ScriptRunner runner = new ScriptRunner(conn);
      runner.setStopOnError(true);
      runner.runScript(new BufferedReader(new InputStreamReader(stream)));
      conn.commit();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    System.out.println("Done running migration");
  }

  @BeforeEach
  void setupAPI() {
    resetTestDatabase();

    String url = "jdbc:mysql://localhost:3306/fogtest?serverTimezone=CET";
    String user = "fogtest";
    String psw = "codergram";

    Database db = new Database(url, user, psw);
    db.runMigrations();

    DBUser dbUser = new DBUser(db);
    JavaXEmailService javaXEmailService = new JavaXEmailService();
    PDFService pDFService = new PDFService();
    LocalSVG localSVG = new LocalSVG();
    DBMaterial dbMaterial = new DBMaterial(db);
    DBOrder dbOrder = new DBOrder(db);
    DBCustomer dbCustomer = new DBCustomer(db);

    api = new Api(dbUser, javaXEmailService, pDFService, localSVG, dbMaterial, dbOrder, dbCustomer);
  }

  @Test
  void createCarport() {
    String name = "test";
    String email = "test@test.dk";
    String password1 = "1234";
    User user = null;
    User user1 = null;

    // Create user
    try {
      user = api.createUser(name, email, password1, User.Role.Employee);
      assertEquals(email, user.getEmail());
    } catch (UserExists userExists) {
      userExists.printStackTrace();
    }

    // Login user
    try {
      user = api.login(user.getEmail(), password1);
      assertEquals(email, user.getEmail());
    } catch (InvalidPassword invalidPassword) {
      invalidPassword.printStackTrace();
    } catch (UserNotFound e) {
      e.getMessage();
    }

    // Invalid login
    try {
      user1 = api.login(user.getEmail(), "2233");
      assertNull(user1.getEmail());
    } catch (InvalidPassword invalidPassword) {
      String exceptionMessage = "" + invalidPassword.getMessage();
    } catch (UserNotFound e) {
      e.getMessage();
    }

    Carport carport = null;
    Shed shed = null;
    double length = 600;
    double width = 300;
    double shedLength;
    double shedWidth = 0.0;
    boolean withShed = true;
    String shedSize = "whole";
    Enum<Carport.Roof> roofType = Roof.Peak;

    // Calculate Shed dimensions
    if (roofType == Carport.Roof.Peak) {
      shedLength = 225.0;
      if (shedSize != null) {
        if (shedSize.equals("whole")) {
          shedWidth = width - 40.0;
        } else {
          shedWidth = (width / 2.0) - 40.0;
        }
      }
    } else {
      shedLength = 210;
      if (shedSize != null) {
        if (shedSize.equals("whole")) {
          shedWidth = width - 75.0;
        } else {
          shedWidth = (width / 2.0) - 75.0;
        }
      }
    }

    // Create shed
    if (withShed) {
      shed = new Shed(shedLength, shedWidth);
    }


    try {
      // Get all Materiel's from DB
      List<Material> allMaterialsFromDB = api.getAllMaterielsFromDB();

      // Get the local empty Partlist
      List<Part> localPartlist = api.getLocalPartslist();

      // Carport object
      carport = new Carport(length, width, roofType, shed);

      // Add to the local Partlist with compared Materiel from DB
      List<Part> carportPartslist =
          api.addToLocalPartslist(carport, allMaterialsFromDB, localPartlist);

      // Create partslist
      Partslist partslist = new Partslist(carportPartslist);

      // Adds created partlist to the carport
      carport.setPartslist(partslist);

    } catch (DBException e) {
      e.printStackTrace();
    }

    assertEquals(600, carport.getLength());
    assertEquals(300, carport.getWidth());
    assertEquals(33, carport.getPartslist().getPartList().size());

    String expectedResult =
        "<svg version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\"\n"
            + "     height=\"100%\"  width=\"100%\" viewBox=\"0 0 750.0 405.0\"\n"
            + "     preserveAspectRatio=\"xMinYMin\" ";

    String svgSide = api.getSVGSide(carport, false);

    //System.out.println(svgSide.getSvgCode());

    assertTrue(svgSide.startsWith(expectedResult));

    String expectedResult1 =
        "<svg version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\"\n"
            + "     xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n"
            + "     height=\"100%\"  width=\"100%\" viewBox=\"0 0 700.0 400.0\"\n"
            + "     preserveAspectRatio=\"xMinYMin\" ";

    String svgTop = api.getSVGTop(carport, false);

    //System.out.println(svgTop.getSvgTop());

    assertTrue(svgTop.startsWith(expectedResult1));

    //Get carport price
    assertEquals(13241.5975, carport.getPrice());

    Customer customer = new Customer(1, "testCustoemr", "", 0, "", 0, "test@testCustomer.dk");

    Timestamp time = new Timestamp(System.nanoTime());
    Order order = new Order(
        1,
        width,
        length,
        time,
        user,
        customer,
        Status.valueOf("New"),
        carport,
        30);

    try {
      //Create order
      order = api.createOrder(order, customer);
      assertEquals(600, order.getLength());

      //Get orders
      List<Order> allOrders = api.getOrders();
      assertEquals(3, allOrders.size());

      //Change status
      api.changeOrderStatus(order.getId(), "Awaiting");
      order = api.getOrderById(order.getId());
      assertEquals("Awaiting", order.getStatus().name());

      //Change price
      api.updatePrice(order.getId(), 16000);
      order = api.getOrderById(order.getId());
      assertEquals(15999.82528, order.getCarport().getPrice() * (order.getMargin()/100 + 1));

      //Release
      api.releaseOrder(order.getId());
      order = api.getOrderById(order.getId());
      assertEquals(false, order.hasSalesman());

    } catch (DBException e) {
      e.printStackTrace();
    } catch (OrderException e) {
      e.printStackTrace();
    } catch (OrderNotFound orderNotFound) {
      orderNotFound.printStackTrace();
    }
  }


  @Test
  void getMaterials() {
    try {
      List<Material> getAllRawMaterielsFromDB = api.getAllRawMaterielsFromDB();

      assertEquals(34, getAllRawMaterielsFromDB.size());

      assertEquals(1, getAllRawMaterielsFromDB.get(0).getId());
      assertEquals(10.95, getAllRawMaterielsFromDB.get(0).getPrice());
      assertEquals("Stk", getAllRawMaterielsFromDB.get(0).getUnit().name());

      api.updateMaterial(1, "testTree", 20, Material.Unit.valueOf("Pk"));

      getAllRawMaterielsFromDB = api.getAllRawMaterielsFromDB();
      assertEquals(20.00, getAllRawMaterielsFromDB.get(0).getPrice());
      assertEquals("Pk", getAllRawMaterielsFromDB.get(0).getUnit().name());

    } catch (DBException e) {
      e.printStackTrace();
    }
  }


}
