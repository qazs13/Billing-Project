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

    public float fuInternetUpdate(UDR customerUDR) throws SQLException {

        databaseConnection db = new databaseConnection();
        Vector<OCC> alloccs =db.getAllOcc(customerUDR);
        for (OCC occ : alloccs){
            System.out.println(occ.msisdn);
        }
    
        return 0;
    }
    public static void main(String[] args) throws SQLException {
        databaseConnection db = new databaseConnection();
        Vector<OCC> alloccs =db.getAllOcc(new UDR("01221234567", 1));
        for (OCC occ : alloccs){
            System.out.println(occ.msisdn);
        }
    }
}
