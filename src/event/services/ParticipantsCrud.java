package event.services;

import event.entites.Activites;
import event.entites.Participants;
import event.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParticipantsCrud  {

    private Connection cnx;

    public ParticipantsCrud(Connection cnx) {
        this.cnx = cnx;
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
                p.setMail(rs.getString("mail"));
                participants.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return participants;
    }
        
         public void addParticipant(Participants p) {
    try {
        //sur l'id
        String requete = "SELECT COUNT(*) as count FROM participants WHERE mail = ?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, p.getMail());
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int count = rs.getInt("count");
            if (count >= 3 && count <= 5) {
                float newPrix = p.getPrix() * 0.7f; // Réduction de 30%
                p.setPrix(newPrix);
            }
        }
        
        // Ajout de l'utilisateur
        requete = "INSERT INTO participants (name, prenom, telephone, mail, prix) VALUES (?, ?, ?, ?, ?)";
        pst = cnx.prepareStatement(requete);
        pst.setString(1, p.getName());
        pst.setString(2, p.getPrenom());
        pst.setString(3, p.getTelephone());
        pst.setString(4, p.getMail());
        pst.setFloat(5, p.getPrix());
        pst.executeUpdate();
        System.out.println("Participant ajouté avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

         public int countParticipantByEmail(String email) {
    int count = 0;
    try {
        String requete = "SELECT COUNT(*) FROM participants WHERE mail = ?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return count;
}

        
        public List<Activites> getAllActivites() {
    List<Activites> activites = new ArrayList<>();
    try {
        String requete = "SELECT * FROM activites";
        Statement st = new MyConnection().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            int id = rs.getInt("id");
            String titre = rs.getString("titre");
            Activites activite = new Activites(id, titre);
            activites.add(activite);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return activites;
}
        
       public void addParticipation(int idParticipant, int idActivite) {
    String req = "INSERT INTO `participants_activites` (`participants_id`, `activites_id`) "
               + "VALUES (?, ?)";
    try {
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, idParticipant);
        st.setInt(2, idActivite);
        st.executeUpdate();
        System.out.println("Participation ajoutée avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

}
       
    
}





   



