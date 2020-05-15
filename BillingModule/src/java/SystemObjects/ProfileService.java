package SystemObjects;


//profile_services_id serial primary key,pid int references profile(pid),sid int references services(sid),
//round_amount int,fees_local_same float,fees_local_diff float,fees_international float

public class ProfileService {
    
    
    int profileServiceID,profileID,serviceID;
    int roundAmount;
    Float feeSameOperator,feeAnotherOperator;
    Float feeInternationally;
    
    
     public ProfileService(){       
    }
     
    public ProfileService(int roundAmount)
    {
        this.roundAmount = roundAmount;
    }     
     
    public ProfileService(int _profileID,int _serviceID){
        this.profileID = _profileID;
        this.serviceID = _serviceID;
        
    }
    
    public ProfileService(int _profileServiceID,int _profileID,int _serviceID,int _roundAmount,
            Float _feeSameOperator,Float _feeAnotherOperator, Float _feeInternationally){
        this.profileServiceID = _profileServiceID;
        this.profileID = _profileID;
        this.serviceID = _serviceID;
        this.roundAmount = _roundAmount;
        this.feeSameOperator = _feeSameOperator;
        this.feeAnotherOperator = _feeAnotherOperator;
        this.feeInternationally = _feeInternationally;
    }
    
    public void setProfileServiceID(int _profileServiceID){
            this.profileServiceID = _profileServiceID;
    }
    
    public void setProfileID(int _profileID){
        this.profileID = _profileID;
    }
    
    public void setServiceID(int _serviceID){
        this.serviceID = _serviceID;
    }
    
    public void setRoundAmount(int _roundAmount){
       this.roundAmount = _roundAmount; 
    }
    
    public void setFeeSameOperator(Float _feeSameOperator){
        this.feeSameOperator = _feeSameOperator;
    }
    
    public void setFeeAnotherOperator(Float _feeAnotherOperator){
        this.feeAnotherOperator = _feeAnotherOperator;
    }
    
    public void setFeeInternationally(Float _feeInternationally){
        this.feeInternationally = _feeInternationally;
    }
    
    
    public int getProfileServiceID(){
            return this.profileServiceID;
    }
    
    public int getProfileID(){
        return this.profileID;
    }
    
    public int getServiceID(){
        return this.serviceID;
    }
    
    public int getRoundAmount(){
       return this.roundAmount; 
    }
    
    public Float getFeeSameOperator(){
        return this.feeSameOperator;
    }
    
    public Float getFeeAnotherOperator(){
        return this.feeAnotherOperator;
    }
    
    public Float getFeeInternationally(){
        return this.feeInternationally;
    }
    
}
