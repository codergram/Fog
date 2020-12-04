package web.employee;

import domain.user.User;
import org.slf4j.Logger;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet(name = "Customers", urlPatterns = { "/Customers" } )
public class Customers extends BaseServlet {
    
    private static final Logger log = getLogger(Customers.class);

    public User curUser;

    /**
     * Renders the index.jsp page
     *
     * @see BaseServlet
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            curUser = (User) req.getSession().getAttribute("user");

            log("Trying to log into admin :" + curUser);

            if (curUser == null || !curUser.isAdmin()) {
                log("User is not admin: " + curUser );
                resp.sendError(401);
            } else {
                log("User is admin: " + curUser);
                render("Customers", "/WEB-INF/pages/sales/customers.jsp", req, resp);
            }

        } catch (Exception e){
            log(e.getMessage());
        }
    }


}
