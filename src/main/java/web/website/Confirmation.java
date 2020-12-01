package web.website;

import domain.carport.Carport;
import domain.carport.shed.Shed;
import org.slf4j.Logger;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet(name = "Confirmation", urlPatterns = { "/Confirmation" } )
public class Confirmation extends BaseServlet {

    private static final Logger log = getLogger(Confirmation.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isCustomer = true;
        boolean withShed;
        double shedLenght, shedWidth;
        Enum<Carport.Roof> roofType;
        Carport carport;
        Shed shed;

        double length = Double.parseDouble(req.getParameter("length"));
        double width = Double.parseDouble(req.getParameter("width"));
        String roof = req.getParameter("roof");
        String shedOption = req.getParameter("shedOption");
        String shedSize = req.getParameter("shedSize");

        if(roof.equals("peak")){
            shedLenght = 225;
            if(shedSize.equals("whole")){
                shedWidth = width - 40;
            } else {
                shedWidth = (width / 2) - 40;
            }
        } else {
            shedLenght = 210;
            if(shedSize.equals("whole")){
                shedWidth = width - 75;
            } else {
                shedWidth = (width / 2) - 75;
            }
        }

        if(roof.equals("peak")){
            roofType = Carport.Roof.valueOf("Peak");
        } else {
            roofType = Carport.Roof.valueOf("Flat");
        }

        if(shedOption.equals("Jeg ønsker et skur")){
            shed = new Shed(shedLenght, shedWidth);
        } else {
            shed = null;
        }

        carport = new Carport(length, width, roofType, shed);

        String svgSide = api.getSVGSide(carport, true);
        String svgTop = api.getSVGTop(carport, true);

        req.setAttribute("svgSide", svgSide);
        req.setAttribute("svgTop", svgTop);

        render("Bekræft Carport", "/WEB-INF/pages/customer/confirmation", req, resp);
    }

}
