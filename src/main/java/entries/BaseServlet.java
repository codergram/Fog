package entries;

import api.Api;
import api.Utils;
import infrastructure.Database;
import infrastructure.dbbestilling.DBBestillingFactory;
import infrastructure.dbbestilling.DBBestillingRepository;
import infrastructure.dbbestilling.DBBestillingServices;
import infrastructure.dbcarport.DBCarportFactory;
import infrastructure.dbcarport.DBCarportRepository;
import infrastructure.dbkunde.DBKundeFactory;
import infrastructure.dbkunde.DBKundeRepository;
import infrastructure.dbmateriel.DBMaterielFactory;
import infrastructure.dbmateriel.DBMaterielRepository;
import infrastructure.dbmateriel.DBMaterielServices;
import infrastructure.dbskur.DBSkurFactory;
import infrastructure.dbskur.DBSkurRepository;
import infrastructure.dbuser.DBUserFactory;
import infrastructure.dbuser.DBUserRepository;
import infrastructure.dbuser.DBUserServices;
import infrastructure.localstyk.LocalStykFactory;
import infrastructure.localstykliste.LocalStyklisteFactory;
import infrastructure.localstykliste.LocalStyklisteRepository;
import infrastructure.localstykliste.LocalStyklisteServices;

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

        return new Api(new DBUserFactory(database), new DBUserRepository(database), new DBUserServices(database),
                new DBBestillingFactory(database), new DBBestillingRepository(database), new DBBestillingServices(database),
                new DBCarportFactory(database), new DBCarportRepository(database), new DBKundeFactory(database),
                new DBKundeRepository(database), new DBMaterielFactory(database), new DBMaterielRepository(database),
                new DBMaterielServices(database), new DBSkurFactory(database), new DBSkurRepository(database),
                new LocalStyklisteFactory(), new LocalStyklisteRepository(), new LocalStyklisteServices(),
                new LocalStykFactory());
    }

    protected void render(String title, String content, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("title", api.genericSiteTitle + " - " + title);
        request.setAttribute("content", content);
        request.getRequestDispatcher("/WEB-INF/includes/base.jsp").forward(request, response);

    }
    
    protected void log(HttpServletRequest req, String str){
        System.err.print("(" + LocalDateTime.now() + ")" + this.getClass().getCanonicalName() + " - " + req.getRequestURI() + " - " + str);
    }

}
