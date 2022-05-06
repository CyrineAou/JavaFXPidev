/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.entities;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


 
public class SmsSender {
    public static final String ACCOUNT_SID = System.getenv("AC03ecff0748671a798d05d9b5c89040a3");
    public static final String AUTH_TOKEN = System.getenv("a15b228e86a0346951c06e8d38527be0");

   public static void sms() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+21656100840"), // to
                        new PhoneNumber("+19704648109"), // from
                        "une nouvelle Reservation a ete ajoutee")
                .create();

        System.out.println(message.getSid());
    }
}