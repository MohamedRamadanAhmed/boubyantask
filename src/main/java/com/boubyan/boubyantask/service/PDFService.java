package com.boubyan.boubyantask.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class PDFService {

    public ByteArrayOutputStream createPDFTable(int columnNumber, List<String> cells) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream file = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, file);
        document.open();
        PdfPTable table = new PdfPTable(columnNumber);
        addTableRow(table, Arrays.asList("Course Name", "Start Date", "End Date"), 2, Font.FontFamily.TIMES_ROMAN, 20);
        addTableRow(table, cells, 2, Font.FontFamily.COURIER, 16);
        document.add(table);
        document.close();

        return file;
    }

    private void addTableRow(PdfPTable table, List<String> headers, int width, Font.FontFamily fontFamily, float fontSize) {
        headers.forEach(cell -> {
            PdfPCell pdfPCell = new PdfPCell();
            pdfPCell.setBorderWidth(width);
            pdfPCell.setPhrase(new Phrase(cell, new Font(fontFamily, fontSize)));
            table.addCell(pdfPCell);
        });
    }
}
