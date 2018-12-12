/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomPackages;

import java.util.ArrayList;

/**
 *
 * @author marku
 */
public class Linie {
    private ArrayList<Steuerung> steuerungen = new ArrayList<Steuerung>();
    private Fertigung fertigung;
    private String name;

    public Linie(Fertigung fertigung, int count)
    {
        this.fertigung = fertigung;
        this.name = "Linie " + count;
        System.out.println(this.name + " " + fertigung.getName() + " erzeugt");
    }

    /*
     * Fügt eine Steuerung der Linie als Objekt hinzu
     */
    public void addSteuerung(Steuerung steuerung)
    {
        System.out.println(steuerung.getName() + " in " + this.name + " eingefügt");
        steuerungen.add(steuerung);
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
}
