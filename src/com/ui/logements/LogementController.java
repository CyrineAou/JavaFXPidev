package com.ui.logements;

import com.model.LogementsObj;
import com.database.DataHelper;
import com.database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import com.global.alerts.Alerts;
import com.model.Activation;
import com.until.FxmlNameSFX;
import com.until.LoaderSFX;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javafx.util.Callback;

public class LogementController implements Initializable {
    
    @FXML
    private TextField textFieldIdSearch;
    @FXML
    private TextField textFieldMomDeLogmement;
    @FXML
    private TableView<LogementsObj> table;
    @FXML
    private TableColumn<LogementsObj, String> col_image;
    @FXML
    private TableColumn<LogementsObj, Integer> col_id;
    @FXML
    private TableColumn<LogementsObj, String> col_titre;
    @FXML
    private TableColumn<LogementsObj, String> col_address;
    @FXML
    private TableColumn<LogementsObj, String> col_description;
    @FXML
    private TableColumn<LogementsObj, String> col_Equpment;
    @FXML
    private TableColumn<LogementsObj, Void> col_btn_activation;
    @FXML
    private TableColumn<LogementsObj, Void> col_btn_show;
    @FXML
    private TableColumn<LogementsObj, Void> col_btn_update;
    @FXML
    private TableColumn<LogementsObj, Void> col_btn_delete;
    @FXML
    private AnchorPane ancher;
    
    private final ObservableList<LogementsObj> logementList = FXCollections.observableArrayList();
    @FXML
    private Button buttonSearchId;
    @FXML
    private Button buttonSearchNom;
    @FXML
    private JFXButton btnReload;
    @FXML
    private JFXButton btnInsert;
    @FXML
    private JFXButton buttonShowChart;
    @FXML
    private FontAwesomeIconView sss;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addButtonToTable();

        // initialize col 
        col_description.setCellValueFactory(
                new PropertyValueFactory<>("description"));
        col_image.setCellValueFactory(
                new PropertyValueFactory<>("filename"));
        col_titre.setCellValueFactory(
                new PropertyValueFactory<>("titre"));
        col_id.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        col_address.setCellValueFactory(
                new PropertyValueFactory<>("addresse"));
        col_Equpment.setCellValueFactory(
                new PropertyValueFactory<>("equipement"));
        col_btn_activation.setCellValueFactory(
                new PropertyValueFactory<>("activationButton"));
        
        btnInsert.setOnAction((ActionEvent ev) -> {
            try {
                Parent root = new FXMLLoader(getClass()
                        .getResource(FxmlNameSFX.addLogementFxml)).load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Add Logement");
                stage.show();
                stage.setOnHidden((WindowEvent e) -> {
                    // if add Equipement  is added 
                    if (AddLogementController.isInserted()) {
                        // show add item in table
                        System.out.println("new logement data inserted in to logement table ");
                        showTable();
                    }
                });
            } catch (IOException e) {
                System.out.println(e);
            }
        });
        buttonShowChart.setOnAction((ActionEvent ev) -> {
            new LoaderSFX().openNewScene(FxmlNameSFX.chartFxmlFxml, "pie chart", false);
        });
        btnReload.setOnAction((ActionEvent ev) -> {
            showTable();
            textFieldIdSearch.setText("");
            textFieldMomDeLogmement.setText("");
        });

