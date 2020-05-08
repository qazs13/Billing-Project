package Rating_Module;

import Database.Database;
import Database_Tables.CDR;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Rating_Module 
{
    public static void main(String[] args) 
    {
        Database db = new Database();
        new Thread(() -> {            
            while (true) 
            {            
                CDR allCDRs = db.getAllNotRadtedCDRs();
                if (allCDRs.getAllCDRS().size() > 0)
                {
                    System.out.println("Rating_Module is Starting and Rating CDRs");
                    for (int i = 0; i < allCDRs.getAllCDRS().size(); i++)
                    {
                        int profileID = db.select_pid(allCDRs.getAllCDRS().elementAt(i));
                        if (profileID > 0)
                        {
                            float cost = db.calculate_fees(profileID ,allCDRs.getAllCDRS().elementAt(i));
                            if (db.insert_into_udr(profileID, allCDRs.getAllCDRS().elementAt(i), cost))
                            {
                                if (db.update_cdr(allCDRs.getAllCDRS().elementAt(i)))
                                {
                                    System.out.println("========================================================");
                                    System.out.println("All Operation Ended Successfully");
                                    System.out.println("========================================================");
                                }
                                else
                                {
                                    System.out.println("========================================================");
                                    System.out.println("CDR Number  = "+allCDRs.getAllCDRS().elementAt(i).getCdr_id()+
                                        " Couldn't be Updated as Rated");
                                    System.out.println("========================================================");
                                }
                            }
                            else
                            {
                                System.out.println("========================================================");
                                System.out.println("CDR Number  = "+allCDRs.getAllCDRS().elementAt(i).getCdr_id()+
                                        " Couldn't be Insertead on UDRs Table");
                                System.out.println("========================================================");
                            }
                        }
                        else
                        {
                            System.out.println("========================================================");
                            System.out.println("No Profile ID FOR MSISDN = "+allCDRs.getAllCDRS().elementAt(i).getDiala());
                            System.out.println("========================================================");
                        }
                    }
                }
                else
                {
                    try
                    {
                        System.out.println("No CDRs to Be Rated");
                        TimeUnit.SECONDS.sleep(10);
                    }
                    catch (InterruptedException ex) 
                    {
                        ex.printStackTrace();
                    }
                }
            }            
        }).start();
    }
}
