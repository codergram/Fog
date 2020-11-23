package entries.website;

import entries.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Logout", urlPatterns = { "/Logout" } )
public class Logout extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            req.getSession().setAttribute("user", null);
            req.getSession().invalidate();
            log(req, "logged out");
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (IOException e){
            log(e.getMessage());
        }
    }
}
