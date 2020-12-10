/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package web.website;

import static org.slf4j.LoggerFactory.getLogger;

import domain.order.Order;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import web.BaseServlet;

@WebServlet("/ViewOrder/*")
public class ViewOrder extends BaseServlet {

  private static final Logger log = getLogger(ViewOrder.class);

  /**
   * Renders the index.jsp page
   *
   * @see BaseServlet
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      resp.setCharacterEncoding("UTF-8");
      req.setCharacterEncoding("UTF-8");

      String uuidReq = req.getPathInfo().substring(1);
      int orderId = -1;

      log.info("UUID requested: {}", uuidReq);
      orderId = api.getOrderByUUID(uuidReq);

      Order order = api.getOrderById(orderId);

      String svgSide = api.getSVGSide(order.getCarport(), !order.isPaid());
      String svgTop = api.getSVGTop(order.getCarport(), !order.isPaid());

      List<String> statuslist = new ArrayList<>();
      for (Order.Status s : Order.Status.values()) {
        statuslist.add(s.name());
      }
      req.setAttribute("statuslist", statuslist);

      // Save requests and sessions
      req.setAttribute("svgSide", svgSide);
      req.setAttribute("svgTop", svgTop);
      req.setAttribute("carport", order.getCarport());
      req.setAttribute("order", order);

      render("Ordre", "/WEB-INF/pages/customer/vieworder.jsp", req, resp);

    } catch (Exception e) {
      log(e.getMessage());
    }
  }
}
