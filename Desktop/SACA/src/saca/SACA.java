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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    private String tmp;
    public static int port = 9999;

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

    Avion InitAirplane() {

        Avion myavion = new Avion();
        myavion.start();
        hmap.put(myavion.getFlightname(), myavion);
        return myavion;

    }

    public String getInfo() {

        return tmp;
    }

    public void openCommunication() {
        final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(10);

        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                try {
                    ser = new ServerSocket(port);
                    System.out.println("Waiting for clients to connect...");
                    while (true) {
                        Socket clientSocket = ser.accept();
                        clientProcessingPool.submit(new ClientTask(clientSocket));
                        BufferedReader ed = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        tmp = ed.readLine();
                        
                        System.out.println("Socket Message: "+ tmp);
                    }
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        };
        Thread serverThread = new Thread(serverTask);
        serverThread.start();

    }

    private class ClientTask implements Runnable {

        private final Socket clientSocket;

        private ClientTask(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {

            System.out.println("Got a client !");

            // Do whatever required to process the client's request
        }

    }

}
