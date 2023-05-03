/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.entites;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class EvenComposantController implements Initializable {

    @FXML
    private Button btnBlog;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btnUser;
    @FXML
    private Button idreturn;
    @FXML
    private TextField match_search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        Image image = new Image("file:C:\\Users\\Dell\\Downloads\\Sport connect (3).png");
//        tfimg.setImage(image);
        
        // TODO
    }    

    @FXML
    private void Categories(ActionEvent event) throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("/event/entites/SupprimerCategorie.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void participants(ActionEvent event) throws IOException  {
        
    Parent root = FXMLLoader.load(getClass().getResource("/event/entites/participants.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
   
    }

    @FXML
    private void Activites(ActionEvent event)  throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("/event/entites/AfficherEven.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void OnReturn(ActionEvent event) throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("Back.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) idreturn.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
        
     
    }
    
}
