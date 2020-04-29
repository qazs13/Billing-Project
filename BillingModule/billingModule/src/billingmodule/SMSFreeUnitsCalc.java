package billingmodule;

import Database.databaseConnection;
import SystemObjects.*;
import java.util.Vector;
import Interfaces.NetConnection;
import static java.lang.Math.abs;

public class SMSFreeUnitsCalc {
       
    databaseConnection db = new databaseConnection();
    FreeUnit fu = db.ProfileFU(new FreeUnit(1));
    CustomerProfile customerRemainedFUs;
    Vector<UDR> udrList= db.customerUDRs(new UDR("01215860927",1,3));
    ProfileService profileSMSDetails;
    NetConnection conn;
    Boolean state;

    
    
    public void fuSMSUpdate(){
         
        int updatedValue = 0;
        int consumedData = 0;
        Float costOfService = 0f;
        
        for(UDR udr: udrList){
            System.out.println("####"+ udr.getDialA() + "#####" + udr.getDialB()+ "#####"+ udr.getOrderedDate());
        }
            
        if(udrList.isEmpty()){
            
             System.out.println("Customer Has no SMS Usage");
             
        }else{
            
            for(UDR udr:udrList){
                customerRemainedFUs= db.RemainedFreeUnits(new CustomerProfile(1,"01215860927"));
                customerRemainedFUs.setServiceID(3);
                profileSMSDetails = db.retrieveProfileService(new ProfileService(udr.getProfileID(),
                                            udr.getServiceID()));
                
                if(udr.getDialB().regionMatches(true,0,udr.getDialA(),0, 3)){
                    
                        if(customerRemainedFUs.getFUSMSOnNet() > 0){
                            
                            updatedValue = customerRemainedFUs.getFUSMSOnNet() - udr.getDurationMsgVolume();
                            System.out.println("updated Voice On Net Value "+ updatedValue);
                            
                            
                            if(updatedValue >= 0){
                                
                                    customerRemainedFUs.setConsumedQuantity(updatedValue);
                                    state=db.UpdateCustomerFUs(customerRemainedFUs,conn.onNet);
                                    costOfService = 0f;
                                    System.out.println("Cost of this service is zero");
                                
                            }else{
                                    customerRemainedFUs.setConsumedQuantity(0);
                                    state=db.UpdateCustomerFUs(customerRemainedFUs,conn.onNet);
                                    consumedData = abs(updatedValue);
//                                    profileSMSDetails = db.retrieveProfileService(new ProfileService(udr.getProfileID(),
//                                            udr.getServiceID()));
                                    
                                    costOfService = (consumedData * profileSMSDetails.getFeeSameOperator())
                                            /(profileSMSDetails.getRoundAmount());
                                    
                                    System.out.println("Cost Calcu :" + costOfService);
                                    
                            }
                            
                        }else{
                            //has no fu ONNET
                            //call function calculate consumption from cost
                            costOfService = udr.getCost();
                            System.out.println("cost from Rating Module:" + costOfService);
                        }
                }else if(!(udr.getDialB().regionMatches(true,0,udr.getDialA(),0, 3))){
                    
                    
                        if(customerRemainedFUs.getFUSMSCrossNet() > 0){
                            
                            updatedValue = customerRemainedFUs.getFUSMSCrossNet() - udr.getDurationMsgVolume();
                            System.out.println("updated Voice On Net Value "+ updatedValue);
                            
                             if(updatedValue >= 0){
                                 
                                    customerRemainedFUs.setConsumedQuantity(updatedValue);
                                    state=db.UpdateCustomerFUs(customerRemainedFUs,conn.crossNet); 
                                    costOfService = 0f;
                                    System.out.println("cost of sms Service is zero");
                                    
                            }else{
                                 
                                    customerRemainedFUs.setConsumedQuantity(0);
                                    state=db.UpdateCustomerFUs(customerRemainedFUs,conn.crossNet);
                                    //call function(give it cost valuein udr table )
                                    consumedData = abs(updatedValue);
//                                    profileSMSDetails = db.retrieveProfileService(new ProfileService(udr.getProfileID(),
//                                            udr.getServiceID()));
                                    
                                    costOfService = (consumedData * profileSMSDetails.getFeeAnotherOperator())
                                            /(profileSMSDetails.getRoundAmount());
                                    
                                    System.out.println("Cost Calcu :" + costOfService);                  
                            }
                        }else{
                            //has No fu Cross Net
                            //call function calculate consumption from cost
                            costOfService = udr.getCost();
                            System.out.println("cost from Rating Module:" + costOfService);
                        }  
                }else if(!(udr.getDialB().regionMatches(true,0,"+20",0, 3))){
                        
//                        profileSMSDetails = db.retrieveProfileService(new ProfileService(udr.getProfileID(),
//                                            udr.getServiceID()));
                                    
                        costOfService = (consumedData * profileSMSDetails.getFeeInternationally())
                                /(profileSMSDetails.getRoundAmount());
                        System.out.println("cost from Rating Module:" + costOfService);
                }           
            }       
        }
    }
    
    public static void main(String [] args){
        SMSFreeUnitsCalc smsFUcalc = new SMSFreeUnitsCalc();
        smsFUcalc.fuSMSUpdate();
    }
}
