package event.entites;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import event.services.ActivitesCrud;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AfficherEvenController implements Initializable {

    @FXML
    private ListView<Activites> EvenListView;
    @FXML
    private Label userCountLabel;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnaddd;
    @FXML
    private Button idreturn;
    @FXML
    private Button pdf;
    @FXML
    private TextField match_search;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnBlog;
    @FXML
    private Button btnEvent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // créer une instance de la classe contenant la méthode displayEntities()
        ActivitesCrud activitesCrud = new ActivitesCrud();

        // appeler la méthode et afficher les résultats dans la ListView
        List<Activites> activites = activitesCrud.displayEntities();
        EvenListView.getItems().addAll(activites);
        //EvenListView.setPrefSize(400, 300);


        // personnaliser l'affichage des éléments de la ListView
        EvenListView.setCellFactory(new Callback<ListView<Activites>, ListCell<Activites>>() {
            @Override
            public ListCell<Activites> call(ListView<Activites> listView) {
                return new ListCell<Activites>() {
                    @Override
                    protected void updateItem(Activites activite, boolean empty) {
                        super.updateItem(activite, empty);

                        if (activite == null || empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            // créer une ImageView pour afficher l'image
                            ImageView imageView = new ImageView();
                            try {
                                // charger l'image depuis le fichier
                                Image image = new Image(new FileInputStream(activite.getImages()));

                                // configurer la taille de l'image
                                imageView.setFitWidth(100);
                                imageView.setFitHeight(100);

                                // afficher l'image dans l'ImageView
                                imageView.setImage(image);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                            // afficher le titre et la description de l'activité
                            Label titleLabel = new Label(activite.getTitre());
                            Label descLabel = new Label(activite.getDescription());
                            //Label locLabel = new Label(activite.getImage());
                            

                            // créer un VBox pour afficher le tout
                            VBox vBox = new VBox(imageView, titleLabel, descLabel);
                            vBox.setSpacing(10);

                            // afficher le VBox dans la cellule
                            setGraphic(vBox);
                        }
                    }
                };
            }
        });

        // afficher le nombre d'éléments dans le Label
        userCountLabel.setText("Nombre d'activités : " + activites.size());
    }

   @FXML
private void OnDelete(ActionEvent event) {
    // obtenir l'élément sélectionné dans la ListView
    Activites activite = EvenListView.getSelectionModel().getSelectedItem();
    if (activite != null) {
        // afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer l'activité " + activite.getTitre() + " ?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // appeler la méthode de suppression en utilisant l'ID de l'entité sélectionnée
            ActivitesCrud activitesCrud = new ActivitesCrud();
            activitesCrud.deleteEntity(activite.getId());

            // supprimer l'élément de la ListView
            EvenListView.getItems().remove(activite);
            
            // afficher une alerte de suppression réussie
            Alert alertSuccess = new Alert(AlertType.INFORMATION);
            alertSuccess.setTitle("Suppression réussie");
            alertSuccess.setHeaderText(null);
            alertSuccess.setContentText("L'activité a été supprimée avec succès !");
            alertSuccess.showAndWait();
        }
    } else {
        // afficher une alerte si aucun élément n'est sélectionné dans la ListView
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Aucune activité sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une activité à supprimer.");
        alert.showAndWait();
    }
}

    @FXML
private void OnUpdate(ActionEvent event) {
    // obtenir l'élément sélectionné dans la ListView
    Activites activite = EvenListView.getSelectionModel().getSelectedItem();
    if (activite != null) {
        // charger la vue de modification de l'entité
        Dialog<Activites> dialog = new Dialog<>();
        dialog.setTitle("Modifier l'activité");
        dialog.setHeaderText(null);

        // charger le fichier FXML de la vue de modification
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/event/entites/Formulaire.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // obtenir le contrôleur de la vue de modification et appeler sa méthode setEntity
        FormulaireController modifierActiviteController = loader.getController();
        modifierActiviteController.setEntity(activite);

        // ajouter les boutons de confirmation et d'annulation
        ButtonType confirmButtonType = new ButtonType("Enregistrer", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);

        // valider les données saisies lors de la confirmation
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirmButtonType) {
                // appeler la méthode de mise à jour en utilisant les données saisies dans la vue de modification
                ActivitesCrud activitesCrud = new ActivitesCrud();
                Activites updatedActivite = modifierActiviteController.getUpdatedEntity();
                activitesCrud.updateEntity(updatedActivite.getId(), updatedActivite);

                // remplacer l'élément dans la ListView par la nouvelle version mise à jour
                int index = EvenListView.getSelectionModel().getSelectedIndex();
                EvenListView.getItems().set(index, updatedActivite);

                // afficher une alerte de mise à jour réussie
                Alert alertSuccess = new Alert(AlertType.INFORMATION);
                alertSuccess.setTitle("Mise à jour réussie");
                alertSuccess.setHeaderText(null);
                alertSuccess.setContentText("L'activité a été mise à jour avec succès !");
                alertSuccess.showAndWait();

                // renvoyer l'entité mise à jour pour afficher les modifications dans la cellule correspondante
                return updatedActivite;
            }
            return null;
        });

        // afficher la boîte de dialogue de modification
        dialog.showAndWait();
    } else {
        // afficher une alerte si aucun élément n'est sélectionné dans la ListView
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Aucune activité sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une activité à modifier.");
        alert.showAndWait();
    }
}

    @FXML
    private void OnAdd(ActionEvent event) throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("/event/entites/AddEven.fxml"));
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
    private void OnDownload(ActionEvent event) {
    try {
        // Créer une instance de la classe Document de iText
        Document document = new Document();

        // Ouvrir le document en mode écriture
        PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Dell\\Documents\\NetBeansProjects\\Event\\src\\event\\entites\\imagesfichier.pdf"));

        // Ouvrir le document pour écrire le contenu
        document.open();

        // Ajouter le contenu au document
        // Par exemple :
        document.add(new Paragraph("Hello, world!"));

        // Fermer le document
        document.close();

        // Afficher une notification de réussite
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Le fichier a été généré avec succès et enregistré.");
        alert.showAndWait();
    } catch (FileNotFoundException | DocumentException e) {
        // Afficher une notification d'erreur si une exception est levée
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur est survenue lors de la génération du fichier PDF : " + e.getMessage());
        alert.showAndWait();
    }
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




}
