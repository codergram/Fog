package web.employee;

import domain.order.Order;
import domain.user.User;
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

@WebServlet("/Ordre/View/*")
public class ViewOrder extends BaseServlet {
    private static final Logger log = getLogger(ViewOrder.class);
    
    public User curUser;
    
    /**
     * Renders the index.jsp page
     *
     * @see BaseServlet
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            curUser = (User) req.getSession().getAttribute("user");
            
            log("Trying to log into admin :" + curUser);
            
            if (curUser == null || !curUser.isAdmin()) {
                log("User is not admin: " + curUser );
                resp.sendError(401);
            } else {
                int orderId = Integer.parseInt(req.getPathInfo().substring(1));
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
            int orderId = Integer.parseInt(req.getParameter("ordrenummer"));
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
                    api.sendLinkByMail(o, linkFromReq);
                    createAlert(req,"success", "Mailen blev sendt til " + o.getCustomer().getEmail());
                    break;
                default:
                    break;
            }
                redirect(req, resp, "Ordre/View/"+orderId); //TODO: Fix redirect error
        } catch (Exception e){
            log.error(e.getMessage());
            createAlert(req,"alert", e.getMessage());
            render("Ordre", "/WEB-INF/pages/sales/vieworder.jsp", req, resp);
        }
    }
    
    private void createAlert(HttpServletRequest req, String type, String message) {
        req.setAttribute("alert", true);
        req.setAttribute("alertMsg", message);
        req.setAttribute("alertType", type);
    }
}
