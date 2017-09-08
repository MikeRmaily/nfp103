/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saca;

import static java.lang.Math.random;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike Rmaily
 */
public class Avion extends Thread {

    String flightname;

    public String getFlightname() {
        return flightname;
    }

    public void setFlightname(String flightname) {
        this.flightname = flightname;
    }
    Point3D CurrentPosition;
    Acceleration Acc;
    int ALTMAX = 20000;
    int ALTMIN = 0;
    int VITMAX = 600;
    int VITMIN = 200;

    public Avion() {
        CurrentPosition = new Point3D(0,0,0);
        Acc = new Acceleration(1,0);
        this.flightname=getRandomName();
    }

    @Override
    public void run() {
        initialiseravion();
        do {
            this.CurrentPosition.Plus(Acc);
            System.out.println(this.flightname + " " + this.CurrentPosition.ToString());
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Avion.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while ( Thread.currentThread().isAlive());
    }
    
    public String getInfo() {
        String info = "Airplane " + flightname + ":\n" 
                + "----X:  " + CurrentPosition.getX()+" \n----Y:  "+CurrentPosition.getY()+"\n----Z:  "+CurrentPosition.getZ()+"\n";

        return info;
    }
    public void setPosition(Point3D CurrentPosition) {
        this.CurrentPosition = CurrentPosition;
    }
    

    /**
     * ******************************
     *** Fonctions gérant le déplacement de l'avion : ne pas modifier
     * ******************************
     * @param name
     */
    // initialise aléatoirement les paramétres initiaux de l'avion
    private void initialiseravion() {
        // initialisation al�atoire du compteur aléatoire
        // intialisation des paramétres de l'avion
        
      //  Random numberGenerator = new Random();
        CurrentPosition.setX((int) (1000 + random() % 1000));
        CurrentPosition.setY((int) (1000 + random() % 1000));
        CurrentPosition.setZ((int) (1000 + random() % 1000));
//        CurrentPosition.setX((int) ( numberGenerator.nextInt(100) % 1000));
//        CurrentPosition.setY((int) ( numberGenerator.nextInt(100) % 1000));
//        CurrentPosition.setZ((int) ( numberGenerator.nextInt(100) % 1000));
        Acc.setCap(0);
        Acc.setVitesse(1);
    }

     private String getRandomName() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
   
}

