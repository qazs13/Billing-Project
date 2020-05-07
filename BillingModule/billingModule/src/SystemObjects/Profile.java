package SystemObjects;


public class Profile {
    
    int profileID;
    String profileName;
    int renewDuration;
    Float profileFees;
    
    public Profile(){
        
    }
    
    public Profile(int _profileID){
        this.profileID = _profileID;
    }
    
    public Profile(int _profileID,String _profileName,int _renewDuration,Float _profileFees){
        
        this.profileID = _profileID;
        this.profileName = _profileName;
        this.renewDuration = _renewDuration;
        this.profileFees = _profileFees;
    }
    
    public void setProfileID(int _profileID){
        this.profileID = _profileID;
    }
    
    public void setProfileName(String _profileName){
        this.profileName = _profileName;
    }
    
    public void setRenewDuration(int _renewDuration){
        this.renewDuration = _renewDuration;
    }
    
    public void setProfileFees(Float _profileFees){
        this.profileFees = _profileFees;
    }
    
   
     public int getProfileID(){
        return this.profileID;
    }
    
    public String getProfileName(){
        return this.profileName;
    }
    
    public int getRenewDuration(){
        return this.renewDuration;
    }
    
    public Float getProfileFees(){
        return this.profileFees;
    }

}
