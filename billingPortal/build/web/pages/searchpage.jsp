<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../header&footer/header.html"%>
<!-- Search -->
<div class="container-fluid fullSizeSearch">
    <h1>Search Page</h1>
    <form class="mainAreaSearch" action="search.jsp" method="GET">
        <lable>Search Box</lable>
        <input class="search-box" placeholder="Search..." type="text" name="Name">
        <input type="submit" id="submitAddCustomer" class="addCustomer" value="Seach customer">
    </form>
</div>    
<!-- Search -->
<%@include file="../header&footer/scripts.html"%>
<%@include file="../header&footer/footer.html"%>