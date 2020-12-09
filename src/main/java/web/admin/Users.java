package web.admin;

import domain.user.User;
import domain.user.exceptions.UserNotFound;
import org.slf4j.Logger;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet(name = "Users", urlPatterns = {"/Users"})
public class Users extends BaseServlet {
    
    private static final Logger log = getLogger(Users.class);
    
    /**
     * Renders the index.jsp page
     *
     * @see BaseServlet
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            User curUser = (User) req.getSession().getAttribute("user");
            
            log("Trying to log into admin :" + curUser);
            
            if (curUser == null || ! curUser.isAdmin()) {
                log("User is not admin: " + curUser);
                resp.sendError(401);
            } else {
                List<User> users = List.copyOf(api.getUsers());
                req.setAttribute("userlist", users);
                log("User is admin: " + curUser);
                render("Users", "/WEB-INF/pages/admin/users.jsp", req, resp);
            }
            
        } catch (Exception e) {
            log(e.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            switch (req.getParameter("action")) {
                case "deleteUser":
                    deleteUser(req);
                    break;
                case "createUser":
                    String name = req.getParameter("inputName");
                    String mail = req.getParameter("inputEmail");
                    String password = req.getParameter("inputPsw");
                    User.Role role = User.Role.valueOf(req.getParameter("inputRole"));
                    api.createUser(name, mail, password, role);
                    break;
                default:
                    break;
            }
            redirect(req, resp, "Users");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        
    }
    
    private void deleteUser(HttpServletRequest req) {
        try {
            api.deleteUser(Integer.parseInt(req.getParameter("userid")));
        } catch (UserNotFound e) {
            log.error(e.getMessage());
        }
    }
    
    
}