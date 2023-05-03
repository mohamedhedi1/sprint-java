///*

package sportconnectfx.gui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.util.Set;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import sportconnectfx.entities.Blog;
import sportconnectfx.services.BlogCRUD;


/**
 * FXML Controller class
 *
 * @author manar
 */
public class AjoutBlogController implements Initializable {
          Blog p = new Blog();
          File f ;


    @FXML
    private Button tfButton;
    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfAuteur;
    @FXML
    private TextField tfDescription;
    @FXML
    private DatePicker tfDate;
    @FXML
    private Button tfImage;
     @FXML
    private Button retour;
    @FXML
    private TextField tfLoc;
      @FXML
    private ImageView imagep;
      private File selectedImageFile;
    private String selectedImagePath;

    

    /**
     * Initializes the controller class.
     */
    
    
    
@FXML
private void addphoto(ActionEvent event) throws IOException {
    FileChooser fc = new FileChooser();
    fc.setTitle("Ajouter une Image");
    fc.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"));
    f = fc.showOpenDialog(null);
    String DBPath = "C:\\xampp\\htdocs" + f.getName();
    String i = f.getName();
    if (f != null) {
        BufferedImage bufferedImage = ImageIO.read(f);
        WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
        String imagePath = "C://xampp/htdocs/img/" + f.getName();
        File destFile = new File(imagePath);
//        Files.copy(f.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        imagep.setImage(image);
        FileInputStream fin = new FileInputStream(f);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        for (int readNum; (readNum = fin.read(buf)) != -1;) {
            bos.write(buf, 0, readNum);
        }
        byte[] post_image = bos.toByteArray();
        // retourner la variable post_image si nécessaire
    }
}



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
@FXML
  private void Onclickajout(ActionEvent event) throws IOException {

    String titre = tfTitre.getText();
    String auteur = tfAuteur.getText();
    String description = tfDescription.getText();
    LocalDate localDate = tfDate.getValue();
    System.out.println(p.getImage());

    // Vérifier si les champs obligatoires sont remplis
    if (titre.isEmpty() || auteur.isEmpty() || description.isEmpty() || localDate == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs obligatoires !");
        alert.showAndWait();
        return; // Arrêter la méthode si les champs sont vides
    }

    // Vérifier si la date est valide
    Date date = null;
    try {
        date = java.sql.Date.valueOf(localDate);
    } catch (IllegalArgumentException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La date est invalide !");
        alert.showAndWait();
        return; // Arrêter la méthode si la date est invalide
    }

    // Copier l'image dans le dossier images et obtenir son chemin relatif
    String imagePath = f.getName();
    File destFile = new File(imagePath);
 Files.copy(f.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    // Mettre à jour le chemin relatif de l'image
    p.setImage(imagePath);

    // Ajouter une nouvelle entité Blog si tous les champs sont remplis et la date est valide
    BlogCRUD pcd = new BlogCRUD();

    Blog t = new Blog(titre, auteur, description, date, imagePath);
    pcd.addEntity(t);

    // Afficher un message de succès si l'ajout de la catégorie est réussi
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setHeaderText(null);
    alert.setContentText("Blog ajouté avec succès !");
    alert.showAndWait();

    // Create a new Scene and load the DetailBlog.fxml file
    FXMLLoader loader = new FXMLLoader(getClass().getResource("BackBlog.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);

    // Get the Stage and set the new Scene
    Stage stage = (Stage) tfButton.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
  
   @FXML

    private void retour(ActionEvent event) throws IOException {
         // Create a new Scene and load the DetailBlog.fxml file
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Backblogg.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);

    // Get the Stage and set the new Scene
    Stage stage = (Stage) retour.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
   

        }

