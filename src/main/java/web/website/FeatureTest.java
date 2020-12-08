package web.website;

import api.exceptions.PDFNotCreated;
import domain.material.materials.Material;
import domain.material.materials.Tree;
import domain.user.User;
import domain.user.exceptions.InvalidPassword;
import domain.user.exceptions.UserExists;
import infrastructure.exceptions.DBException;
import org.slf4j.Logger;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet(name = "Test", urlPatterns = { "/Test" } )
public class FeatureTest extends BaseServlet {
    
    private static final Logger log = getLogger(FeatureTest.class);
    
    private void testUserLoggedIn(User.Role role, HttpServletRequest request) {
    
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String getRole = req.getParameter("role");
        Enum<User.Role> role;
        
        if(getRole != null){
            role = User.valueOfIgnoreCase(getRole);
        } else {
            role = null;
        }
        
        User testUser = new User(-1,"Test bruger", "test@mail.dk", role);
        req.getSession().setAttribute("user", testUser);
    
        System.out.println("GET role: " + role);
        System.out.println("Test user: " + testUser);
        System.out.println("isAdmin: " + testUser.isAdmin());
        System.out.println("isEmployee: " + testUser.isEmployee());
    
    
        try {
            for(Material m: api.getAllMaterielsFromDB()){
                if(m instanceof Tree){
                    System.out.println("Træ: ");
                    System.out.println(m.toString());
                } else {
                    System.out.println("Option: ");
                    System.out.println(m.toString());
                }
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    
    
        log.info("Serving page {}", req.getRequestURI());
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
