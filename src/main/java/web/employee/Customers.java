package web.employee;

import domain.customer.Customer;
import domain.user.User;
import org.slf4j.Logger;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet(
    name = "Customers",
    urlPatterns = {"/Customers"})
public class Customers extends BaseServlet {

  private static final Logger log = getLogger(Customers.class);

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

      if (curUser == null || !curUser.isAdmin()) {
        log("User is not admin: " + curUser);
        resp.sendError(401);
      } else {
        List<Customer> customers = List.copyOf(api.getCustomers());
        req.setAttribute("customerlist", customers);
        log("User is admin: " + curUser);
        render("Customers", "/WEB-INF/pages/sales/customers.jsp", req, resp);
      }

    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    redirect(req, resp, "Customers");
  }
}
