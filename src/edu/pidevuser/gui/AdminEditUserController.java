/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.pidevuser.gui;

import edu.pidevuser.entities.User;
import edu.pidevuser.services.ProfileAdmin;
import edu.pidevuser.services.ProfileUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class AdminEditUserController implements Initializable {


    private User user;
    @FXML
    private CheckBox rbBanne;
    @FXML
    private CheckBox rdDebanne;
    @FXML
    private Label lblUsername;
    /**
     * Initializes the controller class.
     */
    
    String id;;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*ProfileUser pu = new ProfileUser();
        
        User u = pu.getUser(id);
        if(u.getBanned()==true){
        rbBanne.isSelected();
        }
        else 
            rdDebanne.isSelected();*/
        
           }    

    public void AfficherUser(String id){
    
        ProfileUser pu = new ProfileUser();
        
        User u = pu.getUser(id);
        lblUsername.setText(u.getUsername());
        
        
        if(u.getBanned()==true){
        rbBanne.setSelected(true);
        }
        
        
    }
    
    
    @FXML
    private void Modifier(ActionEvent event) throws IOException {
        ProfileAdmin pa = new ProfileAdmin();

        //user.setBanned(rbBanne.setSelected(true));
        if(rbBanne.isSelected()){
        pa.BanneUser(user);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Test Connection");
		alert.setHeaderText("Results:");
		alert.setContentText("Banne!");

		alert.showAndWait();
        }else if(rdDebanne.isSelected()){
            
            pa.DebanneUser(user);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Test Connection");
		alert.setHeaderText("Results:");
		alert.setContentText("Debanne!");

		alert.showAndWait();
        }
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeAdmin.fxml"));
        Parent root = loader.load();
        lblUsername.getScene().setRoot(root);
       
    }
    
    
 
    
    
    public void setUser(User u) {
        this.user= u; 
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/HomeAdmin.fxml"));
       
        Parent root = loader.load();
        
        lblUsername.getScene().setRoot(root);
    }

    @FXML
    private void banne(ActionEvent event) {
        
    }

    @FXML
    private void debanne(ActionEvent event) {
    }
    
}
