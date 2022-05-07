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


import projet.entities.Sponsor;
import projet.tools.MyConnection;

/**
 *
 * @author ena
 */
public class CRUDsponsor { 
    
     public void addSponsor(Sponsor s) {
        try {
            String requete = "INSERT INTO sponsor (CodeTVA,Nom_s,Type_s,Total)" + "VALUES (?,?,?,?)";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, s.getCodeTVA());
            pst.setString(2, s.getNom_s());
            pst.setString(3, s.getType_s());
            pst.setInt(4, s.getTotal());
        
            pst.executeUpdate();

            System.out.println("Facture ajouté!");
        } catch (SQLException ex) {
            Logger.getLogger(CRUDagence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public ObservableList<Sponsor> showSponsor() {
        ObservableList<Sponsor> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM sponsor";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(new Sponsor(rs.getInt("CodeTVA"),rs.getString("Nom_s"),rs.getString("Type_s"),rs.getInt("Total")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CRUDsponsor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public void editSponsor(Sponsor s) {
        try {

            String requete = "UPDATE sponsor SET  Type_s= ?,Nom_s= ? , Total=?  where CodeTVA = ? ";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
           pst.setString(1, s.getType_s());
            pst.setString(2, s.getNom_s()); 
                   pst.setInt(3, s.getTotal());

            pst.setInt(4, s.getCodeTVA());
            pst.executeUpdate();

            System.out.println("Facture modifiée!");

        } catch (SQLException ex) {
            Logger.getLogger(CRUDsponsor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
       public void deleteSponsor(int CodeTVA) {
        
            try {
                String requete = "DELETE FROM sponsor WHERE CodeTVA = ?";
                PreparedStatement pst
                        = new MyConnection().cn.prepareStatement(requete);
                pst.setInt(1, CodeTVA);
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(CRUDsponsor.class.getName()).log(Level.SEVERE, null, ex);
            }

        
    }
    
}
