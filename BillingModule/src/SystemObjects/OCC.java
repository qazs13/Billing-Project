package SystemObjects;

import java.sql.Date;


public class OCC {
    
    public int occ_id;
    public String msisdn;
    public int one_rec_id ;
    public String type_of_service;
    public boolean is_service_processed;
    public Date serviceprocessed_date;
    Float totalOneTimeFees;
    Float totalRecurringFees;

    public OCC(){
        
    }
    
    public OCC(int _occ_id){
        this.occ_id = _occ_id;
    }
    
    public OCC(Float _totalOneTimeFees, Float _totalRecurringFees){
        this.totalOneTimeFees = _totalOneTimeFees;
        this.totalRecurringFees = _totalRecurringFees;
    }
    
    public OCC(int occ_id, String msisdn, int one_rec_id, String type_of_service, boolean is_service_processed, Date serviceprocessed_date) {
        this.occ_id = occ_id;
        this.msisdn = msisdn;
        this.one_rec_id = one_rec_id;
        this.type_of_service = type_of_service;
        this.is_service_processed = is_service_processed;
        this.serviceprocessed_date = serviceprocessed_date;
    } 
    
    public void setTotalOneTimeFees(Float _totalOneTimeFees){
        this.totalOneTimeFees = _totalOneTimeFees;
    }
    
    public void setTotalRecurringFees(Float _totalRecurringFees){
        this.totalRecurringFees = _totalRecurringFees;
    }
    
    public Float getTotalOneTimeFees(){
        return this.totalOneTimeFees;
    }
    
    public Float getTotalRecurringFees(){
        return this.totalRecurringFees;
    }
}
