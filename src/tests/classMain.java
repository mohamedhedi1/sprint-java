/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.utilisateur;
import static gui.front.InscrireController.calculerAge;
import java.time.LocalDate;
import services.Front;
import services.User;
import utils.DataBase;

/**
 *
 * @author PC
 */
public class classMain {
    public static void main(String[] args) {
        DataBase db = DataBase.getInstance();
       // DataBase db2 = DataBase.getInstance();
        
        User us = new User();
        Front f=new Front();
       // utilisateur u2 = new utilisateur("franck","russell","franck_rf","franck@yahoo.com","java123",12345678);
       // us.supprimer(22);
        
        
        //System.out.println(db.hashCode()+" _ "+db2.hashCode());
        //System.out.println(f.afficher());
        LocalDate dateNaissance = LocalDate.of(2000, 1, 1);
        System.out.println(calculerAge(dateNaissance));

    }
}