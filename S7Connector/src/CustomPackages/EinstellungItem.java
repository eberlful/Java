/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomPackages;

import java.util.ArrayList;
import javafx.scene.Node;

/**
 *
 * @author marku
 */
public class EinstellungItem {
    private String name;
    private ArrayList<ArrayList<Node>> nodes = new ArrayList<ArrayList<Node>>();
    
    public EinstellungItem(String name){
        this.name = name;
    }
    
    public void addNode(Node node1, Node node2){
        ArrayList<Node> liste = new ArrayList<Node>();
        liste.add(node1);
        liste.add(node2);
        nodes.add(liste);
    }
}
