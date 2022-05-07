/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.WinDeskTop.gui;

import edu.WinDeskTop.Services.ServicePromotion;
import edu.WinDeskTop.entities.Promotion;
import java.io.IOException;
import java.net.URL;
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
public class DetailPromoFXMLController implements Initializable {

    @FXML
    private TextField tfTaux;
    @FXML
    private TextField tfdateD;
    @FXML
    private TextField tfdateF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save(ActionEvent event) throws IOException {
     
        Promotion  p = new Promotion();
        ServicePromotion sp = new ServicePromotion();
        p.setTaux(tfTaux.DEFAULT_PREF_COLUMN_COUNT);
        p.setDate_debut(tfdateD.getText());
        p.setDate_fin(tfdateF.getText());
        
        sp.modifier(p);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListPromoFXML.fxml"));
        Parent root = loader.load(); 
        tfTaux.getScene().setRoot(root);
        ListPromoFXMLController apc = loader.getController();
        

  //  Annonce A = new Annonce(lbTitre.setText(), lbprix.setFont(),lbdispo.set,lbdes.setText(),lbtype.setText(),lbdate);

       //  sp.modifier(A);
        JOptionPane.showMessageDialog(null, "Promotion a ete mis a !");
    }

    public TextField getTfTaux() {
        return tfTaux;
    }

    public TextField getTfdateD() {
        return tfdateD;
    }

    public TextField getTfdateF() {
        return tfdateF;
    }

    public void setTfTaux(TextField tfTaux) {
        this.tfTaux = tfTaux;
    }

    public void setTfdateD(TextField tfdateD) {
        this.tfdateD = tfdateD;
    }

    public void setTfdateF(TextField tfdateF) {
        this.tfdateF = tfdateF;
    }

  
    
}
