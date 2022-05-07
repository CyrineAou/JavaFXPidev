package projet.tools;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendWhatsapp {
    




   // Replace these placeholders with your Account Sid and Auth Token
   public static final String ACCOUNT_SID = "ACa7cb8cb8295a4f53968272becfc0c706";
   public static final String AUTH_TOKEN = "919a0bddba5cb4e5ccde73d0297813dd";

   public static void main(String[] args) {
       Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
       Message message = Message.creator(
               new com.twilio.type.PhoneNumber("whatsapp:+21629284613"),
               new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
               "Hello from your friendly neighborhood Java application!")
           .create();
            System.out.println(message.getSid());
       
   }
}