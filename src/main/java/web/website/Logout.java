/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package web.website;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import web.BaseServlet;

@WebServlet(
    name = "Logout",
    urlPatterns = {"/Logout"})
public class Logout extends BaseServlet {
  private static final Logger log = getLogger(Logout.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    try {
      req.getSession().setAttribute("user", null);
      req.getSession().invalidate();
      log.info("logged out");
      resp.sendRedirect(req.getContextPath() + "/");
    } catch (IOException e) {
      log.warn(e.getMessage());
    }
  }
}
