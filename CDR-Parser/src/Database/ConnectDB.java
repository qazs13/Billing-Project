/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import CdrModel.Cdr;
import java.io.IOException;
import java.sql.*;
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
                    "jdbc:postgresql://localhost:5432/billing_system", "postgres", "amrwsk13");
            System.out.println("connection success");
        } catch (SQLException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
        return con;

    }
    
    public void insertCdr(Cdr cdr) throws ClassNotFoundException, SQLException {
        connect();
        String sql = "insert into cdr (diala,dialb,sid,duration_msg_volume,start_date,start_time,external_charges,is_rated)"
                + "values (?,?,?,?,?,?,?,?)";
        pst = con.prepareStatement(sql);
        pst.setString(1, cdr.diala);
        pst.setString(2, cdr.dialb);
        pst.setInt(3,cdr.sid);
        pst.setLong(4, cdr.duration_msg_vol);
        pst.setString(5, cdr.start_date);
        pst.setString(6,cdr.start_time);
        pst.setDouble(7,cdr.external_charges);
        pst.setBoolean(8,cdr.is_rated);
        pst.execute();
        System.out.println("inserted");
        pst.close();
        con.close();
    }

}
