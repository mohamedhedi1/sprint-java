/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class MenuController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane AP;
    @FXML
    private AnchorPane main_form;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPage("AfficherEquipement");
    }    
    
     private void loadPage(String page){          
        Parent root = null;
        try {
        root = FXMLLoader.load(getClass().getResource(page+".fxml"));

        } catch (Exception ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE,null,ex);
        }
        bp.setCenter(root);
    }

    @FXML
    private void AjouterEq(ActionEvent event) {
        loadPage("AjouterEquipement");
    }

    @FXML
    private void AjouterEx(ActionEvent event) {
        loadPage("AjouterExercice");
    }

    @FXML
    private void AffEquip(ActionEvent event) {
        loadPage("AfficherEquipement");
    }

    @FXML
    private void AffSerie(ActionEvent event) {
        loadPage("AfficherSerie");
    }

    @FXML
    private void AffEx(ActionEvent event) {
        loadPage("AfficherExercice");
    }

    @FXML
    private void AjouterSerie(ActionEvent event) {
        loadPage("AjouterSerie");
    }
 
    
}
