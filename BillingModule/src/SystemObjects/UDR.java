package SystemObjects;

import java.sql.Timestamp;

public class UDR {
    
    int udrID,profileID;
    String dialA,dialB;
    int serviceID;
    Float duration_msg_volume;
    String start_date,start_time;
    Float external_charges;
    Boolean has_fu,is_billed;
    Float cost;
    Timestamp orderedDate;
    
    public UDR(){
    }
    
    public UDR(String _dialA, String _dialB, int _serviceID, float _duration_msg_volume, String _orderedDate, String string2) {
       
    
        
        this.dialA = _dialA;
        this.dialB = _dialB;
        this.serviceID = _serviceID;
        this.duration_msg_volume = _duration_msg_volume;
        this.start_date = _orderedDate;
        this.start_time = string2;
       
    
    
    }
    
    
    public UDR(int _udrID){
        this.udrID = _udrID;
    }
    
    public UDR(String _dialA, int _profileID){
        this.dialA = _dialA;
        this.profileID = _profileID;
    }
  
    
    public UDR(String _dialA,int _profileID, int _serviceID){
        this.dialA = _dialA;
        this.profileID = _profileID;
        this.serviceID = _serviceID;
    }
    
    public UDR(int _udrID,Timestamp _orderedDate){
        this.udrID = _udrID;
        this.orderedDate = _orderedDate;
    }
    
    public UDR(int _udrID,int _profileID,String _dialA,String _dialB,int _serviceID,Float _duration_msg_volume
        ,String _startDate, String _startTime,Float _externalCharges, Boolean _hasFU, Float _cost, Boolean _isBilled){
        
        this.udrID = _udrID;
        this.profileID = _profileID;
        this.dialA = _dialA;
        this.dialB = _dialB;
        this.serviceID = _serviceID;
        this.duration_msg_volume = _duration_msg_volume;
        this.start_date = _startDate;
        this.start_time =_startTime;
        this.external_charges = _externalCharges;
        this.has_fu = _hasFU;
        this.cost = _cost;
        this.is_billed = _isBilled;
    }
    
      public UDR(int _udrID,int _profileID,String _dialA,String _dialB,int _serviceID,Float _duration_msg_volume
        ,Timestamp _orderedDate,Float _externalCharges, Boolean _hasFU, Float _cost, Boolean _isBilled){
        
        this.udrID = _udrID;
        this.profileID = _profileID;
        this.dialA = _dialA;
        this.dialB = _dialB;
        this.serviceID = _serviceID;
        this.duration_msg_volume = _duration_msg_volume;
        this.orderedDate = _orderedDate;
        this.external_charges = _externalCharges;
        this.has_fu = _hasFU;
        this.cost = _cost;
        this.is_billed = _isBilled;
    }
    
    
    public void setUdrID(int _udrID){
        this.udrID = _udrID;
    }
    public void setProfileID(int _profileID){
        this.profileID = _profileID;
    } 
    public void setDialA(String _dialA){
        this.dialA = _dialA;
    }
    public void setDialB(String _dialB){
        this.dialB = _dialB;
    }
    public void setServiceID(int _serviceID){
        this.serviceID = _serviceID;
    }
    public void setDurationMsgVolume(Float _duration_msg_volume){
        this.duration_msg_volume = _duration_msg_volume;
    }
    public void setStartDate(String _startDate){
        this.start_date = _startDate;
    }
    public void setStartTime(String _startTime){
        this.start_time = _startTime;
    }
    public void setExternalCharges(Float _externalCharges){
        this.external_charges = _externalCharges;
    }
    public void setHasFU(Boolean _hasFU){
        this.has_fu = _hasFU;
    }
    public void setCost(Float _cost){
        this.cost = _cost;
    }
    public void setIsBilled( Boolean _isBilled){
        this.is_billed = _isBilled;
    }
    public void setOrderedDate(Timestamp _date){
        this.orderedDate = _date;
    }


    public int getUdrID(){
        return this.udrID;
    }
    public int getProfileID(){
        return this.profileID;
    } 
    public String getDialA(){
        return this.dialA;
    }
    public String getDialB(){
        return this.dialB;
    }
    public int getServiceID(){
        return this.serviceID;
    }
    public Float getDurationMsgVolume(){
        return this.duration_msg_volume;
    }
    public String getStartDate(){
        return this.start_date;
    }
    public String getStartTime(){
        return this.start_time;
    }
    public Float getExternalCharges(){
        return this.external_charges;
    }
    public Boolean getHasFU(){
        return this.has_fu;
    }
    public Float getCost(){
        return this.cost;
    }
    public Boolean getIsBilled(){
        return this.is_billed;
    }
    public Timestamp getOrderedDate(){
        return orderedDate;
    }
}
