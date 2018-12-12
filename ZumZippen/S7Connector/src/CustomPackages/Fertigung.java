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
public class Fertigung implements Serializable{
    private Steuerung fertigung;
    private TreeItem<Steuerung> treeItem;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private ArrayList<Steuerung> listeSteuerungen = new ArrayList<Steuerung>();
    private ArrayList<Linie> linien = new ArrayList<Linie>();
    private String name;
    //private ListViewItem listViewItem;
    public Fertigung(String name, int anzahlLinien)
    {
        this.name = name;
        for (int i = 1; i <= anzahlLinien; i++)
        {
            Linie linie = new Linie(this, i);
            linien.add(linie);
        }
    }
    
    public ArrayList<Linie> getLinien(){
        return linien;
    }

    public Steuerung getFertigung() {
        return fertigung;
    }

    public void setFertigung(Steuerung fertigung) {
        this.fertigung = fertigung;
    }

    public TreeItem<Steuerung> getTreeItem() {
        return treeItem;
    }

    public void setTreeItem(TreeItem<Steuerung> treeItem) {
        this.treeItem = treeItem;
    }

    public void addSteuerung(Steuerung steuerung)
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
    
    public void addLinie(Linie linie){
        linien.add(linie);
    }
    
    @Override
    public String toString(){
        return name;
    }
    
}
