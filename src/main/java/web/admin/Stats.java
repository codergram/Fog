package web.admin;

import domain.user.User;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Stats", urlPatterns = { "/Stats" } )
public class Stats extends BaseServlet {

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
                render("Statestic", "/WEB-INF/pages/admin/statistik.jsp", req, resp);
            }

        } catch (Exception e){
            log(e.getMessage());
        }
    }


}


