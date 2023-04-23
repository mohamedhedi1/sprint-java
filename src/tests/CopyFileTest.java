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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
public class CopyFileTest {
     public static void main(String[] args) {
        // Chemin de fichier source
        Path source = Paths.get("C:\\Users\\Mohamed\\Desktop\\rio.jpg");
        
        // Chemin de fichier de destination
        Path destination = Paths.get("C:\\xampp\\htdocs\\imgSportConnect\\rio3333.jpg");
        
        try {
            // Appeler la fonction copyFile
            copyFile(source, destination);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.out.println("Error copying file: " + e.getMessage());
        }
    }
    
    public static void copyFile(Path source, Path destination) throws IOException {
        // Copier le fichier source vers la destination
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
    }
}
