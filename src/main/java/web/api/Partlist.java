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
import domain.carport.Carport;
import domain.carport.Carport.Roof;
import domain.carport.shed.Shed;
import domain.material.Material;
import domain.partslist.Part;
import domain.partslist.Partslist;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import web.BaseServlet;

@WebServlet("/api/carport/partlist/")
public class Partlist extends BaseServlet {

  private static final Logger log = getLogger(Partlist.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    try {
      if (authorizeApi(req)) {
        double width = Double.parseDouble(req.getParameter("width"));
        double length = Double.parseDouble(req.getParameter("length"));
        String roofTypeStr = req.getParameter("rooftype");
        roofTypeStr = roofTypeStr.substring(0, 1).toUpperCase() + roofTypeStr.substring(1);
        Roof roofType = Roof.valueOf(roofTypeStr);

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

        // Get all Materiel's from DB
        List<Material> allMaterialsFromDB = api.getAllMaterielsFromDB();

        // Get the local empty Drawing
        List<Part> localPartlist = api.getLocalPartslist();

        // Carport object
        Carport c = new Carport(length, width, roofType, shed);

        // Add to the local Drawing with compared Materiel from DB
        List<Part> carportPartslist = api.addToLocalPartslist(c, allMaterialsFromDB, localPartlist);

        // Create partslist
        Partslist partslist = new Partslist(carportPartslist);

        // Adds created partlist to the carport
        c.setPartslist(partslist);

        log.info("API request for the following: {}", c);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        out.print(new Gson().toJson(c.getPartslist().getPartList()));
        out.flush();
      }
    } catch (Exception e) {
      log.error(e.getMessage());
      error(resp, 401);
    }
  }
}
