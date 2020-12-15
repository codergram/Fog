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
import domain.user.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import web.BaseServlet;

@WebServlet("/api/users/")
public class Users extends BaseServlet {

  private static final Logger log = getLogger(Users.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    try {
      if (authorizeApi(req)) {
        List<User> users = List.copyOf(api.getUsers());
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        out.print(new Gson().toJson(users));
        out.flush();
      }
    } catch (Exception e) {
      log.error(e.getMessage());
      error(resp, 401);
    }
  }
}
