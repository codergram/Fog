/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package intergration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

import api.Api;
import domain.carport.Carport;
import domain.carport.Carport.Roof;
import domain.carport.shed.Shed;
import domain.customer.Customer;
import domain.material.Material;
import domain.order.Order;
import domain.order.Order.Status;
import domain.order.exceptions.OrderException;
import domain.order.exceptions.OrderNotFound;
import domain.partslist.Part;
import domain.partslist.Partslist;
import domain.user.User;
import domain.user.User.Role;
import domain.user.exceptions.InvalidPassword;
import domain.user.exceptions.UserExists;
import domain.user.exceptions.UserNotFound;
import infrastructure.DBCustomer;
import infrastructure.DBMaterial;
import infrastructure.DBOrder;
import infrastructure.DBUser;
import infrastructure.Database;
import infrastructure.JavaXEmailService;
import infrastructure.LocalSVG;
import infrastructure.PDFService;
import infrastructure.exceptions.DBException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import jdk.jfr.Description;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;

@Tag("integration-test")
@DisplayName("Integration test")
@Description("Integration test of selected user stories")
@TestMethodOrder(MethodOrderer.MethodName.class)
class IntergrationTest {

  private static final Logger log = getLogger(IntergrationTest.class);
  private static Api api;

  static User user = null;
  Carport carport = null;
  Shed shed = null;

