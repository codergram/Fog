package web.employee;

import domain.order.Order;
import domain.user.User;
import org.slf4j.Logger;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet(
    name = "Ordre",
    urlPatterns = {"/Ordre"})
public class Orders extends BaseServlet {

  private static final Logger log = getLogger(Orders.class);

  /**
   * Renders the index.jsp page
   *
   * @see BaseServlet
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    try {
      User curUser = (User) req.getSession().getAttribute("user");

      log("Trying to log into admin :" + curUser);

      if (curUser == null || !curUser.isEmployee()) {
        log("User is not admin: " + curUser);
        resp.sendError(401);
      } else {
        List<Order> orders = List.copyOf(api.getOrders());

        List<String> statuslist = new ArrayList<>();
        for (Order.Status s : Order.Status.values()) {
          statuslist.add(s.name());
        }

        req.setAttribute("orderlist", orders);
        req.setAttribute("statuslist", statuslist);
        req.setAttribute("currentUser", curUser);

        log("User is admin: " + curUser);
        render("Ordre", "/WEB-INF/pages/sales/orders.jsp", req, resp);
      }

    } catch (Exception e) {
      log(e.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      User curUser = (User) req.getSession().getAttribute("user");

      int orderId = Integer.parseInt(req.getParameter("ordrenummer"));
      switch (req.getParameter("action")) {
        case "assignOrder":
          api.assignOrder(orderId, curUser.getId());
          break;
        case "releaseOrder":
          api.releaseOrder(orderId);
          break;
        case "changeStatus":
          api.changeOrderStatus(orderId, req.getParameter("statusvalue"));
          break;
        default:
          break;
      }
      redirect(req, resp, "Ordre");

    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }
}
