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
        boolean withShed = false;
        double length = 0.0;
        double width = 0.0;
        double shedLength = 0.0;
        double shedWidth = 0.0;
        Enum<Carport.Roof> roofType = null;
        Carport carport = null;
        Shed shed = null;
        
        try {
            length = Double.parseDouble(req.getParameter("length"));
            width = Double.parseDouble(req.getParameter("width"));
            withShed = req.getParameter("shedOption").equalsIgnoreCase("on");
        } catch (NullPointerException | NumberFormatException e){
            log.warn(e.getMessage());
        }
        String roof = req.getParameter("roof");
        String shedSize = req.getParameter("shedSize");
    
        System.out.println("width: " + width);
        System.out.println("length: " + length);
        System.out.println("shedSize: " + shedSize);
        
        
        if(roof.equals("peak")){
            shedLength = 225.0;
            if(shedSize != null) {
                if (shedSize.equals("whole")) {
                    shedWidth = width - 40.0;
                } else {
                    shedWidth = (width / 2.0) - 40.0;
                }
            }
        } else {
            shedLength = 210;
            
            if(shedSize != null) {
                if (shedSize.equals("whole")) {
                    shedWidth = width - 75.0;
                } else {
                    shedWidth = (width / 2.0) - 75.0;
                }
            }
        }

        if(roof.equals("peak")){
            roofType = Carport.Roof.valueOf("Peak");
        } else {
            roofType = Carport.Roof.valueOf("Flat");
        }

        if(withShed){
            shed = new Shed(shedLength, shedWidth);
        } else {
            //shed = new Shed(0.0,0.0);
            shed = null;
        }

        carport = new Carport(length, width, roofType, shed);

        String svgSide = api.getSVGSide(carport, isCustomer);
        String svgTop = api.getSVGTop(carport, isCustomer);

        req.setAttribute("svgSide", svgSide);
        req.setAttribute("svgTop", svgTop);

        render("Bekræft Carport", "/WEB-INF/pages/customer/confirmation.jsp", req, resp);
    }

}
