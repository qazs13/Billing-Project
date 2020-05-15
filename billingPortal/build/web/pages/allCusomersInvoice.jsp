<%@include file="../header&footer/header.html"%>
  <!-- One Customer Bill Request -->  
  <div class="fullSizeBillGenerator">
    <form class="generateBill" action="/BillingModule/Billing_Module/billing/AllPersons" method="GET">
      <h1>Generate Bill For All Customers</h1>
      <div class="phoneNumber"></div>
      <div class="phoneNumber">
        <label>Start Date</label>
        <input required type="date" name="startDate"/>
      </div>
      <div class="phoneNumber">
        <label>End Date</label>
        <input required type="date" name="endDate"/>
      </div>
      <input type="submit" value="GENERATE BILL">            
    </form>
  </div>
<%@include file="../header&footer/scripts.html" %>
<%@include file="../header&footer/footer.html" %>