/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UploadServlet;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author ahmed
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)

public class UploadServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        Part part = req.getPart("ufile");
        String fileName = getFileName(part);
        System.out.println(fileName);
        // String applicationpath=getServletContext().getRealPath("");
        String applicationpath = System.getProperty("user.home");
        
        System.out.println(applicationpath);
        String uploadPath = applicationpath +"/NetBeansProjects/Billing-Project/CDR-Parser/cdr"; //want to generic
        System.out.println("applicationPath:" + applicationpath);
        String savePath = uploadPath + File.separator + fileName;
        System.out.println("savePath: " + savePath);

        part.write(savePath + File.separator);
        File fileSaveDir1 = new File(savePath);
        part.write(savePath + File.separator);

    }

    private String getFileName(Part part) {
        String conDis = part.getHeader("content-disposition");
        String[] items = conDis.split(";");
        for (String it : items) {
            if (it.trim().startsWith("filename")) {
                return it.substring(it.indexOf("=") + 2, it.length() - 1);
            }

        }

        return "";
    }

}