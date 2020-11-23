package entries.admin;

import core.User;
import entries.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Start", urlPatterns = { "/Start" } )
public class Start extends BaseServlet {
    
    /**
     * Renders the index.jsp page
     * @see BaseServlet
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        try {
            User usr = (User) req.getSession().getAttribute("user");
            
            log("Trying to log into admin :" + usr);
            
            if (usr != null && usr.isAdmin()) {
                log("User is admin: " + usr);
                render("Administrator", "/WEB-INF/pages/adminpage.jsp", req, resp);
            } else if (usr != null && usr.isEmployee()){
                log("User is employee: " + usr);
                render("Employee", "/WEB-INF/pages/employeepage.jsp", req, resp);
            } else {
                log("User is not admin: " + usr );
                resp.sendError(401);
            }
            
        } catch (Exception e){
            log(e.getMessage());
        }
    }

}
