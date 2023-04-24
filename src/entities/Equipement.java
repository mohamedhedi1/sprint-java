/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author Mohamed
 */
public class Equipement {
    private int id;
    private String nomEquipement;
    private String imageEquipement;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.nomEquipement);
        hash = 79 * hash + Objects.hashCode(this.imageEquipement);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipement other = (Equipement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nomEquipement, other.nomEquipement)) {
            return false;
        }
        if (!Objects.equals(this.imageEquipement, other.imageEquipement)) {
            return false;
        }
        return true;
    }
    

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
