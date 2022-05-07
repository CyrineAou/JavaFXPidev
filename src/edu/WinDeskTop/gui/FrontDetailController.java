/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.WinDeskTop.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class FrontDetailController implements Initializable {

    @FXML
    private Label lbT;
    @FXML
    private Label lbTy;
    @FXML
    private Label lbde;
    @FXML
    private Label lbdi;
    @FXML
    private Label lbda;
    @FXML
    private Label lbP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
     public void setLbTitre(String titre) {
        this.lbT.setText(titre);
    }
      public void setLbType(String type) {
        this.lbTy.setText(type);
    }
       public void setLbDesc(String description) {
        this.lbde.setText(description);
    }
        public void setLbDispo(String disponible) {
        this.lbdi.setText(disponible);
    }
         public void setLbDate(String date) {
        this.lbda.setText(date);
    }
          public void setLbPrix(String prix) {
        this.lbP.setText(prix);
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontV.fxml"));
        Parent root = loader.load(); 
        lbP.getScene().setRoot(root);
        FrontVController apc = loader.getController();
    }

    public void setLbT(Label lbT) {
        this.lbT = lbT;
    }

    public void setLbTy(Label lbTy) {
        this.lbTy = lbTy;
    }

    public void setLbde(Label lbde) {
        this.lbde = lbde;
    }

    public void setLbdi(Label lbdi) {
        this.lbdi = lbdi;
    }

    public void setLbda(Label lbda) {
        this.lbda = lbda;
    }

    public void setLbP(Label lbP) {
        this.lbP = lbP;
    }

    public Label getLbT() {
        return lbT;
    }

    public Label getLbTy() {
        return lbTy;
    }

    public Label getLbde() {
        return lbde;
    }

    public Label getLbdi() {
        return lbdi;
    }

    public Label getLbda() {
        return lbda;
    }

    public Label getLbP() {
        return lbP;
    }
    
}
