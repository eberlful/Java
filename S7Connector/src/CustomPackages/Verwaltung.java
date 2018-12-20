/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomPackages;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author marku
 */
public class Verwaltung implements Serializable{
    private ArrayList<Steuerung> controlList = new ArrayList<Steuerung>();
    private ArrayList<Fertigung> fertigungList = new ArrayList<Fertigung>();
    private ArrayList<Linie> linienList = new ArrayList<Linie>();
    private int currentUser;    // 99 = Root
    private String currentUserName = "";
    private Einstellungen einstellung;
    private ArrayList<Fehler> ueberwachungsFehler = new ArrayList<Fehler>();
    private ArrayList<Fehler> lastFehler = new ArrayList<Fehler>();

    public Verwaltung() {
        currentUser = 0;
        einstellung = new Einstellungen(true, true, currentUserName, currentUser);
    }
    
    public boolean checkUser(String username, String password){
        if (username.equals("Root") && password.equals("root")) {
            currentUser = 99;
            currentUserName = username;
            return true;
        } else {
            return false;
        }
    }

    public void addLinie(Linie linie){
        linienList.add(linie);
    }

    public ArrayList<Linie> getLinienListe(){
        return this.linienList;
    }
    
    public Einstellungen getEinstellung(){
        return einstellung;
    }
    
    public void setEinstellungen(Einstellungen ein){
        this.einstellung = ein;
    }
    
    public void addSteuerung(Steuerung steuerung){
        controlList.add(steuerung);
    }
    
    public void addFertigung(Fertigung fertigung){
        fertigungList.add(fertigung);
    }

    public ArrayList<Steuerung> getControlList() {
        return controlList;
    }

    public void setControlList(ArrayList<Steuerung> controlList) {
        this.controlList = controlList;
    }

    public ArrayList<Fertigung> getFertigungList() {
        return fertigungList;
    }

    public void setFertigungList(ArrayList<Fertigung> fertigungList) {
        this.fertigungList = fertigungList;
    }

    public int getCurrentUser() {
        return currentUser;
    }

    /*public void setCurrentUser(int currentUser) {
        this.currentUser = currentUser;
    }*/

    public String getCurrentUserName() {
        return currentUserName;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }
    
    
    
}
