/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomPackages;

import java.io.File;
import java.util.ArrayList;
import javafx.beans.property.StringProperty;

/**
 *
 * @author marku
 */
public class UserWriter extends Thread{
    private ArrayList<Benutzer> userList;
    private String pfad;
    private StringProperty property;
    
    public UserWriter(ArrayList<Benutzer> userList, String pfad, StringProperty property){
        this.userList = userList;
        this.pfad = pfad;
        this.property = property;
    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        try {
            File currentFile = new File(pfad);
            
        } catch (Exception e) {
        }
    }
    
    
}
