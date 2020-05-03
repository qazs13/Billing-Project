package billingmodule;

import Database.databaseConnection;
import SystemObjects.*;
import java.util.Vector;


public class FreeUnitsCalc {
    
    public void CustomerServicesCalculations(){
        databaseConnection db = new databaseConnection();
        DataFreeUnitsCalc dataObject = new DataFreeUnitsCalc();
        SMSFreeUnitsCalc smsObject = new SMSFreeUnitsCalc();
        VoiceFreeUnitsCalc voiceObject = new VoiceFreeUnitsCalc();
        Float totalDataServiceCost = 0f;
        Float totalSMSServiceCost = 0f;
        Float totalVoiceServiceCost = 0f;
        
        Vector<UDR> allCustomersInUDRTable = db.retrieveAllCustomersHaveUDRs();
        
        for(UDR customer:allCustomersInUDRTable){
 
            System.out.println("element1:" + customer.getDialA());
            System.out.println("element2:"+ customer.getProfileID());
            System.out.println("############################################");
            totalVoiceServiceCost=voiceObject.fuVoiceUpdate(new UDR(customer.getDialA(),customer.getProfileID(),1));
            System.out.println("############################################");
            System.out.println("############################################");
            totalSMSServiceCost=smsObject.fuSMSUpdate(new UDR(customer.getDialA(),customer.getProfileID(),2));
            System.out.println("############################################");
            System.out.println("############################################");
            totalDataServiceCost=dataObject.fuInternetUpdate(new UDR(customer.getDialA(),customer.getProfileID(),3));
            System.out.println("############################################");
            System.out.println("###############"+totalVoiceServiceCost+"###############");
            System.out.println("###############"+totalSMSServiceCost+"###############");
            System.out.println("###############"+totalDataServiceCost+"###############");
            System.out.println("############################################");

        }
     
    }
        
    public static void main(String [] args){     
        FreeUnitsCalc fucalc = new FreeUnitsCalc();  
        fucalc.CustomerServicesCalculations();
    }
}
