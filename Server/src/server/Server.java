/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Mike
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            ServerSocket ser = new ServerSocket(9999);
            Socket sock = ser.accept();
            BufferedReader ed = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String tmp;
            int i = 0;
            PrintStream pr = new PrintStream(sock.getOutputStream());
            tmp = ed.readLine();
            System.out.println("I Received:" + tmp);
            pr.println("Initializing");
            while (true) {               
                tmp = ed.readLine();
                System.out.println("I Received:" + tmp);  
                if (i == 10) {
                    pr.println("crash");
                     System.out.println("We gonna crash!");
                }else{
                     pr.println("Recieved");
                }
                 i++;
            }

        } catch (Exception ex) {
        }
    }

}
