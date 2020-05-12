package billingmodule;

import Database.databaseConnection;
import Interfaces.NetConnection;
import SystemObjects.*;
import static java.lang.Math.abs;
import java.util.Vector;



public class VoiceFreeUnitsCalc {
    
    
   
   
    public Float fuVoiceUpdate(UDR customerUDR, BillDateInterval intervalDate){//contains dialA,pid,sid
        
        databaseConnection db = new databaseConnection();
        Vector<UDR> udrList= db.customerUDRs(new UDR(customerUDR.getDialA(),customerUDR.getProfileID()
                ,customerUDR.getServiceID()), intervalDate);
        CustomerProfile customerRemainedFUs;
        CustomerProfile cProfileupdateFU;
        ProfileService profileVoiceDetails;
        Boolean state = false ;
        
        Float updatedValue =0f;
        Float consumedData = 0f;
        Float costOfService = 0f;
        Float TotalUDRsCost = 0f;
        
        for(UDR udr: udrList){
           
                System.out.println("####"+ udr.getDialA() + "#####" + udr.getDialB()+ 
                    "#####"+ udr.getOrderedDate()+ "##########" + udr.getDurationMsgVolume());     
        }

        if(udrList.isEmpty()){
                    System.out.println("Customer Has no Voice Usage");
        }else{
            
            for(UDR udr:udrList){
                  
                    // customerRemainedFUs obj from constuctor "new CustomerProfile();"
                    customerRemainedFUs= db.RemainedFreeUnits(new CustomerProfile(
                            udr.getProfileID(),udr.getDialA()));
                    
                     profileVoiceDetails = db.retrieveProfileService(new ProfileService(udr.getProfileID(),
                                            udr.getServiceID()));

                    
                    if(udr.getDialB().regionMatches(true,0,udr.getDialA(),0, 6)){//same operator

                        if(customerRemainedFUs.getFUVoiceOnNet() > 0){ // has free units 
                            
                            updatedValue = customerRemainedFUs.getFUVoiceOnNet() - udr.getDurationMsgVolume();//100-150=-50
                            System.out.println("updated Voice On Net Value "+ updatedValue);
                            
                            if(updatedValue >= 0){

                                    cProfileupdateFU = new CustomerProfile(
                                                udr.getDialA(),udr.getProfileID(),udr.getServiceID(),updatedValue);
                                    state=db.UpdateCustomerFUs(cProfileupdateFU,NetConnection.onNet, udr.getUdrID());
                                    costOfService = 0f;
                                    TotalUDRsCost += costOfService;
                                    System.out.println("Cost of onNet Voice service is zero "+state);
//                                    //is_billed ---> true 
                            }else{ // cost of (-50) ---> profile_service

                                    cProfileupdateFU = new CustomerProfile(
                                                udr.getDialA(),udr.getProfileID(),udr.getServiceID(),0f);
                                    state=db.UpdateCustomerFUs(cProfileupdateFU,NetConnection.onNet,udr.getUdrID());
                                    consumedData = abs(updatedValue);
                                    float round = ((float) consumedData) / profileVoiceDetails.getRoundAmount(); 
                                    round =   (float) Math.ceil(round);                                    
                                    costOfService = profileVoiceDetails.getFeeSameOperator() * round;
                                    TotalUDRsCost += costOfService;
                                    System.out.println("onNet voice Cost Calcu :" + costOfService);         
                            }
                        }else{

                            costOfService = udr.getCost();
                            TotalUDRsCost += costOfService;
                            System.out.println("cost from Rating Module:" + costOfService);
                        }
                    }else if(!(udr.getDialB().regionMatches(true,0,"0020",0, 4))){
                                    
                        float round = ((float) consumedData) / profileVoiceDetails.getRoundAmount(); 
                        round =   (float) Math.ceil(round);                        
                        costOfService = profileVoiceDetails.getFeeInternationally() * round;
                        TotalUDRsCost += costOfService;                       
                        System.out.println(" international voice cost from Rating Module:" + costOfService);
                        
                    }else if(!(udr.getDialB().regionMatches(true,0,udr.getDialA(),0, 6))){
                        
                        if(customerRemainedFUs.getFUVoiceCrossNet() > 0){
//                            
                            updatedValue = customerRemainedFUs.getFUVoiceCrossNet() - udr.getDurationMsgVolume();
                            System.out.println("fuVoiceCrossNet :" +customerRemainedFUs.getFUVoiceCrossNet());
                            System.out.println("updated Voice On Net Value "+ updatedValue);
//                            
                            if(updatedValue >= 0){
//                                  
                                    cProfileupdateFU = new CustomerProfile(
                                                udr.getDialA(),udr.getProfileID(),udr.getServiceID(),updatedValue);
                                    state=db.UpdateCustomerFUs(cProfileupdateFU,NetConnection.crossNet, udr.getUdrID());
                                    costOfService = 0f;
                                    TotalUDRsCost += costOfService;                                     
                                    System.out.println("cost of crossVoiceNet Service is zero");
              
                            }else{
                                    cProfileupdateFU = new CustomerProfile(
                                                udr.getDialA(),udr.getProfileID(),udr.getServiceID(),0f);
                                    state=db.UpdateCustomerFUs(cProfileupdateFU,NetConnection.crossNet, udr.getUdrID());
                                    consumedData = abs(updatedValue);
                                    float round = ((float) consumedData) / profileVoiceDetails.getRoundAmount(); 
                                    round =   (float) Math.ceil(round);
                                    costOfService = profileVoiceDetails.getFeeAnotherOperator() * round;
                                    TotalUDRsCost += costOfService;
                                    System.out.println("crossNet voice Cost Calcu :" + costOfService);                              
                            }
                        }else{
                            costOfService = udr.getCost();  
                            TotalUDRsCost += costOfService;   
                            System.out.println("crossNet cost from Rating Module:" + costOfService);
                        }
                    }else{
                        System.out.println("#############error msh mtwak3##############");
                    }
                    state = db.updateUDRwithFalse(udr.getUdrID());
            }   
          
        }   
          //total cost of all cdrs of voice per customer 
            System.out.println("Total cost of udrs of customer # : "+ TotalUDRsCost);
            return TotalUDRsCost;
    }
}