        // on click buttonSearchId
        buttonSearchId.setOnAction((ActionEvent event) -> {
            
            if (!textFieldIdSearch.getText().isEmpty()) {
                
                String id = textFieldIdSearch.getText().trim(); //  get id 
                String sql = searchQueryID("logement_id", id); // get sql search 
                loadItem(sql);
                
            } else {
                new Alerts().warningAlert("text filed id is empty ");
            }
            
        });
        // on click buttonSearchNom
        buttonSearchNom.setOnAction((ActionEvent event) -> {
            if (!textFieldMomDeLogmement.getText().isEmpty()) {
                String nom = textFieldMomDeLogmement.getText().trim();
                String sql = searchQueryNom("nom", nom);
                loadItem(sql);
                
            } else {
                new Alerts().warningAlert("text filed nom is empty ");
            }
        });
        // load 
        showTable();
        
    }
    
    boolean idValidation() {
        
        return !textFieldIdSearch.getText().isEmpty();
    }
    
    boolean nomValidation() {
        return !textFieldMomDeLogmement.getText().isEmpty();
    }

    // for add button to table view 
    private void addButtonToTable() {
        
        Callback<TableColumn<LogementsObj, Void>, TableCell<LogementsObj, Void>> show
                = (final TableColumn<LogementsObj, Void> param) -> new TableCell<LogementsObj, Void>() {
            private final Button btn = new Button(" ");
            
            {
                //-danger  = #B9271B  -primary =#121E31
                FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.EYE);
                icon.setSize("24px");
                icon.setStyle("-fx-fill: white;");
                btn.setGraphic(icon);
                btn.setStyle("-fx-background-color : #00B454;;"
                        + "-fx-effect: dropshadow(three-pass-box,rgba(0,0,0,0.1),10.0,0.0,0.0,10.0); ");
                btn.setText("Afficher");
                btn.setOnAction((ActionEvent event) -> {
                    
                    LogementsObj data1 = getTableView().getItems().get(getIndex());
                    System.out.println("selectedData: " + data1);
                    System.out.println("id : " + data1.getId());
                    showlogementsObj(data1, true);
                    
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
        
        Callback<TableColumn<LogementsObj, Void>, TableCell<LogementsObj, Void>> update
                = (final TableColumn<LogementsObj, Void> param) -> new TableCell<LogementsObj, Void>() {
            private final Button btn = new Button(" ");
            
            {
                //-danger  = #B9271B  -primary =#121E31
                FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.RETWEET);
                icon.setSize("24px");
                icon.setStyle("-fx-fill: white;");
                btn.setGraphic(icon);
                btn.setStyle("-fx-background-color : #FFAA00;;"
                        + "-fx-effect: dropshadow(three-pass-box,rgba(0,0,0,0.1),10.0,0.0,0.0,10.0); ");
                btn.setText("Editer");
                btn.setOnAction((ActionEvent event) -> {
                    
                    LogementsObj data1 = getTableView().getItems().get(getIndex());
                    System.out.println("selectedData: " + data1);
                    System.out.println("id : " + data1.getId());
                    showlogementsObj(data1, false);
                    
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
        
        Callback<TableColumn<LogementsObj, Void>, TableCell<LogementsObj, Void>> delete
                = (final TableColumn<LogementsObj, Void> param) -> new TableCell<LogementsObj, Void>() {
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
                    
                    LogementsObj data1 = getTableView().getItems().get(getIndex());
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
        
        col_btn_show.setCellFactory(show);
        col_btn_update.setCellFactory(update);
        col_btn_delete.setCellFactory(delete);
        
    }
    //=================================================================================================================================================================================
    // sql command for get all data form logement table 
    String query = "SELECT \n"
            + "logement_id as log_id , equipement_id , equipement.nom ,\n"
            + "titre ,addresse , description , is_active ,qr_file_name ,filename ,hote_id\n"
            + "FROM pi.logement_equipement\n"
            + "inner join pi.logement on logement.id = logement_id\n"
            + "inner join pi.equipement on equipement.id = equipement_id\n"
            + "order by logement_id asc;";

    /**
     *
     * SELECT logement_id as log_id , equipement_id , equipement.nom , titre
     * ,addresse , description , is_active ,qr_file_name ,filename ,hote_id FROM
     * pi.logement_equipement inner join pi.logement on logement.id =
     * logement_id inner join pi.equipement on equipement.id = equipement_id
     * order by logement_id asc;
     */
    // sql command for search logement by nom 
    String searchQueryNom(String col, String search) {
        return "SELECT \n"
                + "logement_id as log_id  , equipement_id , equipement.nom ,\n"
                + "titre ,addresse , description , is_active ,qr_file_name ,filename ,hote_id\n"
                + "FROM pi.logement_equipement\n"
                + "inner join pi.logement on logement.id = logement_id\n"
                + "inner join pi.equipement on equipement.id = equipement_id\n"
                + " where " + col + " LIKE '%" + search + "%' "
                + " ORDER BY equipement.nom ASC;";
    }
// sql command for search logement by id 

    String searchQueryID(String col, String search) {
        return "SELECT \n"
                + "logement_id as log_id  , equipement_id , equipement.nom ,\n"
                + "titre ,addresse , description , is_active ,qr_file_name ,filename ,hote_id\n"
                + "FROM pi.logement_equipement\n"
                + "inner join pi.logement on logement.id = logement_id\n"
                + "inner join pi.equipement on equipement.id = equipement_id\n"
                + " where " + col + " =  '" + search + "' "
                + " ORDER BY equipement.nom ASC;";
    }

    //=================================================================================================================================================================================
// validation table 
    public boolean validTable() {
        return table.getSelectionModel().getSelectedIndex() >= 0;
    }
// get size of table list

    private int tableSize() {
        return table.getItems().size();
    }

    // load all data from logement table and load to logementList and set to table
    public void loadItem(String query) {
        System.out.println(query);
        if (!logementList.isEmpty()) {
            logementList.clear();
        }
        try {
            Connection conn = DatabaseHandler.getInstance().getConnection();
            ResultSet rs;
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                rs = pst.executeQuery();
                while (rs.next()) {
                    LogementsObj io = new LogementsObj();
                    //   io.setQr_file_name(getImage(rs.getString("qr_file_name")));
                    
                    String img = rs.getString("filename");
                    io.setId(rs.getInt("log_id"));
                    io.setHote_id(rs.getInt("hote_id"));
                    io.setTitre(rs.getString("titre"));
                    io.setDescription(rs.getString("description"));
                    io.setEquipement(rs.getString("nom"));
                    io.setFilePath(img);
                    io.setQrPath(rs.getString("qr_file_name"));
                    io.setFilename(getImage(img));
                    io.setAddresse(rs.getString("addresse"));
                    io.setActivationButton(getButton(String.valueOf(rs.getInt("is_active"))));
                    logementList.add(io);
                }
                System.out.print(logementList);
                table.setItems(logementList);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    // reload logement table
    void showTable() {
        if (!logementList.isEmpty()) {
            logementList.clear();
        }
        loadItem(query);
    }

    // for print logement table 
    void printTable() {
        System.out.println("---------------------------------------------------");
        table.getItems().forEach((selectedItem) -> System.out.println(selectedItem.toString()));
        System.out.println("---------------------------------------------------");
    }

    // for set image to tableview for any list
    public ImageView getImage(String path) {
        // path for get file
        File file = new File("data/produce/img/" + path);
        
        if (file.exists()) {
            ImageView iv = new ImageView(new Image(file.toURI().toString()));
            iv.setFitWidth(35);
            iv.setFitHeight(35);
            return iv;
        } else {
            System.out.println("Image path is database is null or no photo");
            return null;
        }
        
    }

    // founction for delete logement 
    public void deleteLogement(LogementsObj e) {
        if (new Alerts().deleteAlert()) {
            if (new DataHelper().delete("logement", e.getId())) {
                Alerts.getInstance().successAlert("successfullt data  " + e.getEquipement() + "Deleted.");
                loadItem(query);
            }
        }
    }

    // for update Activation to active or disactive
    public void updateActivation(LogementsObj e) {
        boolean activationAlert = Alerts.getInstance()
                .activationAlert("do you went active " + e.getDescription());
        if (activationAlert) {
            // active statement update database and load table agen
            if (new DataHelper().update("logement", "is_active = '1' ", e.getId() + "")) {
                showTable();
                new Alerts().successAlert("data updated " + e.getEquipement());
            }
        } else {
            if (new DataHelper().update("logement", "is_active = '0' ", e.getId() + "")) {
                showTable();
            }
        }
    }
    
    public void showlogementsObj(LogementsObj logementsObj, boolean flag) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlNameSFX.showLogementFxml));
            Parent rot = loader.load();
            ShowLogementController c = (ShowLogementController) loader.getController();
            c.setData(logementsObj);
            if (flag) {
                c.visibaleUpdateButton();
            }
            
            Stage s = new Stage();
            s.setScene(new Scene(rot));
            s.setTitle("Edite");
            s.show();
            s.setOnHiding((e) -> {
                System.out.println("closed ...");
                if (ShowLogementController.isUpdate()) {
                    System.out.println("is Update .... show table ");
                    showTable();
                } else {
                    System.out.println("data not updated ...");
                }
            });
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //==========================================================================================================
    public Button getButton(String activation) {
        String act = getStringActivation(activation);
        Button button = new Button(act);
        if (act.equals(Activation.ACTIVE)) {
            button.setStyle(""
                    + "-fx-font: bold 12px 'System';"
                    + " -fx-text-fill :white;"
                    + "-fx-background-color : green;");
        } else {
            button.setStyle(""
                    + "-fx-font: bold 12px 'System';"
                    + " -fx-text-fill :white;"
                    + "-fx-background-color : red;");
        }
        
        button.setPrefSize(100, 25);
        return button;
    }
    
    public String getStringActivation(String act) {
        if (act.equals("1")) {
            return Activation.ACTIVE;
        } else {
            return Activation.DISACTIVE;
        }
    }
}
