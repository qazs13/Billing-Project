package Add_Functions;

import Database.Database;
import Database_Tables.Customer;
import Database_Tables.Customer_Profile;
import Database_Tables.Free_Units;
import Database_Tables.OCC;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Add_Customer extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        long date = System.currentTimeMillis();
        Database db = new Database();
        
        String profileName = req.getParameter("selectProfile");
        int profileID = db.getProfileID(profileName);
        String msisdn = "002012"+req.getParameter("msisdn");
        String fName = req.getParameter("fName");
        String lName = req.getParameter("lName");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        Date startDate = new Date(date);
        int blockedServices = db.getServiceIDByItsName(req.getParameter("blockedService"));
        String[] allRecurringServices = req.getParameter("allRecurringServices").split(",");
        String onetTimeService = req.getParameter("oneTimeService");

        Free_Units freeUnit = db.getFreeUnitOfProfileByItsID(profileID);        
////////////////////////////////////////////////////////////////////////////////////////////////////        
        Customer customer = new Customer(msisdn, fName, lName, email, address);
////////////////////////////////////////////////////////////////////////////////////////////////////                
        Customer_Profile customerProfile = new Customer_Profile(msisdn, profileID, startDate.toString().replace("-", ""), "",
                blockedServices, freeUnit.getFree_voice_same(), freeUnit.getFree_voice_diff(), 
                freeUnit.getFree_sms_same(), freeUnit.getFree_sms_diff(), freeUnit.getFree_internet());
////////////////////////////////////////////////////////////////////////////////////////////////////                
        System.out.println(msisdn+" "+fName+" "+lName+" "+email+" "+address+" "+startDate+" "+blockedServices+" "+onetTimeService);
////////////////////////////////////////////////////////////////////////////////////////////////////        
        
        if (!db.checkCustomerExistance(customer))
        {
            System.out.println("Not Exist");
            db.addCustomer(customer);
            db.addCustomerProfile(customerProfile);
            for (int i = 0; i < allRecurringServices.length; i++)
            {
                db.addOCCToCustomer(new OCC(msisdn, db.getServiceIDByItsName(allRecurringServices[i]), 
                        "recurring_service", false, startDate));
            }
            db.addOCCToCustomer(new OCC(msisdn, db.getOneTimeServiceIDByItsName(onetTimeService), 
                    "one_time_service", false, startDate));
            resp.sendError(200);                       
        }
        else 
        {
            System.out.println("Exist");
            resp.sendError(513);
        }
    }        
}