package pdfusingitext;

import Database.ConnectDB;
import SystemObjects.InvoiceSheet;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import com.itextpdf.layout.element.Table;  
import com.itextpdf.text.Font.FontFamily;
import java.sql.Date;


public class PdfUsingItext 
{
    
    public void start (InvoiceSheet oneCustomerIvoice)
    {
         String FILE_NAME = "src" + File.separatorChar+ "allPDFs" +File.separatorChar + oneCustomerIvoice.getCustomerName()+ ";"+ 
                java.time.LocalDate.now() + ";"+ oneCustomerIvoice.getCustomerNumber().substring(3) + ";"+ ".pdf";
        Document document = new Document();
        try 
        {
            Font f = new Font();
            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
            document.open();
            Paragraph p = new Paragraph("Invoice", f);
            f.setColor(BaseColor.ORANGE);
            f.setSize(20);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            Paragraph p2 = new Paragraph();
            p2.add("Date of Bill: " + java.time.LocalDate.now()); //no alignment
            document.add(p2);
            document.add(new Paragraph("Time: "+  LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME) ));
            document.add(new Paragraph("Bill Number: " + oneCustomerIvoice.getBillId()));
            document.add(new Paragraph("Customer Name: " + oneCustomerIvoice.getCustomerName()));
            document.add(new Paragraph("Phone Number: " + oneCustomerIvoice.getCustomerNumber().substring(3)));          
            document.add(new Paragraph("Address: " + oneCustomerIvoice.getAddress()));
            document.add(new Paragraph("Customer on Profile: "+oneCustomerIvoice.getProfileName()));
            document.add(new Paragraph(" "));


            Image image = Image.getInstance("src"+File.separatorChar+"img"+File.separatorChar+"logo-orange.png");
            image.scaleAbsolute(50, 50);
            image.setAbsolutePosition(500f, 750f);
            document.add(image);

           
            ConnectDB c = new ConnectDB();

            ///////////////////////////////////////////
//            PdfPCell totalafter = null ;
//            Paragraph t = new Paragraph( String.format("%f", oneCustomerIvoice.getTotalInvoiceAfter()));
//            totalafter.addElement(p);
//            invoicetable.addCell(totalafter);
                
   PdfPTable table = new PdfPTable(6); // Create 2 columns in table.
 
                  // Create cells
                  PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
                  PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
                  PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));
                  PdfPCell cell4 = new PdfPCell(new Paragraph("Cell 4"));
                  PdfPCell cell5 = new PdfPCell(new Paragraph("Cell 5"));
                  PdfPCell cell6 = new PdfPCell(new Paragraph("Cell 6"));
                  PdfPCell cell11 = new PdfPCell(new Paragraph("Cell 1"));

                 

 
                  // Add cells in table
                  table.addCell(cell1);
                  table.addCell(cell2);
                  table.addCell(cell3);
                  table.addCell(cell4);
                  table.addCell(cell5);
                  table.addCell(cell6);
                  table.addCell(cell11);

 

            ///////////////////////////////////////////////
            
            
            document.add(table);
            document.close();

            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }

    public static void main(String[] args) 
    {
        PdfUsingItext itext = new PdfUsingItext();
        itext.start(new InvoiceSheet(2, "Amr Walid", "00201222728511", "Agouza", "Orange 100", 100f, 50f, 30f,
                573.6f, 0f, 100f,773.6f, 851f, new Date(20200512)));
        
        
        
    }

}
