/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Mike
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try
        {
        Socket sock = new Socket("localhost",9999);
        PrintStream pr = new PrintStream(sock.getOutputStream());
        System.out.print("Enter Something");
        InputStreamReader rd = new InputStreamReader(System.in);
        BufferedReader ed = new BufferedReader(rd);
        String temp = ed.readLine();
        pr.println(temp);
        BufferedReader Ack = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        String StrAck = Ack.readLine();
        System.out.print(StrAck);
        }
        catch (Exception ex){}
    }
    
}
