package Database_Tables;

import java.sql.Date;
import java.util.Vector;

public class OCC 
{
    int occ_id;
    String msisdn;
    int one_recurring_id;
    String type_of_service;
    boolean service_processed;
    Date service_processed_date;
    Vector<OCC> allOCCs;

    public OCC ()
    {
        allOCCs = new Vector();
    }
       
    public OCC (int occ_id)
    {
        this.occ_id = occ_id;
    }
    
    public OCC (String msisdn)
    {
        this.msisdn = msisdn;
    }   
    
    public OCC (String msisdn, int one_recurring_id, String type_of_service, Date service_processed_date)
    {
        this.msisdn = msisdn;
        this.one_recurring_id = one_recurring_id;
        this.type_of_service = type_of_service;        
        this.service_processed_date = service_processed_date;        
    }        

    public OCC(String msisdn, int one_recurring_id, String type_of_service, boolean service_processed, Date service_processed_date) 
    {
        this.msisdn = msisdn;
        this.one_recurring_id = one_recurring_id;
        this.type_of_service = type_of_service;
        this.service_processed = service_processed;
        this.service_processed_date = service_processed_date;
    }
    
    
    public OCC(int occ_id, String msisdn, int one_recurring_id, String type_of_service, boolean service_processed, Date service_processed_date) 
    {
        this.occ_id = occ_id;
        this.msisdn = msisdn;
        this.one_recurring_id = one_recurring_id;
        this.type_of_service = type_of_service;
        this.service_processed = service_processed;
        this.service_processed_date = service_processed_date;
    }

    public void setOcc_id(int occ_id) 
    {
        this.occ_id = occ_id;
    }

    public void setMsisdn(String msisdn) 
    {
        this.msisdn = msisdn;
    }

    public void setOne_recurring_id(int one_recurring_id) 
    {
        this.one_recurring_id = one_recurring_id;
    }

    public void setType_of_service(String type_of_service) 
    {
        this.type_of_service = type_of_service;
    }

    public void setService_processed(boolean service_processed) 
    {
        this.service_processed = service_processed;
    }

    public void setService_processed_date(Date service_processed_date) 
    {
        this.service_processed_date = service_processed_date;
    }

    public void setAllOCCs(Vector<OCC> allOCCs) 
    {
        this.allOCCs = allOCCs;
    }

    public int getOcc_id() 
    {
        return occ_id;
    }

    public String getMsisdn() 
    {
        return msisdn;
    }

    public int getOne_recurring_id() 
    {
        return one_recurring_id;
    }

    public String getType_of_service() 
    {
        return type_of_service;
    }

    public boolean getService_processed() 
    {
        return service_processed;
    }

    public Date getService_processed_date() 
    {
        return service_processed_date;
    }

    public Vector<OCC> getAllOCCs() 
    {
        return allOCCs;
    }
}