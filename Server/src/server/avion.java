/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Khalil
 */
public class avion {
 String avion_ID;
    Float PositionX;
    Float PositionY;
    Float PositionZ;
    Float VitesseX;
    Float VitesseY;
    Float VitesseZ;
    public avion(String avion_ID, Float PositionX, Float PositionY, Float PositionZ, Float VitesseX, Float VitesseY, Float VitesseZ) {
        this.avion_ID = avion_ID;
        this.PositionX = PositionX;
        this.PositionY = PositionY;
        this.PositionZ = PositionZ;
        this.VitesseX = VitesseX;
        this.VitesseY = VitesseY;
        this.VitesseZ = VitesseZ;
    }
 
    public String getAvion_ID() {
        return avion_ID;
    }

    public void setAvion_ID(String avion_ID) {
        this.avion_ID = avion_ID;
    }

    public Float getPositionX() {
        return PositionX;
    }

    public void setPositionX(Float PositionX) {
        this.PositionX = PositionX;
    }

    public Float getPositionY() {
        return PositionY;
    }

    public void setPositionY(Float PositionY) {
        this.PositionY = PositionY;
    }

    public Float getPositionZ() {
        return PositionZ;
    }

    public void setPositionZ(Float PositionZ) {
        this.PositionZ = PositionZ;
    }

    public Float getVitesseX() {
        return VitesseX;
    }

    public void setVitesseX(Float VitesseX) {
        this.VitesseX = VitesseX;
    }

    public Float getVitesseY() {
        return VitesseY;
    }

    public void setVitesseY(Float VitesseY) {
        this.VitesseY = VitesseY;
    }

    public Float getVitesseZ() {
        return VitesseZ;
    }

    public void setVitesseZ(Float VitesseZ) {
        this.VitesseZ = VitesseZ;
    }

  
}
