/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.WinDeskTop.entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author cyrine
 */
public class Promotion {
     private  int id;
     private int taux;
     private String date_debut;
     private String date_fin;

    public Promotion() {
    }

    public Promotion(int id, int taux, String date_debut, String date_fin) {
        this.id = id;
        this.taux = taux;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Promotion(int taux, String date_debut, String date_fin) {
        this.taux = taux;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public int getId() {
        return id;
    }

    public int getTaux() {
        return taux;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTaux(int taux) {
        this.taux = taux;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", taux=" + taux + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + this.taux;
        hash = 71 * hash + Objects.hashCode(this.date_debut);
        hash = 71 * hash + Objects.hashCode(this.date_fin);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Promotion other = (Promotion) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.taux != other.taux) {
            return false;
        }
        if (!Objects.equals(this.date_debut, other.date_debut)) {
            return false;
        }
        if (!Objects.equals(this.date_fin, other.date_fin)) {
            return false;
        }
        return true;
    }
    
}
