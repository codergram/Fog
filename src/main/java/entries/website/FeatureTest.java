package entries.website;

import entries.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Test", urlPatterns = { "/Test" } )
public class FeatureTest extends BaseServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        render("Startside", "/WEB-INF/pages/test.jsp", req, resp);
        
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String valFromTable = req.getParameter("ValOne");
    
        HttpSession session = req.getSession();
        
        session.setAttribute("valSet", valFromTable);
    
        render("Startside", "/WEB-INF/pages/test.jsp", req, resp);
    }
}
