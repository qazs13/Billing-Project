/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author ramr
 */
import Database_Tables.CDR;
import Database_Tables.Customer_Profile;
import Database_Tables.Profile;
import Database_Tables.OCC;
import Database_Tables.Services;
import Database_Tables.One_Time_Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Database1 {

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
            s = connection.createStatement();
            rs = s.executeQuery(SQLcommand);
            while (rs.next()) {
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

        } catch (Exception e) {
            e.printStackTrace();
            disconnect();
        } finally {

            return cdr;
        }

    }

    public int select_pid(CDR cdr) {

        int pid = 0;

        try {
            connect();

            if (cdr.getAllCDRS().size() > 0) {
                for (int i = 0; i < cdr.getAllCDRS().size(); i++) {
                    System.out.println(cdr.getAllCDRS().elementAt(i).getDiala());
                    SQLcommand = "select * from customer_profile where msisdn =? ";
                    ps = connection.prepareStatement(SQLcommand);
                    ps.setString(1, cdr.getAllCDRS().elementAt(i).getDiala());
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        System.out.println("I'm in");
                        pid = rs.getInt("pid");
                        System.out.println(pid);

                        float cost = calculate_fees(pid, cdr.getAllCDRS().elementAt(i));
                    }
                }
            }
            inserted = 1;
        } catch (Exception e) {
            e.printStackTrace();
            disconnect();
            inserted = 0;
        } finally {

            return pid;
        }

    }

    public float calculate_fees(int pid, CDR cdr_oneUser) {

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
                cost = (cdr_oneUser.getDuration_msg_volume() / round) * local_fees_same;
//                        System.out.println(cost);
            } else if ((cdr_oneUser.getDiala().substring(0, 2)).equals(cdr_oneUser.getDialb().substring(0, 2))) {

                cost = (cdr_oneUser.getDuration_msg_volume() / round) * local_fees_diff;
                System.out.println(cost);
            } else if ((cdr_oneUser.getDialb().substring(0, 3)).equalsIgnoreCase("www") || (cdr_oneUser.getDialb().substring(0, 4).equalsIgnoreCase("http"))) {
                cost = (cdr_oneUser.getDuration_msg_volume() / round) * local_fees_same;
//                     System.out.println(cost);
            } else {
                cost = cdr_oneUser.getDuration_msg_volume() * fees_international;
//                     System.out.println(cost);
            }

        } catch (Exception e) {
            e.printStackTrace();
            disconnect();
            cost = 0;
        } finally {

            return cost;
        }

    }

    public CDR insert_into_udr(int pid, CDR cdr_one, float cost) {
        try {
            connect();

            System.out.println(pid);
            System.out.println(cdr_one.getDiala());
            System.out.println(cdr_one.getDialb());
            System.out.println(cdr_one.getSid());
            System.out.println(cdr_one.getDuration_msg_volume());
            System.out.println(cdr_one.getStart_date());
            System.out.println(cdr_one.getStart_time());
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

        } catch (Exception e) {

        }

        return cdr_one;
    }

    public int update_cdr(CDR cdr) {
        try {
            connect();
            SQLcommand = "update cdr set is_rated=true where cdr_id=?";
            ps = connection.prepareStatement(SQLcommand);
            ps.setInt(1, cdr.getCdr_id());
            inserted = ps.executeUpdate();

        } catch (Exception e) {
            inserted = 0;

        }

        return inserted;

    }

    public Vector<String[]> search(String key) {

        Vector<String[]> customers = new Vector();

        try {
            connect();
            SQLcommand = "select * from customer where msisdn ILIKE ? or f_name ILIKE ? or l_name ILIKE ?;";
            ps = connection.prepareStatement(SQLcommand);
            ps.setString(1, "%" +key+ "%");
            ps.setString(2, "%" +key+ "%");
            ps.setString(3, "%" +key+ "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                customers.add(new String[]{rs.getString("msisdn"), rs.getString("f_name"), rs.getString("l_name"), rs.getString("email"), rs.getString("address")});
            }

        } catch (Exception e) {
            e.printStackTrace();
            disconnect();
        } finally {

            return customers;
        }

    }
    
     public Vector<Profile> select_from_profile(int pid) {

        Vector<Profile> profile = new Vector();

        try {
            connect();
            SQLcommand = "select * from profile where pid=?;";
            ps = connection.prepareStatement(SQLcommand);
            ps.setInt(1, pid);
          
            rs = ps.executeQuery();
            while (rs.next()) {
                profile.add(new Profile(rs.getInt("pid"), rs.getString("pname"), rs.getInt("renew_duration"), rs.getFloat("pfees")));
            }

        } catch (Exception e) {
            e.printStackTrace();
            disconnect();
        } finally {

            return profile;
        }

    }



     public Vector<Customer_Profile> select_from_customer_profile(String key) {

        Vector<Customer_Profile> c = new Vector();

        try {
            connect();
            SQLcommand = "select * from customer_profile where msisdn ILIKE ? ;";
            ps = connection.prepareStatement(SQLcommand);
            ps.setString(1, "%" +key+ "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                     c.add(new Customer_Profile(rs.getString("msisdn"),
                              rs.getInt("pid"),
                              rs.getString("start_date"),
                              rs.getString("end_date"),
                              rs.getInt("blocked_services"),
                              rs.getFloat("free_voice_same"),
                              rs.getFloat("free_voice_diff"),
                              rs.getFloat("free_sms_same"),
                              rs.getFloat("free_sms_diff"),
                              rs.getFloat("free_internet")));
                              
                      
            }
            
            

        } catch (Exception e) {
            e.printStackTrace();
            disconnect();
        } finally {

            return c;
        }

    }
     
        public Vector<OCC> select_from_occ(String msisdn) {

        Vector<OCC> occ = new Vector();

        try {
            connect();
            SQLcommand = "select * from occ where msisdn =?";
            ps = connection.prepareStatement(SQLcommand);
            ps.setString(1, msisdn);
          
            rs = ps.executeQuery();
            while (rs.next()) {
                occ.add(new OCC(rs.getString("msisdn"),rs.getInt("one_recurring_id"),rs.getString("type_of_service"),rs.getDate("service_processed_date")));
            }

        } catch (Exception e) {
            e.printStackTrace();
            disconnect();
        } finally {

            return occ;
        } }
        
         public Vector<Services> select_from_services(int sid) {

        Vector<Services> service = new Vector();

        try {
            connect();
            SQLcommand = "select * from services where sid =?";
            ps = connection.prepareStatement(SQLcommand);
            ps.setInt(1, sid);
          
            rs = ps.executeQuery();
            while (rs.next()) {
                service.add(new Services(rs.getString("sname"),rs.getBoolean("isRecurring"),rs.getFloat("serviceFees")));
            }

        } catch (Exception e) {
            e.printStackTrace();
            disconnect();
        } finally {

            return service;
        }

    }
         
                public Vector<One_Time_Service> select_from_one_time(int sid) {

        Vector<One_Time_Service> oservice = new Vector();

        try {
            connect();
            SQLcommand = "select * from one_time_service where one_time_service_id =?";
            ps = connection.prepareStatement(SQLcommand);
            ps.setInt(1, sid);
            rs = ps.executeQuery();
            while (rs.next()) {
                oservice.add(new One_Time_Service(rs.getString("osname"),rs.getFloat("osfee")));
            }

        } catch (Exception e) {
            e.printStackTrace();
            disconnect();
        } finally {

            return oservice;
        }
                }



}
