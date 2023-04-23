/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Equipement;
import entities.Exercice;
import entities.Serie;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.EquipementCRUD;
import services.ExerciceCRUD;
import services.SerieCRUD;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AfficherSerieController implements Initializable {

    @FXML
    private TableView<Serie> serieTable;
    @FXML
    private TableColumn<Serie, String> id_col;
    @FXML
    private TableColumn<Serie, String> nom_col;
    @FXML
    private TableColumn<Serie, String> col_listEx;
    @FXML
    private Button btnSupprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         try {
            SerieShowListData();
        } catch (SQLException ex) {
            
        }
        
    }   
    
    public ObservableList<Serie> SerieListData() throws SQLException {

        return new SerieCRUD().afficher();
    }
    
     private ObservableList<Serie> SerieList; 
     
       public void SerieShowListData() throws SQLException {
        SerieList = SerieListData();

        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("titreSerie"));
       col_listEx.setCellValueFactory(new PropertyValueFactory<>("imageSerie"));
       
       
        

        serieTable.setItems(SerieList);

    }
       
            public void RefreshSerieShowListData() throws SQLException {
            SerieList.clear();
           SerieList = SerieListData();

        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nomEquipement"));
         col_listEx.setCellValueFactory(new PropertyValueFactory<>("imageSerie"));
       
       
        

        serieTable.setItems(SerieList);

    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        
             Serie u = serieTable.getSelectionModel().getSelectedItem();
    
    if (u != null) {
        SerieCRUD us = new SerieCRUD();
        us.supprimer(u.getId());
        RefreshSerieShowListData();
    } else {
         Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Selectionner une serie !");
            alertType.show();
        
       
    }
    }
   
    
}
