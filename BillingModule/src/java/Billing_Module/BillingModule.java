package Billing_Module;

import Interfaces.CustomerNum;
import SystemObjects.BillDateInterval;
import billingmodule.RequestedOperation;
import java.io.File;
import javax.ws.rs.*;


@Path("billing")
public class BillingModule 
{
    @GET
    @Path("/onePerson")
    public void startBillingForOneCustomer (@QueryParam("msisdn") String phoneNumber, @QueryParam("startDate") String start, @QueryParam("endDate") String end)
    {
        String msisdn = "002012"+phoneNumber;
        String startDate = start.replace("-", "");
        String endDate = end.replace("-", "");
        System.out.println(msisdn+" "+startDate+" "+endDate);
        
        createIfNotExisit ();
        try
        {
            BillDateInterval billObject = new BillDateInterval(startDate, endDate);
            RequestedOperation requestSend = new RequestedOperation();
            requestSend.requestHandlingFromWebService(CustomerNum.oneCustomer, msisdn, billObject);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    @GET
    @Path("/AllPersons")
    public void stratBillingForAllCustomers (@QueryParam("startDate") String start, @QueryParam("endDate") String end)
    {
        String startDate = start.replace("-", "");
        String endDate = end.replace("-", "");        
        System.out.println(startDate+" "+endDate);
        createIfNotExisit ();
        try
        {
            BillDateInterval billObject = new BillDateInterval(startDate, endDate);
            RequestedOperation requestSend = new RequestedOperation();
            requestSend.requestHandlingFromWebService(CustomerNum.allCustomers, null, billObject);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }        
    }
    
    public void createIfNotExisit ()
    {
        String pathtopdf = "C:"+File.separatorChar+"Users"+File.separatorChar+"amrws"+File.separatorChar+"Desktop" +File.separatorChar +"BillingModule"+File.separatorChar+"src"
                +File.separatorChar+"java"+File.separatorChar+"allPDFs";        
        System.out.println(pathtopdf);
        File pdfdir = new File(pathtopdf);
        if (!pdfdir.exists()) 
        {
            pdfdir.mkdir();
        }
    }    
}