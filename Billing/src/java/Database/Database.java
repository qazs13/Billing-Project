/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author ramr
 */
public class Database {

    private final String url = "jdbc:postgresql://localhost/billing_system";
    private final String user = "postgres";
    private final String password = "1234";
    public Connection connection = null;
    public String SQLcommand = null;
    public ResultSet rs = null;
    public PreparedStatement ps = null;
    public Statement s = null;

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection made successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void disconnect() throws SQLException {
        connection.close();
        ps.close();
        System.out.println("Connection disabled");
    }

    boolean r = false;
    int inserted = 0;

    public int insert_customer(String fname ,String lname , String email ,String address ) {
       
        try {
            connect();
            SQLcommand = "insert into customer (first_name,last_name,email,address) values (?,?,?,?); ";
            ps = connection.prepareStatement(SQLcommand);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, email);
            ps.setString(4, address);
            inserted=ps.executeUpdate();
          
        } 
        catch (Exception e) {
            e.printStackTrace();
            disconnect();
           inserted=0;
        }
        finally {

            return inserted;
        }
    }
    
     public int insert_service(String sname) {
       
        try {
            connect();
            SQLcommand = "insert into services (sname) values (?); ";
            ps = connection.prepareStatement(SQLcommand);
            ps.setString(1, sname);
            inserted=ps.executeUpdate();
          
        } 
        catch (Exception e) {
            e.printStackTrace();
            disconnect();
           inserted=0;
        }
        finally {

            return inserted;
        }
    }
     
      public int insert_profile(String pname , int duration, int pfees ) {
       
        try {
            connect();
            SQLcommand = "insert into profile (pname,renew_duration,pfees) values (?,?,?); ";
            ps = connection.prepareStatement(SQLcommand);
            ps.setString(1, pname);
            ps.setInt(2, duration);
            ps.setInt(3, pfees);
            inserted=ps.executeUpdate();
          
        } 
        catch (Exception e) {
            e.printStackTrace();
            disconnect();
           inserted=0;
        }
        finally {

            return inserted;
        }
    }
    

       public int insert_freeUnits(int fid,int free_voice ,int free_sms,int free_internet) {
       
        try {
            connect();
            SQLcommand = "insert into free_units values (?,?,?,?); ";
            ps = connection.prepareStatement(SQLcommand);
            ps.setInt(1, fid);
            ps.setInt(2, free_voice);
            ps.setInt(3, free_sms);
            ps.setInt(4, free_internet);
            inserted=ps.executeUpdate();
          
        } 
        catch (Exception e) {
            e.printStackTrace();
            disconnect();
           inserted=0;
        }
        finally {

            return inserted;
        }
    }
      
    public static void main(String[] args) {
        
        Database db =new Database();
//        db.insert_customer("Ali", "Mohamed", "ali@telecom.com", "Masr el gedida");
        db.insert_freeUnits(3, 10, 0, 300);
 }

}
