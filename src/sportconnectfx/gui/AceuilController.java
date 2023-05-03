/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportconnectfx.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author manar
 */
public class AceuilController implements Initializable {

    @FXML
    private Button btnblog;
    @FXML
    private Button btncommentaire;
    @FXML
    private AnchorPane main_form;
    @FXML
    private Button home_btn;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goblog(ActionEvent event) throws IOException {
         // Create a new Scene and load the DetailBlog.fxml file
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Backblogg.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);

    // Get the Stage and set the new Scene
    Stage stage = (Stage) btnblog.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
        @FXML

    private void gocomm(ActionEvent event) throws IOException {
         // Create a new Scene and load the DetailBlog.fxml file
    FXMLLoader loader = new FXMLLoader(getClass().getResource("blogimg.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);

    // Get the Stage and set the new Scene
    Stage stage = (Stage) btncommentaire.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
   
  
    }
    

