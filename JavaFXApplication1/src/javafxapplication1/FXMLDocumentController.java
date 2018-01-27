/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author marku
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        
    }
    
    @FXML
    void erzeugenStart(ActionEvent event) {
        System.out.println("Erzeugen gedrückt");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /*
    Schwierigkeitsgrad:
    1 = leicht
    2 = mittel
    3 = schwer
    */
    public void erzeugeFeld(int schwierigkeitsgrad){
        int anzahlFelder;   //Anzahl der erzeugten Felder für den Start
        switch(schwierigkeitsgrad){
            case 1:
                anzahlFelder = 20;
                break;
            case 2:
                anzahlFelder = 15;
                break;
            case 3:
                anzahlFelder = 10;
                break;
            default:
                anzahlFelder = 0;
        }
        
        //Koordinatenvariablen erzeugen
        int x;
        int y;
        
        //Feld erzeugen und mit null initiallisiern
        int[][] feld = new int[9][9];
        for (int i = 0; i < feld.length; i++){
            for (int j = 0; j < feld[i].length; j++){
                feld[i][j] = 0;
            }
        }
        
        //Random Objekt erzeugen
        Random random = new Random();
        
        //Speichervariable für generiertes Feld
        int neuesElement;
        boolean freigabe;
        
        //Erzeugung des Feldes mittels Zufallkoordinaten
        for (int i = 0; i < anzahlFelder; i++){
            do{
               x = random.nextInt(8) + 1;
               y = random.nextInt(8) + 1;
            }while(feld[x][y] != 0);
            if (feld[x][y] == 0){
                do {
                    neuesElement = random.nextInt(8) + 1;
                }while((freigabe = checkNeuesElement(feld, x, y, neuesElement)));
                if (freigabe){
                    feld[x][y] = neuesElement;
                } else {
                    System.out.println("Fehler bei der Freigabe");
                }
            } else {
                System.out.println("Fehler bei der Zufallserzeugung!!!");
            }
        }        
    }
    
    /*
    Funktion checkt bewertet übergebenes Feld mit den Koordinaten und den neuen Wert,
    ob dieser laut Spielregeln eingefügt werden darf
    */
    private boolean checkNeuesElement(int [][] feld, int xKor, int yKor, int neuerWert){
        boolean wertVorhanden = false;
        
        //Checkt horizontale und vertikale Linie auf vorhandenes Element
        for (int i = 0; i < feld.length; i++){
            if (feld[xKor][i] == neuerWert || feld[i][yKor] == neuerWert){
                wertVorhanden = false;
            }
        }
        
        //Checkt 3 x 3 Feld
        
        return wertVorhanden;
    }
}
