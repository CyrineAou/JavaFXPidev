/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.activation.DataSource;
import javax.swing.JOptionPane;
import projet.entities.Agence;
import projet.services.CRUDagence;
import projet.tools.MyConnection;

/**org
 * FXML Controller class
 *
 * @author Ih3b
 */
public class CRUDagenceController implements Initializable {

    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfCodeL;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfNb_adultes;
    @FXML
    private TextField tfNb_enfants;
    @FXML
    private TableView<Agence> tabAgence;
    @FXML
    private TableColumn<Agence, Integer> colPhone;
    @FXML
    private TableColumn<Agence, Integer> colCodeL;
    @FXML
    private TableColumn<Agence, String> colEmail;
    @FXML
    private TableColumn<Agence, String> colAdresse;
    @FXML
    private TableColumn<Agence, Integer> colNb_adultes;
    @FXML
    private TableColumn<Agence, Integer> colNb_enfants;
    @FXML
    private Button btnDeleteAgence;
    @FXML
    private Button btnEditAgence;
    @FXML
    private Button btnAddAgence;
    @FXML
    private Button btnHomeTransition;
    @FXML
    private TextField tfRecherche;

    int index = -1;
    ObservableList<Agence> listA;
    ObservableList<Agence> dataList; 
    ObservableList<PieChart.Data> piechartdata;
   
    @FXML
    private Pane ResetPane;
    @FXML
    private PieChart stat_Agence;

    /**
     * Initializes the controller class.
     */
    void recherche_agence() {
        colPhone.setCellValueFactory(new PropertyValueFactory<Agence, Integer>("Phone"));
        colCodeL.setCellValueFactory(new PropertyValueFactory<Agence, Integer>("CodeL"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Agence, String>("Email"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<Agence, String>("Adress"));
        colNb_adultes.setCellValueFactory(new PropertyValueFactory<Agence, Integer>("Nb_adultes"));
        colNb_enfants.setCellValueFactory(new PropertyValueFactory<Agence, Integer>("Nb_enfants"));

        CRUDagence ca = new CRUDagence();
        dataList = ca.showAgence();
        tabAgence.setItems(dataList);

        FilteredList<Agence> filteredData = new FilteredList<>(dataList, b -> true);
        tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Agence -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (Agence.getAdress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Agence.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Agence> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabAgence.comparatorProperty());
        tabAgence.setItems(sortedData);
    }
    



    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    
 /* public void SmsSender() {
     // Find your Account Sid and Auth Token at twilio.com/console
       final String ACCOUNT_SID
                = "AC03ecff0748671a798d05d9b5c89040a3";
       final String AUTH_TOKEN
               = "a15b228e86a0346951c06e8d38527be0";

      Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

      Message message = Message
                .creator(new com.twilio.type.PhoneNumber("+21656100840"), // to
                    new com.twilio.type.PhoneNumber("+19704648109"), // from
                       "Reservation ajoutée avec success")
               .create();

       System.out.println(message.getSid());

    } */


    @FXML
    private void AddAgence(ActionEvent event) {
         if (tfPhone.getText() == null || tfAdresse.getText()==null || tfEmail.getText() == null || tfCodeL.getText()== null || tfNb_enfants.getText()== null || tfNb_adultes == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Reservation");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();

        } else {
        String aPhone1 = tfPhone.getText();  
        String aCodeL1 = tfCodeL.getText();
        String aEmail = tfEmail.getText();
        String aAdress = tfAdresse.getText();
        String aNb_adultes1 = tfNb_adultes.getText();
        String aNb_enfants1 = tfNb_enfants.getText();

        int aPhone = Integer.parseInt(aPhone1);
        int aCodeL = Integer.parseInt(aCodeL1);
        int aNb_adultes = Integer.parseInt(aNb_adultes1);
        int aNb_enfants = Integer.parseInt(aNb_enfants1);  
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Reservation");
            alert.setHeaderText(null);
            alert.setContentText("Reservation ajoutée!");
            alert.show();

        Agence a = new Agence(aPhone, aCodeL, aEmail, aAdress, aNb_adultes, aNb_enfants);
        CRUDagence cda = new CRUDagence();
        cda.addAgence(a); 
        majTable();
        recherche_agence();
      // SmsSender();
    
    } }

    @FXML
    private void DeleteAgence(ActionEvent event) { 
        if (tfPhone.getText()== null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Reservation");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner une Reservation!");
            alert.show();

        } else { 
            Alert alertWE2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertWE2.setTitle("Suppression Reservation");
            alertWE2.setHeaderText("Voulez vous confirmer la Suppression ?");
            alertWE2.setContentText("La Reservation va être supprimer");
            Optional<ButtonType> result = alertWE2.showAndWait();

            if (result.get() == ButtonType.OK) {
        
        String dID1 = tfPhone.getText();

        int dID = Integer.parseInt(dID1); 
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Reservation");
                alert.setHeaderText(null);
                alert.setContentText("Reservation supprimée!");
                alert.show();

        CRUDagence cda = new CRUDagence();
        cda.deleteAgence(dID);
        majTable();
        recherche_agence();
            }else { 
                return;
            }

        tfPhone.setText(null);
        tfAdresse.setText(null);
        tfNb_adultes.setText(null);
        tfEmail.setText(null);
        tfCodeL.setText(null);
        tfNb_enfants.setText(null);
    }
    }

    @FXML
    private void EditAgence(ActionEvent event) {
        if (tfPhone.getText()== null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Reservation");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner une Reservation!");
            alert.showAndWait();

        } else {
            Alert alertWE2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertWE2.setTitle("Modification Reservation");
            alertWE2.setHeaderText("Voulez vous confirmer la modification ?");
            alertWE2.setContentText("La Reservation va être modifier");
            Optional<ButtonType> result = alertWE2.showAndWait();

            if (result.get() == ButtonType.OK) {
        
        
        
        
        
        
        String ePhone1 = tfPhone.getText();
        String eCodeL1 = tfCodeL.getText();
        String eEmail = tfEmail.getText();
        String eAdress = tfAdresse.getText();
        String eNb_adultes1 = tfNb_adultes.getText();
        String eNb_enfants1 = tfNb_enfants.getText();

        int ePhone = Integer.parseInt(ePhone1);
        int eCodeL = Integer.parseInt(eCodeL1);
        int eNb_adultes = Integer.parseInt(eNb_adultes1);
        int eNb_enfants = Integer.parseInt(eNb_enfants1); 
        
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Reservation");
                alert.setHeaderText(null);
                alert.setContentText("Reservation modifiée!");
                alert.show();

        Agence a = new Agence(ePhone, eCodeL, eEmail, eAdress, eNb_adultes, eNb_enfants);
        CRUDagence cda = new CRUDagence();
        cda.editAgence(a);
        majTable();
        recherche_agence(); 
        } else {
                return;
            }
    }
    }
    @FXML
    private void getSelectedAgence(MouseEvent event) {
        index = tabAgence.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }

