import java.awt.*;
import java.awt.event.*;

import java.text.SimpleDateFormat;

import java.util.Base64;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.*;
import javax.swing.event.*;


/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 21.06.2018
 * @author Benjamin Reichelt
 */
public class MailClientGUI extends JFrame {
  // Anfang Attribute
  private MailClient mc;
  private JLabel lSendMail = new JLabel();
  private JLabel lTo = new JLabel();
  private JTextField jtfTo = new JTextField();
  private JLabel lSubject = new JLabel();
  private JTextField jtfSubject = new JTextField();
  private JTextArea jtAeMailtext = new JTextArea("");
  private JScrollPane jtAeMailtextScrollPane = new JScrollPane(jtAeMailtext);
  private JLabel lUsername = new JLabel();
  private JLabel lPassword = new JLabel();
  private JTextField jtfUsername = new JTextField();
  private JTextField jtfPassword = new JTextField();
  private JButton btnLogin = new JButton();
  private JButton btnSend = new JButton();
  private String server;
  private String username;
  private String password;
  private JTextField jtfPort = new JTextField();
  private Date date = new Date();
  private JLabel lFrom = new JLabel();
  private JTextField jtfFrom = new JTextField();
  private JCheckBox jcbFrom = new JCheckBox();
  private JLabel lPort = new JLabel();
  private JButton btnLogout = new JButton();
  private JLabel lServer1 = new JLabel();
  private JTextField jtfServer = new JTextField();
  private JTextArea jTextAreaLog = new JTextArea("");
  private JScrollPane jTextAreaLogScrollPane = new JScrollPane(jTextAreaLog);

