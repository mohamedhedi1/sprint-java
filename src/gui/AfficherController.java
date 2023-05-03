/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AfficherController implements Initializable {

    
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtMdp;
    @FXML
    private TextField txtTel;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setTextId(String message) {
        this.txtId.setText(message);
    }
    
    public void setTextNom(String message) {
        this.txtNom.setText(message);
    }

    public void setTextPrenom(String prenom) {
        this.txtPrenom.setText(prenom);
    }
    
    public void setTextUsername(String message) {
        this.txtUsername.setText(message);
    }
    
    public void setTextEmail(String message) {
        this.txtEmail.setText(message);
    }
    
    public void setTextMdp(String message) {
        this.txtMdp.setText(message);
    }
    
    public void setTextTel(String message) {
        this.txtTel.setText(message);
    }
}
