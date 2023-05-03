/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import entities.client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import services.User;
import entities.utilisateur;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import services.Front;
import services.Session;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.scene.control.Cell;
import javax.swing.text.Document;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;


import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import com.itextpdf.text.Font;
import com.itextpdf.text.Element;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



/**
 * FXML Controller class
 *
 * @author PC
 */
public class SportConnectBackController implements Initializable {
    
    @FXML
    private Button btnUser;
    
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
    private TableColumn<?, ?> tcId;
    @FXML
    private TableColumn<?, ?> tcNom;
    @FXML
    private TableColumn<?, ?> tcPrenom;
    @FXML
    private TableColumn<?, ?> tcUser;
    @FXML
    private TableColumn<?, ?> tcEmail;
    @FXML
    private TableColumn<?, ?> tcMdp;
    @FXML
    private TableColumn<?, ?> tcTel;
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
    private TableView<utilisateur> tbUser;
    @FXML
    private Button ajouter;
    @FXML
    private Button annuler;
    @FXML
    private Button logout;
    @FXML
    private Button btnClient;
    @FXML
    private Button excel;
    @FXML
    private Button pdf;
    

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listUser();
        // Ajouter un gestionnaire d'événements au bouton "Supprimer"
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
    
    public void listUser()
    {
        User us = new User();
        ObservableList<utilisateur> list = us.getAll();
        System.out.println("list");
        
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tcPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tcUser.setCellValueFactory(new PropertyValueFactory<>("username"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcMdp.setCellValueFactory(new PropertyValueFactory<>("password"));
        tcTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        tbUser.setItems(list);
    }
    

    /*@FXML
void modifier(ActionEvent event) {
    utilisateur u = tbUser.getSelectionModel().getSelectedItem();
    User us = new User();
    if (u != null) {
        u.setNom(tfNom.getText());
        u.setPrenom(tfPrenom.getText());
        u.setUsername(tfUsername.getText());
        u.setEmail(tfEmail.getText());
        u.setPassword(tfMdp.getText());
        u.setTel(Integer.parseInt(tfTel.getText()));
        us.modifier(u);
        
        System.out.println("Utilisateur modifié !");
        
    } else {
        System.out.println("Veuillez sélectionner un utilisateur.");
    }
    annulerS();
    tbUser.refresh();
}*/

    @FXML
    void modifier(ActionEvent event) {
    utilisateur u = tbUser.getSelectionModel().getSelectedItem();
    User us = new User();
    if (u != null) {
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String username = tfUsername.getText();
        String email = tfEmail.getText();
        String password = tfMdp.getText();
        String tel = tfTel.getText();
        if (us.verifierSaisie(nom, prenom, username, email, password, tel)) {
            u.setNom(nom);
            u.setPrenom(prenom);
            u.setUsername(username);
            u.setEmail(email);
            u.setPassword(password);
            u.setTel(Integer.parseInt(tel));
            boolean modifie = us.modifier(u);
    } else {
        System.out.println("Veuillez sélectionner un utilisateur.");
    }
    annulerS();
    tbUser.refresh();
    }   
}



    /* @FXML
    private void supprimer(ActionEvent event) {
    // Récupérer l'utilisateur sélectionné
    utilisateur u = tbUser.getSelectionModel().getSelectedItem();

    if (u != null) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Cet utilisateur sera supprimé!!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            User us = new User();
            us.supprimer(u.getId());
            listUser();
            annulerS();
        } else {
            // Annuler la suppression
            System.out.println("Suppression annulée");
        }
    } else {
        System.out.println("Aucun utilisateur sélectionné");
    }
}*/

    @FXML
    private void supprimer(ActionEvent event) {
        // Récupérer l'utilisateur sélectionné
    utilisateur u = tbUser.getSelectionModel().getSelectedItem();
    
    if (u != null) {
        User us = new User();
        us.supprimer(u.getId());
        listUser();
    } else {
        System.out.println("Aucun utilisateur sélectionné");
    }
    annulerS();
    }

    /*@FXML
    private void ajouter(ActionEvent event) {
            String nom = tfNom.getText();
            String prenom = tfPrenom.getText();
            String username = tfUsername.getText();
            String email = tfEmail.getText();
            String password = tfMdp.getText();
            int tel = Integer.parseInt(tfTel.getText());
            User us = new User();
            utilisateur u =new utilisateur(nom,prenom,username,email,password,tel);
            
            
            us.ajouter(u);
            listUser();
            annulerS();
            
            if (us.verifierSaisie(nom, prenom, username, email, password, tel)) {
        utilisateur u = new utilisateur(nom, prenom, username, email, password, tel);
        //User us = new User();
        us.ajouter(u);
        listUser();
        annulerS();
}
 }*/
    
    @FXML
private void ajouter(ActionEvent event) {
    String nom = tfNom.getText();
    String prenom = tfPrenom.getText();
    String username = tfUsername.getText();
    String email = tfEmail.getText();
    String password = tfMdp.getText();
    //int tel = Integer.parseInt(tfTel.getText());
    String tel = tfTel.getText();
    User us = new User();
    if (us.verifierSaisie(nom, prenom, username, email, password, tel)) {
        utilisateur u = new utilisateur(nom, prenom, username, email, password, Integer.parseInt(tel));
        us.ajouter(u);
        listUser();
        annulerS();
    } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText(us.getMessageErreur()); // affichage du message d'erreur
        alert.showAndWait();
    }
}


    public void annulerS()
    {
    tfNom.clear();
    tfPrenom.clear();
    tfUsername.clear();
    tfEmail.clear();
    tfMdp.clear();
    tfTel.clear();
    }
    
    @FXML
    private void annuler(ActionEvent event) {
         annulerS();
    }

    @FXML
    public void selectItem() {
    utilisateur u = tbUser.getSelectionModel().getSelectedItem();
    int num = tbUser.getSelectionModel().getSelectedIndex();
        System.out.println(u);
    if ((num - 1)<-6) {
        return;
    }
    tfNom.setText(u.getNom());
        tfPrenom.setText(u.getPrenom());
        tfUsername.setText(u.getUsername());
        tfEmail.setText(u.getEmail());
        tfMdp.setText(u.getPassword());
        tfTel.setText(Integer.toString(u.getTel()));
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
    private void client(ActionEvent event) {
        try {
            Session.getInstance().logout();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("clientBack.fxml"));
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

    
    public void exportToExcel(String fileName, ObservableList<utilisateur> data) {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("admin");

    // Créer une rangée pour les en-têtes de colonnes
    XSSFRow headerRow = sheet.createRow(0);
    headerRow.createCell(0).setCellValue("ID");
    headerRow.createCell(1).setCellValue("Nom");
    headerRow.createCell(2).setCellValue("Prénom");
    headerRow.createCell(3).setCellValue("Nom d'utilisateur");
    headerRow.createCell(4).setCellValue("E-mail");
    headerRow.createCell(5).setCellValue("Mot de passe");
    headerRow.createCell(6).setCellValue("Téléphone");

    // Ajouter les données à la feuille de calcul
    int rowNum = 1;
    for (utilisateur c : data) {
        XSSFRow row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(c.getId());
        row.createCell(1).setCellValue(c.getNom());
        row.createCell(2).setCellValue(c.getPrenom());
        row.createCell(3).setCellValue(c.getUsername());
        row.createCell(4).setCellValue(c.getEmail());
        row.createCell(5).setCellValue(c.getPassword());
        row.createCell(6).setCellValue(c.getTel());
        //row.createCell(7).setCellValue(c.getDate_naissance().toString());
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
        fileChooser.setInitialFileName("admins.xlsx");
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("Fichier Excel", "*.xlsx")
        );
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            User us = new User();
            ObservableList<utilisateur> data = us.getAll();
            exportToExcel(selectedFile.getAbsolutePath(), data);
        }try {
                Desktop.getDesktop().open(selectedFile);
            } catch (IOException e) {
                e.printStackTrace();
}
}
    
    @FXML
    private void exportToPdf(ActionEvent event) {
    com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);
    try {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF file");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
        );
        File file = fileChooser.showSaveDialog(null);
        if (file == null) {
            return;
        }
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        writer.setPageEvent(new DateAndTime());
        document.open();
        
        PdfPTable table = new PdfPTable(7);
        table.addCell("ID");
        table.addCell("Nom");
        table.addCell("Prenom");
        table.addCell("Username");
        table.addCell("Email");
        table.addCell("Password");
        table.addCell("Telephone");
        
        User us = new User();
        ObservableList<utilisateur> data = us.getAll();
        
        for(utilisateur user : data) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getNom());
            table.addCell(user.getPrenom());
            table.addCell(user.getUsername());
            table.addCell(user.getEmail());
            table.addCell(user.getPassword());
            table.addCell(new PdfPCell(new Phrase(String.valueOf(user.getTel()))));
        }
        
        document.add(table);
        
        Image image3 = Image.getInstance("C:\\Users\\PC\\Documents\\NetBeansProjects\\Users\\src\\gui\\image\\logo3.png");
        float x = (PageSize.A4.getWidth() - image3.getWidth()) / 2f;
        float y = (PageSize.A4.getHeight() - table.getTotalHeight() - 100f - image3.getHeight()) / 2f;
        image3.setAbsolutePosition(x, y);
        document.add(image3);
        
        document.close();
        try {
    Desktop.getDesktop().open(file);
    } catch (IOException e) {
        e.printStackTrace();
}

        
    } catch (DocumentException e) {
        e.printStackTrace();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}



    private static class DateAndTime extends PdfPageEventHelper {
    public void onEndPage(PdfWriter writer, com.itextpdf.text.Document document) {
        try {
            Phrase phrase = new Phrase("Exporté le " + LocalDateTime.now().toString(),
            FontFactory.getFont(FontFactory.HELVETICA, 9));
            ColumnText.showTextAligned(writer.getDirectContent(),
            Element.ALIGN_LEFT, phrase,
            240, 30, 0); // Position de la marge gauche et du bas en pixels

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

}

