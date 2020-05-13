package billingmodule;

import Interfaces.CustomerNum;
import java.util.Vector;
import Database.*;
import SystemObjects.*;
import java.sql.SQLException;
import java.util.Scanner;


public class RequestedOperation {
    
    public void requestHandling(String request, BillDateInterval intervalBill) throws SQLException{
        
        BillingModule module = new BillingModule();
        databaseConnection db = new databaseConnection();
        Scanner scanner = new Scanner(System.in);
        
        if(request.equals(CustomerNum.oneCustomer)){
            //one customer
            
            System.out.printf("Please,enter Customer's DialNum: ");
            String dialNum = scanner.nextLine();  // Read user input
            System.out.println("dialNum" + dialNum);
            
            CustomerProfile cust = db.retrieveCustomerProfile(new CustomerProfile(dialNum));
            System.out.println("tb hna shayf eh " + cust.getMSISDN() + " " + cust.getProfileID());
            UDR custUDR = new UDR(cust.getMSISDN(), cust.getProfileID());
            System.out.println("shayf eh " + custUDR.getDialA() + " " + custUDR.getProfileID());
            module.InvoiceGeneration(custUDR,intervalBill); 
            
        }else if(request.equalsIgnoreCase(CustomerNum.allCustomers)){
            //all customers
            
            Vector<UDR> allCustomersInUDRTable = db.retrieveAllCustomersHaveUDRs();
            
            for(UDR customer:allCustomersInUDRTable)
            {
                
                module.InvoiceGeneration(customer,intervalBill);
            }
        }
        else
        {
            //wrong request  
            System.out.println("Wrong request !!!!!, please try again");
        }
        
    }
    
}
