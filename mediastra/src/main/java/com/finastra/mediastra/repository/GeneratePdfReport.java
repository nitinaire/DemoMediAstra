package com.finastra.mediastra.repository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finastra.mediastra.entity.Employee;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePdfReport {

    private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

    public static ByteArrayInputStream citiesReport(List<Employee> employees) {

    	Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
          
        	 PdfWriter.getInstance(document, out);
            document.open();
          
            // Add Text to PDF file ->
          Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
          Paragraph para = new Paragraph( "Intake Report", font);
          para.setAlignment(Element.ALIGN_CENTER);
          document.add(para);
          document.add(Chunk.NEWLINE);
          
          PdfPTable table = new PdfPTable(5);
          // Add PDF Table Header ->
            Stream.of("SR No", "NAME", "CITY" ,"INTAKE","ADDRESS")
              .forEach(headerTitle -> {
                  PdfPCell header = new PdfPCell();
                  Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                  header.setBackgroundColor(BaseColor.ORANGE);
                  header.setHorizontalAlignment(Element.ALIGN_CENTER);
                  header.setBorderWidth(2);
                  header.setPhrase(new Phrase(headerTitle, headFont));
                  table.addCell(header);
              });
             int i=1;
            for (Employee employee : employees) {
            	 PdfPCell srNo = new PdfPCell(new Phrase(String.valueOf(i)));
            	 srNo.setPaddingLeft(4);
            	 srNo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	 srNo.setHorizontalAlignment(Element.ALIGN_CENTER);
                   table.addCell(srNo);
                   i++;
                   
                   PdfPCell name = new PdfPCell(new Phrase(employee.getFirstName().toString().concat(" ").concat(employee.getLastName().toString())));
                   name.setPaddingLeft(4);
                   name.setVerticalAlignment(Element.ALIGN_MIDDLE);
                   name.setHorizontalAlignment(Element.ALIGN_CENTER);
                     table.addCell(name);
                     
                     
                     PdfPCell city = new PdfPCell(new Phrase(employee.getCity() !=null?employee.getCity():""));
                     city.setPaddingLeft(4);
                     city.setVerticalAlignment(Element.ALIGN_MIDDLE);
                     city.setHorizontalAlignment(Element.ALIGN_LEFT);
                     table.addCell(city);
                     
            	
                     
              PdfPCell intake = new PdfPCell(new Phrase(employee.getIsIntake().equals("1") ?"YES":"NO"));
              intake.setPaddingLeft(4);
              intake.setVerticalAlignment(Element.ALIGN_MIDDLE);
              intake.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(intake);
 
               
                
                PdfPCell lastNameCell = new PdfPCell(new Phrase(employee.getVacAddress()!=null?employee.getVacAddress():""));
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                lastNameCell.setPaddingRight(4);
                table.addCell(lastNameCell);
               
            }
            document.add(table);
           
            
            document.close();
        }catch(DocumentException e) {
          logger.error(e.toString());
        }
        
    return new ByteArrayInputStream(out.toByteArray());
  }
}