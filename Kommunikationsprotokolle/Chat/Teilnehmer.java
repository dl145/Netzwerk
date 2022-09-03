/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 24.03.2009
  * @author
  */
public class Teilnehmer {
  // Anfang Variablen
  private String ip;
  private int port;
  private String name;

  // Ende Variablen
  public Teilnehmer(String ip, int port, String name) {
    this.name = name;
    this.ip = ip;
    this.port = port;
  }

  // Anfang Ereignisprozeduren
  public String gibIP() {
    return ip;
  }

  public int gibPort() {
    return port;
  }

  public String gibName() {
    return name;
  }

  // Ende Ereignisprozeduren
}
