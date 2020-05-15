 package billingmodule;

import Database.databaseConnection;
import SystemObjects.UDR;
import SystemObjects.*;
import java.sql.Date;
import java.sql.SQLException;
import pdfusingitext.PdfUsingItext;


public class BillingModule 
{
  
    public void InvoiceGeneration(UDR customer, BillDateInterval intervalDate) throws SQLException{
        
        databaseConnection db = new databaseConnection();
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
        
         
        //Current Date Generation 
        long millis=System.currentTimeMillis();  
        Date date=new Date(millis);  
        System.out.println(date);


        hasBilledThisMonth = db.checkIfCustomerBilledBefore(customer.getDialA(),intervalDate);

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

                String cName = retrieveCustomerData.getF_name() + " " + retrieveCustomerData.getL_name();
 
                //create InvoiceSheetObject
                customerInvoiceObject = new InvoiceSheet(0,cName,customer.getDialA(),retrieveCustomerData.getAddress(),
                        customerProfile.getProfileName(),profileRecurringFees,oneTimeRecurringFees.getTotalOneTimeFees(), 
                        oneTimeRecurringFees.getTotalRecurringFees(), cProfile.getTotalVoiceServiceCost(), 
                        cProfile.getTotalSMSServiceCost(), cProfile.getTotalDataServiceCost(),
                        totalInvoiceBeforeTaxs,totalInvoiceAfterTaxs,intervalDate.getStartDate(),
                        intervalDate.getEndDate(), date);
                
                Boolean state = db.insertCustomerbillData(customerInvoiceObject);
                
                customerInvoiceObject.setBillId(db.getBillID(customer.getDialA(), date));
                

                if(state){
                    //---> move to pdf generation carring "InvoiceSheetObject"
                    
                     PdfUsingItext itext = new PdfUsingItext();
                     itext.start(customerInvoiceObject);
                     
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + state + "@@@@@@@@@@@@@@@@@@");
                    //reNew customerProfile
                    RenewCustomerProfile cust = new RenewCustomerProfile();
                    Boolean updated = cust.renewProfile(customer);
                    System.out.println("999999999999999999999999999999999999" + updated);

                }
                else{
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@" + state + "@@@@@@@@@@@@@@@@@@@@");
                }


        }else{
         System.out.println("This customer has been already billed ");
        }
    }    
}
