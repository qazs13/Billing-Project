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
//       Vector<> cdr = new Vector();
        int id=0;
        String dialA=null;
        String dialB=null;
        int sid=0;
        int duration=0;
        String start_date=null;
        String start_time=null;
        float external_charges=0;
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
              
              
              // insert into vector
              
              if(!rated)
              {
                  select_pid(dialA,dialB,duration,start_date,start_time,external_charges);
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
    
     
          public int select_pid(String dialA,String dialB, int duration,String date , String time,float external) {
              
              int pid = 0;

        
         try {
          connect();
          
          System.out.println(dialA);
                    SQLcommand= "select * from customer_profile where msisdn =?";
                    ps= connection.prepareStatement(SQLcommand);
                    ps.setString(1, dialA);
                    rs = ps.executeQuery();
                 
                    while (rs.next()) {
                        System.out.println("I'm in");
                        pid = rs.getInt("pid");
                        System.out.println(pid); 
                        
                        calculate_fees(dialA, dialB, pid,duration,date ,time,external);
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
    
     
     
     
      public float calculate_fees(String dialA,String dialB,int pid,int duration,String date , String time,float external) {
    
        float local_fees_same = 0;
        float local_fees_diff = 0;
        float fees_international = 0;
        float cost = 0;
        int round = 0;
        
         try {
          connect();
          SQLcommand = "select * from profile_services where pid =?";
                        ps = connection.prepareStatement(SQLcommand);
                        ps.setInt(1, pid);
//                        ps.setInt(1, sid);
                        rs = ps.executeQuery();
//                         System.out.println(rs.next());

                        while (rs.next()) {
                            local_fees_same = rs.getFloat("fees_local_same");
                            local_fees_diff = rs.getFloat("fees_local_diff");
                            fees_international = rs.getFloat("fees_international");
                            round = rs.getInt("round_amount");
                            System.out.println(round);
                            System.out.println(local_fees_diff);
                            System.out.println(local_fees_same);
                        }

                    

//                    System.out.println(dialA.substring(0, 2));
                    if ((dialA.substring(0, 3)).equals(dialB.substring(0, 3))) {
                        cost = (duration/round) * local_fees_same;
//                        System.out.println(cost);
                    } else if ((dialA.substring(0,2)).equals(dialB.substring(0,2))) {

                        cost = (duration/round) * local_fees_diff;
                        System.out.println(cost);
                    } else if ((dialB.substring(0, 3)).equalsIgnoreCase("www") || (dialB.substring(0, 4).equalsIgnoreCase("http"))) {
                        cost = (duration/round) * local_fees_same;
//                     System.out.println(cost);
                    } else {
                        cost = duration * fees_international;
//                     System.out.println(cost);
                    }
                    
                    insert_into_udr(pid, dialA, dialB, pid, duration, date, time, external, cost);
          
        
         }
        catch (Exception e) {
            e.printStackTrace();
            disconnect();
          cost=0;
        } 
        finally {

            return cost;
        }
    
     }
      
          public int insert_into_udr(int pid, String dialA, String dialB, int sid, int duration, String date, String time, float external, float cost) {
        try {
            connect();

            System.out.println(pid);
            System.out.println(dialA);
            System.out.println(dialB);
            System.out.println(sid);
            System.out.println(duration);
            System.out.println(date);
            System.out.println(time);
            System.out.println(external);
            System.out.println(cost);

            SQLcommand = "insert into udr (pid,dialA,dialB,sid,duration_msg_volume,start_date,start_time,external_charges,cost,is_billed) values (?,?,?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(SQLcommand);
            ps.setInt(1, pid);
            ps.setString(2, dialA);
            ps.setString(3, dialB);
            ps.setInt(4, sid);
            ps.setInt(5, duration);
            ps.setString(6, date);
            ps.setString(7, time);
            ps.setFloat(8, external);
            ps.setFloat(9, cost);
            ps.setBoolean(10, false);

            inserted = ps.executeUpdate();

        } catch (Exception e) {

        }

        return inserted;
    }
    
    
    
      public static void main(String[] args) {

                billing2 rate = new billing2();
                rate.select_cdr();
               
      }
    
}
