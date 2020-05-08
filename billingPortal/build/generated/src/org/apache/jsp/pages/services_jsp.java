package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Database_Tables.Services;
import Database.Database;

public final class services_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

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
      out.write("                        <a class=\"nav-link\" href=\"../pages/mainPage.jsp\">Home <span class=\"sr-only\">(current)</span></a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item active mx-5\">\n");
      out.write("                        <a class=\"nav-link\" href=\"../pages/customer.jsp\">Customer<span class=\"sr-only\">(current)</span></a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item active mx-5\">\n");
      out.write("                        <a class=\"nav-link\" href=\"../pages/profile.jsp\">Profile<span class=\"sr-only\">(current)</span></a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item active mx-5\">\n");
      out.write("                      <a class=\"nav-link\" href=\"../pages/services.jsp\">Services<span class=\"sr-only\">(current)</span></a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item active mx-5\">\n");
      out.write("                      <a class=\"nav-link\" href=\"#\">Billing<span class=\"sr-only\">(current)</span></a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item active mx-5\">\n");
      out.write("                        <a class=\"nav-link\" href=\"../pages/uploadCDR.jsp\">Upload CDR<span class=\"sr-only\">(current)</span></a>\n");
      out.write("                    </li>                        \n");
      out.write("                  </ul>\n");
      out.write("                </div>\n");
      out.write("          </nav>        \n");
      out.write("      </div>\n");
      out.write("  </header>\n");
      out.write("<!-- Header -->\n");
      out.write('\n');

    Database db = new Database();
    Services allServices = db.getAllServices();

      out.write("\n");
      out.write("<!-- Service Area -->\n");
      out.write("  <div class=\"container-fluid fullSize\">\n");
      out.write("      <form class=\"addService\">\n");
      out.write("      <span class=\"addSeriveTitle\">Add Service</span>\n");
      out.write("      <div class=\"addServiceInput\">\n");
      out.write("        <span>Enter Service Name</span>\n");
      out.write("        <input type=\"text\" name=\"serviceName\" placeholder=\"Serivce Name\" required>\n");
      out.write("      </div>\n");
      out.write("      <div class=\"addServiceInput\">\n");
      out.write("        <span>Service Additional Info</span>\n");
      out.write("        <select name=\"recurring\" size=\"1\">\n");
      out.write("          <option value=\"false\">Not Recurring</option>\n");
      out.write("          <option value=\"true\">Recurring</option>          \n");
      out.write("        </select>\n");
      out.write("        <select name=\"oneTimeService\" size=\"1\">\n");
      out.write("          <option value=\"false\">Not one Time</option>\n");
      out.write("          <option value=\"true\">One Time</option>          \n");
      out.write("        </select>\n");
      out.write("        <label>Service Fee</label>\n");
      out.write("        <input type=\"number\" name=\"serviceFees\" value=\"0\" min=\"0\"/>\n");
      out.write("        <h6>LE</h6>\n");
      out.write("      </div>\n");
      out.write("      <div class=\"addServiceSubmit\">\n");
      out.write("        <input id=\"su\" class=\"sub\" type=\"submit\" value=\"ADD SERVICE\">\n");
      out.write("      </div>\n");
      out.write("    </form>\n");
      out.write("    <div class=\"showAllServices overflow-auto\">\n");
      out.write("      <table class=\"table table-hover\">\n");
      out.write("        <thead>\n");
      out.write("          <tr>\n");
      out.write("            <th scope=\"col\">#</th>\n");
      out.write("            <th scope=\"col\">Service ID</th>\n");
      out.write("            <th scope=\"col\">Service Name</th>\n");
      out.write("            <th scope=\"col\">Type Of Service</th>\n");
      out.write("            <th scope=\"col\">Service Fee</th>            \n");
      out.write("          </tr>\n");
      out.write("        </thead>\n");
      out.write("        <tbody>\n");
for (int i = 0; i < allServices.getAllServices().size(); i++){
      out.write("\n");
      out.write("          <tr>\n");
      out.write("            <th scope=\"row\">");
      out.print(i+1);
      out.write("</th>\n");
      out.write("            <td>");
      out.print(allServices.getAllServices().elementAt(i).getSid());
      out.write("</td>\n");
      out.write("            <td>");
      out.print(allServices.getAllServices().elementAt(i).getSname());
      out.write("</td>\n");
if (allServices.getAllServices().elementAt(i).getIsOneTime() == false && allServices.getAllServices().elementAt(i).getIsRecurring()== false){
      out.write("\n");
      out.write("            <td>Normal Service</td>\n");
      out.write("            <td>It differ from Profile to another</td>\n");
}else{
      out.write('\n');
if (allServices.getAllServices().elementAt(i).getIsOneTime() == true){
      out.write("\n");
      out.write("            <td>One Time Service</td>\n");
}else if (allServices.getAllServices().elementAt(i).getIsRecurring()== true){
      out.write("\n");
      out.write("            <td>Recurring Service</td>\n");
}
      out.write("\n");
      out.write("            <td>");
      out.print(allServices.getAllServices().elementAt(i).getServiceFees());
      out.write(" LE</td>\n");
}
      out.write("\n");
      out.write("          </tr>\n");
}
      out.write("\n");
      out.write("        </tbody>\n");
      out.write("      </table>\n");
      out.write("    </div>\n");
      out.write("  </div>\n");
      out.write("<!-- Service Area -->\n");
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
      out.write("<script src=\"../js/services.js\"></script>  \n");
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