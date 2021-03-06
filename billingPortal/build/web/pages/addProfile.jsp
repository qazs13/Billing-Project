<%@page import="Database_Tables.Services"%>
<%@page import="Database.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../header&footer/header.html"%>
<%!
    Database db = new Database();
    Services service = db.getAllNormalServices();
%>
<!-- Profile Area -->
  <div class="container-fluid fullAreaProfile">
    <form class="firstPageProfile">
      <div class="firstProfilePageTitle">
        <span>Main Profile Info</span>
      </div>
      <div class="firstProfilePageName">
        <span>Profile Name</span>
        <input id="profileName" type="text" placeholder="Enter Profile Name" required name="profileName"/>
      </div>
      <div class="firstProfilePageFees">
        <span>Profile Fees</span>
        <input type="number" required min="1" value="0" name="profileFees"/>
        <label>LE</label>
      </div> 
      <div class="firstProfilePageFees">
        <span>Renew Duration</span>
        <input type="number" required min="1" value="0" name="renewProfileDuration"/>
        <label>days</label>
      </div>
      <div class="addFirstPageProfileSubmit">
        <input id="firstPageProfileSubmit" class="sub" type="submit" value="Next Page">      
      </div>  
    </form>
<!--/////////////////////////////////////////////////////////////////////////////////////////////-->
    <form class="secondPageProfile overflow-auto" id="secondPageProfile">
      <div class="secondProfilePageTitle">
        <span id="secondProfilePageTitle">Add Services to </span>
      </div>
      <div class="secondProfilePageName">
        <span>Add Service</span>
        <select class="secondPageProfileSelectService" name="selectService" id="selectService">
<%for(int i = 0; i < service.getAllServices().size(); i++){%>
          <option><%=service.getAllServices().elementAt(i).getSname()%></option>
<%}%>     
        </select>
        <input type="button" id="addServiceButton" value="+"/>
      </div>
      <div class="allSelectedServices" id="allSelectedServices"></div>
      <div class="addSecondPageProfileSubmit">
        <input type="hidden" value="" id="allServicesNames" name="allServicesName"/>
        <input type="hidden" value="" id="profileNameServices" name="profileNameServices"/>
        <input id="secondPageProfileSubmit" class="sub" type="submit" value="Next Page"/>  
      </div>  
    </form>
<!--/////////////////////////////////////////////////////////////////////////////////////////////-->
    <form class="thirdPageProfile overflow-auto" id="thirdPageProfile">
      <div class="thirdPageProfilePageTitle">
        <span id="thirdPageProfilePageTitle">Add Free Units to </span>
      </div>
      <div class="thirdProfilePageFreeUnits">
        <span>Free Unit Voice On Net</span>
        <input type="number" required min="0" value="0" name="free_Unit_Voice_On_Net"/>
        <label>Unit</label>
      </div>
      <div class="thirdProfilePageFreeUnits">
        <span>Free Unit Voice On Cross Net</span>
        <input type="number" required min="0" value="0" name="free_Unit_Voice_On_Cross_Net"/>
        <label>Unit</label>
      </div>
      <div class="thirdProfilePageFreeUnits">
        <span>Free Unit SMS On Net</span>
        <input type="number" required min="0" value="0" name="free_Unit_SMS_On_Net"/>
        <label>Unit</label>
      </div>
      <div class="thirdProfilePageFreeUnits">
        <span>Free Unit SMS On Cross Net</span>
        <input type="number" required min="0" value="0" name="free_Unit_SMS_On_Cross_Net"/>
        <label>Unit</label>
      </div>
      <div class="thirdProfilePageFreeUnits">
        <span>Free Unit for Internet</span>
        <input type="number" required min="0" value="0" name="free_Unit_For_Internet"/>
        <label>Unit</label>
      </div>
      <div class="addthirdPageProfileSubmit">
        <input id="thirdPageProfileSubmit" class="sub" type="submit" value="Finish">  
      </div>  
    </form>
</div>        
  </div>
<!-- Profile Area -->
<%@include file="../header&footer/scripts.html"%>
<script src="../js/addProfile.js"></script>  
<%@include file="../header&footer/footer.html"%>