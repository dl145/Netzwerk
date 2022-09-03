import java.awt.*;
import java.awt.event.*;

import java.net.*;

import javax.swing.*;


/**
  *
  * Beschreibung
  *
  * @version 17.06.2018
  * @author Benjamin Reichelt
  */
public class ChatServerGUI extends JFrame {
  // Anfang Variablen
  // Anfang Attribute
  private ChatServer chatServer;
  private JButton btnStarten = new JButton();
  private JTextField jtfPort = new JTextField();
  private JLabel lblIPAdresse = new JLabel();
  private JLabel lblPort = new JLabel();
  private JLabel lChatServer = new JLabel();
  private JTextArea jtAVerlauf = new JTextArea("");
  private JScrollPane jtAVerlaufScrollPane = new JScrollPane(jtAVerlauf);

  // Ende Attribute
  // Ende Variablen
  public ChatServerGUI(String title) {
    // Frame-Initialisierung
    super(title);
    addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent evt) {
          dispose();
        }
      });

    int frameWidth = 461; 
    int frameHeight = 558;
    setSize(frameWidth, frameHeight);

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);

    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    btnStarten.setBounds(8, 56, 163, 33);
    btnStarten.setText("Starten");
    btnStarten.setFont(new Font("Dialog", Font.PLAIN, 14));
    cp.add(btnStarten);
    btnStarten.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnStarten_ActionPerformed(evt);
        }
      });

    jtfPort.setBounds(224, 56, 49, 33);
    jtfPort.setText("1000");
    jtfPort.setFont(new Font("Dialog", Font.PLAIN, 14));
    cp.add(jtfPort);
    lblIPAdresse.setBounds(8, 96, 262, 20);
    lblIPAdresse.setText("");
    lblIPAdresse.setFont(new Font("Dialog", Font.PLAIN, 14));
    cp.add(lblIPAdresse);
    lblPort.setBounds(176, 56, 46, 33);
    lblPort.setText("Port:");
    cp.add(lblPort);
    lChatServer.setBounds(8, 8, 263, 47);
    lChatServer.setText("Chat-Server");
    lChatServer.setFont(new Font("Dialog", Font.BOLD, 36));
    lChatServer.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lChatServer);
    jtAVerlaufScrollPane.setBounds(8, 120, 265, 193);
    jtAVerlauf.setEnabled(false);
    jtAVerlauf.setEditable(false);
    cp.add(jtAVerlaufScrollPane);
    // Ende Komponenten
    setResizable(false);
    setVisible(true);
  }

  // Anfang Methoden

  // Anfang Ereignisprozeduren
  public void btnStarten_ActionPerformed(ActionEvent evt) {
    //TODO
    //SERVER starten
    
    
    chatServer = new ChatServer(Integer.parseInt(jtfPort.getText()), jtAVerlauf);
    
    try {
      lblIPAdresse.setText("IP-Adresse: " +
                           InetAddress.getLocalHost().toString()
                                      .substring(InetAddress.getLocalHost()
                                                            .toString()
                                                            .indexOf('/') + 1));
      btnStarten.setEnabled(false);
      this.jtAVerlauf.setEnabled(true);
      this.jtfPort.setEditable(false);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // Ende Ereignisprozeduren
  public static void main(String[] args) {
    new ChatServerGUI("ChatServer");
  }

  // Ende Methoden
}
