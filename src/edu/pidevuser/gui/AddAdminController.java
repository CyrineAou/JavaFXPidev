/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.pidevuser.gui;

import edu.pidevuser.entities.Admin;
import edu.pidevuser.entities.User;
import edu.pidevuser.services.ProfileUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class AddAdminController implements Initializable {

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfTel;
    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField tfPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RegisterAdmin(ActionEvent event) throws SQLException, IOException {
        
        
        if (tfUsername.getText().length()==0 || tfEmail.getText().length()==0|| tfPrenom.getText().length()==0
                || tfNom.getText().length()==0|| tfPassword.getText().length()==0 || tfTel.getText().length()==0  ){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Test Connection");
		alert.setHeaderText("Results:");
		alert.setContentText("champs vide");

		alert.showAndWait();
    }else{
        Admin a = new Admin(tfUsername.getText(),tfEmail.getText(),Integer.parseInt(tfTel.getText()),tfPrenom.getText(),tfNom.getText(),tfPassword.getText());
        ProfileUser pu = new ProfileUser();
        
        pu.Register(a);     
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Test Connection");
		alert.setHeaderText("Results:");
		alert.setContentText("Votre profil a été bien enregistré!");

		alert.showAndWait();
    
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/HomeAdmin.fxml"));
       
        Parent root = loader.load();
        
        tfEmail.getScene().setRoot(root);}
        
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/HomeAdmin.fxml"));
       
        Parent root = loader.load();
        
        tfUsername.getScene().setRoot(root);
    }
    
}
