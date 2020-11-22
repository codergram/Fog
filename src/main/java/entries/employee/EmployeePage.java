package entries.employee;

import entries.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmployeePage", urlPatterns = { "/EmployeePage" } )
public class EmployeePage extends BaseServlet {

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
        render("Employee Page", "/WEB-INF/pages/employeepage.jsp", req, resp);
    }

    private void redirectWithError (String errorMessage, String profileMenu, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorMessage", errorMessage);
        req.setAttribute("profileMenu", profileMenu);
        render("Employee Page", "/WEB-INF/pages/employeepage.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
