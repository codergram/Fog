package web.website;

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

@WebServlet("/ViewOrder/*")
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
                int orderId = Integer.parseInt(req.getPathInfo().substring(1));
                Order order = null;
                
                for(Order o: api.getOrders()){
                    if(o.getId() == orderId){
                        order = o;
                    }
                }
    
                String svgSide = api.getSVGSide(order.getCarport(), !order.isPaid());
                String svgTop = api.getSVGTop(order.getCarport(), !order.isPaid());
    
                List<String> statuslist = new ArrayList<>();
                for(Order.Status s: Order.Status.values()){
                    statuslist.add(s.name());
                }
                req.setAttribute("statuslist", statuslist);
    
                //Save requests and sessions
                req.setAttribute("svgSide", svgSide);
                req.setAttribute("svgTop", svgTop);
                req.setAttribute("carport", order.getCarport());
                req.setAttribute("order", order);
                
                render("Ordre", "/WEB-INF/pages/customer/vieworder.jsp", req, resp);
            
        } catch (Exception e){
            log(e.getMessage());
        }
    }
}
