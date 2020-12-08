package web.employee;

import domain.order.Order;
import domain.user.User;
import org.slf4j.Logger;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet("/Ordre/View/*")
public class ViewOrder extends BaseServlet {
    private static final Logger log = getLogger(ViewOrder.class);
    
    private User curUser;
    private int orderId = -1;
    
    /**
     * Renders the index.jsp page
     *
     * @see BaseServlet
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            
            curUser = (User) req.getSession().getAttribute("user");
            
            log("Trying to log into admin :" + curUser);
            
            if (curUser == null || !curUser.isAdmin()) {
                log("User is not admin: " + curUser );
                resp.sendError(401);
            } else {
                orderId = Integer.parseInt(req.getPathInfo().substring(1));
                Order order = null;
                
                for(Order o: api.getOrders()){
                    if(o.getId() == orderId){
                        order = o;
                    }
                }
                req.setAttribute("order", order);
                log("User is admin: " + curUser);
    
                String svgSide = api.getSVGSide(order.getCarport(), false);
                String svgTop = api.getSVGTop(order.getCarport(), false);
    
                List<String> statuslist = new ArrayList<>();
                for(Order.Status s: Order.Status.values()){
                    statuslist.add(s.name());
                }
                req.setAttribute("statuslist", statuslist);
    
                //Save requests and sessions
                req.setAttribute("svgSide", svgSide);
                req.setAttribute("svgTop", svgTop);
                req.setAttribute("carport", order.getCarport());
                
                render("Ordre", "/WEB-INF/pages/sales/vieworder.jsp", req, resp);
            }
            
        } catch (Exception e){
            log(e.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            
            orderId = Integer.parseInt(req.getParameter("ordrenummer"));
            switch (req.getParameter("action")) {
                case "updatePrice":
                    double newPrice = Double.parseDouble(req.getParameter("salgspris"));
                    api.updatePrice(orderId, newPrice);
                    break;
                case "changeStatus":
                    api.changeOrderStatus(orderId, req.getParameter("statusvalue"));
                    break;
                case "sendLink":
                    Order o = api.getOrderById(orderId);
                    String linkFromReq = req.getParameter("ordreurl");
                    String link = "<a href=\"" + linkFromReq + "\">" + linkFromReq + "</a>";
                    api.sendLinkByMail(o, link);
                    createAlert(req,"success", "Mailen blev sendt til " + o.getCustomer().getEmail());
                    break;
                case "createPdf":
                    Order pdfOrder = api.getOrderById(orderId);
                    File pdfFile = api.createPdf(pdfOrder);
                    String downloadUrl = req.getParameter("downloadurl");
                    sendMail(pdfOrder.getCustomer().getEmail(), downloadUrl, pdfFile);
                    break;
                default:
                    break;
            }
                redirect(req, resp, "Ordre/View/"+orderId);
        } catch (Exception e){
            log.error(e.getMessage());
            createAlert(req,"alert", e.getMessage());
            redirect(req, resp, "Ordre/View/"+orderId);
        }
    }
    
    private void sendMail(String email, String url, File pdf){
        String message = "Tillykke med din nye carport! Du finder din vejledning vedhæftet denne mail og den kan downloads her: " + url;
        api.sendMail(email,"Din stykliste", "Her er din stykliste", message, pdf);
    }
    
    private void createAlert(HttpServletRequest req, String type, String message) {
        req.setAttribute("alert", true);
        req.setAttribute("alertMsg", message);
        req.setAttribute("alertType", type);
    }
}