        tfPhone.setText(colPhone.getCellData(index).toString());
        tfCodeL.setText(colCodeL.getCellData(index).toString());
        tfEmail.setText(colEmail.getCellData(index));
        tfAdresse.setText(colAdresse.getCellData(index));
        tfNb_adultes.setText(colNb_adultes.getCellData(index).toString());
        tfNb_enfants.setText(colNb_enfants.getCellData(index).toString());
    }
    
    public void majTable() {

        colPhone.setCellValueFactory(new PropertyValueFactory<Agence, Integer>("Phone"));
        colCodeL.setCellValueFactory(new PropertyValueFactory<Agence, Integer>("CodeL"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Agence, String>("Email"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<Agence, String>("Adress"));
        colNb_adultes.setCellValueFactory(new PropertyValueFactory<Agence, Integer>("Nb_adultes"));
        colNb_enfants.setCellValueFactory(new PropertyValueFactory<Agence, Integer>("Nb_enfants"));

        CRUDagence cda = new CRUDagence();
        listA = cda.showAgence();
        tabAgence.setItems(listA);
    }

    @FXML
    private void HomeTransition(ActionEvent event) {
        Stage stage = (Stage) btnHomeTransition.getScene().getWindow();
        stage.close();
    }
   

    // el fou9 ena aamalt stage jdid yajouti el reservation en facture wa7da barka plz 
    @FXML
    private void ResetValues(MouseEvent event) {
        tfPhone.setText(null);
        tfAdresse.setText(null);
        tfNb_adultes.setText(null);
        tfEmail.setText(null);
        tfCodeL.setText(null);
        tfNb_enfants.setText(null);

    }
public void loadDataPie() throws SQLException
    {
        piechartdata = FXCollections.observableArrayList();
        String requete = "select o.Adress,count(o.Phone) as nbadd from agence o group by o.Adress";
              PreparedStatement pst= new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            piechartdata.add(new PieChart.Data(rs.getString("Adress"), rs.getInt("nbadd")));

        }
    }
    @Override
   
    public void initialize(URL url, ResourceBundle rb) { 
        try {
            // TODO
            loadDataPie();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDagenceController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        stat_Agence.setData(piechartdata);
        tfPhone.setText(null);
        tfAdresse.setText(null);
        tfNb_adultes.setText(null);
        tfEmail.setText(null);
        tfCodeL.setText(null);
        tfNb_enfants.setText(null);
        majTable();
        recherche_agence();
    }

}
