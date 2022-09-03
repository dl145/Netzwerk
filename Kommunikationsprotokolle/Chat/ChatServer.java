import java.io.*;
import java.awt.*;
import java.awt.event.*;

import java.net.*;

import javax.swing.*;

public class ChatServer extends Server{
  
  private JTextArea protokoll = new JTextArea();
   
  private TeilnehmerListe liste = new TeilnehmerListe();
  
  public ChatServer(int pPortNr, JTextArea protokoll) {
    super(pPortNr);
    this.protokoll = protokoll;
  }
  
  public void processMessage(String pClientIP, int pClientPort, String pMessage){
    
    String basic = new String();
    
    if (pMessage.startsWith("NAME: ")) {
      
      String nickname = pMessage;
      nickname = nickname.replace("NAME: ", "");
      basic = nickname;
      liste.hinzufuegen(pClientIP, pClientPort, nickname);
      
      send(pClientIP, pClientPort, "+OK: Du bist angenommen");
      
    } else if (pMessage.startsWith("ALL: ")) {
        String toAll = pMessage;
        toAll = toAll.replace("ALL: ", "");
        
        sendToAll("+MSG von " + liste.gibName(pClientIP,  pClientPort) + ": " + toAll);
        
      } else if (pMessage.startsWith("ONE: ")) {
          
          String toOne = pMessage;
          toOne = toOne.replace("ONE: ", "");        //NAME:MSG
          
          
          String toOneMsg = toOne;
          String [] split = toOneMsg.split(":");
          String name = split[0];
          String msg = split[1];
          
          send(liste.gibIP(name), liste.gibPort(name), msg);
          
        } else if (pMessage.equals("NICK")) {
            send(pClientIP, pClientPort, liste.gibAlleNamen());
          }
            else if (pMessage.equals("QUIT")) {
              
              processClosingConnection(pClientIP, pClientPort);
              liste.entfernen(pClientIP, pClientPort);
              liste.entfernen(basic); 
              
            }
    
    
    
  }
  
  public void processNewConnection(String pClientIP, int pClientPort){
    send(pClientIP, pClientPort, "+NAME");
  }
  
  public void processClosingConnection(String pClientIP, int pClientPort){
    sendToAll("+MSG: Benutzer x angemeldet");
    closeConnection(pClientIP, pClientPort);
  }
  
}
