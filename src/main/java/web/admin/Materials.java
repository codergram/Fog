package web.admin;

import domain.material.materials.Material;
import domain.user.User;
import org.slf4j.Logger;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet(name = "Materials", urlPatterns = { "/Materials" } )
public class Materials extends BaseServlet {
    
    private static final Logger log = getLogger(Materials.class);
    
    public User curUser;
    private List<Material> materials;
    
    /**
     * Renders the index.jsp page
     *
     * @see BaseServlet
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            curUser = (User) req.getSession().getAttribute("user");
            
            log("Trying to log into admin :" + curUser);
            
            if (curUser == null || !curUser.isAdmin()) {
                log("User is not admin: " + curUser );
                resp.sendError(401);
            } else {
                materials = List.copyOf(api.getAllRawMaterielsFromDB());
                
                req.setAttribute("materiallist", materials);
                log("User is admin: " + curUser);
                render("Materialer", "/WEB-INF/pages/admin/materials.jsp", req, resp);
            }
            
        } catch (Exception e){
            log(e.getMessage());
        }
        
        
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        redirect(req,resp,"Materials");
        
    }
}


