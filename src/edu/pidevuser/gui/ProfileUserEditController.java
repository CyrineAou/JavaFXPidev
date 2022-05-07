/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.pidevuser.gui;

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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class ProfileUserEditController implements Initializable {

    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfTel;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;

    private User user;
    public int id_user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    



    @FXML
    private void Modifier(ActionEvent event) throws IOException, SQLException {
        
        ProfileUser pu = new ProfileUser();
        
        user.setUsername(tfUsername.getText());
        user.setEmail(tfEmail.getText());
        user.setNumTel(Integer.parseInt(tfTel.getText()));
        user.setFirstName( tfPrenom.getText());      
        user.setLastName(tfNom.getText()); 
        
        pu.ModifierUser(user);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Test Connection");
		alert.setHeaderText("Results:");
		alert.setContentText("Votre profile a été modifier !");

		alert.showAndWait();
    
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ProfileUser.fxml"));
       
        Parent root = loader.load();
        
        tfEmail.getScene().setRoot(root);
        
        ProfileUserController puc = loader.getController();
        puc.AfficherUser(String.valueOf(user.getId()));
        puc.setUser(user);
        
        
    }

    
    public void settfUsername(String Username) {
        this.tfUsername.setText(Username); 
    }

    public void settfNom(String Nom) {
        this.tfNom.setText(Nom); 
    }

    public void settfEmail(String Email) {
        this.tfEmail.setText(Email);
    }

    public void settfPrenom(String Prenom) {
        this.tfPrenom.setText(Prenom);
    }

    public void settfTel(int Tel) {
        this.tfTel.setText(String.valueOf(Tel));
    }
   
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ProfileUser.fxml"));
       
        Parent root = loader.load();
        
        tfEmail.getScene().setRoot(root);
        
        ProfileUserController home = loader.getController();
        home.id_user = id_user;
        home.AfficherUser(String.valueOf(id_user));
    }

    @FXML
    private void Logout(ActionEvent event) {
    }
    
}
