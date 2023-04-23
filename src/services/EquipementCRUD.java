/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Equipement;
import interfaces.EntityCRUD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author Mohamed
 */
public class EquipementCRUD implements EntityCRUD<Equipement> {

    @Override
    public void ajouter(Equipement t) {
       try {
            String requete = "INSERT INTO equipement (nom_equipement,image_equipement) "
                    + "VALUES ("
                    + "'"+t.getNomEquipement()+"','"+t.getImageEquipement()+"')";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("Equipement ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } }

  /*  @Override
    public List<Equipement> afficher() throws SQLException {
         List<Equipement> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM equipement";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Equipement p = new  Equipement();
                p.setId(rs.getInt(1));
                p.setNomEquipement(rs.getString("nom_equipement"));
                p.setImageEquipement(rs.getString("image_equipement"));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }*/

     @Override
    public ObservableList<Equipement> afficher() throws SQLException{
    ObservableList<Equipement> ls = FXCollections.observableArrayList();
    Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
    String req = "select * from equipement";
    ResultSet rs = st.executeQuery(req);
     
   while(rs.next()){
                Equipement p = new  Equipement();
                p.setId(rs.getInt(1));
                p.setNomEquipement(rs.getString("nom_equipement"));
                p.setImageEquipement("C:\\xampp\\htdocs\\"+rs.getString("image_equipement"));
                ls.add(p);
            }
    
    return ls;

    }
    
    @Override
    public void supprimer(int id) throws SQLException {
         Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
        String query = "delete from equipement where id  =" + id ;
        st.executeUpdate(query);
        }

    @Override
    public void modifier(Equipement t) throws SQLException {
      Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
        String query = "update equipement set `nom_equipement`='"+t.getNomEquipement()+"', `image_equipement`='"+t.getImageEquipement()+"' where id= "+t.getId();
        
        st.executeUpdate(query);
    }
    
}
