<%-- 
    Document   : search
    Created on : May 8, 2020, 3:56:49 AM
    Author     : ramr
--%>

<%@page import="java.util.Vector"%>
<%@page import="Database.Database1"%>
<%@page import="Database_Tables.CDR"%>
<%@include file="../header&footer/header.html"%>
<link rel="stylesheet" href="try.css">


<%@page contentType="text/html" pageEncoding="UTF-8"%>


        <%
            Database1 db = new Database1();
            Vector<String[]> customer = new Vector();
            customer=db.search(request.getParameter("Name"));
            int i;
            

                   
                     for (i = 0; i < customer.size(); i++) {%>
                    
                    <div id="customer_data">
                        
                        <img id="productpic" src= "../img/profile_pic1.jpg">
                        <p id="msisdn"> Msisdn : <% out.println(customer.elementAt(i)[0]); %> </p>
                        <p id="f_name"> Name :  <% out.println(customer.elementAt(i)[1]);out.println(customer.elementAt(i)[2]); %> </p>
                        <a href="customerDetails.jsp?data=<%out.println(customer.elementAt(i)[0]);%>" > Click Here for more details</a>
                       
                        
                    </div> 
                        <% } %>
            
            

<%@include file="../header&footer/scripts.html"%>
<script src="../js/addCustomer.js"></script>  
<%@include file="../header&footer/footer.html"%>