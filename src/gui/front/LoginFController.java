/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.front;

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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.Session;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class LoginFController implements Initializable {

    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtmdp;
    @FXML
    private Button se_connecter;
    @FXML
    private Button inscription;
    @FXML
    private TextField txtmdp2;
    @FXML
    private Label mdp_oublie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         mdp_oublie.setOnMouseClicked(event -> {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mdpOublie.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
    }
});
        // TODO
    }    

    @FXML
    private void se_connecter(ActionEvent event) {
        Session s = new Session();
        String email = txtemail.getText();
        String password = txtmdp.getText();
        boolean isValidUser = s.verifierClient(email, password);

        if (isValidUser) {
            Session session = Session.getInstance();
            session.setEmail(email);
            session.setLoggedIn(true);
            
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SportConnectFront.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
            
        } else {
            // Afficher un message d'erreur
            System.out.println("erreur");
            annuler();
        }
    }

    public void annuler()
    {
    txtemail.clear();
    txtmdp.clear();
    }

    @FXML
    private void inscription(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inscrire.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }
    
    
   




}
