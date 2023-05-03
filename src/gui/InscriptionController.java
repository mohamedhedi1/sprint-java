/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.User;
import entities.utilisateur;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class InscriptionController implements Initializable {

    //composant graphiquje
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
    private Button tfBtn;
    @FXML
    private Button button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void enregistrer(ActionEvent event) 
    {
        
            //int id = Integer.parseInt(tfId.getText());
            String nom = tfNom.getText();
            String prenom = tfPrenom.getText();
            String username = tfUsername.getText();
            String email = tfEmail.getText();
            String password = tfMdp.getText();
            int tel = Integer.parseInt(tfTel.getText());
            
            utilisateur u =new utilisateur(nom,prenom,username,email,password,tel);
            User us = new User();
            
            us.ajouter(u);
            
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher.fxml"));
         try {
            Parent root = loader.load();
            
            AfficherController ac = loader.getController();
            ac.setTextId(""+u.getId());
            ac.setTextNom(u.getNom());
            ac.setTextPrenom(u.getPrenom());
            ac.setTextUsername(u.getUsername());
            ac.setTextEmail(u.getEmail());
            ac.setTextMdp(u.getPassword());
            ac.setTextTel(""+u.getTel());
            
            tfNom.getScene().setRoot(root);
        } catch (IOException ex) {
             System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void test(ActionEvent event) {
       
    }
    
}
