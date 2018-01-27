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
public class Sudoku {
    
    int[][] feld = new int[9][9];
    public static int[][] feld2 = {
{ 2, 4, 0, 0, 9, 0, 0, 0, 0 },
{ 5, 1, 0, 0, 0, 3, 4, 0, 9 },
{ 0, 0, 0, 0, 6, 4, 2, 8, 1 },
{ 0, 0, 0, 3, 0, 8, 1, 0, 0 },
{ 1, 0, 7, 0, 0, 0, 0, 5, 3 },
{ 0, 3, 5, 6, 0, 0, 0, 0, 4 },
{ 0, 5, 1, 7, 0, 0, 0, 0, 2 },
{ 0, 0, 3, 0, 2, 0, 0, 0, 0 },
{ 0, 2, 4, 0, 1, 9, 3, 0, 0 }
};
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Generator a = new Generator(feld2);
        a.loesen();
    }
    
}
