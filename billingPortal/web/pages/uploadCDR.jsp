<%@include file="../header&footer/header.html" %>
<!--////////////////////////////////////////////////////////////////////-->
<form method="post" action="/billingPortal/UploadServlet" enctype="multipart/form-data">
    Choose a file: <input type="file" name="ufile" multiple="multiple" />
    <input type="submit" value="Upload" />
</form>
<!--////////////////////////////////////////////////////////////////////-->
<%@include file="../header&footer/scripts.html" %>
<%@include file="../header&footer/footer.html" %>