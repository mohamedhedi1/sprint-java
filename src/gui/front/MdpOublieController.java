/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.front;


import com.sun.javafx.scene.web.skin.HTMLEditorSkin;
import java.io.IOException;
import java.io.StringWriter;
import javax.mail.*;
import javax.mail.internet.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author PC
 */
public class MdpOublieController implements Initializable {

    @FXML
    private Button envoyer;
    @FXML
    public TextField email;
    
    private String codeGenere;
    @FXML
    private Button annuler;
    /**
     * Initializes the controller class.
     */
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /*public void setCodeGenere(String codeGenere) {
    this.codeGenere = codeGenere;
    }*/
    
    @FXML
    private void envoyer(ActionEvent event) {
        // récupérer l'adresse e-mail entrée par l'utilisateur
    String mail = email.getText();

    // générer le code
    this.codeGenere = generateCode();

    try {
        // envoyer le mail
        sendPasswordResetCode(mail, codeGenere);

        // afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Code envoyé");
        alert.setContentText("Le code de réinitialisation a été envoyé à l'adresse suivante : " + mail);
        alert.showAndWait();

        // charger la fenêtre de saisie du code
        FXMLLoader loader = new FXMLLoader(getClass().getResource("saisieCode.fxml"));
        Parent root = loader.load();
        SaisieCodeController controller = loader.getController();
        controller.setCodeGenere(codeGenere);
        controller.setEmail1(mail);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    } catch (Exception ex) {
        // afficher une erreur en cas de problème d'envoi
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur d'envoi");
        alert.setContentText("Une erreur est survenue lors de l'envoi du code de reinitialisation. Veuillez réessayer plus tard.");
        alert.showAndWait();
    }
    }
    
    
    public static String sendMail(String recipient) throws Exception {
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");

    String sender = "franckrussell.fongang@esprit.tn";
    String password = "Allemagne20";

    Session session = Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(sender, password);
        }
    });

    // Génération du code
    String codeGenere = generateCode();

    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(sender));
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
    message.setSubject("Code de réinitialisation de mot de passe");

    String htmlBody = "<h1>Code de réinitialisation de mot de passe :</h1>"
            + "<p>Votre code de réinitialisation est : <strong>" + codeGenere + "</strong>.</p>"
            + "<p>Entrez ce code sur la page de réinitialisation de mot de passe pour continuer.</p>";

    message.setContent(htmlBody, "text/html");

    Transport.send(message);

    return codeGenere;
}

    public static void sendPasswordResetCode(String recipientEmail, String resetCode) throws Exception {
    String senderEmail = "franckrussell.fongang@esprit.tn";
    String senderPassword = "Allemagne20";

    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");

    Session session = Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmail, senderPassword);
        }
    });

    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(senderEmail));
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
    message.setSubject("Code de reinitialisation de mot de passe");

    String htmlBody = "<h1>Code de reinitialisation de mot de passe :</h1>"
            + "<p>Votre code de reinitialisation est : <strong>" + resetCode + "</strong>.</p>"
            + "<p>Entrez ce code sur la page de reinitialisation de mot de passe pour continuer.</p>";

    message.setContent(htmlBody, "text/html");

    Transport.send(message);
}

    
public static String generateCode() {
    // Générer un code aléatoire à 6 chiffres
    Random rand = new Random();
    int codeInt = rand.nextInt(9000) + 1000; // entre 100000 et 999999
    return Integer.toString(codeInt);
}

    @FXML
    private void annuler(ActionEvent event) {
        Scene scene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();
        stage.close();
    }
}
    

    

