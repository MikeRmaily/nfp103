/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Khalil
 */
public class Client {

    public static int port =9999;
    public static int counter = 1;
    public static Vector position = new Vector(3);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        try {
            //Set First Random Position
            Random rn = new Random();
            setPosition(rn.nextInt(10) + 1, rn.nextInt(10) + 1, rn.nextInt(10) + 1);

            //Open Socket with Remote Server
            System.out.println(port);
            Socket sock = new Socket("localhost", port);
            PrintStream pr = new PrintStream(sock.getOutputStream());

            //Get Info from server
            BufferedReader Ack = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String StrAck;
            //Push Position to server
            Vector v;
            int i = 1;
            while (true) {

                if (i % 5 == 0) {
                    v = getPosition(2, 10, 5, 3);
                } else {
                    v = getPosition(1, 0, 0, 0);
                }

                String varDumb = v.toString();
                //Push info to server
                System.out.println(varDumb);
                pr.println(varDumb);
                i++;

                StrAck = Ack.readLine();

                if (StrAck.equals("crash")) {
                    System.out.println("Stop you gonna crash!");
                    System.exit(0);
                } else {
                    System.out.println(StrAck);
                }
                Thread.sleep(1000);

            }
        } catch (IOException ex) {
            System.out.print(ex);
        }
    }

    public static void setPosition(int x, int y, int z) {
        if (position.isEmpty()) {
            position.add(x);
            position.add(y);
            position.add(z);
        } else {
            position.set(0, x);
            position.set(1, y);
            position.set(2, z);
        }
    }

    public static int getPositionX() {
        int x = (int) position.get(0);
        return x;
    }

    public static int getPositionY() {
        int y = (int) position.get(1);
        return y;
    }

    public static int getPositionZ() {
        int z = (int) position.get(2);
        return z;
    }

    public static void updateVitesse(int x, int y, int z) {
        setPosition(getPositionX() + x, getPositionY() + y, getPositionZ() + z);
    }

    public static void cap(float angle, float vitesse, float attitude) {

    }

    public static Vector getPosition(int type, int x, int y, int z) {

        switch (type) {
            case 1:
                setPosition(getPositionX() + 1, getPositionY() + 1, getPositionZ() + 1);
                break;
            case 2:
                updateVitesse(x, y, z);
                break;
        }

        return position;
    }
}
