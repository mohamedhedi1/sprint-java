/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.Session;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class LoginController implements Initializable {

    @FXML
    private Button se_connecter;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtmdp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void se_connecter(ActionEvent event) {
        Session s = new Session();
        String email = txtemail.getText();
        String password = txtmdp.getText();
        boolean isValidUser = s.verifierUtilisateur(email, password);

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

}
