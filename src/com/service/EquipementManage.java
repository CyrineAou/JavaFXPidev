
package com.service;

import com.utils.DBConnection;
import com.entities.Equipement;
import com.database.DatabaseHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Yousra
 */
public class EquipementManage {

    private static EquipementManage instance;

    private EquipementManage() {
    }

    public void create(Equipement e) {
        try {
            String sql = "INSERT INTO equipement (nom) VALUES (?)";
            PreparedStatement stmnt =  DatabaseHandler.getInstance().getConnection().prepareStatement(sql);
            stmnt.setString(1, e.getName());
            stmnt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Equipement e) {
        try {
            String sql = "UPDATE pi.equipement SET nom =? WHERE id = ? ;";
            PreparedStatement stmnt = DatabaseHandler.getInstance().getConnection().prepareStatement(sql);
            stmnt.setString(1, e.getName());
            stmnt.setInt(2, e.getId());
            stmnt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Equipement e) {
        try {
            String sql = "DELETE FROM equipement WHERE id = ?;";
            PreparedStatement stmnt =  DatabaseHandler.getInstance().getConnection().prepareStatement(sql);
            stmnt.setInt(1, e.getId());
            stmnt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Equipement> getAll() {
        try {
            String sql = "select * FROM equipement ;";
            PreparedStatement stmnt =  DatabaseHandler.getInstance().getConnection().prepareStatement(sql);
            ArrayList<Equipement> all = new ArrayList<>();
            ResultSet rs = stmnt.executeQuery();
            while (rs.next()) {
                Equipement e = new Equipement();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("nom"));
                all.add(e);
            }
            return all;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static EquipementManage getInstance() {
        if (instance == null) {
            instance = new EquipementManage();
        }
        return instance;
    }

}
