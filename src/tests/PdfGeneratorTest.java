/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 *
 * @author Mohamed
 */
public class PdfGeneratorTest {
    public static void main(String[] args) {
      try {
         // Créer un nouveau document PDF
         Document document = new Document();
         // Obtenir le chemin d'accès du dossier de téléchargement par défaut de Windows
         String downloadFolderPath = System.getProperty("user.home") + "\\Downloads\\";
         // Nom du fichier de sortie
         String outputFileName = "exemple1.pdf";
         // Chemin complet pour le fichier de sortie
         String outputFilePath = downloadFolderPath + outputFileName;
         // Écrire dans un fichier de sortie dans le dossier de téléchargement par défaut de Windows
         PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));
         // Ouvrir le document
         document.open();
         
         
         // Ajouter un nouveau paragraphe contenant le texte
         document.add(new Paragraph("Hello World!"));
         
          // Ajouter une image
         Image image = Image.getInstance("C:\\Users\\Mohamed\\Desktop\\rio.jpg");
         document.add(image);
         
          // Ajouter un nouveau tableau
         PdfPTable table = new PdfPTable(3); // Nombre de colonnes = 3
         // Ajouter une en-tête de tableau
         PdfPCell cell = new PdfPCell(new Paragraph("En-tête de tableau"));
         cell.setColspan(3);
         table.addCell(cell);
         // Ajouter des cellules
         table.addCell("Cellule 1");
         table.addCell("Cellule 2");
         table.addCell("Cellule 3");
         table.addCell("Cellule 4");
         table.addCell("Cellule 5");
         table.addCell("Cellule 6");
         // Ajouter le tableau au document
         document.add(table);
         
         
         
         // Fermer le document
         document.close();
         System.out.println("Le fichier PDF a été généré avec succès et téléchargé dans le dossier Downloads !");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
