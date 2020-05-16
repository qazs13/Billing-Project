<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Care</title>
    <link rel="icon" type="image/x-icon" href="../img/favicon.ico">    
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/all.min.css">
    <link rel="stylesheet" href="../css/mainPage.css">
    <link rel="stylesheet" href="../css/upload.css">
</head>
<body>
<!-- Header -->    
  <header class="sticky-top">
      <div class="firstRowHeader"></div>
      
      <div class="secondRowHeader">
          <nav class="navbar navbar-expand-lg navbar-dark">
              <a class="navbar-brand mx-5" href="../pages/mainPage.jsp">
                <img class="orange" src="../img/logo-orange.png" alt="logo-orange">
                
              </a>
              
              <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>                
              <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                  <div class="mx-4" style="height: 5px; width: 5px;"></div>
                  <ul class="navbar-nav mr-auto mx-4 mt-lg-0">
                     

                    <li class="nav-item active mx-5">
                        <a class="nav-link" href="../pages/mainPage.jsp">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active mx-5">
                        <a class="nav-link" href="../pages/customer.jsp">Customer<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active mx-5">
                        <a class="nav-link" href="../pages/profile.jsp">Profile<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active mx-5">
                      <a class="nav-link" href="../pages/services.jsp">Services<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active mx-5">
                      <a class="nav-link" href="../pages/billing.jsp">Billing<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active mx-5">
                        <a class="nav-link" href="../pages/uploadCDR.jsp">Upload CDR<span class="sr-only">(current)</span></a>
                    </li>                        
                  </ul>
                </div>
          </nav>        
      </div>
  </header>
<!-- Header -->
<!--////////////////////////////////////////////////////////////////////-->
<div class="wrapper">
	<div class="title">
		CDR Upload
	</div>
	<div class="file_upload_list">
            <ul>
                <li>

                    <div class="file_item">
				
                        <div class="file_name" id="file_name"><ul id="fp"></ul> </div>
                    </div>
                </li>
            </ul>
	</div>
	<form method="POST" action="/billingPortal/UploadServlet" enctype="multipart/form-data">
	<div class="choose_file">
		<label for="choose_file">
                        <input name="ufile" type="file" id="choose_file" multiple="multiple">
			<span>Choose Files</span>
		</label>
	</div>
	<div class="choose_file">
		<label for="upload">
                        <input type="submit" value="Upload" id="upload" >
			<span>upload</span>
		</label>
	</div>
	</form>
</div>
<!--////////////////////////////////////////////////////////////////////-->
<script src="../js/upload.js"></script>
</body>
</html>