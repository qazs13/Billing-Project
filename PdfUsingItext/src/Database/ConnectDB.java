/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import SystemObjects.OCC;
import SystemObjects.UDR;
//import com.itextpdf.text.List;
import java.util.List.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmed
 */
public class ConnectDB {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public Connection connect() throws ClassNotFoundException {

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/billing_system", "postgres", "ahmed");
            System.out.println("connection success");
        } catch (SQLException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
        return con;

    }

    public void getData() {
        try {
            connect();
            String sql = "select * from cdr";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                rs.getString("diala");
                rs.getString("dialb");
                rs.getString("start_date");
        
            }

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("wrong");
        } finally {
            stop();
        }

    }

   
    public List<OCC> getAllOcc() throws SQLException, ClassNotFoundException {
        connect();
        List<OCC> retrievedOccs = new ArrayList<OCC>();
        OCC occ;
        String sql = "select * from occ";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            occ = new OCC(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getString(4));
            retrievedOccs.add(occ);

        }
        stop();
        return retrievedOccs;
    }

    private void stop() {
        try {
            con.close();
            System.out.println("Database Closed");
        } catch (SQLException ex) {
            System.out.println("databaseconnection.database.stop()error");
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectDB c = new ConnectDB();
        c.connect();
        
      
    }

}
