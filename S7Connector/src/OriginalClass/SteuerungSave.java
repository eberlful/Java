/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OriginalClass;

import CustomPackages.Fehler;
import CustomPackages.Fertigung;
import CustomPackages.Linie;
import CustomPackages.Steuerung;
import MessageBox.MessageBox;
import Moka7.S7Client;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.TreeItem;

/**
 *
 * @author marku
 */
public class SteuerungSave {
    private int modus; // 0 = Fertigung
    private ArrayList<FehlerSave> fehlerListe;
    private String ipAdresse;
    private int rack;
    private int slot;
    private String name;
    private FertigungSave fertigung;
    private int datenbaustein;
    private int dbByte;
    private int dbBit;
    private int verbindung;
    //private S7Client client;
    private LinieSave linie;
    private boolean run;
    private boolean verbindungsStatus;
    private boolean runLast;

    public SteuerungSave(int modus, String name) {
        this.modus = modus;
        this.name = name;
    }
    
    @Override
    public String toString(){
        System.out.println("To-String");
        if (modus == 0) {
            return name;
        } else {
            return name;
        }
    }
    
    public FertigungSave getFertigung(){
        return this.fertigung;
    }
    
    public String getRack(){
        return ((Integer)rack).toString();
    }
    
    public String getSlot(){
        return ((Integer)slot).toString();
    }
    
    public String getFunktionsbit(){
        return "DB" + datenbaustein + ".DBX" + dbByte + "." + dbBit;
    }
    
    public String getZustand(){
        return ((Boolean)verbindungsStatus).toString();
    }
    
    public String getIP(){
        return ipAdresse;
    }

    public SteuerungSave(String ipAdresse, int rack, int slot, String name, FertigungSave fertigung, int datenbaustein, int dbByte, int dbBit, LinieSave linie)
        {
            this.fehlerListe = new ArrayList<FehlerSave>();
            this.ipAdresse = ipAdresse;
            this.rack = rack;
            this.slot = slot;
            this.name = name;
            this.fertigung = fertigung;
            this.datenbaustein = datenbaustein;
            this.dbByte = dbByte;
            this.dbBit = dbBit;
            this.linie = linie;
            //client = new S7Client();
            //this.verbindung = client.ConnectTo(this.ipAdresse, this.rack, this.slot);
            //verbindungsStatus = client.Connected;
            
            //status.addListener(new ChangeListener<Boolean>);
        }
    
//        public boolean checkOnline()
//        {
//            //verbindungsStatus = client.Connected;
//            //return client.Connected;
//        }
        
        public LinieSave getLinie(){
            return linie;
        }

//        public List<Fehler> getFehlerListe()
//        {
//            return this.fehlerListe;
//        }

        public String getName()
        {
            return this.name;
        }

//        public S7Client getS7Client()
//        {
//            return client;
//        }

        public void addFehler(boolean ueberwachung, int db, int dbByte, int dbBit)
        {
            //Fehler fehler = new Fehler(client, ueberwachung, datenbaustein, dbByte, dbBit);
        }

        public void addFehler(FehlerSave fehler)
        {
            if(fehler != null)
            {
                fehlerListe.add(fehler);
                System.out.println(fehler.getFehlername() + " hinzugefügt");
                //Console.WriteLine("Es wurde eine NULL übergeben" + "Steuerung, addFehler");
            }
            else
            {
                System.out.println("-----------------------------------------");
                System.out.println("Übergebener Fehler hat keine gültige Referenz");
                System.out.println(this.getName());
                System.out.println("-----------------------------------------");
            }
        }

//        public void loadFrame(System.Windows.Forms.IWin32Window window)
//        {
//            frame = window;
//        }

//        public void checkZustand()
//        {
//            try
//            {
//                if (verbindungsStatus)
//                {
//                    Byte[] buffer = new Byte[1];
////                    client.DBGet(buffer, datenbaustein, dbByte);
//                    runLast = run;
////                    run = S7.GetBitAt(buffer, 0, dbBit);
//                }
//                else
//                {
//                    verbindung = client.ConnectTo(ipAdresse, rack, slot);
//                    if (client.Connected)
//                    {
//                        Byte[] buffer = new Byte[1];
////                        client.DBRead(datenbaustein, dbByte, 8, buffer);
////                        run = S7.GetBitAt(buffer, 0, dbBit);
//                    }
//                }
//            }
//            catch (Exception ex)
//            {
//                MessageBox.Show(ex.getMessage() + "\n" + ex.getStackTrace(), "Fehler bei Aktualliserung");
//            }
//        }

    public ArrayList<FehlerSave> getFehlerListe() {
        return fehlerListe;
    }

    public int getDatenbaustein() {
        return datenbaustein;
    }

    public int getDbByte() {
        return dbByte;
    }

    public int getDbBit() {
        return dbBit;
    }
        
        
}
