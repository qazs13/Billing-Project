package SystemObjects;

public class FreeUnit {
    int FU_ID;
    int fuVoiceOnNet,fuVoiceCrossNet;
    int fuSMSOnNet,fuSMSCrossNet;
    int fuInternet,profileID;
    
    
    public FreeUnit(){ 
    }
    
    public FreeUnit(int _profileID){
        this.profileID = _profileID;
    }
    
    public FreeUnit(int _FU_ID,int _fuVoiceOnNet, int _fuVoiceCrossNet,int _fuSMSOnNet
    ,int _fuSMSCrossNet, int _fuInternet, int _profileID){
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
    
    public void setFUVoiceOnNet(int _fuVoiceOnNet){
        this.fuVoiceOnNet = _fuVoiceOnNet;
    }
    
    public void setFUVoiceCrossNet(int _fuVoiceCrossNet){
        this.fuVoiceCrossNet = _fuVoiceCrossNet;
    }
    
     public void setFUSMSOnNet(int _fuSMSOnNet){
        this.fuSMSOnNet = _fuSMSOnNet;
    }
    
    public void setFUSMSCrossNet(int _fuSMSCrossNet){
        this.fuSMSCrossNet = _fuSMSCrossNet;
    }
     
    public void setFUInternet(int _fuInternet){
        this.fuInternet = _fuInternet;
    }
    
    public void setProfileID(int _profileID){
        this.profileID = _profileID;
    }
    
    
    public int getFUID(){
        return this.FU_ID;
    }
    
    public int getFUVoiceOnNet(){
        return this.fuVoiceOnNet;
    }
    
    public int getFUVoiceCrossNet(){
        return this.fuVoiceCrossNet;
    }
    
    public int getFUSMSOnNet(){
       return this.fuSMSOnNet;
   }

    public int getFUSMSCrossNet(){
        return this.fuSMSCrossNet;
    }

    public int getFUInternet(){
        return  this.fuInternet;
    }

    public int getProfileID(){
         return this.profileID;
     }
}
