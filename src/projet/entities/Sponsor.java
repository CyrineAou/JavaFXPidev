/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.entities;

import java.util.Date;

/**
 *
 * @author Ih3b
 */
public class Sponsor { 
    private int CodeTVA ;
    private String Nom_s ;
    private String Type_s; 
    private int Total;  

    public Sponsor(int CodeTVA, String Nom_s, String Type_s, int Total) {
        this.CodeTVA = CodeTVA;
        this.Nom_s = Nom_s;
        this.Type_s = Type_s;
        this.Total = Total;
    }

    public int getCodeTVA() {
        return CodeTVA;
    }

    public void setCodeTVA(int CodeTVA) {
        this.CodeTVA = CodeTVA;
    }

    public String getNom_s() {
        return Nom_s;
    }

    public void setNom_s(String Nom_s) {
        this.Nom_s = Nom_s;
    }

    public String getType_s() {
        return Type_s;
    }

    public void setType_s(String Type_s) {
        this.Type_s = Type_s;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }
    
}

    