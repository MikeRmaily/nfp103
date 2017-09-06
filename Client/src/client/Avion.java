/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Khalil Bsaibes
 */
public class Avion extends Thread {

    String name;
    Point3D CurrentPosition;
    Acceleration acceleration;
    Acceleration Acc;
    int ALTMAX = 20000;
    int ALTMIN = 0;
    int VITMAX = 600;
    int VITMIN = 200;

    public Avion(String Name, Point3D CurrentPosition, Acceleration acceleration) {
        this.name = Name;
        this.CurrentPosition = CurrentPosition;
        this.acceleration = acceleration;

    }

    @Override
    public void run() {
        do {

            this.CurrentPosition.Plus(Acc);
            System.out.println(this.name + " " + this.CurrentPosition.ToString());
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Avion.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (!CurrentPosition.EqualsOrGreater(EndPoint) && Thread.currentThread().isAlive());
    }

    private String getRandomName() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
    public void setPosition(Point3D CurrentPosition) {
        this.CurrentPosition = CurrentPosition;
    }
    /**
     * ******************************
     *** Fonctions gérant le déplacement de l'avion : ne pas modifier
     * ******************************
     */
    // initialise aléatoirement les paramétres initiaux de l'avion
    private void initialiseravion() {
        // initialisation al�atoire du compteur aléatoire
        // intialisation des paramétres de l'avion
        Point3D CurrentPosition = null;
        Acceleration acceleration = null;
        Random numberGenerator = new Random();

        CurrentPosition.setX((int) (1000 + numberGenerator.nextInt(100) % 1000));
        CurrentPosition.setY((int) (1000 + numberGenerator.nextInt(100) % 1000));
        CurrentPosition.setZ((int) (900 + numberGenerator.nextInt(100) % 100));
        acceleration.setCap((int) (numberGenerator.nextInt(100) % 360));
        acceleration.setVitesse((int) (600 + numberGenerator.nextInt(100) % 200));
        String flightName = getRandomName();
        Avion avion = new Avion(flightName, CurrentPosition, acceleration);

    }

// modifie la valeur de l'avion avec la valeur passée en param�tre
    private void changer_vitesse(int vitesse) {
        if (vitesse < 0) {
            acceleration.setVitesse(0);
        } else if (vitesse > VITMAX) {
            acceleration.setVitesse(VITMAX);
        } else {
            acceleration.setVitesse(vitesse);
        }
    }

// modifie le cap de l'avion avec la valeur passée en paramètre
    private void changer_cap(int cap) {
        if ((cap >= 0) && (cap < 360)) {
            acceleration.setCap(cap);
        }
    }

// modifie l'altitude de l'avion avec la valeur passée en paramètre
    private void changer_altitude(int alt) {

        if (alt < 0) {
            CurrentPosition.setZ(0);
        } else if (alt > ALTMAX) {
            CurrentPosition.setZ(ALTMAX);
        } else {
            CurrentPosition.setZ(alt);
        }
    }
 public String getInfo() {
        String info = "Vol:" + name + ", " 
                + "Position: " + CurrentPosition.getX()+":"+CurrentPosition.getY()+":"+CurrentPosition.getAltitude()+", "
                + "Vitesse: " + acceleration.getVitesse()+",Cap: "+acceleration.getCap();

        return info;
    }
// affiche les caractéristiques courantes de l'avion
    private void afficher_donnees() {
       System.out.println(this.getInfo());
    }

// recalcule la localisation de l'avion en fonction de sa vitesse et de son cap
    private Boolean calculacceleration() {
        double cosinus, sinus;
        double dep_x, dep_y;

        if (acceleration.getVitesse() < VITMIN) {

            Message msg = new Message("Alert: destruction de l'avion: ", this.name);
            sendObject(msg);
            fermer_communication();
            return false;
        }
        if (CurrentPosition.getAltitude() == 0) {

            Message msg = new Message("Alert: L'avion s'est ecrase au sol: ",this.name);
            sendObject(msg);
            fermer_communication();
            return false;
        }
        //cos et sin ont un paramétre en radian, dep.cap en degré nos habitudes francophone
        /* Angle en radian = pi * (angle en degré) / 180 
       Angle en radian = pi * (angle en grade) / 200 
       Angle en grade = 200 * (angle en degré) / 180 
       Angle en grade = 200 * (angle en radian) / pi 
       Angle en degré = 180 * (angle en radian) / pi 
       Angle en degré = 180 * (angle en grade) / 200 
         */

        cosinus = cos(acceleration.getCap() * 2 * Math.PI / 360);
        sinus = sin(acceleration.getCap() * 2 * Math.PI / 360);

        //newPOS = oldPOS + Vt
        dep_x = cosinus * acceleration.getVitesse() * 10 / VITMIN;
        dep_y = sinus * acceleration.getVitesse() * 10 / VITMIN;

        // on se d�place d'au moins une case quels que soient le cap et la vitesse
        // sauf si cap est un des angles droit
        if ((dep_x > 0) && (dep_x < 1)) {
            dep_x = 1;
        }
        if ((dep_x < 0) && (dep_x > -1)) {
            dep_x = -1;
        }

        if ((dep_y > 0) && (dep_y < 1)) {
            dep_y = 1;
        }
        if ((dep_y < 0) && (dep_y > -1)) {
            dep_y = -1;
        }

        //printf(" x : %f y : %f\n", dep_x, dep_y);
        CurrentPosition.setX((int) (CurrentPosition.getX() + (int) dep_x));
        CurrentPosition.setY((int) (CurrentPosition.getY() + (int) dep_y));
        this.setPosition(CurrentPosition);
        afficher_donnees();
        return true;
    }

// fonction principale : gère l'exécution de l'avion au fil du temps
    private void se_deplacer() {
        if (calculacceleration()) {
            envoyer_caracteristiques();
        }
    }
    //</editor-fold>

    public void run() {
        while (true) {
            receiveObject();
        }
    }
}

