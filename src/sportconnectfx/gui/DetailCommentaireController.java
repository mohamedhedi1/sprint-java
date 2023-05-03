/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportconnectfx.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sportconnectfx.entities.Blog;
import sportconnectfx.entities.Commentaire;
import sportconnectfx.tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author manar
 */
public class DetailCommentaireController implements Initializable {

   @FXML
    private TableView<Commentaire> tableView;
    @FXML
    private TableColumn<Commentaire, Integer> idColumn;
    @FXML
    private TableColumn<Commentaire, String> pseudoColumn;
    @FXML
    private TableColumn<Commentaire, String> contenuColumn;
    @FXML
    private TableColumn<Commentaire, Blog> blogIdColumn;
     @FXML
        private Button suppcomm;
        @FXML
    private Label userCountLabel;
         @FXML
        private Button tfajou;
 @FXML
        private Button tfretour;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        pseudoColumn.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
        contenuColumn.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        blogIdColumn.setCellValueFactory(new PropertyValueFactory<>("idblog_id"));
        List<Commentaire> commentaires = displayCommentaire();
tableView.setItems(FXCollections.observableArrayList(commentaires));

    }
    
      @FXML
private void supprimercommentaire(ActionEvent event) {
    // Récupérer l'objet sélectionné dans la ListView
    Commentaire commentaire = tableView.getSelectionModel().getSelectedItem();

    if (commentaire != null) {
        // Afficher une alerte de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer le blog " + commentaire.getPseudo() + " ?");
        alert.setContentText("Cette action est irréversible.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // L'utilisateur a cliqué sur le bouton OK dans l'alerte, on peut supprimer la catégorie
            // Supprimer l'objet de la base de données en appelant la méthode "deleteEntity()"
            deleteEntity(commentaire);

            // Retirer l'objet de la ListView
            tableView.getItems().remove(commentaire);

            // Afficher un message de confirmation à l'utilisateur
            userCountLabel.setText("blog supprimée: " + commentaire.getPseudo());
        }
    } else {
        // Aucune catégorie sélectionnée, afficher un message d'erreur à l'utilisateur
        userCountLabel.setText("Veuillez sélectionner une categorie à supprimer.");
    }
}
    
     public List<Commentaire> displayCommentaire() {
        List<Commentaire> commentaires = new ArrayList<>();
        tableView.setItems(FXCollections.observableArrayList(commentaires));

        try {
            String requete = "SELECT * FROM commentaire";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Commentaire c = new Commentaire();
                c.setId(rs.getInt("id"));
                c.setPseudo(rs.getString("pseudo"));
                c.setContenu(rs.getString("contenu"));
              int blogId = rs.getInt("idblog_id");
            Blog blog = new Blog();
            blog.setId(blogId);
            c.setIdblog_id(blog);
                

               
                

                commentaires.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commentaires;
    }
     
     
 public void deleteEntity(Commentaire e) {
        try {
            String requete ="DELETE FROM commentaire WHERE pseudo = ?";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setString(1, e.getPseudo());
            pst.executeUpdate();
            System.out.println("commentaire supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
     
     
     
    
}
@FXML
    private void goajoutcomm(ActionEvent event) throws IOException {
         // Create a new Scene and load the DetailBlog.fxml file
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCommentaire.fxml"));

    Parent root = loader.load();
    Scene scene = new Scene(root);

    // Get the Stage and set the new Scene
    Stage stage = (Stage) tfajou.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
     
@FXML
    private void onretour(ActionEvent event) throws IOException {
         // Create a new Scene and load the DetailBlog.fxml file
  FXMLLoader loader = new FXMLLoader(getClass().getResource("Aceuil.fxml"));

    Parent root = loader.load();
    Scene scene = new Scene(root);

    // Get the Stage and set the new Scene
    Stage stage = (Stage) tfretour.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
      public void updateEntity(Commentaire e) {
    try {
        String requete = "UPDATE blog SET pseudo=?, contenu=?, idblog_id=? WHERE id=?";
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
        pst.setString(1, e.getPseudo());
        pst.setString(2, e.getContenu());
     //   pst.setString(3, e.getIdblog_id());
        
      
        
        pst.executeUpdate();
        System.out.println("commentaire mise à jour");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }
         @FXML
private void BtnUpdate(ActionEvent event) {
    // Récupérer l'objet sélectionné dans la ListView
    Commentaire commentaire = tableView.getSelectionModel().getSelectedItem();

    if (commentaire != null) {
        // Afficher une boîte de dialogue pour saisir les nouvelles informations de la catégorie
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modification de blog");
        dialog.setHeaderText("Modifier le pseudo " + commentaire.getPseudo());
        dialog.setContentText("titre:");

        // Pré-remplir le champ "titre" avec l'ancien titre de blog
        dialog.getEditor().setText(commentaire.getPseudo());

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            // L'utilisateur a saisi un nouveau titre de blog on peut mettre à jour l'objet
            commentaire.setPseudo(result.get());

            // Afficher une boîte de dialogue pour saisir la nouvelle auteur de la catégorie
            TextInputDialog dialog2 = new TextInputDialog();
            dialog2.setTitle("Modification de contenu");
            dialog2.setHeaderText("Modifier la blog " + commentaire.getContenu());
            dialog2.setContentText("conteny:");

            // Pré-remplir le champ "auteur" avec l'ancien auteur du blog
            dialog2.getEditor().setText(commentaire.getContenu());

            Optional<String> result2 = dialog2.showAndWait();
            if (result2.isPresent()) {
                // L'utilisateur a saisi une nouvelle auteur de blog, on peut mettre à jour l'objet
                commentaire.setContenu(result2.get());

                
                   
                
                
                
                

                // Mettre à jour l'objet dans la base de données en appelant la méthode "updateEntity()"
                updateEntity(commentaire);

                // Rafraîchir la ListView pour afficher les modifications
                tableView.refresh();

                // Afficher un message de confirmation à l'utilisateur
                userCountLabel.setText("commentaire mise à jour: " + commentaire.getPseudo());
            }
        }
    } else {
        // Aucune catégorie sélectionnée, afficher un message d'erreur à l'utilisateur
        userCountLabel.setText("Veuillez sélectionner une categorie à modifier.");
    }
    }
}
    



