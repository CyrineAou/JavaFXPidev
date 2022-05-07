/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.WinDeskTop.entities;


import java.sql.Date;
import java.util.Objects;
import javafx.scene.control.Button;

/**
 *
 * @author cyrine
 */
public class Annonce {
    private  int id;
    private String titre;
    private int prix;
    private boolean disponible;
    private String description;
    private String type;
    private String date;
    private String image;
     
    public Annonce() {
    }

    public Annonce(String titre, int prix, boolean disponible, String description, String type) {
        this.titre = titre;
        this.prix = prix;
        this.disponible = disponible;
        this.description = description;
        this.type = type;
        
    }
    

    public Annonce(int id, String titre, int prix, boolean disponible, String description, String type, String date) {
        this.id = id;
        this.titre = titre;
        this.prix = prix;
        this.disponible = disponible;
        this.description = description;
        this.type = type;
        this.date = date;
    }

    public Annonce(String titre, int prix, boolean disponible, String description, String type, String date) {
        this.titre = titre;
        this.prix = prix;
        this.disponible = disponible;
        this.description = description;
        this.type = type;
        this.date = date;
    }

    public Annonce(String titre, int prix, boolean disponible, String description, String type, String date, String image) {
        this.titre = titre;
        this.prix = prix;
        this.disponible = disponible;
        this.description = description;
        this.type = type;
        this.date = date;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    


    public int getId() {
        return id;
    }

  

    public String getTitre() {
        return titre;
    }

    public int getPrix() {
        return prix;
    }

    /**
     *
     * @return
     */
    public boolean getDisponible() {
        return disponible;
    }


    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", titre=" + titre + ", prix=" + prix + ", disponible=" + disponible + ", description=" + description + ", type=" + type + ", date=" + date + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.titre);
        hash = 67 * hash + Float.floatToIntBits(this.prix);
        hash = 67 * hash + (this.disponible ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.type);
        hash = 67 * hash + Objects.hashCode(this.date);
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
        final Annonce other = (Annonce) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (this.disponible != other.disponible) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
    
}
