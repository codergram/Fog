package web.website;

import org.slf4j.Logger;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet("")
public class Index extends BaseServlet {
    
    private static final Logger log = getLogger(Index.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        
        log.info("Serving page {}", req.getRequestURI());
        
        render("Startside", "/WEB-INF/pages/index.jsp", req, resp);

    }

}
