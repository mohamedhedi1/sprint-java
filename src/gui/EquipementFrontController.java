/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Equipement;
import java.awt.Insets;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import services.EquipementCRUD;


/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class EquipementFrontController implements Initializable {

    @FXML
    private ListView<Equipement> listEquipementF;

 private ObservableList<Equipement> equipements;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        EquipementCRUD eq = new EquipementCRUD();
        try {
            equipements = eq.afficher();
        } catch (SQLException ex) {
            Logger.getLogger(EquipementFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        listEquipementF.setItems(equipements);
        listEquipementF.setCellFactory(equipementListView -> new EquipementListCell());
    }

    private class EquipementListCell extends ListCell<Equipement> {
        private ImageView imageView = new ImageView();
        private Text nomEquipementText = new Text();

        @Override
        protected void updateItem(Equipement equipement, boolean empty) {
            super.updateItem(equipement, empty);

            if (empty || equipement == null) {
                setGraphic(null);
                setText(null);
            } else {
                
             // Création d'une instance de l'ImageView et configuration de la taille de l'image
ImageView imageView = new ImageView(new Image("file:/" + equipement.getImageEquipement()));
imageView.setFitWidth(150);
imageView.setFitHeight(150);

// Création du texte
Text nomEquipementText = new Text(equipement.getNomEquipement());

// Création de la VBox et ajout de l'image et du texte
VBox vbox = new VBox(imageView, nomEquipementText);
vbox.setAlignment(Pos.CENTER);
vbox.setSpacing(10);

// Affichage de la VBox
setGraphic(vbox);
setText(null);

            }
        }
    }
}


