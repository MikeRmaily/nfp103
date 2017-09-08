/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saca;

/**
 *
 * @author Khalil Bsaibes
 */
public class Acceleration{

    private int cap;
    private int vitesse;
    
    Acceleration(int vitesse, int angle){
    setVitesse(vitesse);
    setCap(angle);
    }
    

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }
}