  /**
   * Before you run this integration test, create a user 'cupcaketest' and grant access to the
   * database: You can use the following script:
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
      runner.setLogWriter(null);
      runner.runScript(new BufferedReader(new InputStreamReader(stream)));
      conn.commit();
    } catch (SQLException throwables) {
      log.error(throwables.getMessage());
    }
    log.info("Migration script done");
  }

  @BeforeAll
  static void setupAPI() {
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

    // Create user
    try {
      api.createUser("test", "test@test.dk", "1234", Role.Employee);
    } catch (UserExists userExists) {
      userExists.printStackTrace();
    }
  }

  @Test
  @DisplayName("Userstory 1")
  void us1() {
    String email = "test@test.dk";
    String password = "1234";

    // Login user
    log.info("Trying to login with correct credentials");
    try {
      user = api.login(email, password);
      assertEquals(email, user.getEmail());
    } catch (InvalidPassword | UserNotFound e) {
      log.warn(e.getMessage());
    }

    // Invalid login
    log.info("Trying to login with wrong credentials");
    try {
      User user1 = api.login(user.getEmail(), "2233");
      assertNull(user1.getEmail());
    } catch (InvalidPassword | UserNotFound e) {
      log.warn(e.getMessage());
    }
  }

  @Test
  @DisplayName("Userstory 2")
  @Description(
      "Som sælger skal man kunne se alle ordre, så sælger nemt kan få et overblik over, hvad status er på alle ordrerne.")
  void us2() {
    if (user.getRole() == Role.Employee) {
      try {
        log.info("Getting all orders from database");
        List<Order> allOrdersFromDB = api.getOrders();
        assertEquals(2, allOrdersFromDB.size());
        log.info("Found {} expected {}", allOrdersFromDB.size(), 2);
      } catch (OrderNotFound e) {
        log.warn(e.getMessage());
      }
    }
  }

  @Test
  @DisplayName("Userstory 3, 4, 7")
  @Description(
      "Som sælger skal det være tydeligt når en ny forespørgsel er kommet, så sælgerne ikke overser nye forespørgsler.%n"
          + "Som sælger skal man kunne ændre i prisen på et tilbud, så sælger måske alligevel kan få et salg igennem, når en kunde er tøvende.%n"
          + "Som sælger skal det være muligt at redigere i salgsprisen, så sælgeren bedre kan matche kundens købspris og derved gennemføre et salg")
  void us3_4_7() {
    double length = 600;
    double width = 300;
    double shedLength;
    double shedWidth = 0.0;
    boolean withShed = true;
    String shedSize = "whole";
    Enum<Roof> roofType = Roof.Peak;

    // Calculate Shed dimensions
    if (roofType == Roof.Peak) {
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
      log.info("Adding shed: {}", shed);
    }

    try {
      // Get all Materiel's from DB
      List<Material> allMaterialsFromDB = api.getAllMaterielsFromDB();

      // Get the local empty Drawing
      List<Part> localPartlist = api.getLocalPartslist();

      // Carport object
      carport = new Carport(length, width, roofType, shed);
      log.info("Carport created: {}", carport);

      // Add to the local Drawing with compared Materiel from DB
      List<Part> carportPartslist =
          api.addToLocalPartslist(carport, allMaterialsFromDB, localPartlist);

      // Create partslist
      Partslist partslist = new Partslist(carportPartslist);
      log.info("Partlist generated");

      // Adds created partlist to the carport
      carport.setPartslist(partslist);

    } catch (DBException e) {
      log.error(e.getMessage());
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
    log.info("SVG for side generated");

    assertTrue(svgSide.startsWith(expectedResult));

    String expectedResult1 =
        "<svg version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\"\n"
            + "     xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n"
            + "     height=\"100%\"  width=\"100%\" viewBox=\"0 0 700.0 400.0\"\n"
            + "     preserveAspectRatio=\"xMinYMin\" ";

    String svgTop = api.getSVGTop(carport, false);

    log.info("SVG for top generated");

    assertTrue(svgTop.startsWith(expectedResult1));

    // Get carport price
    assertEquals(13241.5975, carport.getPrice());

    Customer customer = new Customer(1, "testCustoemr", "", 0, "", 0, "test@testCustomer.dk");

    log.info("Customer created: {}", customer);

    Timestamp time = new Timestamp(System.nanoTime());
    Order order =
        new Order(1, width, length, time, user, customer, Status.valueOf("New"), carport, 30);

    try {
      // Create order
      order = api.createOrder(order, customer);
      assertEquals(600, order.getLength());
      log.info("Order created: {}", order);

      // Get orders
      List<Order> allOrders = api.getOrders();
      assertEquals(3, allOrders.size());
      log.info("Found {} orders in database: {}", allOrders.size(), allOrders);

      // Change status
      String oldStatus = order.getStatus().name();
      api.changeOrderStatus(order.getId(), "Awaiting");
      order = api.getOrderById(order.getId());
      assertEquals("Awaiting", order.getStatus().name());
      log.info("Order status changed from {} to {}", oldStatus, order.getStatus().name());

      // Change price
      api.updatePrice(order.getId(), 16000);
      order = api.getOrderById(order.getId());
      assertEquals(15999.82528, order.getCarport().getPrice() * (order.getMargin() / 100 + 1));
      log.info("Order price changed");

      // Release
      api.releaseOrder(order.getId());
      order = api.getOrderById(order.getId());
      assertFalse(order.hasSalesman());
      log.info("Order released from salesman");

    } catch (DBException | OrderException | OrderNotFound e) {
      log.error(e.getMessage());
    }
  }

  @Test
  @DisplayName("Userstory 5, 10")
  @Description(
      "Som sælger skal man kunne se styklisten for en ordre, så sælger kan dobbelt tjekke om der er nok materialer til at lave en stabil konstruktion.%n"
          + "som admin skal man kunne rette i eksisterende materialer, så materialelisten er ”up to date” med det eksisterende lager. ")
  void us5_10() {
    try {
      List<Material> getAllRawMaterielsFromDB = api.getAllRawMaterielsFromDB();
      log.info("Found {} orders in database: {}", getAllRawMaterielsFromDB.size(),
          getAllRawMaterielsFromDB);

      assertEquals(34, getAllRawMaterielsFromDB.size());

      assertEquals(1, getAllRawMaterielsFromDB.get(0).getId());
      assertEquals(10.95, getAllRawMaterielsFromDB.get(0).getPrice());
      assertEquals("Stk", getAllRawMaterielsFromDB.get(0).getUnit().name());

      api.updateMaterial(1, "testTree", 20, Material.Unit.valueOf("Pk"));

      getAllRawMaterielsFromDB = api.getAllRawMaterielsFromDB();
      assertEquals(20.00, getAllRawMaterielsFromDB.get(0).getPrice());
      assertEquals("Pk", getAllRawMaterielsFromDB.get(0).getUnit().name());

      log.info("Material updated in database");

    } catch (DBException e) {
      log.error(e.getMessage());
    }
  }
}
