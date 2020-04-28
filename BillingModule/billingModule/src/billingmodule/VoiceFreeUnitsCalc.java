package billingmodule;

import Database.databaseConnection;
import Interfaces.NetConnection;
import SystemObjects.*;
import java.util.Vector;


public class VoiceFreeUnitsCalc {
    
    databaseConnection db = new databaseConnection();
    FreeUnit fu = db.ProfileFU(new FreeUnit(1));
    CustomerProfile customerRemainedFUs= db.RemainedFreeUnits(new CustomerProfile(1,"01215860927"));
    Vector<UDR> udrList= db.customerUDRs(new UDR("01215860927",1,2));
    NetConnection conn;
    Boolean state;
    
    public void fuVoiceUpdate(){
        
        int updatedValue =0;

        if(udrList.isEmpty()){
                    System.out.println("Customer Has no SMS Usage");
        }else{
            
            for(UDR udr:udrList){
                    customerRemainedFUs.setServiceID(2);
                    
                    if(udr.getDialB().regionMatches(true,0,udr.getDialA(),0, 3)){
                        
                        if(customerRemainedFUs.getFUVoiceOnNet() > 0){
                            
                            updatedValue = customerRemainedFUs.getFUVoiceOnNet() - udr.getDurationMsgVolume();
                            System.out.println("updated Voice On Net Value "+ updatedValue);
                            
                            if(updatedValue >= 0){
                                    
                                    customerRemainedFUs.setConsumedQuantity(updatedValue);
                                    state=db.UpdateCustomerFUs(customerRemainedFUs,conn.onNet);
                            }else{
                                
                                    customerRemainedFUs.setConsumedQuantity(0);
                                    state=db.UpdateCustomerFUs(customerRemainedFUs,conn.onNet);
                                    //call function(give it cost valuein udr table )
                                
                            }
                        }
                        else{
                            //has no fu ONNET
                            //call function calculate consumption from cost
                        }
                    }
                    else if(!(udr.getDialB().regionMatches(true,0,udr.getDialA(),0, 3))){
                        if(customerRemainedFUs.getFUVoiceCrossNet() > 0){
                            
                            updatedValue = customerRemainedFUs.getFUVoiceCrossNet() - udr.getDurationMsgVolume();
                            System.out.println("updated Voice On Net Value "+ updatedValue);
                            
                            if(updatedValue >= 0){
                                
                                    customerRemainedFUs.setConsumedQuantity(updatedValue);
                                    state=db.UpdateCustomerFUs(customerRemainedFUs,conn.crossNet);
                                
                            }else{
                                    
                                    customerRemainedFUs.setConsumedQuantity(0);
                                    state=db.UpdateCustomerFUs(customerRemainedFUs,conn.crossNet);
                                    //call function(give it cost valuein udr table )
                                
                            }
                        }else{
                             //has No fu Cross Net
                            //call function calculate consumption from cost
                        }
                    }               
            }     
        }       
    }
    
    public static void main(String [] args){
        VoiceFreeUnitsCalc voiceFUcalc = new VoiceFreeUnitsCalc();
        voiceFUcalc.fuVoiceUpdate();
    }
    
}
