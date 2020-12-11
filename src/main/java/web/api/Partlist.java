/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package web.api;

import static org.slf4j.LoggerFactory.getLogger;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import web.BaseServlet;

@WebServlet("/api/partlist/")
public class Partlist extends BaseServlet {

  private static final Logger log = getLogger(Partlist.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      String orderId = req.getPathInfo().substring(1);

      domain.order.Order o = api.getOrderById(Integer.parseInt(orderId));

      if (o != null) {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        out.print(new Gson().toJson(o));
        out.flush();
      }
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }
}
