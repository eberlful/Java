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
import javafx.scene.control.TextField;

/**
 *
 * @author marku
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private TextField feld11;

    @FXML
    private TextField feld12;

    @FXML
    private TextField feld13;

    @FXML
    private TextField feld21;

    @FXML
    private TextField feld22;

    @FXML
    private TextField feld23;

    @FXML
    private TextField feld31;

    @FXML
    private TextField feld32;

    @FXML
    private TextField feld33;

    @FXML
    private TextField feld14;

    @FXML
    private TextField feld15;

    @FXML
    private TextField feld16;

    @FXML
    private TextField feld24;

    @FXML
    private TextField feld25;

    @FXML
    private TextField feld26;

    @FXML
    private TextField feld34;

    @FXML
    private TextField feld35;

    @FXML
    private TextField feld36;

    @FXML
    private TextField feld17;

    @FXML
    private TextField feld18;

    @FXML
    private TextField feld19;

    @FXML
    private TextField feld27;

    @FXML
    private TextField feld28;

    @FXML
    private TextField feld29;

    @FXML
    private TextField feld37;

    @FXML
    private TextField feld38;

    @FXML
    private TextField feld39;

    @FXML
    private TextField feld41;

    @FXML
    private TextField feld42;

    @FXML
    private TextField feld43;

    @FXML
    private TextField feld51;

    @FXML
    private TextField feld52;

    @FXML
    private TextField feld53;

    @FXML
    private TextField feld61;

    @FXML
    private TextField feld62;

    @FXML
    private TextField feld63;

    @FXML
    private TextField feld44;

    @FXML
    private TextField feld45;

    @FXML
    private TextField feld46;

    @FXML
    private TextField feld54;

    @FXML
    private TextField feld55;

    @FXML
    private TextField feld56;

    @FXML
    private TextField feld64;

    @FXML
    private TextField feld65;

    @FXML
    private TextField feld66;

    @FXML
    private TextField feld47;

    @FXML
    private TextField feld48;

    @FXML
    private TextField feld49;

    @FXML
    private TextField feld57;

    @FXML
    private TextField feld58;

    @FXML
    private TextField feld59;

    @FXML
    private TextField feld67;

    @FXML
    private TextField feld68;

    @FXML
    private TextField feld69;

    @FXML
    private TextField feld71;

    @FXML
    private TextField feld72;

    @FXML
    private TextField feld73;

    @FXML
    private TextField feld81;

    @FXML
    private TextField feld82;

    @FXML
    private TextField feld83;

    @FXML
    private TextField feld91;

    @FXML
    private TextField feld92;

    @FXML
    private TextField feld93;

    @FXML
    private TextField feld74;

    @FXML
    private TextField feld75;

    @FXML
    private TextField feld76;

    @FXML
    private TextField feld84;

    @FXML
    private TextField feld85;

    @FXML
    private TextField feld86;

    @FXML
    private TextField feld94;

    @FXML
    private TextField feld95;

    @FXML
    private TextField feld96;

    @FXML
    private TextField feld77;

    @FXML
    private TextField feld78;

    @FXML
    private TextField feld79;

    @FXML
    private TextField feld87;

    @FXML
    private TextField feld88;

    @FXML
    private TextField feld89;

    @FXML
    private TextField feld97;

    @FXML
    private TextField feld98;

    @FXML
    private TextField feld99;

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        
    }
    
    @FXML
    void erzeugenStart(ActionEvent event) {
        System.out.println("Erzeugen gedrückt");
        erzeugeFeld(1);
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
                }while(!(freigabe = checkNeuesElement(feld, x, y, neuesElement)));
                if (freigabe){
                    feld[x][y] = neuesElement;
                    aendereFeld(x,y, String.valueOf(neuesElement));
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
    
    /*
    Koordinaten gehen von 0 bis 8
    */
    private void aendereFeld(int xKor, int yKor, String element){
        if(xKor >= 9 || yKor >= 9){
            System.out.println("Fehler bei der Adressierung der Funktion aendereFeld");
        }
        if (xKor == 0){
            if(yKor == 0){
                feld11.setText(element);
            }else if(yKor == 1){
                feld12.setText(element);
            }else if(yKor == 2){
                feld13.setText(element);
            }else if(yKor == 3){
                feld14.setText(element);
            }else if(yKor == 4){
                feld15.setText(element);
            }else if(yKor == 5){
                feld16.setText(element);
            }else if(yKor == 6){
                feld17.setText(element);
            }else if(yKor == 7){
                feld18.setText(element);
            }else if(yKor == 8){
                feld19.setText(element);
            }
        }else if(xKor == 1){
            if(yKor == 0){
                feld21.setText(element);
            }else if(yKor == 1){
                feld22.setText(element);
            }else if(yKor == 2){
                feld23.setText(element);
            }else if(yKor == 3){
                feld24.setText(element);
            }else if(yKor == 4){
                feld25.setText(element);
            }else if(yKor == 5){
                feld26.setText(element);
            }else if(yKor == 6){
                feld27.setText(element);
            }else if(yKor == 7){
                feld28.setText(element);
            }else if(yKor == 8){
                feld29.setText(element);
            }
        }else if(xKor == 2){
            if(yKor == 0){
                feld31.setText(element);
            }else if(yKor == 1){
                feld32.setText(element);
            }else if(yKor == 2){
                feld33.setText(element);
            }else if(yKor == 3){
                feld34.setText(element);
            }else if(yKor == 4){
                feld35.setText(element);
            }else if(yKor == 5){
                feld36.setText(element);
            }else if(yKor == 6){
                feld37.setText(element);
            }else if(yKor == 7){
                feld38.setText(element);
            }else if(yKor == 8){
                feld39.setText(element);
            }
        }else if(xKor == 3){
            if(yKor == 0){
                feld41.setText(element);
            }else if(yKor == 1){
                feld42.setText(element);
            }else if(yKor == 2){
                feld43.setText(element);
            }else if(yKor == 3){
                feld44.setText(element);
            }else if(yKor == 4){
                feld45.setText(element);
            }else if(yKor == 5){
                feld46.setText(element);
            }else if(yKor == 6){
                feld47.setText(element);
            }else if(yKor == 7){
                feld48.setText(element);
            }else if(yKor == 8){
                feld49.setText(element);
            }
        }else if(xKor == 4){
            if(yKor == 0){
                feld51.setText(element);
            }else if(yKor == 1){
                feld52.setText(element);
            }else if(yKor == 2){
                feld53.setText(element);
            }else if(yKor == 3){
                feld54.setText(element);
            }else if(yKor == 4){
                feld55.setText(element);
            }else if(yKor == 5){
                feld56.setText(element);
            }else if(yKor == 6){
                feld57.setText(element);
            }else if(yKor == 7){
                feld58.setText(element);
            }else if(yKor == 8){
                feld59.setText(element);
            }
        }else if(xKor == 5){
            if(yKor == 0){
                feld61.setText(element);
            }else if(yKor == 1){
                feld62.setText(element);
            }else if(yKor == 2){
                feld63.setText(element);
            }else if(yKor == 3){
                feld64.setText(element);
            }else if(yKor == 4){
                feld65.setText(element);
            }else if(yKor == 5){
                feld66.setText(element);
            }else if(yKor == 6){
                feld67.setText(element);
            }else if(yKor == 7){
                feld68.setText(element);
            }else if(yKor == 8){
                feld69.setText(element);
            }
        }else if(xKor == 6){
            if(yKor == 0){
                feld71.setText(element);
            }else if(yKor == 1){
                feld72.setText(element);
            }else if(yKor == 2){
                feld73.setText(element);
            }else if(yKor == 3){
                feld74.setText(element);
            }else if(yKor == 4){
                feld75.setText(element);
            }else if(yKor == 5){
                feld76.setText(element);
            }else if(yKor == 6){
                feld77.setText(element);
            }else if(yKor == 7){
                feld78.setText(element);
            }else if(yKor == 8){
                feld79.setText(element);
            }
        }else if(xKor == 7){
            if(yKor == 0){
                feld81.setText(element);
            }else if(yKor == 1){
                feld82.setText(element);
            }else if(yKor == 2){
                feld83.setText(element);
            }else if(yKor == 3){
                feld84.setText(element);
            }else if(yKor == 4){
                feld85.setText(element);
            }else if(yKor == 5){
                feld86.setText(element);
            }else if(yKor == 6){
                feld87.setText(element);
            }else if(yKor == 7){
                feld88.setText(element);
            }else if(yKor == 8){
                feld89.setText(element);
            }
        }else if(xKor == 8){
            if(yKor == 0){
                feld91.setText(element);
            }else if(yKor == 1){
                feld92.setText(element);
            }else if(yKor == 2){
                feld93.setText(element);
            }else if(yKor == 3){
                feld94.setText(element);
            }else if(yKor == 4){
                feld95.setText(element);
            }else if(yKor == 5){
                feld96.setText(element);
            }else if(yKor == 6){
                feld97.setText(element);
            }else if(yKor == 7){
                feld98.setText(element);
            }else if(yKor == 8){
                feld99.setText(element);
            }
        }
    }
    
}
