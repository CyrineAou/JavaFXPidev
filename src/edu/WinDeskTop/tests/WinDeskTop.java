/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.WinDeskTop.tests;

import edu.WinDeskTop.Services.ServiceAnnonce;
import edu.WinDeskTop.Services.ServicePromotion;
import edu.WinDeskTop.entities.Annonce;
import edu.WinDeskTop.entities.Promotion;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author cyrine
 */
public class WinDeskTop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
   
    
   // Annonce A1 = new Annonce();
   // LocalDate d1 = new LocalDate(16, 3, 2022);
    Date d2 = new Date(5, 2, 2020);
    Date d3 = new Date(5, 2, 2020);
    
   //Annonce A1 = new Annonce("maison",  500 , true, "maison d'hote disponible ",  "Maison", d2);
   // Annonce A2 = new Annonce("titre",  50 , true, "description",  "type", d1);
  // Annonce A3 = new Annonce("Annonce2",  30 , false, "chambre indiv",  "Appartement", d3);
    
    ServiceAnnonce sp = new ServiceAnnonce();
  
    /*********************** TEST ANNONCE *************************************************************/
    System.out.print( sp.getAll());
    // sp.supprimer(1);
    //sp.ajouter(A2);
     //sp.ajouter(A3);
    //sp.ajouter(A1);
     
 
     /***************************** TEST  PROMOTION ****************************************************/
     ServicePromotion pp = new ServicePromotion();
     //Promotion p1 = new Promotion(1,d2,d3);
  // pp.ajouter(p1);
        System.out.println(sp.chercherAnnonce("maison"));
    }
       
}
