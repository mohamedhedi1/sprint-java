/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleurs;

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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class MatchUserController implements Initializable {

    @FXML
    private AnchorPane reservation_form;
    @FXML
    private Button btnEvent;
    @FXML
    private AnchorPane add_reservation_form;
    @FXML
    private DatePicker dpdate;
    @FXML
    private ComboBox<?> cbmatch;
    @FXML
    private ComboBox<?> cbuser;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField tfnbrbillet;
    @FXML
    private Button btnretour;
    @FXML
    private AnchorPane reservation_details;
    @FXML
    private ListView<?> reservationList;
    @FXML
    private Button btnupdate;
    @FXML
    private AnchorPane update_reservation_form;
    @FXML
    private Button btnretour1;
    @FXML
    private TextField tfnbrbillet1;
    @FXML
    private Button btnmodifier;
    @FXML
    private ComboBox<?> cbuser1;
    @FXML
    private ComboBox<?> cbmatch1;
    @FXML
    private DatePicker dpdate1;
    @FXML
    private TextField tfid;
    @FXML
    private AnchorPane classement_form;
    @FXML
    private TableView<?> classement_table;
    @FXML
    private TableColumn<?, ?> rangCol;
    @FXML
    private TableColumn<?, ?> equipeCol;
    @FXML
    private TableColumn<?, ?> mjCol;
    @FXML
    private TableColumn<?, ?> victoireCol;
    @FXML
    private TableColumn<?, ?> nulCol;
    @FXML
    private TableColumn<?, ?> defaiteCol;
    @FXML
    private TableColumn<?, ?> bmCol;
    @FXML
    private TableColumn<?, ?> beCol;
    @FXML
    private TableColumn<?, ?> dbCol;
    @FXML
    private TableColumn<?, ?> pointsCol;
    @FXML
    private Button admin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void evenement(ActionEvent event) throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("/event/entites/Participer.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void ajouterReservation(ActionEvent event)  {
        
   
    }

    @FXML
    private void reserverBillet(ActionEvent event) {
    }

    @FXML
    private void ReservationSelect(MouseEvent event) {
    }

    @FXML
    private void switchform2(ActionEvent event) {
    }

    @FXML
    private void modifierReservation(ActionEvent event) {
    }

    @FXML
    private void switchform3(ActionEvent event) {
    }

    @FXML
private void Admin(ActionEvent event) throws IOException {
    // Charger la vue Back.fxml
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/event/entites/Back.fxml"));
    Parent root = loader.load();

    // Créer une nouvelle scène
    Scene scene = new Scene(root);

    // Obtenir la fenêtre actuelle
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Définir la nouvelle scène de la fenêtre
    stage.setScene(scene);

    // Afficher la nouvelle scène
    stage.show();
}

    
}
