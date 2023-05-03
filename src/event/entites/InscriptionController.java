    package event.entites;

    import java.net.URL;
    import java.util.ResourceBundle;

    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Button;
    import javafx.scene.control.TextField;

    import event.services.CatCrud;
    import event.entites.Categorie;
    import java.io.IOException;
    import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.stage.Stage;

    public class InscriptionController implements Initializable {

        @FXML
        private TextField tfNom;
        @FXML
        private TextField tfDescription;
        @FXML
        private Button tfBtn;
    @FXML
    private TextField search;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnBlog;
    @FXML
    private Button btnEvent;
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
        private void OnClick(ActionEvent event) throws IOException {
            if (validateFields()) {
                String nom = tfNom.getText();
                String description = tfDescription.getText();
                Categorie c = new Categorie(nom, description);
                CatCrud catCrud = new CatCrud();
                catCrud.addEntity(c);
                 // Afficher un message de succès si l'ajout de la catégorie est réussi
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("La catégorie a été ajoutée avec succès !");
                alert.showAndWait();

                 // Create a new Scene and load the SupprimerCategorie.fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/event/entites/SupprimerCategorie.fxml"));



                Parent root = loader.load();
                Scene scene = new Scene(root);

                // Get the Stage and set the new Scene
                Stage stage = (Stage) tfBtn.getScene().getWindow();
                stage.setScene(scene);
                stage.show();


            }
        }

        private boolean validateFields() {
            String nom = tfNom.getText();
            String description = tfDescription.getText();
            String nomRegex = "^[a-zA-Z]+$"; // Regex pour valider le nom (lettres uniquement)
            String descRegex = "^[a-zA-Z0-9\\s]+$"; // Regex pour valider la description (lettres, chiffres et espaces uniquement)

            if (nom.isEmpty()) {
                // Afficher un message d'erreur si le champ de nom est vide
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez saisir un nom !");
                alert.showAndWait();
                return false;
            }

            if (!nom.matches(nomRegex)) {
                // Afficher un message d'erreur si le nom est invalide
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le nom doit contenir des lettres uniquement !");
                alert.showAndWait();
                return false;
            }

            if (description.isEmpty()) {
                // Afficher un message d'erreur si le champ de description est vide
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez saisir une description !");
                alert.showAndWait();
                return false;
            }

            if (!description.matches(descRegex)) {
                // Afficher un message d'erreur si la description est invalide
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("La description doit contenir des lettres, des chiffres et des espaces uniquement !");
                alert.showAndWait();
                return false;
            }

            return true;
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
    private void OnReturn(ActionEvent event) {
    }
    }
