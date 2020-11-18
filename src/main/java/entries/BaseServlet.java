package entries;

import api.Api;
import infrastructure.Database;
import infrastructure.dbuser.DBUserFactory;
import infrastructure.dbuser.DBUserRepository;
import infrastructure.dbuser.DBUserServices;

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
//        Cart cart = new Cart();

        return new Api(new DBUserFactory(database), new DBUserRepository(database), new DBUserServices(database));

//        return new Api(new DBUserFactory(database), new DBUserRepository(database), new DBUserServices(database),
//                new DBItemRepository(database), new DBBottomRepository(database), new DBToppingRepository(database),
//                new LocalCartFactory(cart), new LocalCartRepository(cart), new LocalCartServices(cart),
//                new DBShippingFactory(database), new DBOrderFactory(database), new DBOrderRepository(database),
//                new DBOrderServices(database));
    }

    protected void render(String title, String content, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("title", title);
        request.setAttribute("content", content);
        request.getRequestDispatcher("/WEB-INF/pages/base.jsp").forward(request, response);

    }

}
