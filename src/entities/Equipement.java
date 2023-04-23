/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Mohamed
 */
public class Equipement {
    private int id;
    private String nomEquipement;
    private String imageEquipement;

    public Equipement() {
    }

    public Equipement(int id, String nomEquipement, String imageEquipement) {
        this.id = id;
        this.nomEquipement = nomEquipement;
        this.imageEquipement = imageEquipement;
    }

    public Equipement(String nomEquipement, String imageEquipement) {
        this.nomEquipement = nomEquipement;
        this.imageEquipement = imageEquipement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEquipement() {
        return nomEquipement;
    }

    public void setNomEquipement(String nomEquipement) {
        this.nomEquipement = nomEquipement;
    }

    public String getImageEquipement() {
        return imageEquipement;
    }

    public void setImageEquipement(String imageEquipement) {
        this.imageEquipement = imageEquipement;
    }

    @Override
    public String toString() {
        return "Equipement{" + "id=" + id + ", nomEquipement=" + nomEquipement + ", imageEquipement=" + imageEquipement + '}';
    }
    
    
    
}
