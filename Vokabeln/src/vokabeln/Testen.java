/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vokabeln;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

/**
 *
 * @author marku
 */
public class Testen {
    
    private String pfad;
    private String [] vokabeln;
    
    public Testen(String pfad){
        this.pfad = pfad;
        ladeDatei();
    }
    
    /*
    Lade Datei in Array
    | -> Splittet einzelne Vokablen voneinander
    */
    private void ladeDatei(){
        
        File file = new File(this.pfad);
        
        if (!file.canRead() || !file.isFile()){
            System.out.println("Fehler beim laden der Datei");
        }
        
        BufferedReader in = null;
        
        try {
            in = new BufferedReader(new FileReader(this.pfad));
            String zeile = null;
            String stringZeile = null;
            while((zeile = in.readLine()) != null){
                stringZeile = stringZeile + zeile;
            }
            
            vokabeln = stringZeile.split("|");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /*
    0 = Englisch
    1 = Deutsch
    
    wortpaar[0] -> Deutsch
    wortpaar[1] -> Englsich
    wortpaar[2] -> Anzahl der richtigen Versuche
    wortpaar[3] -> Anzahl der falschen Versuche
    
    Bsp.:   hallo,hello,anzahlrichtig,anzahlfalsch|
    */
    public void naechstesWort(){
        Random random = new Random();
        int zufall = random.nextInt(vokabeln.length);
        int sprache = random.nextInt(1);
        
        String [] wortpaar = vokabeln[zufall].split(",");
        
    }
}
