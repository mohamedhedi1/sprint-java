/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.front;

import entities.client;
import gui.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.Front;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ReinitialiserMdpController implements Initializable {

    @FXML
    private Button valider;
    @FXML
    private TextField mdp;
    @FXML
    private TextField email2;
    
    private String emailClient;
    @FXML
    private Button annuler;
    
    /*public void setEmailClient(String mail) {
    this.emailClient = mail;
    email2.setText(emailClient);
    }*/
    public void setEmail2(String message) {
        this.email2.setText(message);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*try {
        // TODO
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MdpOublieController.fxml"));
        Parent root = loader.load();
        MdpOublieController controller = loader.getController();
        String emailClient = controller.email.getText();
        } catch (IOException ex) {
        Logger.getLogger(ReinitialiserMdpController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }    

    @FXML
    private void valider(ActionEvent event) throws IOException {
        String emailClient = email2.getText();
    String password = mdp.getText();
        Front f = new Front(); 
        client c = new client();
    c.setEmail(emailClient);
    c.setPassword(mdp.getText());

    // Appeler la méthode modifierMdp pour modifier le mot de passe dans la base de données
    boolean modifie = f.modifierMdp(c);

    if(modifie) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Mot de passe modifié");
    alert.setHeaderText(null);
    alert.setContentText("Votre mot de passe a été modifié avec succès !");
    alert.showAndWait();
    
    Stage currentStage = (Stage) valider.getScene().getWindow();
        currentStage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginF.fxml"));
        Parent root = loader.load();
        LoginFController controller = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    else {
        // Afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur s'est produite lors de la modification de votre mot de passe.");
        alert.showAndWait();
    }
    }

    @FXML
    private void annuler(ActionEvent event) {
        Scene scene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();
        stage.close();
    }
}
