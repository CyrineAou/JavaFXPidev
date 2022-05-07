/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Ih3b
 */
public class Facture { 
    private int CodeTVA ;
    private int frais_ad ;
    
    private int frais_en ;
    
    private int frais_tour ;
   private String Methode; 
    
    public Facture(int CodeTVA, int frais_ad, int frais_en, int frais_tour, String Methode) {
        this.CodeTVA = CodeTVA;
        this.frais_ad = frais_ad;
        this.frais_en = frais_en;
        this.frais_tour = frais_tour;
        this.Methode = Methode;
    }

    public int getCodeTVA() {
        return CodeTVA;
    }

    public void setCodeTVA(int CodeTVA) {
        this.CodeTVA = CodeTVA;
    }

    public int getFrais_ad() {
        return frais_ad;
    }

    public void setFrais_ad(int frais_ad) {
        this.frais_ad = frais_ad;
    }

    public int getFrais_en() {
        return frais_en;
    }

    public void setFrais_en(int frais_en) {
        this.frais_en = frais_en;
    }

    public int getFrais_tour() {
        return frais_tour;
    }

    public void setFrais_tour(int frais_tour) {
        this.frais_tour = frais_tour;
    }

    public String getMethode() {
        return Methode;
    }

    public void setMethode(String Methode) {
        this.Methode = Methode;
    }

    
  
 

    
}

    