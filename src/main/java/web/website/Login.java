/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package web.website;

import static org.slf4j.LoggerFactory.getLogger;

import domain.user.User;
import domain.user.exceptions.InvalidPassword;
import domain.user.exceptions.UserNotFound;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import web.BaseServlet;

@WebServlet(
    name = "Login",
    urlPatterns = {"/Login"})
public class Login extends BaseServlet {

  private static final Logger log = getLogger(Login.class);

  protected void render(HttpServletRequest request, HttpServletResponse response) {
    try {
      super.render("Log ind", "/WEB-INF/pages/login.jsp", request, response);
    } catch (ServletException | IOException e) {
      log.error(e.getMessage());
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    render(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      request.setAttribute("providedMail", request.getParameter("inputEmail"));
      User curUser = login(request);

      if (curUser.isAdmin() || curUser.isEmployee()) {
        response.sendRedirect(request.getContextPath() + "/Ordre");
      } else {
        response.sendRedirect(request.getContextPath() + "/");
      }

    } catch (InvalidPassword | UserNotFound i) {
      log.info(i.getMessage());
      request.setAttribute("errorMsg", i.getMessage());
      request.setAttribute("error", true);
      render(request, response);
    }
  }

  private User login(HttpServletRequest req) throws InvalidPassword, UserNotFound {
    HttpSession session = req.getSession();

    String usrEmail = req.getParameter("inputEmail");
    String usrPassword = req.getParameter("inputPassword");

    User curUsr = api.login(usrEmail, usrPassword);

    session.setAttribute("user", curUsr);
    session.setAttribute("userrole", curUsr.getRole().name());

    return curUsr;
  }
}
