/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomPackages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import org.json.JSONArray;
import org.json.JSONObject;

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
    private Benutzer currentUserBenutzer;

    public Verwaltung() {
        currentUser = 0;
        einstellung = new Einstellungen(true, true, currentUserName, currentUser);
    }
    
    public boolean checkUser(String username, String password){
        try {
            String permissionFile = "user.json";
            File file = new File(permissionFile);
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())), "UTF-8");
            JSONObject json = new JSONObject(content);
            FileReader reader = new FileReader(permissionFile);
            BufferedReader br = new BufferedReader(reader);
            String zeile = null;
            StringBuilder text = new StringBuilder();
            while ((zeile = br.readLine()) != null) {                
                text.append(zeile);
                System.out.println(zeile);
            }
            br.close();
            reader.close();
//            JSONObject json = new JSONObject(text);
            //JSONObject che = json.getJSONObject("Checksum");
            String checksum = json.getString("Checksum");
            if (checksum.equals(checksum)) {
                JSONArray user = json.getJSONArray("Benutzer");
                for (int i = 0; i < user.length(); i++) {
                    JSONObject localUser = user.getJSONObject(i);
                    if (username.equals(localUser.getString("Name")) && password.equals(localUser.getString("Passwort"))) {
                        currentUser = localUser.getInt("Zugriffsstufe");
                        currentUserName = username;
                        currentUserBenutzer = new Benutzer(currentUserName, password, currentUser);
                        return true;
                    }
                }
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
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
    
    public Benutzer getBenutzer(){
        return currentUserBenutzer;
    }
    
}
