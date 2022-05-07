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
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class FrontVController implements Initializable {

    @FXML
    private TableView<Annonce> table;
    @FXML
    private TableColumn<Annonce, String> titreA;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         table();
    }
    
    private void table()  {
        ServiceAnnonce s = new ServiceAnnonce();
    
    
    ObservableList <Annonce> all;
     all = s.getAll();
     
    table.setItems(all);
   
     titreA.setCellValueFactory (new PropertyValueFactory<Annonce,String>("titre"));
     
        
    }
     private void supp(ActionEvent event) throws IOException {
        
       // table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
         Annonce a = table.getSelectionModel().getSelectedItem();
         ServiceAnnonce sp = new ServiceAnnonce();
         System.out.println(a);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontDetail.fxml"));
         FrontDetailController apc = loader.getController();
         
         Parent root = loader.load();
         table.getScene().setRoot(root);
    }

    @FXML
    private void chercher(ActionEvent event) {
        
    }

    @FXML
    private void paasse(MouseEvent event) throws IOException {
        Annonce a = table.getSelectionModel().getSelectedItem();
         ServiceAnnonce sp = new ServiceAnnonce();
         System.out.println(a);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontDetail.fxml"));
         FrontDetailController apc = loader.getController();
         
         Parent root = loader.load();
         table.getScene().setRoot(root);
    }

    
}
