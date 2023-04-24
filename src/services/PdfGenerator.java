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
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Equipement;
import java.util.List;

public class PdfGenerator {

    public PdfGenerator() {
    }
    
 public static void GenererPdf(String entity){
   try {
         // Créer un nouveau document PDF
         Document document = new Document();
         // Obtenir le chemin d'accès du dossier de téléchargement par défaut de Windows
         String downloadFolderPath = System.getProperty("user.home") + "\\Downloads\\";
         // Nom du fichier de sortie
         String outputFileName = "exemple12.pdf";
         // Chemin complet pour le fichier de sortie
         String outputFilePath = downloadFolderPath + outputFileName;
         // Écrire dans un fichier de sortie dans le dossier de téléchargement par défaut de Windows
         PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));
         // Ouvrir le document
         document.open();
         
         
         // Ajouter un nouveau paragraphe contenant le texte
         document.add(new Paragraph("nos "+entity));
         //if(entity == "equipements")
         EquipementCRUD equipementCRUD = new EquipementCRUD();
         List<Equipement> l = equipementCRUD.getList();
         int taille  =l.size();
         
         PdfPTable table = new PdfPTable(3);
         PdfPCell cell = new PdfPCell();
         cell.setColspan(3);
         table.addCell(cell);
         for(Equipement eq : l)
          
         {
             //System.out.println(eq.getImageEquipement());
              table.addCell(eq.getNomEquipement());
              Image image = Image.getInstance(eq.getImageEquipement());
              float largeurImage = 70f;
              float hauteurImage = 70f;
              table.addCell(image);
         }
       
         document.add(table);
         
         // Fermer le document
         document.close();
         System.out.println("Le fichier PDF a été généré avec succès et téléchargé dans le dossier Downloads !");
      } catch (Exception e) {
         e.printStackTrace();
      }
  }

}