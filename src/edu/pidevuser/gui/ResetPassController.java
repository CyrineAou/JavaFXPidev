/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.pidevuser.gui;

import edu.pidevuser.utils.Mail;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class ResetPassController implements Initializable {

    @FXML
    private TextField tfemail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Confirmer(ActionEvent event) throws Exception {
        Mail mail= new Mail();
        mail.sendMail(tfemail.getText());
        
                Notifications notif = Notifications.create()
                    
                    .title("Mot de passe oublieé")
                    .text("Message a envoyé ")
                            .text("Verifier votre boite email")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("Message a envoyé ");
                        }
                    });
            notif.darkStyle();
            notif.show();
            
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/LoginUserFXML.fxml"));
       
        Parent root = loader.load();
        
        tfemail.getScene().setRoot(root);
    }

    @FXML
    private void Return(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/LoginUserFXML.fxml"));
       
        Parent root = loader.load();
        
        tfemail.getScene().setRoot(root);
    }
    
}
