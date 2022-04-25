package com.until;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoaderSFX {

    public LoaderSFX() {
    }
    static LoaderSFX fXLoadFXML = null;
    private double xOffSet = 0;
    private double yOffSet = 0;
    private Stage otherStage;

    public static LoaderSFX getInstance() {
        if (fXLoadFXML == null) {
            fXLoadFXML = new LoaderSFX();
        }
        return fXLoadFXML;
    }

    /**/
    /**
     *
     * @param root
     * @param title show loader to load new stage
     */
    public void showLoader(Parent root, String title) {
        Stage s = new Stage();
        s.setScene(new Scene(root));
        s.initStyle(StageStyle.UNDECORATED);
        setTitle(s, title);
        draging(s, root);
        s.show();
    }

    /**/
    public void openSceneUNDECORATED(String path, String title) {
        try {
            if (otherStage == null) {
                Parent root = new FXMLLoader(getClass().getResource(path)).load();
                otherStage = new Stage();
                otherStage.initStyle(StageStyle.UNDECORATED);
                otherStage.setScene(new Scene(root));

                setTitle(otherStage, title);

                draging(otherStage, root);

                otherStage.show();
            } else if (otherStage.isShowing()) {
                otherStage.toFront();
                boolean isMinimized = otherStage.isIconified();
                if (isMinimized) {
                    otherStage.setIconified(false);
                }
            } else {
                otherStage.show();
            }
        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }

    public void openNewSceneUNDECORATED(String path, String title) {
        try {
            Parent root = new FXMLLoader(getClass().getResource(path)).load();
            Stage s = new Stage();
            s.initStyle(StageStyle.UNDECORATED);
            s.setScene(new Scene(root));
            setTitle(s, title);
            draging(s, root);
            s.show();
        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }

    public void setTitle(Stage stage, String title) {
        stage.setTitle(title);
        UtilSFX.setStageIcon(stage);
    }

    public void draging(Stage stage, Parent root) {
        root.setOnMousePressed((event) -> {
            xOffSet = event.getSceneX();
            yOffSet = event.getSceneY();
        });
        root.setOnMouseDragged((event) -> {
            stage.setX(event.getScreenX() - xOffSet);
            stage.setY(event.getScreenY() - yOffSet);
            stage.setOpacity(1.0f);
        });
        root.setOnDragDone((event) -> stage.setOpacity(1.0f));
        root.setOnMouseReleased((event) -> stage.setOpacity(1.0f));
    }

    public void dragingPro(Stage stage, Parent root, String title) {
        setTitle(stage, title);
        //undecorated
        stage.initStyle(StageStyle.UNDECORATED);
        //draging
        draging(stage, root);

    }

    public void openNewScene(String fxmlPath, String title, boolean maximized) {
        try {
            Parent root = new FXMLLoader(getClass().getResource(fxmlPath)).load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            setMaximized(stage, maximized);
            setTitle(stage, title);
            stage.show();
        } catch (IOException e) {

        }
    }

    public void setMaximized(Stage stage, boolean maximized) {
        if (maximized) {
            stage.setMaximized(true);
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());
        }
    }
}
