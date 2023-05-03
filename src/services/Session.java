/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class Session {
    private static Session instance;
    private String email;
    private boolean loggedIn;

    public Session() {
        loggedIn = false;
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    
    public boolean verifierUtilisateur(String email, String password) {
    User us=new User();
        try {
        String req = "SELECT COUNT(*) FROM admin WHERE email = ? AND password = ?";
        PreparedStatement pst = us.cnx.prepareStatement(req);
        pst.setString(1, email);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        if (count > 0) {
            return true;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return false;
}
    public boolean verifierClient(String email, String password) {
    User us=new User();
        try {
        String req = "SELECT COUNT(*) FROM client WHERE email = ? AND password = ?";
        PreparedStatement pst = us.cnx.prepareStatement(req);
        pst.setString(1, email);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        if (count > 0) {
            return true;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return false;
}

    public void logout() {
    email = null;
    loggedIn = false;
}

}

