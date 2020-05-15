package SystemObjects;

public class FreeUnit {
    int FU_ID;
    Float fuVoiceOnNet,fuVoiceCrossNet;
    Float fuSMSOnNet,fuSMSCrossNet;
    Float fuInternet;
    int profileID;
    
    
    public FreeUnit(){ 
    }
    
    public FreeUnit(int _profileID){
        this.profileID = _profileID;
    }
    
    public FreeUnit(Float _fuVoiceOnNet, Float _fuVoiceCrossNet,Float _fuSMSOnNet, Float _fuSMSCrossNet, Float _fuInternet, int _profileID)
    {  
        this.fuVoiceOnNet = _fuVoiceOnNet;
        this.fuVoiceCrossNet = _fuVoiceCrossNet;
        this.fuSMSOnNet = _fuSMSOnNet;
        this.fuSMSCrossNet = _fuSMSCrossNet;
        this.fuInternet = _fuInternet;
        this.profileID =_profileID;     
    }    
    
    public FreeUnit(int _FU_ID,Float _fuVoiceOnNet, Float _fuVoiceCrossNet,Float _fuSMSOnNet
    ,Float _fuSMSCrossNet, Float _fuInternet, int _profileID){
        this.FU_ID=_FU_ID;
        this.fuVoiceOnNet = _fuVoiceOnNet;
        this.fuVoiceCrossNet = _fuVoiceCrossNet;
        this.fuSMSOnNet = _fuSMSOnNet;
        this.fuSMSCrossNet = _fuSMSCrossNet;
        this.fuInternet = _fuInternet;
        this.profileID =_profileID;     
    }
    
    public void setFUID(int _FU_ID){
        this.FU_ID = _FU_ID;
    }
    
    public void setFUVoiceOnNet(Float _fuVoiceOnNet){
        this.fuVoiceOnNet = _fuVoiceOnNet;
    }
    
    public void setFUVoiceCrossNet(Float _fuVoiceCrossNet){
        this.fuVoiceCrossNet = _fuVoiceCrossNet;
    }
    
     public void setFUSMSOnNet(Float _fuSMSOnNet){
        this.fuSMSOnNet = _fuSMSOnNet;
    }
    
    public void setFUSMSCrossNet(Float _fuSMSCrossNet){
        this.fuSMSCrossNet = _fuSMSCrossNet;
    }
     
    public void setFUInternet(Float _fuInternet){
        this.fuInternet = _fuInternet;
    }
    
    public void setProfileID(int _profileID){
        this.profileID = _profileID;
    }
    
    
    public int getFUID(){
        return this.FU_ID;
    }
    
    public Float getFUVoiceOnNet(){
        return this.fuVoiceOnNet;
    }
    
    public Float getFUVoiceCrossNet(){
        return this.fuVoiceCrossNet;
    }
    
    public Float getFUSMSOnNet(){
       return this.fuSMSOnNet;
   }

    public Float getFUSMSCrossNet(){
        return this.fuSMSCrossNet;
    }

    public Float getFUInternet(){
        return  this.fuInternet;
    }

    public int getProfileID(){
         return this.profileID;
     }
}
