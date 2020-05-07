/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_tables;

/**
 *
 * @author ramr
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Database {
    
     
    private final String url = "jdbc:postgresql://localhost:5432/billing_system";
    private final String user = "postgres";
    private final String password = "1234";

    public Connection connection = null;
    public String SQLcommand = null;
    public ResultSet rs = null;
    public PreparedStatement ps = null;
    public Statement s = null;
    boolean r = false;
    int inserted = 0;



    private void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is made successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void disconnect() throws SQLException {
        connection.close();
        ps.close();
        System.out.println("Connection disabled");
    }

    
     public CDR select_cdr() {
            CDR cdr = new CDR();
    
        
         try {
          connect();
          SQLcommand = "select * from cdr where is_rated=false";
          s=connection.createStatement();
          rs=s.executeQuery(SQLcommand);
          while(rs.next())
          {
              cdr.getAllCDRS().add(new CDR(rs.getInt("cdr_id"),
                      rs.getString("dialA"),
                      rs.getString("dialB"),
                      rs.getInt("sid"),
                      rs.getInt("duration_msg_volume"),
                      rs.getString("start_date"),
                      rs.getString("start_time"),
                      rs.getFloat("external_charges"),
                      rs.getBoolean("is_rated")
              ));
                                         
          }
          

         } 
        catch (Exception e) {
            e.printStackTrace();
            disconnect();
        } 
        finally {

            return cdr;
        }
    
     }
     
     
          public int select_pid(CDR cdr) {
              
              int pid = 0;

        
         try {
          connect();
          for(int i=0;i<cdr.getAllCDRS().size();i++){
          System.out.println(cdr.getAllCDRS().elementAt(i).getDiala());
                    SQLcommand= "select * from customer_profile where msisdn =? ";
                    ps= connection.prepareStatement(SQLcommand);
                    ps.setString(1, cdr.getAllCDRS().elementAt(i).getDiala());
                    rs = ps.executeQuery();
                 
                    while (rs.next()) {
                        System.out.println("I'm in");
                        pid = rs.getInt("pid");
                        System.out.println(pid); 
                        
                    }}
          inserted=1;
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
    
     
}
