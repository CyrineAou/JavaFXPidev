/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.WinDeskTop.gui;

import edu.WinDeskTop.Services.ServiceAnnonce;
import edu.WinDeskTop.entities.Annonce;
import java.io.File;

import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class AjoutAnnonceFXMLController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfType;
    @FXML
    private Button btnajouter;
    @FXML
    private CheckBox cbdispo;
    @FXML
    private TextField tfdisc;
    @FXML
    private TextField tfprix;
    @FXML
    private DatePicker tfdate;
    private TextField tfimege;
    @FXML
    private Button btnlisteA;
    @FXML
    private TextField Impath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void add(ActionEvent event) throws IOException {
         if (tfTitre.getText().isEmpty() || tfType.getText().isEmpty() ||
                 tfprix.getText().isEmpty() || tfdisc.getText().isEmpty()) 
                 {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est produite lors de l'ajout.");
            alert.setContentText("Vous devez remplir tous les champs!");
            
            
            alert.showAndWait();        
            return;
        }
         if (!((tfprix.getText().matches("[0-9]+"))))
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est produite lors de l'ajout.");
            alert.setContentText("Le champ prix doit être des chiffres.");
            alert.showAndWait();        
            return;
        }
         if (!((tfTitre.getText().matches("[a-z]+")) && (tfType.getText().matches("[a-z]+"))))
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est produite lors de l'ajout.");
            alert.setContentText("Les champs titre et type  doivent être des lettres.");
            alert.showAndWait();        
            return;
        }
           
String date = tfdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                
     Annonce A = new Annonce(tfTitre.getText(), tfprix.DEFAULT_PREF_COLUMN_COUNT,cbdispo.isSelected(),tfdisc.getText(),tfType.getText(),date,Impath.getText());
         ServiceAnnonce sp = new ServiceAnnonce();
         sp.ajouter(A);
        JOptionPane.showMessageDialog(null, "Annonce ajoutée !");
        
          Notifications notifications=Notifications.create();
       // notifications.graphic(new ImageView(image));
        notifications.text("Votre anonce en cours de traitemt ");
        notifications.title("Success Message");
        notifications.hideAfter(Duration.seconds(4));
        notifications.darkStyle();
        /*notifications.darkStyle();*/
     /*   notifications.position(Pos.BOTTOM_CENTER);*/
        notifications.show();
    }
    

    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeAnnonceFXML.fxml"));
        Parent root = loader.load(); 
        tfTitre.getScene().setRoot(root);
        AjoutAnnonceFXMLController apc = loader.getController();
    }

    public TextField getTfTitre() {
        return tfTitre;
    }

    public TextField getTfType() {
        return tfType;
    }

    public Button getBtnajouter() {
        return btnajouter;
    }

    public CheckBox getCbdispo() {
        return cbdispo;
    }

    public TextField getTfdisc() {
        return tfdisc;
    }

    public TextField getTfprix() {
        return tfprix;
    }

    public DatePicker getTfdate() {
        return tfdate;
    }

    public TextField getTfimege() {
        return tfimege;
    }

    public Button getBtnlisteA() {
        return btnlisteA;
    }

    public void setTfTitre(TextField tfTitre) {
        this.tfTitre = tfTitre;
    }

    public void setTfType(TextField tfType) {
        this.tfType = tfType;
    }

    public void setBtnajouter(Button btnajouter) {
        this.btnajouter = btnajouter;
    }

    public void setCbdispo(CheckBox cbdispo) {
        this.cbdispo = cbdispo;
    }

    public void setTfdisc(TextField tfdisc) {
        this.tfdisc = tfdisc;
    }

    public void setTfprix(TextField tfprix) {
        this.tfprix = tfprix;
    }

    public void setTfdate(DatePicker tfdate) {
        this.tfdate = tfdate;
    }

    public void setTfimege(TextField tfimege) {
        this.tfimege = tfimege;
    }

    public void setBtnlisteA(Button btnlisteA) {
        this.btnlisteA = btnlisteA;
    }

    @FXML
    private void backto(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeAnnonceFXML.fxml"));
        Parent root = loader.load(); 
        tfTitre.getScene().setRoot(root);
        ListeAnnonceFXMLController apc = loader.getController();
    }

    @FXML
    private void addImage(ActionEvent event) throws IOException {
JFileChooser chooser = new JFileChooser();
chooser.showOpenDialog(null);
File f = chooser.getSelectedFile();
String filename = f.getAbsolutePath();
DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyyMMdd");
LocalDate now =LocalDate.now();
String fileNewName =dtf.format(now);
String copied ="C:\\Users\\cyrine\\OneDrive\\Bureau\\MaterialScreensUIKit\\WinDeskTop\\src\\edu\\WinDeskTop\\Images"+fileNewName+".jpg";
Path src =Paths.get(filename);
Path dst =Paths.get(copied);
Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);

Impath.setText(fileNewName+".jpg");


        }

    
    
}
