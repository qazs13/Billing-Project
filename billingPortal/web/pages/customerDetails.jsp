<%@page import="Database.Database"%>
<%@page import="Database_Tables.*"%>
<%@page import="java.util.Vector"%>
<%@include file="../header&footer/header.html"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Database db = new Database();
    Customer customer = db.getCustomerByMSISDN(new Customer(request.getParameter("data")));
    Customer_Profile c_p = db.getCustomerProfileByMSISDN(customer);
    Profile profile = db.select_from_profile(c_p.getPid());
    OCC occ = db.select_from_occ(customer.getMsisdn());
    Services services = null;
    One_Time_Service oservice = null;
%>
<div id="customer_data1">
    <img id="productpic" src= "../img/profile_pic1.jpg">
    <p id="msisdn"> Msisdn : <% out.println(customer.getMsisdn()); %> </p>
        <p id="f_name"> Name :  <% out.println(customer.getF_name());
                        out.println(customer.getL_name()); %> </p>
    <p id="email"> Email :  <% out.println(customer.getEmail()); %> </p>
    <p id="address"> Address :  <% out.println(customer.getAddress());%> </p>
    <p id="start_date"> Start date :  <% out.println(c_p.getStart_date()); %> </p>
    <p id="end_date"> End date :  <% if (c_p.getEnd_date() == null) 
        {
            out.println("-");
        } else {
            out.println(c_p.getEnd_date());
        }
        %> </p>
    <p id="pname"> Profile name  :  <% out.println(profile.getpName()); %> </p>
        <p id="pfees"> Fees  :  <% out.println(profile.getpFees());
                        out.println("L.E"); %> </p>
        <p id="renew"> Renew Duration  :  <% out.println(profile.getRenew_Duration());
                        out.println("Days"); %> </p>                        
    <p id="free_voice_same"> Voice units for same network :  <% out.println(c_p.getFree_voice_same()); out.println("Minutes"); %> </p>
    <p id="free_voice_diff"> Voice units for different network :  <% out.println(c_p.getFree_voice_diff());  out.println("Minutes ");%> </p>
    <p id="free_sms_same"> SMS units for same network :  <% out.println(c_p.getFree_sms_same()); %> </p>
    <p id="free_sms_diff"> SMS units for different network :  <% out.println(c_p.getFree_sms_diff()); %> </p>
    <p id="free_internet"> Internet units  :  <% out.println(c_p.getFree_internet());  out.println("MegaBytes");%> </p>
    
    <%
        if (occ.getAllOCCs().size() > 0) {
            for (int y = 0; y < occ.getAllOCCs().size(); y++) {
                if (occ.getAllOCCs().elementAt(y).getType_of_service().equals("recurring_service")) {
                    services = db.select_from_services(occ.getAllOCCs().elementAt(y).getOne_recurring_id());
    %>

    <p id="services"> Recurring Service name  :  <% out.println(services.getSname()); %> </p>
    <p id="services"> <% out.println(services.getSname()); %> Service fees  :  <% out.println(services.getServiceFees()); out.println("L.E"); %> </p>    
    
    <%
        }
                else if (occ.getAllOCCs().elementAt(y).getType_of_service().equals("one_time_service")) {
                    oservice = db.select_from_one_time(occ.getAllOCCs().elementAt(y).getOne_recurring_id());%>

    <p id="oservice"> One time Service name  :  <% out.println(oservice.getOsname()); %> </p>
    <p id="oservice"> <% out.println(oservice.getOsname()); %> Service fees  :  <% out.println(oservice.getOsfee()); out.println("L.E"); %> </p>

    <%
        }
            else 
            {
                System.out.println("No services found");
            }
    }
}
    %>    
</div>
<%@include file="../header&footer/scripts.html"%>
<script src="../js/changeBodyColor.js"></script>  
<%@include file="../header&footer/footer.html"%>