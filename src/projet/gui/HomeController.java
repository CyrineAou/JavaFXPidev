/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ih3b
 */
public class HomeController implements Initializable {

    @FXML
    private Button btnAgences;
    @FXML
    private Button btnSpons; 
    @FXML
    private Button btncontact;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CRUDagencesTransition(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUDagence.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Reservation");
            Scene sceneAg = new Scene(root);           
            stage.setScene(sceneAg);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void CRUDSponsTransition(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUDsponsor.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Facturation Sponsors");
            Scene sceneSp = new Scene(root);           
            stage.setScene(sceneSp);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void emailtransition(ActionEvent event) { 
        
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Email.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Email");
            Scene sceneSp = new Scene(root);           
            stage.setScene(sceneSp);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
