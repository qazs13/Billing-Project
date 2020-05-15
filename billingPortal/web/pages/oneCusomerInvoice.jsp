<%@include file="../header&footer/header.html"%>
  <!-- One Customer Bill Request -->  
  <div class="fullSizeBillGenerator">
    <form class="generateBill">
      <h1>Generate Bill For One Customer</h1>
      <div class="phoneNumber">
        <label>Phone Number</label>
        <h5>+2012</h5>
        <input required type="text" name="msisdn"/>
      </div>
      <div class="phoneNumber">
        <label>Start Date</label>
        <input required type="date" name="startDate"/>
      </div>
      <div class="phoneNumber">
        <label>End Date</label>
        <input required type="date" name="endDate"/>
      </div>
      <input id="generateBill" type="submit" value="GENERATE BILL">            
    </form>
  </div>
<%@include file="../header&footer/scripts.html" %>
<script src="../js/oneCustomerBill.js"></script>
<%@include file="../header&footer/footer.html" %>