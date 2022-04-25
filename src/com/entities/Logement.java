/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Yousra
 */
public class Logement {

    private int id;
    private int hotelID;
    private String titre;
    private String description;
    private String addresse;
    private String filename;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String qr_file_name;
    private boolean active;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id;
        hash = 19 * hash + this.hotelID;
        hash = 19 * hash + Objects.hashCode(this.titre);
        hash = 19 * hash + Objects.hashCode(this.description);
        hash = 19 * hash + Objects.hashCode(this.addresse);
        hash = 19 * hash + Objects.hashCode(this.filename);
        hash = 19 * hash + Objects.hashCode(this.created_at);
        hash = 19 * hash + Objects.hashCode(this.updated_at);
        hash = 19 * hash + Objects.hashCode(this.qr_file_name);
        hash = 19 * hash + (this.active ? 1 : 0);
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
        final Logement other = (Logement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.hotelID != other.hotelID) {
            return false;
        }
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.addresse, other.addresse)) {
            return false;
        }
        if (!Objects.equals(this.filename, other.filename)) {
            return false;
        }
        if (!Objects.equals(this.qr_file_name, other.qr_file_name)) {
            return false;
        }
        if (!Objects.equals(this.created_at, other.created_at)) {
            return false;
        }
        if (!Objects.equals(this.updated_at, other.updated_at)) {
            return false;
        }
        return true;
    }

    public Logement(int id, int hotelID, String titre, String description, String addresse, String filename, LocalDateTime created_at, LocalDateTime updated_at, String qr_file_name, boolean active) {
        this.id = id;
        this.hotelID = hotelID;
        this.titre = titre;
        this.description = description;
        this.addresse = addresse;
        this.filename = filename;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.qr_file_name = qr_file_name;
        this.active = active;
    }

    public Logement() {
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public String getQr_file_name() {
        return qr_file_name;
    }

    public void setQr_file_name(String qr_file_name) {
        this.qr_file_name = qr_file_name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
