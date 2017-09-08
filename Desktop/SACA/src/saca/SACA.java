/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike Rmaily
 */
public class SACA {

    private ObjectInputStream input;
    private ObjectOutputStream output;
    HashMap<String, Avion> hmap = new HashMap<String, Avion>();
    public ServerSocket ser;
    public Socket sock;
    private String tmp;
    public static int port = 1000;

    public SACA() {

        System.out.println("Initialising server");
        openCommunication();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public String getInfo() {

        tmp = input.toString();
        // PrintStream pr = new PrintStream(sock.getOutputStream());
        //tmp = ed.readLine();
        // ed.reset();

        return tmp;
    }

    Avion InitAirplane() {
        Avion myavion = new Avion(sock);
        myavion.start();
        hmap.put(myavion.getFlightname(), myavion);
        return myavion;
    }

    private void openCommunication() {
        try {
            ser = new ServerSocket(port);

            sock = ser.accept();

            input = new ObjectInputStream(sock.getInputStream());

        } catch (IOException e) {
            System.err.println("Could not listen on port " + port);

        }
    }

}
