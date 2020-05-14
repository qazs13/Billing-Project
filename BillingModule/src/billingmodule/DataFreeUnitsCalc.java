package billingmodule;

import Database.databaseConnection;
import SystemObjects.*;
import java.util.Vector;
import static java.lang.Math.abs;


public class DataFreeUnitsCalc {
     
   
    
    public Float fuInternetUpdate(UDR customerUDR, BillDateInterval intervalDate){
        //dialA & profileID & serviceID & YYYYMM
        
        databaseConnection db = new databaseConnection();
        Vector<UDR> udrList= db.customerUDRs(new UDR(customerUDR.getDialA(),customerUDR.getProfileID()
                ,customerUDR.getServiceID()), intervalDate); //add yyyyMM
        CustomerProfile customerRemainedFUs;
        CustomerProfile cProfileupdateFU;
        ProfileService profileDataDetails;
        Boolean state = false;
        boolean has_freeUnits = false;
            
        Float updatedValue = 0f;
        Float consumedData = 0f;
        Float costOfService = 0f;
        Float TotalUDRsCost = 0f;
        
        for(UDR udr: udrList){
            System.out.println("####"+ udr.getDialA() + "#####" + udr.getDurationMsgVolume()
            + "##########" + udr.getOrderedDate());
        }
        

        if(udrList.isEmpty()){
            System.out.println("Customer Has no Internet Usage");     
        }
        else{
            for(UDR udr:udrList){
                
                customerRemainedFUs= db.RemainedFreeUnits(new CustomerProfile(udr.getProfileID()
                        ,udr.getDialA()));
                
                profileDataDetails = db.retrieveProfileService(new ProfileService(udr.getProfileID(),
                                            udr.getServiceID()));
                         
                if(udr.getExternalCharges() == 0){
                    
                    if(customerRemainedFUs.getFUInternet() > 0){
                          
                                updatedValue = customerRemainedFUs.getFUInternet() - udr.getDurationMsgVolume() / profileDataDetails.getRoundAmount();
                                System.out.println("updated Internet Value "+ updatedValue);
                              
                                if(updatedValue >= 0){
                                            
                                        cProfileupdateFU = new CustomerProfile(
                                            udr.getDialA(),udr.getProfileID(),udr.getServiceID(),updatedValue);
                                        state=db.UpdateCustomerFUs(cProfileupdateFU,"nothing");
                                        costOfService = 0f;
                                        TotalUDRsCost += costOfService;
                                        System.out.println("Cost of Internet service is zero");
                                        has_freeUnits = true;
                                }else{
                                      
                                        cProfileupdateFU = new CustomerProfile(
                                                    udr.getDialA(),udr.getProfileID(),udr.getServiceID(),0f);  
                                        state=db.UpdateCustomerFUs(cProfileupdateFU,"nothing");
                                        consumedData = abs(updatedValue);  
                                        float round = (float) Math.ceil(consumedData);                                                                      
                                        costOfService = profileDataDetails.getFeeSameOperator() * round;
                                        TotalUDRsCost += costOfService;
                                        System.out.println("Cost Calcu :" + costOfService);
                                }    
                    }else{
                            costOfService = udr.getCost();
                            TotalUDRsCost += costOfService;
                            System.out.println("cost of service from Rating module" + costOfService);
                    }            
                }else{
                    costOfService = udr.getExternalCharges();
                    TotalUDRsCost += costOfService;
                    System.out.println("Cost Calcu :" + costOfService);     
                }  
                state = db.updateUDRwithFalse(udr.getUdrID());
                if (has_freeUnits)
                {
                    db.updateHasFreeunit(udr.getUdrID());
                }                   
            }
        } 
        
        System.out.println("Total consumption od Data :"+ TotalUDRsCost); 
        return TotalUDRsCost;
    }
}
