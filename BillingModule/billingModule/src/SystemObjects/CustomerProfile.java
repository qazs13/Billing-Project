package SystemObjects;

public class CustomerProfile {
    
    
    String msisdn;
    int profileID;
    int serviceID;
    int consumedQuantity;
    String startDateOfContract,endDateOfContract;
    String blockedServices;
    int fuVoiceOnNet,fuVoiceCrossNet;
    int fuSMSOnNet,fuSMSCrossNet,fuInternet;
    Float totalVoiceServiceCost;
    Float totalSMSServiceCost;
    Float totalDataServiceCost;
    
    
    public CustomerProfile(){
    }
    
    public CustomerProfile(int _profileID,String _msisdn){
        this.msisdn = _msisdn;
        this.profileID = _profileID;
    }    
    
     public CustomerProfile(String _msisdn,int _profileID,int _serviceID,int _consumedQuantity){
        this.msisdn = _msisdn;
        this.profileID = _profileID;
        this.serviceID = _serviceID;
        this.consumedQuantity = _consumedQuantity;
    } 

    public CustomerProfile(String _msisdn,int _profileID,
            String _startDateOfContract, String _endDateOfContract,String _blockedServices,
            int _fuVoiceOnNet,int _fuVoiceCrossNet, int _fuSMSOnNet,
            int _fuSMSCrossNet,int _fuInternet){
        this.msisdn = _msisdn;
        this.profileID= _profileID;
        this.startDateOfContract =_startDateOfContract;
        this.endDateOfContract = _endDateOfContract;
        this.blockedServices = _blockedServices;
        this.fuVoiceOnNet = _fuVoiceOnNet;
        this.fuVoiceCrossNet = _fuVoiceCrossNet;
        this.fuSMSOnNet = _fuSMSOnNet;
        this.fuSMSCrossNet = _fuSMSCrossNet;
        this.fuInternet = _fuInternet;
    }
    
    
    public CustomerProfile(String _msisdn,int _profileID,Float _totalVoiceServiceCost,
            Float _totalSMSServiceCost,Float _totalDataServiceCost){
        this.msisdn = _msisdn;
        this.profileID = _profileID;
        this.totalVoiceServiceCost = _totalVoiceServiceCost;
        this.totalSMSServiceCost = _totalSMSServiceCost;
        this.totalDataServiceCost = _totalDataServiceCost;
    }
    
    public void setProfileID(int _profileID){
        this.profileID = _profileID;
    }
    
    public void setMSISDN(String _msisdn){
        this.msisdn = _msisdn;
    }
  
    public void setBlockedServices(String _blockedServices){
        this.blockedServices =_blockedServices;
    }

    public void setStartDateOfContract(String _startDateOfContract){
        this.startDateOfContract = _startDateOfContract;
    }

    public void setEndDateOfContract(String _endDateOfContract){
        this.endDateOfContract = _endDateOfContract;
    }
    
    public void setFUVoiceOnNet(int _fuVoiceOnNet){
        this.fuVoiceOnNet = _fuVoiceOnNet;
    }

    public void setFUVoiceCrossNet(int _fuVoiceCrossNet){
        this.fuVoiceCrossNet =_fuVoiceCrossNet;
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

    public void setServiceID(int _serviceID){
        this.serviceID = _serviceID;
    }
    
    public void setConsumedQuantity(int _consumedQuantity){
        this.consumedQuantity = _consumedQuantity;
    }

    public void setTotalVoiceServiceCost(Float _totalVoiceServiceCost){
        this.totalVoiceServiceCost = _totalVoiceServiceCost;
    }
    
    public void setTotalSMSServiceCost(Float _totalSMSServiceCost){
        this.totalSMSServiceCost = _totalSMSServiceCost;
    }
    
    public void setTotalDataServiceCost(Float _totalDataServiceCost){
        this.totalDataServiceCost = _totalDataServiceCost;
    }
    
    
    
    public int getProfileID(){
         return this.profileID;
    }
    
    public String getMSISDN(){
        return this.msisdn;
    }
   
    public String getBlockedServices(){
        return this.blockedServices;
    }

    public String getStartDateOfContract(){
        return this.startDateOfContract;
    }

    public String getEndDateOfContract(){
        return this.endDateOfContract;
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
       return this.fuInternet; 
    }

    public int getServiceID(){
        return this.serviceID;
    }
    
    public int getConsumedQuantity(){
        return this.consumedQuantity;
    }

    public Float getTotalVoiceServiceCost(){
        return this.totalVoiceServiceCost;
    }
    
    public Float getTotalSMSServiceCost(){
        return this.totalSMSServiceCost;
    }
    
    public Float getTotalDataServiceCost(){
        return this.totalDataServiceCost;
    }
}
