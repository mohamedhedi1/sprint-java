/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import entities.utilisateur;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;
import utils.DataBase;

/**
 *
 * @author PC
 */
public class User {

    Connection cnx = DataBase.getInstance().getCnx();
    
    private String messageErreur; // propriété pour stocker le message d'erreur

    public String getMessageErreur() {
        return messageErreur;
    }
    public void ajouterrrr(utilisateur u) {
        try {
            String req = "INSERT INTO `admin` (`nom`, `prenom`, `username`, `email`, `password`, `telephone`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req); //statement pour les requettes statique et ps pour les dynamiques 
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setString(3, u.getUsername());
            pst.setString(4, u.getEmail());
            pst.setString(5, u.getPassword());       
            pst.setInt(6, u.getTel());    
            pst.executeUpdate();
            System.out.println("ajouté avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /*public boolean verifierSaisie(String nom, String prenom, String username, String email, String password, int tel) {
    // Vérification du champ nom
    if (nom.isEmpty()) {
        System.out.println("Veuillez saisir un nom !");
        return false;
    }
    
    // Vérification du champ prenom
    if (prenom.isEmpty()) {
        System.out.println("Veuillez saisir un prénom !");
        return false;
    }
    
    // Vérification du champ username
    if (username.isEmpty()) {
        System.out.println("Veuillez saisir un nom d'utilisateur !");
        return false;
    }
    
    // Vérification du champ email
    if (email.isEmpty() || !email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z]{2,5}")) {
        System.out.println("Veuillez saisir une adresse email valide !");
        return false;
    }
    
    // Vérification du champ password
    if (password.isEmpty()) {
        System.out.println("Veuillez saisir un mot de passe !");
        return false;
    }
    
    if (tel == 0 || tel < 0) {
        System.out.println("Veuillez saisir un numéro de téléphone valide !");
        return false;
    }
    
    return true;
}*/
    
    public boolean verifierSaisie(String nom, String prenom, String username, String email, String password, String tel) {
    // Vérification du champ nom
    if (nom.isEmpty()) {
        this.messageErreur = "Veuillez saisir un nom !";
        return false;
    }
    
    // Vérification du champ prenom
    if (prenom.isEmpty()) {
        this.messageErreur = "Veuillez saisir un prénom !";
        return false;
    }
    
    // Vérification du champ username
    if (username.isEmpty()) {
        this.messageErreur = "Veuillez saisir un nom d'utilisateur !";
        return false;
    }
    
    // Vérification du champ email
    if (email.isEmpty() || !email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z]{2,5}")) {
        this.messageErreur = "Veuillez saisir une adresse email valide !";
        return false;
    }
    
    // Vérification du champ password
    if (password.isEmpty()) {
        this.messageErreur = "Veuillez saisir un mot de passe !";
        return false;
    }
    
    // Vérification du champ tel
    if (tel.isEmpty()) {
    this.messageErreur = "Veuillez saisir un numéro de téléphone valide !";
    return false;
}

try {
    int num = Integer.parseInt(tel);
} catch (NumberFormatException e) {
    this.messageErreur = "Veuillez saisir un numéro de téléphone valide !";
    return false;
}

    
    return true;
}

    /*public void ajouter(utilisateur u) {
    if (!verifierSaisie(u.getNom(), u.getPrenom(), u.getUsername(), u.getEmail(), u.getPassword(), Integer.toString(u.getTel()))) {
        return;
    }
    
    try {
        String req = "INSERT INTO `admin` (`nom`, `prenom`, `username`, `email`, `password`, `telephone`) VALUES (?,?,?,?,?,?)";
        PreparedStatement pst = cnx.prepareStatement(req); //statement pour les requettes statique et ps pour les dynamiques 
        pst.setString(1, u.getNom());
        pst.setString(2, u.getPrenom());
        pst.setString(3, u.getUsername());
        pst.setString(4, u.getEmail());
        pst.setString(5, u.getPassword());       
        pst.setInt(6, u.getTel());    
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Utilisateur ajouté avec succès !");
        System.out.println("ajouté avec succes");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de l'utilisateur : " + ex.getMessage());
        System.out.println(ex.getMessage());
    }
}*/
    
    public void ajouter(utilisateur u) {
    if (!verifierSaisie(u.getNom(), u.getPrenom(), u.getUsername(), u.getEmail(), u.getPassword(), Integer.toString(u.getTel()))) {
        return;
    }

    try {
        String email = u.getEmail();
        String req = "SELECT COUNT(*) FROM admin WHERE email = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        if (count > 0) {
            JOptionPane.showMessageDialog(null, "L'email '" + email + "' est déjà utilisé par un autre utilisateur.");
            return;
        }

        req = "INSERT INTO admin (nom, prenom, username, email, password, telephone) VALUES (?, ?, ?, ?, ?, ?)";
        pst = cnx.prepareStatement(req);
        pst.setString(1, u.getNom());
        pst.setString(2, u.getPrenom());
        pst.setString(3, u.getUsername());
        pst.setString(4, email);
        pst.setString(5, u.getPassword());
        pst.setInt(6, u.getTel());
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Utilisateur ajouté avec succès !");
        System.out.println("ajouté avec succès");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de l'utilisateur : " + ex.getMessage());
        System.out.println(ex.getMessage());
    }
}


    
    public List<utilisateur> afficher() {
        List<utilisateur> list = new ArrayList<>();
        try {
            String req = "Select * from admin";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                utilisateur u = new utilisateur();
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setTel(rs.getInt("telephone"));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `admin` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("utilisateur supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    public void modifierU(utilisateur u) {
    try {
        String req = "UPDATE `admin` SET `nom` = '" + u.getNom() 
                + "', `prenom` = '" + u.getPrenom() 
                + "', `username` = '" + u.getUsername()
                + "', `email` = '" + u.getEmail()
                + "', `password` = '" + u.getPassword()
                + "', `telephone` = '" + u.getTel()
                + "' WHERE `admin`.`id` = " + u.getId();
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.executeUpdate(req);
        System.out.println("utilisateur modifié !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        System.out.println("Impossible de modifier l'utilisateur " + u.getNom());
    }
}

public boolean modifier(utilisateur u) {
    boolean modifie = false;
    if (!verifierSaisie(u.getNom(), u.getPrenom(), u.getUsername(), u.getEmail(), u.getPassword(), Integer.toString(u.getTel()))) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(messageErreur);
        alert.showAndWait();
        
    }
    try {
        String req = "UPDATE admin SET nom=?, prenom=?, username=?, email=?, password=?, telephone=? WHERE id=?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, u.getNom());
        pst.setString(2, u.getPrenom());
        pst.setString(3, u.getUsername());
        pst.setString(4, u.getEmail());
        pst.setString(5, u.getPassword());
        pst.setInt(6, u.getTel());
        pst.setInt(7, u.getId());
        int result = pst.executeUpdate();
        if(result > 0) {
            System.out.println("utilisateur modifié !");
        } else {
            System.out.println("Impossible de modifier l'utilisateur !");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return modifie;
}


    public ObservableList<utilisateur> getAll() {
        ObservableList<utilisateur> user = FXCollections.observableArrayList();
try {
    
            String req = "Select * from admin";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                utilisateur u = new utilisateur();
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setTel(rs.getInt("telephone"));
                user.add(u);
            }
            System.out.println("user");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return user;    
    }
   
}
