/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Mohamed
 */
public interface EntityCRUD<T> {
 public void ajouter(T t);
    public List<T> afficher() throws SQLException;
    public void supprimer(int id) throws SQLException;
    public void modifier( T t) throws SQLException;
    
}
