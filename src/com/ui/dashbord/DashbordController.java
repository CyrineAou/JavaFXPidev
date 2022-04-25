package com.ui.dashbord;

import com.until.FxmlNameSFX;
import com.until.UtilSFX;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class DashbordController implements Initializable {

    @FXML
    private AnchorPane ancher;
    @FXML
    private Button btn_logements;
    @FXML
    private Button btn_Eqipement;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UtilSFX.getInstance().setFXMLLoaderToAnchore(FxmlNameSFX.logementFxml, ancher);
        btn_Eqipement.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UtilSFX.getInstance().setFXMLLoaderToAnchore(FxmlNameSFX.EquipementsFxml, ancher);
            }
        });

        btn_logements.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UtilSFX.getInstance().setFXMLLoaderToAnchore(FxmlNameSFX.logementFxml, ancher);
            }
        });
    }

}
