package Add_Functions;

import Database.Database;
import Database_Tables.Services;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Add_Services extends HttpServlet 
{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        Services service = new Services(req.getParameter("serviceName"));
        Database db = new Database();
        if(db.addService(service))
        {
            resp.sendError(200);
        }
        else
        {
            resp.sendError(513);
        }
    }
}
