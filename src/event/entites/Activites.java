package event.entites;

import java.util.ArrayList;
import java.util.List;

public class Activites {
    
    private int id ;
    private Categorie categorie;
    private String titre;
    private String description;
    private String image;
    private String images;

    
    private static List<Activites> activitesList = new ArrayList<>();

    public Activites(int id, Categorie categorie, String titre, String description, String image, String images) {
        this.id = id;
        this.categorie = categorie;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.images = images;
        activitesList.add(this);
    }

    public Activites(Categorie categorie, String titre, String description, String image, String images) {
        this.categorie = categorie;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.images = images;
        activitesList.add(this);
    }
    
    

    public Activites() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Activites(String titre, String description, String images) {
         //To change body of generated methods, choose Tools | Templates.
    }

    public Activites(int id, String titre) {
        
          this.id = id;
          this.titre = titre;
    }

    Activites(String activity_1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
    
    public String getNomCategorie() {
        return categorie.getNom();
    }

    @Override
    public String toString() {      
        return titre;
    }

        public static Activites get(int id) {
    for (Activites activity : activitesList) {
        if (activity.getId() == id) {
            return activity;
        }
    }
    return null;
}

}
