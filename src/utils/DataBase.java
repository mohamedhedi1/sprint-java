/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class DataBase {
    public String url="jdbc:mysqL://localhost:3306/utilisateurs";
    public String login="root";
    public String pwd="";
    
    Connection cnx;
    private static DataBase instance;
    
    private  DataBase() 
    {
        try {
            cnx=DriverManager.getConnection(url,login,pwd);
            System.out.println("Connection réussie !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public Connection getCnx() {
        return cnx;
    }
    
    public static DataBase getInstance() {
        if(instance == null)
        {
            instance = new DataBase();
        }
        return instance;
    }
}
