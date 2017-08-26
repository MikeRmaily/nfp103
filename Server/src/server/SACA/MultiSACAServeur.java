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
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Set;

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
        JTextField nomAvion = new JTextField("", 8);
        JTextField XPosition = new JTextField("", 8);
        JTextField YPosition = new JTextField("", 8);
        panel.add(nomAvion);
        panel.add(XPosition);
        panel.add(YPosition);
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
                    Thread curThread = MultiSACAClient.AddClientThread(nomAvion.getText());
                   
                    socket = s.accept();
                    new SACAServer(socket);
                       
                 
                     //   System.out.println(MultiSACAClient.threadCount());
                   
                        MultiSACAClient.setX(Integer.parseInt(XPosition.getText()));
                        MultiSACAClient.setY(Integer.parseInt(YPosition.getText()));
                       System.out.println(MultiSACAClient.tg1.activeCount());
//                        System.out.println(getRootThreadGroup().activeCount());
                      //  System.out.println(curThread.getName());
                     //   System.out.println(curThread.getThreadGroup().getName());
//                         System.out.println(getRootThreadGroup().getParent());
//                           System.out.println(getAllThreadGroups().length);
                        
//                             Thread[] threads = MultiSACAClient.getAllThreads();
//                            System.out.println("coun" + threads.length);
//                            for (Thread t  : threads) {
//                                   System.out.print("Thread name"  + t.getName());
//                                }
//                            Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//                            Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
//                            System.out.println("------------");
//                            System.out.println("Length" + threadArray.length);
//                             System.out.println("------------");
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

  
                    static ThreadGroup rootThreadGroup = null;
                   static ThreadGroup getRootThreadGroup( ) {
                        if ( rootThreadGroup != null )
                            return rootThreadGroup;
                        ThreadGroup tg = Thread.currentThread( ).getThreadGroup( );
                        ThreadGroup ptg;
                        while ( (ptg = tg.getParent( )) != null )
                            tg = ptg;
                        return tg;
                    }
                   
                static ThreadGroup[] getAllThreadGroups( ) {
                 final ThreadGroup root = getRootThreadGroup( );
              
                 int nAlloc = root.activeGroupCount( );
                 int n = 0;
                 ThreadGroup[] groups;
                 do {
                     nAlloc *= 2;
                     groups = new ThreadGroup[ nAlloc ];
                     n = root.enumerate( groups, true );
                 } while ( n == nAlloc );

                 ThreadGroup[] allGroups = new ThreadGroup[n+1];
                 allGroups[0] = root;
                 System.arraycopy( groups, 0, allGroups, 1, n );
                 return allGroups;
             }
                
              static  Thread[] getAllThreads( ) {
               // final ThreadGroup root = getRootThreadGroup( );
                  final ThreadGroup root = MultiSACAClient.getTg1();
                final ThreadMXBean thbean = ManagementFactory.getThreadMXBean( );
                int nAlloc = thbean.getThreadCount( );
                int n = 0;
                Thread[] threads;
                do {
                    nAlloc *= 2;
                    threads = new Thread[ nAlloc ];
                    n = root.enumerate( threads, true );
                } while ( n == nAlloc );
                return java.util.Arrays.copyOf( threads, n );
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