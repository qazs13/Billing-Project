//package billingmodule;
//
//import Database.databaseConnection;
//import SystemObjects.*;
//import java.util.Vector;
//
//
//public class FreeUnitsCalc {
//        
//    public static void main(String [] args){
//        
//        FreeUnitsCalc fucalc = new FreeUnitsCalc();  
////        fucalc.serviceType();
//    }
//  
//}
//
//for(customer: customers){
//call 3 functions of 3 classes 
//return float total cost Service 
//+ add reccuring 
//+add one-time fees (one month (flag))
//
//}
//
////    public void funct(){
////            String str1 = "0109292";
////            String str2 = "010009068";
////            System.out.println(str1.regionMatches(true,0,str2,0, 3));
////    }
//
////  


//DB function will retrieve all Customers who have udrs 
//loop on eachone 
//    public void serviceType(){        
//        
//        for(UDR udr:udrList){    
//            if(udr.getServiceID() == 2){
//                fuVoiceUpdate(udr.getUdrID());
//            }else if(udr.getServiceID() == 3){
//                fuSMSUpdate(udr.getUdrID());
//            }else if(udr.getServiceID() == 4){
//                fuInternetUpdate(udr.getUdrID());
//            }
//        }  
//        //Boolean state=db.updateCustomerFUs(customerRemainedFUs);
//    }