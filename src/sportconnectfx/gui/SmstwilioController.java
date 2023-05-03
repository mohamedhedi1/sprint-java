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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sportconnectfx.services.Smstwilio;

/**
 * FXML Controller class
 *
 * @author manar
 */
public class SmstwilioController implements Initializable {
      String accountSID = "AC63c82efc852d7d63e6aaacea1b0e0908";
    String authToken = "413af0f4d563bfcf563f12e28501ba5b";
     String recipientPhoneNumber = "+19789066250";
    
    private Smstwilio twilioSMS;

    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField messageTextField;
     @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
     @Override
      public void initialize(URL url, ResourceBundle rb) {
        twilioSMS = new Smstwilio(accountSID, authToken, recipientPhoneNumber);
}

    

  @FXML
public void handleSendButtonAction(ActionEvent event) {
    String recipientPhoneNumber = phoneTextField.getText();
    String message = messageTextField.getText();
    if (!recipientPhoneNumber.matches("^\\+?[1-9]\\d{1,14}$")) {
        Alert alert = new Alert(AlertType.ERROR, "Invalid phone number format: " + recipientPhoneNumber);
        alert.showAndWait();
        return;
    }
    try {
        twilioSMS.sendSMS(recipientPhoneNumber, message);
        Alert alert = new Alert(AlertType.INFORMATION, "SMS sent successfully");
        alert.showAndWait();
    } catch (IllegalArgumentException e) {
        Alert alert = new Alert(AlertType.ERROR, e.getMessage());
        alert.showAndWait();
    }
}
   @FXML
    private void back(ActionEvent event) throws IOException {
         // Create a new Scene and load the DetailBlog.fxml file
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Backblogg.fxml"));

    Parent root = loader.load();
    Scene scene = new Scene(root);

    // Get the Stage and set the new Scene
    Stage stage = (Stage) back.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    
}
