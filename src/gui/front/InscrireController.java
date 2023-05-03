/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.front;

//import com.nexmo.client.sms.SmsSubmissionResult;
//import com.twilio.rest.api.v2010.account.Message;


import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.type.PhoneNumber;
import entities.client;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.Period;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.mail.Message;
import services.Front;
import services.User;



/**
 * FXML Controller class
 *
 * @author PC
 */
public class InscrireController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private Button annuler;
    @FXML
    private Button inscription;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMdp;
    @FXML
    private TextField tfTel;
    @FXML
    private TextField tfUsername;
    @FXML
    private Button parcourirPhoto;
    @FXML
    private DatePicker dpDateNaissance;
    @FXML
    private TextField tfPhoto;
    @FXML
    private ImageView image;
    @FXML
    private TextField tfAge;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dpDateNaissance.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int age = calculerAge(newValue);
                tfAge.setText(Integer.toString(age));
            }
        });
        // TODO
    }    
    
    public void setTextAge(String message) {
        this.tfAge.setText(message);
    }

    public static int calculerAge(LocalDate dateNaissance) {
    LocalDate aujourdHui = LocalDate.now();
    int age = aujourdHui.getYear() - dateNaissance.getYear();
    if (aujourdHui.getMonthValue() < dateNaissance.getMonthValue()) {
        age--;
    } else if (aujourdHui.getMonthValue() == dateNaissance.getMonthValue() 
               && aujourdHui.getDayOfMonth() < dateNaissance.getDayOfMonth()) {
        age--;
    }
    return age;
}

    

    @FXML
    private void inscription(ActionEvent event) {
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String username = tfUsername.getText();
        String email = tfEmail.getText();
        String password = tfMdp.getText();
        String tel = tfTel.getText();
        LocalDate date_naissance = dpDateNaissance.getValue();
       // File photoFile = tfPhoto.getText().isEmpty() ? null : new File(tfPhoto.getText());
        File photoFile = tfPhoto.getText().isEmpty() ? null : new File(tfPhoto.getText());
            if (photoFile != null && photoFile.exists()) {
                Path sourcePath = photoFile.toPath();
                Path destinationPath = Paths.get("C:\\Users\\PC\\Documents\\NetBeansProjects\\Users\\src\\gui\\clients\\" + photoFile.getName());
                try {
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        
        //int age = calculerAge(dateNaissance);
        setTextAge(""+calculerAge(date_naissance)); // calculer l'âge
        //setTextAge(Integer.toString(age)); // afficher l'âge dans le champ texte
        
        Front f = new Front();
        if (f.verifierSaisie2(nom, prenom, username, email, password, date_naissance, photoFile)) {
           // int age = calculerAge(dateNaissance);
            client c = new client(nom, prenom, username, email, password, tel, date_naissance, photoFile);
            f.inscription(nom, prenom, username, email, password, tel, date_naissance, photoFile);

            annulerS();
            try {
            Twilio.init("ACa073b7c0dcf7051fc3cfa35c390d4c31", "836e7eb87c8ad0313f4b863e32d98d99"); 
        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(
                new PhoneNumber(tel),
                new PhoneNumber("+1 620 349 6558"), 
                "🎉 Bienvenue " + prenom + " " + nom + "! 🎉\n\nVotre nom d'utilisateur est : " + username + " et votre mot de passe est : " + password + "\n\nMerci d'avoir rejoint SportConnect! 🏆🎉🚀")
                //"Bienvenue " + prenom+" " + nom + "!\n Votre nom d'utilisateur est " + username + "et Votre mot de passe est " + password + "\n SportConnect.")
                .create();
        System.out.println(message.getSid());
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Message envoyé");
        alert.setContentText("Le message a été envoyé avec succès !");
        alert.showAndWait();
    } catch (ApiException e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur d'envoi");
        alert.setContentText("Une erreur est survenue lors de l'envoi du message : " + e.getMessage());
        alert.showAndWait();
    }

        
            
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginF.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
            
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText(f.getMessageErreur()); 
            alert.showAndWait();
        }
    }

        @FXML
    private void parcourirPhoto(ActionEvent event) {
    
         FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une photo");
    fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("Fichiers images", "*.png", "*.jpg", "*.gif"),
        new ExtensionFilter("Tous les fichiers", "*.*")
    );
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
        try {
    
            javafx.scene.image.Image img = new javafx.scene.image.Image(selectedFile.toURI().toURL().toString());
            image.setImage(img);
            tfPhoto.setText(selectedFile.getAbsolutePath());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    }

    @FXML
    private void annuler(ActionEvent event) {
        annulerS();
    }

    public void annulerS()
    {
        tfEmail.clear();
        tfMdp.clear();
        tfPhoto.clear();
        tfTel.clear();
        dpDateNaissance.setValue(null);
        tfUsername.clear();
        tfPrenom.clear();
        tfNom.clear();
        tfPhoto.clear();
        tfAge.clear();
    }
    
/*    public void sendSMS(String to, String message) {
        
    // Création d'une instance de NexmoClient en utilisant l'API key et l'API secret de votre compte Nexmo
    NexmoClient client = new NexmoClient.Builder()
        .apiKey("60db3bb8")
        .apiSecret("Pi7P7TBMRON7RdzG")
        .build();
        
    // Récupération du numéro de téléphone du destinataire depuis votre interface utilisateur
    String phoneNumber = tfTel.getText();

    // Création d'un objet TextMessage avec le numéro de téléphone de l'expéditeur, le numéro de téléphone du destinataire et le message à envoyer
    TextMessage text = new TextMessage("+216 55 640 171", to, message);

    try {
        // Envoi du message en utilisant le client SMS de NexmoClient
        SmsSubmissionResult[] response = client.getSmsClient().submitMessage(text);
            
        // Vérification du statut de l'envoi
        if (response[0].getStatus() == SmsSubmissionResult.STATUS_OK) {
            System.out.println("Message sent successfully.");
        } else {
            System.out.println("Message failed with error: " + response[0].getErrorText());
        }
    } catch (NexmoClientException e) {
        System.out.println("Failed to send SMS: " + e.getMessage());
    }
}*/

    
    
    
    /*    public void envoyerSMS(String numero, String message) {
    Twilio.init("ACa073b7c0dcf7051fc3cfa35c390d4c31", "836e7eb87c8ad0313f4b863e32d98d99");
    
    Message sms = Message.creator(
    new com.twilio.type.PhoneNumber(numero),
    new com.twilio.type.PhoneNumber("(620) 349-6558"),
    message)
    .create();
    
    System.out.println(sms.getSid());
    }*/

}
