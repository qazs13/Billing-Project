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
    Vector<UDR> udrList= db.customerUDRs(new UDR("+201215860927",1,3));
    ProfileService profileSMSDetails;
    NetConnection conn;
    Boolean state;

    
    
    public void fuSMSUpdate(){//take DialA,serviceID, ProfileID
         
        int updatedValue = 0;
        int consumedData = 0;
        Float costOfService = 0f;
        Float TotalUDRsCost = 0f;
        
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
                
                if(udr.getDialB().regionMatches(true,0,udr.getDialA(),0, 5)){
                    
                        if(customerRemainedFUs.getFUSMSOnNet() > 0){
                            
                            updatedValue = customerRemainedFUs.getFUSMSOnNet() - udr.getDurationMsgVolume();
                            System.out.println("updated Voice On Net Value "+ updatedValue);
                            
                            if(updatedValue >= 0){
                                
                                    customerRemainedFUs.setConsumedQuantity(updatedValue);
                                    state=db.UpdateCustomerFUs(customerRemainedFUs,conn.onNet);
                                    costOfService = 0f;
                                    TotalUDRsCost += costOfService;
                                    System.out.println("Cost of this service is zero");
                                
                            }else{
                                    customerRemainedFUs.setConsumedQuantity(0);
                                    state=db.UpdateCustomerFUs(customerRemainedFUs,conn.onNet);
                                    consumedData = abs(updatedValue);
                                    
                                    costOfService = (consumedData * profileSMSDetails.getFeeSameOperator())
                                            /(profileSMSDetails.getRoundAmount());
                                    
                                    TotalUDRsCost += costOfService;
                                    
                                    System.out.println("Cost Calcu :" + costOfService);
                                    
                            }
                            
                        }else{
                            //has no fu ONNET
                            //call function calculate consumption from cost
                            costOfService = udr.getCost();
                            
                            TotalUDRsCost += costOfService;
                            
                            System.out.println("onNet sms cost from Rating Module:" + costOfService);
                        }
                }else if(!(udr.getDialB().regionMatches(true,0,"+20",0, 3))){
                        
                        costOfService = (udr.getDurationMsgVolume() * profileSMSDetails.getFeeInternationally())
                                /(profileSMSDetails.getRoundAmount());
                        
                        TotalUDRsCost += costOfService;
                        
                        System.out.println("international sms cost from Rating Module:" + costOfService);
                        
                }else if(!(udr.getDialB().regionMatches(true,0,udr.getDialA(),0, 5))){
                    
                    
                        if(customerRemainedFUs.getFUSMSCrossNet() > 0){
                            
                            updatedValue = customerRemainedFUs.getFUSMSCrossNet() - udr.getDurationMsgVolume();
                            System.out.println("updated Voice On Net Value "+ updatedValue);
                            
                             if(updatedValue >= 0){
                                 
                                    customerRemainedFUs.setConsumedQuantity(updatedValue);
                                    state=db.UpdateCustomerFUs(customerRemainedFUs,conn.crossNet); 
                                    costOfService = 0f;
                                    TotalUDRsCost += costOfService;
                                    System.out.println("cost of crossNet sms Service is zero");
                                    
                            }else{
                                 
                                    customerRemainedFUs.setConsumedQuantity(0);
                                    state=db.UpdateCustomerFUs(customerRemainedFUs,conn.crossNet);
                                    //call function(give it cost valuein udr table )
                                    consumedData = abs(updatedValue);
                                    
                                    costOfService = (consumedData * profileSMSDetails.getFeeAnotherOperator())
                                            /(profileSMSDetails.getRoundAmount());
                                    TotalUDRsCost += costOfService;
                                    System.out.println("Cost Calcu :" + costOfService);                  
                            }
                        }else{
                            //has No fu Cross Net
                            //call function calculate consumption from cost
                            costOfService = udr.getCost();
                            TotalUDRsCost += costOfService;
                            System.out.println("croosNet sms cost from Rating Module:" + costOfService);
                        }  
                }           
            }       
        }
        System.out.println("Total consuption of SMS :"+ TotalUDRsCost);
    }
    
    public static void main(String [] args){
        SMSFreeUnitsCalc smsFUcalc = new SMSFreeUnitsCalc();
        smsFUcalc.fuSMSUpdate();
    }
}
