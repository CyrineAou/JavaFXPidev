/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.WinDeskTop.Services;

import edu.WinDeskTop.Utils.DataSource;
import edu.WinDeskTop.entities.Annonce;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author cyrine
 */
public class ServiceAnnonce implements IServices<Annonce> {

    Connection cnx = DataSource.getInstance().getCnx();
    private List<Annonce> list=getAll();

    @Override
    public void ajouter(Annonce a) {
        try {
            String req = "INSERT INTO `annonce` (`titre`, `type`, `disponible`, `prix`, `description`) VALUES ( ?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, a.getTitre());
            ps.setString(2, a.getType());
            ps.setBoolean(3, a.getDisponible());
            ps.setInt(4, a.getPrix());
            ps.setString(5, a.getDescription());
            ps.setString(6,  a.getDate());
         

            ps.executeUpdate();
             System.out.println("Annonce ajouter avec succées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `annonce` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Annonce Supprimer avec succées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Annonce a) {
        try {
            String req = "UPDATE `annonce` SET `titre` = '" + a.getTitre() + "', `type` = '" + a.getType() + "', `disponible` = '" + a.getDisponible() + "', `prix` = '" + a.getPrix() + "', `description` = '" + a.getDescription() + "', `date` = '" + a.getDate() + "' WHERE `annonce`.`id` = " + a.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("L'annonce a été mis a jours !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Annonce> getAll() {
        ObservableList<Annonce> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from annonce";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                Annonce a = new Annonce(rs.getInt(7),rs.getString(1), rs.getInt(4), rs.getBoolean(3), rs.getString(5), rs.getString(2), rs.getString(6));
                list.add(a);
                // System.out.println("Votre liste d'annonces : !");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return  list;
    }

    /* Avec l’api Stream */
 /*boolean anyMatch(Predicate):Renvoyer un booléen qui précise si au moins un élément du Stream respecte le Predicate*/
    public boolean rechercherAnnonce(Annonce a) {
        return list.stream().anyMatch(e -> e.equals(a));
    }

    /* Avec l’api Stream */
    public boolean rechercherByTitle(String titre) {
        return list.stream().anyMatch(e -> e.getTitre() == titre);
    }

    /* Avec lambda expression */
    public ObservableList<Annonce> trierAnnoncesParType() {
     
   ObservableList<Annonce> aa =FXCollections.observableArrayList();
   List<Annonce> ann=list;
   ann.stream().sorted((a,b)->a.getPrix()-b.getPrix()).collect(Collectors.toList()).forEach(t->aa.add(t));
  return aa;
    }

    /* Avec l'api stream */
    public void AnnonceSansRedondance() {
        list.stream()
                .distinct()
                .forEach((e) -> System.out.println(e));
    }
    public ObservableList<Annonce> chercherAnnonce(String type){
        
        ObservableList<Annonce> a =FXCollections.observableArrayList();
        List<Annonce> ann=list;
            ann.stream().filter(e->e.getType().equalsIgnoreCase(type)).forEach(t->a.add(t));
        return a;
    }
    
   
   
   
}
