package Database_Tables;

import java.util.Vector;


public class Profile 
{
    int pID;
    String pName;
    int renew_Duration;
    float pFees;
    Vector<Profile> allProfiles;

    public Profile()
    {
        allProfiles = new Vector();
    }
    
    public Profile(String pName) 
    {
        this.pName = pName;
    }

    public Profile(int pID, String pName) 
    {
        this.pID = pID;
        this.pName = pName;
    }
    
    public Profile(String pName, int renew_Duration, float pFees) 
    {
        this.pName = pName;
        this.renew_Duration = renew_Duration;
        this.pFees = pFees;
    }        
    
    public Profile(int pID, String pName, int renew_Duration, float pFees) 
    {
        this.pID = pID;
        this.pName = pName;
        this.renew_Duration = renew_Duration;
        this.pFees = pFees;
    }    
    
    public void setpID(int pID)
    {
        this.pID = pID;
    }

    public void setpName(String pName) 
    {
        this.pName = pName;
    }

    public void setRenew_Duration(int renew_Duration)
    {
        this.renew_Duration = renew_Duration;
    }

    public void setpFees(float pFees) 
    {
        this.pFees = pFees;
    }

    public void setAllProfiles(Vector<Profile> allProfiles)
    {
        this.allProfiles = allProfiles;
    }

    public int getpID() 
    {
        return pID;
    }

    public String getpName() 
    {
        return pName;
    }

    public int getRenew_Duration()
    {
        return renew_Duration;
    }

    public float getpFees()
    {
        return pFees;
    }

    public Vector<Profile> getAllProfiles() 
    {
        return allProfiles;
    }    
}
