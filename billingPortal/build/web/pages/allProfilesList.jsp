<%@page import="Database_Tables.Profile"%>
<%@page import="Database.Database"%>
<%@include file="../header&footer/header.html" %>
<%!
    Database db = new Database();
    Profile allProfiles = db.getAllProfiles();
%>
<!-- Profile List -->
  <div class="container-fluid fullAreaProfileList overflow-auto">
    <table class="table table-hover">
      <thead>
        <tr>
          <th scope="col">Profile ID</th>
          <th scope="col">Profile Name</th>
          <th scope="col">Renew Duration</th>
          <th scope="col">Profile Fees</th>
          <th scope="col">Action</th>
        </tr>
      </thead>
      <tbody>
<%for (int i = 0; i < allProfiles.getAllProfiles().size(); i++){%>          
        <tr>
          <th scope="row"><%=allProfiles.getAllProfiles().elementAt(i).getpID()%></th>
          <td><%=allProfiles.getAllProfiles().elementAt(i).getpName()%></td>
          <td><%=allProfiles.getAllProfiles().elementAt(i).getRenew_Duration()%></td>
          <td><%=allProfiles.getAllProfiles().elementAt(i).getpFees()%></td>
          <td><a href="seeAllProfileDetails?pid=<%=allProfiles.getAllProfiles().elementAt(i).getpID()%>" class="seeMore">
                  See More <span><i class="fas fa-info-circle"></i></span></a></td>
        </tr>
<%}%>        
      </tbody>
    </table>    
  </div>
<!-- Profile List -->
<%@include file="../header&footer/scripts.html" %>
<%@include file="../header&footer/footer.html" %>