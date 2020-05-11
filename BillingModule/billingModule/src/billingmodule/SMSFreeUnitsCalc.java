package billingmodule;

import Database.databaseConnection;
import SystemObjects.*;
import java.util.Vector;
import Interfaces.NetConnection;
import static java.lang.Math.abs;

public class SMSFreeUnitsCalc {
        
    public Float fuSMSUpdate(UDR customerUDR, BillDateInterval intervalDate){//take DialA,serviceID, ProfileID
         
        
        databaseConnection db = new databaseConnection();
        FreeUnit fu = db.ProfileFU(new FreeUnit(customerUDR.getProfileID()));
        Vector<UDR> udrList= db.customerUDRs(new UDR(customerUDR.getDialA(),customerUDR.getProfileID()
                ,customerUDR.getServiceID()), intervalDate);
        CustomerProfile customerRemainedFUs;
        CustomerProfile cProfileupdateFU;
        ProfileService profileSMSDetails;
        Boolean state = false;
        
        Float updatedValue = 0f;
        Float consumedData = 0f;
        Float costOfService = 0f;
        Float TotalUDRsCost = 0f;
        
        for(UDR udr: udrList){
            System.out.println("####"+ udr.getDialA() + "#####" + udr.getDialB()+ 
                    "#####"+ udr.getOrderedDate() + "##########" + udr.getDurationMsgVolume());
        }
            
        if(udrList.isEmpty()){
            
             System.out.println("Customer Has no SMS Usage");
             
        }else{
            
            for(UDR udr:udrList){
                
                customerRemainedFUs= db.RemainedFreeUnits(new CustomerProfile(udr.getProfileID()
                        ,udr.getDialA()));
                
                profileSMSDetails = db.retrieveProfileService(new ProfileService(udr.getProfileID(),
                                            udr.getServiceID()));
                
                if(udr.getDialB().regionMatches(true,0,udr.getDialA(),0, 6)){
                    
                        if(customerRemainedFUs.getFUSMSOnNet() > 0){
                            
                            updatedValue = customerRemainedFUs.getFUSMSOnNet() - udr.getDurationMsgVolume();
                            System.out.println("updated SMS OnNet Value "+ updatedValue);
                            
                            if(updatedValue >= 0){
                                    
                                    cProfileupdateFU = new CustomerProfile(
                                                udr.getDialA(),udr.getProfileID(),udr.getServiceID(),updatedValue);                            
                                    state=db.UpdateCustomerFUs(cProfileupdateFU,NetConnection.onNet);
                                    costOfService = 0f;
                                    TotalUDRsCost += costOfService;
                                    System.out.println("Cost of SMS onNet service is zero");
//                                
                            }else{
                                    cProfileupdateFU = new CustomerProfile(
                                                udr.getDialA(),udr.getProfileID(),udr.getServiceID(),0f);                            
                                    state=db.UpdateCustomerFUs(cProfileupdateFU,NetConnection.onNet);
                                    consumedData = abs(updatedValue);                
                                    costOfService = (consumedData * profileSMSDetails.getFeeSameOperator())
                                            /(profileSMSDetails.getRoundAmount());                                   
                                    TotalUDRsCost += costOfService;                                   
                                    System.out.println("Cost Calcu for onNet SMS service:" + costOfService);                                   
                            } 
                        }else{
                            costOfService = udr.getCost();                           
                            TotalUDRsCost += costOfService;                           
                            System.out.println("onNet sms cost from Rating Module:" + costOfService);
                        }
                }else if(!(udr.getDialB().regionMatches(true,0,"0020",0, 4))){
                        
                        costOfService = (udr.getDurationMsgVolume() * profileSMSDetails.getFeeInternationally())
                                /(profileSMSDetails.getRoundAmount());                       
                        TotalUDRsCost += costOfService;                        
                        System.out.println("international sms cost from Rating Module:" + costOfService);
                        
                }else if(!(udr.getDialB().regionMatches(true,0,udr.getDialA(),0, 6))){
                          
                        if(customerRemainedFUs.getFUSMSCrossNet() > 0){
                            
                            updatedValue = customerRemainedFUs.getFUSMSCrossNet() - udr.getDurationMsgVolume();
                            System.out.println("updated SMS crossNet Value "+ updatedValue);
                            
                             if(updatedValue >= 0){
                                 
                                    cProfileupdateFU = new CustomerProfile(
                                                udr.getDialA(),udr.getProfileID(),udr.getServiceID(),updatedValue);
                                    state=db.UpdateCustomerFUs(cProfileupdateFU,NetConnection.crossNet); 
                                    costOfService = 0f;
                                    TotalUDRsCost += costOfService;
                                    System.out.println("cost of crossNet sms Service is zero");
//                                    
                            }else{
                                 
                                    cProfileupdateFU = new CustomerProfile(
                                                udr.getDialA(),udr.getProfileID(),udr.getServiceID(),0f);
                                    state=db.UpdateCustomerFUs(cProfileupdateFU,NetConnection.crossNet);
                                    consumedData = abs(updatedValue);        
                                    costOfService = (consumedData * profileSMSDetails.getFeeAnotherOperator())
                                            /(profileSMSDetails.getRoundAmount());
                                    TotalUDRsCost += costOfService;
                                    System.out.println("Cost Calcu for SMS crossNet:" + costOfService);                  
                            }
                        }else{

                            costOfService = udr.getCost();
                            TotalUDRsCost += costOfService;
                            System.out.println("croosNet sms cost from Rating Module:" + costOfService);
                        }  
                }           
            }       
        }
        System.out.println("Total consuption of SMS :"+ TotalUDRsCost);
        return TotalUDRsCost;
    }
    
//    public static void main(String [] args){
//        SMSFreeUnitsCalc smsFUcalc = new SMSFreeUnitsCalc();
//        smsFUcalc.fuSMSUpdate(new UDR("00201215860927",1,2));
//    }
}
