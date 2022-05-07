/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.WinDeskTop.gui;

import edu.WinDeskTop.Services.ServiceAnnonce;
import edu.WinDeskTop.entities.Annonce;
import java.io.IOException;
import java.net.URL;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.management.Notification;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class ListeAnnonceFXMLController implements Initializable {

    @FXML
    private Button BtnAnnonce;
    @FXML
    private Button btnpromotion;
    @FXML
    private TableView<Annonce> table;
    @FXML
    private TableColumn<Annonce, String> titreA;
    @FXML
    private TableColumn<Annonce, String> typeA;
    @FXML
    private TableColumn<Annonce, String> desA;
    @FXML
    private TableColumn<Annonce, Float> prixA;
    @FXML
    private TableColumn<Annonce, String> dateA;
    @FXML
    private TableColumn<Annonce, Boolean> dispoA;
    @FXML
    private TextField tftype;
   
    
    //private WebView webView;
    //private WebEngine engine;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
        //engine = webView.getEngine();
    }    
public void table(){
    ServiceAnnonce s = new ServiceAnnonce();
    
    
    ObservableList <Annonce> all;
     all = s.getAll();
    
    table.setItems(all);
   
     titreA.setCellValueFactory (new PropertyValueFactory<Annonce,String>("titre"));
     typeA.setCellValueFactory (new PropertyValueFactory<Annonce,String>("type"));
     desA.setCellValueFactory (new PropertyValueFactory<Annonce,String>("description"));
     prixA.setCellValueFactory (new PropertyValueFactory<Annonce,Float>("prix"));
     dateA.setCellValueFactory (new PropertyValueFactory<Annonce,String>("date"));
     dispoA.setCellValueFactory (new PropertyValueFactory<Annonce,Boolean>("disponible"));
  
   
    
}
    
    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutAnnonceFXML.fxml"));
        Parent root = loader.load(); 
        table.getScene().setRoot(root);
        AjoutAnnonceFXMLController apc = loader.getController();
    }

    @FXML
    private void listeannonce(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeAnnonceFXML.fxml"));
        Parent root = loader.load(); 
         table.getScene().setRoot(root);
        ListeAnnonceFXMLController apc = loader.getController();
    }

    @FXML
    private void listepromotion(ActionEvent event) throws IOException {

      FXMLLoader loader =  new FXMLLoader(getClass().getResource("ListPromoFXML.fxml"));
        Parent root =loader.load();
        table.getScene().setRoot(root);
        ListPromoFXMLController apc =loader.getController();
   
       
        
        
    }

    @FXML
    private void editt(ActionEvent event) throws IOException {
         Annonce a = table.getSelectionModel().getSelectedItem();
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("DetailAnnonce.fxml"));
        Parent root =loader.load();
        table.getScene().setRoot(root);
        DetailAnnonceController apc =loader.getController();
        apc.getLbTitre().setText(a.getTitre());
        apc.getLbtype().setText(a.getType());
        apc.getLbdes().setText(a.getDescription());
        apc.getLbdate().setText(a.getDate());
        apc.getLbprix().setText(String.valueOf(a.getPrix()));
        apc.getLbdispo().setVisible(a.getDisponible());
        

    }

    @FXML
    private void supp(ActionEvent event) throws IOException {
        
       // table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
         Annonce a = table.getSelectionModel().getSelectedItem();
         ServiceAnnonce sp = new ServiceAnnonce();
         System.out.println(a);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeAnnonceFXML.fxml"));
         ListeAnnonceFXMLController apc = loader.getController();
         sp.supprimer(a.getId());
         Parent root = loader.load();
         table.getScene().setRoot(root);
    }

    @FXML
    private void btnrecherche(ActionEvent event) {
        ServiceAnnonce s = new ServiceAnnonce();
        ObservableList <Annonce> all;
        all= s.chercherAnnonce(tftype.getText());
        System.out.println(all);
        table.setItems(all);
   
     titreA.setCellValueFactory (new PropertyValueFactory<Annonce,String>("titre"));
     typeA.setCellValueFactory (new PropertyValueFactory<Annonce,String>("type"));
     desA.setCellValueFactory (new PropertyValueFactory<Annonce,String>("description"));
     prixA.setCellValueFactory (new PropertyValueFactory<Annonce,Float>("prix"));
     dateA.setCellValueFactory (new PropertyValueFactory<Annonce,String>("date"));
     dispoA.setCellValueFactory (new PropertyValueFactory<Annonce,Boolean>("disponible"));
  
   
        
        
    
               
        
    }

    @FXML
    private void btnmoincher(ActionEvent event) {
        ServiceAnnonce s = new ServiceAnnonce();
        ObservableList <Annonce> all;
        all= s.trierAnnoncesParType();
        System.out.println(all);
        table.setItems(all);
   
     titreA.setCellValueFactory (new PropertyValueFactory<Annonce,String>("titre"));
     typeA.setCellValueFactory (new PropertyValueFactory<Annonce,String>("type"));
     desA.setCellValueFactory (new PropertyValueFactory<Annonce,String>("description"));
     prixA.setCellValueFactory (new PropertyValueFactory<Annonce,Float>("prix"));
     dateA.setCellValueFactory (new PropertyValueFactory<Annonce,String>("date"));
     dispoA.setCellValueFactory (new PropertyValueFactory<Annonce,Boolean>("disponible"));
         
         
    }

    @FXML
    private void btnunique(ActionEvent event) throws IOException {
         Annonce a = table.getSelectionModel().getSelectedItem();
         ServiceAnnonce sp = new ServiceAnnonce();
          FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeAnnonceFXML.fxml"));
         System.out.println(a);
         sp.AnnonceSansRedondance();
          Parent root = loader.load();
         table.getScene().setRoot(root);
        
    }

    @FXML
    private void statistiqyeview(ActionEvent event) throws IOException {
         FXMLLoader loader =  new FXMLLoader(getClass().getResource("StatisticFXML.fxml"));
        Parent root =loader.load();
        table.getScene().setRoot(root);
       StatisticFXMLController apc =loader.getController();
    }

    @FXML
    private void websiteview(ActionEvent event) throws IOException {
//        Image image=new Image("edu.WinDeskTop.Images/camera");

        Notifications notifications=Notifications.create();
       // notifications.graphic(new ImageView(image));
        notifications.text("visitez notre site web");
        notifications.title("Vous passez a notre site web");
        notifications.hideAfter(Duration.seconds(4));
        notifications.darkStyle();
        /*notifications.darkStyle();*/
     /*   notifications.position(Pos.BOTTOM_CENTER);*/
        notifications.show();
         FXMLLoader loader = new FXMLLoader(getClass().getResource("WebView.fxml"));
        Parent root = loader.load(); 
         table.getScene().setRoot(root);
        WebViewController apc = loader.getController();


      
    }


    
    
}
