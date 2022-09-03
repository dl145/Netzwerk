/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 17.06.2018
  * @author Benjamin Reichelt
  */
public class TeilnehmerListe {
  // Anfang Variablen
  private List<Teilnehmer> liste;

  // Ende Variablen
  public TeilnehmerListe() {
    liste = new List<Teilnehmer>();
  }

  // Anfang Ereignisprozeduren
  public boolean hinzufuegen(String ip, int port, String nick) {
    Teilnehmer teilnehmer = new Teilnehmer(ip, port, nick);

    if (gibPort(nick) == -1) {
      liste.append(teilnehmer);

      return true;
    } else {
      return false;
    }
  }

  public boolean entfernen(String nick) {
    if (this.gibPort(nick) != -1) {
      liste.remove();

      return true;
    } else {
      return false;
    }
  }

  public boolean entfernen(String ip, int port) {
    if (this.gibName(ip, port) != null) {
      liste.remove();

      return true;
    } else {
      return false;
    }
  }

  public String gibIP(String name) {
    for (liste.toFirst(); liste.hasAccess(); liste.next()) {
      if (liste.getContent().gibName().equals(name)) {
        return liste.getContent().gibIP();
      }
    }

    return null;
  }

  public int gibPort(String name) {
    for (liste.toFirst(); liste.hasAccess(); liste.next()) {
      if (liste.getContent().gibName().equals(name)) {
        return liste.getContent().gibPort();
      }
    }

    return -1;
  }

  public String gibName(String ip, int port) {
    for (liste.toFirst(); liste.hasAccess(); liste.next()) {
      if (liste.getContent().gibIP().equals(ip) &&
            (liste.getContent().gibPort() == port)) {
        return liste.getContent().gibName();
      }
    }

    return null;
  }

  public String gibAlleNamen() {
    String s = "";

    for (liste.toFirst(); liste.hasAccess(); liste.next()) {
      s = s + liste.getContent().gibName() + ",";
    }

    s = s.substring(0, s.length() - 1);

    return s;
  }

  // Ende Ereignisprozeduren
}
