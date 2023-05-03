/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.client;
import entities.utilisateur;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.Front;
import javafx.util.Callback;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.Session;
import services.User;
import utils.DataBase;


/**
 * FXML Controller class
 *
 * @author PC
 */
public class ClientBackController implements Initializable {
    Connection cnx = DataBase.getInstance().getCnx();

    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnBlog;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btnNutrition;
    @FXML
    private Button btnCoaching;
    @FXML
    private Button btnProduit;
    @FXML
    private Button btnUser;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMdp;
    @FXML
    private TextField tfTel;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button ajouter;
    @FXML
    private Button annuler;
    @FXML
    private Button logout;
    @FXML
    private TableView<client> tbClient;
    @FXML
    private TableColumn<?, ?> tcIdClient;
    @FXML
    private TableColumn<?, ?> tcNomClient;
    @FXML
    private TableColumn<?, ?> tcPrenomClient;
    @FXML
    private TableColumn<?, ?> tcUserClient;
    @FXML
    private TableColumn<?, ?> tcEmailClient;
    @FXML
    private TableColumn<?, ?> tcMdpClient;
    @FXML
    private TableColumn<?, ?> tcTelClient;
    @FXML
    private TableColumn<?, ?> tcDate;
    @FXML
    private Button btnClient;
    @FXML
    private TextField tfDate;
    @FXML
    private TableColumn<?, ?> tcPhoto;
    @FXML
    private ImageView PhotoClient;
    @FXML
    private Button excel;
    @FXML
    private Button pdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listClient();
        supprimer.setOnAction(this::supprimer);
    }    

    @FXML
    private void acceuil(ActionEvent event) {
        try {
            Session.getInstance().logout();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SportConnectBack.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            
            ((Node)(event.getSource())).getScene().getWindow().hide();      // Fermer la fenêtre actuelle
        } catch (IOException ex) {
            Logger.getLogger(SportConnectBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void blog(ActionEvent event) {
    }

    @FXML
    private void evenement(ActionEvent event) {
    }

    @FXML
    private void nutrition(ActionEvent event) {
    }

    @FXML
    private void coaching(ActionEvent event) {
    }

    @FXML
    private void produit(ActionEvent event) {
    }

    @FXML
    private void utilsateur(ActionEvent event) {
        try {
            Session.getInstance().logout();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SportConnectBack.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            
            ((Node)(event.getSource())).getScene().getWindow().hide();      // Fermer la fenêtre actuelle
        } catch (IOException ex) {
            Logger.getLogger(SportConnectBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
    }

    
    
   
    
    @FXML
    private void supprimer(ActionEvent event) {
        client c = tbClient.getSelectionModel().getSelectedItem();
    
    if (c != null) {
        Front f = new Front();
        f.supprimer(c.getId());
        listClient();
    } else {
        System.out.println("Aucun client sélectionné");
    }
    //annulerS();
    }

    @FXML
    private void ajouter(ActionEvent event) {
    }

    @FXML
    private void annuler(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
        try {
            Session.getInstance().logout();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            
            ((Node)(event.getSource())).getScene().getWindow().hide();      // Fermer la fenêtre actuelle
        } catch (IOException ex) {
            Logger.getLogger(SportConnectBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectItem(MouseEvent event) {
    }

    @FXML
    private void client(ActionEvent event) {
    }
    
    
    public static ImageView createImageView(File file) {
    ImageView imageView = null;
    try {
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    return imageView;
}

    
    /*public void listClient()
    {
        Front f = new Front();
        ObservableList<client> list = f.getAll();
        System.out.println("list");
        
        tcIdClient.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNomClient.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tcPrenomClient.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tcUserClient.setCellValueFactory(new PropertyValueFactory<>("username"));
        tcEmailClient.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcMdpClient.setCellValueFactory(new PropertyValueFactory<>("password"));
        tcTelClient.setCellValueFactory(new PropertyValueFactory<>("tel"));
        tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
         tcPhoto.setCellValueFactory(new PropertyValueFactory<>("photo"));

        tbClient.setItems(list);
    }*/
    
    
    
      public void listClient()
{
    Front f = new Front();
    ObservableList<client> list = f.getAll();
    System.out.println("list");
    
    tcIdClient.setCellValueFactory(new PropertyValueFactory<>("id"));
    tcNomClient.setCellValueFactory(new PropertyValueFactory<>("nom"));
    tcPrenomClient.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    tcUserClient.setCellValueFactory(new PropertyValueFactory<>("username"));
    tcEmailClient.setCellValueFactory(new PropertyValueFactory<>("email"));
    tcMdpClient.setCellValueFactory(new PropertyValueFactory<>("password"));
    tcTelClient.setCellValueFactory(new PropertyValueFactory<>("telephone"));
    tcDate.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
    
    
    tbClient.setItems(list);
}
    public void selectItem() {
    client c = tbClient.getSelectionModel().getSelectedItem();
    int num = tbClient.getSelectionModel().getSelectedIndex();
        System.out.println(c);
    if ((num - 1)<-8) {
        return;
    }
     /*   tfNom.setText(c.getNom());
        tfPrenom.setText(c.getPrenom());
        tfUsername.setText(c.getUsername());
        tfEmail.setText(c.getEmail());
        tfMdp.setText(c.getPassword());
        tfTel.setText(Integer.toString(c.getTelephone()));
        tfDate.setText(c.getDate_naissance());
*/
}
    
    public void exportToExcel(String fileName, ObservableList<entities.client> data) {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("client");

    // Créer une rangée pour les en-têtes de colonnes
    XSSFRow headerRow = sheet.createRow(0);
    headerRow.createCell(0).setCellValue("ID");
    headerRow.createCell(1).setCellValue("Nom");
    headerRow.createCell(2).setCellValue("Prénom");
    headerRow.createCell(3).setCellValue("Nom d'utilisateur");
    headerRow.createCell(4).setCellValue("E-mail");
    headerRow.createCell(5).setCellValue("Mot de passe");
    headerRow.createCell(6).setCellValue("Téléphone");
    headerRow.createCell(7).setCellValue("Date de naissance");

    // Ajouter les données à la feuille de calcul
    int rowNum = 1;
    for (client c : data) {
        XSSFRow row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(c.getId());
        row.createCell(1).setCellValue(c.getNom());
        row.createCell(2).setCellValue(c.getPrenom());
        row.createCell(3).setCellValue(c.getUsername());
        row.createCell(4).setCellValue(c.getEmail());
        row.createCell(5).setCellValue(c.getPassword());
        row.createCell(6).setCellValue(c.getTelephone());
        row.createCell(7).setCellValue(c.getDate_naissance().toString());
    }

    // Écrire le contenu dans le fichier
    try {
        FileOutputStream outputStream = new FileOutputStream(fileName);
        workbook.write(outputStream);
        workbook.close();
        System.out.println("Le fichier " + fileName + " a été créé avec succès !");
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    

}

    @FXML
    private void exportToExcel(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer sous");
        fileChooser.setInitialFileName("clients.xlsx");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Fichier Excel", "*.xlsx")
        );
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            Front f = new Front();
            ObservableList<entities.client> data = f.getAll();
            exportToExcel(selectedFile.getAbsolutePath(), data);
        }try {
                Desktop.getDesktop().open(selectedFile);
            } catch (IOException e) {
                e.printStackTrace();
}


    

    }

    @FXML
    private void exportToPdf(ActionEvent event) {
    }
}
