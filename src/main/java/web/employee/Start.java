package web.employee;

import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmployeeStart", urlPatterns = { "/EmployeeStart" } )
public class Start extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String profileMenu, errorMessage;
        String target = "";

        if(req.getParameter("target") != null){
            target = req.getParameter("target");
        }

        switch (target) {
            case "viewProfile":
                profileMenu = "viewProfile";
                redirect(profileMenu, req, resp);
                break;
            default:
                profileMenu = "";
                redirect(profileMenu, req, resp);
        }
    }

    private void redirect (String profileMenu, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        render("Employee Page", "/WEB-INF/pages/sales/start.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
