package billingmodule;

import Database.databaseConnection;
import Interfaces.NetConnection;
import SystemObjects.*;
import static java.lang.Math.abs;
import java.util.Vector;


public class VoiceFreeUnitsCalc {
    
    databaseConnection db = new databaseConnection();
    FreeUnit fu = db.ProfileFU(new FreeUnit(1));
    CustomerProfile customerRemainedFUs;
    Vector<UDR> udrList= db.customerUDRs(new UDR("+201215860927",1,2));
    ProfileService profileVoiceDetails;
    NetConnection conn;
    Boolean state;
    
    public void fuVoiceUpdate(){
        
        int updatedValue =0;
        int consumedData = 0;
        Float costOfService = 0f;
        
//        for(UDR udr: udrList){
//            System.out.println("####"+ udr.getDialA() + "#####" + udr.getDialB()+ "#####"+ udr.getOrderedDate());
//        }

        if(udrList.isEmpty()){
                    System.out.println("Customer Has no SMS Usage");
        }else{
            
            for(UDR udr:udrList){
                    customerRemainedFUs= db.RemainedFreeUnits(new CustomerProfile(1,"01215860927"));
                    customerRemainedFUs.setServiceID(2);
                     profileVoiceDetails = db.retrieveProfileService(new ProfileService(udr.getProfileID(),
                                            udr.getServiceID()));
                    
                    if(udr.getDialB().regionMatches(true,0,udr.getDialA(),0, 5)){//same operator
                        
                        if(customerRemainedFUs.getFUVoiceOnNet() > 0){ // has free units 
                            
                            updatedValue = customerRemainedFUs.getFUVoiceOnNet() - udr.getDurationMsgVolume();//100-150=-50
                            System.out.println("updated Voice On Net Value "+ updatedValue);
                            
                            if(updatedValue >= 0){
                                    
                                    customerRemainedFUs.setConsumedQuantity(updatedValue);
                                    state=db.UpdateCustomerFUs(customerRemainedFUs,conn.onNet);
                                    costOfService = 0f;
                                    System.out.println("Cost of onNet Voice service is zero");
                                    //is_billed ---> true 
                            }else{ // cost of (-50) ---> profile_service
                                
                                    customerRemainedFUs.setConsumedQuantity(0);
                                    state=db.UpdateCustomerFUs(customerRemainedFUs,conn.onNet);
                                    //call function(give it cost valuein udr table )
                                    consumedData = abs(updatedValue);
                                    
                                    costOfService = (consumedData * profileVoiceDetails.getFeeSameOperator())
                                            /(profileVoiceDetails.getRoundAmount());
                                    
                                    System.out.println("onNeet voice Cost Calcu :" + costOfService);         
                            }
                        }
                        else{
                            //has no fu ONNET
                            //call function calculate consumption from cost
                            costOfService = udr.getCost();
                            System.out.println("cost from Rating Module:" + costOfService);
                        }
                    }else if(!(udr.getDialB().regionMatches(true,0,"+20",0, 3))){
                        
                                    
                        costOfService = (udr.getDurationMsgVolume() * profileVoiceDetails.getFeeInternationally())
                                /(profileVoiceDetails.getRoundAmount());
                        System.out.println(" international voice cost from Rating Module:" + costOfService);
                        
                    }else if(!(udr.getDialB().regionMatches(true,0,udr.getDialA(),0, 5))){
                        if(customerRemainedFUs.getFUVoiceCrossNet() > 0){
                            
                            updatedValue = customerRemainedFUs.getFUVoiceCrossNet() - udr.getDurationMsgVolume();
                            System.out.println("updated Voice On Net Value "+ updatedValue);
                            
                            if(updatedValue >= 0){
                                
                                    customerRemainedFUs.setConsumedQuantity(updatedValue);
                                    state=db.UpdateCustomerFUs(customerRemainedFUs,conn.crossNet);
                                      costOfService = 0f;
                                    System.out.println("cost of crossVoiceNet Service is zero");
                                
                            }else{
                                    
                                    customerRemainedFUs.setConsumedQuantity(0);
                                    state=db.UpdateCustomerFUs(customerRemainedFUs,conn.crossNet);
                                    //call function(give it cost valuein udr table )
                                    consumedData = abs(updatedValue);
                                    
                                    costOfService = (consumedData * profileVoiceDetails.getFeeAnotherOperator())
                                            /(profileVoiceDetails.getRoundAmount());
                                    
                                    System.out.println("crossNet voice Cost Calcu :" + costOfService);
                                
                            }
                        }else{
                             //has No fu Cross Net
                            //call function calculate consumption from cost
                            costOfService = udr.getCost();
                            System.out.println("crossNet cost from Rating Module:" + costOfService);
                        }
                    }else{
                        System.out.println("#############error msh mtwak3##############");
                    }
            }   
            //total cost of all cdrs of voice per customer 
        }       
    }
    
    public static void main(String [] args){
        VoiceFreeUnitsCalc voiceFUcalc = new VoiceFreeUnitsCalc();
        voiceFUcalc.fuVoiceUpdate();
    }
    
}
