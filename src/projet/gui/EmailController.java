/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gui;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;   
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author moura
 */
public class EmailController implements Initializable {

    private TextField emailPasswordfield;
    @FXML
    private TextField emailTofield;
    @FXML
    private TextArea emailMessagefield;
    @FXML
    private Button btnenvoyer;
    @FXML
    private Button btnHomeTransition;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
   
        
    
    

    @FXML
    private void sendmail(ActionEvent event) {
        String to = emailTofield.getText();
        String from = "iheb.tarkhani@esprit.tn"; // your email   
        String password ="barcamessi"; // your password 
        
 //configuration
        String host = "smtp.gmail.com";
        boolean sessionDebug = false;
        Properties pros = System.getProperties();
        pros.put("mail.smtp.host", "smtp.gmail.com");
        pros.put("mail.smtp.socketFactory.port", "465");
        pros.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        pros.put("mail.smtp.auth", "true");
        pros.put("mail.smtp.prot", "465");
        try {
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(pros, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("A3mel Talla App");
            msg.setText(emailMessagefield.getText());
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("sent sucess");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
        
        
        
        
 
        

    }

    @FXML
    private void HomeTransition(ActionEvent event) { 
          Stage stage = (Stage) btnHomeTransition.getScene().getWindow();
        stage.close();
    }
    


}
