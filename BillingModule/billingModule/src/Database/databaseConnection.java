package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import SystemObjects.*;
import Interfaces.NetConnection;
import java.util.Vector;


public class databaseConnection {
    
    private final String url = "jdbc:postgresql://localhost:5432/billing_system";
    private final String user = "postgres";
    private final String password = "postgres";
     
    private Connection connection;
    private String sqlcommand;
    private PreparedStatement preparedstatement;
    private ResultSet result;
    Boolean state =false;
    String privilage;
    
    
    private void connect(){        
        try {         
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to Database Succeeded");           
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("databaseconnection.dataBase.connect()error");
            Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    public FreeUnit ProfileFU(FreeUnit freeunitObj){
        connect();       
        try {
            sqlcommand = "SELECT * FROM  profileFreeUnits(?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1, freeunitObj.getProfileID());
            result = preparedstatement.executeQuery();
            FreeUnit fuObject=new FreeUnit();
            while(result.next()){
                fuObject.setFUID(result.getInt(1));
                fuObject.setFUVoiceOnNet(result.getInt(2));
                fuObject.setFUVoiceCrossNet(result.getInt(3));
                fuObject.setFUSMSOnNet(result.getInt(4));
                fuObject.setFUSMSCrossNet(result.getInt(5));
                fuObject.setFUInternet(result.getInt(6));
                fuObject.setProfileID(result.getInt(7));
            }
            System.out.println("Data retrieved successfully");
            return fuObject;           
        } catch (SQLException ex) {
            System.out.println("Something wrong happened while retrieving FreeUnit info");
            Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
             stop();    
        }
    }
    
    public CustomerProfile RemainedFreeUnits(CustomerProfile pCustomer){
        connect();
        
        CustomerProfile customerRemainedFUs = new CustomerProfile();
        try {
            sqlcommand = "SELECT * FROM customerRemainedFreeUnits(?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1,pCustomer.getProfileID());
            preparedstatement.setString(2,pCustomer.getMSISDN());
            result = preparedstatement.executeQuery();
            
            while(result.next()){
                customerRemainedFUs.setMSISDN(result.getString(1));
                customerRemainedFUs.setProfileID(result.getInt(2));
                customerRemainedFUs.setStartDateOfContract(result.getString(3));
                customerRemainedFUs.setEndDateOfContract(result.getString(4));
                customerRemainedFUs.setFUVoiceOnNet(result.getInt(5));
                customerRemainedFUs.setFUVoiceCrossNet(result.getInt(6));
                customerRemainedFUs.setFUSMSOnNet(result.getInt(7));
                customerRemainedFUs.setFUSMSCrossNet(result.getInt(8));
                customerRemainedFUs.setFUInternet(result.getInt(9));                  
            }
            System.out.println("Remained data of customer free Units returned successed");
            return customerRemainedFUs;
        } catch (SQLException ex) {
            System.out.println("Something Wrong happened");
            Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
           stop(); 
        }  
    }
    
    public Vector<UDR> customerUDRs(UDR udr){
        connect();
        Vector<UDR> retrievedUDRs = new Vector();
        UDR udrRow;
        try {
            sqlcommand = "SELECT udr_id,pid,dialA,dialB,sid,duration_msg_volume,\n" +
                "	( start_date || ' ' || start_time)::timestamp,external_charges,\n" +
                "	has_freeUnits,cost,is_billed FROM udr \n" +
                "	WHERE dialA=? AND pid=? AND sid=? ORDER BY timestamp";    
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setString(1,udr.getDialA());
            preparedstatement.setInt(2,udr.getProfileID());
            preparedstatement.setInt(3,udr.getServiceID());
            result = preparedstatement.executeQuery();
            
            while(result.next()){
                udrRow=new UDR(result.getInt(1),result.getInt(2),result.getString(3),
                        result.getString(4),result.getInt(5),result.getInt(6),
                        result.getTimestamp(7),result.getFloat(8),result.getBoolean(9),
                result.getFloat(10),result.getBoolean(11));
                retrievedUDRs.add(udrRow);
            }
            System.out.println("Successed##############");
            return retrievedUDRs;
            
        } catch (SQLException ex) {
            System.out.println("Error #########################");
            Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            stop();
        } 
    }
    
    public UDR UDRRow(UDR udr){
        connect();
        UDR udrObj = new UDR();
        try {
            sqlcommand = "SELECT * FROM udrRow(?);";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1,udr.getUdrID());
            result = preparedstatement.executeQuery();
        
            while(result.next()){
                udrObj.setUdrID(result.getInt(1));
                udrObj.setProfileID(result.getInt(2));
                udrObj.setDialA(result.getString(3));
                udrObj.setDialB(result.getString(4));
                udrObj.setServiceID(result.getInt(5));
                udrObj.setDurationMsgVolume(result.getInt(6));
                udrObj.setStartDate(result.getString(7));
                udrObj.setStartTime(result.getString(8));
                udrObj.setExternalCharges(result.getFloat(9));
                udrObj.setHasFU(result.getBoolean(10));
                udrObj.setCost(result.getFloat(11));
                udrObj.setIsBilled(result.getBoolean(12));         
            }
            
            System.out.println("UDR Data retrieved successfully");
            
        } catch (SQLException ex) {
            
            System.out.println("Something happened wrong");
            Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            
            stop();
            return udrObj;
        }
      
        
    }
    
    public Boolean UpdateCustomerFUs(CustomerProfile custRemainedFUs,String netConnection){
        
        connect();
        
        try {
            sqlcommand = "SELECT updateCustomerFUs(?,?,?,?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setString(1,custRemainedFUs.getMSISDN());
            preparedstatement.setInt(2,custRemainedFUs.getProfileID());
            preparedstatement.setInt(3,custRemainedFUs.getServiceID());
            preparedstatement.setInt(4,custRemainedFUs.getConsumedQuantity());
            preparedstatement.setString(5,netConnection);
            result = preparedstatement.executeQuery();
            
            while(result.next()){
                state = result.getBoolean(1);
            }
            System.out.println("Successfully updating record ");
            return state;
            
        } catch (SQLException ex) {
            
            System.out.println("sorry, record can not be updated !");
            Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
            
        }finally{
            stop();
        }
    }
    
    public ProfileService retrieveProfileService(ProfileService prof_service){
        connect();
        
        ProfileService profileServiceRow = new ProfileService();
        try {
            sqlcommand = "SELECT * FROM retrieveProfileServices(?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1,prof_service.getProfileID());
            preparedstatement.setInt(2,prof_service.getServiceID());
            result = preparedstatement.executeQuery();
        
            while(result.next()){
                profileServiceRow.setProfileServiceID(result.getInt(1));
                profileServiceRow.setProfileID(result.getInt(2));
                profileServiceRow.setServiceID(result.getInt(3));
                profileServiceRow.setRoundAmount(result.getInt(4));
                profileServiceRow.setFeeSameOperator(result.getFloat(5));
                profileServiceRow.setFeeAnotherOperator(result.getFloat(6));
                profileServiceRow.setFeeInternationally(result.getFloat(7));   
            }
            System.out.println("Profile Service Information retrieved successfully");
            return profileServiceRow;
            
        } catch (SQLException ex) {
            
            System.out.println("Something happened wrong while retrieving profile's services");
            Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
            
        }finally{
            stop();
        }  
    }
    
    private void stop(){
        try {
            connection.close();
            System.out.println("Database Closed");
        } catch (SQLException ex) {
            System.out.println("databaseconnection.database.stop()error");
            Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
     
    public static void main(String [] args){
        databaseConnection db = new databaseConnection();
        
//        FreeUnit fu =db.ProfileFU(new FreeUnit(1)); 
//        System.out.println("##########"+ fu.getFUSMSCrossNet() + "#############");
        
//        CustomerProfile cFreeunits = new CustomerProfile(1,"01215860927");
//        CustomerProfile cPFreeUnits=db.RemainedFreeUnits(cFreeunits);
//        System.out.println("###########"+cPFreeUnits.getEndDateOfContract()+"###############");
        
//        UDR udr=new UDR("01215860927",1,4);
//        Vector<UDR> udrList=db.customerUDRs(udr);
//        for(UDR udr2 : udrList)
//        {
//            System.out.println("#######"+ udr2.getOrderedDate()+"#########" + udr2.getDurationMsgVolume());
//        }
        
//        UDR udr=db.UDRRow(new UDR(3));
//        System.out.println("#####"+ udr.getDialB()+ "######" + udr.getDurationMsgVolume());
        

//        Boolean state = db.UpdateCustomerFUs(new CustomerProfile("01215860927",1,2,300),"onNet");
//        System.out.println("##########################"+state);

//        ProfileService pservice = db.retrieveProfileService(new ProfileService(1, 4));
//        System.out.println("#####"+ pservice.getProfileServiceID() + "#######"+ pservice.getFeeSameOperator());



    }
    
    
    
}
