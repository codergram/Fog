package entries.customer;

import core.User;
import entries.BaseServlet;
import infrastructure.DBexception;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(name = "CustomerPage", urlPatterns = { "/CustomerPage" } )
public class CustomerPage extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String profileMenu, errorMessage;
        String target = "";

        if(req.getParameter("target") != null){
            target = req.getParameter("target");
        }

        switch (target) {
            case "viewProfile":
                profileMenu = "viewProfile";
                redirect(profileMenu, req, resp);
                break;

//            case "orderHistory":
//                HttpSession session = req.getSession();
//                User user = (User) session.getAttribute("user");
//
//                try{
//                    ArrayList<Order> allOrders = api.getAllOrdersByUserId(user.getId());
//                    Collections.reverse(allOrders);
//                    req.setAttribute("allOrders", allOrders);
//                } catch (DBexception dBexception) {
//                    dBexception.printStackTrace();
//                }
//
//                profileMenu = "orderHistory";
//                redirect(profileMenu, req, resp);
//                break;

            default:
                profileMenu = "";
                redirect(profileMenu, req, resp);
        }
    }

    private void redirect (String profileMenu, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("profileMenu", profileMenu);
        render("Admin Page", "/WEB-INF/pages/customerpage.jsp", req, resp);
    }

    private void redirectWithError (String errorMessage, String profileMenu, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorMessage", errorMessage);
        req.setAttribute("profileMenu", profileMenu);
        render("Admin Page", "/WEB-INF/pages/customerpage.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
