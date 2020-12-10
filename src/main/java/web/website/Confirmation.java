/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package web.website;

import static org.slf4j.LoggerFactory.getLogger;

import domain.carport.Carport;
import domain.carport.shed.Shed;
import domain.customer.Customer;
import domain.material.materials.Material;
import domain.order.Order;
import domain.order.exceptions.OrderException;
import domain.partslist.Part;
import domain.partslist.Partslist;
import infrastructure.exceptions.DBException;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import web.BaseServlet;

@WebServlet(
    name = "Confirmation",
    urlPatterns = {"/Confirmation"})
public class Confirmation extends BaseServlet {

  private static final Logger log = getLogger(Confirmation.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setCharacterEncoding("UTF-8");

    boolean isCustomer = true;
    boolean withShed = false;
    double length = 0.0;
    double width = 0.0;
    double shedLength;
    double shedWidth = 0.0;
    String actionVal = null;
    Enum<Carport.Roof> roofType = null;
    Carport carport = null;
    Shed shed = null;

    try {
      actionVal = req.getParameter("action");
      length = Double.parseDouble(req.getParameter("length"));
      width = Double.parseDouble(req.getParameter("width"));
      withShed = req.getParameter("shedOption").equalsIgnoreCase("on");
    } catch (NullPointerException | NumberFormatException e) {
      log.warn(e.getMessage());
    }
    String roof = req.getParameter("roof");
    if (roof.equalsIgnoreCase("peak")) {
      roofType = Carport.Roof.Peak;
    } else {
      roofType = Carport.Roof.Flat;
    }

    String shedSize = req.getParameter("shedSize");

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

    // Get Carport drawing
    String svgSide = api.getSVGSide(carport, isCustomer);
    String svgTop = api.getSVGTop(carport, isCustomer);

    // Save requests and sessions
    req.setAttribute("svgSide", svgSide);
    req.setAttribute("svgTop", svgTop);
    req.getSession().setAttribute("carport", carport);

    try {
      if (actionVal != null) {
        if (actionVal.equals("preview")) {
          System.out.println("preview carport");
          req.setAttribute("showCarport", true);
          render("Se din carport", "/WEB-INF/pages/customer/carport.jsp", req, resp);
        } else {
          render("Bekræft Carport", "/WEB-INF/pages/customer/confirmation.jsp", req, resp);
        }
      }
    } catch (ServletException | IOException e) {
      log.error(e.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setCharacterEncoding("UTF-8");

    Carport carport = (Carport) req.getSession().getAttribute("carport");

    String name = "";
    String email = "";
    String address = "";
    String city = "";
    int zip = 0;
    int phone = 0;

    try {
      name = req.getParameter("name");
      email = req.getParameter("email");
      address = req.getParameter("address");
      zip = Integer.parseInt(req.getParameter("zip"));
      city = req.getParameter("city");
      phone = Integer.parseInt(req.getParameter("phone"));
    } catch (NumberFormatException e) {
      log.error(e.getMessage());
    }

    Customer customer = new Customer(name, address, zip, city, phone, email);

    Order order = new Order(carport.getWidth(), carport.getLength(), customer, carport);

    try {
      order = api.createOrder(order, customer);
      String orderUrl = req.getParameter("orderurl") + order.getUuid().toString();
      api.sendLinkByMail(order, orderUrl);
      req.setAttribute("orderUrl", orderUrl);
    } catch (OrderException | DBException e) {
      log.error(e.getMessage());
    }

    req.setAttribute("order", order);

    try {
      render("Bekræft Carport", "/WEB-INF/pages/customer/thankyou.jsp", req, resp);
    } catch (ServletException | IOException e) {
      log.error(e.getMessage());
    }
  }
}
