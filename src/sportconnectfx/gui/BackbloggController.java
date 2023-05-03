/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportconnectfx.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.io.FileInputStream;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sportconnectfx.entities.Blog;
import sportconnectfx.entities.Commentaire;
import sportconnectfx.services.BlogCRUD;
import sportconnectfx.services.BlogStatistics;
import sportconnectfx.tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author manar
 */
public class BackbloggController implements Initializable {

    @FXML
    private TableView<Blog> tableblog;
    @FXML
    private TableColumn<Blog, Integer> id;
    @FXML
    private TableColumn<Blog, String> tftitre;
    @FXML
    private TableColumn<Blog, String> tfauteur;
    @FXML
    private TableColumn<Blog, String> tfdescription;
    @FXML
    private TableColumn<Blog, Date> tfdate;
    @FXML
    private TableColumn<Blog, String> tfimage;
    @FXML
    private Button ajoutblog;
    @FXML
    private Button sms;
    @FXML
     
    private Button tfdetail;
    @FXML
     
    private Button supprimer;
    @FXML
    private Button updateblog;
    @FXML
    private Label userCountLabel;
    @FXML
    private TextField searchField;
        @FXML
        private PieChart blogStatsChart;
                @FXML
                private BlogStatistics blogStatistics;


        
     

    /**
     * Initializes the controller class.
     */
       @Override
    public void initialize(URL url, ResourceBundle rb) {
       BlogCRUD eq = new BlogCRUD();
ArrayList<Blog> blogs = (ArrayList<Blog>) eq.displayEntities();
ObservableList<Blog> observableBlogs = FXCollections.observableArrayList(blogs);
tableblog.setItems(observableBlogs);
searchField.textProperty().addListener((observable, oldValue, newValue) -> {
    // utiliser la méthode filter() de l'objet categories pour filtrer les résultats
    tableblog.setItems(observableBlogs.filtered(blog -> {
        String lowerCaseFilter = newValue.toLowerCase();
        return blog.getTitre().toLowerCase().contains(lowerCaseFilter)||
               blog.getAuteur().toLowerCase().contains(lowerCaseFilter)||
                blog.getDescription().toLowerCase().contains(lowerCaseFilter)
                
                ;
    }));
});

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tftitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tfauteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
        tfdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
         tfdate.setCellValueFactory(new PropertyValueFactory<>("date"));
          tfimage.setCellFactory(column ->{
            return new TableCell<Blog,String>(){
                final ImageView imageView = new ImageView();
                    {
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        setGraphic(imageView);
                    }
                protected void updateItem(String imageName, boolean empty) {
                    super.updateItem(imageName, empty);
                    if (imageName == null || empty) {
                        imageView.setImage(null);
                    }else{
                        try {
                            FileInputStream stream = new FileInputStream("C:\\xampp\\htdocs\\img\\" + imageName);
                            Image image = new Image(stream);
                            imageView.setImage(image);
                            imageView.setFitWidth(50); // Set the desired width and height here
                            imageView.setFitHeight(50);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        
                    }
                }    
            };
        }
        );
        tfimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        //List<Blog> blogs = displayEntities();
tableblog.setItems(FXCollections.observableArrayList(blogs));

//searchField.setOnKeyReleased(e -> {
//if (e.getCode() == KeyCode.ENTER) {
//search();
//}
//});
        blogStatistics = new BlogStatistics(blogs);
        blogStatsChart.setData(blogStatistics.getBlogStatsByAuthor());
    }
////@FXML
////private void search() {
////    String searchText = searchField.getText().toLowerCase();
////    ObservableList<Blog> blogs = tableblog.getItems();
////    ObservableList<Blog> filteredList = blogs.stream()
////        .filter(blog -> blog.getTitre().toLowerCase().contains(searchText)
////                || blog.getAuteur().toLowerCase().contains(searchText)
////                || blog.getDescription().toLowerCase().contains(searchText)
////                || blog.getDate().toString().toLowerCase().contains(searchText))
////        .collect(Collectors.toCollection(FXCollections::observableArrayList));
////    tableblog.setItems(filteredList);
////}      
//    private void filterBlog(String searchValue) {
//    ObservableList<Blog> filteredList = FXCollections.observableArrayList();
//    
//    for (Blog blog : observableReponses) {
//        if (blog.getMessage().toLowerCase().contains(searchValue.toLowerCase()) 
//                || blog.getDescription().toLowerCase().contains(searchValue.toLowerCase())
//                || blog.getTitre().toLowerCase().contains(searchValue.toLowerCase())
//                || blog.getAuteur().toLowerCase().contains(searchValue.toLowerCase())
//               ) {
//            filteredList.add(blog);
//        }
//    }
//    
//    tableblog.setItems(filteredList);
//}
//
//@FXML
//private void rechercherReponses() {
//    String searchValue = searchField.getText().toLowerCase();
//    filterBlog(searchValue);
//}

    
    @FXML
private void Btnsuppblog(ActionEvent event) {
    // Récupérer l'objet sélectionné dans la ListView
    Blog blog = tableblog.getSelectionModel().getSelectedItem();

    if (blog != null) {
        // Afficher une alerte de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer le blog " + blog.getTitre() + " ?");
        alert.setContentText("Cette action est irréversible.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // L'utilisateur a cliqué sur le bouton OK dans l'alerte, on peut supprimer la catégorie
            // Supprimer l'objet de la base de données en appelant la méthode "deleteEntity()"
            deleteEntity(blog);

            // Retirer l'objet de la ListView
            tableblog.getItems().remove(blog);

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
        tableblog.setItems(FXCollections.observableArrayList(blogs));  
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
    Blog blog = tableblog.getSelectionModel().getSelectedItem();

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
                tableblog.refresh();

                // Afficher un message de confirmation à l'utilisateur
                userCountLabel.setText("bLog mise à jour: " + blog.getTitre());
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
    Blog selectedBlog = tableblog.getSelectionModel().getSelectedItem();
    
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
    Stage stage = (Stage) tableblog.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
        
@FXML
    private void smsenvo(ActionEvent event) throws IOException {
         // Create a new Scene and load the DetailBlog.fxml file
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Smstwilio.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);

    // Get the Stage and set the new Scene
    Stage stage = (Stage) sms.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
        
    
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
