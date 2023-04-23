/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportconnectp;

import entities.Equipement;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.EquipementCRUD;

/**
 *
 * @author Mohamed
 */
public class SportconnectP extends Application {

    
     @Override
    public void start(Stage primaryStage) throws Exception {
      try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Menu.fxml"));
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Main application!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       /*  EquipementCRUD ec = new EquipementCRUD();
        ec.ajouter(new Equipement("hamdi","hhamdi"));
        System.out.println(ec.afficher().toString());
        ec.supprimer(1);
        ec.modifier( new Equipement(2,"hedi","image"));*/
       launch(args);
    }

   
    
}
