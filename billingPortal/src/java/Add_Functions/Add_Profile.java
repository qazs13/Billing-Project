package Add_Functions;

import Database.Database;
import Database_Tables.Profile;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Add_Profile extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        Database db = new Database();
        Profile profile = new Profile(req.getParameter("profileName"),
                Integer.parseInt(req.getParameter("renewProfileDuration")),
                Float.parseFloat(req.getParameter("profileFees")));
        boolean operation = false;
        System.out.println("////////////////////////////////////////////////////////////");
        System.out.println("New Profile Is HERE");
        System.out.println("////////////////////////////////////////////////////////////");
        if (db.checkProfileExistance(profile))
        {
            operation = db.addNewProfile(profile);
            System.out.println("////////////////////////////////////////////////////////////");
            System.out.println("New Profile Is Successfully ADDED");
            System.out.println("////////////////////////////////////////////////////////////");            
        }
        
        if (operation)
        {
            resp.sendError(200);
        }
        else
        {
            resp.sendError(400);
        }
    }
}
