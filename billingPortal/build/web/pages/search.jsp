<%@page import="Database.Database"%>
<%@page import="Database_Tables.*"%>
<%@include file="../header&footer/header.html"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="fullSizeSearch">
    <h1>Search Result</h1>
<%
    Database db = new Database();
    Customer allCustomers = db.searchOfAllCustomersData(request.getParameter("Name"));
for (int i = 0; i < allCustomers.getAllCustomers().size(); i++) {%>
    <div id="customer_data">
        <img id="productpic" src= "../img/profile_pic1.jpg">
        <p id="msisdn"> Msisdn : <%=allCustomers.getAllCustomers().elementAt(i).getMsisdn()%> </p>
        <p id="f_name"> Name :  <%out.println(allCustomers.getAllCustomers().elementAt(i).getF_name());out.println(allCustomers.getAllCustomers().elementAt(i).getL_name());%></p>
        <a class="anch" href="customerDetails.jsp?data=<%out.println(allCustomers.getAllCustomers().elementAt(i).getMsisdn());%>" > Click Here for more details</a>
    </div> 
<%}%>
</div>
<%@include file="../header&footer/scripts.html"%>
<%@include file="../header&footer/footer.html"%>