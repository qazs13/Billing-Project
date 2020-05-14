<%@page import="Database_Tables.*"%>
<%@page import="Database.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../header&footer/header.html"%>
<%
    Database db = new Database();
    if (request.getParameter("pid") != null){
    int profileID = Integer.parseInt(request.getParameter("pid"));
    Profile profile = db.getProfileByItsID(profileID);
    Profile_Services profile_Services = db.getProfileServicesByItsID(profileID);
    Free_Units free_Units = db.getFreeUnitOfProfileByItsID(profileID);
%>
<!-- Show Profile Details -->
  <div class="container-fluid fullAreaProfileEdit">
    <div class="row">
      <div class="col-sm-4 leftCol">
        <span class="distance">Profile Info</span>
        <div class="profileInfo">
          <span class="textHeader">Profile Name</span>
          <span class="textFooter"><%=profile.getpName()%></span>
        </div>
        <div class="profileInfo">
          <span class="textHeader">Renew Duration</span>
          <span class="textFooter"><%=profile.getRenew_Duration()%></span>
        </div>    
        <div class="profileInfo">
          <span class="textHeader">Profile Fees</span>
          <span class="textFooter"><%=profile.getpFees()%></span>
        </div>             
      </div>
      <div class="col-sm-4 middleCol overflow-auto">
        <span class="distance">Services On Profile</span>
        <div id="accordion">
<!--/////////////////////////////////////////////////////////////////////////////////////////////--> 
<%for(int i = 0; i < profile_Services.getAllProfileServices().size(); i++){%>
          <div class="card">
            <div class="card-header" id="heading<%=i%>">
              <h5 class="mb-0">
                 <button class="btn btn-link" data-toggle="collapse" data-target="#collapse<%=i%>" aria-expanded="true" aria-controls="collapse<%=i%>">
                     <%=db.getServiceNameByItsID(profile_Services.getAllProfileServices().elementAt(i).getSid())%>
                </button>
              </h5>
            </div>
        
            <div id="collapse<%=i%>" class="collapse" aria-labelledby="heading<%=i%>" data-parent="#accordion">
              <div class="card-body">
                  <div class="card-body-service">
                      <h6 class="serviceHeader">Round Amount: </h6>
                      <lable><%=profile_Services.getAllProfileServices().elementAt(i).getRound_amount()%> Sec/SMS/Byte</lable>
                  </div>
                  <div class="card-body-service">
                      <h6>Fees For Same Local Operator: </h6>
                      <lable><%=profile_Services.getAllProfileServices().elementAt(i).getFees_local_same()%> LE</lable>            
                  </div>
                  <div class="card-body-service">
                      <h6>Fees For Other Local Operator: </h6>
                      <lable><%=profile_Services.getAllProfileServices().elementAt(i).getFees_local_diff()%> LE</lable>
                  </div>
                  <div class="card-body-service">
                      <h6>Fees For International: </h6>
                      <lable><%=profile_Services.getAllProfileServices().elementAt(i).getFees_international()%> LE</lable> 
                  </div>
              </div>
            </div>
          </div>
<%}%>          
<!--/////////////////////////////////////////////////////////////////////////////////////////////-->            
        </div>
      </div>
      <div class="col-sm-4 rightCol">
        <span class="distance">Profile Free Units</span>
        <div class="profileFreeUnit">
          <span class="textHeader">Voice Free Units</span>
          <div class="divFooter">
            <span>Same Operator : <%=free_Units.getFree_voice_same()%> Unit</span>
            <span>Other Operator : <%=free_Units.getFree_voice_diff()%> Unit</span>
          </div>
        </div>
        <div class="profileFreeUnit">
          <span class="textHeader">SMS Free Units</span>
          <div class="divFooter">
            <span>Same Operator : <%=free_Units.getFree_sms_same()%> Unit</span>
            <span>Other Operator : <%=free_Units.getFree_sms_diff()%> Unit</span>
          </div>
        </div>    
        <div class="profileFreeUnit">
          <span class="textHeader">Internet Free Units</span>
          <span class="textFooter"><%=free_Units.getFree_internet()%> Unit</span>
        </div>     
      </div>
    </div>
  </div>
<!-- Show Profile Details -->
<%}%>
<%@include file="../header&footer/scripts.html"%>
<%@include file="../header&footer/footer.html"%>