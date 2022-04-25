package com.ui.logements;

import com.database.DatabaseHandler;
import com.global.alerts.Alerts;
import com.global.alerts.SFXNotifications;
import com.until.copyImage.CopyImage;
import java.io.File;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class AddLogementController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfAddress;
    @FXML
    private ComboBox<String> comboboxEquipement;
    @FXML
    private ComboBox<String> comboboxActivation;
    @FXML
    private TextField tfImageFile;
    @FXML
    private Button buttonBrowse;
    @FXML
    private Button buttonAdd;

    ObservableList<String> activationList = FXCollections.observableArrayList();
    ObservableList<String> equipementList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setInserted(false);

        activationList.addAll(Arrays.asList("Active", "Disactive"));
        comboboxActivation.setItems(activationList);

        comboboxActivation.getSelectionModel().selectFirst();

        comboboxActivation.setOnAction((ActionEvent event) -> {
            System.out.println("You selecet User type "
                    + comboboxActivation.getSelectionModel().getSelectedItem());

        });

        // add
        equipementList.addAll(getAllEquipement());
        //set
        comboboxEquipement.setItems(equipementList);

        comboboxEquipement.getSelectionModel().selectFirst();

        comboboxEquipement.setOnAction((ActionEvent event) -> {
            System.out.println("You selecet User type "
                    + comboboxEquipement.getSelectionModel().getSelectedItem());

        });

        buttonAdd.setOnAction((ActionEvent event) -> {
            System.out.println("You selecet User type "
                    + comboboxEquipement.getSelectionModel().getSelectedItem());
            insert();

        });
        buttonBrowse.setOnAction((ActionEvent event) -> {
            setImageToImageView(imageView);
            new CopyImage().copy(new File(tfImageFile.getText()), "");

        });
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

    public void insert() {
        try {
            String sql = "INSERT INTO logement(hote_id,titre,description,addresse,filename,created_at,\n"
                    + "updated_at, qr_file_name,is_active)VALUES(?,?,?,?,?,?,?,?,?);";
            PreparedStatement stmnt = DatabaseHandler.getInstance()
                    .getConnection().prepareStatement(sql);

            stmnt.setInt(1, 1);
            stmnt.setString(2, tfTitre.getText());
            stmnt.setString(3, tfDescription.getText());
            stmnt.setString(4, tfAddress.getText());
            stmnt.setString(5, getFileName());
            stmnt.setString(6, LocalDateTime.now().toString());
            stmnt.setString(7, LocalDateTime.now().toString());
            stmnt.setString(8, "qr_file_name");
            stmnt.setBoolean(9, getActivation());

            if (stmnt.executeUpdate() == 1) {
                if (insert2()) {
                    new SFXNotifications().success("Logement", "data insert ....");
                    setInserted(true);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            setInserted(false);
        }
    }

    public boolean insert2() {
        boolean flag = false;
        try {
            String sql = "INSERT INTO logement_equipement(logement_id,equipement_id)VALUES(?,?);";
            PreparedStatement stmnt = DatabaseHandler.getInstance()
                    .getConnection().prepareStatement(sql);

            stmnt.setInt(1, getLogement_Id());
            stmnt.setInt(2, getEquipementID());

            if (stmnt.executeUpdate() == 1) {
                flag = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            setInserted(false);
        }
        return flag;
    }

    public int getLogement_Id() {
        int id = 0;
        try {
            Connection conn = DatabaseHandler.getInstance()
                    .getConnection();
            ResultSet rs;
            try (PreparedStatement pst = conn.prepareStatement("SELECT MAX(id) as id from logement;")) {
                rs = pst.executeQuery();
                while (rs.next()) {
                    id = rs.getInt("id");
                }
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());

        }
        return id;
    }

    private static boolean inserted;

    public static boolean isInserted() {
        return inserted;
    }

    public static void setInserted(boolean inserted) {
        AddLogementController.inserted = inserted;
    }

    public int getEquipementID() {
        return equipementIdList.get(comboboxEquipement
                .getSelectionModel().getSelectedIndex());
    }

    public String getFileName() {
        return new File(tfImageFile.getText()).getName();
    }

    public boolean getActivation() {
        return comboboxActivation.getSelectionModel()
                .getSelectedIndex() == 0;
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

}
