/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billing;

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
public class billing2 {
    
     private final String url = "jdbc:postgresql://localhost:5432/billing_system";
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
            System.out.println("Connection is made successfully");
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
    
     public int select_cdr() {
       Vector<> cdr = new Vector();
        int id=0;
        String dialA=null;
        String dialB=null;
        int sid=0;
        int duration=0;
        String start_date=null;
        String start_time=null;
        int external_charges=0;
        boolean rated=false;
        
         try {
          connect();
          SQLcommand = "select * from cdr";
          s=connection.createStatement();
          rs=s.executeQuery(SQLcommand);
          while(rs.next())
          {
//              cdr.add(new String[]{rs.getString("dialA"),rs.getString("dialB"),rs.getInt("sid"),rs.getString("duration_msg_volume"),rs.getString("start_date"),rs.getString("start_time"),rs.getString("extenal_charges"),rs.getString("is_rated")});
              id=rs.getInt("cdr_id");
              dialA=rs.getString("dialA");
              dialB=rs.getString("dialB");
              sid=rs.getInt("sid");
              duration=rs.getInt("duration_msg_volume");
              start_date=rs.getString("start_date");
              start_time=rs.getString("start_time");
              external_charges=rs.getInt("external_charges");
              rated=rs.getBoolean("is_rated");
          }
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
    
    
    
    
      public static void main(String[] args) {

                billing2 rate = new billing2();
               
      }
    
}
