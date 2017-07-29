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
        try{
            ServerSocket ser = new ServerSocket(9999);
            Socket sock = ser.accept();
            BufferedReader ed = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String tmp = ed.readLine();
            System.out.print("I Received:" + tmp);
            
            PrintStream pr = new PrintStream(sock.getOutputStream());
            String ack = "Yup I got it";
            pr.println(ack);
        }
        catch (Exception ex){}
    }
    
}
