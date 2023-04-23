/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import javax.mail.MessagingException;
import services.MailSender;

/**
 *
 * @author Mohamed
 */
public class Testmail {
    public static void main(String[] args) {
    System.out.println("oui");
    try {
    MailSender.sendEmail("mohamedelhedi.hamdi@esprit.tn", "Sujet de l'email", "Texte de l'email");
} catch (MessagingException e) {
    e.printStackTrace();
}
    }
    
}
