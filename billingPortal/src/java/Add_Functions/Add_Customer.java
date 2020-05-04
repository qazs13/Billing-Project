package Add_Functions;

import Database.Database;
import Database_Tables.Customer;
import Database_Tables.Customer_Profile;
import Database_Tables.Free_Units;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Add_Customer extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        Database db = new Database();
        
        String profileName = req.getParameter("selectProfile");
        int profileID = db.getProfileID(profileName);
        String msisdn = req.getParameter("msisdn");
        String fName = req.getParameter("fName");
        String lName = req.getParameter("lName");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        //String date = dateFormat.format(date);

        Free_Units freeUnit = db.getFreeUnitOfProfileByItsID(profileID);        
////////////////////////////////////////////////////////////////////////////////////////////////////        
        Customer customer = new Customer(msisdn, fName, lName, email, address);
        
        /*Customer_Profile customerProfile = new Customer_Profile(msisdn, profileID, start_date, end_date,
                profileID, profileID, profileID, 
                profileID, profileID, profileID);*/
        
        if (!db.checkCustomerExistance(customer))
        {
            System.out.println("Not Exist");
        }
        else 
        {
            System.out.println("Exist");
        }
    }
}
