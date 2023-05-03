/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportconnectfx.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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


public class BlogfrontController implements Initializable {
    @FXML
private TextField titleLabel;

@FXML
private TextField authorLabel;
@FXML
private TextField descriptionLabel;

@FXML
private TextField dateLabel;
@FXML
private ImageView imageView;
@FXML
    private TextField tfpseudo;
    @FXML
    private TextArea tfcontenu;
//    @FXML
//    private ComboBox<Blog> tfidblog;
    @FXML
    private Button btnajoutcommentaire;
     @FXML
    private Button like;
      @FXML
    private Button dislike;
      @FXML
    private Label nbLikesLabel;
       @FXML
    private Label nbDislikesLabel;
      
      
    
    
    
    private Blog currentBlog;
    private boolean isLiked = false;



   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        BlogCRUD blogCRUD = new BlogCRUD();
    List<Blog> blogs = blogCRUD.displayEntities();
//         // Ajouter les blogs au ComboBox
//    ObservableList<Blog> observableListBlogs = FXCollections.observableArrayList(blogs);
//    tfidblog.setItems(observableListBlogs);

    } 
public void setBlog(Blog blog) {
    if (blog != null) {
        // Chargement des données du blog dans les labels et l'ImageView
        titleLabel.setText(blog.getTitre());
        authorLabel.setText(blog.getAuteur());
        dateLabel.setText(blog.getDate().toString());
        descriptionLabel.setText(blog.getDescription());

        // Charger l'image depuis le fichier
        File file = new File("C:\\xampp\\htdocs\\img\\" + blog.getImage());
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);

        // Mettre à jour le nombre de likes et de dislikes
        nbLikesLabel.setText("Likes : " + blog.getLikes());
        nbDislikesLabel.setText("Dislikes : " + blog.getDislikes());

        // Sauvegarder le blog courant dans la variable currentBlog
        currentBlog = blog;
    }
}



@FXML
private void commenter(ActionEvent event) {
    // Récupérer les données du formulaire
    String pseudo = tfpseudo.getText();
    String contenu = tfcontenu.getText();

    // Vérifier si un blog est sélectionné
    if (currentBlog == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucun blog sélectionné");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un blog avant de commenter.");
        alert.showAndWait();
        return;
    }

    // Créer un nouvel objet Commentaire
    Commentaire commentaire = new Commentaire();
    commentaire.setPseudo(pseudo);
    commentaire.setContenu(contenu);
    commentaire.setIdblog_id(currentBlog);

    // Ajouter le commentaire à la base de données
    CommentaireCRUD commentaireCRUD = new CommentaireCRUD();
    commentaireCRUD.addCommentaire(commentaire);

    // Afficher un message de confirmation
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Commentaire ajouté avec succès.");
    alert.showAndWait();

    // Effacer les champs du formulaire
    tfpseudo.setText("");
    tfcontenu.setText("");
}
@FXML
private void onLikeButtonClicked(ActionEvent event) {
    if (!isLiked) {
        currentBlog.incrementLikes();
        // Mettre à jour l'affichage des likes
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbd", "root", "");
            PreparedStatement stmt = conn.prepareStatement("UPDATE blog SET likes = ? WHERE id = ?");
            stmt.setInt(1, currentBlog.getLikes());
            stmt.setInt(2, currentBlog.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Mettre à jour le texte des labels
        nbLikesLabel.setText("Likes : " + currentBlog.getLikes());

        // Désactiver le bouton "like"
        like.setDisable(true);
        isLiked = true;
    }
}


@FXML
private void onDislikeButtonClicked(ActionEvent event) {
    if (!isLiked) {
        currentBlog.incrementDislikes();
        // Mettre à jour l'affichage des dislikes
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbd", "root", "");
            PreparedStatement stmt = conn.prepareStatement("UPDATE blog SET dislikes = ? WHERE id = ?");
            stmt.setInt(1, currentBlog.getDislikes());
            stmt.setInt(2, currentBlog.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Mettre à jour le texte des labels
        nbDislikesLabel.setText("Dislikes : " + currentBlog.getDislikes());

        // Désactiver le bouton "dislike"
        dislike.setDisable(true);
        isLiked = true;
    }
}

   




   
}











