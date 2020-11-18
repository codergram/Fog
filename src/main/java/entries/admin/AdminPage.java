package entries.admin;

//import core.Order;
import core.User;
import entries.BaseServlet;
import infrastructure.DBexception;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(name = "AdminPage", urlPatterns = { "/AdminPage" } )
public class AdminPage extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String adminMenu, errorMessage, successMessage;
        String target = "";

        if(req.getParameter("target") != null){
            target = req.getParameter("target");
        }
        //Response from AddCustomer and DeleteCustomer Servlet
        if(req.getParameter("successMessage") != null){
            successMessage = req.getParameter("successMessage");
            req.setAttribute("successMessage", successMessage);
        }
        if(req.getParameter("errorMessage") != null){
            errorMessage = req.getParameter("errorMessage");
            req.setAttribute("errorMessage", errorMessage);
        }

        switch (target) {
//            case "addCustomer":
//                adminMenu = "addCustomer";
//                redirect(adminMenu, req, resp);
//                break;
//
//            case "allCustomers":
//                adminMenu = "allCustomers";
//                ArrayList<User> allUsersFromDB;
//                try {
//                    //We get the updated list
//                    allUsersFromDB = api.getAllUsersFromDB();
//                    req.setAttribute("allUsersFromDB", allUsersFromDB);
//
//                    redirect(adminMenu, req, resp);
//                } catch (DBexception dBexception) {
//                    errorMessage = "Error at the DB";
//                    redirectWithError(errorMessage, adminMenu, req, resp);
//                }
//                break;

//            case "editCustomer":
//                int user_id = Integer.parseInt(req.getParameter("user_id"));
//                adminMenu = "editCustomer";
//                try {
//                    User userToEdit = api.getUserById(user_id);
//                    req.setAttribute("userToEdit", userToEdit);
//                    if(req.getParameter("update").equals("true")){
//                        String successMessageEdit = "User: " + userToEdit.getUserEmail() + " was successfully updated!";
//                        req.setAttribute("successMessageEdit", successMessageEdit);
//
//                    } else if (req.getParameter("update").equals("false")){
//                        String errorMessageEdit = "User: " + userToEdit.getUserEmail() + " was not updated!";
//                        req.setAttribute("errorMessageEdit", errorMessageEdit);
//
//                    } else if (req.getParameter("update").equals("emptyFields")){
//                        String errorMessageEdit = "Fields can't be empty...";
//                        req.setAttribute("errorMessageEdit", errorMessageEdit);
//
//                    }else{
//                        String errorMessageEdit = "DB error";
//                        req.setAttribute("errorMessageEdit", errorMessageEdit);
//                    }
//                    redirect(adminMenu, req, resp);
//
//                } catch (DBexception dBexception) {
//                    errorMessage = "Error at the DB";
//                    redirectWithError(errorMessage, adminMenu, req, resp);
//                }
//                break;

//            case "allOrders":
//
//                try{
//                    ArrayList<Order> allOrders = api.getAllOrders();
//                    Collections.reverse(allOrders);
//                    req.setAttribute("allOrders", allOrders);
//                    adminMenu = "allOrders";
//                    redirect(adminMenu, req, resp);
//                } catch (DBexception dBexception) {
//                    dBexception.printStackTrace();
//                }
//
//                break;

            default:
                adminMenu = "";
                redirect(adminMenu, req, resp);
        }
    }

    private void redirect (String adminMenu, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("adminMenu", adminMenu);
        render("Admin Page", "/WEB-INF/pages/adminpage.jsp", req, resp);
    }

    private void redirectWithError (String errorMessage, String adminMenu, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorMessage", errorMessage);
        req.setAttribute("adminMenu", adminMenu);
        render("Admin Page", "/WEB-INF/pages/adminpage.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
