package Database_Tables;

import java.util.Vector;


public class Profile_Services 
{
    int profile_services_id;
    int pid;
    int sid;
    int round_Amount;
    float fees_local_same;
    float fees_local_diff;
    float fees_international;
    Vector<Profile_Services> allProfileServices;
    
    public Profile_Services()
    {
        allProfileServices = new Vector();
    }
    
    public Profile_Services(int round_amount, float fees_local_same, float fees_local_diff, float fees_international) 
    {
        this.round_Amount = round_amount;
        this.fees_local_same = fees_local_same;
        this.fees_local_diff = fees_local_diff;
        this.fees_international = fees_international;
    }        
    
    public Profile_Services(int sid, int round_amount, float fees_local_same, float fees_local_diff, float fees_international) 
    {
        this.sid = sid;
        this.round_Amount = round_amount;
        this.fees_local_same = fees_local_same;
        this.fees_local_diff = fees_local_diff;
        this.fees_international = fees_international;
    }    

    public Profile_Services(int pid, int sid, int round_amount, float fees_local_same, float fees_local_diff, float fees_international) 
    {
        this.pid = pid;
        this.sid = sid;
        this.round_Amount = round_amount;
        this.fees_local_same = fees_local_same;
        this.fees_local_diff = fees_local_diff;
        this.fees_international = fees_international;
    }

    public Profile_Services(int profile_services_id, int pid, int sid, int round_amount, float fees_local_same, float fees_local_diff, float fees_international) 
    {
        this.profile_services_id = profile_services_id;
        this.pid = pid;
        this.sid = sid;
        this.round_Amount = round_amount;
        this.fees_local_same = fees_local_same;
        this.fees_local_diff = fees_local_diff;
        this.fees_international = fees_international;
    }
    
    public void setprofile_services_id(int profile_services_id) 
    {
        this.profile_services_id = profile_services_id;
    }
    
    public void setPid(int pid) 
    {
        this.pid = pid;
    }

    public void setSid(int sid) 
    {
        this.sid = sid;
    }

    public void setRound_amount(int round_amount) 
    {
        this.round_Amount = round_amount;
    }

    public void setFees_local_same(float fees_local_same)
    {
        this.fees_local_same = fees_local_same;
    }

    public void setFees_local_diff(float fees_local_diff)
    {
        this.fees_local_diff = fees_local_diff;
    }

    public void setFees_international(float fees_international) 
    {
        this.fees_international = fees_international;
    }

    public void setAllProfileServices(Vector<Profile_Services> allProfileServices) 
    {
        this.allProfileServices = allProfileServices;
    }

    public int getprofile_services_id() 
    {
        return profile_services_id;
    }    
    
    public int getPid() 
    {
        return pid;
    }

    public int getSid() 
    {
        return sid;
    }

    public int getRound_amount() 
    {
        return round_Amount;
    }

    public float getFees_local_same()
    {
        return fees_local_same;
    }

    public float getFees_local_diff()
    {
        return fees_local_diff;
    }

    public float getFees_international() 
    {
        return fees_international;
    }

    public Vector<Profile_Services> getAllProfileServices() 
    {
        return allProfileServices;
    }
}
