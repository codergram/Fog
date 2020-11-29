package web;

import api.Api;
import infrastructure.DBUser;
import infrastructure.Database;
import infrastructure.JavaXEmailService;
import infrastructure.PDFService;
import web.widget.Navbar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class BaseServlet extends HttpServlet {

    protected static final Api api;

    static {
        api = createCupCakeApi();
    }

    private static Api createCupCakeApi(){

        Database database = new Database();

        return new Api(new DBUser(database), new JavaXEmailService(), new PDFService());
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
    
    protected void log(HttpServletRequest req, String str){
        System.err.print("(" + LocalDateTime.now() + ")" + this.getClass().getCanonicalName() + " - " + req.getRequestURI() + " - " + str);
    }

}
