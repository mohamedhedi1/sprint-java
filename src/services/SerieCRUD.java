/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Exercice;
import entities.Serie;
import interfaces.EntityCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author Mohamed
 */
public class SerieCRUD implements EntityCRUD<Serie>{

    @Override
   public void ajouter(Serie t) {
    try {
        String requete = "INSERT INTO serie (titre_serie, image_serie) VALUES (?, ?)";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setString(1, t.getTitreSerie());
        pst.setString(2, t.getImageSerie());
        pst.executeUpdate();
        System.out.println("Série ajoutée");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


    @Override
public ObservableList<Serie> afficher() throws SQLException {
    ObservableList<Serie> ls = FXCollections.observableArrayList();
    Connection connection = MyConnection.getInstance().getCnx();
    Statement statement = connection.createStatement();
    String req = "SELECT s.id, s.titre_serie ,s.image_serie,  GROUP_CONCAT(e.nom_exercice SEPARATOR ', ') as nom_exercices FROM serie s INNER JOIN serie_exercice se ON s.id = se.serie_id INNER JOIN exercice e ON e.id = se.exercice_id GROUP BY s.id;";
    ResultSet rs = statement.executeQuery(req);
    while (rs.next()) {
        Serie serie = new Serie();
        serie.setId(rs.getInt(1));
        serie.setTitreSerie(rs.getString("titre_serie"));
        serie.setImageSerie(rs.getString("nom_exercices"));
        serie.setImSerie("C:\\xampp\\htdocs\\"+rs.getString("image_serie"));
        ls.add(serie);
    }
    rs.close();
    statement.close();
    return ls;
}

    @Override
     public void supprimer(int id) throws SQLException {
          Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
        String query = "delete from serie where id  =" + id ;
        st.executeUpdate(query);
    }

    @Override
    public void modifier(Serie t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
