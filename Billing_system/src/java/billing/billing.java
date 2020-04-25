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
public class billing {

    private final String url = "jdbc:postgresql://localhost/billing_system";
    private final String user = "postgres";
    private final String password = "1234";
    public Connection connection = null;
    public String SQLcommand = null;
    public ResultSet rs = null;
    public ResultSet rs1 = null;
    public PreparedStatement ps = null;
    public PreparedStatement ps1 = null;
    public Statement s = null;
    public Statement s1 = null;

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
       
       
     public int select_cdr() {
//       Vector<String[]> cdr = new Vector();
        int id=0;
        String dialA=null;
        String dialB=null;
        int sid=0;
        int duration=0;
        String start_date=null;
        String start_time=null;
        int external_charges=0;
        boolean rated=false;
        int free_voice_same;
        int free_voice_diff;
        int free_sms_same;
        int free_sms_diff;
        int free_internet;
        
        
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
              
              if(!rated)
              {
                  SQLcommand = "select * from customer_profile where msisdn.equals(?)";
                  ps1=connection.prepareStatement(SQLcommand);
                  ps1.setString(1, dialA);
                  rs1=ps1.executeQuery(SQLcommand);
                  while(rs1.next())
                  {
                      int blocked_service =rs1.getInt("blocked_services");
                      if((blocked_service)!=1 || (blocked_service)!=2 || (blocked_service)!=3)
                      {
                          free_voice_same=rs1.getInt("free_voice_same");
                          free_voice_diff=rs1.getInt("free_voice_diff");
                          free_sms_same=rs1.getInt("free_sms_same");
                          free_sms_diff=rs1.getInt("free_sms_diff");
                          free_internet=rs1.getInt("free_internet");
                          
                          if(sid==1)  // voice
                          {
                              
                          }
                          
                          else if (sid==2)  //sms
                          {
                              
                          }
                          
                          else if (sid==3) //internet
                          {
                              free_internet=free_internet=duration;
                              
                          }
                          
                          else 
                          {
                              System.out.println("This is not a valid service");
                          }

                      }
                  
                  }
                  
                  
              }
          
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
     
//          public int check_cdr() {
//      
//               Vector<String[]> cdrCheck = new Vector();
//               cdrCheck = select_cdr();
//               
//        try {
//            connect();
//            
//            for(int i=0;i<cdrCheck.size();i++)
//            { 
//                if(cdrCheck[0][])
//                {
//                    
//                }
//                
//            }
//            
//              inserted=1;
//          
//        } 
//        catch (Exception e) {
//            e.printStackTrace();
//            disconnect();
//           inserted=0;
//        } 
//        finally {
//
//            return inserted;
//        }
//     }
//      
    public static void main(String[] args) {
  
 }

}
