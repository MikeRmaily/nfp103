/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.SACA;
import java.io.*;
import java.net.ServerSocket;
/**
 *
 * @author Mike
 */
public class SACACommandes implements Runnable {
  SACAServeur _SACAServ; // pour utilisation des méthodes de la classe principale
  BufferedReader _in; // pour gestion du flux d'entrée (celui de la console)
  String _strCommande=""; // contiendra la commande tapée
  Thread _t; // contiendra le thread
  ServerSocket _ss;
  
  //** Constructeur : initialise les variables nécessaires **
  SACACommandes(SACAServeur SACAServ,ServerSocket ss)
  {
    _SACAServ=SACAServ; // passage de local en global
    // le flux d'entrée de la console sera géré plus pratiquement dans un BufferedReader
    _in = new BufferedReader(new InputStreamReader(System.in));
    _t = new Thread(this); // instanciation du thread
    _t.start(); // demarrage du thread, la fonction run() est ici lancée
    _ss = ss;
  }

  //** Methode : attend les commandes dans la console et exécute l'action demandée **
  @Override
  public void run() // cette méthode doit obligatoirement être implémentée à cause de l'interface Runnable
  {
    try
    {
      // si aucune commande n'est tapée, on ne fait rien (bloquant sur _in.readLine())
      while ((_strCommande=_in.readLine())!=null)
      {
        if (_strCommande.equalsIgnoreCase("quit")) // commande "quit" detectée ...
          System.exit(0); // ... on ferme alors le serveur
        else if(_strCommande.equalsIgnoreCase("total")) // commande "total" detectée ...
        {
          // ... on affiche le nombre de clients actuellement connectés
          System.out.println("Nombre de connectes : "+_SACAServ.getNbClients());
          System.out.println("--------");
        }
         else if(_strCommande.equalsIgnoreCase("add")) // commande "add" detectée ...
        {
           new SACAThread(_ss.accept(),_SACAServ); 
        }
        else
        {
          // si la commande n'est ni "total", ni "quit", on informe l'utilisateur et on lui donne une aide
          System.out.println("Cette commande n'est pas supportee");
          System.out.println("Quitter : \"quit\"");
          System.out.println("Nombre de connectes : \"total\"");
          System.out.println("--------");
        }
        System.out.flush(); // on affiche tout ce qui est en attente dans le flux
      }
    }
    catch (IOException e) {}
  } 
}
