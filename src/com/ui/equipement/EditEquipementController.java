package com.ui.equipement;

import com.database.DataHelper;
import com.global.alerts.Alerts;
import com.global.alerts.SFXNotifications;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditEquipementController implements Initializable {

    @FXML
    private TextField textFieldId;
    @FXML
    private TextField textFieldNom;
    @FXML
    private Button btn_update;
    Equipement equipement;

    public void setData(Equipement e) {
        equipement = e;
        System.out.println(e);
        textFieldId.setText(e.getId() + "");
        textFieldNom.setText(e.getNom());
    }
    private static boolean update;

    public static boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        EditEquipementController.update = update;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn_update.setOnAction((e) -> {
            Alerts a = new Alerts();
            boolean updateAlert = a.updateAlert("Do you went update ");
            if (updateAlert) {
                System.out.println("yes");
                if (update(equipement.getId())) {
                     new SFXNotifications().success("equipement", "data update ");
                    setUpdate(true);
                }
            } else {
                System.out.println("no");
                setUpdate(false);
            }
        });
    }

    public boolean update(int id) {
        return new DataHelper().update("equipement", "nom='" + textFieldNom.getText() + "'", "" + id);
    }

}
