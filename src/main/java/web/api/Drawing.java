/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package web.api;

import static org.slf4j.LoggerFactory.getLogger;

import domain.carport.Carport;
import domain.carport.Carport.Roof;
import domain.carport.shed.Shed;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import web.BaseServlet;

@WebServlet("/api/carport/drawing/")
public class Drawing extends BaseServlet {

  private static final Logger log = getLogger(Drawing.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    try {
      if (authorizeApi(req)) {
        String view = req.getParameter("view");
        double width = Double.parseDouble(req.getParameter("width"));
        double length = Double.parseDouble(req.getParameter("length"));
        String roofTypeStr = req.getParameter("rooftype");
        roofTypeStr = roofTypeStr.substring(0, 1).toUpperCase() + roofTypeStr.substring(1);
        Roof roofType = Roof.valueOf(roofTypeStr);
        boolean dimensions = !req.getParameter("dimensions").equals("true");

        Shed shed = null;
        String shedSize = req.getParameter("shedsize");
        double shedWidth;
        double shedLength;

        if (shedSize != null && !shedSize.isEmpty()) {
          if (roofType == Roof.Peak) {
            shedLength = 225.0;
            if (shedSize.equals("whole")) {
              shedWidth = width - 40.0;
            } else {
              shedWidth = (width / 2.0) - 40.0;
            }
          } else {
            shedLength = 210;
            if (shedSize.equals("whole")) {
              shedWidth = width - 75.0;
            } else {
              shedWidth = (width / 2.0) - 75.0;
            }
          }
          shed = new Shed(shedLength, shedWidth);
        }

        Carport c = new Carport(length, width, roofType, shed);

        log.info("API request for the following: {}", c);

        String svgSource;
        if (view.equals("top")) {
          svgSource = api.getSVGTop(c, dimensions);
        } else {
          svgSource = api.getSVGSide(c, dimensions);
        }

        PrintWriter out = resp.getWriter();
        resp.setContentType("image/svg+xml");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        out.print(svgSource);
        out.flush();
      }
    } catch (Exception e) {
      log.error(e.getMessage());
      error(resp, 401);
    }
  }
}
