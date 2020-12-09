package web.website;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
    name = "Shop",
    urlPatterns = {"/Shop"})
public class Shop extends BaseServlet {
  private static final Logger log = getLogger(Shop.class);

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      render("Din carport", "/WEB-INF/pages/customer/carport.jsp", request, response);
    } catch (ServletException | IOException ex) {
      log.error(ex.getMessage());
    }
  }
}
