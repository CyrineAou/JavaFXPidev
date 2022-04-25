package com.model;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class LogementsObj {

    private int id;
    private int hote_id;
    private String titre;
    private String description;
    private String equipement;
    private String addresse;
    private String filePath;
    private ImageView filename;
    private String created_at;
    private String updated_at;
    private String qrPath;
    private ImageView qr_file_name;
    private int is_active;
    private Button activationButton;

    public Button getActivationButton() {
        return activationButton;
    }

    public void setActivationButton(Button activationButton) {
        this.activationButton = activationButton;
    }

    public LogementsObj() {
    }

    public LogementsObj(int id, int hote_id, String titre, String description, String equipement, String addresse, ImageView filename, String created_at, String updated_at, ImageView qr_file_name, int is_active) {
        this.id = id;
        this.hote_id = hote_id;
        this.titre = titre;
        this.description = description;
        this.equipement = equipement;
        this.addresse = addresse;
        this.filename = filename;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.qr_file_name = qr_file_name;
        this.is_active = is_active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHote_id() {
        return hote_id;
    }

    public void setHote_id(int hote_id) {
        this.hote_id = hote_id;
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

    public String getEquipement() {
        return equipement;
    }

    public void setEquipement(String equipement) {
        this.equipement = equipement;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public ImageView getFilename() {
        return filename;
    }

    public void setFilename(ImageView filename) {
        this.filename = filename;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public ImageView getQr_file_name() {
        return qr_file_name;
    }

    public void setQr_file_name(ImageView qr_file_name) {
        this.qr_file_name = qr_file_name;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getQrPath() {
        return qrPath;
    }

    public void setQrPath(String qrPath) {
        this.qrPath = qrPath;
    }

    @Override
    public String toString() {
        return "LogementsObj{" + "id=" + id + ", hote_id=" + hote_id + ", titre=" + titre + ", description=" + description + ", equipement=" + equipement + ", addresse=" + addresse + ", filePath=" + filePath + ", filename=" + filename + ", created_at=" + created_at + ", updated_at=" + updated_at + ", qrPath=" + qrPath + ", qr_file_name=" + qr_file_name + ", is_active=" + is_active + '}';
    }

    public String toInfo() {
        return "id=" + id
                + ", titre=" + titre
                + ", description=" + description
                + ", equipement=" + equipement
                + ", addresse=" + addresse
                + ", is_active=" + is_active;
    }

}
