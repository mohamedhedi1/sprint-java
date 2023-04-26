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
import entities.Exercice;
import java.util.List;

public class PdfGenerator {

    public PdfGenerator() {
    }
    
 public static void GenererPdf(String entity){
   try {
         // Créer un nouveau document PDF
         Document document = new Document();
         // Obtenir le chemin d'accès du dossier de téléchargement par défaut de Windows
        // String downloadFolderPath = System.getProperty("user.home") + "\\Downloads\\";
        String downloadFolderPath =   "C:\\xampp\\htdocs\\pdf\\";
        // Nom du fichier de sortie
         String outputFileName = "fiche.pdf";
         // Chemin complet pour le fichier de sortie
         String outputFilePath = downloadFolderPath + outputFileName;
         // Écrire dans un fichier de sortie dans le dossier de téléchargement par défaut de Windows
         PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));
         // Ouvrir le document
         document.open();
         Image im = Image.getInstance("http://localhost/imgSportConnect/banner-bg.jpg");
          im.scaleAbsolute(500,200);
         document.add(im);
         // Ajouter un nouveau paragraphe contenant le texte
         document.add(new Paragraph("Nos "+entity+"\n \n \n"));
         
         
         if(entity == "equipements")
         { int nb = 2;
            
             EquipementCRUD equipementCRUD = new EquipementCRUD();
         List<Equipement> l = equipementCRUD.getList();
         int taille  = l.size();
         
         PdfPTable table = new PdfPTable(nb);
         PdfPCell cell = new PdfPCell();
         cell.setColspan(3);
         table.addCell(cell);
         table.addCell("Nom equipement");
         table.addCell("Image equipement");
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
         }
         
         if(entity == "exercices")
         { int nb =5;
         ExerciceCRUD exercieCRUD =new  ExerciceCRUD();
          List<Exercice> l = exercieCRUD.getList();
         int taille  = l.size();
         
         PdfPTable table = new PdfPTable(nb);
         PdfPCell cell = new PdfPCell();
        // cell.setColspan(1);
         //table.addCell(cell);
         table.addCell("Nom exercice");
         table.addCell("Image exercice");
         table.addCell("Duration exercice");
         table.addCell("Nombre repetition");
         table.addCell("Instruction");
         for(Exercice eq : l)
          
         {
             //System.out.println(eq.getImageEquipement());
              table.addCell(eq.getNomExercice());
              Image image = Image.getInstance(eq.getImageExercice());
              float largeurImage = 70f;
              float hauteurImage = 70f;
              table.addCell(image);
              table.addCell(String.valueOf(eq.getDuration()));
              table.addCell(String.valueOf(eq.getRepetation()));
              table.addCell(eq.getInstruction());
              
              
         }
          document.add(table);
         
         
         }
       
        
         
         // Fermer le document
         document.close();
         System.out.println("Le fichier PDF a été généré avec succès et téléchargé dans le dossier Downloads !");
      } catch (Exception e) {
         e.printStackTrace();
      }
  }

}