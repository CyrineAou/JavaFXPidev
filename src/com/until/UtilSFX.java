package com.until;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.global.alerts.Alerts;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static javafx.application.Platform.runLater;

public class UtilSFX {

    static UtilSFX INSTANCE = null;

    public UtilSFX() {
    }

    public static UtilSFX getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UtilSFX();
        }
        return INSTANCE;
    }

    public static final String ICON_ACCEPT_LOC = "/application/ui/icons/Accept-icon.png";
    public static final String ICON_IMAGE_LOC = "/resources/picon.png";
    public static final String MAIL_CONTENT_LOC = "/resources/mail_content.html";
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private static Stage STAGE;

    public void requestFocus(Node node) {
        runLater(() -> {
            if (!node.isFocused()) {
                node.requestFocus();
                UtilSFX.this.requestFocus(node);
            }
        });
    }

    private void closeRequest(Stage stage) {
        stage.setOnCloseRequest(e -> {
            e.consume();
            System.out.println("main stage is requesred ...");
            if (new Alerts().exitDialog()) {
                System.out.println("closed ...");
                stage.close();
            }
        });
    }

    void setImageToImageView() {
        new ImageView().setImage(new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/resources/images/barcode.png"))));
    }

    /**
     * @param imageView
     * @param url
     */
    public void setImageToImageView(ImageView imageView, String url) {
        //String path = "/resources/images/barcode.png";
        imageView.setImage(new Image(getClass().getResourceAsStream(url)));
    }

    public static String[] setValue(String... ss) {
        String[] arr = null;
        int i = 0;
        for (String s : ss) {
            assert arr != null;
            arr[i] = s;
            i++;
        }
        return null;
    }

    //----------------------------
    //----------------------------
    private void onDragDroppedImageView(DragEvent event) throws FileNotFoundException, MalformedURLException {
        List<File> files = event.getDragboard().getFiles();
    }

    private void onDragOverImageView(DragEvent event) {
        // this is for accept file
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    public static String formatDateTimeString(Date date) {
        return DATE_TIME_FORMAT.format(date);
    }

    public static String formatDateTimeString(Long time) {
        return DATE_TIME_FORMAT.format(new Date(time));
    }

    public static String getDateString(Date date) {
        return DATE_FORMAT.format(date);
    }

    /* test the value is number or text ------------------*/
    //-----------------------------------------//
    public static void setStageIcon(String path) {
        new Stage().getIcons().add(new Image(path));// adding icon 
    }

    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(IconsSFX.ICON_IMAGE_LOC));
    }

//------ Get Stage -------------//
    public Stage getStage(Label text) {
        return (Stage) text.getScene().getWindow();
    }

    public Stage getStage(JFXButton button) {
        return (Stage) button.getScene().getWindow();
    }

    public Stage getStage(Button button) {
        return (Stage) button.getScene().getWindow();
    }

    public Stage getStage(ImageView imageView) {
        return (Stage) imageView.getScene().getWindow();
    }

    public Stage getSTAGE() {
        return STAGE;
    }

    public void setSTAGE(Stage STAGE) {
        UtilSFX.STAGE = STAGE;
    }

    //-----Close stage -------------//
    public void closeStage(Button button) {
        getStage(button).close();
    }

    public void closeStage(JFXButton button) {
        getStage(button).close();
    }

    public void closeStage(Label text) {
        getStage(text).close();
    }

    //------------------ minimizeStage -------------//
    public void minimizeStage(Label text) {
        getStage(text).setIconified(true);
    }

    public void minimizeStage(Button button) {
        getStage(button).setIconified(true);
    }

    public void minimizeStage(JFXButton button) {
        getStage(button).setIconified(true);
    }

    //------------------ Anchore -------------//
    public void setFXMLLoaderToAnchore(String fxml, AnchorPane anchorPane) {
        try {
            Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
            setAnchore(newRoot, anchorPane);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void setAnchore(Parent root, AnchorPane anchorPane) {
        //text.setText("");
        if (!anchorPane.getChildren().isEmpty()) {
            anchorPane.getChildren().clear();
        }
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        
        
        anchorPane.getChildren().add(root);

    }

    public void setFXMLLoaderToAnchore(Parent root, AnchorPane anchorPane) {
        setAnchore(root, anchorPane);
    }

    //------------------ Anchore -------------//
    public void removeAllRowsinTable(TableView tableView) {
        tableView.getItems().forEach((_item) -> tableView.getItems().clear());
    }

    public void setGraphicImage(Button button, String url) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(url)));
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
    }

    public void setGraphicImage(String url) {
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(url))));

    }

    public static void ClearTextField(JFXTextField[] textField) {
        for (JFXTextField txt : textField) {
            txt.clear();
        }
    }

    public static void ClearTextField(TextField[] textField) {
        for (TextField txt : textField) {
            txt.clear();
        }
    }

    public static void ClearTextFields(JFXTextField... tf) {
        for (JFXTextField txt : tf) {
            txt.clear();
        }
    }

}
