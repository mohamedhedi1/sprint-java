/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Equipement;
import entities.client;
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
public class ClientCRUD {
    public static List<client> getList() throws SQLException{
    List<client> ls = new ArrayList();
    Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
    String req = "select  nom , prenom , email from client";
    ResultSet rs = st.executeQuery(req);
     
   while(rs.next()){
                client p = new  client();
                
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setEmail(rs.getString("email"));
                
                ls.add(p);
            }
    
    return ls;

    }
    
}
