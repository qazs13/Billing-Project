package Database_Tables;

import java.util.Stack;
import java.util.Vector;

public class Services 
{
    int sid;
    String sname;
    Vector<Services> allServices;
 
    public Services() 
    {
        allServices = new Vector();
    }    
    
    public Services(int sid) 
    {
        this.sid = sid;
    }

    public Services(String sname) 
    {
        this.sname = sname;
    }

    public Services(int sid, String sname) 
    {
        this.sid = sid;
        this.sname = sname;
    }

    public void setSid(int sid) 
    {
        this.sid = sid;
    }

    public void setSname(String sname) 
    {
        this.sname = sname;
    }
    
    public void setAllServices(Vector<Services> allServices) 
    {
        this.allServices = allServices;
    }    

    public int getSid() 
    {
        return sid;
    }

    public String getSname() 
    {
        return sname;
    }   
    
    public Vector<Services> getAllServices() 
    {
        return allServices;
    }      
}
