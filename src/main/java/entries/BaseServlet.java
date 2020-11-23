package entries;

import api.Api;
import infrastructure.Database;
import infrastructure.dbbeslagskruer.DBBeslagSkruerFactory;
import infrastructure.dbbeslagskruer.DBBeslagSkruerRepository;
import infrastructure.dbbeslagskruer.DBBeslagSkruerServices;
import infrastructure.dbbestilling.DBBestillingFactory;
import infrastructure.dbbestilling.DBBestillingRepository;
import infrastructure.dbbestilling.DBBestillingServices;
import infrastructure.dbcarport.DBCarportFactory;
import infrastructure.dbcarport.DBCarportRepository;
import infrastructure.dbkunde.DBKundeFactory;
import infrastructure.dbkunde.DBKundeRepository;
import infrastructure.dbtræ.DBTræFactory;
import infrastructure.dbtræ.DBTræRepository;
import infrastructure.dbtræ.DBTræServices;
import infrastructure.dbskur.DBSkurFactory;
import infrastructure.dbskur.DBSkurRepository;
import infrastructure.dbuser.DBUserFactory;
import infrastructure.dbuser.DBUserRepository;
import infrastructure.dbuser.DBUserServices;
import infrastructure.localstykliste.LocalStyklisteFactory;
import infrastructure.localstykliste.LocalStyklisteRepository;
import infrastructure.localstykliste.LocalStyklisteServices;
import infrastructure.localstyklistebeslagskruer.LocalStyklisteBeslagSkruerFactory;
import infrastructure.localstyklistetræ.LocalStyklisteTræFactory;

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
                new DBKundeRepository(database), new DBTræFactory(database), new DBTræRepository(database),
                new DBTræServices(database), new DBBeslagSkruerFactory(database), new DBBeslagSkruerRepository(database),
                new DBBeslagSkruerServices(database), new DBSkurFactory(database), new DBSkurRepository(database),
                new LocalStyklisteFactory(), new LocalStyklisteRepository(), new LocalStyklisteServices(),
                new LocalStyklisteTræFactory(), new LocalStyklisteBeslagSkruerFactory());
    }

    protected void render(String title, String content, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("title", title);
        request.setAttribute("content", content);
        request.getRequestDispatcher("/WEB-INF/includes/base.jsp").forward(request, response);

    }

}
