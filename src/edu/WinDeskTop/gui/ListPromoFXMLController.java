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
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class ListPromoFXMLController implements Initializable {

    @FXML
    private Button BtnAnnonce;
    @FXML
    private Button btnpromotion;
    @FXML
    private TableView<Promotion> table;
    @FXML
    private TableColumn<Promotion,Integer> tfTaux;
    @FXML
    private TableColumn<Promotion, String> tfDD;
    @FXML
    private TableColumn<Promotion, String> tfDF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     table();
    }    

    public void table(){
    ServicePromotion s = new ServicePromotion();
    ObservableList <Promotion> all;
     all = s.getAll();
    
    table.setItems(all);
    
    tfDD.setCellValueFactory (new PropertyValueFactory<Promotion,String>("date_debut"));
    tfTaux.setCellValueFactory(new PropertyValueFactory<Promotion,Integer>("taux"));
    tfDF.setCellValueFactory (new PropertyValueFactory<Promotion,String>("date_fin"));
    
    
    
    
    
    
    
    }
    
    @FXML
    private void listeannonce(ActionEvent event) throws IOException {
         FXMLLoader loader =  new FXMLLoader(getClass().getResource("ListeAnnonceFXML.fxml"));
        Parent root =loader.load();
        table.getScene().setRoot(root);
        ListeAnnonceFXMLController apc =loader.getController();
    }

    @FXML
    private void listepromotion(ActionEvent event) throws IOException {
         FXMLLoader loader =  new FXMLLoader(getClass().getResource("ListPromoFXML.fxml"));
        Parent root =loader.load();
        table.getScene().setRoot(root);
        ListPromoFXMLController apc =loader.getController();
    }

    @FXML
    private void add(ActionEvent event) throws IOException {
   
      
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutPromoFXML.fxml"));
          Parent root = loader.load();
        table.getScene().setRoot(root);
         ListPromoFXMLController apc = loader.getController();
        
    }

    @FXML
    private void supp(ActionEvent event) throws IOException {
         Promotion a = table.getSelectionModel().getSelectedItem();
         ServicePromotion sp = new ServicePromotion();
         System.out.println(a);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ListPromoFXML.fxml"));
         ListPromoFXMLController apc = loader.getController();
         sp.supprimer(a.getId());
         Parent root = loader.load();
         table.getScene().setRoot(root);
    }

    @FXML
    private void editt(ActionEvent event) throws IOException {
        Promotion p= table.getSelectionModel().getSelectedItem();
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("DetailPromoFXML.fxml"));
        Parent root =loader.load();
        table.getScene().setRoot(root);
        DetailPromoFXMLController apc =loader.getController();
     // apc.setTaux(p.getTaux());
         apc.getTfdateD().setText(p.getDate_debut());
        apc.getTfdateF().setText(p.getDate_debut());

    }
    

}
