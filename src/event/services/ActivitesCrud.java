package event.services;

import event.entites.Activites;
import event.entites.Categorie;
import event.interfaces.Activitesrud;
import event.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ActivitesCrud implements Activitesrud<Activites> {

    public void addEntity(Activites e) {
        try {
            String requete = "INSERT INTO activites (categorie_id, titre, description, image, images) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setInt(1, e.getCategorie().getId());
            pst.setString(2, e.getTitre());
            pst.setString(3, e.getDescription());
            pst.setString(4, e.getImage());
            pst.setString(5, e.getImages());
            pst.executeUpdate();
            System.out.println("Activité ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Categorie> getAllCategories() {
    List<Categorie> categories = new ArrayList<>();
    try {
        String requete = "SELECT * FROM categorie";
        Statement st = new MyConnection().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            Categorie categorie = new Categorie(id, nom);
            categories.add(categorie);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return categories;
}


    public void deleteEntity(int id) {
    try {
        String requete = "DELETE FROM activites WHERE id=?";
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
        pst.setInt(1, id);
        pst.executeUpdate();
        System.out.println("L'activité avec l'ID " + id + " a été supprimée avec succès !");
    } catch (SQLException ex) {
        System.out.println("Une erreur est survenue lors de la suppression de l'activité avec l'ID " + id + " : " + ex.getMessage());
    }
}
public List<Activites> displayEntities() {
    List<Activites> activites = new ArrayList<>();
    try {
        String requete = "SELECT activites.*, categorie.nom as categorie_nom FROM activites JOIN categorie ON activites.categorie_id = categorie.id";
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Activites a = new Activites();
            a.setId(rs.getInt("id"));
            a.setTitre(rs.getString("titre"));
            a.setDescription(rs.getString("description"));
            a.setImage(rs.getString("image"));
            a.setImages(rs.getString("images"));

            String categorieNom = rs.getString("categorie_nom");
            Categorie categorie = new Categorie();
            categorie.setNom(categorieNom);
            a.setCategorie(categorie);

            activites.add(a);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return activites;
}



public void updateEntity(int id, Activites activite) {
        // créer une connexion à la base de données
     
    try {
                 
        // définir la requête SQL avec des paramètres pour l'ID et les valeurs à mettre à jour
        String sql = "UPDATE activites SET titre=?, description=?, images=? WHERE id=?";
        PreparedStatement ps = new MyConnection().getCnx().prepareStatement(sql);
      
        // définir les paramètres de la requête à partir des valeurs de l'entité fournie
        ps.setString(1, activite.getTitre());
        ps.setString(2, activite.getDescription());
        ps.setString(3, activite.getImages());
        ps.setInt(4, id);

        // exécuter la requête SQL
        ps.executeUpdate();

        // fermer la connexion
        ps.close(); 
        
    } catch (SQLException e) {
        System.err.println("Une erreur s'est produite lors de la mise à jour de l'activité : " + e.getMessage());

        e.printStackTrace();
    }
}

    

    

   

}


    




    
    

    

  


