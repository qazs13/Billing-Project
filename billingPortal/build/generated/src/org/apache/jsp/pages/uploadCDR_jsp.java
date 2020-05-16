package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class uploadCDR_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <title>Customer Care</title>\r\n");
      out.write("    <link rel=\"icon\" type=\"image/x-icon\" href=\"../img/favicon.ico\">    \r\n");
      out.write("    <link rel=\"stylesheet\" href=\"../css/bootstrap.min.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"../css/all.min.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"../css/mainPage.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"../css/upload.css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<!-- Header -->    \r\n");
      out.write("  <header class=\"sticky-top\">\r\n");
      out.write("      <div class=\"firstRowHeader\"></div>\r\n");
      out.write("      \r\n");
      out.write("      <div class=\"secondRowHeader\">\r\n");
      out.write("          <nav class=\"navbar navbar-expand-lg navbar-dark\">\r\n");
      out.write("              <a class=\"navbar-brand mx-5\" href=\"../pages/mainPage.jsp\">\r\n");
      out.write("                <img class=\"orange\" src=\"../img/logo-orange.png\" alt=\"logo-orange\">\r\n");
      out.write("                \r\n");
      out.write("              </a>\r\n");
      out.write("              \r\n");
      out.write("              <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarTogglerDemo01\" aria-controls=\"navbarTogglerDemo01\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n");
      out.write("                  <span class=\"navbar-toggler-icon\"></span>\r\n");
      out.write("                </button>                \r\n");
      out.write("              <div class=\"collapse navbar-collapse\" id=\"navbarTogglerDemo01\">\r\n");
      out.write("                  <div class=\"mx-4\" style=\"height: 5px; width: 5px;\"></div>\r\n");
      out.write("                  <ul class=\"navbar-nav mr-auto mx-4 mt-lg-0\">\r\n");
      out.write("                     \r\n");
      out.write("\r\n");
      out.write("                    <li class=\"nav-item active mx-5\">\r\n");
      out.write("                        <a class=\"nav-link\" href=\"../pages/mainPage.jsp\">Home <span class=\"sr-only\">(current)</span></a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li class=\"nav-item active mx-5\">\r\n");
      out.write("                        <a class=\"nav-link\" href=\"../pages/customer.jsp\">Customer<span class=\"sr-only\">(current)</span></a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li class=\"nav-item active mx-5\">\r\n");
      out.write("                        <a class=\"nav-link\" href=\"../pages/profile.jsp\">Profile<span class=\"sr-only\">(current)</span></a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li class=\"nav-item active mx-5\">\r\n");
      out.write("                      <a class=\"nav-link\" href=\"../pages/services.jsp\">Services<span class=\"sr-only\">(current)</span></a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li class=\"nav-item active mx-5\">\r\n");
      out.write("                      <a class=\"nav-link\" href=\"../pages/billing.jsp\">Billing<span class=\"sr-only\">(current)</span></a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li class=\"nav-item active mx-5\">\r\n");
      out.write("                        <a class=\"nav-link\" href=\"../pages/uploadCDR.jsp\">Upload CDR<span class=\"sr-only\">(current)</span></a>\r\n");
      out.write("                    </li>                        \r\n");
      out.write("                  </ul>\r\n");
      out.write("                </div>\r\n");
      out.write("          </nav>        \r\n");
      out.write("      </div>\r\n");
      out.write("  </header>\r\n");
      out.write("<!-- Header -->\r\n");
      out.write("<!--////////////////////////////////////////////////////////////////////-->\r\n");
      out.write("<div class=\"wrapper\">\r\n");
      out.write("\t<div class=\"title\">\r\n");
      out.write("\t\tCDR Upload\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"file_upload_list\">\r\n");
      out.write("            <ul>\r\n");
      out.write("                <li>\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"file_item\">\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("                        <div class=\"file_name\" id=\"file_name\"><ul id=\"fp\"></ul> </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </li>\r\n");
      out.write("            </ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<form method=\"POST\" action=\"/billingPortal/UploadServlet\" enctype=\"multipart/form-data\">\r\n");
      out.write("\t<div class=\"choose_file\">\r\n");
      out.write("\t\t<label for=\"choose_file\">\r\n");
      out.write("                        <input name=\"ufile\" type=\"file\" id=\"choose_file\" multiple=\"multiple\">\r\n");
      out.write("\t\t\t<span>Choose Files</span>\r\n");
      out.write("\t\t</label>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"choose_file\">\r\n");
      out.write("\t\t<label for=\"upload\">\r\n");
      out.write("                        <input type=\"submit\" value=\"Upload\" id=\"upload\" >\r\n");
      out.write("\t\t\t<span>upload</span>\r\n");
      out.write("\t\t</label>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t</form>\r\n");
      out.write("</div>\r\n");
      out.write("<!--////////////////////////////////////////////////////////////////////-->\r\n");
      out.write("<script src=\"../js/upload.js\"></script>\r\n");
      out.write("</body>\r\n");
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
