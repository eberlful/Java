/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomPackages;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author marku
 */
public class RegExParserThread extends Thread{
    private Steuerung steuerung;
    private String pfad;
    private String pattern = "\\<";
    
    @Override
    public void run() {
        FileReader reader = null;
        BufferedReader br = null;
        try {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            reader = new FileReader(pfad);
            br = new BufferedReader(reader);
            String line = "";
            StringBuilder text = new StringBuilder();
            Pattern p = Pattern.compile(pattern);
            Matcher m = null;
            while ((line = br.readLine()) != null) {
                m = p.matcher(line);
                while (m.find()) {                    
                    System.out.println(m.start());
                }
                text.append(line);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegExParserThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegExParserThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(RegExParserThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public RegExParserThread(String pfad, Steuerung steuerung){
        this.pfad = pfad;
        this.steuerung = steuerung;
    }
    
    public synchronized void createFault(Steuerung steuerung, boolean ueberwachung, int db, int dbByte, int dbBit, int art, int merkerByte, int merkerBit, String fehlertext, String fehlername, int fehlernummer) throws Exception{
        Fehler fehler = new Fehler(steuerung, ueberwachung, db, dbByte, dbBit, art, merkerByte, merkerBit, fehlertext, fehlername, fehlernummer);
        if (steuerung != null) {
            steuerung.addFehler(fehler);
        } else {
            throw new Exception("Steuerung = NULL, keine Zugriff möglich");
        }
    }
}
