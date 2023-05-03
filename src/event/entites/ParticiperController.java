package event.entites;

import event.services.ParticipantsCrud;
import event.tools.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class ParticiperController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField tlf;
    @FXML
    private TextField mail;
    @FXML
    private Button btn;
    @FXML
    private ComboBox<Activites> activitesid;

    private ParticipantsCrud participantsCrud;

    // Define a list of bad words
    private List<String> badWords = Arrays.asList("fuck", "shit", "putain");
    @FXML
    private TextField prix;
    @FXML
    private Button front;
    @FXML
    private Button admin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Initialiser l'objet participantsCrud avec une instance de ParticipantsCrud
        participantsCrud = new ParticipantsCrud(new MyConnection().getCnx());

        // Récupérer toutes les activités et les ajouter à la combobox
        ArrayList<Activites> activites = (ArrayList<Activites>) participantsCrud.getAllActivites();
        activitesid.getItems().addAll(activites);

    }
    
    private void sendEmail(String subject, String body, String recipient) {
    
     //Le code commence par définir les paramètres du serveur SMTP de GMail
     
    String host = "smtp.gmail.com";
    String username = "pidev800@gmail.com";
    String password = "nmeitynhjipylzed";
    String from = "pidev800@gmail.com";
    
    //Activation de parametre par Proprieties
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", "587");
    
    
    //Creation d'une session par getinstnace
    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(username, password);
                }
            });
    
    try {
        // MimeMessage qui représente l'email à envoyer et définit le sujet, le destinataire et le corps de l'email.
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(recipient));
        message.setSubject(subject);
        message.setText(body);

        //la méthode statique send de la classe Transport pour envoyer l'email.
        Transport.send(message);

        System.out.println("Email sent to " + recipient);

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}

    @FXML
private void OnParticipe(ActionEvent event) {// Get the text entered by the user
    String nomParticipant = nom.getText();
    String prenomParticipant = prenom.getText();
    String tlfParticipant = tlf.getText();
    String mailParticipant = mail.getText();
    String prixText = prix.getText(); // Récupérer la valeur texte entrée dans le champ de texte
    float prixParticipants = Float.parseFloat(prixText); // Convertir la valeur texte en un nombre de type float

    // Check if any bad word is present in the entered text
    boolean hasBadWord = false;
    for (String word : badWords) {
        if (nomParticipant.contains(word) || prenomParticipant.contains(word) || tlfParticipant.contains(word) || mailParticipant.contains(word)) {
            hasBadWord = true;
            break;
        }
    }

    if (hasBadWord) {
        // Display a message to the user about the use of bad language
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Please avoid using bad language.");

        alert.showAndWait();
    } else {
        // Check if any field is empty
        if (nomParticipant.isEmpty() || prenomParticipant.isEmpty() || tlfParticipant.isEmpty() || mailParticipant.isEmpty()) {
            // Display a message to the user about empty fields
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the fields.");

            alert.showAndWait();
        } else {
            // Check if telephone number is valid
            if (!tlfParticipant.matches("\\d{8}")) {
                // Display a message to the user about invalid telephone number
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid telephone number.");

                alert.showAndWait();
            } else {
                // Check if email is valid
                if (!mailParticipant.contains("@") || !mailParticipant.contains(".")) {
                    // Display a message to the user about invalid email
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a valid email address.");

                    alert.showAndWait();
                } else {
                    // Check if the email is repeated between 3 and 5 times
                    int count = participantsCrud.countParticipantByEmail(mailParticipant);
                    if (count >= 3 && count <= 5) {
                        // Apply 30% discount on the price
                        prixParticipants = prixParticipants * 0.7f;
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Congratulations you will receive an additional 30% discount on the price due to the presence of your email between 3 and 5 times.");

                        alert.showAndWait();
                    }

                    // Afficher le prix final dans le champ de texte
                    prix.setText(String.valueOf(prixParticipants));

                    // If all checks pass, add participant to the list
                    Participants p = new Participants(nomParticipant, prenomParticipant, tlfParticipant, mailParticipant, prixParticipants);
                    participantsCrud.addParticipant(p);
                    // Display a message to the user about successful addition
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Tu es sur de participer ?");

                        
                 
                    alert.showAndWait();
                
                String subject = "Nouveau participants";            
                String body = "Un nouveau participant a été enregistré avec une réduction de prix de 30% " +mailParticipant;
                String recipient = "pidev800@gmail.com";
    
    sendEmail(subject, body, recipient);
    

                // Clear the text fields
                nom.clear();
                prenom.clear();
                tlf.clear();
                mail.clear();
                prix.clear();
      
            }
        }
    }
}
}

    @FXML
    private void OnFront(ActionEvent event) throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("/event/entites/Front.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void Admin(ActionEvent event) {
    }
}