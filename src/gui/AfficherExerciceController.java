/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Equipement;
import entities.Exercice;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import services.EquipementCRUD;
import services.ExerciceCRUD;
import services.PdfGenerator;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AfficherExerciceController implements Initializable {

    @FXML
    private TableView<Exercice> ExerciceTable;
    @FXML
    private TableColumn<Exercice, String> id_col;        
    @FXML
    private TableColumn<Exercice, String> idEq_col;
    @FXML
    private TableColumn<Exercice, String> nom_col;
    @FXML
    private TableColumn<Exercice, String> image_col;
    @FXML
    private TableColumn<Exercice, String> duration_col;
    @FXML
    private TableColumn<Exercice, String> repetition_col;
    @FXML
    private TableColumn<Exercice, String> instruction_col;
    @FXML
    private Button btnSupprimer;
    @FXML
    private ComboBox<String> cb_equipement;
    ObservableList<String> optionsEquipement = FXCollections.observableArrayList();
    @FXML
    private TextField tfNomExercice;
    @FXML
    private TextField tfDuration;
    @FXML
    private TextField tfRepetation;
    @FXML
    private TextArea tfInstruction;
    @FXML
    private TextField tfId;
     Map<String, Integer> maMap = new HashMap<String, Integer>();
    @FXML
    private Button btnModifier;
    @FXML
    private ImageView imageuploadedID;
    @FXML
    private Button BtnAjouterImage;
      String imagePath="";
    File selectedFile;
    int resultat=-1;
    @FXML
    private AnchorPane ModifierAnarchor;
    @FXML
    private AnchorPane AfficherAnarchor;
    @FXML
    private Button btnSwitch;
    @FXML
    private Button pdfBtn;
    @FXML
    private TextField recherchetf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            ExerciceShowListData();
            fillcomboExercice();
        cb_equipement.setItems(optionsEquipement);
        } catch (SQLException ex) {
            
        }
         // Associer les données à la table
        ExerciceCRUD eq = new ExerciceCRUD();
        try {
            ObservableList<Exercice>  x =eq.afficher();
              ExerciceTable.setItems(x);
                 recherchetf.textProperty().addListener((observable, oldValue, newValue) -> {
        // utiliser la méthode filter() de l'objet categories pour filtrer les résultats
        ExerciceTable.setItems(x.filtered(ex -> {
            String lowerCaseFilter = newValue.toLowerCase();
            return ex.getNomExercice().toLowerCase().contains(lowerCaseFilter) || ex.getInstruction().toLowerCase().contains(lowerCaseFilter)
                    || Integer.toString(ex.getRepetation()).toLowerCase().contains(lowerCaseFilter) || Integer.toString(ex.getDuration()).toLowerCase().contains(lowerCaseFilter)
                ;
        }));
    });
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEquipementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
    public ObservableList<Exercice> ExerciceListData() throws SQLException {

        return new ExerciceCRUD().afficher();
    }
     private ObservableList<Exercice> ExerciceList;
     
      public void ExerciceShowListData() throws SQLException {
        ExerciceList = ExerciceListData();
        
             

        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        idEq_col.setCellValueFactory(new PropertyValueFactory<>("equipementId"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nomExercice"));
        
            image_col.setCellFactory(column ->{
            return new TableCell<Exercice,String>(){
                final ImageView imageView = new ImageView();
                    {
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        setGraphic(imageView);
                    }
                protected void updateItem(String iPath, boolean empty) {
                    super.updateItem(iPath, empty);
                    if (iPath == null || empty) {
                        imageView.setImage(null);
                    }else{
                        try {
                            File file = new File(iPath);
                            Image image = new Image(file.toURI().toString());
                            imageView.setImage(image);
                            imageView.setFitWidth(50); // Set the desired width and height here
                            imageView.setFitHeight(50);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        
                    }
                }    
            };
        }
        );
        image_col.setCellValueFactory(new PropertyValueFactory<>("imageExercice"));
       duration_col.setCellValueFactory(new PropertyValueFactory<>("duration"));
       repetition_col.setCellValueFactory(new PropertyValueFactory<>("repetation"));
        instruction_col.setCellValueFactory(new PropertyValueFactory<>("instruction"));
       
        

        ExerciceTable.setItems(ExerciceList);

    }
      
       public void RefreshExerciceShowListData() throws SQLException {
            ExerciceList.clear();
           ExerciceList = ExerciceListData();

        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nomEquipement"));
        image_col.setCellValueFactory(new PropertyValueFactory<>("imageEquipement"));
       
       
        

        ExerciceTable.setItems(ExerciceList);

    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
        Exercice u = ExerciceTable.getSelectionModel().getSelectedItem();
    
    if (u != null) {
        ExerciceCRUD us = new ExerciceCRUD();
        us.supprimer(u.getId());
        RefreshExerciceShowListData();
    } else {
         Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Selectionner un exercice !");
            alertType.show();
        
       
    }
    }

    @FXML
    private void selectItem(MouseEvent event) {
          Exercice u = ExerciceTable.getSelectionModel().getSelectedItem();
    int num = ExerciceTable.getSelectionModel().getSelectedIndex();
        System.out.println(u);
    if ((num - 1)<-6) {
        return;
    }
   tfNomExercice.setText(u.getNomExercice());
   tfId.setText(Integer.toString(u.getId()));
   tfInstruction.setText(u.getInstruction());
           tfRepetation.setText(Integer.toString(u.getRepetation()));
           tfDuration.setText(Integer.toString(u.getDuration()));
            imagePath = u.getImageExercice();
            resultat = u.getEquipementId();
              
           //cb_equipement.setValue();
   
   
     /*  
   tfdebut.setText(u.getHeure_debut());
          tffin.setText(u.getHeyre_fin());
          tflocalisation.setText(u.getLocalisation());*/
         
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
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
            
        }/*else if(cb_equipement.getValue().isEmpty()) 
        {
             Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Veuillez choisir un equipement");
            alertType.show();   
            
        }*/
         else if(imagePath =="")
        {
             Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Choisir une image");
            alertType.show();
            
        }
        
        else {
        int id = Integer.parseInt(tfId.getText());
        String resNomExercice = tfNomExercice.getText();
        int resDuration = Integer.parseInt(tfDuration.getText());
        int resRepetation = Integer.parseInt(tfRepetation.getText());
        String resInstruction = tfInstruction.getText();
if(cb_equipement.getValue() != null)
{   System.out.println(cb_equipement.getValue());
    resultat = maMap.get(cb_equipement.getValue());
 System.out.println(resultat);

}
        Exercice t = new Exercice(id,resultat, resNomExercice, imagePath, resDuration, resRepetation, resInstruction);
       ExerciceCRUD pcd = new ExerciceCRUD();
        pcd.modifier(t);
        AfficherAnarchor.setVisible(true);
        ModifierAnarchor.setVisible(false);
        
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
    private void switchAnarchor(ActionEvent event) {
        AfficherAnarchor.setVisible(false);
        ModifierAnarchor.setVisible(true);
    }

    @FXML
    private void GenerePDF(ActionEvent event) {
                 PdfGenerator.GenererPdf("exercices");
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
