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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import web.BaseServlet;

@WebServlet("/api/user/*")
public class User extends BaseServlet {

  private static final Logger log = getLogger(User.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      String userId = req.getPathInfo().substring(1);

      domain.user.User user = null;
      List<domain.user.User> users = List.copyOf(api.getUsers());
      for (domain.user.User u : users) {
        if (u.getId() == Integer.parseInt(userId)) {
          user = u;
        }
      }

      if (user != null) {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        out.print(new Gson().toJson(user));
        out.flush();
      }
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      String action = req.getPathInfo().substring(1);
      switch (action) {
        case "delete":
          int id = Integer.parseInt(req.getHeader("userid"));
          if (apiAuth) api.deleteUser(id);
          log.info("Deleted user with ID {}", id);
          break;
        case "create":
          int idFromDb = 0;
          String name = req.getHeader("name");
          String email = req.getHeader("email");
          String password = req.getHeader("password");
          domain.user.User.Role role = domain.user.User.Role.valueOf(req.getHeader("role"));
          if (apiAuth) idFromDb = api.createUser(name, email, password, role).getId();
          log.info("New user got ID {}", idFromDb);
          break;
        case "update":
          System.out.println("update");
          break;
        default:
          error(resp, 500);
          break;
      }
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }
}
