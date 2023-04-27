/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Exercice;
import entities.Serie;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.ExerciceCRUD;
import services.SerieCRUD;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class SerieFrontController implements Initializable {

    @FXML
    private ListView<Serie> listEquipementF;
    private ObservableList<Serie> equipements;

     @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        SerieCRUD eq = new SerieCRUD();
        try {
            equipements = eq.afficher();
        } catch (SQLException ex) {
            Logger.getLogger(EquipementFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        listEquipementF.setItems(equipements);
        listEquipementF.setCellFactory(equipementListView -> new EquipementListCell());
    }

    private class EquipementListCell extends ListCell<Serie> {
        private ImageView imageView = new ImageView();
        private Text nomExerciceText = new Text();
        private Text exercicesText = new Text();
       
                

        @Override
        protected void updateItem(Serie serie, boolean empty) {
            super.updateItem(serie, empty);

            if (empty || serie == null) {
                setGraphic(null);
                setText(null);
            } else {
                
             // Création d'une instance de l'ImageView et configuration de la taille de l'image
ImageView imageView = new ImageView(new Image("file:/" + serie.getImSerie()));
imageView.setFitWidth(150);
imageView.setFitHeight(150);

// Création du texte
Text nomExerciceText = new Text(serie.getTitreSerie());
Text exercicesText = new Text("listes exercice:\n "+serie.getImageSerie());



// Création de la VBox et ajout de l'image et du texte
VBox vbox = new VBox(imageView, nomExerciceText,exercicesText );
vbox.setAlignment(Pos.CENTER);
vbox.setSpacing(10);

// Affichage de la VBox
setGraphic(vbox);
setText(null);

            }
        }
    }     
    
}
