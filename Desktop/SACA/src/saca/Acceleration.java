/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saca;
/**
 *
 * @author Mike
 */
public class Acceleration{
     float x;
    float y;
    float z;
    private int cap;
    private int vitesse;
    
    Acceleration(float x,float y,float z){
    this.x = x;
    this.y = y;
    this.z = z;
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
