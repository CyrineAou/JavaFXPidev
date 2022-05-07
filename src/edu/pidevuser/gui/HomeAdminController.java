/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.pidevuser.gui;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import edu.pidevuser.entities.User;
import edu.pidevuser.services.ProfileAdmin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class HomeAdminController implements Initializable {

    @FXML
    private TableView<User> tbUsers;
    @FXML
    private TableColumn<User, String> colUsername;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, Integer> colTel;
    @FXML
    private TableColumn<User, String> colPrenom;
    @FXML
    private TableColumn<User, String> colNom;
    @FXML
    private TableColumn<User, Boolean> colBanned;
    @FXML
    private Button btDelete;
    @FXML
    private Button btEdit;
    @FXML
    private TextField filterField;

    /**
     * Initializes the controller class.
     */
    
    private ObservableList<User> masterData = FXCollections.observableArrayList();
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ProfileAdmin s = new ProfileAdmin();
        masterData =s.ListerUsers();
        
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        colBanned.setCellValueFactory(new PropertyValueFactory<>("banned"));
		
		
		FilteredList<User> filteredData = new FilteredList<>(masterData, p -> true);
		
		
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {
				
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (user.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
					return true; 
				} else if (user.getLastName().toLowerCase().contains(lowerCaseFilter)) {
					return true; 
				}
				return false; 
			});
		});
		
		
		SortedList<User> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(tbUsers.comparatorProperty());
		
		
		tbUsers.setItems(sortedData);
    }    
    
    public void showAll(){
        ProfileAdmin s = new ProfileAdmin();
    

        
        ObservableList<User> all;
        all =s.ListerUsers();
        System.out.println(all);
        tbUsers.setItems(all);
        colUsername.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        colNom.setCellValueFactory(new PropertyValueFactory<User,String>("lastName"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<User,String>("firstName"));
        colTel.setCellValueFactory(new PropertyValueFactory<User,Integer>("numTel"));
        colBanned.setCellValueFactory(new PropertyValueFactory<User,Boolean>("banned"));


 
    
         }

    @FXML
    private void Delete(ActionEvent event) throws IOException {
        User u = tbUsers.getSelectionModel().getSelectedItem();
        ProfileAdmin s = new ProfileAdmin();
        
        s.SupprimerUser(u.getId());
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/HomeAdmin.fxml"));
       
        Parent root = loader.load();
        
        tbUsers.getScene().setRoot(root);  
        
        
    }

    /*private void Edit(ActionEvent event) throws IOException {
        
        User u = tbUsers.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminEditUser.fxml"));
        Parent root = loader.load();
        tbUsers.getScene().setRoot(root);
        
       AdminEditUserController apc = loader.getController();
       apc.AfficherUser(String.valueOf(u.getId()));
       apc.setUser(u);  
        
        
    }*/

    @FXML
    private void ajouterAdmin(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AddAmin.fxml"));
       
        Parent root = loader.load();
        
        filterField.getScene().setRoot(root);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/LoginUserFXML.fxml"));
       
        Parent root = loader.load();
        
        filterField.getScene().setRoot(root); 
    }


    private void ModifierUser(ActionEvent event) throws IOException {
        User u = tbUsers.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminEditUser.fxml"));
        Parent root = loader.load();
        tbUsers.getScene().setRoot(root);
        
        AdminEditUserController apc = loader.getController();
        apc.AfficherUser(String.valueOf(u.getId()));
        apc.setUser(u);
    }

    private void editEtat(MouseEvent event) throws IOException {
        
    }

    @FXML
    private void editEtat(ActionEvent event) throws IOException {
        User u = tbUsers.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminEditUser.fxml"));
        Parent root = loader.load();
        tbUsers.getScene().setRoot(root);
        
        AdminEditUserController apc = loader.getController();
        apc.AfficherUser(String.valueOf(u.getId()));
        apc.setUser(u);
    }



    
    
    

}
