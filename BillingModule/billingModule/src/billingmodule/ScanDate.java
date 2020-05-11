package billingmodule;

import java.util.Scanner;
import SystemObjects.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ScanDate {
    
    public static void main(String [] args){
        
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Enter Bill Start Date: ");
        String startDate = scanner.nextLine();  // Read user input

        System.out.printf("Enter Bill End Date: ");
        String endDate = scanner.nextLine();  // Read user input

        BillDateInterval billObject = new BillDateInterval(startDate,endDate);
        BillingModule billingModule = new BillingModule();
        
        try {
            billingModule.InvoiceGeneration(billObject);
        } catch (SQLException ex) {
            Logger.getLogger(ScanDate.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    } 
}
