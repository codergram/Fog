

package web.website;

        import web.BaseServlet;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

@WebServlet(name = "Ordre", urlPatterns = { "/Ordre" })
public class Ordre extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        render("Ordre", "/WEB-INF/pages/admin/ordre.jsp", req, resp);
    }
}
