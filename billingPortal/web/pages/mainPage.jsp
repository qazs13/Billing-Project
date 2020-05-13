<%@page import="Database.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../header&footer/header.html"%>
<%
    Database db = new Database();
%>
<!-- Main Pager -->
<div class="container-fluid fullSizeMainPage">
  <h1>Main Information</h1>
  <div class="row">
    <div class="mainStatic">
      <h2>Num Of Users</h2>
      <div class="precent">
        <svg>
          <circle cx = "150" cy = "100" r = "70"></circle>
          <circle id="userscircle2" cx = "150" cy = "100" r = "70"></circle>
        </svg>
        <div class="number">
          <h3 id="usersNumber"><%=db.countAllUsers()%></h3>
        </div>
      </div>
    </div>
    <div class="mainStatic">
      <h2>Num Of Rate Plans</h2>
      <div class="precent">
        <svg>
          <circle cx = "150" cy = "100" r = "70"></circle>
          <circle id="ratecircle2" cx = "150" cy = "100" r = "70"></circle>
        </svg>
        <div class="number">
          <h3 id="rateNumber"><%=db.countAllProfiles()%></h3>
        </div>
      </div>      
    </div>
    <div class="mainStatic">
      <h2>Num Of Services</h2>
      <div class="precent">
        <svg>
          <circle cx = "150" cy = "100" r = "70"></circle>
          <circle id="servicescircle2" cx = "150" cy = "100" r = "70"></circle>
        </svg>
        <div class="number">
          <h3 id="servicesNumber"><%=db.countAllServices()%></h3>
        </div>
      </div>      
    </div>
    <div class="mainStatic">
      <h2>Num Of CDRs</h2>
      <div class="precent">
        <svg>
          <circle cx = "150" cy = "100" r = "70"></circle>
          <circle id="cdrcircle2" cx = "150" cy = "100" r = "70"></circle>
        </svg>
        <div class="number">
          <h3 id="cdrNumber"><%=db.countAllCDRs()%></h3>
        </div>
      </div>      
    </div>
  </div>
</div>
<!-- Main Page -->
<%@include file="../header&footer/scripts.html"%>
<script src="../js/mainPage.js"></script>  
<%@include file="../header&footer/footer.html"%>