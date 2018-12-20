/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomPackages;

import Moka7.S7;
import Moka7.S7Client;
import java.util.Date;
import javafx.beans.property.ListProperty;

/**
 *
 * @author marku
 */
public class Fehler {
    private boolean ueberwachung;
    private int art;    // 0 = DB, 1 = Eingang, 2 = Ausgang
    private boolean zustand; // true = Fehler vorhanden
    private Steuerung clientST;
    private S7Client client;
    private int db;
    private int dbByte;
    private int dbBit;
    private int merkerByte;
    private int merkerBit;
    private String fehlerText;
    private String fehlername;
    private int fehlernummer;
    private static int fehlerCounter = 0;
    private String pfadFehlerBehebung;
    // Muss noch implementiert werden
    private Date last;
    //private ListViewItem listViewItem;

    public Fehler(Steuerung clientST, boolean ueberwachung, int db, int dbByte, int dbBit, int art, int merkerByte, int merkerBit, String fehlertext, String fehlername, int fehlernummer)
    {
        this.clientST = clientST;
        this.client = clientST.getS7Client();
        this.ueberwachung = ueberwachung;
        this.db = db;
        this.dbByte = dbByte;
        this.dbBit = dbBit;
        this.art = art; // 0 = DB, 1 = M
        this.merkerByte = merkerByte;
        this.merkerBit = merkerBit;
        this.fehlerText = fehlertext;
        this.fehlername = fehlername;
        this.fehlernummer = fehlernummer;
    }

    public String getFehlername() {
        return fehlername;
    }
    
    @Override
    public String toString(){
        return this.fehlername;
    }

    public boolean checkZustand()
    {
        if (client.Connected)
        {
            if (art == 0)
            {
                byte[] buffer = new byte[1];
//                int result = client.DBRead(db, dbByte, 1, buffer);
                zustand = S7.GetBitAt(buffer, 0, dbBit);
//                txtBox.AppendText(Environment.NewLine + DateTime.Now.ToString() + "DBRead-Return-Value: " + result.ToString() + " Zustand: " + zustand.ToString() + " Buffer: " + buffer.ToString());
                checkUueberwachung();
                return zustand;
            } else if (art == 1)
            {
                byte[] buffer = new byte[1];
//                int result = client.MBRead(merkerByte,1,buffer);
                zustand = S7.GetBitAt(buffer, 0, merkerBit);
//                txtBox.AppendText(Environment.NewLine + DateTime.Now.ToString() + "MBRead-Return-Value: " + result.ToString() + " Zustand: " + zustand.ToString() + " Buffer: " + buffer.ToString());
                //txtBox.AppendText("\n" + DateTime.Now.ToString() + "MBRead-Return-Value: " + result.ToString() + " Zustand: " + zustand.ToString() + " Buffer: " + buffer.ToString());
                //txtBox.AppendText("\n" + DateTime.Now.ToString() + "MBRead-Return-Value: " + result.ToString() + " Zustand: " + zustand.ToString() + " Buffer: " + buffer.ToString());
                checkUueberwachung();
                return zustand;
            }
            else
            {
//                txtBox.AppendText(Environment.NewLine + DateTime.Now.ToString() + " keine Verbindung");
                return false;
            }

        } else
        {
            zustand = false;
//            txtBox.AppendText(Environment.NewLine + DateTime.Now.ToString() + "\n Nicht Online");
            //Exception ex = new Exception();
            //throw ex;
            return false;
        }
    }

    public boolean checkZustandNormal()
    {
        if (client.Connected)
        {
            if (art == 0)
            {
                byte[] buffer = new byte[1];
//                int result = client.DBRead(db, dbByte, 1, buffer);
                zustand = S7.GetBitAt(buffer, 0, dbBit);
                //txtBox.AppendText(Environment.NewLine + DateTime.Now.ToString() + "DBRead-Return-Value: " + result.ToString() + " Zustand: " + zustand.ToString() + " Buffer: " + buffer.ToString());
                checkUueberwachung();
                return zustand;
            }
            else if (art == 1)
            {
                byte[] buffer = new byte[1];
 //               int result = client.ReadArea(S7.S7AreaMK, merkerByte,  result, buffer);
                zustand = S7.GetBitAt(buffer, 0, merkerBit);
                //txtBox.AppendText(Environment.NewLine + DateTime.Now.ToString() + "MBRead-Return-Value: " + result.ToString() + " Zustand: " + zustand.ToString() + " Buffer: " + buffer.ToString());
                //txtBox.AppendText("\n" + DateTime.Now.ToString() + "MBRead-Return-Value: " + result.ToString() + " Zustand: " + zustand.ToString() + " Buffer: " + buffer.ToString());
                //txtBox.AppendText("\n" + DateTime.Now.ToString() + "MBRead-Return-Value: " + result.ToString() + " Zustand: " + zustand.ToString() + " Buffer: " + buffer.ToString());
                checkUueberwachung();
                return zustand;
            }
            else
            {
                //txtBox.AppendText(Environment.NewLine + DateTime.Now.ToString() + " keine Verbindung");
                return false;
            }

        }
        else
        {
            zustand = false;
            //txtBox.AppendText(Environment.NewLine + DateTime.Now.ToString() + "\n Nicht Online");
            //Exception ex = new Exception();
            //throw ex;
            return false;
        }
    }

    public void checkUueberwachung()
    {
        if(ueberwachung == true && zustand == true)
        {
            //Ueberwachung werfen
        }
    }

    public boolean isUeberwachung() {
        return ueberwachung;
    }

    public int getArt() {
        return art;
    }

    public boolean isZustand() {
        return zustand;
    }

    public int getDb() {
        return db;
    }

    public int getDbByte() {
        return dbByte;
    }

    public int getDbBit() {
        return dbBit;
    }

    public int getMerkerByte() {
        return merkerByte;
    }

    public int getMerkerBit() {
        return merkerBit;
    }

    public String getFehlerText() {
        return fehlerText;
    }

    public int getFehlernummer() {
        return fehlernummer;
    }

    public static int getFehlerCounter() {
        return fehlerCounter;
    }

    public String getPfadFehlerBehebung() {
        return pfadFehlerBehebung;
    }

    public Date getLast() {
        return last;
    }
    
    
}
