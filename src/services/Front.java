/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.client;
import entities.utilisateur;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import utils.DataBase;

/**
 *
 * @author PC
 */
public class Front {
    
    Connection cnx = DataBase.getInstance().getCnx();
    
    private String messageErreur; // propriété pour stocker le message d'erreur

    public String getMessageErreur() {
        return messageErreur;
    }
    
    public List<client> afficher() {
    List<client> list = new ArrayList<>();
    try {
        String req = "SELECT * FROM client";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            client c = new client();
            c.setId(rs.getInt("id"));
            c.setNom(rs.getString("nom"));
            c.setPrenom(rs.getString("prenom"));
            c.setUsername(rs.getString("username"));
            c.setEmail(rs.getString("email"));
            c.setPassword(rs.getString("password"));
            c.setTelephone(rs.getString("telephone"));
           // c.setPhoto(new File(rs.getString("photo")));
           
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date_naissance = LocalDate.parse(rs.getString("date_naissance"), formatter);
            c.setDate_naissance(date_naissance);
            list.add(c);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

    return list;
}

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
    
    public boolean verifierSaisie2(String nom, String prenom, String username, String email, String password,  LocalDate date_naissance, File photo) {
    try {
        boolean saisieValide = true;
        StringBuilder messageErreur = new StringBuilder();
        
       /* if (nom.isEmpty() || prenom.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || tel.isEmpty() || dateNaissance == null || photo == null) {
            saisieValide = false;
            messageErreur.append("Tous les champs obligatoires doivent être remplis !");
        }*/
        
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
    if (email.isEmpty()) {
        this.messageErreur = "Veuillez saisir un email !";
        return false;
    }
    
    // Vérification du champ mdp
    if (password.isEmpty()) {
        this.messageErreur = "Veuillez saisir un mdp !";
        return false;
    }
    
    /*if (tel.isEmpty()) {
    this.messageErreur = "Veuillez saisir un numero !";
    return false;
    }try {
    Integer.parseInt(tel);
    } catch (NumberFormatException e) {
    this.messageErreur = "Le numéro de téléphone doit être un nombre !";
    return false;
    }*/
    
    // Vérifier que la date de naissance est valide
        if (date_naissance==null) {
        this.messageErreur = "Veuillez saisir une date !";
        return false;
    }else{
        LocalDate now = LocalDate.now();
        if (date_naissance.isAfter(now.minusYears(5))) {
            saisieValide = false;
            messageErreur.append("Vous devez avoir au moins 5 ans pour vous inscrire !");
        }
        }
        
        // Vérifier que l'email n'est pas déjà utilisé par un autre client
        String checkEmailReq = "SELECT COUNT(*) FROM client WHERE email = ?";
        PreparedStatement checkEmailPst = cnx.prepareStatement(checkEmailReq);
        checkEmailPst.setString(1, email);
        ResultSet checkEmailRs = checkEmailPst.executeQuery();
        checkEmailRs.next();
        int emailCount = checkEmailRs.getInt(1);
        if (emailCount > 0) {
            saisieValide = false;
            messageErreur.append("L'email est déjà utilisé par un autre client !");
        }
        
        // Vérifier que le nom d'utilisateur n'est pas déjà utilisé par un autre client
        String checkUsernameReq = "SELECT COUNT(*) FROM client WHERE username = ?";
        PreparedStatement checkUsernamePst = cnx.prepareStatement(checkUsernameReq);
        checkUsernamePst.setString(1, username);
        ResultSet checkUsernameRs = checkUsernamePst.executeQuery();
        checkUsernameRs.next();
        int usernameCount = checkUsernameRs.getInt(1);
        if (usernameCount > 0) {
            saisieValide = false;
            messageErreur.append("Le nom d'utilisateur est déjà utilisé par un autre client !");
        }
        
        
        
        /*/ Vérifier que le fichier photo est une image
        try {
            ImageIO.read(photo);
        }catch (IOException ex) {
            Logger.getLogger(Front.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        if (!saisieValide) {
            messageErreur.append("\nVeuillez corriger votre saisie.");
            this.messageErreur = messageErreur.toString();
        }
        
        return saisieValide;
    } catch (SQLException ex) {
            Logger.getLogger(Front.class.getName()).log(Level.SEVERE, null, ex);
    }
        return false;
}

    
    public boolean inscription(String nom, String prenom, String username, String email, String password, String telephone, LocalDate dateNaissance, File photo)
    
    {
    boolean success = false;
    try {
        // Vérifier que tous les champs obligatoires sont remplis
        if (nom.isEmpty() || prenom.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || telephone.isEmpty() || dateNaissance == null) {
            System.err.println("Tous les champs obligatoires doivent être remplis !");
            return success;
        }
        
        // Vérifier que l'email n'est pas déjà utilisé par un autre client
        String checkEmailReq = "SELECT COUNT(*) FROM client WHERE email = ?";
        PreparedStatement checkEmailPst = cnx.prepareStatement(checkEmailReq);
        checkEmailPst.setString(1, email);
        ResultSet checkEmailRs = checkEmailPst.executeQuery();
        checkEmailRs.next();
        int emailCount = checkEmailRs.getInt(1);
        if (emailCount > 0) {
            System.err.println("L'email est déjà utilisé par un autre client !");
            return success;
        }
        
        // Vérifier que le nom d'utilisateur n'est pas déjà utilisé par un autre client
        String checkUsernameReq = "SELECT COUNT(*) FROM client WHERE username = ?";
        PreparedStatement checkUsernamePst = cnx.prepareStatement(checkUsernameReq);
        checkUsernamePst.setString(1, username);
        ResultSet checkUsernameRs = checkUsernamePst.executeQuery();
        checkUsernameRs.next();
        int usernameCount = checkUsernameRs.getInt(1);
        if (usernameCount > 0) {
            System.err.println("Le nom d'utilisateur est déjà utilisé par un autre client !");
            return success;
        }
        
        // Convertir la photo en tableau de bytes pour l'enregistrer dans la base de données
        byte[] photoBytes = null;
        if (photo != null) {
            FileInputStream fis = new FileInputStream(photo);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int readNum; (readNum = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, readNum);
            }
            photoBytes = bos.toByteArray();
        }
        
        // Insérer les données du client dans la base de données
        String insertReq = "INSERT INTO client (nom, prenom, username, email, password, telephone, date_naissance, photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertPst = cnx.prepareStatement(insertReq);
        insertPst.setString(1, nom);
        insertPst.setString(2, prenom);
        insertPst.setString(3, username);
        insertPst.setString(4, email);
        insertPst.setString(5, password);
        insertPst.setString(6, telephone);
        insertPst.setString(7, dateNaissance.toString());
        if (photoBytes != null) {
            insertPst.setBytes(8, photoBytes);
        } else {
            insertPst.setNull(8, java.sql.Types.BLOB);
        }
        int rowsInserted = insertPst.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Client inséré avec succès !");
            success = true;
        }
    } catch (SQLException | FileNotFoundException ex) {
        System.err.println(ex.getMessage());
    } finally {
        return success;
    }
}
    
    

    public ObservableList<client> getAll() {
    ObservableList<client> client = FXCollections.observableArrayList();
    try {
        String req = "SELECT * FROM client";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            client c = new client();
            c.setId(rs.getInt("id"));
            c.setNom(rs.getString("nom"));
            c.setPrenom(rs.getString("prenom"));
            c.setUsername(rs.getString("username"));
            c.setEmail(rs.getString("email"));
            c.setPassword(rs.getString("password"));
            c.setTelephone(rs.getString("telephone"));
            c.setDate_naissance(LocalDate.parse(rs.getString("date_naissance")));

           /* // Récupérer la photo depuis la base de données
            byte[] photoBytes = rs.getBytes("photo");
            if (photoBytes != null) {
                // Convertir le tableau de bytes en objet File
                File photo = File.createTempFile("photo", ".jpg");
                FileOutputStream fos = new FileOutputStream(photo);
                fos.write(photoBytes);
                fos.close();
                c.setPhoto(photo);
            }*/

            client.add(c);
        }
        System.out.println("Clients récupérés avec succès !");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

    return client;
}
    
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `client` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("client supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    /*public void modifierMdp(client c) {
    try {
    String req = "UPDATE `client` SET `email` = '" + c.getEmail()
    + "', `password` = '" + c.getPassword();
    PreparedStatement pst = cnx.prepareStatement(req);
    pst.executeUpdate(req);
    System.out.println("mot de passe modifié !");
    } catch (SQLException ex) {
    System.out.println(ex.getMessage());
    System.out.println("Impossible de modifier le mot de passe " + c.getEmail());
    }
    }*/

    public boolean modifierMdp(client c) {
    boolean modifie = false;
    try {
        String req = "UPDATE client SET password=? WHERE email=?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, c.getPassword());
        pst.setString(2, c.getEmail());
        int result = pst.executeUpdate();
        if(result > 0) {
            System.out.println("Mot de passe modifié !");
            modifie = true;
        } else {
            System.out.println("Impossible de modifier le mot de passe !");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return modifie;
}

}
