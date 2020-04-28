package billingmodule;

import Database.databaseConnection;
import SystemObjects.*;
import java.util.Vector;
import Interfaces.NetConnection;


public class DataFreeUnitsCalc {
    
    databaseConnection db = new databaseConnection();
    FreeUnit fu = db.ProfileFU(new FreeUnit(1));
    CustomerProfile customerRemainedFUs= db.RemainedFreeUnits(new CustomerProfile(1,"01215860927"));
    Vector<UDR> udrList= db.customerUDRs(new UDR("01215860927",1,4));
    NetConnection conn;
    Boolean state;
    
    public void fuInternetUpdate(){
            
        int updatedValue =0;

        if(udrList.isEmpty()){
            System.out.println("Customer Has no Internet Usage");     
        }
        else{
            for(UDR udr:udrList){
                if(customerRemainedFUs.getFUInternet() > 0){
                    
                        updatedValue = customerRemainedFUs.getFUInternet() - udr.getDurationMsgVolume();
                        System.out.println("updated Voice On Net Value "+ updatedValue);
                          
                        if(updatedValue >= 0){
                                    customerRemainedFUs.setConsumedQuantity(updatedValue);
                                    customerRemainedFUs.setServiceID(4);
                                    state=db.UpdateCustomerFUs(customerRemainedFUs,"nothing");
                                    System.out.println("=========="+ state);
                        }else{
                              customerRemainedFUs.setFUInternet(0);
                              //call update function to update customer_profile table
                              //call function(give it cost valuein udr table )
                        }    
                        
                }else{
                        //external_charges or cost column calculation
                }   
            }
        }   
    }
        
    public static void main(String [] args){
        DataFreeUnitsCalc dataFUcalc = new DataFreeUnitsCalc();
        dataFUcalc.fuInternetUpdate();
    }
    
}
