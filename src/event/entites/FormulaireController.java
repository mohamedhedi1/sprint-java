/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.entites;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FormulaireController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfDescription2;
    @FXML
    private TextField tfImage;
    @FXML
    private TextField tfLoc;
    @FXML
    private ComboBox<Categorie> cbCategorie;
    @FXML
    private Button btnUploadImage;
    @FXML
    private TextField search;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnBlog;
    @FXML
    private Button btnEvent;
    @FXML
    private Button idreturn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }    

    
    
    @FXML
    private void uploadImage(ActionEvent event) {
        
          FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Sélectionner une image");
    fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
        String imagePath = selectedFile.getAbsolutePath();
        tfLoc.setText(imagePath);
        
        
    }
    
    
        
    }
    
    private void handleUploadImage(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Image File");

    // Set the initial directory and file extension filter  
    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
    );

    // Show the file chooser dialog and get the selected file
    File selectedFile = fileChooser.showOpenDialog(tfLoc.getScene().getWindow());

    // If the user selected a file, set its path to the tfImage TextField
    if (selectedFile != null) {
        tfLoc.setText(selectedFile.getAbsolutePath());
    }
}

    
    public void setEntity(Activites activites) {
     tfTitre.setText(activites.getTitre());
    tfDescription2.setText(activites.getDescription());
    tfImage.setText(activites.getImage());
    tfLoc.setText(activites.getImages());
   

    // Sélectionner la catégorie correspondant à l'activité dans la liste déroulante
    cbCategorie.setValue(activites.getCategorie());}

public Activites getUpdatedEntity() {
    // Créer une nouvelle instance de l'entité et la remplir avec les valeurs actuelles des champs de formulaire
    Activites activites = new Activites();
    activites.setTitre(tfTitre.getText());
    activites.setDescription(tfDescription2.getText());
    activites.setImage(tfImage.getText());
    activites.setImages(tfLoc.getText());
    Categorie selectedCategorie = (Categorie) cbCategorie.getValue();
    return activites;
}
@FXML
    private void Categories(ActionEvent event) throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("/event/entites/SupprimerCategorie.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void participants(ActionEvent event) throws IOException  {
        
    Parent root = FXMLLoader.load(getClass().getResource("/event/entites/participants.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
   
    }

    @FXML
    private void Activites(ActionEvent event)  throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("/event/entites/AfficherEven.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void OnReturn(ActionEvent event) {
    }





    
}
