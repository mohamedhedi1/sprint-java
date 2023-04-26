/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

/**
 *
 * @author Mohamed
 */
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OuvrirPDF {
   public static void main(String[] args) {
      // Adresse URL complète du fichier PDF
      String urlPDF = "http://localhost/pdf/fiche.pdf";
      
      // Ouvrir le fichier PDF avec le navigateur web par défaut
      Desktop desktop = Desktop.getDesktop();
      try {
         desktop.browse(new URI(urlPDF));
      } catch (IOException | URISyntaxException e) {
         e.printStackTrace();
      }
   }
}
