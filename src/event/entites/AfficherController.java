import event.entites.Categorie;
import event.tools.MyConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AfficherController implements Initializable {

    @FXML
    private Label userCountLabel;

    

    @FXML
    private TableColumn<Categorie, String> nn;

    @FXML
    private TableColumn<Categorie, String> dd;
    @FXML
    private TableView<Categorie> abc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Récupérer les catégories de la base de données
        ObservableList<Categorie> categories = FXCollections.observableArrayList(displayEntities());

        // Définir les colonnes de la table
        nn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        dd.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Associer les données à la table
        abc.setItems(categories);
    }

    // Fonction pour récupérer les catégories de la base de données
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
}
