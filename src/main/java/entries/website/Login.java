package entries.website;

import core.User;
import domain.user.InvalidPassword;
import domain.user.UserNotFound;
import entries.BaseServlet;
import infrastructure.DBexception;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = { "/Login" } )
public class Login extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

//        if(req.getParameter("messageSignIn") != null){
//            String messageSignIn = req.getParameter("messageSignIn");
//            req.setAttribute("messageSignIn", messageSignIn);
//        }

        render("Log Ind", "/WEB-INF/pages/login.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Error message and destination page
        String messageSignIn = "";
        String errorMessage = "";

        String user_email = request.getParameter( "email" );
        String password = request.getParameter( "password" );
        String checkoutProcess = request.getParameter("checkoutProcess");

        //Check the username and password that is provided, and the user from DB
        try {

            User user = api.login(user_email, password);

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            //Redirect by user_role and get session data
            if(user.getRole().equals(User.Role.Admin)){
                if(checkoutProcess.equals("true")){
                    response.sendRedirect(request.getContextPath() + "/CheckoutPage?user_role=admin");

                } else {
                    response.sendRedirect(request.getContextPath() + "/Start");
                }

            } else if (user.getRole().equals(User.Role.Employee)){
                if(checkoutProcess.equals("true")){
                    response.sendRedirect(request.getContextPath() + "/CheckoutPage?user_role=sælger");

                } else {
                    response.sendRedirect(request.getContextPath() + "/EmployeePage");
                }

            } else {
                messageSignIn = "Unknown username or password";
                redirectWithMessage(checkoutProcess, messageSignIn, request, response);
            }

        } catch(NullPointerException | UserNotFound | InvalidPassword | DBexception e){
            //Unknown username or password
            messageSignIn = "Unknown username or password";
            redirectWithMessage(checkoutProcess, messageSignIn, request, response);
        }
    }

    private void redirectWithMessage(String checkoutProcess, String messageSignIn, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(checkoutProcess.equals("true")){
            request.setAttribute("messageSignIn", messageSignIn);
//            response.sendRedirect(request.getContextPath() + "/LoginRegisterPage?user_role=&messageSignIn=" + messageSignIn);
            render("Log Ind/Opret Konto", "/WEB-INF/pages/loginregisterpage.jsp", request, response);
        } else {
            request.setAttribute("messageSignIn", messageSignIn);
//            response.sendRedirect(request.getContextPath() + "/Login?messageSignIn=" + messageSignIn);
            render("Log Ind", "/WEB-INF/pages/login.jsp", request, response);
        }
    }


}
