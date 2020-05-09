/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billingmodule;

import Database.databaseConnection;
import SystemObjects.OCC;
import SystemObjects.UDR;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author ahmed
 */
public class OneTime_Recurring_Calc {
    
    //recurring_service 
//one_time_service 

    public OCC getOneTimeRecurringFee(UDR customerUDR) throws SQLException {
        float totalonetimecost =0;
        float totalrecurringcost =0;
        databaseConnection db = new databaseConnection();
        Vector<OCC> alloccs =db.getAllOcc(customerUDR);
        for (OCC occ : alloccs){
            System.out.println(occ.type_of_service);
            if(occ.type_of_service .equals("onetime")){
                
                if( occ.one_rec_id== db.getonetimeid(occ))  { //onetime
                    System.out.println(db.getOneTimeFees(occ));
                    totalonetimecost+=db.getOneTimeFees(occ);
                    System.out.println(totalonetimecost);
                }
            }
            else if(occ.type_of_service.equals("recurring"))  {  
                 if(  occ.one_rec_id ==db.getRecurringid(occ) ) {
                
                    totalrecurringcost += db.getRecurringFees(occ);
                     System.out.println(totalrecurringcost);
                }
                
                
            }
            db.updateOCC(occ);
        }
        return new OCC(totalonetimecost, totalrecurringcost);
    }
        
    
    
    public static void main(String[] args) throws SQLException {
        OneTime_Recurring_Calc o = new OneTime_Recurring_Calc();
        OCC occ = new OCC();
        occ =o.getOneTimeRecurringFee(new UDR("01221234567",1));
        System.out.println(occ.totalonetime +" "+occ.totalrecurring);
    }
    
    
    }