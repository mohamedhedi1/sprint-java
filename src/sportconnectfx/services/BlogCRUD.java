package sportconnectfx.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import sportconnectfx.entities.Blog;
import sportconnectfx.interfaces.EntityCRUD;
import sportconnectfx.tools.MyConnection;

public class BlogCRUD implements EntityCRUD<Blog>{

    @Override
     public void addEntity(Blog e) {
        try {
            String requete ="INSERT INTO blog (titre, auteur, description, date, image, likes, dislikes) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setString(1, e.getTitre());
            pst.setString(2, e.getAuteur());
            pst.setString(3, e.getDescription());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = dateFormat.format(e.getDate());
            pst.setString(4, dateString);
            pst.setString(5, e.getImage());
            pst.setInt(6, e.getLikes());
            pst.setInt(7, e.getDislikes());

            pst.executeUpdate();
            System.out.println("Blog ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void updateEntity(Blog e) {
        try {
            String requete = "UPDATE blog SET description = ?, date = ?, image = ? WHERE titre = ? AND auteur = ?";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setString(1, e.getDescription());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = dateFormat.format(e.getDate());
            pst.setString(2, dateString);
            pst.setString(3, e.getImage());
            pst.setString(4, e.getTitre());
            pst.setString(5, e.getAuteur());

            pst.executeUpdate();
            System.out.println("Blog mis à jour");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    public void deleteEntity(Blog e) {
        try {
            String requete ="DELETE FROM blog WHERE titre = ? AND auteur = ?";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setString(1, e.getTitre());
            pst.setString(2, e.getAuteur());
            pst.executeUpdate();
            System.out.println("Blog supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Blog> displayEntities() {
        List<Blog> blogs = new ArrayList<>();
        try {
            String requete ="SELECT * FROM blog";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                Blog b = new Blog();
                b.setId(rs.getInt("id"));
                b.setTitre(rs.getString("titre"));
                b.setAuteur(rs.getString("auteur"));
                b.setDescription(rs.getString("description"));
                b.setDate(rs.getDate("date"));
                b.setImage(rs.getString("image"));
                blogs.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return blogs;
    }

}
