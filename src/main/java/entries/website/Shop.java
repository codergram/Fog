package entries.website;

import entries.BaseServlet;
import infrastructure.DBexception;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Shop", urlPatterns = { "/Shop" } )
public class Shop extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        try {
//            ArrayList<Item> getAllItemsFromDB = api.getAllItemsFromDB();
//            req.setAttribute("getAllItemsFromDB", getAllItemsFromDB);
//        } catch (DBexception dBexception) {
//            dBexception.printStackTrace();
//        }

        render("The CupCake Shop", "/WEB-INF/pages/index.jsp", request, response);
    }

}
