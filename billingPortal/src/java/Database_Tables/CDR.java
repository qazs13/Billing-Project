package Database_Tables;

import java.util.Vector;

public class CDR 
{
    int cdr_id;
    String diala;
    String dialb;
    int sid;
    int duration_msg_volume;
    String start_date;
    String start_time;
    String external_charges;
    boolean is_rated;
    Vector<CDR> allCDRS;
    
    public CDR ()
    {
        allCDRS = new Vector();
    }
    
    public CDR(int cdr_id, String diala, String dialb, int sid, int duration_msg_volume, String start_date, String start_time, String external_charges, boolean is_rated, Vector<CDR> allCDRS) 
    {
        this.cdr_id = cdr_id;
        this.diala = diala;
        this.dialb = dialb;
        this.sid = sid;
        this.duration_msg_volume = duration_msg_volume;
        this.start_date = start_date;
        this.start_time = start_time;
        this.external_charges = external_charges;
        this.is_rated = is_rated;
        this.allCDRS = allCDRS;
    }    

    public void setCdr_id(int cdr_id) 
    {
        this.cdr_id = cdr_id;
    }

    public void setDiala(String diala) 
    {
        this.diala = diala;
    }

    public void setDialb(String dialb) 
    {
        this.dialb = dialb;
    }

    public void setSid(int sid) 
    {
        this.sid = sid;
    }

    public void setDuration_msg_volume(int duration_msg_volume) 
    {
        this.duration_msg_volume = duration_msg_volume;
    }

    public void setStart_date(String start_date) 
    {
        this.start_date = start_date;
    }

    public void setStart_time(String start_time) 
    {
        this.start_time = start_time;
    }

    public void setExternal_charges(String external_charges) 
    {
        this.external_charges = external_charges;
    }

    public void setIs_rated(boolean is_rated) 
    {
        this.is_rated = is_rated;
    }
    
    public void setAllCDRS(Vector<CDR> allCDRS)
    {
        this.allCDRS = allCDRS;
    }    

    public int getCdr_id() 
    {
        return cdr_id;
    }

    public String getDiala() 
    {
        return diala;
    }

    public String getDialb()
    {
        return dialb;
    }

    public int getSid()
    {
        return sid;
    }

    public int getDuration_msg_volume() 
    {
        return duration_msg_volume;
    }

    public String getStart_date()
    {
        return start_date;
    }

    public String getStart_time() 
    {
        return start_time;
    }

    public String getExternal_charges()
    {
        return external_charges;
    }

    public boolean isIs_rated() 
    {
        return is_rated;
    }

    public Vector<CDR> getAllCDRS() 
    {
        return allCDRS;
    }    
}
