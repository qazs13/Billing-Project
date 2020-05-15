<%@page import="FilesName.FilesName"%>
<%@page import="java.util.Vector"%>
<%@page import="java.io.File"%>
<%
    Vector<FilesName> allPDFs = new Vector();
    String path = "C:\\Users\\amrws\\Desktop\\BillingModule\\src\\java\\allPDFs";
    File folder = new File(path);
    File[] files = null;
    if (folder.exists())
    {
        files = folder.listFiles();

        for (File file : files)
        {
            String[] namesAndDateAndPhoneNumber = file.getName().split(";");
            allPDFs.add(new FilesName(namesAndDateAndPhoneNumber[0], namesAndDateAndPhoneNumber[1], file.getAbsolutePath(),
                    namesAndDateAndPhoneNumber[2]));
        }        
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>PDF VIEWER</title>
  <link rel="icon" type="image/x-icon" href="../img/favicon.ico">      
  <link rel="stylesheet" href="../css/swiper.min.css">  
  <link rel="stylesheet" href="../css/pdfCss.css">
  <link rel="stylesheet" href="../css/all.min.css">  
</head>
<body>
    <div class="homeButtonDiv">
        <a class="homeButton" href="/billingPortal/pages/mainPage.jsp"><i class="fas fa-home"></i></a>
    </div>
  <!-- Swiper -->
  <div class="swiper-container">
    <div class="swiper-wrapper">
<%for (int i = 0; i < allPDFs.size(); i++){%>        
      <!-- For Loop Here -->
      <div class="swiper-slide">
        <div class="card">
          <div class="sliderText">
              <h3><%=allPDFs.elementAt(i).getFileName()%></h3>
          </div>
          <div class="content">
            <p>
              Phone Number: <span>(<%=allPDFs.elementAt(i).getFilePhoneNumber()%>)</span>
            </p>              
            <p>
              Date of Bill: <lable>(<%=allPDFs.elementAt(i).getFileDate().substring(8, 10)+" / "+
                        allPDFs.elementAt(i).getFileDate().substring(5, 7)+" / "+
                        allPDFs.elementAt(i).getFileDate().substring(0, 4)%>)</lable>
            </p>
            <a href="/billingPortal/openPDF?path=<%=allPDFs.elementAt(i).getFilePath()%>" target="_blank">Open PDF</a>      
          </div>
        </div>
     </div>
     <!-- END For Loop Here -->
<%}%>     
    </div>
  </div>
  <!-- Swiper -->
  <script src="../js/swiper.min.js"></script>
  <script src="../js/pdfList.js"></script>
  <script src="../js/jquery-3.5.1.min.js"></script>
</body>
</html>