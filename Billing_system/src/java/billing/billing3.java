
package billing;

import database_tables.CDR;
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
public class billing3 {
    
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
          
                            select_pid(cdr);

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
                        
                        calculate_fees(pid ,cdr.getAllCDRS().elementAt(i));
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
    
     
     
     
      public float calculate_fees(int pid,CDR cdr_oneUser) {
    
        float local_fees_same = 0;
        float local_fees_diff = 0;
        float fees_international = 0;
        float cost = 0;
        int round = 0;
        
         try {
          connect();
          SQLcommand = "select * from profile_services where pid =? and sid=?";
                        ps = connection.prepareStatement(SQLcommand);
                        ps.setInt(1, pid);
                        ps.setInt(2, cdr_oneUser.getSid());
                        rs = ps.executeQuery();

                        while (rs.next()) {
                            local_fees_same = rs.getFloat("fees_local_same");
                            local_fees_diff = rs.getFloat("fees_local_diff");
                            fees_international = rs.getFloat("fees_international");
                            round = rs.getInt("round_amount");
                            System.out.println(round);
                            System.out.println(local_fees_diff);
                            System.out.println(local_fees_same);
                        }

                    

                    if ((cdr_oneUser.getDiala().substring(0, 3)).equals(cdr_oneUser.getDialb().substring(0, 3))) {
                        cost = (cdr_oneUser.getDuration_msg_volume()/round) * local_fees_same;
//                        System.out.println(cost);
                    } else if ((cdr_oneUser.getDiala().substring(0,2)).equals(cdr_oneUser.getDialb().substring(0,2))) {

                        cost = (cdr_oneUser.getDuration_msg_volume()/round) * local_fees_diff;
                        System.out.println(cost);
                    } else if ((cdr_oneUser.getDialb().substring(0, 3)).equalsIgnoreCase("www") || (cdr_oneUser.getDialb().substring(0, 4).equalsIgnoreCase("http"))) {
                        cost = (cdr_oneUser.getDuration_msg_volume()/round) * local_fees_same;
//                     System.out.println(cost);
                    } else {
                        cost = cdr_oneUser.getDuration_msg_volume() * fees_international;
//                     System.out.println(cost);
                    }
                    
                    insert_into_udr(pid,cdr_oneUser, cost);
          
        
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
      
          public int insert_into_udr(int pid,CDR cdr_one, float cost) {
        try {
            connect();

            System.out.println(pid);
            System.out.println(cdr_one.getDiala());
            System.out.println(cdr_one.getDialb());
            System.out.println(cdr_one.getSid());
            System.out.println(cdr_one.getDuration_msg_volume());
            System.out.println( cdr_one.getStart_date());
            System.out.println( cdr_one.getStart_time());
            System.out.println(cdr_one.getExternal_charges());
            System.out.println(cost);

            SQLcommand = "insert into udr (pid,dialA,dialB,sid,duration_msg_volume,start_date,start_time,external_charges,cost,is_billed) values (?,?,?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(SQLcommand);
            ps.setInt(1, pid);
            ps.setString(2, cdr_one.getDiala());
            ps.setString(3, cdr_one.getDialb());
            ps.setInt(4, cdr_one.getSid());
            ps.setInt(5, cdr_one.getDuration_msg_volume());
            ps.setString(6, cdr_one.getStart_date());
            ps.setString(7, cdr_one.getStart_time());
            ps.setFloat(8, cdr_one.getExternal_charges());
            ps.setFloat(9, cost);
            ps.setBoolean(10, false);
            inserted = ps.executeUpdate();
            
            update_cdr(cdr_one);

        } catch (Exception e) {

        }

        return inserted;
    }
    
          
          public int update_cdr(CDR cdr)
          {
              try{
                  connect();
                  SQLcommand="update cdr set is_rated=true where cdr_id=?";
                  ps=connection.prepareStatement(SQLcommand);
                  ps.setInt(1, cdr.getCdr_id());
                  inserted=ps.executeUpdate();
          
           } catch (Exception e) {
               inserted=0;

        }

        return inserted;
          
          }
          
    
    
      public static void main(String[] args) {

                billing3 rate = new billing3();
                rate.select_cdr();
               
      }
    
}
