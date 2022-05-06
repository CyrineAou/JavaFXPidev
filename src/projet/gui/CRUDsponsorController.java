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
import projet.entities.Sponsor;
import projet.services.CRUDagence;
import projet.services.CRUDsponsor;
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
public class CRUDsponsorController implements Initializable {

    @FXML
    private TextField tfCodeTVA;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfType;
 
    @FXML
    private Button btnAddSponsor;
    @FXML
    private Button btnEditSponsor;
    @FXML
    private Button btnDeleteSponsor;
    @FXML
    private Button btnHomeTransition;
    @FXML
    private TableView<Sponsor> tabSponsor;
    @FXML
    private TextField tfRecherche;
    @FXML
    private TableColumn<Sponsor, Integer> colCodeTVA;
    @FXML
    private TableColumn<Sponsor, String> colNom;
    @FXML
    private TableColumn<Sponsor, String> colType;
    @FXML
    private TableColumn<Sponsor,Integer> colTotal;

    
      int index = -1;
    ObservableList<Sponsor> listS;
    ObservableList<Sponsor> dataList; 
    ObservableList<PieChart.Data> piechartdata;
    @FXML
    private TextField tfTotal;
    @FXML
    private PieChart stat_Sponsor;
    @FXML
    private Button pdfs;
    
    /**
     * Initializes the controller class.
     */
    public void loadDataPie() throws SQLException
    {
        piechartdata = FXCollections.observableArrayList();
        String requete = "select o.Type_s,count(o.CodeTVA) as nbadd from sponsor o group by o.Type_s";
              PreparedStatement pst= new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            piechartdata.add(new PieChart.Data(rs.getString("Type_s"), rs.getInt("nbadd")));

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
       
        
        stat_Sponsor.setData(piechartdata); 
        
        
        
        
         tfCodeTVA.setText(null);
        tfNom.setText(null);
        tfType.setText(null);
        tfTotal.setText(null);
        
        majTable();
        recherche_sponsor();
       
    }    

    @FXML
    private void AddSponsor(ActionEvent event) {
         if (tfCodeTVA.getText() == null || tfNom.getText()== null || tfType.getText()== null || tfTotal.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Facture");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();

        } else {
        
        String sId1 = tfCodeTVA.getText();
        String sNom_s = tfNom.getText();
        String sType_s = tfType.getText();
        String sTotal1 =tfTotal.getText();
       
        int sId = Integer.parseInt(sId1); 
        int sTotal = Integer.parseInt(sTotal1); 
        
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Facture");
            alert.setHeaderText(null);
            alert.setContentText("Facture ajoutée!");
            alert.show();
       

        Sponsor s = new Sponsor(sId,sNom_s,sType_s,sTotal);
        CRUDsponsor cds = new CRUDsponsor();
        cds.addSponsor(s); 
        majTable (); 
         recherche_sponsor(); 
         }
       
    }

