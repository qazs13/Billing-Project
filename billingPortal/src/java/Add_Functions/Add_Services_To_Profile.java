package Add_Functions;

import Database.Database;
import Database_Tables.Profile;
import Database_Tables.Profile_Services;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Add_Services_To_Profile extends HttpServlet 
{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        Database db = new Database();
        String[] allServices = req.getParameter("allServicesName").split(",");
        String profileName = req.getParameter("profileNameServices");
        int operation = 0;
        if (db.checkProfileExistance(new Profile(profileName)))
        {
            int profileID = db.getProfileID(profileName);
            for (int i = 0; i < allServices.length; i++)
            {
                int serviceID = db.getServiceIDByItsName(allServices[i]);
                int round_Amount = Integer.parseInt(req.getParameter("serviceRound"+allServices[i]));
                float fees_local_same = Float.parseFloat(req.getParameter("serviceFeesSameLocal"+allServices[i]));
                float fees_local_diff = Float.parseFloat(req.getParameter("serviceFeesOtherLocal"+allServices[i]));
                float fees_international = Float.parseFloat(req.getParameter("serviceFeesInternational"+allServices[i]));
                
                Profile_Services profile_Services = new Profile_Services(profileID, serviceID,
                        round_Amount, fees_local_same, fees_local_diff, fees_international);
                operation += db.addProfileServices(profile_Services);
                System.out.println("////////////////////////////////////////////////////////////////////////////");
//                System.out.println(profile_Services.getSid());
//                System.out.println(profile_Services.getRound_amount());
//                System.out.println(profile_Services.getFees_local_same());
                System.out.println("ADDED NEW PROFILE SERVICE SUCCESSFULLY");
                System.out.println("////////////////////////////////////////////////////////////////////////////");  
            }
        }
        else
        {
            resp.sendError(400);
        }
        
        if (operation == allServices.length)
        {
            resp.sendError(200);
        }
        else
        {
            resp.sendError(400);
        }
    }
}
