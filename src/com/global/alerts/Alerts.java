package com.global.alerts;

import com.module.media.img.MediaImage;
import com.until.IconsSFX;
import java.util.Objects;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

public class Alerts {

    static Alerts INSTANCE = null;

    public Alerts() {
    }

    public static Alerts getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Alerts();
        }
        return INSTANCE;
    }

    //############################################################################
    public boolean updateAlert() {
        return contentUpdateAlert("Do you went update  ");
    }

    public boolean updateAlert(String message) {
        return contentUpdateAlert(message);
    }

    public boolean contentUpdateAlert(String message) {
        ButtonType update = new ButtonType("Update",
                ButtonBar.ButtonData.OK_DONE);
        Alert a = new Alert(Alert.AlertType.NONE, "",
                new ButtonType("Cancle",
                        ButtonBar.ButtonData.CANCEL_CLOSE), update);
        setIcon(a);
        a.setTitle("Warning");
        a.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass()
                .getResource(MediaImage.WARNING)).toExternalForm())));
        a.setHeaderText(message);
        a.getDialogPane().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        return a.showAndWait().get() == update;
    }
////############################################################################

    public boolean deleteAlert() {
        return contentDeleteAlert("do you went delete ");

    }

    public boolean deleteAlert(String message) {
        return contentDeleteAlert(message);

    }

    private boolean contentDeleteAlert(String message) {
        ButtonType delete = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
        Alert a = new Alert(Alert.AlertType.NONE, "",
                new ButtonType("Cancle", ButtonBar.ButtonData.CANCEL_CLOSE),
                delete);
        setIcon(a);
        a.setTitle("Warning");
        a.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass()
                .getResource(MediaImage.TRASH)).toExternalForm())));
        a.setHeaderText(message);
        a.getDialogPane().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        return a.showAndWait().get() == delete;
    }
////############################################################################

    public void successAlert(String message) {
        showDialog(message, "App", MediaImage.SUCCESS);
    }

    public void infoAlert(String message) {
        showDialog(message, "App", MediaImage.INFO);
    }

    public void errorAlert(String message) {
        showDialog(message, "App", MediaImage.ERROR);
    }

    public void warningAlert(String message) {
        showDialog(message, "App", MediaImage.WARNING);
    }

    void showDialog(String message, String title, String type) {
        ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        Alert a = new Alert(Alert.AlertType.NONE, "", ok);
        setIcon(a);
        a.setTitle(title);
        a.setHeaderText(message);
        a.setGraphic(new ImageView(new Image(Objects.
                requireNonNull(getClass().getResource(type))
                .toExternalForm())));
        a.showAndWait();
    }

    public boolean exitDialog() {
        return exit("Do you want exit app", MediaImage.WARNING);
    }

    public boolean exit(String title, String resource) {
        ButtonType exit = new ButtonType("Exit", ButtonBar.ButtonData.OK_DONE);
        Alert a = new Alert(
                Alert.AlertType.NONE, "",
                new ButtonType("Cancle", ButtonBar.ButtonData.CANCEL_CLOSE),
                exit);
        setIcon(a);
        a.setTitle("Warning");
        a.setHeaderText(title);
        a.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass()
                .getResource(resource))
                .toExternalForm())));
        return a.showAndWait().get() == exit;
    }

    private void setIcon(Alert dialog) {
        ((Stage) dialog.getDialogPane().getScene().getWindow())
                .getIcons().add(new Image(Objects.requireNonNull(this.getClass()
                        .getResource(IconsSFX.ICON_IMAGE_LOC)).toString()));
    }

    public boolean activationAlert(String message) {
        ButtonType Active = new ButtonType("Active",
                ButtonBar.ButtonData.OK_DONE);
        ButtonType Disactive = new ButtonType("Disactive",
                ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert a = new Alert(Alert.AlertType.NONE, "", Disactive, Active);
        setIcon(a);
        a.setTitle("Activation");
        a.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass()
                .getResource(MediaImage.WARNING)).toExternalForm())));
        a.setHeaderText(message);
        a.getDialogPane().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        return a.showAndWait().get() == Active;
    }
}
