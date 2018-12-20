/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomPackages;

import OriginalClass.FehlerSave;
import OriginalClass.FertigungSave;
import OriginalClass.SteuerungSave;
import OriginalClass.VerwaltungSave;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import javafx.beans.property.StringProperty;

/**
 *
 * @author marku
 */
public class ObjectWriter extends Thread{
    private Verwaltung verwaltung;
    private String pfadVerwaltung;
    private StringProperty stringProperty;
    
    public ObjectWriter(Verwaltung verwaltung, String pfadVerwaltung, StringProperty stringProperty){
        this.verwaltung = verwaltung;
        this.pfadVerwaltung = pfadVerwaltung;
        this.stringProperty = stringProperty;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Thread start");
        System.out.println(verwaltung.getCurrentUserName());
//        try {
//                // Hilfsdatenstruktur erstellen
//                VerwaltungSave verwaltungSave = new VerwaltungSave();
//                FileOutputStream fos = new FileOutputStream(pfadVerwaltung);
//                stringProperty.setValue(stringProperty.getValue() + new Date().toString() + ": Laden der Config-Datei von - " + pfadVerwaltung + "\n");
//                ObjectOutputStream oos = new ObjectOutputStream(fos);
//                for(Fertigung localFertigung : verwaltung.getFertigungList()){
//                    FertigungSave localFertigungSave = new FertigungSave(localFertigung.getName(), localFertigung.getLinien().size());
//                    verwaltungSave.addFertigung(localFertigungSave);
//                    int linieSize = localFertigung.getLinien().size();
//                    int linieCount = 0;
//                    stringProperty.setValue(stringProperty.getValue() + new Date().toString() + ": Sicherung Fertigung - " + localFertigung.getName() + " mit " + localFertigung.getLinien().size() + " Linien erstellt" + "\n");
//                    for(Linie localLinie : localFertigung.getLinien()){
//                        for(Steuerung localSteuerung : localLinie.getSteuerungen()){
//                            SteuerungSave steuerungSave = new SteuerungSave(localSteuerung.getIP(), Integer.parseInt(localSteuerung.getRack()), Integer.parseInt(localSteuerung.getSlot()), localSteuerung.getName(), localFertigungSave, localSteuerung.getDatenbaustein(), localSteuerung.getDBByte(), localSteuerung.getDBBit(), localFertigungSave.getLinien().get(linieCount));
//                            localFertigungSave.getLinien().get(linieCount).addSteuerung(steuerungSave);
//                            localFertigungSave.addSteuerung(steuerungSave);
//                            for(Fehler localFehler : localSteuerung.getFehlerListe()){
//                                FehlerSave fehlerSave = new FehlerSave(steuerungSave, localFehler.isUeberwachung(), localFehler.getDb(), localFehler.getDbByte(), localFehler.getDbBit(), localFehler.getArt(), localFehler.getMerkerByte(), localFehler.getMerkerBit(), localFehler.getFehlerText(), localFehler.getFehlername(), localFehler.getFehlernummer());
//                                fehlerSave.setLast(localFehler.getLast());
//                                fehlerSave.setZustand(localFehler.isZustand());
//                                fehlerSave.setFehlerCount(localFehler.getFehlerCounter());
//                                fehlerSave.setPfadFehlerBehebung(localFehler.getPfadFehlerBehebung());
//                                steuerungSave.addFehler(fehlerSave);
//                            }
//                        }
//                        linieCount++;
//                    }
//                }
//                oos.writeObject(verwaltungSave);
//                oos.close();
//                fos.close();
//            } catch (Exception e) {
//                stringProperty.setValue(stringProperty.getValue() + new Date().toString() + ": " + e.getMessage() + "\n" + new Date().toString() + ": " + e.getStackTrace() + "\n");
//            }
        
    }
    
}
