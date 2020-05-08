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
    private final String password = "ahmed";

    private Connection connection;
    private String sqlcommand;
    private PreparedStatement preparedstatement;
    private ResultSet result;
    Boolean state = false;
    String privilage;

    private void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to Database Succeeded");
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("databaseconnection.dataBase.connect()error");
            Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FreeUnit ProfileFU(FreeUnit freeunitObj) {
        connect();
        try {
            sqlcommand = "SELECT * FROM  profileFreeUnits(?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1, freeunitObj.getProfileID());
            result = preparedstatement.executeQuery();
            FreeUnit fuObject = new FreeUnit();
            while (result.next()) {
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
        } finally {
            stop();
        }
    }

    public CustomerProfile RemainedFreeUnits(CustomerProfile pCustomer) {
        connect();

        CustomerProfile customerRemainedFUs = new CustomerProfile();
        try {
            sqlcommand = "SELECT * FROM customerRemainedFreeUnits(?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1, pCustomer.getProfileID());
            preparedstatement.setString(2, pCustomer.getMSISDN());
            result = preparedstatement.executeQuery();

            while (result.next()) {
                customerRemainedFUs.setMSISDN(result.getString(1));
                customerRemainedFUs.setProfileID(result.getInt(2));
                customerRemainedFUs.setStartDateOfContract(result.getString(3));
                customerRemainedFUs.setEndDateOfContract(result.getString(4));
                customerRemainedFUs.setBlockedServices(result.getString(5));
                customerRemainedFUs.setFUVoiceOnNet(result.getInt(6));
                customerRemainedFUs.setFUVoiceCrossNet(result.getInt(7));
                customerRemainedFUs.setFUSMSOnNet(result.getInt(8));
                customerRemainedFUs.setFUSMSCrossNet(result.getInt(9));
                customerRemainedFUs.setFUInternet(result.getInt(10));

            }
            System.out.println("Database retrievl:" + customerRemainedFUs.getFUVoiceCrossNet());
            System.out.println("Remained data of customer free Units returned successed");
            return customerRemainedFUs;
        } catch (SQLException ex) {
            System.out.println("Something Wrong happened");
            Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            stop();
        }
    }

    public Vector<UDR> customerUDRs(UDR udr) {
        connect();
        Vector<UDR> retrievedUDRs = new Vector();
        UDR udrRow;
        try {
            sqlcommand = "SELECT udr_id,pid,dialA,dialB,sid,duration_msg_volume,\n"
                    + "	( start_date || ' ' || start_time)::timestamp,external_charges,\n"
                    + "	has_freeUnits,cost,is_billed FROM udr \n"
                    + "	WHERE dialA=? AND pid=? AND sid=? ORDER BY timestamp";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setString(1, udr.getDialA());
            preparedstatement.setInt(2, udr.getProfileID());
            preparedstatement.setInt(3, udr.getServiceID());
            result = preparedstatement.executeQuery();

            while (result.next()) {
                udrRow = new UDR(result.getInt(1), result.getInt(2), result.getString(3),
                        result.getString(4), result.getInt(5), result.getInt(6),
                        result.getTimestamp(7), result.getFloat(8), result.getBoolean(9),
                        result.getFloat(10), result.getBoolean(11));
                retrievedUDRs.add(udrRow);
            }
            System.out.println("Successed##############");
            return retrievedUDRs;

        } catch (SQLException ex) {
            System.out.println("Error #########################");
            Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            stop();
        }
    }

    //UDRRow Function is Not Used Until Now
    public UDR UDRRow(UDR udr) {
        connect();
        UDR udrObj = new UDR();
        try {
            sqlcommand = "SELECT * FROM udrRow(?);";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1, udr.getUdrID());
            result = preparedstatement.executeQuery();

            while (result.next()) {
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

        } finally {

            stop();
            return udrObj;
        }

    }

    public ProfileService retrieveProfileService(ProfileService prof_service) {
        connect();

        ProfileService profileServiceRow = new ProfileService();
        try {
            sqlcommand = "SELECT * FROM retrieveProfileServices(?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1, prof_service.getProfileID());
            preparedstatement.setInt(2, prof_service.getServiceID());
            result = preparedstatement.executeQuery();

            while (result.next()) {
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

        } finally {
            stop();
        }
    }

    public Boolean UpdateCustomerFUs(CustomerProfile custRemainedFUs, String netConnection) {

        connect();

        try {
            sqlcommand = "SELECT updateCustomerFUs(?,?,?,?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setString(1, custRemainedFUs.getMSISDN());
            preparedstatement.setInt(2, custRemainedFUs.getProfileID());
            preparedstatement.setInt(3, custRemainedFUs.getServiceID());
            preparedstatement.setInt(4, custRemainedFUs.getConsumedQuantity());
            System.out.println("Database consumedQuentity" + custRemainedFUs.getConsumedQuantity());
            preparedstatement.setString(5, netConnection);
            result = preparedstatement.executeQuery();

            while (result.next()) {
                state = result.getBoolean(1);
            }
            System.out.println("Updated Database function:" + state);
            System.out.println("Successfully updating record ");
            return state;

        } catch (SQLException ex) {

            System.out.println("sorry, record can not be updated !");
            Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            stop();
        }
    }

    public Vector<UDR> retrieveAllCustomersHaveUDRs() {

        connect();
        Vector<UDR> customersDialNums = new Vector();
        UDR udrDialNum;
        try {
            sqlcommand = "SELECT * FROM retrieveAllCustomersHaveUDRs()";
            preparedstatement = connection.prepareStatement(sqlcommand);
            result = preparedstatement.executeQuery();

            while (result.next()) {
                udrDialNum = new UDR(result.getString(1), result.getInt(2));
                customersDialNums.add(udrDialNum);
            }

            System.out.println("Customer's Dial Num retrieved successfully");
            return customersDialNums;

        } catch (SQLException ex) {
            System.out.println("Something Wrong happened");
            Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            stop();
        }
    }

    public Profile retieveProfileDetails(Profile profile) {

        connect();

        Profile profileDetails = new Profile();
        try {
            sqlcommand = "SELECT * FROM retieveProfileDetails(?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1, profile.getProfileID());
            result = preparedstatement.executeQuery();

            while (result.next()) {
                profileDetails.setProfileID(result.getInt(1));
                profileDetails.setProfileName(result.getString(2));
                profileDetails.setRenewDuration(result.getInt(3));
                profileDetails.setProfileFees(result.getFloat(4));
            }

            System.out.println("Profile Details retrieved successfully");
            return profileDetails;

        } catch (SQLException ex) {

            System.out.println("Profile Details could not be retrieved !!!!");
            Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            stop();
        }

    }

    public Vector<OCC> getAllOcc(UDR udr) throws SQLException {
        connect();
        Vector<OCC> retrievedOccs = new Vector();
        OCC occ;
        String sql = "select * from occ where msisdn= ? and is_service_processed=false";
        preparedstatement = connection.prepareStatement(sql);
        preparedstatement.setString(1, udr.getDialA());
       result= preparedstatement.executeQuery();
        while(result.next()){
            occ= new OCC(result.getInt(1),result.getString(2),result.getInt(3),
            result.getString(4),result.getBoolean(5),result.getDate(6));
            retrievedOccs.add(occ);
        
        
        }
        return retrievedOccs;
    }
    
    public double getRecurringFees(OCC occ) throws SQLException{
        connect();
        double recurringfee=0;
        String sql = "select recurring_fees from services where sid = ? ";
        preparedstatement = connection.prepareStatement(sql);
        preparedstatement.setInt(1, occ.one_rec_id);
        result = preparedstatement.executeQuery();
        while(result.next()){
            recurringfee=result.getDouble(1);
        }
        return recurringfee;
    }
    public double getOneTimeFees(OCC occ) throws SQLException{
        connect();
        double onetimefee=0;
        String sql = "select osfee from one_time_service where one_time_service_id = ? ";
        preparedstatement = connection.prepareStatement(sql);
        preparedstatement.setInt(1, occ.one_rec_id);
        result = preparedstatement.executeQuery();
        while(result.next()){
            onetimefee=result.getDouble(1);
        }
        return onetimefee;
    }

    private void stop() {
        try {
            connection.close();
            System.out.println("Database Closed");
        } catch (SQLException ex) {
            System.out.println("databaseconnection.database.stop()error");
            Logger.getLogger(databaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws SQLException {
        databaseConnection db = new databaseConnection();
        

//        FreeUnit fu =db.ProfileFU(new FreeUnit(1)); 
//        System.out.println("##########"+ fu.getFUSMSCrossNet() + "#############");
//        CustomerProfile cFreeunits = new CustomerProfile(1,"01215860927");
//        CustomerProfile cPFreeUnits=db.RemainedFreeUnits(cFreeunits);
//        System.out.println("###########"+cPFreeUnits.getEndDateOfContract()+"###############");
 //       UDR udr=new UDR("01221234567",1,4);
//        Vector<UDR> udrList=db.customerUDRs(udr);
//        for(UDR udr2 : udrList)
//        {
//            System.out.println("#######"+ udr2.getOrderedDate()+"#########" + udr2.getDurationMsgVolume());
//        }
//        UDR udr=db.UDRRow(new UDR(3));
//        System.out.println("#####"+ udr.getDialB()+ "######" + udr.getDurationMsgVolume());
///////////////////////////////////Test////////////////////////////////////////////////////////////
//        Boolean state = db.UpdateCustomerFUs(new CustomerProfile("00201215860927",1,1,50),"crossNet");
//        System.out.println("##########################"+state);
//        
//        CustomerProfile cFreeunits = new CustomerProfile(1,"00201215860927");
//        CustomerProfile cPFreeUnits=db.RemainedFreeUnits(cFreeunits);
//        System.out.println("###########"+cPFreeUnits.getFUVoiceCrossNet()+"###############");
//        
////        databaseConnection db2 = new databaseConnection();
//        
//        state = db.UpdateCustomerFUs(new CustomerProfile("00201215860927",1,1,20),"crossNet");
//        System.out.println("##########################"+state);
//        
//        cPFreeUnits=db.RemainedFreeUnits(cFreeunits);
//        System.out.println("###########"+cPFreeUnits.getFUVoiceCrossNet()+"###############");
        /////////////////////////////////////////////////////////////////////////
//        ProfileService pservice = db.retrieveProfileService(new ProfileService(1, 4));
//        System.out.println("#####"+ pservice.getProfileServiceID() + "#######"+ pservice.getFeeSameOperator());
//        Vector<UDR> udrs = db.retrieveAllCustomersHaveUDRs();
//        for(UDR udr:udrs){
//            System.out.println("#######"+ udr.getDialA() +"#######" +udr.getProfileID());
//        }
//        Profile p = db.retieveProfileDetails(new Profile(1));
//        System.out.println("#####" + p.getProfileName() + "######" + p.getProfileFees());
   
        UDR udr= new UDR("01221234567", 1);
        Vector<OCC> occs =db.getAllOcc(udr);
        for(OCC occ : occs){
            System.out.println(occ.occ_id+""+occ.msisdn+""+occ.type_of_service +" "+occ.one_rec_id);
        
        }
        
    }
    
    
    
    

}
