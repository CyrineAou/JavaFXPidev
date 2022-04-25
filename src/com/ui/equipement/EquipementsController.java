package com.ui.equipement;

import com.database.DataHelper;
import com.database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import com.global.alerts.Alerts;
import com.global.alerts.SFXNotifications;
import com.until.FxmlNameSFX;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class EquipementsController implements Initializable {

    @FXML
    private TableView<Equipement> table;
    @FXML
    private TableColumn<Equipement, Integer> col_id;
    @FXML
    private TableColumn<Equipement, String> col_nom;
    @FXML
    private TableColumn<Equipement, Void> btn_edite;

    private final ObservableList<Equipement> eqipementList = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnReload;
    @FXML
    private JFXButton btnInsert;
    @FXML
    private TableColumn<Equipement, Void> btn_delete;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        col_id.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(
                new PropertyValueFactory<>("nom"));

        addButtonToTable();

        showTable();

        btnReload.setOnAction((ActionEvent ev) -> {
            showTable();
        });

        btnInsert.setOnAction((ActionEvent ev) -> {
            try {
                Parent root = new FXMLLoader(getClass().getResource(FxmlNameSFX.addEquipementFxml)).load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Add Equipements");
                stage.show();
                stage.setOnHidden((WindowEvent event) -> {
                    // if add Equipement  is added 
                    if (AddEquipementController.isAdded()) {
                        // show add item in table
                        showTable();
                    }
                });
            } catch (IOException e) {
                System.out.println(e);
            }
        });

    }

    private void addButtonToTable() {

        Callback<TableColumn<Equipement, Void>, TableCell<Equipement, Void>> update
                = (final TableColumn<Equipement, Void> param) -> new TableCell<Equipement, Void>() {
            private final Button btn = new Button(" ");

            {
                //-danger  = #B9271B  -primary =#121E31
                FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.EDIT);
                icon.setSize("24px");
                icon.setStyle("-fx-fill: white;");
                btn.setGraphic(icon);
                btn.setStyle("-fx-background-color : #FFAA00;;"
                        + "-fx-effect: dropshadow(three-pass-box,rgba(0,0,0,0.1),10.0,0.0,0.0,10.0); ");
                btn.setText("Editer");
                btn.setOnAction((ActionEvent event) -> {

                    Equipement equipement = getTableView().getItems().get(getIndex());
                    System.out.println("selectedData: " + equipement);
                    System.out.println("id : " + equipement.getId());
                    showEquipement(equipement);

                });
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        };
        Callback<TableColumn<Equipement, Void>, TableCell<Equipement, Void>> delete
                = (final TableColumn<Equipement, Void> param) -> new TableCell<Equipement, Void>() {
            private final Button btn = new Button(" ");

            {
                //-danger  = #B9271B  -primary =#121E31
                FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                icon.setSize("24px");
                icon.setStyle("-fx-fill: white;");
                btn.setGraphic(icon);
                btn.setStyle("-fx-background-color : #FF5148;;"
                        + "-fx-effect: dropshadow(three-pass-box,rgba(0,0,0,0.1),10.0,0.0,0.0,10.0); ");
                btn.setText("Supprimer");
                btn.setOnAction((ActionEvent event) -> {

                    Equipement data1 = getTableView().getItems().get(getIndex());
                    System.out.println("selectedData: " + data1);
                    System.out.println("id : " + data1.getId());
                    deleteLogement(data1);

                });
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        };
        btn_delete.setCellFactory(delete);
        btn_edite.setCellFactory(update);

    }

    // For delete Equipement table 
    public void deleteLogement(Equipement e) {
        // do you want delete Equipement
        if (new Alerts().deleteAlert()) {
            // sql command for delete data from equipement
            if (new DataHelper().delete("equipement", e.getId())) {
                    new SFXNotifications().success("equipement", "data detelete "+e.getNom());
                   
                // reload data 
                loadItem(query);
            }
        }
    }
    // sql commad for get  all equipement
    String query = "SELECT * FROM equipement ;";

    // search from equipement table by nom 
    String searchQuery(String col, String search) {
        return "SELECT * FROM equipement "
                + " WHERE " + col + " LIKE '%" + search + "%'  "
                + " order by nom asc; ";
    }

    // clean eqipementList and reload eqipementList
    void showTable() {
        if (!eqipementList.isEmpty()) {
            eqipementList.clear();
        }
        // reload table 
        loadItem(query);
    }

    // for print all data in table by console 
    void printTable() {
        System.out.println("---------------------------------------------------");
        table.getItems().forEach((selectedItem) -> System.out.println(selectedItem.toString()));
        System.out.println("---------------------------------------------------");
    }

    // load all data from equipement to ObservableList eqipementList list 
    public void loadItem(String query) {
        System.out.println(query);
        if (!eqipementList.isEmpty()) {
            eqipementList.clear();
        }
        try {
            Connection conn = DatabaseHandler.getInstance().getConnection();
            ResultSet rs;
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                rs = pst.executeQuery();
                while (rs.next()) {
                    Equipement io = new Equipement();
                    io.setId(rs.getInt("id"));
                    io.setNom(rs.getString("nom"));

                    eqipementList.add(io);
                }
                System.out.println(eqipementList);
                // set all data to table 
                table.setItems(eqipementList);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void showEquipement(Equipement equipement) {
        try {
            // load editEquipementFxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlNameSFX.editEquipementFxml));
            Parent rot = loader.load();
            
            ((EditEquipementController) loader.getController())
                    .setData(equipement);
            Stage s = new Stage();
            s.setScene(new Scene(rot));
            s.setTitle("Edite");
            s.show();
            // after stage cloded 
            s.setOnHiding((e) -> {
                System.out.println("closed ");
                if (EditEquipementController.isUpdate()) {
                    System.out.println("is Update  show table ");
                    showTable();
                }
            });
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
