<%@page import="Database_Tables.Profile"%>
<%@page import="Database_Tables.Services"%>
<%@page import="Database_Tables.One_Time_Service"%>
<%@page import="Database.Database"%>
<%@include file="../header&footer/header.html"%>
<%
    Database db = new Database();
    One_Time_Service one_time_service = db.getAllOneTimeServices();
    Services services = db.getAllRecurringAndNotRecurringServices();
    Profile profile = db.getAllProfiles();
%>

<!-- Add Customer -->
<div class="container-fluid fullSizeCustomer">
  <h1>Add New Customer</h1>
  <form class="mainAreaCustomer">
    <div class="row">
      <div class="inputCustomer col-sm-3">
        <span>Mobile Phone</span>
        <label>012</label>
        <input type="number" id="msisdn" name="msisdn" required  minlength="8" min="0" max="99999999">
      </div>
      <div class="inputCustomer col-sm-3">
        <span>First Name</span>
        <input type="text" required minlength="1" name="fName" required>        
      </div>
      <div class="inputCustomer col-sm-3">
        <span>Last Name</span>
        <input type="text" required minlength="1" name="lName" required>          
      </div>
      <div class="inputCustomer col-sm-3">
        <span>Email Address</span>
        <input type="email" required minlength="1" name="email" required>          
      </div>
      <div class="inputCustomer col-sm-3">
        <span>Address</span>
        <input type="text" required minlength="1" name="address" required>          
      </div>             
    </div>
    <div style="background-color: #FF7900; height: 2px; width: 99%;"></div>
    <div class="row">
      <div class="inputCustomer col-sm-4">
        <span>Choose Profile</span>
        <label>Profile Name</label>
        <select class="pName" name="selectProfile">
<%for (int i = 0; i < profile.getAllProfiles().size(); i++){%>
            <option><%=profile.getAllProfiles().elementAt(i).getpName()%></option>
<%}%>     
        </select>
      </div>
      <div class="inputCustomer col-sm-4">
        <span>Blocked Services</span>
        <label>Service Name</label>
        <select class="bService" name="blockedService">
            <option>---</option>
<%for (int i = 0; i < services.getAllServices().size(); i++){%>
    <%if (!services.getAllServices().elementAt(i).getIsRecurring()){%>
            <option><%=services.getAllServices().elementAt(i).getSname()%></option>
    <%}%>     
<%}%>
        </select>
      </div>       
      <div class="inputCustomer col-sm-4">
        <span>Add One Time Service</span>
        <label>Service Name</label>
        <select class="pName" name="oneTimeService">
<%for (int i = 0; i < one_time_service.getAllOneTimeServices().size(); i++){%>            
        <option><%=one_time_service.getAllOneTimeServices().elementAt(i).getOsname()%></option>
<%}%>            
        </select>
      </div>              
    </div>
    <div style="background-color: #FF7900; height: 2px; width: 99%;"></div>
    <div class="row">
      <div class="inputCustomer col-sm-4">
        <span>Add Recurring Services</span>
        <label>Service Name</label>
        <select class="pName" id="selectRecurringServices">
<%for (int i = 0; i < services.getAllServices().size(); i++){%>
    <%if (services.getAllServices().elementAt(i).getIsRecurring()){%>
            <option><%=services.getAllServices().elementAt(i).getSname()%></option>
    <%}%>     
<%}%>
        </select>
        <input type="button" id="addServiceButton" value="+"/>          
      </div>
      <div id="recurringServices" class="allRecurringServices col-sm-8 ">
        <span>Selected Recurring Services</span>
      </div>   
    </div>
    <input type="hidden" name="allRecurringServices" id="allRecurringServices" value="">
    <input type="submit" id="submitAddCustomer" class="addCustomer" value="Add Customer">
  </form>
</div>
<!-- Add Customer -->
<%@include file="../header&footer/scripts.html"%>
<script src="../js/addCustomer.js"></script>  
<%@include file="../header&footer/footer.html"%>