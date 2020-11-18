package entries.login_register_logout;

import core.User;
import entries.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Logout", urlPatterns = { "/Logout" } )
public class Logout extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        User user = null;
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        resp.sendRedirect(req.getContextPath());

//        render("The CupCake Shop", "/WEB-INF/index.jsp", req, resp);
    }
}
