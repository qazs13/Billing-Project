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
            sqlCommand = "INSERT INTO onetimeservice (osname,osfees) VALUES (?,?);";
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
    
    private Services getOneTimeServices (Services allServices)
    {
        try
        {
            connect();
            sqlCommand = "SELECT * FROM oneTimeService";
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