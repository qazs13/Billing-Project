package billingmodule;

import Database.databaseConnection;
import SystemObjects.UDR;
import java.util.Vector;
import SystemObjects.*;
import Interfaces.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BillingModule {

  
    
    public void InvoiceGeneration(BillDateInterval intervalDate) throws SQLException{
        databaseConnection db = new databaseConnection();
        Vector<UDR> allCustomersInUDRTable = db.retrieveAllCustomersHaveUDRs();
        FreeUnitsCalc retrieveCustomerObjectTotalFUCalcuation = new FreeUnitsCalc();
        OneTime_Recurring_Calc feesObject = new OneTime_Recurring_Calc();
        Profile customerProfile;
        CustomerProfile cProfile;      
        OCC oneTimeRecurringFees;
        InvoiceSheet customerInvoiceObject;
        Customer retrieveCustomerData;
        Boolean hasBilledThisMonth = false;
        Float profileRecurringFees = 0f;
        Float totalInvoiceBeforeTaxs = 0f;
        Float totalInvoiceAfterTaxs = 0f;
        
        for(UDR customer:allCustomersInUDRTable){
            
                    //Current Date Generation 
                    long millis=System.currentTimeMillis();  
                    Date date=new Date(millis);  
                    System.out.println(date);

                    
                    hasBilledThisMonth = db.checkIfCustomerBilledBefore(customer.getDialA(),date);
                    
                    if(hasBilledThisMonth){
                        
                            //get ProfileTotalFees
                            customerProfile = db.retieveProfileDetails(new Profile(customer.getProfileID()));
                            profileRecurringFees = customerProfile.getProfileFees();

                            //get Return of FreeUnitCalculation ------Vector------>ServiceName:Cost
                            cProfile = retrieveCustomerObjectTotalFUCalcuation.CustomerServicesCalculations(new 
                                    UDR(customer.getDialA(), customer.getProfileID()),intervalDate);                
                            System.out.println("Billing Module Test ####" + cProfile.getTotalDataServiceCost());


                            //return from class Recuring services/one-Time(may be +ve or -ve) services
                            oneTimeRecurringFees = feesObject.getOneTimeRecurringFee(new UDR(customer.getDialA(),
                                    customer.getProfileID()), intervalDate);
                            System.out.println("Billing Module Test (recuring)#########" + 
                                    oneTimeRecurringFees.getTotalRecurringFees());

                            /////////////Total Invoice Before Taxs////////////////////////
                            totalInvoiceBeforeTaxs = profileRecurringFees + cProfile.getTotalVoiceServiceCost() +
                                    cProfile.getTotalSMSServiceCost() + cProfile.getTotalDataServiceCost() +
                                    oneTimeRecurringFees.getTotalOneTimeFees() + 
                                    oneTimeRecurringFees.getTotalRecurringFees();         
                            System.out.println("#####Toooooooooooooootal ###" + totalInvoiceBeforeTaxs);

                            /////////////Total Invoice After Taxs////////////////////////
                            //Taxs hardcodes
                            totalInvoiceAfterTaxs = totalInvoiceBeforeTaxs * 1.1f;                  
                            System.out.println("#####Toooooooooooooootal ###" + totalInvoiceAfterTaxs);

                            //insert or update in bill Table
                            retrieveCustomerData = db.retrieveCustomerInfo(new Customer(customer.getDialA()));

                            StringBuilder customerName = new StringBuilder();
                            customerName.append(retrieveCustomerData.getF_name());
                            customerName.append(" ");
                            customerName.append(retrieveCustomerData.getL_name());
                            String cName = customerName.toString();
                            System.out.println("####" + customerName );


                            //create InvoiceSheetObject
                            customerInvoiceObject = new InvoiceSheet(cName,customer.getDialA(),retrieveCustomerData.getAddress(),
                                    customerProfile.getProfileName(),profileRecurringFees,oneTimeRecurringFees.getTotalOneTimeFees(), 
                                    oneTimeRecurringFees.getTotalRecurringFees(), cProfile.getTotalVoiceServiceCost(), 
                                    cProfile.getTotalSMSServiceCost(), cProfile.getTotalDataServiceCost(),
                                    totalInvoiceBeforeTaxs, totalInvoiceAfterTaxs, date);

                            Boolean state = db.insertCustomerbillData(customerInvoiceObject);

                            if(state){
                                //---> move to pdf generation carring "InvoiceSheetObject"
                                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + state + "@@@@@@@@@@@@@@@@@@");
                            }
                            else{
                                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@" + state + "@@@@@@@@@@@@@@@@@@@@");
                            }

                    
                    }else{
                     System.out.println("This customer has been already billed ");
                    }
                          
        }
    }
    
//    public static void main(String [] args){
//        BillingModule module = new BillingModule();
//        
//        try {
//            module.InvoiceGeneration(new BillDateInterval("20200401", "20200430"));
//        } catch (SQLException ex) {
//            Logger.getLogger(BillingModule.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//    }
    

    
}
