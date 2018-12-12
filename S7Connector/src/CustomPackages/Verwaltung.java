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
    private int currentUser;    // 99 = Root
    private String currentUserName = "";

    public Verwaltung() {
        currentUser = 0;
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
