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
public class Exercice {
    private int id;
    private int equipementId;
    private String nomExercice;
    private String imageExercice;
    private int duration;
    private int repetation;
    private String instruction;
    private String nomEquipement;

    public Exercice(int id, int equipementId, String nomExercice, String imageExercice, int duration, int repetation, String instruction, String nomEquipement) {
        this.id = id;
        this.equipementId = equipementId;
        this.nomExercice = nomExercice;
        this.imageExercice = imageExercice;
        this.duration = duration;
        this.repetation = repetation;
        this.instruction = instruction;
        this.nomEquipement = nomEquipement;
    }
    
    

    
    public Exercice() {
    }

      
    public Exercice(int id, int equipementId, String nomExercice, String imageExercice, int duration, int repetation, String instruction) {
        this.id = id;
        this.equipementId = equipementId;
        this.nomExercice = nomExercice;
        this.imageExercice = imageExercice;
        this.duration = duration;
        this.repetation = repetation;
        this.instruction = instruction;
    }

    public Exercice(int equipementId, String nomExercice, String imageExercice, int duration, int repetation, String instruction) {
        this.equipementId = equipementId;
        this.nomExercice = nomExercice;
        this.imageExercice = imageExercice;
        this.duration = duration;
        this.repetation = repetation;
        this.instruction = instruction;
    }

    public int getEquipementId() {
        return equipementId;
    }

    public void setEquipementId(int equipementId) {
        this.equipementId = equipementId;
    }
    
    
    

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomExercice() {
        return nomExercice;
    }

    public void setNomExercice(String nomExercice) {
        this.nomExercice = nomExercice;
    }

    public String getImageExercice() {
        return imageExercice;
    }

    public void setImageExercice(String imageExercice) {
        this.imageExercice = imageExercice;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRepetation() {
        return repetation;
    }

    public void setRepetation(int repetation) {
        this.repetation = repetation;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return "Exercice{" + "id=" + id + ", nomExercice=" + nomExercice + ", imageExercice=" + imageExercice + ", duration=" + duration + ", repetation=" + repetation + ", instruction=" + instruction + '}';
    }
    
    
    
    
    
}
