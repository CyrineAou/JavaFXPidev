/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.WinDeskTop.gui;


import edu.WinDeskTop.Services.ServiceAnnonce;
import javafx.scene.control.Label;

import edu.WinDeskTop.entities.Annonce;
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
 * @author cyrine
 */
public class DetailAnnonceController implements Initializable {

    @FXML
    private TextField lbTitre;
    @FXML
    private TextField lbdes;
    @FXML
    private TextField lbtype;
    @FXML
    private TextField lbdispo;
    @FXML
    private TextField lbprix;
    @FXML
    private TextField lbdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }    
public void setLbTite(String titre) {
        this.lbTitre.setText(titre);
    }
public void setLbDesc(String description) {
        this.lbdes.setText(description);
    }
public void setLbType(String type) {
        this.lbtype.setText(type);
    }
public void setLbDispo(String disponible) {
        this.lbdispo.setText(disponible);
    }
public void setLbPrix(String prix) {
        this.lbprix.setText(prix);
    }
public void setLbDate(String date) {
        this.lbdate.setText(date);
    }
/*
    @FXML
    private void modifier(ActionEvent event) throws IOException {
      //  table();
        // Annonce a = table.getSelectionModel().getSelectedItem();
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("AjoutAnnonceFXML.fxml"));
        Parent root =loader.load();
      //  table.getScene().setRoot(root);
        AjoutAnnonceFXMLController apc =loader.getController();
      //  apc.getTfTitre().setText(a.getTitre());
    }

    @FXML
    private void delete(ActionEvent event) {
    }
    */


    public TextField getLbTitre() {
        return lbTitre;
    }

    public TextField getLbdes() {
        return lbdes;
    }

    public TextField getLbtype() {
        return lbtype;
    }

    public TextField getLbdispo() {
        return lbdispo;
    }

    public TextField getLbprix() {
        return lbprix;
    }

    public TextField getLbdate() {
        return lbdate;
    }

    public void setLbTitre(TextField lbTitre) {
        this.lbTitre = lbTitre;
    }

    public void setLbdes(TextField lbdes) {
        this.lbdes = lbdes;
    }

    public void setLbtype(TextField lbtype) {
        this.lbtype = lbtype;
    }

    public void setLbdispo(TextField lbdispo) {
        this.lbdispo = lbdispo;
    }

    public void setLbprix(TextField lbprix) {
        this.lbprix = lbprix;
    }

    public void setLbdate(TextField lbdate) {
        this.lbdate = lbdate;
        
    }
    
    

    @FXML
    private void save(ActionEvent event) throws IOException { 
        Annonce a = new Annonce();
        ServiceAnnonce sp = new ServiceAnnonce();
        a.setTitre(lbTitre.getText());
        a.setDescription(lbdes.getText());
        a.setPrix(lbprix.DEFAULT_PREF_COLUMN_COUNT);
        //a.setDisponible(lbdispo.getText());
        a.setDate(lbdate.getText());
        a.setType(lbtype.getText());
        sp.modifier(a);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeAnnonceFXML.fxml"));
        Parent root = loader.load(); 
        lbTitre.getScene().setRoot(root);
        ListeAnnonceFXMLController apc = loader.getController();
        

  //  Annonce A = new Annonce(lbTitre.setText(), lbprix.setFont(),lbdispo.set,lbdes.setText(),lbtype.setText(),lbdate);

       //  sp.modifier(A);
        JOptionPane.showMessageDialog(null, "Annonce a ete mis a !");
    }

     
     
    

    
}
