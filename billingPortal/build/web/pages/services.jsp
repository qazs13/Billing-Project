<%@page import="Database_Tables.Services"%>
<%@page import="Database.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../header&footer/header.html"%>
<%
    Database db = new Database();
    Services allServices = db.getAllServices();
%>
<!-- Service Area -->
  <div class="container-fluid fullSize">
      <form class="addService">
      <span class="addSeriveTitle">Add Service</span>
      <div class="addServiceInput">
        <span>Enter Service Name</span>
        <input type="text" name="serviceName" placeholder="Serivce Name" required>
      </div>
      <div class="addServiceInput">
        <span>Service Additional Info</span>
        <select name="recurring" size="1">
          <option value="false">Not Recurring</option>
          <option value="true">Recurring</option>          
        </select>
        <select name="oneTimeService" size="1">
          <option value="false">Not one Time</option>
          <option value="true">One Time</option>          
        </select>
        <label>Service Fee</label>
        <input type="number" name="serviceFees" value="0" min="0"/>
        <h6>LE</h6>
      </div>
      <div class="addServiceSubmit">
        <input id="su" class="sub" type="submit" value="ADD SERVICE">
      </div>
    </form>
    <div class="showAllServices overflow-auto">
      <table class="table table-hover">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Service ID</th>
            <th scope="col">Service Name</th>
            <th scope="col">Type Of Service</th>
            <th scope="col">Service Fee</th>            
          </tr>
        </thead>
        <tbody>
<%for (int i = 0; i < allServices.getAllServices().size(); i++){%>
          <tr>
            <th scope="row"><%=i+1%></th>
            <td><%=allServices.getAllServices().elementAt(i).getSid()%></td>
            <td><%=allServices.getAllServices().elementAt(i).getSname()%></td>
<%if (allServices.getAllServices().elementAt(i).getIsOneTime() == false && allServices.getAllServices().elementAt(i).getIsRecurring()== false){%>
            <td>Normal Service</td>
            <td>It differ from Profile to another</td>
<%}else{%>
<%if (allServices.getAllServices().elementAt(i).getIsOneTime() == true){%>
            <td>One Time Service</td>
<%}else if (allServices.getAllServices().elementAt(i).getIsRecurring()== true){%>
            <td>Recurring Service</td>
<%}%>
            <td><%=allServices.getAllServices().elementAt(i).getServiceFees()%> LE</td>
<%}%>
          </tr>
<%}%>
        </tbody>
      </table>
    </div>
  </div>
<!-- Service Area -->
<%@include file="../header&footer/scripts.html"%>
<script src="../js/services.js"></script>  
<%@include file="../header&footer/footer.html"%>