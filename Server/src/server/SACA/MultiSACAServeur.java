/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.SACA;

import java.io.*;
import java.net.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import client.MultiSACAClient;
/**
 *
 * @author Mike
 */

public class MultiSACAServeur implements ActionListener  {  
  public static final int PORT = 8080;
  public static void main(String[] args)
      throws IOException {
         JFrame frame = new JFrame("JFrame Example");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Add client!");
        JButton button = new JButton();
        button.setText("Press me");
        panel.add(label);
        panel.add(button);
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        button.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                 ServerSocket s = null;
                 Socket socket = null;
                try {
                    //System.out.println("Do Something Clicked");
                    // Blocks until a connection occurs:
                    s = new ServerSocket(PORT);
                    System.out.println("Server Started");
                    MultiSACAClient MultiSACAClient = new MultiSACAClient();
                    MultiSACAClient.AddClient();
                    socket = s.accept();
                    new SACAServer(socket);
                } catch (IOException ex) {
                     try {
                         socket.close();
                     } catch (IOException ex1) {
                         Logger.getLogger(MultiSACAServeur.class.getName()).log(Level.SEVERE, null, ex1);
                     }
                    Logger.getLogger(MultiSACAServeur.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MultiSACAServeur.class.getName()).log(Level.SEVERE, null, ex);
                }
                  finally{
                     try {
                         s.close();
                     } catch (IOException ex) {
                         Logger.getLogger(MultiSACAServeur.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }
                  
                }
        });
  } 

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
class SACAServer extends Thread {
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  public SACAServer(Socket s) 
      throws IOException {
    socket = s;
    in = 
      new BufferedReader(
        new InputStreamReader(
          socket.getInputStream()));
    // Enable auto-flush:
    out = 
      new PrintWriter(
        new BufferedWriter(
          new OutputStreamWriter(
            socket.getOutputStream())), true);
    // If any of the above calls throw an 
    // exception, the caller is responsible for
    // closing the socket. Otherwise the thread
    // will close it.
    start(); // Calls run()
  }
  public void run() {
    try {
      while (true) {  
        String str = in.readLine();
        if (str.equals("END")) break;
        System.out.println("Echoing: " + str);
        out.println(str);
      }
      System.out.println("closing...");
    } catch (IOException e) {
    } finally {
      try {
        socket.close();
      } catch(IOException e) {}
    }
  }
}