package web;

import api.Api;
import infrastructure.*;
import org.slf4j.Logger;
import web.widget.Navbar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class BaseServlet extends HttpServlet {

    protected static final Api api;
    private static final Logger log = getLogger(BaseServlet.class);

    static {
        api = createFogApi();
    }

    private static Api createFogApi(){

        Database database = new Database();

        return new Api(new DBUser(database), new JavaXEmailService(), new PDFService(), new LocalSVG(),
                new DBMaterial(database), new DBOrder(database), new DBCustomer(database));
    }

    protected void render(String title, String content, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("title", Api.genericSiteTitle + " - " + title);
        request.setAttribute("content", content);
        request.setAttribute("navbar", new Navbar(request));
        request.getRequestDispatcher("/WEB-INF/includes/base.jsp").forward(request, response);
        
    }
    
    // TODO : fix this methode
    protected void redirect(HttpServletRequest req, HttpServletResponse resp, String servletName){
        try {
            resp.sendRedirect(req.getContextPath() + "/" + servletName);
        } catch (IOException ee){
            log.info(ee.getMessage());
        }
    }
    
    

}
