/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package web.employee;

import static org.slf4j.LoggerFactory.getLogger;

import domain.order.Order;
import domain.user.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import web.BaseServlet;

@WebServlet("/Ordre/View/*")
public class ViewOrder extends BaseServlet {
  private static final Logger log = getLogger(ViewOrder.class);

  private static final String encoding = "UTF-8";

  /**
   * Renders the index.jsp page
   *
   * @see BaseServlet
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      resp.setCharacterEncoding(encoding);
      req.setCharacterEncoding(encoding);

      User curUser = (User) req.getSession().getAttribute("user");

      log("Trying to log into admin :" + curUser);

      if (curUser == null || !curUser.isEmployee()) {
        log("User is not admin: " + curUser);
        resp.sendError(401);
      } else {
        int orderId = Integer.parseInt(req.getPathInfo().substring(1));
        Order order = null;

        for (Order o : api.getOrders()) {
          if (o.getId() == orderId) {
            order = o;
          }
        }

        req.setAttribute("order", order);
        log("User is admin: " + curUser);

        String svgSide = "";
        String svgTop = "";
        if (order != null) {
          if (!order.getSalesEmployee().getEmail().equals(curUser.getEmail())) {
            log.error("{} not assigned to order {}", curUser.getEmail(), order.getId());
            resp.sendError(401);
          }
          svgSide = api.getSVGSide(order.getCarport(), false);
          svgTop = api.getSVGTop(order.getCarport(), false);

          List<String> statuslist = new ArrayList<>();
          for (Order.Status s : Order.Status.values()) {
            statuslist.add(s.name());
          }
          req.setAttribute("statuslist", statuslist);

          // Save requests and sessions
          req.setAttribute("svgSide", svgSide);
          req.setAttribute("svgTop", svgTop);
          req.setAttribute("carport", order.getCarport());
        }

        render("Ordre", "/WEB-INF/pages/sales/vieworder.jsp", req, resp);
      }

    } catch (Exception e) {
      log(e.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      resp.setCharacterEncoding(encoding);
      req.setCharacterEncoding(encoding);

      int orderId = Integer.parseInt(req.getParameter("ordrenummer"));
      switch (req.getParameter("action")) {
        case "updatePrice":
          double newPrice = Double.parseDouble(req.getParameter("salgspris"));
          api.updatePrice(orderId, newPrice);
          break;
        case "changeStatus":
          api.changeOrderStatus(orderId, req.getParameter("statusvalue"));
          break;
        case "sendLink":
          Order o = api.getOrderById(orderId);
          String linkFromReq = req.getParameter("ordreurl");
          String link = "<a href=\"" + linkFromReq + "\">" + linkFromReq + "</a>";
          api.sendLinkByMail(o, link);
          createAlert(req, "success", "Mailen blev sendt til " + o.getCustomer().getEmail());
          break;
        case "createPdf":
          Order pdfOrder = api.getOrderById(orderId);
          File pdfFile = api.createPdf(pdfOrder);
          String downloadUrl = req.getParameter("downloadurl");
          sendMail(pdfOrder.getCustomer().getEmail(), downloadUrl, pdfFile);
          break;
        default:
          break;
      }
      redirect(req, resp, "Ordre/View/" + orderId);
    } catch (Exception e) {
      log.error(e.getMessage());
      createAlert(req, "alert", e.getMessage());
      redirect(req, resp, "Ordre");
    }
  }

  private void sendMail(String email, String url, File pdf) {
    String message =
        "Du er nu et skridt nærmere din nye carport!<br>Din vejledning er vedhæftet denne mail og kan ligeledes findes her: "
            + url
            + "<br><br>Tak fordi du valgte Fog!";
    api.sendMail(email, "Din nye carport", "Tillykke!", message, pdf);
  }

  private void createAlert(HttpServletRequest req, String type, String message) {
    req.setAttribute("alert", true);
    req.setAttribute("alertMsg", message);
    req.setAttribute("alertType", type);
  }
}
