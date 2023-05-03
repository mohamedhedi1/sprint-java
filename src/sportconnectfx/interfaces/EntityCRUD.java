/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportconnectfx.interfaces;

import java.util.List;

/**
 *
 * @author manar
 */
public interface EntityCRUD<T> {
    public void addEntity(T t);
    public List<T> displayEntities();
    
}
