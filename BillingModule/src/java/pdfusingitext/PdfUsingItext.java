package pdfusingitext;

import Database.databaseConnection;
import SystemObjects.CustomerProfile;
import SystemObjects.FreeUnit;
import SystemObjects.InvoiceSheet;
import SystemObjects.OCC;
import SystemObjects.One_Time;
import SystemObjects.Services;
import SystemObjects.UDR;
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
import com.itextpdf.text.html.WebColors;
import java.sql.Date;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Vector;


public class PdfUsingItext 
{
    
    private String dateName (int d)
    {
        String monthName = null;
        if (d == 1)
        {
            monthName = "JANUARY";
        }
        else if (d == 2)
        {
            monthName = "FEBRAUARY";
        }
        else if (d == 3)
        {
            monthName = "MARCH";
        }
        else if (d == 4)
        {
            monthName = "APRIL";
        }
        else if (d == 5)
        {
           monthName = "MAY";
        }
        else if (d == 6)
        {
            monthName = "JUNE";
        }
        else if (d == 7)
        {
           monthName = "JULY";
        }
        else if (d == 8)
        {
            monthName = "AUGEST";
        }
        else if (d == 9)
        {
            monthName = "SEPTEMBER";
        }
        else if (d == 10)
        {
            monthName = "OCTOBER";
        }        
        else if (d == 11)
        {
            monthName = "NOVEMBER";
        }        
        else if (d == 12)
        {
            monthName = "DECEMBER";
        }  
        return monthName;
    }

