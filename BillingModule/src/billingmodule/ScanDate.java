package billingmodule;

import java.util.Scanner;
import SystemObjects.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ScanDate {
    
    public static void main(String [] args) throws SQLException{
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.printf("TO GET Invoice Sheet for specific customer, please enter 'one' \n");
        System.out.printf("TO GET Invoice Sheet for all customer, please enter 'all' :");
        String request = scanner.nextLine();
        
 
        System.out.printf("Enter Bill Start Date: ");
        String startDate = scanner.nextLine();  // Read user input

        System.out.printf("Enter Bill End Date: ");
        String endDate = scanner.nextLine();  // Read user input
        
        BillDateInterval billObject = new BillDateInterval(startDate,endDate);
        
        RequestedOperation requestSend = new RequestedOperation();
        requestSend.requestHandling(request, billObject);
     
    } 
}
