package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Database_Tables.Services;
import Database.Database;

public final class addProfile_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


    Database db = new Database();
    Services service = db.getAllNotOneTimeServices();

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/pages/../header&footer/header.html");
    _jspx_dependants.add("/pages/../header&footer/scripts.html");
    _jspx_dependants.add("/pages/../header&footer/footer.html");
  }

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
      response.setContentType("text/html;charset=UTF-8");
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <title>Customer Care</title>\n");
      out.write("    <link rel=\"icon\" type=\"image/x-icon\" href=\"../img/favicon.ico\">    \n");
      out.write("    <link rel=\"stylesheet\" href=\"../css/bootstrap.min.css\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"../css/all.min.css\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"../css/mainPage.css\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<!-- Header -->    \n");
      out.write("  <header class=\"sticky-top\">\n");
      out.write("      <div class=\"firstRowHeader\"></div>\n");
      out.write("      <div class=\"secondRowHeader\">\n");
      out.write("          <nav class=\"navbar navbar-expand-lg navbar-dark\">\n");
      out.write("              <a class=\"navbar-brand mx-5\" href=\"#\">\n");
      out.write("                <img class=\"orange\" src=\"../img/logo-orange.png\" alt=\"logo-orange\">\n");
      out.write("              </a>\n");
      out.write("              <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarTogglerDemo01\" aria-controls=\"navbarTogglerDemo01\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n");
      out.write("                  <span class=\"navbar-toggler-icon\"></span>\n");
      out.write("                </button>                \n");
      out.write("              <div class=\"collapse navbar-collapse\" id=\"navbarTogglerDemo01\">\n");
      out.write("                  <div class=\"mx-4\" style=\"height: 5px; width: 5px;\"></div>\n");
      out.write("                  <ul class=\"navbar-nav mr-auto mx-4 mt-lg-0\">\n");
      out.write("                    <li class=\"nav-item active mx-5\">\n");
      out.write("                      <a class=\"nav-link\" href=\"#\">Home <span class=\"sr-only\">(current)</span></a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item active mx-5\">\n");
      out.write("                      <a class=\"nav-link\" href=\"#\">Users<span class=\"sr-only\">(current)</span></a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item active mx-5\">\n");
      out.write("                      <a class=\"nav-link\" href=\"#\">Profile<span class=\"sr-only\">(current)</span></a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item active mx-5\">\n");
      out.write("                      <a class=\"nav-link\" href=\"../pages/services.jsp\">Services<span class=\"sr-only\">(current)</span></a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item active mx-5\">\n");
      out.write("                      <a class=\"nav-link\" href=\"#\">Billing<span class=\"sr-only\">(current)</span></a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item active mx-5\">\n");
      out.write("                      <a class=\"nav-link\" href=\"#\">Search<span class=\"sr-only\">(current)</span></a>\n");
      out.write("                    </li>                        \n");
      out.write("                  </ul>\n");
      out.write("                </div>\n");
      out.write("          </nav>        \n");
      out.write("      </div>\n");
      out.write("  </header>\n");
      out.write("<!-- Header -->\n");
      out.write('\n');
      out.write("\n");
      out.write("<!-- Profile Area -->\n");
      out.write("  <div class=\"container-fluid fullAreaProfile\">\n");
      out.write("    <form class=\"firstPageProfile\">\n");
      out.write("      <div class=\"firstProfilePageTitle\">\n");
      out.write("        <span>Main Profile Info</span>\n");
      out.write("      </div>\n");
      out.write("      <div class=\"firstProfilePageName\">\n");
      out.write("        <span>Profile Name</span>\n");
      out.write("        <input id=\"profileName\" type=\"text\" placeholder=\"Enter Profile Name\" required name=\"profileName\"/>\n");
      out.write("      </div>\n");
      out.write("      <div class=\"firstProfilePageFees\">\n");
      out.write("        <span>Profile Fees</span>\n");
      out.write("        <input type=\"number\" required min=\"1\" value=\"0\" name=\"profileFees\"/>\n");
      out.write("        <label>LE</label>\n");
      out.write("      </div> \n");
      out.write("      <div class=\"firstProfilePageFees\">\n");
      out.write("        <span>Renew Duration</span>\n");
      out.write("        <input type=\"number\" required min=\"1\" value=\"0\" name=\"renewProfileDuration\"/>\n");
      out.write("        <label>days</label>\n");
      out.write("      </div>\n");
      out.write("      <div class=\"addFirstPageProfileSubmit\">\n");
      out.write("        <input id=\"firstPageProfileSubmit\" class=\"sub\" type=\"submit\" value=\"Next Page\">      \n");
      out.write("      </div>  \n");
      out.write("    </form>\n");
      out.write("<!--/////////////////////////////////////////////////////////////////////////////////////////////-->\n");
      out.write("    <form class=\"secondPageProfile overflow-auto\" id=\"secondPageProfile\">\n");
      out.write("      <div class=\"secondProfilePageTitle\">\n");
      out.write("        <span id=\"secondProfilePageTitle\">Add Services to </span>\n");
      out.write("      </div>\n");
      out.write("      <div class=\"secondProfilePageName\">\n");
      out.write("        <span>Add Service</span>\n");
      out.write("        <select class=\"secondPageProfileSelectService\" name=\"selectService\" id=\"selectService\">\n");
for(int i = 0; i < service.getAllServices().size(); i++){
      out.write("\n");
      out.write("          <option>");
      out.print(service.getAllServices().elementAt(i).getSname());
      out.write("</option>\n");
}
      out.write("     \n");
      out.write("        </select>\n");
      out.write("        <input type=\"button\" id=\"addServiceButton\" value=\"+\"/>\n");
      out.write("      </div>\n");
      out.write("      <div class=\"allSelectedServices\" id=\"allSelectedServices\"></div>\n");
      out.write("      <div class=\"addSecondPageProfileSubmit\">\n");
      out.write("        <input type=\"hidden\" value=\"\" id=\"allServicesNames\" name=\"allServicesName\"/>\n");
      out.write("        <input type=\"hidden\" value=\"\" id=\"profileNameServices\" name=\"profileNameServices\"/>\n");
      out.write("        <input id=\"secondPageProfileSubmit\" class=\"sub\" type=\"submit\" value=\"Next Page\"/>  \n");
      out.write("      </div>  \n");
      out.write("    </form>\n");
      out.write("  </div>\n");
      out.write("<!-- Profile Area -->\n");
      out.write("<!-- Footer -->\n");
      out.write("  <footer class=\"container-fluid footerOrange\">\n");
      out.write("    <p class=\"text-center\">Welcome to Admin Portal of Orange Mobile Services</ps>\n");
      out.write("  </footer>\n");
      out.write("<!-- Footer -->  \n");
      out.write("<script src=\"https://code.jquery.com/jquery-3.4.1.slim.min.js\" integrity=\"sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n\" crossorigin=\"anonymous\"></script>\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>\n");
      out.write("<script src=\"../js/all.js\"></script>\n");
      out.write("<script src=\"../js/bootstrap.min.js\"></script>    ");
      out.write("\n");
      out.write("<script src=\"../js/addProfile.js\"></script>  \n");
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
