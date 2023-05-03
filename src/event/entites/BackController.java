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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class BackController implements Initializable {
    @FXML
    private AnchorPane main_form;
    @FXML
    private Button btnNutrition;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnCoaching;
    @FXML
    private Button btnEvent;
    
    @FXML
    private Button btnBlog;
    @FXML
    private TextField match_search;
    @FXML
    private Button idreturn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        // TODO
    }    


    @FXML
    private void evenement(ActionEvent event) throws IOException {
        
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/event/entites/EvenComposant.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void OnReturn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) idreturn.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }



    
}
