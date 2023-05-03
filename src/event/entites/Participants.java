/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.entites;

import javafx.scene.control.TableView;

/**
 *
 * @author Dell
 */
public class Participants {
    
    private int id;
    private String name;
    private String prenom;
    private String telephone;
    private String mail;
    private float prix;

public Participants(int id, String name, String prenom, String telephone, String mail) {
    this.id = id;
    this.name = name;
    this.prenom = prenom;
    this.telephone = telephone;
    this.mail = mail;
}

public Participants(String name, String prenom, String telephone, String mail, float prix) {
    this.name = name;
    this.prenom = prenom;
    this.telephone = telephone;
    this.mail = mail;
    this.prix = prix;
}

public Participants() {
    // constructor code here
}

    


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    
    

    @Override
    public String toString() {
        return "Participants{" + "id=" + id + ", name=" + name + ", prenom=" + prenom + ", telephone=" + telephone + ", mail=" + mail + '}';
    }


    
    
}
