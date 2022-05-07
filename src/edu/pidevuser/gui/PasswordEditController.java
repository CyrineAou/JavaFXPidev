/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.pidevuser.gui;

import edu.pidevuser.services.ProfileUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class PasswordEditController implements Initializable {

    @FXML
    private TextField tfMdp;
    @FXML
    private TextField tfMdpC;
    public int id_user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
        if (tfMdp.getText().equals(tfMdpC.getText()) == false) {
            JOptionPane.showMessageDialog(null, "password doesn't much");
        }else if (tfMdp.getText().length()<4|| tfMdpC.getText().length()<4 ){
        JOptionPane.showMessageDialog(null, "mot est faible !");
        }
        else {
            ProfileUser u = new ProfileUser();
            u.modifier_password(id_user, tfMdp.getText());
            JOptionPane.showMessageDialog(null, "mot de passe modifié");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileUser.fxml"));
            Parent root = loader.load();
            tfMdp.getScene().setRoot(root);
            ProfileUserController home = loader.getController();
            home.id_user = id_user;
            home.AfficherUser(String.valueOf(id_user));
            

        }
    }

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void Logout(ActionEvent event) {
    }
    
}
