package CheckServlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class openPDF extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {   
        try 
        {
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/pdf");
            String filepath = req.getParameter("path");
            resp.setHeader("Content-Disposition", "inline; filename=’jsp.pdf'");
            FileInputStream fileout = new FileInputStream(filepath);
            int i;
            while((i  = fileout.read()) != -1)
            {
                out.write(i);
            }
            
            fileout.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
}
