/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package projet.services;

/**
 *
 * @author AMEN
 
import javax.mail.Authenticator;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import projet.entities.Agence;

public class MailingService {

    public static void sendMail(String recepient) throws Exception {
        // TODO code application logic here
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myacount = "amen.benkhalifaaa@gmail.com";
        String password = "Nabelnapro80002007ze";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myacount, password);

            }
        });
        Message message = prepareMessage(session, myacount);
        Transport.send(message);
        System.out.println("done");
    }
// (int Id, int Phone, String Email, String Adress, int Code_p, int Num_emp) {
    private static Message prepareMessage(Session session, String myacount) {
        Agence r = new Agence (2, 25, "ulubatli@esprit.tn","darna",5,5);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myacount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("amenallah.benkhalifa@esprit.tn"));

            message.setSubject("Facture N");

            message.setText("Bonjour Reclamation");

            return message;
        } catch (MessagingException e) {

        }
        return message;
    }
}
*/