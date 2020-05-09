<%-- 
    Document   : customerDetails
    Created on : May 8, 2020, 10:41:08 PM
    Author     : ramr
--%>

<%@page import="Database_Tables.Profile"%>
<%@page import="Database_Tables.*"%>
<%@page import="java.util.Vector"%>
<%@page import="Database.Database1"%>
<%@page import="Database_Tables.CDR"%>
<%@include file="../header&footer/header.html"%>
<link rel="stylesheet" href="try.css">


<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    Database1 db = new Database1();
    Vector<String[]> customer = new Vector();
    customer = db.search(request.getParameter("data"));
    Vector<Customer_Profile> c_p = new Vector();
    Vector<Profile> profile = new Vector();
    Vector<OCC> occ = new Vector();
    Vector<Services> services = new Vector();
    Vector<One_Time_Service> oservice = new Vector();
    int i, j, x, y, z;

    for (i = 0; i < customer.size(); i++) {
        c_p = db.select_from_customer_profile(customer.elementAt(i)[0]);
        for (j = 0; j < c_p.size(); j++) {
            profile = db.select_from_profile(c_p.elementAt(j).getPid());
            for (x = 0; x < profile.size(); x++) {
                occ = db.select_from_occ(customer.elementAt(i)[0]);
                for (y = 0; y < occ.size(); y++) {

%>
                <div id="customer_data1">


                    <img id="productpic" src= "../img/profile_pic1.jpg">
                    <p id="msisdn"> Msisdn : <% out.println(customer.elementAt(i)[0]); %> </p>
                    <p id="f_name"> Name :  <% out.println(customer.elementAt(i)[1]);
                                            out.println(customer.elementAt(i)[2]); %> </p>
                    <p id="email"> Email :  <% out.println(customer.elementAt(i)[3]); %> </p>
                    <p id="address"> Address :  <% out.println(customer.elementAt(i)[4]); %> </p>
                    <p id="start_date"> Start date :  <% out.println(c_p.elementAt(j).getStart_date()); %> </p>
                    <p id="end_date"> End date :  <% if (c_p.elementAt(j).getEnd_date() == null) {
                            out.println("-");
                        } else {
                            out.println(c_p.elementAt(j).getEnd_date());
                        }
                        %> </p>
                    <p id="pname"> Profile name  :  <% out.println(profile.elementAt(x).getpName()); %> </p>
                    <p id="pfees"> Fees  :  <% out.println(profile.elementAt(x).getpFees());
                                            out.println("L.E"); %> </p>
                    <p id="renew"> Renew Duration  :  <% out.println(profile.elementAt(x).getRenew_Duration());
                                            out.println("days"); %> </p>                        
                    <p id="free_voice_same"> Voice units for same network :  <% out.println(c_p.elementAt(j).getFree_voice_same()); %> </p>
                    <p id="free_voice_diff"> Voice units for different network :  <% out.println(c_p.elementAt(j).getFree_voice_diff()); %> </p>
                    <p id="free_sms_same"> SMS units for same network :  <% out.println(c_p.elementAt(j).getFree_sms_same()); %> </p>
                    <p id="free_sms_diff"> SMS units for different network :  <% out.println(c_p.elementAt(j).getFree_sms_diff()); %> </p>
                    <p id="free_internet"> Internet units  :  <% out.println(c_p.elementAt(j).getFree_internet()); %> </p>

                    <%
                     if (occ.elementAt(y).getType_of_service().equals("recurring")) {
                          services = db.select_from_services(occ.elementAt(y).getOne_recurring_id());
                          for(y=0;y<services.size();y++){
                    
                    %>

                    <p id="services"> Recurring Service name  :  <% out.println(services.elementAt(y).getSname()); %> </p>

                    <%

                     } } if (occ.elementAt(y).getType_of_service().equals("onetime")) {
                             oservice = db.select_from_one_time(occ.elementAt(y).getOne_recurring_id());
                                for(y=0;y<oservice.size();y++) {%>

                    <p id="oservice"> One time Service name  :  <% out.println(oservice.elementAt(y).getOsname()); %> </p>

                    <%
                        } }else {
                            System.out.println("No services found");
                        }

                    %>

                </div> 
<% }
                                    }
                                }
                            }%>



<%@include file="../header&footer/scripts.html"%>
<script src="../js/addCustomer.js"></script>  
<%@include file="../header&footer/footer.html"%>