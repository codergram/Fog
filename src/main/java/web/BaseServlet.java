package web;

import api.Webapp;
import infrastructure.Database;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class BaseServlet extends HttpServlet {
    protected static final Webapp api;
    
    static {
        api = createApplication();
    }
    
    private static Webapp createApplication() {
        new Database();
        return new Webapp();
    }
    
    
    protected void render(String title, String content, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setAttribute("title", Webapp.getTitle() + " - " + title);
        req.setAttribute("content", content);
        req.setAttribute("year", LocalDateTime.now().getYear());
        req.getRequestDispatcher("/WEB-INF/includes/base.jsp").forward(req, resp);
    }
    
    
    protected void log(HttpServletRequest req, String str){
        System.err.print("(" + LocalDateTime.now() + ")" + this.getClass().getCanonicalName() + " - " + req.getRequestURI() + " - " + str);
    }
}