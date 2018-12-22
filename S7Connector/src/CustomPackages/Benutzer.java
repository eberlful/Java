/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomPackages;

/**
 *
 * @author marku
 * @version 2.0
 * 
 * <table summary="" border="1">
 * <tr>
 * <td>f</td><td>Root</td><td>Superadmin</td>
 * </tr>
 * </table>
 */
public class Benutzer {
    private String userName;
    private String password;
    private int protectionLevel;
    /*
    99 = Root-Rechte
    90 = Super-Admin-Rechte
    80 = Admin-Rechte
    70 = Normal-User-Rechte
    00 = Default-User-Rechte
    */
    
    /**
     * Ein neuer Benutzer wird geladen.
     * @param name Username des Benutzers
     * @param password Passwort des Benutzers
     * @param userLevel Zugriffsstufe des Benutzers
     */
    public Benutzer(String name, String password, int userLevel){
        this.userName = name;
        this.password = password;
        this.protectionLevel = userLevel;
    }
    
    /**
     *
     * @param aktion Übergibt die auszuführende Aktion.
     * <br> <h2>Liste der Aktionen:</h2>
     * <ul>
     * <li>1 = Neue Fertigung</li>
     * <li>2 = Neue Steuerung</li>
     * <li>3 = Neuer Fehler</li>
     * </ul>
     * @return Informiert, ob der aktuelle User eine gegebe Funktion ausführen darf.
     */
    public boolean hasPermission(int aktion){
        if (true) {
            return true;
        } else{
            return false;
        }
    }
}
