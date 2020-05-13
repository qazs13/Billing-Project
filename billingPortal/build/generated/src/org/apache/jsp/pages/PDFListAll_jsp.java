package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import FilesName.FilesName;
import java.util.Vector;
import java.io.File;

public final class PDFListAll_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");

    Vector<FilesName> allPDFs = new Vector();
    String path = "C:\\Users\\amrws\\Desktop\\BillingModule\\src\\allPDFs";
    File folder = new File(path);

    File[] files = folder.listFiles();

    for (File file : files)
    {
        String[] namesAndDate = file.getName().split(";");
        allPDFs.add(new FilesName(namesAndDate[0], namesAndDate[1].substring(0, 10), file.getAbsolutePath()));
    }

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("  <meta charset=\"UTF-8\">\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("  <title>PDF VIEWER</title>\n");
      out.write("  <link rel=\"stylesheet\" href=\"../css/swiper.min.css\">  \n");
      out.write("  <link rel=\"stylesheet\" href=\"../css/pdfCss.css\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("  <!-- Swiper -->\n");
      out.write("  <div class=\"swiper-container\">\n");
      out.write("    <div class=\"swiper-wrapper\">\n");
for (int i = 0; i < allPDFs.size(); i++){
      out.write("        \n");
      out.write("      <!-- For Loop Here -->\n");
      out.write("      <div class=\"swiper-slide\">\n");
      out.write("        <div class=\"card\">\n");
      out.write("          <div class=\"sliderText\">\n");
      out.write("              <h3>");
      out.print(allPDFs.elementAt(i).getFileName());
      out.write("</h3>\n");
      out.write("          </div>\n");
      out.write("          <div class=\"content\">\n");
      out.write("            <p>\n");
      out.write("              Date of Bill: ");
      out.print(allPDFs.elementAt(i).getFileDate());
      out.write("\n");
      out.write("            </p>\n");
      out.write("            <a href=\"/billingPortal/openPDF?path=");
      out.print(allPDFs.elementAt(i).getFilePath());
      out.write("\" target=\"_blank\">Open PDF</a>      \n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("     </div>\n");
      out.write("     <!-- END For Loop Here -->\n");
}
      out.write("     \n");
      out.write("    </div>\n");
      out.write("  </div>\n");
      out.write("  <!-- Swiper -->\n");
      out.write("  \n");
      out.write("      \n");
      out.write("  </div>\n");
      out.write("  <script src=\"../js/swiper.min.js\"></script>\n");
      out.write("  <script src=\"../js/pdfList.js\"></script>\n");
      out.write("  <script src=\"../js/jquery-3.5.1.min.js\"></script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
