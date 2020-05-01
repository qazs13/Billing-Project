package billingmodule;

import Database.databaseConnection;
import SystemObjects.*;
import java.util.Vector;
import Interfaces.NetConnection;
import static java.lang.Math.abs;


public class DataFreeUnitsCalc {
    
    databaseConnection db = new databaseConnection();
    FreeUnit fu = db.ProfileFU(new FreeUnit(1));
    CustomerProfile customerRemainedFUs;
    Vector<UDR> udrList= db.customerUDRs(new UDR("+201215860927",1,4));
    ProfileService profileDataDetails;
    NetConnection conn;
    Boolean state;
    
    
    public void fuInternetUpdate(){
            
        int updatedValue = 0;
        int consumedData = 0;
        Float costOfService = 0f;
        Float TotalUDRsCost = 0f;
        
//        for(UDR udr: udrList){
//            System.out.println("####"+ udr.getDialA() + "#####" + udr.getDialB());
//        }

        if(udrList.isEmpty()){
            System.out.println("Customer Has no Internet Usage");     
        }
        else{
            for(UDR udr:udrList){
                customerRemainedFUs= db.RemainedFreeUnits(new CustomerProfile(1,"01215860927"));
                customerRemainedFUs.setServiceID(4);
                
                if(udr.getExternalCharges() == 0){
                    
                    if(customerRemainedFUs.getFUInternet() > 0){
                                
                                System.out.println("3ayzaaaa 23rf Quantity be kam !" + udr.getDurationMsgVolume());
                                updatedValue = customerRemainedFUs.getFUInternet() - udr.getDurationMsgVolume();
                                System.out.println("updated Voice On Net Value "+ updatedValue);

                                if(updatedValue >= 0){
                                            customerRemainedFUs.setConsumedQuantity(updatedValue);
                                            state=db.UpdateCustomerFUs(customerRemainedFUs,"nothing");
                                            System.out.println("=========="+ state);
                                            costOfService = 0f;
                                            TotalUDRsCost += costOfService;
                                            System.out.println("Cost of this service is zero");
                                }else{
                                      customerRemainedFUs.setConsumedQuantity(0);
                                      state=db.UpdateCustomerFUs(customerRemainedFUs,"nothing");
                                      consumedData = abs(updatedValue);
                                      
                                      profileDataDetails = db.retrieveProfileService(new ProfileService(udr.getProfileID()
                                                ,udr.getServiceID()));
                                      
                                      costOfService = (consumedData * profileDataDetails.getFeeSameOperator())
                                              /(profileDataDetails.getRoundAmount());
                                      
                                      TotalUDRsCost += costOfService;
                                      
                                      System.out.println("Cost Calcu :" + costOfService);
                                      //call function(give it cost valuein udr table )
                                }    
                    }else{
                            //external_charges or cost column calculation
                            costOfService = udr.getCost();
                            TotalUDRsCost += costOfService;
                            System.out.println("cost of service from Rating module" + costOfService);
                    }            
                }else{
                    //take External Charges as it to bill sheet
                    costOfService = udr.getExternalCharges();
                    TotalUDRsCost += costOfService;
                    System.out.println("Cost Calcu :" + costOfService);     
                }  
            }
        } 
        
        System.out.println("Total consumption od Data :"+ TotalUDRsCost); 
    }
        
    public static void main(String [] args){
        DataFreeUnitsCalc dataFUcalc = new DataFreeUnitsCalc();
        dataFUcalc.fuInternetUpdate();
        
    }
    
}
