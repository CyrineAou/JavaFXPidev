/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.entities.Agence;
import projet.tools.MyConnection;

/**
 *
 * @author Ih3b
 */
public class CRUDagence {
        public void addAgence(Agence a) {
        try {
            String requete = "INSERT INTO agence (Phone,CodeL,Email,Adress,Nb_adultes,Nb_enfants)" + "VALUES (?,?,?,?,?,?)";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, a.getPhone());
            pst.setInt(2, a.getCodeL());
            pst.setString(3, a.getEmail());
            pst.setString(4, a.getAdress());
            pst.setInt(5, a.getNb_adultes());
            pst.setInt(6, a.getNb_enfants());
            pst.executeUpdate();

            System.out.println("Reservation ajoutée!");
        } catch (SQLException ex) {
            Logger.getLogger(CRUDagence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Agence> showAgence() {
        ObservableList<Agence> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM agence";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(new Agence(rs.getInt("Phone"), rs.getInt("CodeL"),rs.getString("Email"),rs.getString("Adress"),rs.getInt("Nb_adultes"),rs.getInt("Nb_enfants")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CRUDagence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void editAgence(Agence a) {
        try {

            String requete = "UPDATE agence SET CodeL= ? ,Email = ? ,Adress= ? ,Nb_adultes = ? , Nb_enfants= ? where Phone = ? ";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, a.getCodeL());
            pst.setString(2, a.getEmail());
            pst.setString(3, a.getAdress());
            pst.setInt(4, a.getNb_adultes());
            pst.setInt(5, a.getNb_enfants());
            pst.setInt(6, a.getPhone());
            pst.executeUpdate();

            System.out.println("Agence modifiée!");

        } catch (SQLException ex) {
            Logger.getLogger(CRUDagence.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteAgence(int Phone) {
        
            try {
                String requete = "DELETE FROM agence WHERE Phone = ?";
                PreparedStatement pst
                        = new MyConnection().cn.prepareStatement(requete);
                pst.setInt(1, Phone);
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(CRUDagence.class.getName()).log(Level.SEVERE, null, ex);
            }

        
    }
    
}
