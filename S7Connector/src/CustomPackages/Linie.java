/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomPackages;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.control.TreeItem;

/**
 *
 * @author marku
 */
public class Linie implements Serializable{
    private ArrayList<Steuerung> steuerungen = new ArrayList<Steuerung>();
    private Fertigung fertigung;
    private String name;
    private Steuerung linieItem;
    private TreeItem<Steuerung> item;

    public Linie(Fertigung fertigung, int count)
    {
        this.fertigung = fertigung;
        this.name = "Linie " + count;
        System.out.println(this.name + " " + fertigung.getName() + " erzeugt");
        linieItem = new Steuerung(2, name);
    }
    
    public Steuerung getSteuerungItem(){
        return linieItem;
    }

    public Steuerung getLinieItem() {
        return linieItem;
    }

    public void setLinieItem(Steuerung linieItem) {
        this.linieItem = linieItem;
    }

    public TreeItem<Steuerung> getItem() {
        return item;
    }
    
    public TreeItem<Steuerung> getTreeItem() {
        return item;
    }

    public void setItem(TreeItem<Steuerung> item) {
        this.item = item;
    }
    
    
    /*
     * Fügt eine Steuerung der Linie als Objekt hinzu
     */
    public void addSteuerung(Steuerung steuerung)
    {
        System.out.println(steuerung.getName() + " in " + this.name + " eingefügt");
        steuerungen.add(steuerung);
    }
    
    public ArrayList<Steuerung> getSteuerungen(){
        return steuerungen;
    }

    public void deleteSteuerung(Steuerung steuerung)
    {
        if (steuerungen.size() > 0)
        {
            int index = -1;
            for(Steuerung item : steuerungen)
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
