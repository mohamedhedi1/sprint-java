/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Mohamed
 */
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailSender {

    public static void sendEmail(String to, String subject, String text) throws MessagingException {

        String username = "c58ec4a75b8201";
        String password = "a9b7aa4dcd755d";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.mailtrap.io");
        props.put("mail.smtp.port", "2525");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("votre_adresse_email@mailtrap.io"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(text);

        Transport.send(message);

        System.out.println("Email envoyé avec succès!");

    }
}

