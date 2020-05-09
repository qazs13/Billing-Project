package Database_Tables;

public class Customer_Profile 
{
    String msisdn;
    int pid;
    String start_date;
    String end_date;
    int blocked_services;
    float free_voice_same;
    float free_voice_diff;
    float free_sms_same;
    float free_sms_diff;
    float free_internet;

    public Customer_Profile(String msisdn, int pid, String start_date, String end_date, int blocked_services, float free_voice_same, float free_voice_diff, float free_sms_same, float free_sms_diff, float free_internet) 
    {
        this.msisdn = msisdn;
        this.pid = pid;
        this.start_date = start_date;
        this.end_date = end_date;
        this.blocked_services = blocked_services;
        this.free_voice_same = free_voice_same;
        this.free_voice_diff = free_voice_diff;
        this.free_sms_same = free_sms_same;
        this.free_sms_diff = free_sms_diff;
        this.free_internet = free_internet;
    }

    public Customer_Profile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setMsisdn(String msisdn) 
    {
        this.msisdn = msisdn;
    }

    public void setPid(int pid) 
    {
        this.pid = pid;
    }

    public void setStart_date(String start_date) 
    {
        this.start_date = start_date;
    }

    public void setEnd_date(String end_date) 
    {
        this.end_date = end_date;
    }

    public void setBlocked_services(int blocked_services) 
    {
        this.blocked_services = blocked_services;
    }

    public void setFree_voice_same(float free_voice_same) 
    {
        this.free_voice_same = free_voice_same;
    }

    public void setFree_voice_diff(float free_voice_diff) 
    {
        this.free_voice_diff = free_voice_diff;
    }

    public void setFree_sms_same(float free_sms_same) 
    {
        this.free_sms_same = free_sms_same;
    }

    public void setFree_sms_diff(float free_sms_diff) 
    {
        this.free_sms_diff = free_sms_diff;
    }

    public void setFree_internet(float free_internet) 
    {
        this.free_internet = free_internet;
    }

    public String getMsisdn() 
    {
        return msisdn;
    }

    public int getPid() 
    {
        return pid;
    }

    public String getStart_date() 
    {
        return start_date;
    }

    public String getEnd_date() 
    {
        return end_date;
    }

    public int getBlocked_services() 
    {
        return blocked_services;
    }

    public float getFree_voice_same() 
    {
        return free_voice_same;
    }

    public float getFree_voice_diff() 
    {
        return free_voice_diff;
    }

    public float getFree_sms_same() 
    {
        return free_sms_same;
    }

    public float getFree_sms_diff() 
    {
        return free_sms_diff;
    }

    public float getFree_internet() 
    {
        return free_internet;
    }   
}
