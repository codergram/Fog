package web;

import api.Api;
import domain.partslist.Partslist;
import infrastructure.*;
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
        api = createFogApi();
    }

    private static Api createFogApi(){

        Database database = new Database();

        return new Api(new DBUser(database), new JavaXEmailService(), new PDFService(), new LocalSVG(),
                new DBMaterial(database), new LocalPartslist());
    }

    protected void render(String title, String content, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("title", Api.genericSiteTitle + " - " + title);
        request.setAttribute("content", content);
//        if(request.getSession().getAttribute("partlist") == null) {
//            request.getSession().setAttribute("partlist", new Partslist());
//        }
        request.setAttribute("navbar", new Navbar(request));
        request.getRequestDispatcher("/WEB-INF/includes/base.jsp").forward(request, response);
        
    }
    
    protected void oldLog(HttpServletRequest req, String str){
        System.err.print("(" + LocalDateTime.now() + ")" + this.getClass().getCanonicalName() + " - " + req.getRequestURI() + " - " + str);
    }
    
    

}
