<%@page import="FilesName.FilesName"%>
<%@page import="java.util.Vector"%>
<%@page import="java.io.File"%>
<%
    Vector<FilesName> allPDFs = new Vector();
    String path = "C:\\Users\\amrws\\Desktop\\BillingModule\\src\\allPDFs";
    File folder = new File(path);

    File[] files = folder.listFiles();

    for (File file : files)
    {
        String[] namesAndDate = file.getName().split(";");
        allPDFs.add(new FilesName(namesAndDate[0], namesAndDate[1].substring(0, 10), file.getAbsolutePath()));
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>PDF VIEWER</title>
  <link rel="stylesheet" href="../css/swiper.min.css">  
  <link rel="stylesheet" href="../css/pdfCss.css">
</head>
<body>
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
              Date of Bill: <%=allPDFs.elementAt(i).getFileDate()%>
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
  
      
  </div>
  <script src="../js/swiper.min.js"></script>
  <script src="../js/pdfList.js"></script>
  <script src="../js/jquery-3.5.1.min.js"></script>
</body>
</html>