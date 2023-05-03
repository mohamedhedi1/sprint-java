package event.tests;

import event.tools.MyConnection;

import event.entites.Categorie;
import event.services.CatCrud;
import java.util.List;


public class MainClass {
    public static void main(String[] args) {
        MyConnection mc = new MyConnection();
        mc.getCnx(); // pour vérifier que la connexion est établie
        Categorie e = new Categorie("malek", "sayda");
        CatCrud eventCrud = new CatCrud();
        CatCrud catCrud = new CatCrud();
        eventCrud.addEntity(e);
        eventCrud.deleteEntity(e);
          List<Categorie> categories = catCrud.displayEntities();
    for (Categorie cat : categories) {
        System.out.println(cat.toString());
    }
   
        }
    
    
    
}
