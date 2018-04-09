package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;

public final class SearchPage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<style>\n");
      out.write("textarea\n");
      out.write("{\n");
      out.write("    overflow-y:scroll;\n");
      out.write("}\n");
      out.write("</style>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Microminer</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h2>KWIC Microminer</h2>\n");
      out.write("        \n");
      out.write("        ");
      out.write("     \n");
      out.write("        ");
 ArrayList<String> output = (ArrayList<String>)request.getAttribute("output"); 
      out.write("\n");
      out.write("        ");
      out.write("  \n");
      out.write("        ");
 String search = (String)request.getAttribute("search"); 
      out.write("\n");
      out.write("        ");
 if(search == null) search = ""; 
      out.write("\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        ");
      out.write("\n");
      out.write("        <form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/InputServlet\" method=\"post\">\n");
      out.write("            <textarea type=\"text\" placeholder=\"Input\" name=\"input\" rows=\"15\" cols=\"150\">\n");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${bean.input}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("            </textarea>\n");
      out.write("            <br>\n");
      out.write("            <input type=\"submit\" name=\"submit\" value=\"Submit Input\"/>\n");
      out.write("        </form>\n");
      out.write("        \n");
      out.write("        ");
      out.write("\n");
      out.write("        <form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/SearchServlet\" method=\"post\">\n");
      out.write("            <br><br>\n");
      out.write("            <input type=\"text\" name=\"search\" placeholder=\"Search\" value=");
      out.print(search);
      out.write(">\n");
      out.write("            <input type=\"submit\" name=\"submit\" value=\"Submit\"/>\n");
      out.write("            <br><br>\n");
      out.write("            <textarea name=\"output\" rows=\"15\" cols=\"150\">\n");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${bean.results}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("            </textarea>\n");
      out.write("        </form>\n");
      out.write("            \n");
      out.write("        ");
      out.write("\n");
      out.write("        <form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/ClearDBServlet\" method=\"post\">\n");
      out.write("            <input type=\"submit\" name=\"clearDB\" value=\"Clear Database\"/>\n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write('\n');
      out.write("\n");
      out.write("</html>\n");
      out.write("\n");
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
