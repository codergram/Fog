/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package web;

import static org.slf4j.LoggerFactory.getLogger;

import api.Api;
import api.exceptions.ApiError;
import domain.user.User;
import domain.user.exceptions.InvalidPassword;
import domain.user.exceptions.UserNotFound;
import infrastructure.DBCustomer;
import infrastructure.DBMaterial;
import infrastructure.DBOrder;
import infrastructure.DBUser;
import infrastructure.Database;
import infrastructure.JavaXEmailService;
import infrastructure.LocalSVG;
import infrastructure.PDFService;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import web.widget.Navbar;

public class BaseServlet extends HttpServlet {

  protected static final Api api;
  protected static final boolean apiAuth = false;
  private static final Logger log = getLogger(BaseServlet.class);

  static {
    api = createFogApi();
  }

  private static Api createFogApi() {

    Database database = new Database();

    return new Api(
        new DBUser(database),
        new JavaXEmailService(),
        new PDFService(),
        new LocalSVG(),
        new DBMaterial(database),
        new DBOrder(database),
        new DBCustomer(database));
  }

  protected void render(
      String title, String content, HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    request.setAttribute("title", Api.genericSiteTitle + " - " + title);
    request.setAttribute("content", content);
    request.setAttribute("navbar", new Navbar(request));
    request.getRequestDispatcher("/WEB-INF/includes/base.jsp").forward(request, response);
  }

  protected void redirect(HttpServletRequest req, HttpServletResponse resp, String servletName) {
    try {
      req.setCharacterEncoding("UTF-8");
      resp.setCharacterEncoding("UTF-8");
      resp.sendRedirect(req.getContextPath() + "/" + servletName);
    } catch (IOException ee) {
      log.info(ee.getMessage());
    }
  }

  protected void error(HttpServletResponse resp, int status) {
    resp.setStatus(status);
    log.error("API error status {}", status);
  }

  protected boolean authorizeApi(HttpServletRequest req) throws ApiError {
    try {
      String authHeader = req.getHeader("Authorization").replace("Basic ", "");
      String[] credential =
          new String(Base64.getDecoder().decode(authHeader), StandardCharsets.UTF_8).split(":");
      String apiUsername = credential[0];
      String apiPassword = credential[1];

      User tmpUser = api.login(apiUsername, apiPassword);

      log.info("API Auth Key: {}", authHeader);
      log.info("API user: {}", apiUsername);

      return tmpUser != null && tmpUser.isAdmin();
    } catch (InvalidPassword | UserNotFound invalidPassword) {
      throw new ApiError("Not authorized!");
    }
  }
}
