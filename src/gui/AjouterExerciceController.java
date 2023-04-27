/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Exercice;
import entities.client;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.mail.MessagingException;
import services.ClientCRUD;
import services.ExerciceCRUD;
import services.MailSender;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AjouterExerciceController implements Initializable {

    @FXML
    private TextField tfNomExercice;
    @FXML
    private TextField tfDuration;
    @FXML
    private TextField tfRepetation;
    @FXML
    private TextArea tfInstruction;
    @FXML
    private Button btnAjouter;
    @FXML
    private ComboBox<String> cb_equipement; // Changed the generic type
    ObservableList<String> optionsEquipement = FXCollections.observableArrayList();
    @FXML
    private Button BtnAnnuler;
    Map<String, Integer> maMap = new HashMap<String, Integer>();
    @FXML
    private ImageView imageuploadedID;
    @FXML
    private Button BtnAjouterImage;
    
    String imagePath="imgSportConnect/";
    File selectedFile;

    /**
     * Initializes the controller class.
     */
  @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillcomboExercice();
        cb_equipement.setItems(optionsEquipement); // Set the items of the ComboBox
    }

    public void fillcomboExercice() {
        

        try {
            Connection cnx = MyConnection.getInstance().getCnx();
            String req = " select * from equipement";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while (rs.next()) {
                maMap.put(rs.getString("nom_equipement"), rs.getInt("id"));


                optionsEquipement.add(rs.getString("nom_equipement"));
            }

        } catch (SQLException ex) {

        }
    }

    @FXML
    private void AjouterExercice(ActionEvent event) throws SQLException {
        
        if(tfNomExercice.getText().isEmpty())
        {
             Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Champs de nom exercice ne doit pas etre vide !");
            alertType.show();
        
        } else if(tfNomExercice.getText().matches(".*[0-9]+.*"))
        {
            Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Nom contient seulement des caractéres");
            alertType.show();
            
        }else if(tfNomExercice.getText().length()<=5)
        {
             Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Nom contient au minimum 5 caractéres");
            alertType.show();
        
        }
        else if(tfDuration.getText().isEmpty())
        {
                Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Champs de duration exercice ne doit pas etre vide !");
            alertType.show();
        
        }
        else if(Integer.parseInt(tfDuration.getText()) <=0)
        {
               Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Champs de duration exercice doit etre positif !");
            alertType.show();
        }
        else if(tfDuration.getText().toString().matches(".*[a_z]+.*")){
          Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Champs de duration exercice contient seulement des chiffres!");
            alertType.show();    
            
        }else if(cb_equipement.getValue() == null) 
        {
             Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Veuillez choisir un equipement");
            alertType.show();   
            
        }
         else if(imagePath =="imgSportConnect/")
        {
             Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Choisir une image");
            alertType.show();
            
        }
          if(tfInstruction.getText().isEmpty())
        {
             Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Champs de nom exercice ne doit pas etre vide !");
            alertType.show();
        
        }
        
        else {
        
        String resNomExercice = tfNomExercice.getText();
        int resDuration = Integer.parseInt(tfDuration.getText());
        int resRepetation = Integer.parseInt(tfRepetation.getText());
        String resInstruction = tfInstruction.getText();

int resultat = maMap.get(cb_equipement.getValue());

        Exercice t = new Exercice(resultat, resNomExercice, imagePath, resDuration, resRepetation, resInstruction);
       ExerciceCRUD pcd = new ExerciceCRUD();
        pcd.ajouter(t);
        
         Alert alertType = new Alert(Alert.AlertType.INFORMATION);
            alertType.setTitle("");
            alertType.setHeaderText("L'ajoute est éffectué avec succés");
            alertType.show();
            
            //mail sender 
         try {
            List<client> l =  ClientCRUD.getList();
            for(client c : l)
            {
             MailSender.sendEmail(c.getEmail(), "Un nouveau exrcice a été ajouté ", "Bonjour "+c.getPrenom()+" "+c.getNom()+"\n l'exercice "+t.getNomExercice()+" est ajouté à notre exercices \n Consultez notre application et restez en bonne santé. ");
            }
            
   
} catch (MessagingException e) {
    e.printStackTrace();
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
        imagePath+=chGenere+selectedFile.getName();
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
    }


}
