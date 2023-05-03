package event.entites;
import event.services.ActivitesCrud;
import java.io.File;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AddEvenController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfImage;
    @FXML
    private Button tfAdd;
    @FXML
    private TextField tfLoc;
    @FXML
    private TextField tfDescription2;
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
        // Initialize the category combo box
        ArrayList<Categorie> categories = null;
        categories = (ArrayList<Categorie>) new ActivitesCrud().getAllCategories();
        cbCategorie.getItems().addAll(categories);
    }    
 
   @FXML
private void OnClick(ActionEvent event) {
    Categorie categorie = cbCategorie.getValue();
    String titre = tfTitre.getText().trim();
    String description = tfDescription2.getText().trim();
    String loc = tfLoc.getText().trim();
    String image = tfImage.getText().trim();
    
    
    
    if (categorie == null) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une catégorie");
        alert.showAndWait();
        return;
    }
    
    if (titre.isEmpty() || image.isEmpty() || loc.isEmpty() || description.isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.showAndWait();
        return;
    }
    
    // Create an Activites object with the entered values
    Activites activites = new Activites(categorie, titre, description, image, loc);

    ActivitesCrud activitesCrud = new ActivitesCrud();
    activitesCrud.addEntity(activites);

    // Call the addEntity method to insert the object into the database
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Activité ajoutée");
    alert.showAndWait();
    
     // Send email
    String subject = "Nouvelle activité ajoutée";
    String body = "Une nouvelle activité a été ajoutée : " + titre;
    String recipient = "pidev800@gmail.com";
    
    sendEmail(subject, body, recipient);
    
     // Redirect the user to Afficher.fxml
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/event/entites/AfficherEven.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Close the current window
            Stage currentStage = (Stage) tfTitre.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

private void sendEmail(String subject, String body, String recipient) {
    String host = "smtp.gmail.com";
    String username = "pidev800@gmail.com";
    String password = "nmeitynhjipylzed";
    String from = "pidev800@gmail.com";
    
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", "587");
    
    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(username, password);
                }
            });
    
    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(recipient));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);

        System.out.println("Email sent to " + recipient);

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
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
    