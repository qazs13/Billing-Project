<%@page import="Database_Tables.Profile"%>
<%@page import="Database_Tables.Services"%>
<%@page import="Database_Tables.One_Time_Service"%>
<%@page import="Database.Database1"%>
<%@include file="../header&footer/header.html"%>
<link rel="stylesheet" href="try.css">


<!-- Search -->
<div class="container-fluid fullSizeCustomer">
  <h1> Search </h1>
  <form class="mainAreaCustomer"action="search.jsp" method="GET">

                        <input class="search-box" placeholder="Search..." type="text" name="Name">
                                <!--<input type="text" id="msisdn" name="msisdn" required  minlength="8" min="0" max="99999999" placeholder="Search..." name="Name">-->

<input type="submit" id="submitAddCustomer" class="addCustomer" value="Seach customer">                           
                        </button>
                    </form>
<!-- Add Customer -->
<%@include file="../header&footer/scripts.html"%>
<script src="../js/addCustomer.js"></script>  
<%@include file="../header&footer/footer.html"%>