/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.log.LoggerFactory;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import entities.Equipement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import services.EquipementCRUD;
import services.MailSender;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AjouterEquipementController implements Initializable {
    

    @FXML
    private TextField tfNomEquipement;
    @FXML
    private Button BtnAjouterImage;
    @FXML
    private ImageView imageuploadedID;
    @FXML
    private Button AjouterEq;
    
    String imagePath="";
    File selectedFile;
    @FXML
    private Button BtnAnnuler;
    
    
    //pour l'image upload 
    private static final String SERVER_IMAGE_PATH = "C:\\xampp\\htdocs\\imgSportConnect\\";


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
     /* String resNomEquipment = tfNomEquipement.getText();
        EquipementCRUD pcd =new EquipementCRUD();
        
        Equipement t = new Equipement(resNomEquipment, "image");
        
        pcd.ajouter(t);
        System.out.println("oui fonctionne");*/
     
     
     
   /* @FXML
    private void uploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
         selectedFile = fileChooser.showOpenDialog(imageuploadedID.getScene().getWindow());
        if (selectedFile != null) {
            // The user selected an image file
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            Path path = Paths.get(selectedFile.getAbsolutePath());
    Path relativePath = path.subpath(path.getNameCount() - 2, path.getNameCount()).normalize();
             imagePath = relativePath.toString().replace('\\', '/');
             Image image = new Image(imagePath);
             imageuploadedID.setImage(image);
            // Add your code here to process the selected image file
        }
    }*/

    @FXML
    private void AjouterEquipement(ActionEvent event) {
        //controle de saisie 
        if(tfNomEquipement.getText().isEmpty() )
        {
            Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Champs de nom equipement ne doit pas etre vide !");
            alertType.show();
        }else 
        if(tfNomEquipement.getText().toString().matches(".*[0-9]+.*"))
        {
             Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Nom contient seulement des caractéres");
            alertType.show();
        }else if(tfNomEquipement.getText().toString().length()<=5)
        {
             Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Nom contient au minimum 5 caractéres");
            alertType.show();
        }
        else if(imagePath =="")
        {
             Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Choisir une image");
            alertType.show();
            
        }
        
        else{
        //faire traitement d'ajout
          String resNomEquipment = tfNomEquipement.getText();
        EquipementCRUD pcd =new EquipementCRUD();
        
        Equipement t = new Equipement(resNomEquipment, imagePath);
        
        pcd.ajouter(t);
        
        //mail sender 
         try {
    MailSender.sendEmail("mohamedelhedi.hamdi@esprit.tn", "Un nouveau equipement a été ajouté ", ""+t.getNomEquipement()+" est ajouté. ");
} catch (MessagingException e) {
    e.printStackTrace();
}
        
        //REDIRECTION
        try {
    FXMLLoader loader = new FXMLLoader( 
        getClass().getResource("/gui/Menu.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
} catch (IOException ex) {
    System.out.println(ex.getMessage());
}
        
        }
        
        
        
      
    }
    
    ///ajout image 
    

public String generateRandomString() {
    int length = 7;
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Random rnd = new Random();
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
        sb.append(characters.charAt(rnd.nextInt(characters.length())));
    }
    return sb.toString();
}

    @FXML
  private void uploadImage(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Image File");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    File selectedFile = fileChooser.showOpenDialog(imageuploadedID.getScene().getWindow());
    if (selectedFile != null) {
        // The user selected an image file
        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        String chGenere = generateRandomString();
        imagePath=chGenere+selectedFile.getName();
        Path source = Paths.get(selectedFile.getAbsolutePath());
        System.out.println(selectedFile.getName());
        Path destination = Paths.get("C:\\xampp\\htdocs\\imgSportConnect\\" + chGenere +selectedFile.getName());
        
        try {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Image copied successfully to " + destination.toString());
            
            Image image = new Image(selectedFile.toURI().toString());
            imageuploadedID.setImage(image);
        } catch (IOException e) {
            System.out.println("Error copying image: " + e.getMessage());
        }
    }
}

  


    @FXML
    private void AnnulerClick(ActionEvent event) {
         try {
    FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/gui/AdminFXML.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
} catch (IOException ex) {
    System.out.println(ex.getMessage());
}
    }
        
    
}
