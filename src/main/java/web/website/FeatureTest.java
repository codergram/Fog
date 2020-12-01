package web.website;

import api.exceptions.PDFNotCreated;
import domain.user.User;
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
            String fileDir = System.getProperty("java.io.tmpdir");
            //api.sendMail("cph-en93@cphbusiness.dk", "Test mail", "Test mail", "Hej se filen.", api.testPdf(fileDir));
            api.testPdf(fileDir);
        } catch (PDFNotCreated pdfNotCreated) {
            log.warn(pdfNotCreated.getMessage());
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
