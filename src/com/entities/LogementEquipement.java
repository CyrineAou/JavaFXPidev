/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.Objects;

/**
 *
 * @author Yousra
 */
public class LogementEquipement {

    private int logementID;
    private int equipementID;
    private LogementEquipement instance;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.logementID;
        hash = 13 * hash + this.equipementID;
        hash = 13 * hash + Objects.hashCode(this.instance);
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
        final LogementEquipement other = (LogementEquipement) obj;
        if (this.logementID != other.logementID) {
            return false;
        }
        if (this.equipementID != other.equipementID) {
            return false;
        }
        if (!Objects.equals(this.instance, other.instance)) {
            return false;
        }
        return true;
    }

    public LogementEquipement(int logementID, int equipementID, LogementEquipement instance) {
        this.logementID = logementID;
        this.equipementID = equipementID;
        this.instance = instance;
    }

    private LogementEquipement() {
    }

    public int getLogementID() {
        return logementID;
    }

    public void setLogementID(int logementID) {
        this.logementID = logementID;
    }

    public int getEquipementID() {
        return equipementID;
    }

    public void setEquipementID(int equipementID) {
        this.equipementID = equipementID;
    }

    public LogementEquipement getInstance() {
        if (instance == null) {
            instance = new LogementEquipement();
        }
        return instance;
    }

}
