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
public class SACA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Point3D p1 = new Point3D(0,0,0);
       Point3D p2 = new Point3D(10,15,20);
       Avion a1 = new Avion("mike",p1,p2);
       
       
       Point3D p3 = new Point3D(0,0,0);
       Point3D p4 = new Point3D(10,15,20);
       Avion a2 = new Avion("toni",p3,p4);
   
       
       a1.start();
        a2.start();
    }
    
}
