package Database;

import Database_Tables.CDR;
import Database_Tables.Profile_Services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Database 
{
    private final String url = "jdbc:postgresql://localhost:5432/billing_system";
    private final String user = "postgres";
    private final String password = "amrwsk13";

    public Connection connection = null;
    public String sqlCommand = null;
    public ResultSet result = null;
    public PreparedStatement preparedStatment = null;
    boolean operation = false;
    
    public void connect() 
    {
        try 
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is made successfully");
        }
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }
    
     public CDR getAllNotRadtedCDRs() 
     {
        CDR cdr = new CDR();
        try {
            connect();
            sqlCommand = "select * from cdr where is_rated=false";
            preparedStatment = connection.prepareStatement(sqlCommand);
            result = preparedStatment.executeQuery();
            while(result.next())
            {
                cdr.getAllCDRS().add(new CDR(result.getInt("cdr_id"),
                    result.getString("dialA"),
                    result.getString("dialB"),
                    result.getInt("sid"),
                    result.getInt("duration_msg_volume"),
                    result.getString("start_date"),
                    result.getString("start_time"),
                    result.getFloat("external_charges"),
                    result.getBoolean("is_rated")
                ));                             
            }
         } 
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            disconnect();
            return cdr;
        }
     }    
    
    public int select_pid(CDR cdr) 
    {
        int pid = 0;
        try 
        {
        connect();
            sqlCommand = "SELECT * FROM customer_profile WHERE msisdn = ?";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setString(1, cdr.getDiala());
            result = preparedStatment.executeQuery();

            while (result.next()) 
            {
                System.out.println("Checking in Profile id For Customer "+cdr.getDiala());
                pid = result.getInt(2);
            }                           
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            pid = 0;
        } 
        finally 
        {
            disconnect();
            return pid;
        }
    }
    
    public float calculate_fees(int pid, CDR cdr_oneUser) 
    {
        float cost = 0; 
        try
        {
            Profile_Services profile_Services = new Profile_Services(0, 0, 0, 0, 0);
            connect();
            sqlCommand = "SELECT * FROM profile_services WHERE pid = ? AND sid = ?";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setInt(1, pid);
            preparedStatment.setInt(2, cdr_oneUser.getSid());
            result = preparedStatment.executeQuery();

            while (result.next())
            {
                profile_Services.setSid(result.getInt("sid"));
                profile_Services.setRound_amount(result.getInt("round_amount"));
                profile_Services.setFees_local_same(result.getFloat("fees_local_same"));
                profile_Services.setFees_local_diff(result.getFloat("fees_local_diff"));
                profile_Services.setFees_international(result.getFloat("fees_international"));
            }
            
            cost = Calculate_Fees.Calculate_Fees_Of_Service(cdr_oneUser, profile_Services);
            
            System.out.println("*************************************************");                                   
            System.out.println("Checking Fees in Profile ID Number " + pid);
            System.out.println("Checking Fees of Service ID Number " + cdr_oneUser.getSid());
            System.out.println("Round Amount is : "+profile_Services.getRound_amount());
            System.out.println("Fees of Local Same Operator is : "+profile_Services.getFees_local_same());
            System.out.println("Fees of Local Different Operator is : "+profile_Services.getFees_local_diff());
            System.out.println("Fees of International is : "+profile_Services.getFees_international());  
            System.out.println("Total Cost is : "+cost); 
            System.out.println("*************************************************");              
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            disconnect();           
            return cost;
        }
    }    
    
    public boolean insert_into_udr(int pid, CDR cdr_one, float cost) 
    {
        try 
        {
            connect();
            sqlCommand = "INSERT INTO udr (pid,dialA,dialB,sid,duration_msg_volume,start_date,start_time,external_charges,"
                    + "has_freeunits,cost,is_billed)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setInt(1, pid);
            preparedStatment.setString(2, cdr_one.getDiala());
            preparedStatment.setString(3, cdr_one.getDialb());
            preparedStatment.setInt(4, cdr_one.getSid());
            preparedStatment.setInt(5, cdr_one.getDuration_msg_volume());
            preparedStatment.setString(6, cdr_one.getStart_date());
            preparedStatment.setString(7, cdr_one.getStart_time());
            preparedStatment.setFloat(8, cdr_one.getExternal_charges());
            preparedStatment.setBoolean(9, false);
            preparedStatment.setFloat(10, cost);
            preparedStatment.setBoolean(11, false);
            preparedStatment.execute();
            System.out.println("*************************************************");              
            System.out.println("Inserted Successfully INTO UDR Table");              
            System.out.println("*************************************************");                          
            operation = true;
        }
        catch (SQLException ex) 
        {
            ex.printStackTrace();
            operation = false;
        }
        finally
        {
            disconnect();
            return operation;
        }
    }
    
    public boolean update_cdr(CDR cdr)
    {
        try
        {
            connect();
            sqlCommand = "UPDATE cdr SET is_rated = true WHERE cdr_id = ?";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setInt(1, cdr.getCdr_id());
            preparedStatment.execute();
            System.out.println("*************************************************");              
            System.out.println("CDR ID "+cdr.getCdr_id()+" has been Updated");              
            System.out.println("*************************************************");                
            operation = true;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
            operation = false;
        }
        finally
        {
            disconnect();
            return operation;
        }
    }    
    
    public void disconnect()
    {
        try 
        {
            connection.close();
            preparedStatment.close();
            System.out.println("Connection is Disconnected");
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }    
}
