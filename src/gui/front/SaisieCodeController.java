/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.front;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author PC
 */
public class SaisieCodeController implements Initializable {

    @FXML
    private TextField code;
    @FXML
    private Button envoyer;

    private String codeGenere;
    
    private String emailClient;
    
    private TextField email;
    
    @FXML
    private TextField email1;
    @FXML
    private Button annuler;
    
    public void setEmail1(String message) {
        this.email1.setText(message);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setCode(String code) {
        this.code.setText(code);
    }
    /*public void setEmail(String mail) {
    this.emailClient = mail;
    }*/
    
    
    public void setCodeGenere(String codeGenere) {
        this.codeGenere = codeGenere;
    }
    
    @FXML
private void envoyerCode(ActionEvent event) throws IOException {
    //MdpOubieController mp = new MdpOubieController();
    String codeSaisi = code.getText();
    String email3 = email1.getText();

    
    if (codeSaisi.equals(codeGenere)) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReinitialiserMdp.fxml"));
        Parent root = loader.load();
        ReinitialiserMdpController controller = loader.getController();
        controller.setEmail2(email3);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        // Fermer la fenêtre de saisie de code
        Stage currentStage = (Stage) envoyer.getScene().getWindow();
        currentStage.close();
    } else {
        // Afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Code incorrect");
        alert.setHeaderText(null);
        alert.setContentText("Le code que vous avez saisi est incorrect.");
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
