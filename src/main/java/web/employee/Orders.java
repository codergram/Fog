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
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet(name = "Ordre", urlPatterns = { "/Ordre" } )
public class Orders extends BaseServlet {
    
    private static final Logger log = getLogger(Orders.class);
    
    public User curUser;
    private List<Order> orders;
    
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
            
            if (curUser == null || !curUser.isEmployee()) {
                log("User is not admin: " + curUser );
                resp.sendError(401);
            } else {
                orders = List.copyOf(api.getOrders());
                
                req.setAttribute("orderlist", orders);
                
                log("User is admin: " + curUser);
                render("Ordre", "/WEB-INF/pages/sales/orders.jsp", req, resp);
            }
            
        } catch (Exception e){
            log(e.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            switch (req.getParameter("action")) {
                case "assignOrder":
                    api.assignOrder(Integer.parseInt(req.getParameter("ordrenummer")), curUser.getId());
                    break;
                default:
                    break;
            }
            redirect(req,resp,"Ordre");
        } catch (Exception e){
            log.error(e.getMessage());
        }
        
    }
    
    
}


