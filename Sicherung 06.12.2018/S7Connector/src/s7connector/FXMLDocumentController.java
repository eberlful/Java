/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s7connector;

import CustomPackages.Fehler;
import CustomPackages.Fertigung;
import CustomPackages.Linie;
import CustomPackages.Steuerung;
import CustomPackages.Verwaltung;
import com.jfoenix.animation.alert.CenterTransition;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
import com.pixelduke.control.ribbon.RibbonGroup;
import com.pixelduke.control.ribbon.RibbonItem;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author marku
 */
public class FXMLDocumentController implements Initializable {
    // Jede Fertigung erhält hier eine Liste
    private ArrayList<Steuerung> listTest = new ArrayList<Steuerung>();
    private Verwaltung verwaltung;
    private boolean console = true;
    // Speichert letztes Pane um zurück zu kommen
    private AnchorPane lastPane;
    
    @FXML
    private JFXTreeView<Steuerung> treeView;
    
    @FXML
    private Label label;
    
    @FXML
    private Button sichtSteuerungen;
    
    @FXML
    private AnchorPane content;
    
    @FXML
    private  StackPane subPane;
    
    @FXML
    private void einstellungenAnzeigen(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Center/Einstellungen.fxml"));
            addTab(pane, "Einstellungen");
            //subPane.getChildren().add(pane);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
        }
        //label.setText("Hello World!");
    }
    
    @FXML
    private void userAnmeldungAnzeigen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Center/BenutzerAnmelden.fxml"));
            loader.setController(this);
            AnchorPane paneAnmeldung = loader.load();
            benutzernameBenutzerAnmelden.requestFocus();
            //AnchorPane pane = FXMLLoader.load(getClass().getResource("Center/BenutzerAnmelden.fxml"));
            //subPane.getChildren().add(paneAnmeldung);
            addTab(paneAnmeldung, "Anmeldung");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
        }
        //label.setText("Hello World!");
    }
    
    /*
    Benutzer-Anmelden -> BenutzerAnmelden.fxml
    */
    @FXML
    private JFXTextField benutzernameBenutzerAnmelden;

    @FXML
    private JFXPasswordField passwortBenutzerAnmelden;
    
    @FXML
    private Label fault;
    
    @FXML
    private AnchorPane anmeldenAnchorPane;

    @FXML
    void anmeldenBenutzerAnmelden(ActionEvent event) {
        if (verwaltung.checkUser(benutzernameBenutzerAnmelden.getText(), passwortBenutzerAnmelden.getText())) {
            Stage stage = (Stage) content.getScene().getWindow();
            stage.setTitle(benutzernameBenutzerAnmelden.getText());
            fault.setText("Anmeldung erfolgreich !!!");
            Tab deleteTab = tabControllPane.getSelectionModel().getSelectedItem();
            tabControllPane.getTabs().remove(deleteTab);
        } else {
            // Anmeldung fehlgeschlagen
            fault.setText("Fehler bei der Anmeldung !!!");
        }
    }
    
    @FXML
    void fertigungVerwaltenNeueFertigung(ActionEvent event) {
        if(verwaltung.getCurrentUser() != 99){
            // Keine Berechtigung
            MessageBox.MessageBox.Show("Keine Berechtigung für diese Aktion!!!", "Zugriffsfehler");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Center/NeueFertiung.fxml"));
                loader.setController(this);
                AnchorPane paneNeueFertigung = loader.load();
                addTab(paneNeueFertigung, "Neue Fertigung");
            } catch (Exception e) {
                MessageBox.MessageBox.Show(e.getMessage() + "\n" + e.getStackTrace(), "Loader-Fehler -> FXMLLoader");
            }
        }
    }
    
    @FXML
    private JFXTextField fertigungName;

    @FXML
    private JFXTextField anzahlLinien;

    @FXML
    void neueFertigungErstellen(ActionEvent event) {
        if (fertigungName.getText().equals("") || anzahlLinien.getText().equals("")) {
            MessageBox.MessageBox.Show("Es fehlen wichtig Informationen. Bitte tragen Sie diese nach!", "Keine Auswahl");
        } else {
            int anzahl = 0;
            try {
                anzahl = Integer.parseInt(anzahlLinien.getText());
            } catch (Exception e) {
                
            }
            Fertigung fertigung = new Fertigung( fertigungName.getText(), Integer.parseInt(anzahlLinien.getText()));
            verwaltung.addFertigung(fertigung);
            Steuerung fSteuerung = new Steuerung(0, fertigungName.getText());
            Tab deleteTab = tabControllPane.getSelectionModel().getSelectedItem();
            tabControllPane.getTabs().remove(deleteTab);
            TreeItem<Steuerung> newFertigung = new TreeItem<Steuerung>(fSteuerung);
            newFertigung.setExpanded(true);
            fertigung.setFertigung(fSteuerung);
            fertigung.setTreeItem(newFertigung);
            treeView.setRoot(newFertigung);
            //saveVerwaltung(verwaltung);
            tabControllPane.getTabs().remove(tabControllPane.getSelectionModel().getSelectedItem());
        }
        //        TreeItem<String> root = new TreeItem<String>("Test");
//        root.setExpanded(true);
//        TreeItem<String> test1 = new TreeItem<String>("Test1");
//        TreeItem<String> test2 = new TreeItem<String>("Test2");
//        root.getChildren().add(test1);
//        root.getChildren().add(test2);
//        treeView.setRoot(root);
    }
    
    public void saveVerwaltung(Verwaltung verwaltung){
        try {
            File f = new File("fertigung.ser");
            if (f.exists()) {
                FileInputStream fis = new FileInputStream("fertigung.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                fis.close();
                f.delete();
                FileOutputStream fs = new FileOutputStream("fertigung.ser");
                ObjectOutputStream os = new ObjectOutputStream(fs);
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while((bytesRead = ois.read(buffer)) != -1){
                    os.write(buffer, 0, bytesRead);
                }
            } else {
                f.createNewFile();
                FileOutputStream fs = new FileOutputStream("fertigung.ser");
                ObjectOutputStream os = new ObjectOutputStream(fs);
                for(Fertigung fertigung : verwaltung.getFertigungList()){
                    os.writeObject(fertigung);
                }
                os.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
        }
    }

    
    /*
    Tabsteuerung -> TabControll.fxml
    */
    @FXML
    private TabPane mainTab;

    @FXML
    private StackPane tabStack;

    @FXML
    private Tab tab;
    
    private TabPane tabControllPane;
    
    // Tab-Init-Variable ob mehr als 1
    private boolean first = true;
    
    private void initTabSystem(AnchorPane pane, String name){
        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("Center/TabControll.fxml"));
//            loader.setController(this);
//            AnchorPane tabControll = loader.load();
//            subPane.getChildren().add(tabControll);
            //BooleanProperty closeable = new SimpleBooleanProperty(Boolean.FALSE);
            //tab.closableProperty().bind(closeable);
            tabControllPane = new TabPane();
            subPane.getChildren().add(tabControllPane);
//            tab.setClosable(Boolean.TRUE);
//            tab.setText(name);
//            tab.setContent(pane);
//            System.out.println(tab.isClosable());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
        }
        
    }
    
    private void addTab(AnchorPane pane, String name){
        if (first) {
            initTabSystem(pane, name);
            Tab firstTab = new Tab(name);
            firstTab.setClosable(true);
            firstTab.setContent(pane);
            tabControllPane.getTabs().add(firstTab);
            System.out.println("Erster Tab erstellt");
            first = false;
        } else {
            Tab newTab = new Tab(name);
            newTab.setClosable(true);
            //newTab.setText(name);
            newTab.setContent(pane);
            //newTab.setClosable(true);
            //mainTab.getTabs().add(newTab);
            tabControllPane.getTabs().add(newTab);
            System.out.println("Zweiter Tab erstellt");
        }
        
    }
    
    private boolean firstInstance = true;
    @FXML
    private BorderPane windowPane;
    private StackPane consoleStackPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initTabSystem();
        if (firstInstance) {
            verwaltung = new Verwaltung();
            firstInstance = false;
        }
        
        if(console){
            consoleStackPane = new StackPane();
            windowPane.setBottom(consoleStackPane);
            TextField field = new TextField();
            field.setEditable(false);
            PrintStream stream = new PrintStream(System.out){
                @Override
                public void print(String text){
                    field.appendText(text + "\n");
                }
            };
            //System.setOut();
            consoleStackPane.getChildren().add(field);
        }
        System.out.println("Init fertig");
        //akuTreeView();
    }
    
    private void akuTreeView(ArrayList<Steuerung> list1, ArrayList<Steuerung> list2, ArrayList<Steuerung> list3, ArrayList<Steuerung> list4, ArrayList<Steuerung> list5, ArrayList<Steuerung> list6, ArrayList<Fertigung> name){
        if(name.size() == 6){
            for(Fertigung fertigung : name){
                TreeItem<Steuerung> root = new TreeItem<Steuerung>(new Steuerung(0, fertigung.getName()));
                root.setExpanded(true);
                for(Steuerung control : list1){
                    TreeItem<Steuerung> item = new TreeItem<Steuerung>(control);
                    root.getChildren().add(item);
                }
                treeView.setRoot(root);
            }
            
        }
//        TreeItem<String> root = new TreeItem<String>("Test");
//        root.setExpanded(true);
//        TreeItem<String> test1 = new TreeItem<String>("Test1");
//        TreeItem<String> test2 = new TreeItem<String>("Test2");
//        root.getChildren().add(test1);
//        root.getChildren().add(test2);
//        treeView.setRoot(root);
    }
    
    /*--------------------------------------------------------------------------
    Bereich für Neue Steuerung
    --------------------------------------------------------------------------*/
    
    @FXML
    private JFXTextField ipAdresseNeueSteuerung;

    @FXML
    private JFXTextField rackNeueSteuerung;

    @FXML
    private JFXTextField slotNeueSteuerung;

    @FXML
    private JFXTextField dbNeueSteuerung;

    @FXML
    private JFXTextField dbByteNeueSteuerung;

    @FXML
    private JFXTextField dbBitNeueSteuerung;

    @FXML
    private JFXTextField nameNeueSteuerung;

    @FXML
    private JFXCheckBox ueberwachungNeueSteuerung;

    @FXML
    private JFXComboBox<Fertigung> fertigungNeueSteuerung;

    @FXML
    private JFXComboBox<Linie> linieNeueSteuerung;

    @FXML
    private JFXButton speichernNeueSteuerung;

    @FXML
    void speichernNeueSteuerung(ActionEvent event) {
        if (ipAdresseNeueSteuerung.getText().equals("") || rackNeueSteuerung.getText().equals("") || slotNeueSteuerung.getText().equals("") || dbNeueSteuerung.getText().equals("") || dbByteNeueSteuerung.getText().equals("") ||
                dbBitNeueSteuerung.getText().equals("") || nameNeueSteuerung.getText().equals("") || fertigungNeueSteuerung.getSelectionModel().equals("")) {
            MessageBox.MessageBox.Show("Es fehlen noch wichtige Eingaben!!!", "Eingabefehler");
        } else {
            Steuerung steuerung = new Steuerung(ipAdresseNeueSteuerung.getText(), Integer.parseInt(rackNeueSteuerung.getText()), Integer.parseInt(slotNeueSteuerung.getText()), nameNeueSteuerung.getText(), fertigungNeueSteuerung.selectionModelProperty().getValue().getSelectedItem(), Integer.parseInt(dbNeueSteuerung.getText()), Integer.parseInt(dbByteNeueSteuerung.getText()), Integer.parseInt(dbBitNeueSteuerung.getText()), linieNeueSteuerung.selectionModelProperty().getValue().getSelectedItem());
            verwaltung.addSteuerung(steuerung);
            Tab deleteTab = tabControllPane.getSelectionModel().getSelectedItem();
            tabControllPane.getTabs().remove(deleteTab);
            TreeItem<Steuerung> newSteuerung = new TreeItem<Steuerung>(steuerung);
            steuerung.setListViewItem(newSteuerung);
            steuerung.getFertigung().getTreeItem().getChildren().add(newSteuerung);
        }
    }
    
    @FXML
    void steuerungVerwaltenNeueSteuerung(ActionEvent event) {
        try {
            if (verwaltung.getCurrentUser() != 99) {
                // Keine Berechtigung
                MessageBox.MessageBox.Show("Keine Berechtigung für diese Aktion (Neue Steuerung)!!!", "Zugriffsfehler");
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Center/NeueSteuerung.fxml"));
                loader.setController(this);
                AnchorPane paneNeueSteuerung = loader.load();
                addTab(paneNeueSteuerung, "Neue Steuerung");
                // Lade Drop-Down Menue
                fertigungNeueSteuerung.getItems().addAll(verwaltung.getFertigungList());
                for(Fertigung fertigung : verwaltung.getFertigungList()){
                    System.out.println(fertigung);
                }
                verwaltung.getFertigungList();
            }
        } catch (Exception e) {
            MessageBox.MessageBox.Show(e.getMessage() + "\n" + e.getStackTrace(), "Loader-Fehler -> FXMLLoader");
        }
    }
    
//    @Override
//    public void stop(){
//        
//    }
    
    @FXML
    public void exitApplication(ActionEvent event){
        System.out.println("Ende");
    }
    
    
}
