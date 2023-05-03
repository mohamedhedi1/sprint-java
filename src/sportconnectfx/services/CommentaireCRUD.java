 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportconnectfx.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import sportconnectfx.entities.Blog;
import sportconnectfx.entities.Commentaire;
import sportconnectfx.interfaces.CommentaireInterface;
import sportconnectfx.tools.MyConnection;



/**
 *
 * @author manar
 */
public class CommentaireCRUD  implements CommentaireInterface<Commentaire> {
    
    
    @Override
    public void addCommentaire(Commentaire t) {
        try {
            String requete ="INSERT INTO commentaire (pseudo, contenu, idblog_id) VALUES (?, ?, ?)";
            
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setString(1, t.getPseudo());
            pst.setString(2, t.getContenu());
            pst.setInt(3, t.getIdblog_id().getId());
            
           
            pst.executeUpdate();
            System.out.println("commentaire ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    

   

public List<Commentaire> displayCommentaire() {
    List<Commentaire> commentaires = new ArrayList<>();
    try {
        String requete = "SELECT * FROM commentaire";
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Commentaire b = new Commentaire();
            b.setId(rs.getInt("id"));
            b.setPseudo(rs.getString("pseudo"));
            b.setContenu(rs.getString("contenu"));
            int blogId = rs.getInt("idblog_id");
            Blog blog = new Blog();
            blog.setId(blogId);
            b.setIdblog_id(blog);

            commentaires.add(b);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return commentaires;
    }
    }


    
    


        