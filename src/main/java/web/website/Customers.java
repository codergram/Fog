
package web.website;

        import web.BaseServlet;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

@WebServlet(name = "Customers", urlPatterns = { "/Customers" })
public class Customers extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        render("Kunder", "/WEB-INF/pages/admin/Customers.jsp", req, resp);
    }
}

