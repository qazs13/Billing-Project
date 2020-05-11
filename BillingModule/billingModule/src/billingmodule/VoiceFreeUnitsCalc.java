package billingmodule;

import Database.databaseConnection;
import Interfaces.NetConnection;
import SystemObjects.*;
import static java.lang.Math.abs;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;



public class VoiceFreeUnitsCalc {
    
    
   
   
    public Float fuVoiceUpdate(UDR customerUDR, BillDateInterval intervalDate){//contains dialA,pid,sid
        
        databaseConnection db = new databaseConnection();
        FreeUnit fu = db.ProfileFU(new FreeUnit(customerUDR.getProfileID()));
        Vector<UDR> udrList= db.customerUDRs(new UDR(customerUDR.getDialA(),customerUDR.getProfileID()
                ,customerUDR.getServiceID()), intervalDate);
        CustomerProfile customerRemainedFUs;
        CustomerProfile cProfileupdateFU;
        ProfileService profileVoiceDetails;
        Boolean state = false ;
        
        int updatedValue =0;
        int consumedData = 0;
        Float costOfService = 0f;
        Float TotalUDRsCost = 0f;
        Date start_date;
        Date end_date;
        
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
                                    state=db.UpdateCustomerFUs(cProfileupdateFU,NetConnection.onNet);
                                    costOfService = 0f;
                                    TotalUDRsCost += costOfService;
                                    System.out.println("Cost of onNet Voice service is zero");
//                                    //is_billed ---> true 
                            }else{ // cost of (-50) ---> profile_service

                                    cProfileupdateFU = new CustomerProfile(
                                                udr.getDialA(),udr.getProfileID(),udr.getServiceID(),0);
                                    state=db.UpdateCustomerFUs(cProfileupdateFU,NetConnection.onNet);
                                    consumedData = abs(updatedValue);  
                                    costOfService = (consumedData * profileVoiceDetails.getFeeSameOperator())
                                            /(profileVoiceDetails.getRoundAmount()); 
                                    TotalUDRsCost += costOfService;
                                    System.out.println("onNet voice Cost Calcu :" + costOfService);         
                            }
                        }else{

                            costOfService = udr.getCost();
                            TotalUDRsCost += costOfService;
                            System.out.println("cost from Rating Module:" + costOfService);
                        }
                    }else if(!(udr.getDialB().regionMatches(true,0,"0020",0, 4))){
                                    
                        costOfService = (udr.getDurationMsgVolume() * profileVoiceDetails.getFeeInternationally())
                                /(profileVoiceDetails.getRoundAmount());                       
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
                                    state=db.UpdateCustomerFUs(cProfileupdateFU,NetConnection.crossNet);
                                    costOfService = 0f;
                                    TotalUDRsCost += costOfService;                                     
                                    System.out.println("cost of crossVoiceNet Service is zero");
              
                            }else{
                                    cProfileupdateFU = new CustomerProfile(
                                                udr.getDialA(),udr.getProfileID(),udr.getServiceID(),0);
                                    state=db.UpdateCustomerFUs(cProfileupdateFU,NetConnection.crossNet);
                                    consumedData = abs(updatedValue);  
                                    costOfService = (consumedData * profileVoiceDetails.getFeeAnotherOperator())
                                            /(profileVoiceDetails.getRoundAmount());                                    
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
            }   
          
        }   
          //total cost of all cdrs of voice per customer 
            System.out.println("Total cost of udrs of customer # : "+ TotalUDRsCost);
            return TotalUDRsCost;
    }
    
//    public static void main(String [] args){
//        VoiceFreeUnitsCalc voiceFUcalc = new VoiceFreeUnitsCalc();
//        voiceFUcalc.fuVoiceUpdate(new UDR("00201215860927",1,1));
//    }
    
}
