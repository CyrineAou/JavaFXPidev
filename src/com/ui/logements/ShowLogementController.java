package com.ui.logements;

import com.model.LogementsObj;
import com.database.DatabaseHandler;
import com.global.alerts.Alerts;
import com.global.alerts.SFXNotifications;
import com.until.FxmlNameSFX;
import com.until.copyImage.CopyImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ShowLogementController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfAddress;
    @FXML
    private ImageView imageView;
    @FXML
    private ComboBox<String> comboboxEquipement;
    @FXML
    private TextField tfImageFile;
    @FXML
    private Button buttonBrowse;
    @FXML
    private ComboBox<String> comboboxActivation;
    @FXML
    private Button buttonUpdate;

    private static boolean update;

    public static boolean isUpdate() {
        return update;
    }
    @FXML
    private Button buttonQrCode;

    public void setUpdate(boolean update) {
        ShowLogementController.update = update;
    }

    LogementsObj logementsObj;

    public void visibaleUpdateButton() {
        System.out.println("you click visibaleUpdateButton");
        buttonUpdate.setVisible(false);
    }

    public void setData(LogementsObj lo) {
        System.out.println(lo);
        logementsObj = lo;
        tfAddress.setText(lo.getAddresse());

        tfTitre.setText(lo.getTitre());

        tfDescription.setText(lo.getDescription());

        comboboxEquipement.getSelectionModel()
                .select(lo.getEquipement());

        comboboxActivation.getSelectionModel()
                .select(lo.getIs_active());
        tfImageFile.setText("data/produce/img/" + lo.getFilePath());
        imageView.setImage(lo.getFilename().getImage());

        initializeByUser();
        buttonUpdate.setVisible(true);

    }
    ObservableList<String> activationList = FXCollections.observableArrayList();
    ObservableList<String> equipementList = FXCollections.observableArrayList();

    public void initializeByUser() {
        setUpdate(false);

        activationList.addAll(Arrays.asList("Active", "Disactive"));
        comboboxActivation.setItems(activationList);

        // add
        equipementList.addAll(getAllEquipement());
        //set
        comboboxEquipement.setItems(equipementList);

    }

    public int getActivation() {
        if (comboboxActivation.getSelectionModel().getSelectedIndex() == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public void update1(LogementsObj logement) {
        System.out.println("data to update " + logement);
        try {
            String sql = "UPDATE pi.logement SET \n"
                    + "hote_id =  ? , titre =  ? , description =  ? , addresse =  ? , filename =  ? , \n"
                    + " updated_at =  ? , qr_file_name = ? , \n"
                    + "is_active = ? WHERE id = ? ;";
            System.out.println(sql);
            PreparedStatement stmnt = DatabaseHandler.getInstance().getConnection().prepareStatement(sql);
            stmnt.setInt(1, logement.getHote_id());
            stmnt.setString(2, logement.getTitre());
            stmnt.setString(3, logement.getDescription());
            stmnt.setString(4, logement.getAddresse());
            stmnt.setString(5, logement.getFilePath());
            stmnt.setString(6, logement.getUpdated_at());
            stmnt.setString(7, logement.getQrPath());
            stmnt.setInt(8, logement.getIs_active());
            stmnt.setInt(9, logement.getId());
            int executeUpdate = stmnt.executeUpdate();
            if (executeUpdate == 1) {
                System.out.println("data updated");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update2() {
        try {
            String sql = "UPDATE pi.logement_equipement SET equipement_id = ? WHERE logement_id = ? ;";
            System.out.println(sql);
            PreparedStatement stmnt = DatabaseHandler.getInstance().getConnection().prepareStatement(sql);
            stmnt.setInt(1, getEquipementID());
            stmnt.setInt(2, logementsObj.getId());
            stmnt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateLogement() {
        if (!(addressValidation() || descriptionValidation() || imageFileValidation() || titreValidation())) {
            new Alerts().warningAlert("complet all fields");
        } else {
            boolean updateAlert = new Alerts().updateAlert("Do you went update " + logementsObj.getEquipement());
            if (updateAlert) {

                LogementsObj lo = new LogementsObj();
                lo.setId(logementsObj.getId());
                lo.setHote_id(logementsObj.getHote_id());
                lo.setAddresse(tfAddress.getText());
                lo.setDescription(tfDescription.getText());
                lo.setTitre(tfTitre.getText());
                lo.setFilePath(getFileName());

                lo.setUpdated_at(LocalDateTime.now().toString());
                lo.setCreated_at(LocalDateTime.now().toString());

                lo.setIs_active(getActivation());

                update1(lo);
                update2();
                new SFXNotifications().success("Logement", "DAta updated ....");
                setUpdate(true);

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        buttonUpdate.setOnAction((event) -> {
            updateLogement();
        });
        buttonBrowse.setOnAction((event) -> {
            setImageToImageView(imageView);
            new CopyImage().copy(new File(tfImageFile.getText()), "");
        });

        comboboxActivation.setOnAction((ActionEvent event) -> {
            System.out.println("You selecet  Activation type " + getActivation());

        });

        comboboxEquipement.setOnAction((ActionEvent event) -> {
            System.out.println("You selecet  Equipement type "
                    + comboboxEquipement.getSelectionModel().getSelectedItem());

        });

        buttonBrowse.setOnAction((ActionEvent event) -> {
            setImageToImageView(imageView);
            new CopyImage().copy(new File(tfImageFile.getText()), "");
            //insert();
        });
        buttonQrCode.setOnAction((ActionEvent event) -> {
            ShowQrcode(logementsObj);
        });
    }
    // browse image from pc and set image to image view

    public ArrayList<String> getAllEquipement() {
        ArrayList<String> list = new ArrayList<>();
        if (!equipementIdList.isEmpty()) {
            equipementIdList.clear();
        }
        try {
            Connection conn = DatabaseHandler.getInstance()
                    .getConnection();
            ResultSet rs;
            try (PreparedStatement pst = conn.prepareStatement(
                    "SELECT id , nom FROM equipement "
                    + "ORDER BY equipement.nom ASC;")) {
                rs = pst.executeQuery();
                while (rs.next()) {
                    list.add(rs.getString("nom"));
                    equipementIdList.add(rs.getInt("id"));

                }
            }
            rs.close();
            System.out.println(list);
            return list;
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    // browse image from pc and set image to image view
    public void setImageToImageView(ImageView imageView) {
        //   String imgPath;
        try {
            FileChooser fileChoosers = new FileChooser();
            fileChoosers.setTitle("Open image");
            FileChooser.ExtensionFilter extFilter
                    = new FileChooser.ExtensionFilter("image extantion",
                            "*.png",
                            "*.jpg",
                            "*..jpeg",
                            "*.jfif",
                            "*.pjpeg",
                            "*..pjp"
                    );
            fileChoosers.getExtensionFilters().add(extFilter);
            File imgFile = fileChoosers.showOpenDialog(imageView.getScene().getWindow());
            if (imgFile != null) {
                tfImageFile.setText(imgFile.getAbsolutePath());
                File file = new File(tfImageFile.getText());
                Image ig = new Image(file.toURI().toString());
                imageView.setImage(ig);
                System.out.println("Successfully set image to image View ...");
            } else {
                // imageView.setImage(new Image("/application/ui/icons/icons8_user_127px.png"));
                System.out.println("Cancel file is [Null] ...");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
    public ArrayList<Integer> equipementIdList = new ArrayList<>();

    public int getEquipementID() {
        return equipementIdList.get(comboboxEquipement
                .getSelectionModel().getSelectedIndex());
    }

    public String getFileName() {
        return new File(tfImageFile.getText()).getName();
    }

    boolean titreValidation() {
        return !tfTitre.getText().isEmpty();

    }

    boolean descriptionValidation() {
        return !tfDescription.getText().isEmpty();

    }

    boolean addressValidation() {
        return !tfAddress.getText().isEmpty();
    }

    boolean imageFileValidation() {
        return !tfImageFile.getText().isEmpty();
    }

    public void ShowQrcode(LogementsObj logementsObj) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlNameSFX.showQrcodeFxml));
            Parent rot = loader.load();
            ShowQrcodeController c = (ShowQrcodeController) loader.getController();
            c.setData(logementsObj);

            Stage s = new Stage();
            s.setScene(new Scene(rot));
            s.setTitle("Qrcode");
            s.show();
            s.setOnHiding((e) -> {
                System.out.println("stage closed ");
            });
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
