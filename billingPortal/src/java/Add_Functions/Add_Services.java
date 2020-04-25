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
        boolean operation = false;
        if ((req.getParameter("recurring").equals("false") || req.getParameter("recurring").equals("true")) && 
                req.getParameter("oneTimeService").equals("false"))
        {
            operation = addNormalOrRecurringService(req);
        }
        else if (req.getParameter("oneTimeService").equals("true"))
        {
            operation = addOneTimeService(req);
        }
        else
        {
            operation = false;
        }
////////////////////////////////////////////////////////////////////////////////////////
        if(operation)
        {
            resp.sendError(200);
        }
        else
        {
            resp.sendError(513);
        } 
    }
    
    
    private boolean addNormalOrRecurringService (HttpServletRequest req)
    {
        Services service = new Services(req.getParameter("serviceName"), 
                Boolean.parseBoolean(req.getParameter("recurring")), 
                Float.parseFloat(req.getParameter("serviceFees")));
        Database db = new Database();
        return db.addService(service);
    }
    
    private boolean addOneTimeService (HttpServletRequest req)
    {
        Services service = new Services(req.getParameter("serviceName"), 
                Boolean.parseBoolean(req.getParameter("oneTimeService")), 
                Float.parseFloat(req.getParameter("serviceFees")),null);
        Database db = new Database();
        return db.addOneTimeService(service);
    }    
}