    public void start(InvoiceSheet oneCustomerIvoice) 
    {

        String FILE_NAME = "C:"+File.separatorChar+"Users"+File.separatorChar+"amrws"+File.separatorChar+"Desktop" +File.separatorChar +"BillingModule"+File.separatorChar+
                "src" + File.separatorChar + "java" + File.separatorChar + "allPDFs" + File.separatorChar + oneCustomerIvoice.getCustomerName() + ";"
                + oneCustomerIvoice.getEndDate() + ";" + oneCustomerIvoice.getCustomerNumber().substring(3) + ";" + ".pdf";
        Document document = new Document();
        try {

            Database.databaseConnection db = new databaseConnection();
            Vector<OCC> occ = new Vector();
            occ = db.select_from_occ(oneCustomerIvoice.getCustomerNumber());
            int i, j = 0;

            Font f = new Font();
            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
            document.open();
            Paragraph p = new Paragraph("Invoice - " + dateName(Integer.parseInt(oneCustomerIvoice.getEndDate().substring(4, 6))), f);
            f.setColor(WebColors.getRGBColor("#FF7900"));
            f.setSize(20);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
////////////////// first data table ///////////////////////////////////////////////

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            Services services = new Services();
            One_Time onetime;

            Paragraph p2 = new Paragraph();
            p2.add("Date of Bill: " + oneCustomerIvoice.getEndDate().substring(6,8)+" - "+oneCustomerIvoice.getEndDate().substring(4,6)+
                    " - "+oneCustomerIvoice.getEndDate().substring(0,4)); //no alignment
            document.add(p2);
            document.add(new Paragraph("Time: " + LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME)));
            document.add(new Paragraph("Bill Number: " + oneCustomerIvoice.getBillId()));

            for (i = 0; i < occ.size(); i++) 
            {
                if (occ.elementAt(i).getstype().equals("recurring")) 
                {

                    services = db.selectall_from_services(occ.elementAt(i).getsid());
                    document.add(new Paragraph("Recurring service: " + services.getSname()));

                }
                if (occ.elementAt(i).getstype().equals("onetime")) 
                {
                    onetime = db.select_from_one_time(occ.elementAt(i).getsid());
                    document.add(new Paragraph("Onetime service: " + onetime.osname));

                }
            }

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            PdfPTable datatable = new PdfPTable(2);
            datatable.setWidthPercentage(95);

            datatable.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cellHeader = new PdfPCell(new Paragraph("Customer Info"));
            cellHeader.setColspan(12);
            cellHeader.setPaddingBottom(8);
            cellHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellHeader.setBackgroundColor(WebColors.getRGBColor("#fd7e14"));
            datatable.addCell(cellHeader);

            datatable.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell x3 = new PdfPCell(new Paragraph("Customer Name"));
            x3.setPaddingBottom(8);
            x3.setHorizontalAlignment(Element.ALIGN_CENTER);
            x3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            datatable.addCell(x3);

            datatable.addCell(oneCustomerIvoice.getCustomerName());

            datatable.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell x4 = new PdfPCell(new Paragraph("Customer Number"));
            x4.setPaddingBottom(8);
            x4.setHorizontalAlignment(Element.ALIGN_CENTER);
            x4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            datatable.addCell(x4);

            datatable.addCell(oneCustomerIvoice.getCustomerNumber().substring(3));

            datatable.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell x1 = new PdfPCell(new Paragraph("Address"));
            x1.setPaddingBottom(8);
            x1.setHorizontalAlignment(Element.ALIGN_CENTER);
            x1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            datatable.addCell(x1);

            datatable.addCell(oneCustomerIvoice.getAddress());

            datatable.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell x2 = new PdfPCell(new Paragraph("Customer on Profile"));
            x2.setPaddingBottom(8);
            x2.setHorizontalAlignment(Element.ALIGN_CENTER);
            x2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            datatable.addCell(x2);

            datatable.addCell(oneCustomerIvoice.getProfileName());

            document.add(datatable);
            
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            Image image = Image.getInstance("C:"+File.separatorChar+"Users"+File.separatorChar+"amrws"+File.separatorChar+"Desktop" +File.separatorChar +"BillingModule"+File.separatorChar+"src" 
                    + File.separatorChar + "java" + File.separatorChar + "img" + File.separatorChar + "logo-orange.png");
            image.scaleAbsolute(50, 50);
            image.setAbsolutePosition(500f, 750f);
            document.add(image);

            ////////////////////////////// Free units table ///////////////////////////////
            PdfPTable freeunits = new PdfPTable(2);
            freeunits.setWidthPercentage(95);

            FreeUnit fu = new FreeUnit();
            CustomerProfile cp = new CustomerProfile();
            cp = db.select_from_customer_profile(oneCustomerIvoice.getCustomerNumber());
            fu = db.select_from_freeUnits(cp.getProfileID());

            freeunits.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell unitsHeader = new PdfPCell(new Paragraph(" Profile Units "));
            unitsHeader.setColspan(12);
            unitsHeader.setPaddingBottom(8);
            unitsHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            unitsHeader.setBackgroundColor(WebColors.getRGBColor("#fd7e14"));
            freeunits.addCell(unitsHeader);

            freeunits.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell x11 = new PdfPCell(new Paragraph("Voice Units for orange"));
            x11.setPaddingBottom(8);
            x11.setHorizontalAlignment(Element.ALIGN_CENTER);
            x11.setBackgroundColor(BaseColor.LIGHT_GRAY);
            freeunits.addCell(x11);

            freeunits.addCell(String.valueOf(fu.getFUVoiceOnNet()));

            freeunits.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell x14 = new PdfPCell(new Paragraph("Voice Units for other networks"));
            x14.setPaddingBottom(8);
            x14.setHorizontalAlignment(Element.ALIGN_CENTER);
            x14.setBackgroundColor(BaseColor.LIGHT_GRAY);
            freeunits.addCell(x14);

            freeunits.addCell(String.valueOf(fu.getFUVoiceCrossNet()));

            freeunits.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell x10 = new PdfPCell(new Paragraph("SMS Units for orange"));
            x10.setPaddingBottom(8);
            x10.setHorizontalAlignment(Element.ALIGN_CENTER);
            x10.setBackgroundColor(BaseColor.LIGHT_GRAY);
            freeunits.addCell(x10);

            freeunits.addCell(String.valueOf(fu.getFUSMSOnNet()));

            freeunits.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell x12 = new PdfPCell(new Paragraph("SMS Units for other networks"));
            x12.setPaddingBottom(8);
            x12.setHorizontalAlignment(Element.ALIGN_CENTER);
            x12.setBackgroundColor(BaseColor.LIGHT_GRAY);
            freeunits.addCell(x12);

            freeunits.addCell(String.valueOf(fu.getFUSMSCrossNet()));

            freeunits.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell x15 = new PdfPCell(new Paragraph("Internet Units"));
            x15.setPaddingBottom(8);
            x15.setHorizontalAlignment(Element.ALIGN_CENTER);
            x15.setBackgroundColor(BaseColor.LIGHT_GRAY);
            freeunits.addCell(x15);

            freeunits.addCell(String.valueOf(fu.getFUInternet()));

            document.add(freeunits);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

////////////////////// Full summerized data /////////////////////////////////////////////////
            PdfPTable invoicetable = new PdfPTable(2);
            invoicetable.setWidthPercentage(95);

            invoicetable.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cell = new PdfPCell(new Paragraph("Invoicing Costs"));
            cell.setColspan(12);
            cell.setPaddingBottom(8);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(WebColors.getRGBColor("#fd7e14"));
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

            invoicetable.addCell(String.valueOf(oneCustomerIvoice.getTotalSMSCost()) + " L.E");

            PdfPCell cell3 = new PdfPCell(new Paragraph("Total Data"));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell3.setPaddingBottom(5);
            cell3.setPaddingTop(5);
            invoicetable.addCell(cell3);

            invoicetable.addCell(String.valueOf(oneCustomerIvoice.getTotalDataCost()) + " L.E");

            PdfPCell cell7 = new PdfPCell(new Paragraph("Recurring service fees"));
            cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell7.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell7.setPaddingBottom(5);
            cell7.setPaddingTop(5);
            invoicetable.addCell(cell7);

            invoicetable.addCell(String.valueOf(oneCustomerIvoice.getRecurringFees()) + " L.E");

            PdfPCell cell8 = new PdfPCell(new Paragraph("One time service fees"));
            cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell8.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell8.setPaddingBottom(5);
            cell8.setPaddingTop(5);
            invoicetable.addCell(cell8);

            invoicetable.addCell(String.valueOf(oneCustomerIvoice.getOneTimeServiceFees()) + " L.E");
            
            PdfPCell cell9 = new PdfPCell(new Paragraph("Rate Plan fees"));
            cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell9.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell9.setPaddingBottom(5);
            cell9.setPaddingTop(5);
            invoicetable.addCell(cell9);

            invoicetable.addCell(String.valueOf(oneCustomerIvoice.getProfileFees()) + " L.E");            

            PdfPCell cell4 = new PdfPCell(new Paragraph("Total Before taxes"));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell4.setPaddingBottom(5);
            cell4.setPaddingTop(5);
            invoicetable.addCell(cell4);

            invoicetable.addCell(String.valueOf(oneCustomerIvoice.getTotalInvoiceBefore()) + " L.E");

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

            document.add(invoicetable);

            Rectangle rect = new Rectangle(577, 825, 18, 12); // you can resize rectangle 
            rect.enableBorderSide(1);
            rect.enableBorderSide(2);
            rect.enableBorderSide(4);
            rect.enableBorderSide(8);
            rect.setBorderColor(BaseColor.BLACK);
            rect.setBorderWidth(1);
            document.add(rect);

 
            
            ////////////////////////////// PAGE 2 //////////////////////////////////////////////  
            Vector<UDR> customers = new Vector();
            customers = db.select_from_udr(oneCustomerIvoice.getCustomerNumber());
            String sname = null;

            document.newPage();

            Paragraph x = new Paragraph("Full Customer logs ", f);
            f.setColor(WebColors.getRGBColor("#fd7e14"));
            f.setSize(20);
            x.setAlignment(Element.ALIGN_CENTER);
            document.add(x);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(95);

            table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell cells = new PdfPCell(new Paragraph("From"));
            cells.setPaddingBottom(8);
            cells.setHorizontalAlignment(Element.ALIGN_CENTER);
            cells.setBackgroundColor(WebColors.getRGBColor("#fd7e14"));
            table.addCell(cells);

            PdfPCell cells1 = new PdfPCell(new Paragraph("To"));
            cells1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cells1.setBackgroundColor(WebColors.getRGBColor("#fd7e14"));
            cells1.setPaddingBottom(5);
            cells1.setPaddingTop(5);
            table.addCell(cells1);

            PdfPCell cellsx = new PdfPCell(new Paragraph("Service Types"));
            cellsx.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellsx.setBackgroundColor(WebColors.getRGBColor("#fd7e14"));
            cellsx.setPaddingBottom(5);
            cellsx.setPaddingTop(5);
            table.addCell(cellsx);

            PdfPCell cells2 = new PdfPCell(new Paragraph("Date"));
            cells2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cells2.setBackgroundColor(WebColors.getRGBColor("#fd7e14"));
            cells2.setPaddingBottom(5);
            cells2.setPaddingTop(5);
            table.addCell(cells2);

            PdfPCell cells3 = new PdfPCell(new Paragraph("Time"));
            cells3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cells3.setBackgroundColor(WebColors.getRGBColor("#fd7e14"));
            cells3.setPaddingBottom(5);
            cells3.setPaddingTop(5);
            table.addCell(cells3);

            PdfPCell cells4 = new PdfPCell(new Paragraph("Duration"));
            cells4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cells4.setBackgroundColor(WebColors.getRGBColor("#fd7e14"));
            cells4.setPaddingBottom(5);
            cells4.setPaddingTop(5);
            table.addCell(cells4);

            for (i = 0; i < customers.size(); i++) {

                sname = db.select_from_services(customers.elementAt(i).getServiceID());

                table.addCell(customers.elementAt(i).getDialA().substring(3));
                if (customers.elementAt(i).getServiceID() != 3) {
                    table.addCell(customers.elementAt(i).getDialB().substring(3));
                } else {
                    table.addCell(customers.elementAt(i).getDialB());
                }

                table.addCell(sname);
//                table.addCell(customers.elementAt(i).getStartDate());
                table.addCell(customers.elementAt(i).getStartDate().substring(6,8)+"-"+customers.elementAt(i).getStartDate().substring(4,6)+
                    "-"+customers.elementAt(i).getStartDate().substring(0,4));
                table.addCell(customers.elementAt(i).getStartTime());

                if (customers.elementAt(i).getServiceID() == 1) {
                    table.addCell(String.valueOf(customers.elementAt(i).getDurationMsgVolume()) + " Seconds");
                } else if (customers.elementAt(i).getServiceID() == 2) {
                    table.addCell(String.valueOf(customers.elementAt(i).getDurationMsgVolume()) + " Msgs");
                } else if (customers.elementAt(i).getServiceID() == 3) {
                    table.addCell(String.valueOf(customers.elementAt(i).getDurationMsgVolume()) + " Bytes");
                }

            }

            document.add(table);
            document.add(rect);

//////////////////////////////////////////////////////////////////////////////////////            
            document.close();

            System.out.println("Done");
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
