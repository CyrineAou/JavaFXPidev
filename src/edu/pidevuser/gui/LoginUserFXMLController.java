/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.pidevuser.gui;

import edu.pidevuser.entities.User;
import edu.pidevuser.services.ProfileUser;
import edu.pidevuser.utils.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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
public class LoginUserFXMLController implements Initializable {

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMotdepasse;

    private User user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    Connection cnx = DataSource.getInstance().getCnx();
    
    @FXML
    private void Login(ActionEvent event) throws SQLException, IOException {
        
        
        
        
        
        
            
        /*if(u.getEmail()==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Test Connection");
		alert.setHeaderText("Results:");
		alert.setContentText(""remplir tout les champs"");

		alert.showAndWait();
        } else */
                ProfileUser pu = new ProfileUser();
                User u = pu.login(tfEmail.getText(), tfMotdepasse.getText());
            
            if (tfEmail.getText().length() == 0 || tfMotdepasse.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Test Connection");
		alert.setHeaderText("Results:");
		alert.setContentText("remplir tout les champs");
		alert.showAndWait();
            } else if(u.getEmail()==null || u.getPassword()== null) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Test Connection");
		alert.setHeaderText("Results:");
		alert.setContentText("Email ou mot de passe invalid");

		alert.showAndWait();
            } else if( pu.check_banne(tfEmail.getText())==true){
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Test Connection");
		alert.setHeaderText("Results:");
		alert.setContentText("Banne");

		alert.showAndWait();
            
            }
            else {
                
                if (pu.isAdmin(tfEmail.getText(), tfMotdepasse.getText())==false  ) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileUser.fxml"));
                Parent root = loader.load();
                tfEmail.getScene().setRoot(root);

                ProfileUserController puc = loader.getController();
                puc.AfficherUser(String.valueOf(u.getId()));
                puc.setUser(u);
                
            }else if ( pu.isAdmin(tfEmail.getText(), tfMotdepasse.getText())==true ) {
               
            
                FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeAdmin.fxml"));
                Parent root = loader.load();
                tfEmail.getScene().setRoot(root);

            }/*else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Test Connection");
		alert.setHeaderText("Results:");
		alert.setContentText("Banne");

		alert.showAndWait();
            }*/
        
        
            }
        
    }

    @FXML
    private void Redirection(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RegisterUserFXML.fxml"));
       
        Parent root = loader.load();
        
        tfEmail.getScene().setRoot(root);
        
        ProfileUserController puc = new ProfileUserController();
        
        
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @FXML
    private void ResetPass(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ResetPass.fxml"));
       
        Parent root = loader.load();
        
        tfEmail.getScene().setRoot(root);
    }
    

}
