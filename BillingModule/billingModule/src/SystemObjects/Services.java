package SystemObjects;

import java.util.Vector;

public class Services {
    
    int sid;
    String sname;
    boolean isRecurring = false;
    boolean isOneTime = false;
    float serviceFees;
    Vector<Services> allServices;
 
    public Services() {
        allServices = new Vector();
    }    
    
    public Services(int sid) {
        this.sid = sid;
    }

    public Services(String sname) {
        this.sname = sname;
    }
    
    public Services(String sname, Boolean isRecurring, float serviceFees) {
        this.sname = sname;
        this.isRecurring = isRecurring;
        this.serviceFees = serviceFees;
    }
    
    public Services(String sname, Boolean isOneTime, float serviceFees, Object PutItNull) {
        this.sname = sname;
        this.isOneTime = isOneTime;
        this.isRecurring = false;
        this.serviceFees = serviceFees;
    }    
    
    public Services(int sid, String sname, Boolean isRecurring, float serviceFees) {
        this.sid = sid;
        this.sname = sname;
        this.isRecurring = isRecurring;
        this.serviceFees = serviceFees;
    }    
    
    public Services(int sid, String sname, Boolean isOneTime, float serviceFees, Object PutItNull){
        this.sid = sid;
        this.sname = sname;
        this.isOneTime = isOneTime;
        this.isRecurring = false;        
        this.serviceFees = serviceFees;
    }

    
    
    public void setSid(int sid){
        this.sid = sid;
    }

    public void setSname(String sname){
        this.sname = sname;
    }

    public void setIsRecurring(boolean isRecurring) {
        this.isRecurring = isRecurring;
    }
    
    public void setIsOneTime(boolean isOneTime){
        this.isOneTime = isOneTime;
    }

    public void setServiceFees(float serviceFees){
        this.serviceFees = serviceFees;
    }    
    
    public void setAllServices(Vector<Services> allServices) {
        this.allServices = allServices;
    }        

    
    
    
    public int getSid() {
        return sid;
    }        
    
    public String getSname() {
        return sname;
    }     
    
    public boolean getIsRecurring() {
        return isRecurring;
    }

    public boolean getIsOneTime() {
        return isOneTime;
    }

    public float getServiceFees() {
        return serviceFees;
    }    
    
    public Vector<Services> getAllServices(){
        return allServices;
    } 
    
}
