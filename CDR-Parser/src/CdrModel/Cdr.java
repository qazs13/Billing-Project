/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CdrModel;

import java.util.Date;

/**
 *
 * @author ahmed
 */
public class Cdr {
    
    public int cdr_id;
    public String diala;
    public String dialb;
    public int sid;
    public int duration_msg_vol;
    public String start_date;
    public String start_time;
    public double external_charges;
    public boolean is_rated;

    public Cdr(String diala, String dialb, int sid, int duration_msg_vol, String start_date, String start_time, double  external_charges, boolean is_rated) {
        this.diala = diala;
        this.dialb = dialb;
        this.sid = sid;
        this.duration_msg_vol = duration_msg_vol;
        this.start_date = start_date;
        this.start_time = start_time;
        this.external_charges = external_charges;
        this.is_rated = is_rated;
    }

    public Cdr() {
    }
    
    
    
    
}
