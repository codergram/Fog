package web.website;

import domain.carport.Carport;
import domain.carport.shed.Shed;
import domain.customer.Customer;
import domain.material.materials.Material;
import domain.order.Order;
import domain.partslist.Part;
import domain.partslist.Partslist;
import infrastructure.exceptions.DBException;
import org.slf4j.Logger;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        String actionVal = null;
//        Enum<Carport.Roof> roofType = null;
        String roofType = "";
        Carport carport = null;
        Shed shed = null;
        
        try {
            actionVal = req.getParameter("action");
            length = Double.parseDouble(req.getParameter("length"));
            width = Double.parseDouble(req.getParameter("width"));
            withShed = req.getParameter("shedOption").equalsIgnoreCase("on");
        } catch (NullPointerException | NumberFormatException e){
            log.warn(e.getMessage());
        }
        String roof = req.getParameter("roof");
        String shedSize = req.getParameter("shedSize");
    
        System.out.println("action: " + actionVal);
        System.out.println("width: " + width);
        System.out.println("length: " + length);
        System.out.println("shedSize: " + shedSize);
        
        //Calculate Shed dimensions
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

        //Validate roof type
        if(roof.equals("peak")){
            roofType = "Peak";
        } else {
            roofType = "Flat";
        }

        //Create shed
        if(withShed){
            shed = new Shed(shedLength, shedWidth);
        } else {
            shed = null;
        }

        try{
            //Get all Materiel's from DB
            List<Material> allMaterialsFromDB = api.getAllMaterielsFromDB();

            //Get the local empty Partlist
            List<Part> localPartlist = api.getLocalPartslist();

            //Carport object
            carport = new Carport(length, width, roofType, shed);

            //Add to the local Partlist with compared Materiel from DB
            List<Part> carportPartslist = api.addToLocalPartslist(carport, allMaterialsFromDB, localPartlist);

            //Create partslist
            Partslist partslist = new Partslist(carportPartslist);

            //Create new Carport with the created Partslist
            carport = new Carport(length, width, roofType, shed, partslist);


        } catch (DBException e) {
            e.printStackTrace();
        }

        //Get Carport drawing
        String svgSide = api.getSVGSide(carport, isCustomer);
        String svgTop = api.getSVGTop(carport, isCustomer);

        //Save requests and sessions
        req.setAttribute("svgSide", svgSide);
        req.setAttribute("svgTop", svgTop);
        req.getSession().setAttribute("carport", carport);

        if(actionVal.equals("preview")){
            System.out.println("preview carport");
            req.setAttribute("showCarport", true);
            render("Se din carport", "/WEB-INF/pages/customer/carport.jsp", req, resp);
        } else {
            render("Bekræft Carport", "/WEB-INF/pages/customer/confirmation.jsp", req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Carport carport = (Carport) req.getSession().getAttribute("carport");

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        int zip = Integer.parseInt(req.getParameter("zip"));
        String city = req.getParameter("city");
        int phone = Integer.parseInt(req.getParameter("phone"));

        Customer customer = new Customer(name, address, zip, city, phone, email);

        Order order = new Order(carport.getWidth(), carport.getLength(), customer, carport);


        //**** DO SOMETHING HERE ******

        render("Bekræft Carport", "/WEB-INF/pages/customer/thankyou.jsp", req, resp);


    }
}
