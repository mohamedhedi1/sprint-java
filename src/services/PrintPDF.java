/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Mohamed
 */
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

public class PrintPDF {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Mohamed\\Downloads\\exemple12.pdf");

        try (PDDocument document = PDDocument.load(file)) {
            PrinterJob job = PrinterJob.getPrinterJob();
            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
            PrintService printService = null;

            // Choose the printer to use
            for (PrintService printer : printServices) {
                if (printer.getName().equals("Printer Name")) {
                    printService = printer;
                    break;
                }
            }

            if (printService == null) {
                System.out.println("Printer not found.");
                return;
            }

            // Set the printer name and number of copies
            PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
            attributes.add(new PrinterName(printService.getName(), null));
            attributes.add(new Copies(1));

            // Create a PDF pageable object
            PDFPageable pageable = new PDFPageable(document);

            // Set the document to be printed
            job.setPageable(pageable);

            // Print the document
            job.print(attributes);
        } catch (IOException | PrinterException e) {
            e.printStackTrace();
        }
    }
}

