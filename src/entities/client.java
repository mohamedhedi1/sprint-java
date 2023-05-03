/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.File;
import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class client {
    private int id;
    private String telephone;
    private String nom;
    private String prenom;
    private String username;
    private String email;
    private String password;
    private LocalDate date_naissance;
   // private  photo;
    private File photo;


    public client() {
    }

    public client(String telephone, String nom, String prenom, String username, String email, String password, LocalDate date_naissance, File photo) {
        this.telephone = telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.date_naissance = date_naissance;
        this.photo = photo;
    }

    public client(int id, String telephone, String nom, String prenom, String username, String email, String password, LocalDate date_naissance, File photo) {
        this.id = id;
        this.telephone = telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.date_naissance = date_naissance;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public File getPhoto() {
        return photo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "client{" + "id=" + id + ", telephone=" + telephone + ", nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", email=" + email + ", password=" + password + ", date_naissance=" + date_naissance + ", photo=" + photo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
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
        final client other = (client) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
    
}
