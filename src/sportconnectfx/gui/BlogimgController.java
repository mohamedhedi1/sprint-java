/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportconnectfx.gui;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sportconnectfx.entities.Blog;
import sportconnectfx.tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author manar
 */
public class BlogimgController implements Initializable {

    @FXML
    private TableView<Blog> tabimg;
    @FXML
    private TableColumn<Blog, String> image;
     @FXML
    private TableColumn<Blog, String> tftitre;
      @FXML
    private TableColumn<Blog, String> auteur;
     @FXML
    private TableColumn<Blog, String> description;
     
      @FXML
         private TableColumn<Blog, String> date;
  
    private Button deteil;
          @FXML

    private Button anuuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                tftitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
                auteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
         date.setCellValueFactory(new PropertyValueFactory<>("date"));

        image.setCellFactory(column ->{
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
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        List<Blog> blogs = displayEntities();
tabimg.setItems(FXCollections.observableArrayList(blogs));
    }    
    
    
    
    
     public  List<Blog> displayEntities() {
       List<Blog> blogs = new ArrayList<>();
        tabimg.setItems(FXCollections.observableArrayList(blogs));  
        try {
        String requete = "SELECT * FROM blog";
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Blog blog = new Blog();
            blog.setTitre(rs.getString("titre"));
           ;
            blog.setImage(rs.getString("image"));
             blog.setAuteur(rs.getString("auteur"));
            blog.setDescription(rs.getString("description"));
            blog.setDate(rs.getDate("date"));
             
            blogs.add(blog);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
            return blogs;
    }
     @FXML
void handleDetailButtonAction(ActionEvent event) throws IOException {
    // Récupération de l'objet Blog sélectionné dans la liste view
    Blog selectedBlog = tabimg.getSelectionModel().getSelectedItem();
    
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

    private void anuuler(ActionEvent event) throws IOException {
         // Create a new Scene and load the DetailBlog.fxml file
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Aceuil.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);

    // Get the Stage and set the new Scene
    Stage stage = (Stage) anuuler.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
   
     
     

}

     

    

