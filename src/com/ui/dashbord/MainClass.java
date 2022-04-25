package com.ui.dashbord;

import com.global.alerts.Alerts;
import com.until.FxmlNameSFX;
import com.until.UtilSFX;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainClass extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(FxmlNameSFX.dashbordExml));

        Scene scene = new Scene(root);
        stage.setTitle("Dashbord");
        stage.setScene(scene);

        stage.show();
        UtilSFX.setStageIcon(stage);
        stage.centerOnScreen();
        //-------- close Request
        stage.setOnCloseRequest((WindowEvent e) -> {
            // main stage is requesred ...
            e.consume();
            System.out.println("form is is requesred ...");
            if (new Alerts().exitDialog()) {
                //closed .......
                System.out.println("set Stage Property closed ...");
                stage.close();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);

    }

}
