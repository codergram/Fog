package entries;

import api.Api;
import core.Stykliste;
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
import infrastructure.localstykliste.LocalStyklisteFactory;
import infrastructure.localstykliste.LocalStyklisteRepository;
import infrastructure.localstykliste.LocalStyklisteServices;
import infrastructure.localstyklistemateriel.LocalStyklisteMaterielFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
                new LocalStyklisteMaterielFactory());
    }

    protected void render(String title, String content, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("title", title);
        request.setAttribute("content", content);
        request.getRequestDispatcher("/WEB-INF/includes/base.jsp").forward(request, response);

    }

}
