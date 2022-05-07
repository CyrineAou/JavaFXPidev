/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.pidevuser.gui;

import edu.pidevuser.entities.User;
import edu.pidevuser.services.ProfileUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class ProfileUserController implements Initializable {

    @FXML
    private Label lblUsername;
    @FXML
    private Label lblNom;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblPrenom;
    @FXML
    private Label lblTel;

    public int id_user;
    private User user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void AfficherUser(String id){
    
        ProfileUser pu = new ProfileUser();
        
        User u = pu.getUser(id);
        lblUsername.setText(u.getUsername());
        lblNom.setText(u.getLastName());
        lblEmail.setText(u.getEmail());
        lblPrenom.setText(u.getFirstName());
        lblTel.setText(String.valueOf(u.getNumTel()));
        id_user = u.getId();
        
    }

    @FXML
    private void Redirection(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ProfileUserEdit.fxml"));
       
        Parent root = loader.load();
        
        lblEmail.getScene().setRoot(root);   
        
        ProfileUserEditController apc = loader.getController();
        apc.settfEmail(lblEmail.getText());
        apc.settfPrenom(lblPrenom.getText());
        apc.settfNom(lblNom.getText());
        apc.settfUsername(lblUsername.getText());
        apc.settfTel(Integer.parseInt(lblTel.getText()));
        apc.setUser(user);
        System.out.println(user.toString());
        
    }

    public void setLblUsername(String Username) {
        this.lblUsername.setText(Username); 
    }

    public void setLblNom(String Nom) {
        this.lblNom.setText(Nom); 
    }

    public void setLblEmail(String Email) {
        this.lblEmail.setText(Email);
    }

    public void setLblPrenom(String Prenom) {
        this.lblPrenom.setText(Prenom);
    }

    public void setLblTel(int Tel) {
        this.lblTel.setText(String.valueOf(Tel));
    }

    @FXML
    private void Logout(ActionEvent event) throws IOException {
        
       FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/LoginUserFXML.fxml"));
       
        Parent root = loader.load();
        
        lblEmail.getScene().setRoot(root);   
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @FXML
    private void RMotdepasse(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PasswordEdit.fxml"));
        Parent root = loader.load();
        lblNom.getScene().setRoot(root);
        PasswordEditController home = loader.getController();
        home.id_user = id_user;
        
    }
    
    
}
