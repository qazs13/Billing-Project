package Database;

import Database_Tables.*;
import java.sql.*;

public class Database {

     private final String url = "jdbc:postgresql://localhost:5432/billing_system";
    private final String user = "postgres";
    private final String password = "amrwsk13";


    private Connection connection = null;
    private PreparedStatement preparedStatment = null;
    private ResultSet result = null;
    private String sqlCommand;

    boolean operation = false;

    private void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is made successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean checkLogin(Admin_User admin)
    {
        try
        {
            connect();
            sqlCommand = "SELECT checklogin (?,?)";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setString(1, admin.getAdminUserName());
            preparedStatment.setString(2, admin.getAdminPassword());
            result = preparedStatment.executeQuery();
            while (result.next())
            {
                operation = result.getBoolean(1);
            }
        }
        catch (SQLException ex)
        {
            operation = false;
            ex.printStackTrace();
        }
        finally
        {
            stop();
            return operation;
        }
    }
    
    public boolean addService (Services service)
    {
        try
        {
            connect();
            sqlCommand = "INSERT INTO services (sname,is_recurring,recurring_fees) VALUES (?,?,?);";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setString(1, service.getSname());
            preparedStatment.setBoolean(2, service.getIsRecurring());
            preparedStatment.setFloat(3, service.getServiceFees());
            preparedStatment.execute();
            operation = true;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            operation = false;
        }
        finally
        {
            stop();
            return operation;
        }
    }
    
    public boolean addOneTimeService (Services service)
    {
        try
        {
            connect();
            sqlCommand = "INSERT INTO  one_time_service (osname,osfee) VALUES (?,?);";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setString(1, service.getSname());
            preparedStatment.setFloat(2, service.getServiceFees());
            preparedStatment.execute();
            operation = true;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            operation = false;
        }
        finally
        {
            stop();
            return operation;
        }
    }    
    
    public Services getAllServices()
    {
        Services allServices = new Services();
        try
        {
            connect();
            sqlCommand = "SELECT * FROM services";
            preparedStatment = connection.prepareStatement(sqlCommand);
            result = preparedStatment.executeQuery();
            while (result.next())
            {
                allServices.getAllServices().add(new Services(result.getInt(1), result.getString(2),
                        result.getBoolean(3),result.getFloat(4)));
            }
            allServices = getOneTimeServices(allServices);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            stop();
            return allServices;
        }
    }
    
    public Services getAllNotOneTimeServices()
    {
        Services allServices = new Services();
        try
        {
            connect();
            sqlCommand = "SELECT * FROM services";
            preparedStatment = connection.prepareStatement(sqlCommand);
            result = preparedStatment.executeQuery();
            while (result.next())
            {
                allServices.getAllServices().add(new Services(result.getInt(1), result.getString(2),
                        result.getBoolean(3),result.getFloat(4)));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            stop();
            return allServices;
        }
    }    
    
    private Services getOneTimeServices (Services allServices)
    {
        try
        {
            connect();
            sqlCommand = "SELECT * FROM one_time_service";
            preparedStatment = connection.prepareStatement(sqlCommand);
            result = preparedStatment.executeQuery();
            while (result.next())
            {
                allServices.getAllServices().add(new Services(result.getInt(1), result.getString(2),
                        true,result.getFloat(3),null));
            }            
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            return allServices;
        }
    }
    
    public int getServiceIDByItsName (String serviceName)
    {
        int serviceNumber = 0;
        try
        {
            connect();
            sqlCommand = "SELECT sid FROM services WHERE sname = ?";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setString(1, serviceName);
            result = preparedStatment.executeQuery();
            while (result.next())
            {
                serviceNumber = result.getInt(1);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            stop();
            return serviceNumber;
        }
    }
    
    public boolean checkProfileExistance (Profile profile)
    {
        try
        {
            int numberOfProfiles = 0;
            connect();
            sqlCommand = "SELECT COUNT (*) FROM profile WHERE pname = ?";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setString(1, profile.getpName());
            result = preparedStatment.executeQuery();
            while (result.next())
            {
                numberOfProfiles = result.getInt(1);
            }
            if (numberOfProfiles > 0)
            {
                operation = true;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            stop();
            return operation;
        }
    }
    
    public boolean addNewProfile (Profile profile)
    {
        try
        {
            connect();
            sqlCommand = "INSERT INTO profile (pname,renew_duration,pfees) VALUES (?,?,?)";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setString(1, profile.getpName());
            preparedStatment.setInt(2, profile.getRenew_Duration());
            preparedStatment.setFloat(3, profile.getRenew_Duration());
            preparedStatment.execute();
            operation = true;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            operation = false;
        }
        finally
        {
            stop();
            return operation;
        }
    }
    
    public int getProfileID (String profileName)
    {
        int profileID = 0;
        try
        {
            connect();
            sqlCommand = "SELECT pid from PROFILE WHERE pname = ?";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setString(1, profileName);
            result = preparedStatment.executeQuery();
            while (result.next())
            {
                profileID = result.getInt(1);
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            stop();
            return profileID;
        }
    }
    
    public int addProfileServices (Profile_Services profile_Services)
    {
        int operatio = 0;
        try
        {
            connect();
            sqlCommand = "INSERT INTO profile_services (pid, sid, round_amount, fees_local_same, fees_local_diff, fees_international)"
                    + "VALUES (?,?,?,?,?,?)";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setInt(1, profile_Services.getPid());
            preparedStatment.setInt(2, profile_Services.getSid());
            preparedStatment.setInt(3, profile_Services.getRound_amount());
            preparedStatment.setFloat(4, profile_Services.getFees_local_same());
            preparedStatment.setFloat(5, profile_Services.getFees_local_diff());
            preparedStatment.setFloat(6, profile_Services.getFees_international());
            preparedStatment.execute();
            operatio = 1;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            operatio = 0;
        }
        finally
        {
            stop();
            return operatio;
        }
    }
    
    public boolean addFreeUnitsToProfile (Free_Units free_Units)
    {
        try
        {
            connect();
            sqlCommand = "INSERT INTO free_units (free_voice_same,free_voice_diff,free_sms_same,free_sms_diff,free_internet,pid)"
                    + "VALUES (?,?,?,?,?,?)";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setFloat(1, free_Units.getFree_voice_same());
            preparedStatment.setFloat(2, free_Units.getFree_voice_diff());
            preparedStatment.setFloat(3, free_Units.getFree_sms_same());
            preparedStatment.setFloat(4, free_Units.getFree_sms_diff());
            preparedStatment.setFloat(5, free_Units.getFree_internet());
            preparedStatment.setInt(6, free_Units.getPid());
            preparedStatment.execute();
            operation = true;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            operation = false;
        }
        finally
        {
            stop();
            return operation;
        }
    }
        
    private void stop()
    {
        try 
        {
            connection.close();
            System.out.println("Database is stopped");
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}