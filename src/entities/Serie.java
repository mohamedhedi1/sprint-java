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
public class Serie {
    private int id;
    private String titreSerie;
    private String imSerie;

    public Serie(int id, String titreSerie, String imSerie, String imageSerie) {
        this.id = id;
        this.titreSerie = titreSerie;
        this.imSerie = imSerie;
        this.imageSerie = imageSerie;
    }

    public String getImSerie() {
        return imSerie;
    }

    public void setImSerie(String imSerie) {
        this.imSerie = imSerie;
    }
    private String imageSerie;

    public Serie(int id, String titreSerie, String imageSerie) {
        this.id = id;
        this.titreSerie = titreSerie;
        this.imageSerie = imageSerie;
    }

    public Serie() {
    }

    public Serie(String titreSerie, String imageSerie) {
        this.titreSerie = titreSerie;
        this.imageSerie = imageSerie;
    }

    @Override
    public String toString() {
        return "Serie{" + "id=" + id + ", titreSerie=" + titreSerie + ", imageSerie=" + imageSerie + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitreSerie() {
        return titreSerie;
    }

    public void setTitreSerie(String titreSerie) {
        this.titreSerie = titreSerie;
    }

    public String getImageSerie() {
        return imageSerie;
    }

    public void setImageSerie(String imageSerie) {
        this.imageSerie = imageSerie;
    }
    
    
}
