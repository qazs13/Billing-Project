/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemObjects;

import java.sql.Date;

/**
 *
 * @author ahmed
 */
public class OCC {
    
    public int occ_id;
    public String msisdn;
    public int one_rec_id ;
    public String type_of_service;
    public boolean is_service_processed;
    public Date serviceprocessed_date;
    public float totalonetime;
    public float totalrecurring;
    
    public OCC(int occ_id, String msisdn, int one_rec_id, String type_of_service, boolean is_service_processed, Date serviceprocessed_date) {
        this.occ_id = occ_id;
        this.msisdn = msisdn;
        this.one_rec_id = one_rec_id;
        this.type_of_service = type_of_service;
        this.is_service_processed = is_service_processed;
        this.serviceprocessed_date = serviceprocessed_date;
    }

    public OCC(float totalonetime, float totalrecurring) {
        this.totalonetime = totalonetime;
        this.totalrecurring = totalrecurring;
    }
    
    public OCC (){
    }
    
    
    
}
