/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.WinDeskTop.gui;

import com.sun.xml.internal.ws.api.pipe.Engine;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class WebViewController implements Initializable {

    @FXML
    private WebView webview;
    @FXML
    private TextField tfserrch;
    @FXML
    private Label lbwelcome;
    private WebEngine webengine;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        animLABEL();
    }    
    public void loadurl(){
        webengine = webview.getEngine();
        webengine.load("https://www.airbnb.fr/");
        webengine.getLoadWorker().stateProperty().addListener((obs,oldValue,newValue)->{
            if(newValue==Worker.State.SUCCEEDED){
                System.out.println("Page a ete charger");
            }else if(newValue==Worker.State.FAILED){
                System.out.println("Page echoue");
                
            }
        });
    }

    @FXML
    private void loadpage(ActionEvent event) {
    }

    @FXML
    private void refrech(ActionEvent event) {
        webengine.reload();
    }

    @FXML
    private void zoomin(ActionEvent event) {
        webview.setZoom(webview.getZoom() + 0.10);
    }

    @FXML
    private void zoomout(ActionEvent event) {
           webview.setZoom(webview.getZoom() - 0.10);
    }

    @FXML
    private void espaceuser(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeAnnonceFXML.fxml"));
        Parent root = loader.load(); 
        tfserrch.getScene().setRoot(root);
        ListeAnnonceFXMLController apc = loader.getController();
    }
    
    
    private void animLABEL() {
       FadeTransition fadeTransition = new FadeTransition(javafx.util.Duration.seconds(1), lbwelcome);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();
    }

    @FXML
    private void textentrykey(KeyEvent event) {
        if (event.getCode()==KeyCode.ENTER){
            loadurl();
        }
    }
}
