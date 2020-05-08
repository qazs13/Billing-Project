package billingmodule;

import Database.databaseConnection;
import SystemObjects.UDR;
import java.util.Vector;
import SystemObjects.*;


public class BillingModule {

  
    public static void main(String[] args) {
        
        //Vector allCustomersInUDRTable ---> each object has DialA & profileID
        databaseConnection db = new databaseConnection();
        Vector<UDR> allCustomersInUDRTable = db.retrieveAllCustomersHaveUDRs();
        
        FreeUnitsCalc retrieveCustomerObjectTotalFUCalcuation = new FreeUnitsCalc();
        //Start for loop 
        
            for(UDR customer:allCustomersInUDRTable){

                    //get ProfileTotalFees
                    Profile customerProfile = db.retieveProfileDetails(new Profile(customer.getProfileID()));
                    //get Return of FreeUnitCalculation ------Vector------>ServiceName:Cost
                    CustomerProfile cProfile = retrieveCustomerObjectTotalFUCalcuation.CustomerServicesCalculations(new 
                            UDR(customer.getDialA(), customer.getProfileID()));
                    
                    System.out.println("Billing Module Test ####" + cProfile.getTotalDataServiceCost());
                    //return from class Recuring services/one-Time(may be +ve or -ve) services 
                    //Taxs hardcodes
                    //insert or update in bill Table 
                    //generate PDF
            }
        
        
    }
    
}
