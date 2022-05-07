/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.WinDeskTop.gui;

import edu.WinDeskTop.Services.ServiceAnnonce;
import edu.WinDeskTop.Services.ServicePromotion;
import edu.WinDeskTop.entities.Promotion;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class AjoutPromoFXMLController implements Initializable {

    @FXML
    private TextField tfTaux;
    @FXML
    private DatePicker dateD;
    @FXML
    private DatePicker dateF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutpromo(ActionEvent event) throws IOException {
        String date = dateD.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String d1 = dateF.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                
     Promotion p = new Promotion(tfTaux.DEFAULT_PREF_COLUMN_COUNT,date,d1);
         ServicePromotion sp = new ServicePromotion();
         sp.ajouter(p);
        JOptionPane.showMessageDialog(null, "Promotion ajoutée !");
         
    }

    @FXML
    private void listPromo(ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("ListPromoFXML.fxml"));
      Parent root = loader.load();
      tfTaux.getScene().setRoot(root);
      ListPromoFXMLController apc = loader.getController();
    }
    
}
