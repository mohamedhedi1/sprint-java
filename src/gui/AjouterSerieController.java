/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Serie;
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javax.mail.MessagingException;
import services.ClientCRUD;
import services.MailSender;
import services.SerieCRUD;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AjouterSerieController implements Initializable {

    @FXML
    private TilePane r;
     Map<String, Integer> maMap = new HashMap<String, Integer>();
    @FXML
    private Button BtnAjouterImage;
    @FXML
    private ImageView imageuploadedID;
      String imagePath="imgSportConnect/";
    File selectedFile;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField tfNomSerie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
       fillBoxExercice();
         
    }    
      public void fillBoxExercice() {
        

        try {
            Connection cnx = MyConnection.getInstance().getCnx();
            String req = " select * from exercice";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while (rs.next()) {
                maMap.put(rs.getString("nom_exercice"), rs.getInt("id"));
               
  
            // create a checkbox
            CheckBox c = new CheckBox(rs.getString("nom_exercice"));
  
            // add label
            r.getChildren().add(c);
  
            // set IndeterMinate
            c.setIndeterminate(true); 
            }

        } catch (SQLException ex) {

        }
    }

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
      private void AjouterSerie(ActionEvent event) throws SQLException {
          if(tfNomSerie.getText().toString().matches(".*[0-9]+.*"))
        {
             Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Nom contient seulement des caractéres");
            alertType.show();
        }else if(tfNomSerie.getText().toString().length()<=5)
        {
             Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Nom contient au minimum 5 caractéres");
            alertType.show();
        }
          if(tfNomSerie.getText().isEmpty() )
        {
            Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Champs de nom serie ne doit pas etre vide !");
            alertType.show();
        }
           else if(imagePath =="imgSportConnect/")
        {
             Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Choisir une image");
            alertType.show();
            
        } 
        String resNomSerie = tfNomSerie.getText();
        Serie t = new Serie(resNomSerie, imagePath);

        // récupérer les checkbox sélectionnées
        List<String> selectedExercices = new ArrayList<>();
        ObservableList<Node> nodes = r.getChildren();
        for (Node node : nodes) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    selectedExercices.add(checkBox.getText());
                }
            }
        }

        // faire quelque chose avec les checkbox sélectionnées
        System.out.println("Les exercices sélectionnés sont : " + selectedExercices);
        

        SerieCRUD pcd = new SerieCRUD();
        pcd.ajouter(t);
        //mail sender 
                     try {
            List<client> l =  ClientCRUD.getList();
            for(client c : l)
            {
             MailSender.sendEmail(c.getEmail(), "Une nouvelle serie a été ajouté ", "Bonjour "+c.getPrenom()+" "+c.getNom()+"\n la serie "+t.getTitreSerie()+" est ajouté à notre equipements \n Consultez notre application et restez en bonne santé. ");
            }
            
   
} catch (MessagingException e) {
    e.printStackTrace();
}
        
        
        int id_serie=0;
        Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
           try {
             String req = "select Max(id) from serie";
             ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                id_serie = rs.getInt(1);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        int value;
        for(String i : selectedExercices)
        {
           value = maMap.get(i);
            try {
            String requete = "INSERT INTO serie_exercice VALUES( "+id_serie+","+value+")";
                   
           
            st.executeUpdate(requete);
            System.out.println("Exercice ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
    }
    
    
    
}
