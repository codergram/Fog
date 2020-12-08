package web.admin;

import domain.material.materials.Material;
import domain.material.materials.Options;
import domain.material.materials.Tree;
import domain.order.Order;
import domain.user.User;
import org.slf4j.Logger;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet("/Materials/Edit/*")
public class EditMaterial extends BaseServlet {
    private static final Logger log = getLogger(EditMaterial.class);
    
    private User curUser;
    private int materialId = -1;
    
    /**
     * Renders the index.jsp page
     *
     * @see BaseServlet
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            
            curUser = (User) req.getSession().getAttribute("user");
            
            List<Material> materialsWithName = new ArrayList<>();
            
            log("Trying to log into admin :" + curUser);
            
            if (curUser == null || !curUser.isAdmin()) {
                log("User is not admin: " + curUser );
                resp.sendError(401);
            } else {
                materialId = Integer.parseInt(req.getPathInfo().substring(1));
                Material material = null;
                
                for(Material m: api.getAllMaterielsFromDB()){
                    if(m.getId() == materialId){
                        material = m;
                    }
                }
    
                for(Material m: api.getAllMaterielsFromDB()){
                    if(m.getName().equals(material.getName())){
                        materialsWithName.add(m);
                    }
                }
                
                Set<Material.Usage> usedUsages = new HashSet<>();
                for(Material m: materialsWithName){
                    usedUsages.add(Material.Usage.valueOf(m.getUsage().name()));
                }

                req.setAttribute("material", material);
                log("User is admin: " + curUser);
                
                List<String> usages = new ArrayList<>();
                List<String> types = new ArrayList<>();
                List<String> units = new ArrayList<>();
                
                
                if(material instanceof Tree){
                    for(Tree.Type treeType: Tree.Type.values()){
                        types.add(treeType.name());
                    }
                } else {
                    for(Options.Type optionsType: Options.Type.values()){
                        types.add(optionsType.name());
                    }
                }
                for(Material.Usage usage: Material.Usage.values()){
                    usages.add(usage.name());
                }
                for(Material.Unit unit: Material.Unit.values()){
                    units.add(unit.name());
                }
    
                req.setAttribute("usedUsage", usedUsages);
                req.setAttribute("usages", usages);
                req.setAttribute("types", types);
                req.setAttribute("units", units);
                
                
                render("Ændre materiale", "/WEB-INF/pages/admin/editmaterial.jsp", req, resp);
            }
            
        } catch (Exception e){
            log(e.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            materialId = Integer.parseInt(req.getParameter("materialid"));
            String matName = req.getParameter("matName");
            double matPrice = Double.parseDouble(req.getParameter("matPrice"));
            Material.Unit matUnit = Material.Unit.valueOf(req.getParameter("matUnit"));
            
            api.updateMaterial(materialId, matName,matPrice,matUnit);
            createAlert(req, "success", "Produktet blev opdateret!");
            redirect(req, resp, "Materials/Edit/"+materialId);
        } catch (Exception e){
            log.error(e.getMessage());
            createAlert(req,"alert", e.getMessage());
            redirect(req, resp, "Materials/Edit/"+materialId);
        }
    }
    
    private void createAlert(HttpServletRequest req, String type, String message) {
        req.setAttribute("alert", true);
        req.setAttribute("alertMsg", message);
        req.setAttribute("alertType", type);
    }
}
