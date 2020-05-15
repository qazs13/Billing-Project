package billingmodule;

import SystemObjects.*;
import Database.*;

public class RenewCustomerProfile {
    
    public Boolean renewProfile(UDR customerX){
            
            databaseConnection db = new databaseConnection();
            
            CustomerProfile renewCustomerProfile = db.RemainedFreeUnits(new CustomerProfile(
                                        customerX.getProfileID(), customerX.getDialA()));
                                
            FreeUnit fuRetrieval = db.ProfileFU(new FreeUnit(customerX.getProfileID()));
            renewCustomerProfile.setFUVoiceOnNet(fuRetrieval.getFUVoiceOnNet());
            renewCustomerProfile.setFUVoiceCrossNet(fuRetrieval.getFUVoiceCrossNet());
            renewCustomerProfile.setFUSMSOnNet(fuRetrieval.getFUSMSOnNet());
            renewCustomerProfile.setFUSMSCrossNet(fuRetrieval.getFUSMSCrossNet());
            renewCustomerProfile.setFUInternet(fuRetrieval.getFUInternet());
            
            Boolean state = db.renewCustomerProfile(renewCustomerProfile);
            if(state){
                System.out.println("Successed !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! yarab");
                return true;
            }else{
                System.out.println("Something hapened wrong in renewing profile");
                return false;
            }
            
            
    }
    
}
