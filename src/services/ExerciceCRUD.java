/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Exercice;
import interfaces.EntityCRUD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author Mohamed
 */
public class ExerciceCRUD implements EntityCRUD<Exercice> {

    @Override
    public void ajouter(Exercice t) {
         try {
            String requete = "INSERT INTO exercice ( equipement_id ,nom_exercice , image_exercice , duration , repetation , instruction) "
                    + "VALUES ("+  t.getEquipementId()
                    + ", '"+t.getNomExercice()+"','"+t.getImageExercice()+"' ,"+t.getDuration()+","+t.getRepetation()+", '"+t.getInstruction()+"'"+")";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("Exercice ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Exercice> afficher() throws SQLException {
     ObservableList<Exercice> ls = FXCollections.observableArrayList();
    Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
    String req = "select * from exercice";
    ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Exercice p = new  Exercice();
                p.setId(rs.getInt(1));
                p.setEquipementId(rs.getInt("equipement_id"));
                p.setNomExercice(rs.getString("nom_exercice"));
                p.setImageExercice("C:\\xampp\\htdocs\\"+rs.getString("image_exercice"));
                
                 p.setDuration(rs.getInt("duration"));
                  p.setRepetation(rs.getInt("repetation"));
                   p.setInstruction(rs.getString("instruction"));
                ls.add(p);
            
        } 
        return ls;  
    }

    @Override
    public void supprimer(int id) throws SQLException {
          Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
        String query = "delete from exercice where id  =" + id ;
        st.executeUpdate(query);
    }

    @Override
    public void modifier(Exercice t) throws SQLException {
    Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
        String query = "update exercice set  `equipement_id`="+t.getEquipementId()+", `nom_exercice`='"+t.getNomExercice()+"', `image_exercice`='"+t.getImageExercice()+"' , `duration`="+t.getDuration()+" , `repetation`="+t.getRepetation()+" , `instruction`='"+t.getInstruction()+"' where id= "+t.getId();
        
        st.executeUpdate(query);   
    }
    
    
    public List<Exercice> getList() throws SQLException {
        List<Exercice> myList = new ArrayList<>();
         try {
            String requete = "SELECT * FROM exercice";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Exercice p = new  Exercice();
                p.setId(rs.getInt(1));
                p.setNomExercice(rs.getString("nom_exercice"));
                p.setImageExercice("C:/xampp/htdocs/"+rs.getString("image_exercice"));
                p.setDuration(rs.getInt("duration"));
                p.setRepetation(rs.getInt("repetation"));
                p.setInstruction(rs.getString("instruction"));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
        
    }
       
    }
    
       
    

