
package com.service;

import com.entities.Logement;
import com.database.DatabaseHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author Yousra
 */
public class logementManage {

    private static logementManage instance;

    private logementManage() {
    }

    public void create(Logement logement) {
        try {
            String sql = "INSERT INTO logement(hote_id,titre,description,addresse,filename,created_at,\n"
                    + "updated_at, qr_file_name,is_active)VALUES(?,?,?,?,?,?,?,?,?);";
            PreparedStatement stmnt =  DatabaseHandler.getInstance().getConnection().prepareStatement(sql);
            stmnt.setInt(1, logement.getHotelID());
            stmnt.setString(2, logement.getTitre());
            stmnt.setString(3, logement.getDescription());
            stmnt.setString(4, logement.getAddresse());
            stmnt.setString(5, logement.getFilename());
            stmnt.setString(6, logement.getCreated_at().toString());
            stmnt.setString(7, logement.getUpdated_at().toString());
            stmnt.setString(8, logement.getQr_file_name());
            stmnt.setBoolean(9, logement.isActive());
            stmnt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Logement logement) {
        try {
            String sql = "UPDATE logement SET \n"
                    + "hote_id =  ? , titre =  ? , description =  ? , addresse =  ? , filename =  ? , \n"
                    + " updated_at =  ? , qr_file_name = ? , \n"
                    + "is_active = ? WHERE id = ? ;";
            PreparedStatement stmnt =  DatabaseHandler.getInstance().getConnection().prepareStatement(sql);
            stmnt.setInt(1, logement.getHotelID());
            stmnt.setString(2, logement.getTitre());
            stmnt.setString(3, logement.getDescription());
            stmnt.setString(4, logement.getAddresse());
            stmnt.setString(5, logement.getFilename());
            stmnt.setString(6, logement.getUpdated_at().toString());
            stmnt.setString(7, logement.getQr_file_name());
            stmnt.setBoolean(8, logement.isActive());
            stmnt.setInt(9, logement.getId());
            stmnt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Logement logement) {
        try {
            String sql = "DELETE FROM logement WHERE id = ?;";
            PreparedStatement stmnt =  DatabaseHandler.getInstance().getConnection().prepareStatement(sql);
            stmnt.setInt(1, logement.getId());
            stmnt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Logement> getAll() {
        try {
            String sql = "select * FROM logement ;";
            PreparedStatement stmnt =  DatabaseHandler.getInstance().getConnection().prepareStatement(sql);
            ArrayList<Logement> all = new ArrayList<>();
            ResultSet rs = stmnt.executeQuery();
            while (rs.next()) {
                Logement e = new Logement();
                e.setId(rs.getInt("id"));
                e.setAddresse(rs.getString("addresse"));
                e.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
                if (rs.getTimestamp("updated_at") != null) {
                    e.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
                }
                e.setDescription(rs.getString("description"));
                e.setFilename(rs.getString("filename"));
                e.setHotelID(rs.getInt("hote_id"));
                e.setActive(rs.getBoolean("is_active"));
                e.setQr_file_name(rs.getString("qr_file_name"));
                e.setTitre(rs.getString("titre"));
                all.add(e);
            }
            return all;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static logementManage getInstance() {
        if (instance == null) {
            instance = new logementManage();
        }
        return instance;
    }
    public PieChart createPieChart() {
      PieChart pie = new PieChart();
      ObservableList<PieChart.Data> data =
         FXCollections.observableArrayList();
      data.addAll(new PieChart.Data("Logement_5_étoiles", 30.0),
         new PieChart.Data("Logement_4_étoiles", 20.3),
         new PieChart.Data("Logement_3_étoiles", 16.3),
         new PieChart.Data("Logement_2_étoiles", 12.0),
         new PieChart.Data("Logement_1_étoiles", 8.9),
         new PieChart.Data("Logement_normal_étoiles", 6.7),
         new PieChart.Data("Logement_luxes_étoiles", 5.2));

      pie.setData(data);
      pie.setTitle("Logements:total");
      return pie;
   }

}
