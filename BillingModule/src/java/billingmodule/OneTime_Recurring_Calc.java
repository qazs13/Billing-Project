package billingmodule;

import Database.databaseConnection;
import SystemObjects.*;
import java.sql.SQLException;
import java.util.Vector;
import Interfaces.*;
import java.sql.Date;
import java.text.SimpleDateFormat;


public class OneTime_Recurring_Calc {

        public OCC getOneTimeRecurringFee(UDR customerUDR, BillDateInterval intervalDate) throws SQLException {

            Float totalonetimecost =0f;
            Float totalrecurringcost =0f;
            OCC occTotalCostObject;

        
            databaseConnection db = new databaseConnection();
            Vector<OCC> alloccs = db.getAllOcc(customerUDR, intervalDate);

            if(alloccs.isEmpty()){

                System.out.println("Customer has no recurring/one-time Services");
                return new OCC(0f,0f);

            }else{

                for (OCC occ : alloccs){

                    System.out.println(occ.type_of_service);

                    if(occ.type_of_service.equals(CustomerServiceTypes.onetime)){

                            if( occ.one_rec_id== db.getonetimeid(occ))  { //onetime
                                totalonetimecost += db.getOneTimeFees(occ);
                                System.out.println(totalonetimecost);
                            }
                    }
                    else if(occ.type_of_service.equals(CustomerServiceTypes.recurring))  {

                            if(  occ.one_rec_id ==db.getRecurringid(occ) ) {
                               totalrecurringcost += db.getRecurringFees(occ);
                                System.out.println(totalrecurringcost);
                            }      
                    }    

                    //update is_processing flag
                    db.updateOCC(new OCC(occ.occ_id));
                }

                occTotalCostObject = new OCC(totalonetimecost, totalrecurringcost);

                return occTotalCostObject; 
            }

        }

//        public static void main(String[] args) throws SQLException {
//            OneTime_Recurring_Calc o = new OneTime_Recurring_Calc();
//            OCC returnocc = o.getOneTimeRecurringFee(new UDR("00201215860927",1),
//                    new BillDateInterval("20200401", "20200430"));
//            System.out.println("####" + returnocc.getTotalOneTimeFees() + "####" + returnocc.getTotalRecurringFees());
//        }   

    }