package event.tests;


import event.entites.Activites;
import event.entites.Categorie;
import event.services.ActivitesCrud;
import event.services.CatCrud;
import java.util.List;



public class ActivitesTest {
    public static void main(String[] args) {
        
        
              ActivitesCrud activitesCrud = new ActivitesCrud();
        CatCrud catCrud = new CatCrud();

        // Test de la méthode addEntity()
//        Activites a1 = new Activites(catCrud.displayEntities().get(1), "Activité 1", "Description de l'activité 10", "rades", "image2.jpg");
//        activitesCrud.addEntity(a1);

      //ActivitesCrud activitesCrud = new ActivitesCrud();
List<Activites> activites = activitesCrud.displayEntities();
for (Activites a : activites) {
    System.out.println("Id: " + a.getId());
    System.out.println("Titre: " + a.getTitre());
    System.out.println("Description: " + a.getDescription());
    System.out.println("Image: " + a.getImage());
    System.out.println("Images: " + a.getImages());
    System.out.println("Catégorie: " + a.getCategorie().getNom());
    System.out.println("-------------------------");
}




    // créer un objet ActivitesCrud
//    ActivitesCrud activitesCrud = new ActivitesCrud();
//
//    // afficher les activités avant la suppression
//    System.out.println("Activités avant la suppression :");
//    List<Activites> activitesList = activitesCrud.displayEntities();
//    activitesList.forEach(System.out::println);
//
//    // supprimer une activité par ID
//    int id = 1;
//    activitesCrud.deleteEntity(id);
//
//    // afficher les activités après la suppression
//    System.out.println("Activités après la suppression :");
//    activitesList = activitesCrud.displayEntities();
//    activitesList.forEach(System.out::println);
}
//
// 
    }



