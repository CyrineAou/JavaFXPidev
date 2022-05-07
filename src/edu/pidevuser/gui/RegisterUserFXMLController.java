/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.pidevuser.gui;

import edu.pidevuser.entities.User;
import edu.pidevuser.services.ProfileUser;
import edu.pidevuser.utils.Mail;
import edu.pidevuser.utils.sendSMS;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class RegisterUserFXMLController implements Initializable {

    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMotdepasse;
    @FXML
    private TextField tfTel;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfNom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void TelCheck(String v2){
        try 
            {
            System.out.println("\nv2: " + v2); // <<<<<<<<<<<<<<<<<<< ajouter
            int a = Integer.parseInt(v2); 

            } 
            catch (NumberFormatException ex) 
            { 
            JOptionPane.showMessageDialog(null,"il faut saisir un nombre !!","Probléme de format",JOptionPane.ERROR_MESSAGE); 
            }
    
    }

    @FXML
    private void RegisterUser(ActionEvent event) throws SQLException, IOException, Exception {
        
        ProfileUser pu = new ProfileUser();
        sendSMS sms = new sendSMS();
        
        //sendSMS send = new sendSMS();
        
        //send.sendSms(Integer.parseInt(tfTel.getText()));
        
        if (tfUsername.getText().length()==0 || tfEmail.getText().length() == 0 || tfPrenom.getText().length() == 0 ||
                 tfNom.getText().length() ==  0 || tfMotdepasse.getText().length() == 0 || tfTel.getText().length() ==0){
                 
            Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Test Connection");
                        alert.setHeaderText("Results:");
                        alert.setContentText("Pas de champs vide !");

                        alert.showAndWait();
        }
        else if( pu.check_email(tfEmail.getText())==true){
            Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Test Connection");
                        alert.setHeaderText("Results:");
                        alert.setContentText("email est deja utilisé");

                        alert.showAndWait();
        }else if(pu.whenMatchesDigitsNumber_thenCorrect(tfTel.getText())== false){
        
            Alert alert = new Alert(AlertType.INFORMATION);
		//alert.setTitle("Test Connection");
		alert.setHeaderText("Results:");
		alert.setContentText("tel  non valide ");

		alert.showAndWait();
        }
        else if (tfEmail.getText().contains(".") && tfEmail.getText().contains("@") //&& tfTel.getText().matches("[0-9]")
                ){
            User u = new User(tfUsername.getText(),tfEmail.getText(),Integer.parseInt(tfTel.getText()),tfPrenom.getText(),tfNom.getText(),tfMotdepasse.getText());
                     pu.Register(u);
                     
                     
                     sms.sendSms("Welcome to WIN", tfTel.getText());
                     
            Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Test Connection");
                        alert.setHeaderText("Results:");
                        alert.setContentText("Votre profile a été enregistré");

                        alert.showAndWait();
                        
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/LoginUserFXML.fxml"));
             
                Parent root = loader.load();
        
                tfEmail.getScene().setRoot(root);
                
                //LoginUserFXMLController ruc= loader.getController();
                //ruc.setTfNom(tfNom.getText());
        }
        
       
        else {
            
            Alert alert = new Alert(AlertType.INFORMATION);
		//alert.setTitle("Test Connection");
		alert.setHeaderText("Results:");
		alert.setContentText("email non valide ");

		alert.showAndWait();
        
        } 
        
        
        
        
        
    }
    
    
    @FXML
    private void RedirectionSignIn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/LoginUserFXML.fxml"));
       
        Parent root = loader.load();
        
        tfEmail.getScene().setRoot(root);
    }

    
    
    
}
