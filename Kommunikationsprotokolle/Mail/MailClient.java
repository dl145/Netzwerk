import java.text.SimpleDateFormat;

//Zum base64-Codieren des Benutzernamens und des Passwortes
import java.util.Base64;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.*;


public class MailClient extends Client {
  private boolean loggedIn = false;
  private String smtpServer = new String();
  private String username = new String();
  private String password = new String();
  private JTextArea jTextAreaLog;

  public MailClient(String pServer, int pPort, String pUsername,
  String pPassword, JTextArea pLog) {
    super(pServer, pPort);
    this.smtpServer = pServer;
    this.username = pUsername;
    this.password = pPassword;
    this.jTextAreaLog = pLog;
  }

  // Anfang Methoden
  public void processMessage(String pMessage) {
    jTextAreaLog.append(pMessage + "\n");
    
    //Server gruesst
    if (pMessage.startsWith("220")) {
      this.senden("EHLO " + smtpServer);
    } // end of if
    // Server erfragt Authentifikationstyp
    
      else if (pMessage.startsWith("250 8BITMIME")) {
        //TODO
        this.senden("AUTH LOGIN");
      } // end of if-else
      //Benutzername wird erfragt    
      
        else if (pMessage.equals("334 VXNlcm5hbWU6")) {
          //Benutzername in B64 codieren
          byte[] encodedBytes = Base64.getEncoder().encode(username.getBytes());
          String usernameB64 = new String(encodedBytes);
          this.senden(usernameB64);
        } // end of if
        //Passwort wird erfragt      
        
          else if (pMessage.equals("334 UGFzc3dvcmQ6")) {
            //TODO      
            byte[] encodedBytes = Base64.getEncoder().encode(password.getBytes());
            String passwordB64 = new String(encodedBytes);
            this.senden(passwordB64);
          } // end of if-else
            else if (pMessage.equals("235 Authentication successful")) {
              this.loggedIn = true;
            } // end of if-else
    
  } // end of if-else
  
  
  public void senden(String pString) {
    this.send(pString);
    jTextAreaLog.append(pString + "\n");
  }
  
  public void emailSenden(String pTo, String pSubject, String pText) {
    senden("MAIL FROM: " + username);
    wait1s();
    
    this.senden("RCPT TO: " + pTo);  
     wait1s();
    
    this.senden("DATA");
    
     wait1s();
    this.senden("From: informatikema2@smart-mail.de  \nTo: " + pTo + "\nSubject: Ein Test \nDate: Thu, 18 August 2022 12:30:00 \n" + pText);
    
  
    
    
    
    //Mit \n.\n beenden
    senden("\n.\n");
  }
  
  public boolean isLoggedIn() {
    return this.loggedIn;
  }
  
  public void wait1s() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ignored) {
      System.out.println(ignored);
    }
  }
  
  // Ende Methoden
  public String getDate() {
    Date date = new Date();
    
    //Date
    SimpleDateFormat dt = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
    
    //dt.setTimeZone(TimeZone.getTimeZone("CET"));                 
    try {
      date = dt.parse((new Date().toString()));
    } catch (Exception e) {
      System.out.println(e);
    }
    
    return date.toString(); //"THU, 21 Jun 2018 17:25:00");
  }
  
  public void abmelden() {
    //TODO Verbindung durch Protokollbefehl beenden
    
    senden("QUIT");
  }
}
