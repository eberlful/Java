/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OriginalClass;

import CustomPackages.Fertigung;
import CustomPackages.Linie;
import CustomPackages.Steuerung;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.shape.Line;

/**
 *
 * @author marku
 */
public class VerwaltungSave implements Serializable{
    private ArrayList<SteuerungSave> controlList;
    private ArrayList<FertigungSave> fertigungList;
    private ArrayList<LinieSave> linienList;
    
    public VerwaltungSave(){
        controlList = new ArrayList<SteuerungSave>();
        fertigungList = new ArrayList<FertigungSave>();
        linienList = new ArrayList<LinieSave>();
    }

    public void addSteuerung(SteuerungSave steuerung){
        controlList.add(steuerung);
    }
    
    public void addFertigung(FertigungSave fertigung){
        fertigungList.add(fertigung);
    }

    public ArrayList<SteuerungSave> getControlList() {
        return controlList;
    }

    public void setControlList(ArrayList<SteuerungSave> controlList) {
        this.controlList = controlList;
    }

    public ArrayList<FertigungSave> getFertigungList() {
        return fertigungList;
    }

    public void setFertigungList(ArrayList<FertigungSave> fertigungList) {
        this.fertigungList = fertigungList;
    }

    public void addLinie(LinieSave linie){
        linienList.add(linie);
    }

    public ArrayList<LinieSave> getLinienListe(){
        return this.linienList;
    }
}
