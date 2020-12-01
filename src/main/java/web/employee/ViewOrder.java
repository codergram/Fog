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
            switch (req.getParameter("action")) {
                default:
                    break;
            }
            redirect(req,resp,"Ordre");
        } catch (Exception e){
            log.error(e.getMessage());
        }
        
    }
}
