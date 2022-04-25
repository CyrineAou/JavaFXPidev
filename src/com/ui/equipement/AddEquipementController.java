
package com.ui.equipement;

import com.database.DataHelper;
import com.global.alerts.Alerts;
import com.global.alerts.SFXNotifications;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class AddEquipementController implements Initializable {
    
    @FXML
    private TextField textFieldItem;
    @FXML
    private Button buttonAdd;
    private static boolean adding;
    
    public static boolean isAdded() {
        return adding;
    }
    
    public static void setAddState(boolean adding) {
        AddEquipementController.adding = adding;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setAddState(false);
        buttonAdd.setOnAction((ActionEvent event) -> {
            if (itemValidation()) {
                boolean insert = new DataHelper().insert("equipement(nom)", textFieldItem.getText());
                if (insert) {
                      new SFXNotifications().success("equipement", "data insert "+textFieldItem.getText());
                    setAddState(true);
                }
            } else {
                new Alerts().warningAlert("text field is empty");
            }
        });
    }
    
    boolean itemValidation() {
        return !textFieldItem.getText().isEmpty();
    }
    
}
