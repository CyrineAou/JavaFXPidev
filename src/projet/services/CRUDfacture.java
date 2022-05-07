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


import projet.entities.Facture;
import projet.tools.MyConnection;

/**
 *
 * @author ena
 */
public class CRUDfacture { 
    
     public void addFacture(Facture s) {
        try {
            String requete = "INSERT INTO facture (CodeTVA,Frais Adultes ,Frais Enfants ,Frais Tour,Methode)" + "VALUES (?,?,?,?,?)";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, s.getCodeTVA());
            pst.setInt(2, s.getFrais_ad());
             pst.setInt(3, s.getFrais_en());
          
            pst.setInt(4, s.getFrais_tour());
           pst.setString(5, s.getMethode());
            pst.executeUpdate();
// tansech bech taaml table fel  base de donne facture
//<Button fx:id="pdfs" layoutX="120.0" layoutY="34.0" mnemonicParsing="false" onAction="#pdfs" prefHeight="31.0" prefWidth="99.0" text="PDF" />
            System.out.println("Facture ajouté!");
        } catch (SQLException ex) {
            Logger.getLogger(CRUDagence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public ObservableList<Facture> showFacture() {
        ObservableList<Facture> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM facture";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(new Facture(rs.getInt("CodeTVA"),rs.getInt("Frais Adultes"),rs.getInt("Frais Enfants"),rs.getInt("Frais Tour"),rs.getString("Methode")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CRUDfacture.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public void editFacture(Facture s) {
        try {

            String requete = "UPDATE facture SET  Frais Adultes= ?,Frais Enfants= ? ,Frais Tour= ? , Methode=?  where CodeTVA = ? ";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
           
            pst.setInt(1, s.getFrais_ad());
             pst.setInt(2, s.getFrais_en());
          
            pst.setInt(3, s.getFrais_tour());
           pst.setString(4, s.getMethode());
pst.setInt(5, s.getCodeTVA());
            System.out.println("Facture modifiée!");

        } catch (SQLException ex) {
            Logger.getLogger(CRUDfacture.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
       public void deleteFacture(int CodeTVA) {
        
            try {
                String requete = "DELETE FROM facture WHERE CodeTVA = ?";
                PreparedStatement pst
                        = new MyConnection().cn.prepareStatement(requete);
                pst.setInt(1, CodeTVA);
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(CRUDfacture.class.getName()).log(Level.SEVERE, null, ex);
            }

        
    }
    
}