    @FXML
    private void EditSponsor(ActionEvent event) {  
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
        
        
        
        String qId1 = tfCodeTVA.getText();
        String qNom_s = tfNom.getText();
        String qType_s = tfType.getText();
        String qTotal1 = tfTotal.getText();
      

        int qId = Integer.parseInt(qId1);
       int qTotal = Integer.parseInt(qTotal1); 
       
       
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Facture");
                alert.setHeaderText(null);
                alert.setContentText("Facture modifiée!");
                alert.show();

        Sponsor s = new Sponsor(qId,qNom_s,qType_s,qTotal);
        CRUDsponsor cds = new CRUDsponsor();
        cds.editSponsor(s);
        majTable(); 
         recherche_sponsor(); 
         } else {
                return;
            }
        
    }
    }
    void recherche_sponsor() {
        colCodeTVA.setCellValueFactory(new PropertyValueFactory<Sponsor, Integer>("CodeTVA"));
        colNom.setCellValueFactory(new PropertyValueFactory<Sponsor, String>("Nom_s"));
        colType.setCellValueFactory(new PropertyValueFactory<Sponsor, String>("Type_s"));
        colTotal.setCellValueFactory(new PropertyValueFactory<Sponsor, Integer>("Total"));

        CRUDsponsor cs = new CRUDsponsor();
        dataList = cs.showSponsor();
        tabSponsor.setItems(dataList);

        FilteredList<Sponsor> filteredData = new FilteredList<>(dataList, b -> true);
        tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Sponsor -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (Sponsor.getNom_s().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Sponsor.getType_s().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Sponsor> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabSponsor.comparatorProperty());
        tabSponsor.setItems(sortedData);
    }

    @FXML
    private void DeleteSponsor(ActionEvent event) { 
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

        CRUDsponsor cds = new CRUDsponsor();
        cds.deleteSponsor(dID);
        majTable(); 
         recherche_sponsor();
    } else { 
                return;
            }   
         tfCodeTVA.setText(null);
        tfNom.setText(null);
        tfType.setText(null);
        tfTotal.setText(null);
            
         } 
    }
         

    @FXML
    private void HomeTransition(ActionEvent event) { 
        Stage stage = (Stage) btnHomeTransition.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void getSelectedSponsor(MouseEvent event) { 
           index = tabSponsor.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }

        tfCodeTVA.setText(colCodeTVA.getCellData(index).toString());
        tfNom.setText(colNom.getCellData(index).toString());
        tfType.setText(colType.getCellData(index).toString());
        tfTotal.setText(colTotal.getCellData(index).toString());
        
    }  
     public void majTable() {

        colCodeTVA.setCellValueFactory(new PropertyValueFactory<Sponsor, Integer>("CodeTVA"));
        colNom.setCellValueFactory(new PropertyValueFactory<Sponsor, String>("Nom_s"));
        colType.setCellValueFactory(new PropertyValueFactory<Sponsor, String>("Type_s"));
       colTotal.setCellValueFactory(new PropertyValueFactory<Sponsor, Integer>("Total"));
     
       

        CRUDsponsor cds = new CRUDsponsor();
        listS = cds.showSponsor();
        tabSponsor.setItems(listS);
    }

    @FXML
   private void pdfs() 
          throws Exception{
        try {
              
            
            
            
            
            
            
           Class.forName("com.mysql.jdbc.Driver");
               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "root", "");
  Statement stmt = con.createStatement();
                    /* Define the SQL query */
                    ResultSet query_set = stmt.executeQuery("SELECT *From sponsor where CodeTVA = 6");
                    /* Step-2: Initialize PDF documents - logical objects */
                    Document my_pdf_report = new Document();
                     String file_name = ("Recette.pdf");  
                    PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file_name));
               
                    my_pdf_report.open();    
                     Paragraph paraHeader = new Paragraph("Sociéte: Wiن ?\n"+
"Adresse Rue Ampere El Ghazela\n"+
"Téléphone :70 685 685 ");
                
                my_pdf_report.add(paraHeader);
                    Image LogoA3MELTALLA = Image.getInstance("C:/Users/Ahmed Rai/Desktop/project/src/projet/srcs/logo.png");
                   
                LogoA3MELTALLA.setAlignment(Element.ALIGN_CENTER);
                my_pdf_report.add(LogoA3MELTALLA); 
                
                 Image qrcode = Image.getInstance("C:/Users/Ahmed Rai/Desktop/qr.png");
                qrcode.setAlignment(Element.ALIGN_BOTTOM);
                my_pdf_report.add(qrcode); 
                
               
                
                
                
                /* 'Sociéte: Wiن ?\n'+
'Adresse Rue Ampere El Ghazela\n'+
'Téléphone :70 685 685 ';*/
                
                Paragraph paraHeader1 = new Paragraph("\n\n  Mr Ahmed Votre recette est comme suit :\n\n");
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
                                    String Type_s=query_set.getString("Type_s");
                                    table_cell=new PdfPCell(new Phrase(Type_s));
                                    my_report_table.addCell(table_cell);
                                    String Total=query_set.getString("Total");
                                    table_cell=new PdfPCell(new Phrase(Total));
                                    my_report_table.addCell(table_cell);
                                    }
                    /* Attach report table to PDF */
                    my_pdf_report.add(my_report_table); 
                    Paragraph parabottom = new Paragraph("\n\n Signature \n\n");
                parabottom.setAlignment(Element.ALIGN_MIDDLE);
                my_pdf_report.add(parabottom);
                    my_pdf_report.close();

                    /* Close all DB related objects */
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
}
}
  

