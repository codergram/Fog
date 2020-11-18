package entries.cart;

import entries.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Show_Cart", urlPatterns = { "/Show_Cart" } )
public class Show_Cart extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        //Parameters from the get request by the DeleteFromCart Servlet
//        String successMessage, errorMessage;
//        if(req.getParameter("successMessage") != null){
//            successMessage = req.getParameter("successMessage");
//            req.setAttribute("successMessage", successMessage);
//        }
//        if(req.getParameter("errorMessage") != null){
//            errorMessage = req.getParameter("errorMessage");
//            req.setAttribute("errorMessage", errorMessage);
//        }

//        HttpSession session = req.getSession();
//        ArrayList<Cart_item> shoppingCart = (ArrayList<Cart_item>) session.getAttribute("shoppingCart");
//
//        double cartTotal = api.getCartTotalAmount(shoppingCart);
//        req.setAttribute("cartTotal", cartTotal);

        render("Shopping Cart", "/WEB-INF/pages/show_cart.jsp", req, resp);
    }

}
