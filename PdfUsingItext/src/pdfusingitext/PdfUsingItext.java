/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfusingitext;

import Database.ConnectDB;
import SystemObjects.OCC;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author ahmed
 */
public class PdfUsingItext {

    public static void main(String[] args) {
        String FILE_NAME = "/home/ahmed/Desktop/aa.pdf";
        Document document = new Document();
        try {
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
            document.add(new Paragraph("Time: "+java.time.LocalTime.now()));
            document.add(new Paragraph("Customer id: "));
            document.add(new Paragraph("Customer Name: Ahmed Alsum"));
            document.add(new Paragraph("Bill ID: "));
            document.add(new Paragraph("     "));

            Image image = Image.getInstance("/home/ahmed/Desktop/orange.png");
            image.scaleAbsolute(50, 50);
            image.setAbsolutePosition(500f, 750f);
            document.add(image);

            PdfPTable invoicetable = new PdfPTable(4);
            invoicetable.setWidthPercentage(100);

            invoicetable.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell = new PdfPCell(new Paragraph("Invoicing Costs"));
            cell.setColspan(8);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.ORANGE);
            invoicetable.addCell(cell);
            PdfPCell cell1 = new PdfPCell(new Paragraph("Total voice"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            invoicetable.addCell(cell1);
            PdfPCell cell2 = new PdfPCell(new Paragraph("Total SMS"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            invoicetable.addCell(cell2);
            PdfPCell cell3 = new PdfPCell(new Paragraph("Total Data"));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            invoicetable.addCell(cell3);
            PdfPCell cell4 = new PdfPCell(new Paragraph("Tax"));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            invoicetable.addCell(cell4);

            ConnectDB c = new ConnectDB();
            List<OCC> a = c.getAllOcc();
            for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i).msisdn);
                invoicetable.addCell(Integer.toString(a.get(i).occ_id));
                invoicetable.addCell(a.get(i).msisdn);
                invoicetable.addCell(Integer.toString(a.get(i).one_rec_id));
                invoicetable.addCell(a.get(i).type_of_service);
        }
            
            document.add(invoicetable);
            document.close();

            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}