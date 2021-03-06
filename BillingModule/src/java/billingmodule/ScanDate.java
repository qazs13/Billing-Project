package billingmodule;

import java.util.Scanner;
import SystemObjects.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ScanDate
{

    public static void main(String[] args) throws SQLException
    {
        try
        {
            String pathtopdf = new File("src").getCanonicalPath()+ File.separatorChar + "java" + File.separatorChar + "allPDFs";
            System.out.println(pathtopdf);
            File pdfdir = new File(pathtopdf);
            if (!pdfdir.exists()) 
            {
                pdfdir.mkdir();
            }            
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        
        Scanner scanner = new Scanner(System.in);
        System.out.printf("TO GET Invoice Sheet for specific customer, please enter 'one' \n");
        System.out.printf("TO GET Invoice Sheet for all customer, please enter 'all' :");
        String request = scanner.nextLine();

        System.out.printf("Enter Bill Start Date: ");
        String startDate = scanner.nextLine();  // Read user input

        System.out.printf("Enter Bill End Date: ");
        String endDate = scanner.nextLine();  // Read user input

        BillDateInterval billObject = new BillDateInterval(startDate, endDate);

        RequestedOperation requestSend = new RequestedOperation();
        requestSend.requestHandlingFromStartMethod(request, billObject);
    }
}
