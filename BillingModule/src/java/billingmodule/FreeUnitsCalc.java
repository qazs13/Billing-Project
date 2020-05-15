package billingmodule;

import Database.databaseConnection;
import SystemObjects.*;
import java.util.Vector;


public class FreeUnitsCalc {
    
    public CustomerProfile CustomerServicesCalculations(UDR customer, BillDateInterval billIntervalDate){

        DataFreeUnitsCalc dataObject = new DataFreeUnitsCalc();
        SMSFreeUnitsCalc smsObject = new SMSFreeUnitsCalc();
        VoiceFreeUnitsCalc voiceObject = new VoiceFreeUnitsCalc();
        Float totalDataServiceCost = 0f;
        Float totalSMSServiceCost = 0f;
        Float totalVoiceServiceCost = 0f;
        
        System.out.println("Dial A: " + customer.getDialA());
        System.out.println("Profile ID: "+ customer.getProfileID());
        System.out.println("############################################");

        totalVoiceServiceCost = voiceObject.fuVoiceUpdate(new UDR(customer.getDialA(),customer.getProfileID(),1),
                billIntervalDate);
        System.out.println("############################################");
        System.out.println("############################################");
        totalSMSServiceCost=smsObject.fuSMSUpdate(new UDR(customer.getDialA(),customer.getProfileID(),2),
                billIntervalDate);
        System.out.println("############################################");
        System.out.println("############################################");
        totalDataServiceCost=dataObject.fuInternetUpdate(new UDR(customer.getDialA(),customer.getProfileID(),3),
                billIntervalDate);
        System.out.println("############################################");
        System.out.println("###############"+totalVoiceServiceCost+"###############");
        System.out.println("###############"+totalSMSServiceCost+"###############");
        System.out.println("###############"+totalDataServiceCost+"###############");
        System.out.println("############################################");

        CustomerProfile customerReccuringServices = new CustomerProfile(customer.getDialA(),
                customer.getProfileID(), totalVoiceServiceCost, totalSMSServiceCost, totalDataServiceCost);

        return customerReccuringServices;     
    }
        
//    public static void main(String [] args){     
//        FreeUnitsCalc fucalc = new FreeUnitsCalc();  
//        fucalc.CustomerServicesCalculations(new UDR("00201289753456", 2));
//    }
}
