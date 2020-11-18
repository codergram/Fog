package entries.login_register_logout;

import core.User;
import domain.user.InvalidPassword;
import domain.user.UserExists;
import entries.BaseServlet;
import infrastructure.DBexception;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Register", urlPatterns = { "/Register" } )
public class Register extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        render("The CupCake Shop", "/WEB-INF/pages/register.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Error message and destination page
        String messageSignUp = "";

        String user_email = request.getParameter( "email" );
        String password1 = request.getParameter( "password1" );
        String password2 = request.getParameter( "password2" );
        String checkoutProcess = request.getParameter("checkoutProcess");

        try{
            User user = api.createUser(user_email, password1, password2);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            //Redirect by user_role and get session data
            if(user.getUserRole().equals("admin")){
                if(checkoutProcess.equals("true")){
                    response.sendRedirect(request.getContextPath() + "/CheckoutPage?user_role=admin");

                } else {
                    response.sendRedirect(request.getContextPath() + "/AdminPage");
                }

            } else if (user.getUserRole().equals("customer")){
                if(checkoutProcess.equals("true")){
                    response.sendRedirect(request.getContextPath() + "/CheckoutPage?user_role=customer");

                } else {
                    response.sendRedirect(request.getContextPath() + "/CustomerPage");
                }

            } else {
                messageSignUp = "Something when wrong, please try again...";
                redirectWithMessage(checkoutProcess, messageSignUp, request, response);
            }

        } catch (InvalidPassword e){
            messageSignUp = "Passwords do not match";
            redirectWithMessage(checkoutProcess, messageSignUp, request, response);

        } catch (UserExists e) {
            messageSignUp = "User already exist in our system";
            redirectWithMessage(checkoutProcess, messageSignUp, request, response);

        } catch (DBexception dBexception) {
            dBexception.printStackTrace();
        }
    }

    private void redirectWithMessage(String checkoutProcess, String messageSignUp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(checkoutProcess.equals("true")){
            request.setAttribute("messageSignUp", messageSignUp);
//            response.sendRedirect(request.getContextPath() + "/LoginRegisterPage?user_role=&messageSignUp=" + messageSignUp);
            render("The CupCake Shop", "/WEB-INF/pages/LoginRegisterPage.jsp", request, response);
        } else {
            request.setAttribute("messageSignUp", messageSignUp);
//            response.sendRedirect(request.getContextPath() + "/Register?messageSignUp=" + messageSignUp);
            render("The CupCake Shop", "/WEB-INF/pages/register.jsp", request, response);
        }
    }


}
