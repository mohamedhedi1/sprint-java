/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Equipement;
import entities.Exercice;
import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.text.PDFTextStripper;

import javafx.collections.transformation.FilteredList;
import services.EquipementCRUD;
import services.ExerciceCRUD;
import services.PDFViewerController;
import services.PdfGenerator;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AfficherEquipementController implements Initializable {

    private ListView<String> LequipAff;
    @FXML
    private TableView<Equipement> equipementTable;
    @FXML
    private TableColumn<Equipement, String> id_col;
    @FXML
    private TableColumn<Equipement, String> nom_col;
    @FXML
    private TableColumn<Equipement, String> image_col;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField tfNomEquipement;
    @FXML
    private Button BtnAjouterImage;
    @FXML
    private ImageView imageuploadedID;
    @FXML
    private Button Modifier;
    @FXML
    private TextField tfIdEq;
      String imagePath="";
    File selectedFile;
    @FXML
    private Button pdfBtn;
    @FXML
    private AnchorPane ModifierAnarchor;
    @FXML
    private AnchorPane AfficherAnarchor;
    @FXML
    private Button btnAnarchor;
    @FXML
    private TextField recherchetf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            EquipementShowListData();
        } catch (SQLException ex) {
            
        }
        
    }   
    
    
     public ObservableList<Equipement> EquipementListData() throws SQLException {

        return new EquipementCRUD().afficher();
    }
    
    private ObservableList<Equipement> EquipementList;
    
    public void EquipementShowListData() throws SQLException {
        EquipementList = EquipementListData();

        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nomEquipement"));
        
        image_col.setCellFactory(column ->{
            return new TableCell<Equipement,String>(){
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
      //  "C:\\xampp\\htdocs\\imgSportConnect\\"
  /*    System.out.println("ttttttttttttttttttttttttttttttttttttttttt");
      System.out.println(new PropertyValueFactory<>("imageEquipement"));*/
        image_col.setCellValueFactory(new PropertyValueFactory<>("imageEquipement"));
       
       
        

        equipementTable.setItems(EquipementList);

    }
       public void RefreshEquipementShowListData() throws SQLException {
            EquipementList.clear();
           EquipementList = EquipementListData();

        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nomEquipement"));
        
        image_col.setCellValueFactory(new PropertyValueFactory<>("imageEquipement"));
       
       
        

        equipementTable.setItems(EquipementList);

    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
          
    Equipement u = equipementTable.getSelectionModel().getSelectedItem();
    
    if (u != null) {
        EquipementCRUD us = new EquipementCRUD();
        us.supprimer(u.getId());
        RefreshEquipementShowListData();
    } else {
         Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Selectionner un equipement !");
            alertType.show();
        
       
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
    private void ModifierEquipement(ActionEvent event) throws SQLException {
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
       
        
        else{
            
        //faire traitement d'ajout
          String resNomEquipment = tfNomEquipement.getText();
          int id = Integer.parseInt(tfIdEq.getText());
        EquipementCRUD pcd =new EquipementCRUD();
        
        Equipement t = new Equipement(id,resNomEquipment, imagePath);
        
        pcd.modifier(t);
          RefreshEquipementShowListData();
          AfficherAnarchor.setVisible(true);
          ModifierAnarchor.setVisible(false);
    }
    }

    @FXML
    private void selectItem(MouseEvent event) {
              Equipement u = equipementTable.getSelectionModel().getSelectedItem();
    int num = equipementTable.getSelectionModel().getSelectedIndex();
        System.out.println(u);
    if ((num - 1)<-6) {
        return;
    }
    imagePath = u.getImageEquipement();
    tfNomEquipement.setText(u.getNomEquipement());
    tfIdEq.setText(Integer.toString(u.getId()));
    
    
    
    
    }

   /* @FXML
    private void GenerePDF(ActionEvent event) {
        PdfGenerator pdf = new PdfGenerator();
        pdf.GenererPdf();
        
        
        
    }
*/
    
    @FXML
private void GenerePDF(ActionEvent event) throws PrinterException {
    
         PdfGenerator.GenererPdf("equipements");
    String urlPDF = "http://localhost/pdf/fiche.pdf";
      
      // Ouvrir le fichier PDF avec le navigateur web par défaut
      Desktop desktop = Desktop.getDesktop();
      try {
         desktop.browse(new URI(urlPDF));
      } catch (IOException | URISyntaxException e) {
         e.printStackTrace();
      }
}

    
    @FXML
    private void switchAnarchor(ActionEvent event) {
        AfficherAnarchor.setVisible(false);
        ModifierAnarchor.setVisible(true);
    }
/*
public void RechercheAV() throws SQLException {
    // Wrap the ObservableList in a FilteredList (initially display all data).
    FilteredList<Equipement> filteredData = new FilteredList<>(EquipementListData(), e -> true);
    recherchetf.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(predicateData -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String searchKey = newValue.toLowerCase();
            return Stream.of(
                    String.valueOf(predicateData.getId()),
                    predicateData.getNomEquipement()
            ).anyMatch(value -> value.toLowerCase().contains(searchKey));
        });
    });
    SortedList<Equipement> sortedData = new SortedList<>(filteredData);   
    equipementTable.comparatorProperty().bind(sortedData.comparatorProperty());
    equipementTable.setItems(sortedData);
}*/

}

