package web.website;

import domain.user.User;
import domain.user.exceptions.InvalidPassword;
import domain.user.exceptions.UserNotFound;
import org.slf4j.Logger;
import web.BaseServlet;
import infrastructure.exceptions.DBException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet(name = "Login", urlPatterns = { "/Login" } )
public class Login extends BaseServlet {
    
    private static final Logger log = getLogger(Login.class);
    

    protected void render(HttpServletRequest request, HttpServletResponse response) {
        try {
            super.render("Log ind", "/WEB-INF/pages/login.jsp", request, response);
        } catch (ServletException | IOException e){
            log.error(e.getMessage());
        }
        
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            render(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("providedMail", request.getParameter("inputEmail"));
            User curUser = login(request);
            
            if(curUser.isAdmin()) {
                response.sendRedirect(request.getContextPath() + "/AdminStart");
            } else if (curUser.isEmployee()){
                response.sendRedirect(request.getContextPath() + "/EmployeeStart");
            } else {
                response.sendRedirect(request.getContextPath() + "/");
            }
        
        } catch (InvalidPassword | UserNotFound i) {
            log.info(i.getMessage());
            request.setAttribute("errorMsg", i.getMessage());
            request.setAttribute("error", true);
            render(request, response);
        } catch (DBException e){
            log.warn(e.getMessage());
        }
    }
    
    private User login(HttpServletRequest req) throws InvalidPassword, UserNotFound, DBException {
        HttpSession session = req.getSession();
        
        String usrEmail = req.getParameter("inputEmail");
        String usrPassword = req.getParameter("inputPassword");
        
        User curUsr = api.login(usrEmail, usrPassword);
        
        session.setAttribute("user", curUsr);
        session.setAttribute("userrole", curUsr.getRole().name());
        
        return curUsr;
    }


}
