/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OriginalClass;

import CustomPackages.Linie;
import CustomPackages.Steuerung;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.control.TreeItem;

/**
 *
 * @author marku
 */
public class FertigungSave implements Serializable{
    private SteuerungSave fertigung;
    //private TreeItem<Steuerung> treeItem;
    private ArrayList<SteuerungSave> listeSteuerungen = new ArrayList<SteuerungSave>();
    private ArrayList<LinieSave> linien = new ArrayList<LinieSave>();
    private String name;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FertigungSave(String name, int anzahlLinien)
    {
        listeSteuerungen = new ArrayList<SteuerungSave>();
        linien = new ArrayList<LinieSave>();
        this.name = name;
        for (int i = 1; i <= anzahlLinien; i++)
        {
            LinieSave linie = new LinieSave(this, i);
            linien.add(linie);
        }
    }
    
    public ArrayList<SteuerungSave> getSteuerungen(){
        return listeSteuerungen;
    }
    
    public ArrayList<LinieSave> getLinien(){
        return linien;
    }

    public SteuerungSave getFertigung() {
        return fertigung;
    }

    public void setFertigung(SteuerungSave fertigung) {
        this.fertigung = fertigung;
    }

    public void addSteuerung(SteuerungSave steuerung)
    {
        if (steuerung != null)
        {
            System.out.println(steuerung.getName() + " in " + this.name + " eingefügt");
            listeSteuerungen.add(steuerung);
        }
        else
        {
            //
        }
    }
    
    public void addLinie(LinieSave linie){
        linien.add(linie);
    }
    
    @Override
    public String toString(){
        return name;
    }
}
