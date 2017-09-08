/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import server.SACA.MultiSACAServeur;
import java.net.*;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 *
 * @author Mike
 */
public class MultiSACAClient {
  private static int counter = 0;
  private static int threadcount = 0;
  public static final ThreadGroup tg1 = new ThreadGroup("SACAThreadGroup");  
  
    public static ThreadGroup getTg1() {
        return tg1;
    }
  public int x = 5;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
  public int y = 10;
  public static int threadCount() { 
    return threadcount; 
  }
  
//  public MultiSACAClient(String name) throws IOException, InterruptedException{
//    InetAddress addr = InetAddress.getByName(null);
//    SACAClientThread  thrd = new SACAClientThread(addr, tg1, name);
//    SACAClientThread.sleep(100);
//  }
  
    public Thread AddClientThread(String name) throws UnknownHostException, IOException, InterruptedException{
         InetAddress addr = InetAddress.getByName(null);
         SACAClientThread  thrd = new SACAClientThread(addr, tg1, name);
         SACAClientThread.sleep(1000);
         return thrd;
   }

class SACAClientThread extends Thread {
    
     public int getX(){
            return x; 
        }
     
     public int getY(){
            return y; 
        }
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private int id = counter++;

  public SACAClientThread(InetAddress addr,ThreadGroup tg, String name) throws IOException {
    super(tg, name);
//     System.out.println(tg.getName());
//     System.out.println(name);
    System.out.println("Making client " + id);
    threadcount++;
    socket =
            new Socket(addr, MultiSACAServeur.PORT); // If the creation of the socket fails, 
      // nothing needs to be cleaned up.
    try {    
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
      //  Thread t1 = new Thread(tg1, runnable,"one");  
     this.start();
      //Thread t = Thread.currentThread( );
//      System.out.println(this.getId());
//            System.out.println(this.getState());
//                  System.out.println(this.getThreadGroup().getName());
//      System.out.println(this.getName());
 
      
    } catch(IOException e) {
      // The socket should be closed on any 
      // failures other than the socket 
      // constructor:
      try {
        socket.close();
      } catch(IOException e2) {}
    }
    // Otherwise the socket will be closed by
    // the run() method of the thread.
  }
     
//  public void run() {
//    try {
//    //   System.out.println("hello");
//    for(int i = 0; i < 25; i++) {
//        out.println("Client " + id + "" + i);
//          String str = in.readLine();
//            System.out.println(str);
//     }
//     
//       System.out.println(this.getX());
//      System.out.println(this.getY());
//      out.println("END");
//   
//   
//    } catch(Exception e) {
//    } finally {
//      // Always close it:
//      try {
//        socket.close();
//      } catch(IOException e) {}
//     // threadcount--; // Ending this thread
//    }
//  }
 
}
 

   public Thread[] getAllThreads( ) {
               // final ThreadGroup root = getRootThreadGroup( );
                  final ThreadGroup root = tg1;
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


//public class MultiSACAClient {
//  static final int MAX_THREADS = 40;
//  public static void main(String[] args) 
//      throws IOException, InterruptedException {
//    InetAddress addr = 
//      InetAddress.getByName(null);
//    while(true) {
//      if(SACAClientThread.threadCount() < MAX_THREADS)
//        new SACAClientThread(addr);
//      Thread.currentThread().sleep(100);
//    }
//  }
//}
}