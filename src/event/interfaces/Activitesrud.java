/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.interfaces;

import java.util.List;

/**
 *
 * @author Dell
 */
public interface Activitesrud<T> {
    
    void addEntity(T t);
    void deleteEntity(int id);


    List<T> displayEntities();
    
}
