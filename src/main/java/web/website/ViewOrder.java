package web.website;

import domain.order.Order;
import domain.order.exceptions.OrderNotFound;
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
    
    /**
     * Renders the index.jsp page
     * @see BaseServlet
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
                String uuidReq = req.getPathInfo().substring(1);
                int orderId = -1;
            
                log.info("UUID requested: " + uuidReq);
                try {
                    orderId = api.getOrderByUUID(uuidReq);
                    log.info("ID found on UUID: " + orderId);
                } catch (Exception e){
                    log.error(e.getMessage());
                }
                
                for(Order o: api.getOrders()){
                    System.out.println("Order found: " + o);
                }
                
                Order order = api.getOrderById(orderId);
                
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
