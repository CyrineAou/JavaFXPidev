/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gui;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projet.entities.Agence;
import projet.entities.Facture;
import projet.entities.Facture;

import projet.services.CRUDagence;
import projet.services.CRUDfacture;
import projet.tools.MyConnection; 
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.nio.sctp.Notification;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;



/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class CRUDfactureController implements Initializable {

    @FXML
    private TextField tfCodeTVA;
    
 
    @FXML
    private Button btnEditFacture;
    @FXML
    private Button btnDeleteFacture;
    @FXML
    private Button btnHomeTransition;
    @FXML
    private TableView<Facture> tab;
    @FXML
    private TextField tfRecherche;
    @FXML
    private TableColumn<Facture, Integer> colCodeTVA;
    @FXML
    private TableColumn<Facture,Integer > coladultes;
    @FXML
    private TableColumn<Facture ,Integer> colenfants;
    @FXML
    private TableColumn<Facture ,Integer> coltour;
    @FXML
    private TableColumn<Facture, String> colMethode;
    

    
      int index = -1;
    ObservableList<Facture> listS;
    ObservableList<Facture> dataList; 
    ObservableList<PieChart.Data> piechartdata;
    
    @FXML
    private PieChart stat_Facture;
    @FXML
    private Button pdfs;
   
   
    
    @FXML
    private Button btnValider;
    @FXML
    private TextField tfadultes;
    @FXML
    private TextField tfenfants;
    @FXML
    private TextField tfrais;
    @FXML
    private TextField tfmethode;

   
    
    /**
     * Initializes the controller class.
     */
    public void loadDataPie() throws SQLException
    {
        piechartdata = FXCollections.observableArrayList();
        String requete = "select o.Methode,count(o.CodeTVA) as nbadd from facture o group by o.Methode";
              PreparedStatement pst= new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            piechartdata.add(new PieChart.Data(rs.getString("Methode"), rs.getInt("nbadd")));

        }
           majTable ();  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        try {
            // TODO
            loadDataPie();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDagenceController.class.getName()).log(Level.SEVERE, null, ex);
        }  
       
        
        stat_Facture.setData(piechartdata); 
        
      
        
         tfCodeTVA.setText(null);
       
         tfadultes.setText(null);
         tfenfants.setText(null);
         tfrais.setText(null);
        tfmethode.setText(null);
      
        
        majTable();
        recherche_facture();
       
    }    

   @FXML
    private void AddFacture(ActionEvent event) {
         if (tfCodeTVA.getText() == null || tfadultes.getText()== null || tfenfants.getText()== null || tfrais.getText() == null||tfmethode.getText()==null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Facture");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();

        } else {
        // get text
        String sCodeTVA = tfCodeTVA.getText();
        String sfrais_ad = tfadultes.getText();
         String sfrais_en = tfenfants.getText();
         String sfrais_tour = tfrais.getText();
         String sMedhode = tfmethode.getText();
        
        int CodeTVA = Integer.parseInt(sCodeTVA); 
      Integer frais_ad= Integer.parseInt(sfrais_ad);
      Integer frais_en= Integer.parseInt(sfrais_en);
       Integer frais_tour= Integer.parseInt(sfrais_tour);            
       
        
        
        
        
        
        // end parsing
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Facture");
            alert.setHeaderText(null);
            alert.setContentText("Facture ajoutée!");
            alert.show();
       

        Facture s = new Facture(CodeTVA,frais_ad,frais_en,frais_tour,sMedhode);
        CRUDfacture cds = new CRUDfacture();
        cds.addFacture(s); 
        majTable();
         recherche_facture(); 
         }
       
    }
    @FXML
    private void EditFacture(ActionEvent event) {  
        if (tfCodeTVA.getText()== null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Facture");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner un Facture!");
            alert.showAndWait();

        } else {
            Alert alertWE2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertWE2.setTitle("Modification Facture");
            alertWE2.setHeaderText("Voulez vous confirmer la modification ?");
            alertWE2.setContentText("Le Facture va être modifier");
            Optional<ButtonType> result = alertWE2.showAndWait();

            if (result.get() == ButtonType.OK) {  
        
      
        
        String eCodeTVA = tfCodeTVA.getText();
        String efrais_ad = tfadultes.getText();
         String efrais_en = tfenfants.getText();
         String efrais_tour = tfrais.getText();
         String eMedhode = tfmethode.getText();
      

         int rCodeTVA = Integer.parseInt(eCodeTVA); 
         Integer rfrais_ad= Integer.parseInt(efrais_ad);
              Integer rfrais_en= Integer.parseInt(efrais_en);
                      Integer rfrais_tour= Integer.parseInt(efrais_tour);  
       
       
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Facture");
                alert.setHeaderText(null);
                alert.setContentText("Facture modifiée!");
                alert.show();

                Facture s = new Facture(rCodeTVA,rfrais_ad,rfrais_en,rfrais_tour,eMedhode);
                CRUDfacture cds = new CRUDfacture();
               
        cds.editFacture(s);
        majTable(); 
         recherche_facture(); 


         
         } else {
                return;
            }
        
    }
    }
    void recherche_facture() {
        colCodeTVA.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("CodeTVA"));
      
       coladultes.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("Frais Adultes"));
       colenfants.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("Frais Enfants"));
        coltour.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("Frais Tour"));
         colMethode.setCellValueFactory(new PropertyValueFactory<Facture, String>("Methode"));
         
        CRUDfacture cs = new CRUDfacture();
        dataList = cs.showFacture();
        tab.setItems(dataList);

        FilteredList<Facture> filteredData = new FilteredList<>(dataList, b -> true);
        tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Facture -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (Facture.getMethode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Facture> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tab.comparatorProperty());
        tab.setItems(sortedData);
    }

    @FXML
    private void DeleteFacture(ActionEvent event) { 
         if (tfCodeTVA.getText()== null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Facture");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner une Facture!");
            alert.show();

        } else { 
            Alert alertWE2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertWE2.setTitle("Suppression Facture");
            alertWE2.setHeaderText("Voulez vous confirmer la Suppression ?");
            alertWE2.setContentText("La Facture va être supprimer");
            Optional<ButtonType> result = alertWE2.showAndWait();

            if (result.get() == ButtonType.OK) {
         
        
        
        String dID1 = tfCodeTVA.getText();

        int dID = Integer.parseInt(dID1); 
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Facture");
                alert.setHeaderText(null);
                alert.setContentText("Facture supprimé!");
                alert.show();

        CRUDfacture cds = new CRUDfacture();
        cds.deleteFacture(dID);
        majTable(); 
         recherche_facture();
    } else { 
                return;
            }   
      
            tfCodeTVA.setText(null);
       
            tfadultes.setText(null);
            tfenfants.setText(null);
            tfrais.setText(null);
           tfmethode.setText(null);
       
           
            
         } 
    }
         

    @FXML
    private void HomeTransition(ActionEvent event) { 
        Stage stage = (Stage) btnHomeTransition.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void getSelected(MouseEvent event) { 
           index = tab.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        tfCodeTVA.setText(colCodeTVA.getCellData(index).toString());
        tfadultes.setText(coladultes.getCellData(index).toString());
        tfenfants.setText(colenfants.getCellData(index).toString());
        tfrais.setText(coltour.getCellData(index).toString());
        tfmethode.setText(colMethode.getCellData(index));
       
         
    }  
     public void majTable() {
  colCodeTVA.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("CodeTVA"));
  coladultes.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("Frais Adultes"));
       colenfants.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("Frais Enfants"));
       
        coltour.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("Frais Tour"));
         colMethode.setCellValueFactory(new PropertyValueFactory<Facture, String>("Methode"));
        
       

        CRUDfacture cds = new CRUDfacture();
        listS = cds.showFacture();
        tab.setItems(listS);
    }
 /*
    @FXML
   private void pdfs() 
          throws Exception{
        try {
              
            
            
            
            
            
            
           Class.forName("com.mysql.jdbc.Driver");
               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "root", "");
  Statement stmt = con.createStatement();
                    // Define the SQL query 
                    ResultSet query_set = stmt.executeQuery("SELECT * From facture");
                    //  Step-2: Initialize PDF documents - logical objects 
                    Document my_pdf_report = new Document();
                     String file_name = ("Liste_factures.pdf");  
                    PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file_name));
               
                    my_pdf_report.open();    
                    Image LogoA3MELTALLA = Image.getInstance("C:/Users/Ahmed Rai/Desktop/project/src/projet/srcs/logo.png");
                   
                LogoA3MELTALLA.setAlignment(Element.ALIGN_CENTER);
                my_pdf_report.add(LogoA3MELTALLA); 
                
                 Image qrcode = Image.getInstance("C:/Users/Ahmed Rai/Desktop/qr.png");
                qrcode.setAlignment(Element.ALIGN_BOTTOM);
                my_pdf_report.add(qrcode); 
                
                
                Paragraph paraHeader1 = new Paragraph("\n\nLa liste des factures est comme suit :\n\n");
                my_pdf_report.add(paraHeader1);
                    //we have four columns in our table
                    PdfPTable my_report_table = new PdfPTable(4);
                    //create a cell object
                    PdfPCell table_cell;

                    while (query_set.next()) {                
                                    String CodeTVA = query_set.getString("CodeTVA");
                                    table_cell=new PdfPCell(new Phrase(CodeTVA));
                                    my_report_table.addCell(table_cell);
                                    String Nom_s=query_set.getString("Nom_s");
                                    table_cell=new PdfPCell(new Phrase(Nom_s));
                                    my_report_table.addCell(table_cell);
                                    String Methode=query_set.getString("Methode");
                                    table_cell=new PdfPCell(new Phrase(Methode));
                                    my_report_table.addCell(table_cell);
                                    String Total=query_set.getString("Total");
                                    table_cell=new PdfPCell(new Phrase(Total));
                                    my_report_table.addCell(table_cell);
                                    }
                    //* Attach report table to PDF 
                    my_pdf_report.add(my_report_table);                       
                    my_pdf_report.close();

                    // Close all DB related objects 
                    query_set.close();
                    stmt.close(); 
                    con.close();

    } catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    } catch (DocumentException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    }
} */

   
}
  

