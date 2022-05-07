/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.entities;

/**
 *
 * @author ahmed
 */
public class Agence {
    private int Phone;
    private int CodeL;
    private String Email ;
    private  String Adress ;
    private int Nb_adultes;
    private int Nb_enfants;

    public Agence(int Phone, int CodeL, String Email, String Adress, int Nb_adultes, int Nb_enfants) {
        this.Phone = Phone;
        this.CodeL = CodeL;
        this.Email = Email;
        this.Adress = Adress;
        this.Nb_adultes = Nb_adultes;
        this.Nb_enfants = Nb_enfants;
    }

    public int getPhone() {
        return Phone;
    }

    public void setCodeL(int Phone) {
        this.Phone = Phone;
    }

    public int getCodeL() {
        return CodeL;
    }

    public void setPhone(int CodeL) {
        this.CodeL = CodeL;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String Adress) {
        this.Adress = Adress;
    }

    public int getNb_adultes() {
        return Nb_adultes;
    }

    public void setNb_adultes(int Nb_adultes) {
        this.Nb_adultes = Nb_adultes;
    }

    public int getNb_enfants() {
        return Nb_enfants;
    }

    public void setNb_enfants(int Nb_enfants) {
        this.Nb_enfants = Nb_enfants;
    }
    
    
    
}
