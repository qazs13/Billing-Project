<%@include file="../header&footer/header.html"%>
<!-- Add Customer -->
  <div class="container-fluid fullSizeCustomer">
    <h1>Add New Profile</h1>
    <form action="/billingPortal/Add_Customer" method="POST" class="mainAreaCustomer">
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
      <div style="background-color: #FF7900; height: 2px; width: 100%;"></div>
      <div class="row">
        <div class="inputCustomer col-sm-4">
          <span>Choose Profile</span>
          <label>Profile Name</label>
          <select class="pName" name="selectProfile">
              <option>Orange100</option>
              <option>Orange50</option>
              <option>Orange10</option>
          </select>
        </div>
        <div class="inputCustomer col-sm-4">
          <span>Blocked Services</span>
          <label>Service Name</label>
          <select class="bService" name="selectProfile">
              <option>---</option>
              <option>Voice</option>
              <option>SMS</option>
              <option>Internet</option>
          </select>
        </div>        
      </div>
      <div style="background-color: #FF7900; height: 2px; width: 100%;"></div>
      <div class="row">
        <div class="inputCustomer col-sm-12">
          <span>Add One Time Service</span>
          <label>Service Name</label>
          <select class="pName" name="selectProfile">
              <option>Billing Fees</option>
          </select>
        </div>     
      </div>      
      <input type="submit" class="addCustomer" value="Add Customer">
    </form>
  </div>
<!-- Add Customer -->
<%@include file="../header&footer/scripts.html"%>
<%@include file="../header&footer/footer.html"%>