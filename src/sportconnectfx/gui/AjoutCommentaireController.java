/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportconnectfx.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sportconnectfx.entities.Blog;
import sportconnectfx.entities.Commentaire;
import sportconnectfx.services.BlogCRUD;
import sportconnectfx.services.CommentaireCRUD;

/**
 * FXML Controller class
 *
 * @author manar
 */
public class AjoutCommentaireController implements Initializable {

    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnBlog;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btnNutrition;
    @FXML
    private Button btnCoaching;
    @FXML
    private Button btnProduit;
    @FXML
    private Button btnUser;
    @FXML
    private TextField tfpseudo;
    @FXML
    private TextArea tfcontenu;
    @FXML
    private ComboBox<Blog> tfidblog;
    @FXML
    private Button btnajoutcommentaire;
     @FXML
    private Button tfretour;

    /**
     * Initializes the controller class.
     */
    @Override
public void initialize(URL url, ResourceBundle rb) {
    // Récupérer la liste des blogs
    BlogCRUD blogCRUD = new BlogCRUD();
    List<Blog> blogs = blogCRUD.displayEntities();

    // Ajouter les blogs au ComboBox
    ObservableList<Blog> observableListBlogs = FXCollections.observableArrayList(blogs);
    tfidblog.setItems(observableListBlogs);
}
@FXML
private void ajoutcommentaire(ActionEvent event) {
// Récupérer les données du formulaire
String pseudo = tfpseudo.getText();
String contenu = tfcontenu.getText();
Blog idblog = (Blog) tfidblog.getSelectionModel().getSelectedItem();
// Vérifier si les champs sont vides
if (pseudo.isEmpty() || contenu.isEmpty()) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez remplir votre pseudo et contenues.");
    alert.showAndWait();
    return;
}

// Vérifier si les champs dépassent une certaine longueur
if (pseudo.length() > 7 || contenu.length() > 500) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
alert.setContentText("Le pseudo ne doit pas dépasser 7 caractères et le contenu ne doit pas dépasser 500 caractères.");
    alert.showAndWait();
    return;
}

// Créer un nouvel objet Commentaire
Commentaire commentaire = new Commentaire();
commentaire.setPseudo(pseudo);
commentaire.setContenu(contenu);
commentaire.setIdblog_id(idblog);

// Ajouter le commentaire à la base de données
CommentaireCRUD commentaireCRUD = new CommentaireCRUD();
commentaireCRUD.addCommentaire(commentaire);
// Call the addEntity method to insert the object into the database
Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Confirmation");
alert.setHeaderText(null);
alert.setContentText("Activité ajoutée");
alert.showAndWait();

// Effacer les champs du formulaire
tfpseudo.setText("");
tfcontenu.setText("");
tfcontenu.setText("");
try {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailCommentaire.fxml"));
    Parent root = loader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();

    // Close the current window
    Stage currentStage = (Stage) tfpseudo.getScene().getWindow();
    currentStage.close();
} catch (Exception e) {
    e.printStackTrace();
}
}
@FXML
    private void onretour(ActionEvent event) throws IOException {
         // Create a new Scene and load the DetailBlog.fxml file
    FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailCommentaire.fxml"));

    Parent root = loader.load();
    Scene scene = new Scene(root);

    // Get the Stage and set the new Scene
    Stage stage = (Stage) tfretour.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
     




}

