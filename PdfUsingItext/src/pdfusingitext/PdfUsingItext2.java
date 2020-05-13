package pdfusingitext;

import Database.ConnectDB;
import SystemObjects.InvoiceSheet;
import SystemObjects.OCC;
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
import java.sql.Date;

public class PdfUsingItext2 {

    public void start(InvoiceSheet oneCustomerIvoice) {
        String FILE_NAME = "src" + File.separatorChar + "allPDFs" + File.separatorChar + oneCustomerIvoice.getCustomerName() + ";"
                + java.time.LocalDate.now() + ";" + oneCustomerIvoice.getCustomerNumber().substring(3) + ";" + ".pdf";
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

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            Paragraph p2 = new Paragraph();
            p2.add("Date of Bill: " + java.time.LocalDate.now()); //no alignment
            document.add(p2);
            document.add(new Paragraph("Time: " + LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME)));
            document.add(new Paragraph("Bill Number: " + oneCustomerIvoice.getBillId()));
            document.add(new Paragraph("Customer Name: " + oneCustomerIvoice.getCustomerName()));
            document.add(new Paragraph("Phone Number: " + oneCustomerIvoice.getCustomerNumber().substring(3)));
            document.add(new Paragraph("Address: " + oneCustomerIvoice.getAddress()));
            document.add(new Paragraph("Customer on Profile: " + oneCustomerIvoice.getProfileName()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            Image image = Image.getInstance("src" + File.separatorChar + "img" + File.separatorChar + "logo-orange.png");
            image.scaleAbsolute(50, 50);
            image.setAbsolutePosition(500f, 750f);
            document.add(image);

            PdfPTable invoicetable = new PdfPTable(2);
            invoicetable.setWidthPercentage(95);

            invoicetable.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell = new PdfPCell(new Paragraph("Invoicing Costs"));
            cell.setColspan(12);
            cell.setPaddingBottom(8);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.ORANGE);
            invoicetable.addCell(cell);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Total voice"));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell1.setPaddingBottom(5);
            cell1.setPaddingTop(5);
            invoicetable.addCell(cell1);

            invoicetable.addCell(String.valueOf(oneCustomerIvoice.getTotalVoiceCost()) + "L.E");

            PdfPCell cell2 = new PdfPCell(new Paragraph("Total SMS"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell2.setPaddingBottom(5);
            cell2.setPaddingTop(5);
            invoicetable.addCell(cell2);

            invoicetable.addCell(String.valueOf(oneCustomerIvoice.getTotalSMSCost()) + "L.E");

            PdfPCell cell3 = new PdfPCell(new Paragraph("Total Data"));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell3.setPaddingBottom(5);
            cell3.setPaddingTop(5);
            invoicetable.addCell(cell3);

            invoicetable.addCell(String.valueOf(oneCustomerIvoice.getTotalDataCost()) + "L.E");

            PdfPCell cell7 = new PdfPCell(new Paragraph("Recurring service fees"));
            cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell7.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell7.setPaddingBottom(5);
            cell7.setPaddingTop(5);
            invoicetable.addCell(cell7);

            invoicetable.addCell(String.valueOf(oneCustomerIvoice.getRecurringFees()) + "L.E");

            PdfPCell cell8 = new PdfPCell(new Paragraph("One time service fees"));
            cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell8.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell8.setPaddingBottom(5);
            cell8.setPaddingTop(5);
            invoicetable.addCell(cell8);

            invoicetable.addCell(String.valueOf(oneCustomerIvoice.getOneTimeServiceFees()) + "L.E");

            PdfPCell cell4 = new PdfPCell(new Paragraph("Total Before taxes"));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell4.setPaddingBottom(5);
            cell4.setPaddingTop(5);
            invoicetable.addCell(cell4);

            invoicetable.addCell(String.valueOf(oneCustomerIvoice.getTotalInvoiceBefore()) + "L.E");

            PdfPCell cell5 = new PdfPCell(new Paragraph("Taxes"));
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell5.setPaddingBottom(5);
            cell5.setPaddingTop(5);
            invoicetable.addCell(cell5);

            invoicetable.addCell(" 10%");

            PdfPCell cell6 = new PdfPCell(new Paragraph("Total after taxes"));
            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell6.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell6.setPaddingBottom(5);
            cell6.setPaddingTop(5);
            invoicetable.addCell(cell6);

            invoicetable.addCell(String.valueOf(oneCustomerIvoice.getTotalInvoiceAfter()) + "L.E");

            ConnectDB c = new ConnectDB();

            document.add(invoicetable);
            document.close();

            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PdfUsingItext2 itext = new PdfUsingItext2();
        itext.start(new InvoiceSheet(2, "Amr Walid", "00201222728511", "Agouza", "Orange 100", 100f, 50f, 30f,
                573.6f, 0f, 100f, 773.6f, 851f, new Date(20200512)));

    }

}
