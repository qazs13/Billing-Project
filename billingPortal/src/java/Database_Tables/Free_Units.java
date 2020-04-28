package Database_Tables;

import java.util.Vector;


public class Free_Units 
{
    int fid;
    float free_voice_same;
    float free_voice_diff;
    float free_sms_same;
    float free_sms_diff;
    float free_internet;
    int pid;
    Vector<Free_Units> allFreeUnits;
    
    public Free_Units() 
    {
        allFreeUnits = new Vector();
    }    

    public Free_Units(float free_voice_same, float free_voice_diff, float free_sms_same, float free_sms_diff, float free_internet, int pid)
    {
        this.free_voice_same = free_voice_same;
        this.free_voice_diff = free_voice_diff;
        this.free_sms_same = free_sms_same;
        this.free_sms_diff = free_sms_diff;
        this.free_internet = free_internet;
        this.pid = pid;
    }

    public Free_Units(int fid, float free_voice_same, float free_voice_diff, float free_sms_same, float free_sms_diff, float free_internet, int pid) 
    {
        this.fid = fid;
        this.free_voice_same = free_voice_same;
        this.free_voice_diff = free_voice_diff;
        this.free_sms_same = free_sms_same;
        this.free_sms_diff = free_sms_diff;
        this.free_internet = free_internet;
        this.pid = pid;
    }

    public void setFid(int fid) 
    {
        this.fid = fid;
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

    public void setPid(int pid) 
    {
        this.pid = pid;
    }

    public void setAllFreeUnits(Vector<Free_Units> allFreeUnits) 
    {
        this.allFreeUnits = allFreeUnits;
    }

    public int getFid() 
    {
        return fid;
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

    public int getPid() 
    {
        return pid;
    }

    public Vector<Free_Units> getAllFreeUnits() 
    {
        return allFreeUnits;
    }
}
