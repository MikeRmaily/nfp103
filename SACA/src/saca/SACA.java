/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saca;

import java.util.HashMap;

/**
 *
 * @author Mike
 */
public class SACA {
    HashMap<String, Avion> hmap = new HashMap<String, Avion>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
  
    Avion InitAirplane() {
        Avion myavion = new Avion();
        myavion.start();
        hmap.put(myavion.getFlightname(), myavion);
        return myavion;
    }
}
