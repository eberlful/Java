/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OriginalClass;

import CustomPackages.Fertigung;
import CustomPackages.Steuerung;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.control.TreeItem;

/**
 *
 * @author marku
 */
public class LinieSave implements Serializable{
    private ArrayList<SteuerungSave> steuerungen = new ArrayList<SteuerungSave>();
    private FertigungSave fertigung;
    private String name;
    private SteuerungSave linieItem;
    
    public LinieSave(FertigungSave fertigung, int count)
    {
        this.fertigung = fertigung;
        this.name = "Linie " + count;
        System.out.println(this.name + " " + fertigung.getName() + " erzeugt");
        linieItem = new SteuerungSave(2, name);
    }
    
    public SteuerungSave getSteuerungItem(){
        return linieItem;
    }

    public SteuerungSave getLinieItem() {
        return linieItem;
    }

    public void setLinieItem(SteuerungSave linieItem) {
        this.linieItem = linieItem;
    }
    /*
     * Fügt eine Steuerung der Linie als Objekt hinzu
     */
    public void addSteuerung(SteuerungSave steuerung)
    {
        System.out.println(steuerung.getName() + " in " + this.name + " eingefügt");
        steuerungen.add(steuerung);
    }
    
    public ArrayList<SteuerungSave> getSteuerungen(){
        return steuerungen;
    }

    public void deleteSteuerung(SteuerungSave steuerung)
    {
        if (steuerungen.size() > 0)
        {
            int index = -1;
            for(SteuerungSave item : steuerungen)
            {
                if (item.equals(steuerung))
                {
                    steuerungen.remove(item);
                }
            }
        }
    }
    
    @Override
    public String toString(){
        return name;
    }
}
