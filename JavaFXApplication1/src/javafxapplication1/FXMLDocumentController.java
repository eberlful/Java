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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.Background;
import javax.swing.JOptionPane;

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
    
    //Generator generator = new Generator(feld);
    
    @FXML
    private RadioButton radioButtonleicht;
    
    @FXML
    private RadioButton radioButtonmittel;
    
    @FXML
    private RadioButton radioButtonschwer;
    
    @FXML
    private RadioButton radioButtonja;
    
    private RadioButton radioButtonnein;
    
    int [][] feldGlobal = new int[9][9];
    
    @FXML
    void textChangeFeld11(InputMethodEvent event) {
        System.out.println("Feld11 geändert");
        boolean freigabe = checkNeuesElement(feldGlobal, 0, 0, Integer.getInteger(feld11.getText()));
        if (freigabe == true){
            feldGlobal[0][0] = Integer.getInteger(feld11.getText());
        } else {
            feld11.setStyle("-fx-text-fill: red");
        }
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        
    }
    
    @FXML
    void erzeugenStart(ActionEvent event) {
        System.out.println("Erzeugen gedrückt");
        //aendereFeld(8,8,"5");
        if (radioButtonleicht.isSelected()){
            erzeugeFeld(1);
        } else if (radioButtonmittel.isSelected()){
            erzeugeFeld(2);
        } else if (radioButtonschwer.isSelected()){
            erzeugeFeld(3);
        } else {
            JOptionPane.showMessageDialog(null,"Es wurde keine Schwierigkeitsauswahl getroffen","Auswahlfehler", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    @FXML
    void loesenStart(ActionEvent event) {
        loesen(feldGlobal);
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
                aendereFeld(i,j,"");
            }
        }
        
        //Random Objekt erzeugen
        Random random = new Random();
        
        //Speichervariable für generiertes Feld
        int neuesElement;
        boolean freigabe = false;
        
        //Erzeugung des Feldes mittels Zufallkoordinaten
        for (int i = 0; i < anzahlFelder; i++){
            do{
               x = random.nextInt(9);
               y = random.nextInt(9);
               System.out.println("X:" + x + " Y: " + y);
            }while(feld[x][y] != 0);
            if (feld[x][y] == 0){
                do {
                    neuesElement = (random.nextInt(8) + 2);
                    System.out.println("Neues Element ist: " + neuesElement);
                    freigabe = checkNeuesElement(feld, x, y, neuesElement);
                    System.out.println("Freigabe: " + freigabe);
                }while(!freigabe);
                System.out.println("Schleife verlassen - Freigabe: " + freigabe);
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

        feldGlobal = feld;
    }
    
    /*
    Funktion checkt bewertet übergebenes Feld mit den Koordinaten und den neuen Wert,
    ob dieser laut Spielregeln eingefügt werden darf
    */
    private boolean checkNeuesElement(int [][] feld, int xKor, int yKor, int neuerWert){
        boolean wertVorhanden = true;
        
        //Checkt horizontale und vertikale Linie auf vorhandenes Element
        for (int i = 0; i < feld.length; i++){
            if (feld[xKor][i] == neuerWert || feld[i][yKor] == neuerWert){
                wertVorhanden = false;
            }
        }
        
        /*
        #################################
        ###       ###       ###       ###
        ###   1   ###   2   ###   3   ###
        ###       ###       ###       ###
        #################################
        ###       ###       ###       ###
        ###   4   ###   5   ###   6   ###
        ###       ###       ###       ###
        #################################
        ###       ###       ###       ###
        ###   7   ###   8   ###   9   ###
        ###       ###       ###       ###
        #################################
        */
        int Xvon = 99;
        int Xbis = 99;
        int Yvon = 99;
        int Ybis = 99;
        //Checkt 3 x 3 Feld
        if (xKor < 3) {
            //1. Feld
            if (yKor < 3){
                Xvon = 0;
                Xbis = 2;
                Yvon = 0;
                Ybis = 2;
            } else if (yKor < 6 && yKor > 2){    //2. Feld
                Xvon = 0;
                Xbis = 2;
                Yvon = 3;
                Ybis = 5;
            } else if (yKor < 9 && yKor > 5){    //3. Feld
                Xvon = 0;
                Xbis = 2;
                Yvon = 6;
                Ybis = 8;
            } else {
                System.out.println("Fehler bei der 3x3 Freigabe");
            }
        } else if (xKor < 6 && xKor > 2){
            //4. Feld
            if (yKor < 3){
                Xvon = 3;
                Xbis = 5;
                Yvon = 0;
                Ybis = 2;
            } else if (yKor < 6 && yKor > 2){   //5. Feld
                Xvon = 3;
                Xbis = 5;
                Yvon = 3;
                Ybis = 5;
            } else if (yKor < 9 && yKor > 5){   //6. Feld
                Xvon = 3;
                Xbis = 5;
                Yvon = 6;
                Ybis = 8;
            } else {
                System.out.println("Fehler bei der 3x3 Freigabe");
            }
        } else if (xKor < 9 && xKor >5){
            //7. Feld
            if (yKor < 3){
                Xvon = 6;
                Xbis = 8;
                Yvon = 0;
                Ybis = 2;
            } else if (yKor < 6 && yKor > 2){   //8. Feld
                Xvon = 6;
                Xbis = 8;
                Yvon = 3;
                Ybis = 5;
            } else if (yKor < 9 && yKor > 5){   //9. Feld
                Xvon = 6;
                Xbis = 8;
                Yvon = 6;
                Ybis = 8;
            } else {
                System.out.println("Fehler bei der 3x3 Freigabe");
            }
        } else {
            System.out.println("Fehler bei der 3x3 Freigabe");
        }
        
        boolean[] array = {false, false, false, false, false, false, false, false, false};
        if (Xvon < 9 && Xbis < 9 && Yvon < 9 && Ybis < 9){
            for (int i = Xvon; i <= Xbis; i++){
                for (int j = Yvon; j <= Ybis; j++){
                    if (feld[i][j] == neuerWert){
                        return false;
                    }
                    /*switch (feld[i][j]){
                        case 1:
                            if (array[0] == true){
                                return false;
                            } else {
                                array[0] = true;
                            }
                            break;
                        case 2:
                            if (array[1] == true){
                                return false;
                            } else {
                                array[1] = true;
                            }
                            break;
                        case 3:
                            if (array[2] == true){
                                return false;
                            } else {
                                array[2] = true;
                            }
                            break;
                        case 4:
                            if (array[3] == true){
                                return false;
                            } else {
                                array[3] = true;
                            }
                            break;
                        case 5:
                            if (array[4] == true){
                                return false;
                            } else {
                                array[4] = true;
                            }
                            break;
                        case 6:
                            if (array[5] == true){
                                return false;
                            } else {
                                array[5] = true;
                            }
                            break;
                        case 7:
                            if (array[6] == true){
                                return false;
                            } else {
                                array[6] = true;
                            }
                            break;
                        case 8:
                            if (array[7] == true){
                                return false;
                            } else {
                                array[7] = true;
                            }
                            break;
                        case 9:
                            if (array[8] == true){
                                return false;
                            } else {
                                array[8] = true;
                            }
                            break;
                    }*/
                }
            }
        } else {
            System.out.println("Fehler bei der 3x3 Zuordnung");
        }
        return wertVorhanden;
    }
    
    private boolean isfine2(int feld[][], int x, int y){
        
        //doppelte Zahl in Zeile
        for (int yi = 0; yi < 9; yi++){
            if (yi != y && feld[x][yi] == feld[x][y]){
                return false;
            }
        }
        
        //doppelte Zahl in Spalte
        for (int xi = 0; xi < 9; xi++){
            if (xi != x && feld[xi][y] == feld[x][y]){
                return false;
            }
        }
        
        //Neuner-Kästchen-Test
        int x1 = (x / 3) * 3;
        int y1 = (y / 3) * 3;
        for (int xk = x1; xk < x1 + 3; xk++){
            for (int yk = y1; yk < y1 + 3; yk++){
                if ((xk != x || yk != y) && feld[xk][yk] == feld[x][y]){
                    return false;
                }
            }
        }
        
        return true;
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
    
    
    
    /*
    --------------------------------------------------------------------------------------------------------------------------
    */
    
    private boolean isfine(int feld[][], int x, int y){
        
        //doppelte Zahl in Zeile
        for (int yi = 0; yi < 9; yi++){
            if (yi != y && feld[x][yi] == feld[x][y]){
                return false;
            }
        }
        
        //doppelte Zahl in Spalte
        for (int xi = 0; xi < 9; xi++){
            if (xi != x && feld[xi][y] == feld[x][y]){
                return false;
            }
        }
        
        //Neuner-Kästchen-Test
        int x1 = (x / 3) * 3;
        int y1 = (y / 3) * 3;
        for (int xk = x1; xk < x1 + 3; xk++){
            for (int yk = y1; yk < y1 + 3; yk++){
                if ((xk != x || yk != y) && feld[xk][yk] == feld[x][y]){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean nextone(int feld[][], int x, int y){
        if (y == 9){
            y = 0;
            x++;
        }
        
        if (x == 9){
            return true;
        }
        
        if (feld[x][y] > 0){
            if (!isfine(feld, x, y)){
                return false;
            }
            return nextone(feld, x, y+1);
        } else {
            for (feld[x][y] = 1; feld[x][y] <= 9; feld[x][y]++){
                if (!isfine(feld, x, y)){
                    continue;
                }
                if (nextone(feld, x, y + 1)){
                    return true;
                }
            }
        }
        feld[x][y] = 0;
        return false;
    }
    
    public int [][] loesen (int [][] feldInPut){
        if (nextone(feldInPut, 0, 0)){
            for (int x = 0; x < 9; x++){
                for (int y = 0; y < 9; y++){
                    aendereFeld(x,y,String.valueOf(feldInPut[x][y]));
                    System.out.print(feldInPut[x][y]);
                }
                System.out.println();
            }
        } else {
            System.out.println("Dieses Sudoku hat keine Lösung");
        }
        
        return feldInPut;
    }
    
}
