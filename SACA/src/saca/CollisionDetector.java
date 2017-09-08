/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saca;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Khalil Bsaibes
 */
public class CollisionDetector extends Thread {

    SACA mySaca;
    HashMap<String, Avion> NextLocationHM;
    HashMap<Avion, Avion> CollisionHM;

    CollisionDetector(SACA saca) {
        this.NextLocationHM = new HashMap<String, Avion>();
        this.mySaca = saca;
    }

    @Override
    public void run() {
        do {
            for (Map.Entry pair : mySaca.hmap.entrySet()) {
                Avion avion = (Avion) pair.getValue();
                avion.CurrentPosition.Plus(avion.Acc);
                NextLocationHM.put(pair.getKey().toString(), avion);
            }
            CollisionHM = getDuplicateValues(NextLocationHM);

        } while (Thread.currentThread().isAlive());
    }

    public HashMap getDuplicateValues(HashMap in) {
        HashMap rval = new HashMap();
        in = (HashMap) in.clone();

        Object[] keys = in.keySet().toArray();

        // iterate through all keys
        for (int x = 0; x < keys.length; x++) {
            Avion avion1 = (Avion) in.get(keys[x]);

            for (int j = x + 1; j < keys.length; j++) {
                Avion avion2 = (Avion) in.get(keys[j]);

                if (avion1.CurrentPosition.getX() == avion2.CurrentPosition.getX() && avion1.CurrentPosition.getY() == avion2.CurrentPosition.getY() && avion1.CurrentPosition.getZ() == avion2.CurrentPosition.getZ()) {
                    rval.put(avion1, avion2);
                    System.out.println(avion1.flightname + " will crash with" + avion2.flightname);

                    //avion1.Acc.setCap(avion1.Acc.set);
                }
            }

        }

        return (rval);
    }
}
