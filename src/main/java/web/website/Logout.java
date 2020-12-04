package web.website;

import org.slf4j.Logger;
import web.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet(name = "Logout", urlPatterns = { "/Logout" } )
public class Logout extends BaseServlet {
    private static final Logger log = getLogger(Logout.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            req.getSession().setAttribute("user", null);
            req.getSession().invalidate();
            log.info("logged out");
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (IOException e){
            log.warn(e.getMessage());
        }
    }
}
