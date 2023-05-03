package event.entites;

import event.tools.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SupprimerCategorieController implements Initializable {

@FXML
private Button Supprimer;
private Label userCountLabel;
private ListView<Categorie> CategoriesListView;
@FXML
private Button Update;
@FXML
private Button btnaj;
@FXML
private Button idreturn;
@FXML
private TableView<Categorie> abc;
@FXML
private TableColumn<Categorie, String> nn;
@FXML
private TableColumn<Categorie, String> dd;
@FXML
private TextField search;
@FXML
private Button btnUser;
@FXML
private Button btnBlog;
@FXML
private Button btnEvent;
    @FXML
    private Button pdf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

         ObservableList<Categorie> categories = FXCollections.observableArrayList(displayEntities());

        // Définir les colonnes de la table
            nn.setCellValueFactory(new PropertyValueFactory<>("nom"));
            dd.setCellValueFactory(new PropertyValueFactory<>("description"));

            // Associer les données à la table
             abc.setItems(categories);
             
             //Stream
             search.textProperty().addListener((observable, oldValue, newValue) -> {
    // utiliser la méthode filter() de l'objet categories pour filtrer les résultats
    abc.setItems(categories.filtered(categorie -> {
        String lowerCaseFilter = newValue.toLowerCase();
        return categorie.getNom().toLowerCase().contains(lowerCaseFilter)
                || categorie.getDescription().toLowerCase().contains(lowerCaseFilter);
    }));    
});

          // Récupérer les catégories de la base de données
       }

  @FXML
private void BtnSupprimerOffre(ActionEvent event) {
// Récupérer l'objet sélectionné dans la table
Categorie categorie = abc.getSelectionModel().getSelectedItem();

if (categorie != null) {
    // Afficher une alerte de confirmation
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de suppression");
    alert.setHeaderText("Êtes-vous sûr de vouloir supprimer la catégorie " + categorie.getNom() + " ?");
    alert.setContentText("Cette action est irréversible.");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        // L'utilisateur a cliqué sur le bouton OK dans l'alerte, on peut supprimer la catégorie
        // Supprimer l'objet de la base de données en appelant la méthode "deleteEntity()"
        deleteEntity(categorie);

        // Retirer l'objet de la table
        abc.getItems().remove(categorie);

        // Afficher un message de confirmation à l'utilisateur
        userCountLabel.setText("Categorie supprimée: " + categorie.getNom());
    }
} else {
    // Aucune catégorie sélectionnée, afficher un message d'erreur à l'utilisateur
    userCountLabel.setText("Veuillez sélectionner une categorie à supprimer.");
}
}


     public List<Categorie> displayEntities() {
        List<Categorie> categories = new ArrayList<>();
        try {
            String requete = "SELECT * FROM categorie";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Categorie c = new Categorie();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setDescription(rs.getString("description"));
                categories.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categories;
    }

     public void deleteEntity(Categorie e) {
        try {
            String requete ="DELETE FROM categorie WHERE nom = ? AND description = ?";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setString(1, e.getNom());
            pst.setString(2, e.getDescription());
            pst.executeUpdate();
            System.out.println("Categorie supprimé");
        }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

 @FXML
private void BtnUpdate(ActionEvent event) {
// Récupérer l'objet sélectionné dans la ListView
Categorie categorie = abc.getSelectionModel().getSelectedItem();

if (categorie != null) {
    // Afficher une boîte de dialogue pour saisir les nouvelles informations de la catégorie
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Modification de catégorie");
    dialog.setHeaderText("Modifier la catégorie " + categorie.getNom());
    dialog.setContentText("Nom:");

    // Pré-remplir le champ "Nom" avec l'ancien nom de la catégorie
    dialog.getEditor().setText(categorie.getNom());

    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
        String newNom = result.get();

        // Afficher une boîte de dialogue pour saisir la nouvelle description de la catégorie
        TextInputDialog dialog2 = new TextInputDialog();
        dialog2.setTitle("Modification de catégorie");
        dialog2.setHeaderText("Modifier la catégorie " + newNom);
        dialog2.setContentText("Description:");

        // Pré-remplir le champ "Description" avec l'ancienne description de la catégorie
        dialog2.getEditor().setText(categorie.getDescription());

        Optional<String> result2 = dialog2.showAndWait();
        if (result2.isPresent()) {
            String newDescription = result2.get();

            // Mettre à jour l'objet dans la base de données en appelant la méthode "updateEntity()"
            categorie.setNom(newNom);
            categorie.setDescription(newDescription);
            updateEntity(categorie);

            // Rafraîchir la ListView pour afficher les modifications
            abc.refresh();

            // Afficher un message de confirmation à l'utilisateur
            userCountLabel.setText("Catégorie mise à jour: " + newNom);
        }
    }
} else {
    // Aucune catégorie sélectionnée, afficher un message d'erreur à l'utilisateur
    userCountLabel.setText("Veuillez sélectionner une categorie à modifier.");
}
}


public void updateEntity(Categorie e) {
    try {
        String requete = "UPDATE categorie SET nom=?, description=? WHERE id=?";
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
        pst.setString(1, e.getNom());
        pst.setString(2, e.getDescription());
        pst.setInt(3, e.getId());
        pst.executeUpdate();
        System.out.println("Catégorie mise à jour");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    @FXML
    private void OnAdd(ActionEvent event)throws  IOException {

    Parent root = FXMLLoader.load(getClass().getResource("/event/entites/inscription.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

      @FXML
    private void OnReturn(ActionEvent event) throws IOException {

    Parent root = FXMLLoader.load(getClass().getResource("EvenComposant.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) idreturn.getScene().getWindow();
    stage.setScene(scene);
    stage.show();


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
    private void onPdf(ActionEvent event) {
    }



}