  // Ende Attribute
  public MailClientGUI() {
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    int frameWidth = 391; 
    int frameHeight = 627;
    setSize(frameWidth, frameHeight);

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("MailClientGUI");
    setResizable(false);

    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    jTextAreaLogScrollPane.setBounds(8, 416, 361, 153);
    cp.add(jTextAreaLogScrollPane);
    lSendMail.setBounds(8, 8, 316, 47);
    lSendMail.setText("Send Mail");
    lSendMail.setFont(new Font("Dialog", Font.BOLD, 36));
    lSendMail.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lSendMail);
    lTo.setBounds(8, 192, 66, 28);
    lTo.setText("To:");
    lTo.setVisible(false);
    cp.add(lTo);
    jtfTo.setBounds(80, 192, 206, 28);
    jtfTo.setVisible(false);
    jtfTo.setText("benjamin.reichelt@ema.nrw.schule");
    cp.add(jtfTo);
    lSubject.setBounds(8, 224, 66, 28);
    lSubject.setText("Subject:");
    lSubject.setVisible(false);
    cp.add(lSubject);
    jtfSubject.setBounds(80, 224, 206, 28);
    jtfSubject.setVisible(false);
    jtfSubject.setText("Die eMailGUI funkioniert");
    cp.add(jtfSubject);
    jtAeMailtextScrollPane.setBounds(8, 256, 361, 153);
    jtAeMailtext.setVisible(false);
    jtAeMailtext.setText("Lieber Herr Reichelt,\n\nes hat wirklich funktioniert.\nToll, dass Sie uns so etwas beibrringen.\nIch bin begeistert und freue mich auch Ihre weiteren Projekte.\n\nHochachtungsvoll\nxxx");
    cp.add(jtAeMailtextScrollPane);
    lUsername.setBounds(8, 96, 66, 28);
    lUsername.setText("Username:");
    cp.add(lUsername);
    lPassword.setBounds(8, 128, 66, 28);
    lPassword.setText("Password:");
    cp.add(lPassword);
    jtfUsername.setBounds(80, 96, 206, 28);
    jtfUsername.setText("informatikema2@smart-mail.de");
    cp.add(jtfUsername);
    jtfPassword.setBounds(80, 128, 206, 28);
    jtfPassword.setText("12345678");
    cp.add(jtfPassword);
    btnLogin.setBounds(296, 128, 75, 28);
    btnLogin.setText("Login");
    btnLogin.setMargin(new Insets(2, 2, 2, 2));
    btnLogin.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnLogin_ActionPerformed(evt);
        }
      });
    cp.add(btnLogin);
    btnSend.setBounds(296, 224, 75, 25);
    btnSend.setText("Send");
    btnSend.setMargin(new Insets(2, 2, 2, 2));
    btnSend.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnSend_ActionPerformed(evt);
        }
      });
    btnSend.setVisible(false);
    cp.add(btnSend);
    jtfPort.setBounds(296, 64, 78, 28);
    jtfPort.setText("25");
    cp.add(jtfPort);
    lFrom.setBounds(8, 160, 66, 28);
    jtfFrom.setBounds(80, 160, 206, 28);
    lFrom.setText("From:");
    lFrom.setVisible(false);
    cp.add(lFrom);
    jtfFrom.setVisible(false);
    cp.add(jtfFrom);
    jcbFrom.setBounds(48, 160, 28, 28);
    jcbFrom.setOpaque(false);
    jcbFrom.setVisible(false);
    cp.add(jcbFrom);
    lPort.setBounds(256, 64, 30, 28);
    lPort.setText("Port:");
    cp.add(lPort);
    btnLogout.setBounds(296, 192, 75, 28);
    btnLogout.setText("Logout");
    btnLogout.setMargin(new Insets(2, 2, 2, 2));
    btnLogout.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnLogout_ActionPerformed(evt);
        }
      });
    btnLogout.setVisible(false);
    cp.add(btnLogout);
    lServer1.setBounds(8, 64, 115, 28);
    lServer1.setText("Server:");
    cp.add(lServer1);
    jtfServer.setBounds(80, 64, 166, 28);
    jtfServer.setText("smtp.smart-mail.de");
    cp.add(jtfServer);
    // Ende Komponenten
    setVisible(true);

    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent evt) {
          try {
            if (mc != null) {
             mc.send("QUIT");
            }
            
            Runtime.getRuntime()
                   .exec("taskkill /F /FI \"WINDOWTITLE eq " + getTitle() +
                         "\"");
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      });
  } // end of public MailClientGUI

  // Anfang Methoden
  public static void main(String[] args) {
    new MailClientGUI();
  } // end of main

  public void wait1s() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ignored) {
      System.out.println(ignored);
    }
  }

  public void btnLogin_ActionPerformed(ActionEvent evt) {
    server = this.jtfServer.getText();
    username = this.jtfUsername.getText();
    password = this.jtfPassword.getText();

    //TODO Instanz der Klasse MailClient erzeugen   
    mc = new MailClient(jtfServer.getText(), Integer.parseInt(jtfPort.getText()), jtfUsername.getText(), jtfPassword.getText(),jTextAreaLog);

    while (!mc.isLoggedIn()) {
      wait1s();
    } // end of while

    //GUI-Komponenten zeigen
    this.lTo.setVisible(true);
    this.lSubject.setVisible(true);
    this.jtfTo.setVisible(true);
    this.jtfSubject.setVisible(true);
    this.jtAeMailtext.setVisible(true);
    this.btnSend.setVisible(true);
    this.btnLogout.setVisible(true);
    //this.jcbFrom.setVisible(true);
    //this.lFrom.setVisible(true);
    //this.jtfFrom.setVisible(true);
    this.lUsername.setVisible(false);
    this.lPassword.setVisible(false);
    this.jtfUsername.setVisible(false);
    this.jtfPassword.setVisible(false);
    this.lPort.setVisible(false);
    this.jtfPort.setVisible(false);
    this.btnLogin.setVisible(false);
  } // end of btnLogin_ActionPerformed

  public void btnSend_ActionPerformed(ActionEvent evt) {
    mc.emailSenden(this.jtfTo.getText(), this.jtfSubject.getText(),
                   this.jtAeMailtext.getText());
  } // end of btnSend_ActionPerformed

  public void btnLogout_ActionPerformed(ActionEvent evt) {
    mc.abmelden();
    this.lTo.setVisible(false);
    this.lSubject.setVisible(false);
    this.jtfTo.setVisible(false);
    this.jtfSubject.setVisible(false);
    this.jtAeMailtext.setVisible(false);
    this.btnSend.setVisible(false);
    this.btnLogout.setVisible(false);
    this.jcbFrom.setVisible(false);
    this.lFrom.setVisible(false);
    this.jtfFrom.setVisible(false);
    this.lUsername.setVisible(true);
    this.lPassword.setVisible(true);
    this.jtfUsername.setVisible(true);
    this.jtfPassword.setVisible(true);
    this.lPort.setVisible(true);
    this.jtfPort.setVisible(true);
    this.btnLogin.setVisible(true);
  } // end of btnLogout_ActionPerformed

  // Ende Methoden
} // end of class MailClientGUI
