package sportconnectfx.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sportconnectfx.entities.Blog;
import sportconnectfx.services.BlogCRUD;
import sportconnectfx.tools.MyConnection;

public class DetailBlogController implements Initializable {

    @FXML
    private ListView<Blog> tlist;
    @FXML
    private Label userCountLabel;
     @FXML
    private Button Supprimer;
    @FXML
    private Button update;
     @FXML
    private Button tfajout;
      @FXML
    private Button tfretour;
      @FXML
    private Button tfdetail;
   
    /**
     * Initializes the controller class.
//     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        BlogCRUD blogcrud = new BlogCRUD();
List<Blog> blogs = blogcrud.displayEntities();
tlist.getItems().addAll(blogs);
    }
     
     @FXML
private void Btnsuppblog(ActionEvent event) {
    // Récupérer l'objet sélectionné dans la ListView
    Blog blog = tlist.getSelectionModel().getSelectedItem();

    if (blog != null) {
        // Afficher une alerte de confirmation
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer le blog " + blog.getTitre() + " ?");
        alert.setContentText("Cette action est irréversible.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // L'utilisateur a cliqué sur le bouton OK dans l'alerte, on peut supprimer la catégorie
            // Supprimer l'objet de la base de données en appelant la méthode "deleteEntity()"
            deleteEntity(blog);

            // Retirer l'objet de la ListView
            tlist.getItems().remove(blog);

            // Afficher un message de confirmation à l'utilisateur
            userCountLabel.setText("blog supprimée: " + blog.getTitre());
        }
    } else {
        // Aucune catégorie sélectionnée, afficher un message d'erreur à l'utilisateur
        userCountLabel.setText("Veuillez sélectionner une categorie à supprimer.");
    }
}
public  List<Blog> displayEntities() {
    List<Blog> blogs = new ArrayList<>();
    try {
        String requete = "SELECT * FROM blog";
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Blog blog = new Blog();
            blog.setId(rs.getInt("id"));
            blog.setTitre(rs.getString("titre"));
            blog.setAuteur(rs.getString("auteur"));
            blog.setDescription(rs.getString("description"));
            blog.setDate(rs.getDate("date"));
            blog.setImage(rs.getString("image"));

            blogs.add(blog);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    tlist.setCellFactory(new Callback<ListView<Blog>, ListCell<Blog>>() {
        @Override
        public ListCell<Blog> call(ListView<Blog> listView) {
            return new ListCell<Blog>() {
                private ImageView imageView = new ImageView();
                private Label titleLabel = new Label();

                @Override
                protected void updateItem(Blog blog, boolean empty) {
                    super.updateItem(blog, empty);

                    if (empty || blog == null) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        titleLabel.setText(blog.getTitre());
                        File file = new File(blog.getImage());
                        if (file.exists()) {
                            try {
                                Image image = new Image(file.toURI().toURL().toString());
                                imageView.setImage(image);
                                imageView.setFitWidth(100);
                                imageView.setFitHeight(100);
                                setGraphic(new VBox(titleLabel, imageView));
                            } catch (Exception e) {
                                System.out.println("Erreur lors du chargement de l'image : " + e.getMessage());
                                setGraphic(titleLabel);
                            }
                        } else {
                            setGraphic(titleLabel);
                        }
                    }
                }
            };
        }
    });

   // tlist.getItems().setAll(blogs);
   return blogs;
}


    public void updateEntity(Blog e) {
    try {
        String requete = "UPDATE blog SET titre=?, auteur=?, description=?, date=?,image=? WHERE id=?";
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
        pst.setString(1, e.getTitre());
        pst.setString(2, e.getAuteur());
        pst.setString(3, e.getDescription());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(e.getDate());
        pst.setString(4, dateString);
        
        pst.setString(5, e.getImage());
        pst.setInt(6, e.getId());
        
        pst.executeUpdate();
        System.out.println("blog mise à jour");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }

    
    public void deleteEntity(Blog e) {
        try {
            String requete ="DELETE FROM blog WHERE titre = ?";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setString(1, e.getTitre());
            pst.executeUpdate();
            System.out.println("Blog supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
private void BtnUpdate(ActionEvent event) {
    // Récupérer l'objet sélectionné dans la ListView
    Blog blog = tlist.getSelectionModel().getSelectedItem();

    if (blog != null) {
        // Afficher une boîte de dialogue pour saisir les nouvelles informations de la catégorie
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modification de blog");
        dialog.setHeaderText("Modifier la catégorie " + blog.getTitre());
        dialog.setContentText("titre:");

        // Pré-remplir le champ "titre" avec l'ancien titre de blog
        dialog.getEditor().setText(blog.getTitre());

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            // L'utilisateur a saisi un nouveau titre de blog on peut mettre à jour l'objet
            blog.setTitre(result.get());

            // Afficher une boîte de dialogue pour saisir la nouvelle auteur de la catégorie
            TextInputDialog dialog2 = new TextInputDialog();
            dialog2.setTitle("Modification de blog");
            dialog2.setHeaderText("Modifier la blog " + blog.getAuteur());
            dialog2.setContentText("auteur:");

            // Pré-remplir le champ "auteur" avec l'ancien auteur du blog
            dialog2.getEditor().setText(blog.getAuteur());

            Optional<String> result2 = dialog2.showAndWait();
            if (result2.isPresent()) {
                // L'utilisateur a saisi une nouvelle auteur de blog, on peut mettre à jour l'objet
                blog.setAuteur(result2.get());

                // Afficher une boîte de dialogue pour saisir la nouvelle description de la catégorie
                TextInputDialog dialog3 = new TextInputDialog();
                dialog3.setTitle("Modification de blog");
                dialog3.setHeaderText("Modifier la blog " + blog.getDescription());
                dialog3.setContentText("description:");

                // Pré-remplir le champ "description" avec l'ancienne description du blog
                dialog3.getEditor().setText(blog.getDescription());

                Optional<String> result3 = dialog3.showAndWait();
                if (result3.isPresent()) {
                    // L'utilisateur a saisi une nouvelle description de blog, on peut mettre à jour l'objet
                    blog.setDescription(result3.get());

                    // Afficher une boîte de dialogue pour saisir la nouvelle date de la catégorie
                    TextInputDialog dialog4 = new TextInputDialog();
                    dialog4.setTitle("Modification de blog");
                    dialog4.setHeaderText("Modifier la blog " + blog.getDate());
                    dialog4.setContentText("date:");

                    // Pré-remplir le champ "date" avec l'ancienne date du blog
                    dialog4.getEditor().setText(blog.getDate().toString());

                    Optional<String> result4 = dialog4.showAndWait();
                    if (result4.isPresent()) {
                        // L'utilisateur a saisi une nouvelle date de blog, on peut mettre à jour l'objet
                        LocalDate newDate = LocalDate.parse(result4.get());

                        // Afficher une boîte de dialogue pour saisir la nouvelle image de la catégorie
                        TextInputDialog dialog5 = new TextInputDialog();
                        dialog5.setTitle("Modification de blog");
                        dialog5.setHeaderText("Modifier la blog " + blog.getImage());
                        dialog5.setContentText("image:");

                        // Pré-remplir le champ "image" avec l'ancienne image du blog
                        dialog5.getEditor().setText(blog.getImage());

                        Optional<String> result5 = dialog5.showAndWait();
                        if (result5.isPresent()) {
                            // L'utilisateur a saisi

                blog.setImage(result5.get());
                
                
                
                

                // Mettre à jour l'objet dans la base de données en appelant la méthode "updateEntity()"
                updateEntity(blog);

                // Rafraîchir la ListView pour afficher les modifications
                tlist.refresh();

                // Afficher un message de confirmation à l'utilisateur
                userCountLabel.setText("Catégorie mise à jour: " + blog.getTitre());
            }
        }
    } else {
        // Aucune catégorie sélectionnée, afficher un message d'erreur à l'utilisateur
        userCountLabel.setText("Veuillez sélectionner une categorie à modifier.");
    }
    }
}
    }}

@FXML
void handleDetailsButton(ActionEvent event) throws IOException {
    // Récupération de l'objet Blog sélectionné dans la liste view
    Blog selectedBlog = tlist.getSelectionModel().getSelectedItem();
    
    // Création d'une nouvelle fenêtre pour afficher les détails du blog
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Blogfront.fxml"));
    Parent root = loader.load();
    BlogfrontController detailBlogController = loader.getController();

    // Chargement des données du blog dans le contrôleur de vue des détails
    detailBlogController.setBlog(selectedBlog);

    // Affichage de la nouvelle fenêtre
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
}
 @FXML
    private void goajout(ActionEvent event) throws IOException {
         // Create a new Scene and load the DetailBlog.fxml file
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutBlog.fxml"));

    Parent root = loader.load();
    Scene scene = new Scene(root);

    // Get the Stage and set the new Scene
    Stage stage = (Stage) tfajout.getScene().getWindow();
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




}
    

   

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


