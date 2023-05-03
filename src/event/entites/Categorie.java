/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.entites;

/**
 *
 * @author Dell
 */
public class Categorie {
    
    private int id ;
    private String nom;
    private String description;

    public Categorie(String nom) {
        this.nom = nom;
    }

    public Categorie(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public Categorie(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public Categorie(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
    

    public Categorie() {
        }

    public Categorie(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return  nom  ;
    }

    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getNomCategorie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
   
    

    
    
}
