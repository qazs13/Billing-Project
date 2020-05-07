package Database_Tables;

import java.util.Vector;

public class One_Time_Service
{
    int one_time_service_id;
    String osname;
    float osfee;
    Vector<One_Time_Service> allOneTimeServices;
    
    public One_Time_Service() 
    {
        allOneTimeServices = new Vector();
    }    
    
    public One_Time_Service(int one_time_service_id) 
    {
        this.one_time_service_id = one_time_service_id;
    }

    public One_Time_Service(String osname) 
    {
        this.osname = osname;
    }
    
    public One_Time_Service(String osname, float osfee) 
    {
        this.osname = osname;
        this.osfee = osfee;
    }
    
    public One_Time_Service(int one_time_service_id, String osname, float osfee) 
    {
        this.one_time_service_id = one_time_service_id;
        this.osname = osname;
        this.osfee = osfee;
    }

    public void setOne_time_service_id(int one_time_service_id) 
    {
        this.one_time_service_id = one_time_service_id;
    }

    public void setOsname(String osname) 
    {
        this.osname = osname;
    }

    public void setOsfee(float osfee) 
    {
        this.osfee = osfee;
    }

    public void setAllOneTimeServices(Vector<One_Time_Service> allOneTimeServices) 
    {
        this.allOneTimeServices = allOneTimeServices;
    }

    public int getOne_time_service_id() 
    {
        return one_time_service_id;
    }

    public String getOsname() 
    {
        return osname;
    }

    public float getOsfee() 
    {
        return osfee;
    }

    public Vector<One_Time_Service> getAllOneTimeServices() 
    {
        return allOneTimeServices;
    }
}