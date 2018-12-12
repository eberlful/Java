/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomPackages;

import java.io.Serializable;

/**
 *
 * @author marku
 */
public class Einstellungen implements Serializable{
    private boolean console;
    private boolean dbAktiv;
    private String ipAdresseDB;
    private int zykluszeit;

    public Einstellungen(boolean console, boolean dbAktiv, String ipAdresseDB, int zykluszeit) {
        this.console = console;
        this.dbAktiv = dbAktiv;
        this.ipAdresseDB = ipAdresseDB;
        this.zykluszeit = zykluszeit;
    }

    public boolean isConsole() {
        return console;
    }

    public void setConsole(boolean console) {
        this.console = console;
    }

    public boolean isDbAktiv() {
        return dbAktiv;
    }

    public void setDbAktiv(boolean dbAktiv) {
        this.dbAktiv = dbAktiv;
    }

    public String getIpAdresseDB() {
        return ipAdresseDB;
    }

    public void setIpAdresseDB(String ipAdresseDB) {
        this.ipAdresseDB = ipAdresseDB;
    }

    public int getZykluszeit() {
        return zykluszeit;
    }

    public void setZykluszeit(int zykluszeit) {
        this.zykluszeit = zykluszeit;
    }
    
    
}
