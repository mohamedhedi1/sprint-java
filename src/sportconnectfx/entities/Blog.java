package sportconnectfx.entities;

import java.sql.Date;
import java.time.LocalDate;

public class Blog {
    private int id;
    private String titre;
    private String auteur;
    private String description;
    private Date date;
    private String image;
    private int likes;
    private int dislikes;

    public Blog() {
    }

    public Blog(int id, String titre, String auteur, String description, Date date, String image) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.description = description;
        this.date = date;
        this.image = image;
        this.likes = 0;
        this.dislikes = 0;
    }

    public Blog(String titre, String auteur, String description, Date date, String image) {
        this.titre = titre;
        this.auteur = auteur;
        this.description = description;
        this.date = date;
        this.image = image;
        this.likes = 0;
        this.dislikes = 0;
    }

    public Blog(String titre, String auteur, String description, Date date, String imagePath, int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public void incrementLikes() {
        this.likes++;
    }

    public void decrementLikes() {
        this.likes--;
    }

    public void incrementDislikes() {
        this.dislikes++;
    }

    public void decrementDislikes() {
        this.dislikes--;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", titre=" + titre + ", auteur=" + auteur + ", description=" + description + ", date=" + date + ", image=" + image + ", likes=" + likes + ", dislikes=" + dislikes + '}';
    }

}
