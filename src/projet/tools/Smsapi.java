
package projet.tools;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.util.Arrays;

public class Smsapi {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = System.getenv("AC03ecff0748671a798d05d9b5c89040a3");
    public static final String AUTH_TOKEN = System.getenv("a15b228e86a0346951c06e8d38527be0");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21656100840"), //to
                new com.twilio.type.PhoneNumber("+19704648109"), // from
                "Reservation Ajoutée avec sucess!")
            .setMediaUrl(
                Arrays.asList(URI.create("https://demo.twilio.com/owl.png")))
            .create();

        System.out.println(message.getSid());
    }
}