package CheckServlets;

import Database.Database;
import Database_Tables.Admin_User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class loginCheck extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("***********************************************************************");
        System.out.println(req.getParameter("adminName"));
        System.out.println(req.getParameter("password"));
        System.out.println("***********************************************************************");
        
        Admin_User admin = new Admin_User(req.getParameter("adminName"), req.getParameter("password"));
        Database db = new Database();
        if (db.checkLogin(admin))
        {
            resp.sendError(200);
        }
        else
        {
            resp.sendError(513);
        }
    }   
}
