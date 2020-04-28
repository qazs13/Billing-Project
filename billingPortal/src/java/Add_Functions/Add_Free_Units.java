package Add_Functions;

import Database.Database;
import Database_Tables.Free_Units;
import Database_Tables.Profile;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Add_Free_Units extends HttpServlet 
{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        Database db = new Database();
        String profileName = req.getParameter("profileNameServices");
        Free_Units free_Units = new Free_Units(Float.parseFloat(req.getParameter("free_Unit_Voice_On_Net")),
                Float.parseFloat(req.getParameter("free_Unit_Voice_On_Cross_Net")),
                Float.parseFloat(req.getParameter("free_Unit_SMS_On_Net")), 
                Float.parseFloat(req.getParameter("free_Unit_SMS_On_Cross_Net")),
                Float.parseFloat(req.getParameter("free_Unit_For_Internet")), db.getProfileID(profileName));
        
        if (db.checkProfileExistance(new Profile(profileName)))
        {
            if (db.addFreeUnitsToProfile(free_Units))
            {
                System.out.println("///////////////////////////////////////////////////////////");
                System.out.println("Add Free UNITS TO PROFILE NAME "+profileName);
                System.out.println("///////////////////////////////////////////////////////////");
                resp.sendError(200);
            }
            else
            {
                resp.sendError(513);
            }
        }
        else
        {
            resp.sendError(513);
        }
    }
}