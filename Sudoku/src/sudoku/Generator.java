/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author marku
 */
public class Generator {
    
    private int[][] feld = new int[9][9];
    
    public Generator(int[][] initFeld){
        this.feld = initFeld;
    }
    
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
    
    public int [][] loesen (){
        if (nextone(this.feld, 0, 0)){
            for (int x = 0; x < 9; x++){
                for (int y = 0; y < 9; y++){
                    System.out.print(this.feld[x][y]);
                }
                System.out.println();
            }
        } else {
            System.out.println("Dieses Sudoku hat keine Lösung");
        }
        
        return this.feld;
    }
}
