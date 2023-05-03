package event.entites;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import event.services.ParticipantsCrud;
import event.tools.MyConnection;
import java.awt.print.Pageable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import javafx.print.*;
import javafx.scene.Node;

public class ParticipantsController implements Initializable {

    @FXML
private TableView<Participants> tableView;

@FXML
private TableColumn<Participants, Integer> idColumn;

@FXML
private TableColumn<Participants, String> nameColumn;

@FXML
private TableColumn<Participants, String> prenomColumn;

@FXML
private TableColumn<Participants, String> telephoneColumn;

@FXML
private TableColumn<Participants, String> emailColumn;
    @FXML
    private Button idreturn;
    @FXML
    private TextField idrecherche;
    @FXML
    private Button search;
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

    ParticipantsCrud participantsCrud = new ParticipantsCrud(new MyConnection().getCnx());
    List<Participants> participants = participantsCrud.displayParticipants();
    ObservableList<Participants> data = FXCollections.observableArrayList(participants);

    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));

    tableView.setItems(data);

}

    public List<Participants> displayParticipants() {
    List<Participants> participants = new ArrayList<>();
    try {
        String requete = "SELECT * FROM participants";
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Participants p = new Participants();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setPrenom(rs.getString("prenom"));
            p.setTelephone(rs.getString("telephone"));     
            p.setMail(rs.getString("email"));
            participants.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return participants;
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
private void OnSearch(ActionEvent event) {
    String searchTerm = idrecherche.getText().trim();
    List<Participants> participants = new ArrayList<>();
    try {
        String requete = "SELECT * FROM participants WHERE name LIKE ? OR prenom LIKE ? OR telephone LIKE ? OR mail LIKE ?";
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
        pst.setString(1, "%" + searchTerm + "%");
        pst.setString(2, "%" + searchTerm + "%");
        pst.setString(3, "%" + searchTerm + "%");
        pst.setString(4, "%" + searchTerm + "%");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Participants p = new Participants();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setPrenom(rs.getString("prenom"));
            p.setTelephone(rs.getString("telephone"));
            p.setMail(rs.getString("mail"));
            participants.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    ObservableList<Participants> data = FXCollections.observableArrayList(participants);
    tableView.setItems(data);
}

    @FXML
private void OnDownload(ActionEvent event) throws IOException, DocumentException {
    ParticipantsCrud participantsCrud = new ParticipantsCrud(new MyConnection().getCnx());
    List<Participants> participants = participantsCrud.displayParticipants();

    // Créer un document PDF vide
    Document document = new Document();
    
        // Écrire le contenu du document
        PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Dell\\Documents\\NetBeansProjects\\Event\\src\\event\\entites\\images\\Participants.pdf"));
        document.open();

        // Créer une table pour afficher les données
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Ajout des entêtes de colonne
        PdfPCell idHeader = new PdfPCell(new Phrase("ID"));
        idHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(idHeader);

        PdfPCell nameHeader = new PdfPCell(new Phrase("Nom"));
        nameHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(nameHeader);

        PdfPCell prenomHeader = new PdfPCell(new Phrase("Prénom"));
        prenomHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(prenomHeader);

        PdfPCell telephoneHeader = new PdfPCell(new Phrase("Téléphone"));
        telephoneHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(telephoneHeader);

        PdfPCell mailHeader = new PdfPCell(new Phrase("Email"));
        mailHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(mailHeader);

        // Ajout des données de chaque participant dans la table
        for (Participants participant : participants) {
            
            PdfPCell nameCell = new PdfPCell(new Phrase(participant.getName()));
            nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(nameCell);

            PdfPCell prenomCell = new PdfPCell(new Phrase(participant.getPrenom()));
            prenomCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(prenomCell);

            PdfPCell telephoneCell = new PdfPCell(new Phrase(participant.getTelephone()));
            telephoneCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(telephoneCell);

            PdfPCell mailCell = new PdfPCell(new Phrase(participant.getMail()));
            mailCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(mailCell);
        }

        document.add(table);
        document.close();

        // PrinterJob est une classe de l'API Java qui permet de gérer les travaux d'impression. 
        //Elle fournit des méthodes pour créer et afficher une boîte de dialogue d'impression
PrinterJob printerJob = PrinterJob.createPrinterJob();

// Vérifier si l'impression est possible
if (printerJob != null) {
    // Charger le document PDF à imprimer
    File file = new File("C:\\Users\\Dell\\Documents\\NetBeansProjects\\Event\\src\\event\\entites\\images\\Participants.pdf");
    PDDocument pdfDocument = PDDocument.load(file);

    // Définir le contenu à imprimer
    Pageable pageable = new PDFPageable(pdfDocument);
    //printerJob.setPageable(pageable);

    // Afficher la boîte de dialogue d'impression et imprimer si l'utilisateur appuie sur OK
    boolean printed = printerJob.showPrintDialog(null);
    if (printed) {
        //printerJob.print();
    }

    // Fermer le document
    pdfDocument.close();
}
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Impression réussie");
        alert.setHeaderText(null);
        alert.setContentText("L'imprime echoué la machine n'est pas connecté à une imprimante! Fchier télécharger avec succés");
        alert.showAndWait();

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
