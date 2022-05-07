/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.WinDeskTop.gui;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class StatisticFXMLController implements Initializable {

    @FXML
    private StackedBarChart<?, ?> linechart;
    @FXML
    private PieChart piechart;
    @FXML
    private Label Alb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      inilineChart();
      inipieChart();
      animLABEL();
      
        
    }    

    private void inilineChart() {
          XYChart.Series series = new XYChart.Series();
         // String req ="select date as date,COUNT (*)as count from annonce groupe by date";
         // Statement st =cnx.createStatement();
       // ResultSet rs =st.executeQuery(req);
        series.getData().add (new XYChart.Data("lun",0));
        series.getData().add (new XYChart.Data("Mar",15));
        series.getData().add (new XYChart.Data("Merc",10));
        series.getData().add (new XYChart.Data("Jeu",6));
        series.getData().add (new XYChart.Data("Ven",9));
        series.getData().add (new XYChart.Data("Sam",15));
        series.getData().add (new XYChart.Data("Dim",17));
        linechart.getData().addAll(series);
        linechart.lookup(".chart-plot-background").setStyle("-fx-backgroun-color:transpaent;");
  
        
      
    }

    private void inipieChart() {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Maison", 13),
                new PieChart.Data("Appartement", 25),
                new PieChart.Data("Chambte", 10),
                new PieChart.Data("Maison d'hote", 22));
               
     piechart.setData(pieChartData);
    }

    private void animLABEL() {
       FadeTransition fadeTransition = new FadeTransition(javafx.util.Duration.seconds(1), Alb);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();
    }
    
}