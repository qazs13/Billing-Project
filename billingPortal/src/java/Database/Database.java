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
    
    public One_Time_Service getAllOneTimeServices ()
    {
        One_Time_Service one_time_service = new One_Time_Service();
        try
        {
            connect();
            sqlCommand = "SELECT * FROM one_time_service";
            preparedStatment = connection.prepareStatement(sqlCommand);
            result = preparedStatment.executeQuery();
            while (result.next())
            {
                one_time_service.getAllOneTimeServices().add(new One_Time_Service(result.getInt(1), result.getString(2)
                        , result.getFloat(3)));
            }            
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            return one_time_service;
        }
    }
    
    public Services getAllRecurringAndNotRecurringServices()
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
    
    public int getServiceIDByItsName (String normalOrRecurringServiceName)
    {
        int serviceNumber = 0;
        try
        {
            connect();
            sqlCommand = "SELECT sid FROM services WHERE sname = ?";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setString(1, normalOrRecurringServiceName);
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
    
    public int getOneTimeServiceIDByItsName (String oneTimeServiceName)
    {
        int serviceNumber = 0;
        try
        {
            connect();
            sqlCommand = "SELECT one_time_service_id FROM one_time_service WHERE osname = ?";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setString(1, oneTimeServiceName);
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
    
    public String getServiceNameByItsID (int serviceRecurringOrNormalID)
    {
        String serviceName = null;
        try
        {
            connect();
            sqlCommand = "SELECT sname FROM services WHERE sid = ?";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setInt(1, serviceRecurringOrNormalID);
            result = preparedStatment.executeQuery();
            while (result.next())
            {
                serviceName = result.getString(1);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            stop();
            return serviceName;
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
            preparedStatment.setFloat(3, profile.getpFees());
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
    
    public Profile getAllProfiles ()
    {
        Profile allProfiles = new Profile();
        try
        {
            connect();
            sqlCommand = "SELECT * FROM profile";
            preparedStatment = connection.prepareStatement(sqlCommand);
            result = preparedStatment.executeQuery();
            while (result.next())
            {
                allProfiles.getAllProfiles().add(new Profile(result.getInt(1), result.getString(2)
                        , result.getInt(3), result.getFloat(4)));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            stop();
            return allProfiles;
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
    
    public Profile getProfileByItsID (int profileID)
    {
        Profile allProfiles = null;
        try
        {
            connect();
            sqlCommand = "SELECT * FROM profile WHERE pid = ?";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setInt(1, profileID);
            result = preparedStatment.executeQuery();
            while (result.next())
            {
                allProfiles = new Profile(result.getString(2)
                        , result.getInt(3), result.getFloat(4));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            stop();
            return allProfiles;
        }        
    }
    
    public Profile_Services getProfileServicesByItsID (int profileID)
    {
        Profile_Services allProfileServices = new Profile_Services();
        try
        {
            connect();
            sqlCommand = "SELECT * FROM profile_services WHERE pid = ?";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setInt(1, profileID);
            result = preparedStatment.executeQuery();
            while (result.next())
            {
                allProfileServices.getAllProfileServices().add(new Profile_Services(result.getInt(3), result.getInt(4),
                        result.getFloat(5), result.getFloat(6), result.getFloat(7)));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            stop();
            return allProfileServices;
        }             
    }
    
    public Free_Units getFreeUnitOfProfileByItsID (int profileID)
    {
        Free_Units allProfileFreeUnit = null;
        try
        {
            connect();
            sqlCommand = "SELECT * FROM free_units WHERE pid = ?";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setInt(1, profileID);
            result = preparedStatment.executeQuery();
            while (result.next())
            {
                allProfileFreeUnit = new Free_Units(result.getFloat(2), result.getFloat(3),
                        result.getFloat(4), result.getFloat(5), result.getFloat(6));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            stop();
            return allProfileFreeUnit;
        }          
    }
    
    public boolean checkCustomerExistance (Customer customer)
    {
        try
        {
            int counter = 0;
            connect();
            sqlCommand = "SELECT COUNT (*) FROM customer WHERE msisdn = ?";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setString(1, customer.getMsisdn());
            result = preparedStatment.executeQuery();
            while (result.next())
            {
                counter = result.getInt(1);
            }
            if (counter == 1)
            {
                operation = true;
            }
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
    
    public boolean addCustomer (Customer customer)
    {
        try
        {
            connect();
            sqlCommand = "INSERT INTO customer VALUES (?,?,?,?,?)";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setString(1, customer.getMsisdn());
            preparedStatment.setString(2, customer.getF_name());
            preparedStatment.setString(3, customer.getL_name());
            preparedStatment.setString(4, customer.getEmail());
            preparedStatment.setString(5, customer.getAddress());
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
        
    public boolean addCustomerProfile (Customer_Profile customer_profile)
    {
        try
        {
            connect();
            sqlCommand = "INSERT INTO customer_profile VALUES (?,?,?,?,?,?,?,?,?,?)";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setString(1, customer_profile.getMsisdn());
            preparedStatment.setInt(2, customer_profile.getPid());
            preparedStatment.setString(3, customer_profile.getStart_date());
            preparedStatment.setString(4, customer_profile.getEnd_date());
            preparedStatment.setInt(5, customer_profile.getBlocked_services());
            preparedStatment.setFloat(6, customer_profile.getFree_voice_same());
            preparedStatment.setFloat(7, customer_profile.getFree_voice_diff());
            preparedStatment.setFloat(8, customer_profile.getFree_sms_same());
            preparedStatment.setFloat(9, customer_profile.getFree_sms_diff());
            preparedStatment.setFloat(10, customer_profile.getFree_internet());
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
    
    public boolean addOCCToCustomer (OCC occ)
    {
        int occID = 0;
        try
        {
            connect();
            sqlCommand = "INSERT INTO occ (msisdn,one_recurring_id,type_of_service,is_service_processed,service_processed_date)"
                    + "VALUES (?,?,?,?,?)";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setString(1, occ.getMsisdn());
            preparedStatment.setInt(2, occ.getOne_recurring_id());            
            preparedStatment.setString(3, occ.getType_of_service());
            preparedStatment.setBoolean(4, occ.getService_processed());
            preparedStatment.setDate(5,occ.getService_processed_date());
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