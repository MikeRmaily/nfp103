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
public class Point3D {

    int x;
    int y;
    int z;

    Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean Equals(Point3D A) {
        return A.x == this.x && A.y == this.y && A.z == this.z;
    }

    public boolean EqualsOrGreater(Point3D A) {
        return A.x <= this.x && A.y <= this.y && A.z <= this.z;
    }

    public float GetMax() {
        return Math.max(this.x, Math.max(this.y, this.z));
    }

    public void Plus(Acceleration Acc) {
        this.x = this.x + Acc.getVitesse();
        this.y = (int) ((Math.tan(Acc.getCap()) * Acc.getVitesse()) + this.y);
    }

    public String ToString() {
        return "X:" + this.x + " " + "Y:" + this.y + " " + "Z:" + this.z;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getZ() {
        return this.z;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }
    
}
