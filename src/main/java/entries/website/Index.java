package entries.website;

import entries.BaseServlet;
import infrastructure.DBexception;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("")
public class Index extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

//        try {
//            ArrayList<Item> getAllItemsFromDB = api.getAllItemsFromDB();
//            req.setAttribute("getAllItemsFromDB", getAllItemsFromDB);
//
//            HttpSession session = req.getSession();
//            ArrayList<Cart_item> shoppingCart = api.getShoppingCart();
//            session.setAttribute("shoppingCart", shoppingCart);
//
//        } catch (DBexception dBexception) {
//            dBexception.printStackTrace();
//        }

        render("The CupCake Shop", "/WEB-INF/pages/index.jsp", req, resp);

    }

}
