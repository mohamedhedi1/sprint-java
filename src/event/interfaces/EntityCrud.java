package event.interfaces;

import java.util.List;

public interface EntityCrud<T> {

   
    
    void addEntity(T t);

    List<T> displayEntities();
    
    

}
