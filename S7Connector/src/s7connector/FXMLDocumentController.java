/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s7connector;

import CustomPackages.Einstellungen;
import CustomPackages.Fehler;
import CustomPackages.Fertigung;
import CustomPackages.Linie;
import CustomPackages.Steuerung;
import CustomPackages.Verwaltung;
import OriginalClass.FertigungSave;
import OriginalClass.LinieSave;
import OriginalClass.SteuerungSave;
import OriginalClass.VerwaltungSave;
import com.jfoenix.animation.alert.CenterTransition;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXTreeView;
import com.pixelduke.control.ribbon.RibbonGroup;
import com.pixelduke.control.ribbon.RibbonItem;
import com.sun.javafx.font.FontConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author marku
 */
public class FXMLDocumentController implements Initializable {
    // Jede Fertigung erhält hier eine Liste
    private ArrayList<Steuerung> listTest = new ArrayList<Steuerung>();
    private ListProperty<Steuerung> steuerungenProperty = new SimpleListProperty<Steuerung>();
    private Verwaltung verwaltung;
    private boolean console = true;
    // Speichert letztes Pane um zurück zu kommen
    private AnchorPane lastPane;
    // Speichert die aktuelle ausgewählte Steuerung
    private Steuerung currentSteuerung;
    private String pfadEinstellung = "einstellung.ser";
    
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
    private JFXTextField nameSteuerungAuswahl;

    @FXML
    private JFXTextField ipAdresseSteuerungAuswahl;

    @FXML
    private JFXTextField rackSteuerungAuswahl;

    @FXML
    private JFXTextField slotSteuerungAuswahl;

    @FXML
    private JFXTextField funktionsbitSteuerungAuswahl;

    @FXML
    private JFXTextField aktuellerZustandSteuerungAuswahl;

    @FXML
    private JFXTextField letzterFehlerSteuerungAuswahl;
    
    @FXML
    private TitledPane titledPaneSteuerung;
    
    @FXML
    private Accordion accSideBar;
    
    @FXML
    private ListView<Fehler> listViewFehlerSteuerungAuswahl;
    
    private boolean normalClickIsAlreadySeen = false;
    private Tab uebersichtsTab;
    
    @FXML
    public void treeViewClick(MouseEvent event) {
        TreeItem<Steuerung> item = treeView.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2) {
            try {
                    //FXMLLoader loader = FXMLLoader.load(getClass().getResource("Center/SteuerungAuswahl.fxml"));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Center/SteuerungAuswahl.fxml"));
                    loader.setController(this);
                    AnchorPane pane = loader.load();
                    addTab(pane, item.getValue().getName(), 1);
                    nameSteuerungAuswahl.setText(item.getValue().toString());
                    ipAdresseSteuerungAuswahl.setText(item.getValue().getIP());
                    rackSteuerungAuswahl.setText(item.getValue().getRack());
                    slotSteuerungAuswahl.setText(item.getValue().getSlot());
                    funktionsbitSteuerungAuswahl.setText(item.getValue().getFunktionsbit());
                    aktuellerZustandSteuerungAuswahl.setText(item.getValue().getZustand());
                    steuerungenProperty.set(FXCollections.observableArrayList(item.getValue().getFertigung().getSteuerungen()));
                    listViewFehlerSteuerungAuswahl.itemsProperty().bindBidirectional(item.getValue().getFehlerProperty());
                    accSideBar.setExpandedPane(titledPaneSteuerung);
                    normalClickIsAlreadySeen = true;
                    //item.getValue().getFehlerProperty().set(FXCollections.observableArrayList(item.getValue().getFehlerListe()));
                } catch (Exception e) {
                    setConsoleColorRed();
                    consoleArea.appendText(new Date().toString() + ": " + e.getMessage() + "\n");
                    consoleArea.appendText(new Date().toString() + ": " + e.getStackTrace() + "\n");
                    setConsoleColorBlack();
                }
        } else {
            if (item != null && normalClickIsAlreadySeen == false) {
                try {
                    //FXMLLoader loader = FXMLLoader.load(getClass().getResource("Center/SteuerungAuswahl.fxml"));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Center/SteuerungAuswahl.fxml"));
                    loader.setController(this);
                    AnchorPane pane = loader.load();
                    addTab(pane, item.getValue().getName(), 1);
                    nameSteuerungAuswahl.setText(item.getValue().toString());
                    ipAdresseSteuerungAuswahl.setText(item.getValue().getIP());
                    rackSteuerungAuswahl.setText(item.getValue().getRack());
                    slotSteuerungAuswahl.setText(item.getValue().getSlot());
                    funktionsbitSteuerungAuswahl.setText(item.getValue().getFunktionsbit());
                    aktuellerZustandSteuerungAuswahl.setText(item.getValue().getZustand());
                    steuerungenProperty.set(FXCollections.observableArrayList(item.getValue().getFertigung().getSteuerungen()));
                    accSideBar.setExpandedPane(titledPaneSteuerung);
                    normalClickIsAlreadySeen = true;
                } catch (Exception e) {
                    setConsoleColorRed();
                    consoleArea.appendText(new Date().toString() + ": " + e.getMessage() + "\n");
                    consoleArea.appendText(new Date().toString() + ": " + e.getStackTrace() + "\n");
                    setConsoleColorBlack();
                }
            } else if (item != null && normalClickIsAlreadySeen == true) {
                try {
                    uebersichtsTab.setText(item.getValue().getName());
                    nameSteuerungAuswahl.setText(item.getValue().toString());
                    ipAdresseSteuerungAuswahl.setText(item.getValue().getIP());
                    rackSteuerungAuswahl.setText(item.getValue().getRack());
                    slotSteuerungAuswahl.setText(item.getValue().getSlot());
                    funktionsbitSteuerungAuswahl.setText(item.getValue().getFunktionsbit());
                    aktuellerZustandSteuerungAuswahl.setText(item.getValue().getZustand());
                    steuerungenProperty.set(FXCollections.observableArrayList(item.getValue().getFertigung().getSteuerungen()));
                    accSideBar.setExpandedPane(titledPaneSteuerung);
                } catch (Exception e) {
                    exceptionOutput(e);
                }
            }
        } 
    }
    
    @FXML
    private JFXToggleButton einstellungenHaeufungToggle;

    @FXML
    private JFXToggleButton einstellungenDatenbankToggle;

    @FXML
    private JFXTextField einstellungenAnzahlHaeufung;

    @FXML
    private JFXTextField einstellungenZeitinterval;

    @FXML
    private JFXTextField einstellungenIPDB;
    
    @FXML void einstellungenSpeichern(ActionEvent event){
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            Einstellungen ein = verwaltung.getEinstellung();
            ein.setDbAktiv(einstellungenDatenbankToggle.isSelected());
            ein.setHaeufigkeitsPruefung(einstellungenHaeufungToggle.isSelected());
            ein.setIntervallHaeufigkeit(Integer.parseInt(einstellungenZeitinterval.getText()));
            ein.setIntervallAnzahl(Integer.parseInt(einstellungenAnzahlHaeufung.getText()));
            ein.setIpAdresseDB(einstellungenIPDB.getText());
            //ObjektStream
            fos = new FileOutputStream(pfadEinstellung);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(verwaltung.getEinstellung());
            oos.close();
            fos.close();
            
        } catch (Exception e) {
            exceptionOutput(e);
        } finally{
        }
    }
    
    @FXML
    private void einstellungenAnzeigen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Center/Einstellungen.fxml"));
            loader.setController(this);
            AnchorPane pane = loader.load();
            addTab(pane, "Einstellungen");
            Einstellungen einstell = verwaltung.getEinstellung();
            einstellungenDatenbankToggle.setSelected(einstell.getDbAktiv());
            einstellungenHaeufungToggle.setSelected(einstell.getHaeufigkeitsPruefung());
            einstellungenZeitinterval.setText(einstell.getIntervallHaeufigkeit());
            einstellungenAnzahlHaeufung.setText(einstell.getIntervallAnzahl());
            einstellungenIPDB.setText(einstell.getIpAdresseDB());
            //subPane.getChildren().add(pane);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
            setConsoleColorRed();
            consoleArea.appendText(new Date().toString() + ": " + e.getMessage() + "\n");
            consoleArea.appendText(new Date().toString() + ": " + e.getStackTrace() + "\n");
            setConsoleColorBlack();
        }
    }
    
    @FXML
    private void wochenauswertungStarten(ActionEvent event){
        try {
            
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
            setConsoleColorRed();
            consoleArea.appendText(new Date().toString() + ": " + e.getMessage() + "\n");
            consoleArea.appendText(new Date().toString() + ": " + e.getStackTrace() + "\n");
            setConsoleColorBlack();
        }
    }
    
    private void exceptionOutput(Exception e){
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
            System.out.println(e.getClass() + "\n" + e.getLocalizedMessage());
            setConsoleColorRed();
            consoleArea.appendText(new Date().toString() + ": " + e.getMessage() + "\n");
            consoleArea.appendText(new Date().toString() + ": " + e.getStackTrace() + "\n");
            setConsoleColorBlack();
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
            setConsoleColorRed();
            consoleArea.appendText(new Date().toString() + ": " + e.getMessage() + "\n");
            consoleArea.appendText(new Date().toString() + ": " + e.getStackTrace() + "\n");
            setConsoleColorBlack();
        }
        //label.setText("Hello World!");
    }
    
    @FXML
    void autoAnmeldenEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            anmeldenBenutzerAnmelden(null);
        }
        //System.out.println(event.getCode());
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
            consoleArea.appendText(new Date().toString() + ": Anmeldung erfolgreich - User: " + verwaltung.getCurrentUserName() + "\n");
            Tab deleteTab = tabControllPane.getSelectionModel().getSelectedItem();
            tabControllPane.getTabs().remove(deleteTab);
        } else {
            // Anmeldung fehlgeschlagen
            fault.setText("Fehler bei der Anmeldung !!!");
            System.out.print("Fehler");
            consoleArea.setStyle("-fx-text-inner-color: red;");
            consoleArea.appendText(new Date().toString() + ": Fehler bei der Anmeldung" + "\n");
            consoleArea.setStyle("-fx-text-inner-color: black;");
        }
    }
    
    private void setConsoleColorRed(){
        consoleArea.setStyle("-fx-text-inner-color: red;");
    }
    
    private void setConsoleColorBlack(){
        consoleArea.setStyle("-fx-text-inner-color: black;");
    }
    
    @FXML
    void fertigungVerwaltenNeueFertigung(ActionEvent event) {
        if(verwaltung.getCurrentUser() != 99){
            // Keine Berechtigung
            MessageBox.MessageBox.Show("Keine Berechtigung für diese Aktion!!!", "Zugriffsfehler");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Center/NeueFertiung.fxml"));
                //FXMLLoader loader = new FXMLLoader(getClass().getResource("Center/SteuerungAuswahl.fxml"));
                loader.setController(this);
                AnchorPane paneNeueFertigung = loader.load();
                addTab(paneNeueFertigung, "Neue Fertigung");
            } catch (Exception e) {
                MessageBox.MessageBox.Show(e.getMessage() + "\n" + e.getStackTrace(), "Loader-Fehler -> FXMLLoader");
                consoleArea.appendText(new Date().toString() + ": " + e.getMessage() + "\n");
                consoleArea.appendText(new Date().toString() + ": " + e.getStackTrace() + "\n");
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
                consoleArea.appendText(new Date().toString() + ": " + e.getMessage() + "\n");
                consoleArea.appendText(new Date().toString() + ": " + e.getStackTrace() + "\n");
            }
            Fertigung fertigung = new Fertigung( fertigungName.getText(), Integer.parseInt(anzahlLinien.getText()));
            verwaltung.addFertigung(fertigung);
            consoleArea.appendText(new Date().toString() + ": neue Fertigung - " + fertigung.getName() + "\n");
            Steuerung fSteuerung = new Steuerung(0, fertigungName.getText());

            Tab deleteTab = tabControllPane.getSelectionModel().getSelectedItem();
            tabControllPane.getTabs().remove(deleteTab);
            TreeItem<Steuerung> newFertigung = new TreeItem<Steuerung>(fSteuerung);
            newFertigung.setExpanded(true);
            fertigung.setFertigung(fSteuerung);
            fertigung.setTreeItem(newFertigung);
            multiRoot.getChildren().add(newFertigung);
            //treeView.setRoot(newFertigung);
            if(anzahl > 0){
                for(Linie linie : fertigung.getLinien()){
                    TreeItem<Steuerung> item = new TreeItem<Steuerung>(linie.getSteuerungItem());
                    newFertigung.getChildren().add(item);
                    linie.setItem(item);
                }
            }
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
            consoleArea.appendText(new Date().toString() + ": " + e.getMessage() + "\n");
            consoleArea.appendText(new Date().toString() + ": " + e.getStackTrace() + "\n");
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
            consoleArea.appendText(new Date().toString() + ": " + e.getMessage() + "\n");
            consoleArea.appendText(new Date().toString() + ": " + e.getStackTrace() + "\n");
        }
        steuerungsListe.itemsProperty().bind(steuerungenProperty);
        steuerungenProperty.set(FXCollections.observableArrayList());
    }
    
    private void addTab(AnchorPane pane, String name, int modus){
        addTab(pane, name);
        if (modus == 1) {
            uebersichtsTab = tabControllPane.getTabs().get(tabControllPane.getTabs().size()-1);
        }
    }
    
    private void addTab(AnchorPane pane, String name){
        if (first) {
            initTabSystem(pane, name);
            Tab firstTab = new Tab(name);
            firstTab.setOnCloseRequest((event) -> {
                System.out.println("Aus und Vorbei");
                if(borderPane.getBottom() == null){
                    borderPane.setBottom(consoleArea);
                }
                if (firstTab == uebersichtsTab) {
                    normalClickIsAlreadySeen = false;
                }
            });
            firstTab.setClosable(true);
            firstTab.setContent(pane);
            tabControllPane.getTabs().add(firstTab);
            System.out.println("Erster Tab erstellt");
            first = false;
        } else {
            Tab newTab = new Tab(name);
            newTab.setOnCloseRequest((event) -> {
                System.out.println("Aus und Vorbei");
                if(borderPane.getBottom() == null){
                    borderPane.setBottom(consoleArea);
                }
                if (newTab == uebersichtsTab) {
                    normalClickIsAlreadySeen = false;
                }
            });
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
    
    @FXML
    private TextArea consoleArea;
    
    private TreeItem<Steuerung> multiRoot;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initTabSystem();
        if (firstInstance) {
            verwaltung = new Verwaltung();
            firstInstance = false;
            // MultiRootNode für TreeView
            Steuerung st = new Steuerung(0, "");
            multiRoot = new TreeItem<Steuerung>(st);
            treeView.setShowRoot(false);
            treeView.setRoot(multiRoot);
        }
        
        
        
        try {
            File file = new File(pfadEinstellung);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(pfadEinstellung);
                ObjectInputStream ois = new ObjectInputStream(fis);
                verwaltung.setEinstellungen((Einstellungen)ois.readObject());
                ois.close();
                fis.close();
            } else {
                writeConsole("Keine Einstellungsdatei vorhanden!!!");
                setConsoleColorRed();
                writeConsole("Einstellungsdatei manuell laden !!!");
                setConsoleColorBlack();
            }
        } catch (Exception e) {
            exceptionOutput(e);
        }
        
        if(console){
            consoleArea.setEditable(false);
            PrintStream stream = new PrintStream(System.out){
                @Override
                public void print(String text){
                    consoleArea.appendText(text + "\n");
                }
            };
        }
        System.out.println("Init fertig");
        
        ObservableList<String> items =FXCollections.observableArrayList("Hallo", "dfk");
        fehlerListView.setItems(items);
    }
    
    private void writeConsole(String text){
        consoleArea.appendText(new Date().toString() + ": " + text + "\n");
    }
    
    @FXML
    private ListView<String> fehlerListView;
    
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
    private ListView<Steuerung> steuerungsListe;

    @FXML
    void speichernNeueSteuerung(ActionEvent event) {
        if (ipAdresseNeueSteuerung.getText().equals("") || rackNeueSteuerung.getText().equals("") || slotNeueSteuerung.getText().equals("") || dbNeueSteuerung.getText().equals("") || dbByteNeueSteuerung.getText().equals("") ||
                dbBitNeueSteuerung.getText().equals("") || nameNeueSteuerung.getText().equals("") || fertigungNeueSteuerung.getSelectionModel().equals("")) {
            MessageBox.MessageBox.Show("Es fehlen noch wichtige Eingaben!!!", "Eingabefehler");
        } else {
            Steuerung steuerung = new Steuerung(ipAdresseNeueSteuerung.getText(), Integer.parseInt(rackNeueSteuerung.getText()), Integer.parseInt(slotNeueSteuerung.getText()), nameNeueSteuerung.getText(), fertigungNeueSteuerung.selectionModelProperty().getValue().getSelectedItem(), Integer.parseInt(dbNeueSteuerung.getText()), Integer.parseInt(dbByteNeueSteuerung.getText()), Integer.parseInt(dbBitNeueSteuerung.getText()), linieNeueSteuerung.getSelectionModel().getSelectedItem());
            consoleArea.appendText(new Date().toString() + ": Neue Steuerung - " + steuerung.toString() + ", " + steuerung.getName() + ", " + steuerung.getIP() + "\n");
            verwaltung.addSteuerung(steuerung);
            Tab deleteTab = tabControllPane.getSelectionModel().getSelectedItem();
            tabControllPane.getTabs().remove(deleteTab);
            TreeItem<Steuerung> newSteuerung = new TreeItem<Steuerung>(steuerung);
            steuerung.setListViewItem(newSteuerung);
            steuerung.getFertigung().addSteuerung(steuerung);
            steuerung.getLinie().addSteuerung(steuerung);
            System.out.println("1");
            if (steuerung.getLinie() != null) {
                System.out.println("Linie nicht Null");
            } else {
                System.out.println("Linie Null");
            }
            if (steuerung.getLinie().getTreeItem() != null) {
                System.out.println("TreeItem nicht null");
            } else {
                System.out.println("TreeItem null");
            }
            steuerung.getLinie().getTreeItem().setExpanded(true);
            System.out.println("2 ");
            steuerung.getLinie().getTreeItem().getChildren().add(newSteuerung);
            System.out.println("3");
            //steuerung.getFertigung().getTreeItem().getChildren().add(newSteuerung);
            System.out.println("df");
            consoleArea.appendText(new Date().toString() + ": neue Steuerung - " + steuerung.getName() + "\n");
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
            consoleArea.appendText(new Date().toString() + ": " + e.getMessage() + "\n");
            consoleArea.appendText(new Date().toString() + ": " + e.getStackTrace() + "\n");
        }
    }
    
    @FXML
    void fertigungNeueSteuerungAuswahl(ActionEvent event) {
        linieNeueSteuerung.getItems().addAll(fertigungNeueSteuerung.getSelectionModel().getSelectedItem().getLinien());
    }
//    @Override
//    public void stop(){
//        
//    }
    
    @FXML
    public void exitApplication(ActionEvent event){
        System.out.println("Ende");
    }
    
    /*--------------------------------------------------------------------------
    Bereich für Neue Fehler
    --------------------------------------------------------------------------*/
    
    @FXML
    public void steuerungNeuerFehler(ActionEvent event){
        if(verwaltung.getCurrentUser() != 99){
            // Keine Berechtigung
            MessageBox.MessageBox.Show("Keine Berechtigung für diese Aktion!!!", "Zugriffsfehler");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Center/NeuerFehler.fxml"));
                loader.setController(this);
                AnchorPane paneAnmeldung = loader.load();
                addTab(lastPane, "Neuer Fehler");
            } catch (Exception e) {
                setConsoleColorRed();
                consoleArea.appendText(new Date().toString() + ": " + e.getMessage() + "\n");
                consoleArea.appendText(new Date().toString() + ": " + e.getStackTrace() + "\n");
                setConsoleColorBlack();
            }
        } 
    }
    
    /*--------------------------------------------------------------------------
    Bereich für neue Fehler
    --------------------------------------------------------------------------*/
    @FXML
    private JFXTextField neuerFehlerFehlername;

    @FXML
    private JFXTextField neuerFehlerFehlernummer;

    @FXML
    private JFXTextField neuerFehlerFehlertext;

    @FXML
    private JFXCheckBox neuerFehlerDB;

    @FXML
    private JFXCheckBox neuerFehlerM;

    @FXML
    private JFXTextField neuerFehlerMerkerByte;

    @FXML
    private JFXTextField neuerFehlerMerkerBit;

    @FXML
    private JFXTextField neuerFehlerDatenbaustein;

    @FXML
    private JFXTextField neuerFehlerDBByte;

    @FXML
    private JFXTextField neuerFehlerDBBit;

    @FXML
    private JFXComboBox<Fertigung> neuerFehlerFertigung;

    @FXML
    private JFXComboBox<Linie> neuerFehlerLinie;

    @FXML
    private JFXComboBox<Steuerung> neuerFehlerSteuerung;

    @FXML
    private JFXToggleButton neuerFehlerUeberwachung;

    @FXML
    void neuerFehlerDBCheckBoxChecked(ActionEvent event) {
        neuerFehlerM.setSelected(false);
        neuerFehlerMerkerBit.setText("0");
        neuerFehlerMerkerByte.setText("0");
    }

    @FXML
    void neuerFehlerMerkerCheckBoxChecked(ActionEvent event) {
        neuerFehlerDB.setSelected(false);
        neuerFehlerDBBit.setText("0");
        neuerFehlerDBByte.setText("0");
        neuerFehlerDatenbaustein.setText("0");
    }

    @FXML
    void neuerFehlerSpeichern(ActionEvent event) {
        try {
            int art = 0;
            Fehler fehler = new Fehler(neuerFehlerSteuerung.getSelectionModel().getSelectedItem(), neuerFehlerUeberwachung.isSelected(), Integer.parseInt(neuerFehlerDatenbaustein.getText()), Integer.parseInt(neuerFehlerDBByte.getText()), Integer.parseInt(neuerFehlerDBBit.getText()), art, Integer.parseInt(neuerFehlerMerkerByte.getText()), Integer.parseInt(neuerFehlerMerkerBit.getText()), neuerFehlerFehlertext.getText(), neuerFehlerFehlername.getText(), Integer.parseInt(neuerFehlerFehlernummer.getText()));
            neuerFehlerSteuerung.getSelectionModel().getSelectedItem().addFehler(fehler);
            Tab deleteTab = tabControllPane.getSelectionModel().getSelectedItem();
            tabControllPane.getTabs().remove(deleteTab);
        } catch (Exception e) {
            exceptionOutput(e);
        }
        
    }
    
    @FXML
    void neuerFehlerFertigungAuswahl(ActionEvent event) {
        neuerFehlerLinie.getItems().addAll(neuerFehlerFertigung.getSelectionModel().getSelectedItem().getLinien());
    }
    
    @FXML
    void neuerFehlerLinieAuswahl(ActionEvent event) {
        System.out.println("Auswahl");
        neuerFehlerSteuerung.getItems().addAll(neuerFehlerLinie.getSelectionModel().getSelectedItem().getSteuerungen());
        for(Steuerung st : neuerFehlerLinie.getSelectionModel().getSelectedItem().getSteuerungen()){
            System.out.println(st.getName());
        }
    }
    
    @FXML
    void neuerFehlerAuswahl(ActionEvent event){
        try {
            if(verwaltung.getCurrentUser() != 99){
                // Keine Berechtigung
                MessageBox.MessageBox.Show("Keine Berechtigung für diese Aktion (Neue Steuerung)!!!", "Zugriffsfehler");
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Center/NeuerFehler.fxml"));
                loader.setController(this);
                AnchorPane paneNeueSteuerung = loader.load();
                addTab(paneNeueSteuerung, "Neuer Fehler");
            }
            neuerFehlerFertigung.getItems().addAll(verwaltung.getFertigungList());
        } catch (Exception e) {
            exceptionOutput(e);
        }
    }
    /*--------------------------------------------------------------------------
    Bereich für Speichern / Laden
    --------------------------------------------------------------------------*/
    @FXML
    void objekteSpeichern(ActionEvent event){
        if(verwaltung.getCurrentUser() != 99){
            // keine Berechtigung
            MessageBox.MessageBox.Show("Keine Berechtigung für diese Aktion!!!", "Zugriffsfehler");
        } else {
            try {
                // Hilfsdatenstruktur erstellen
                VerwaltungSave verwaltungSave = new VerwaltungSave();
//                FileOutputStream fos = new FileOutputStream(pfadVerwaltung);
                consoleArea.appendText(new Date().toString() + ": Laden der Config-Datei von - " + pfadVerwaltung);
//                ObjectOutputStream oos = new ObjectOutputStream(fos);
                StringProperty stringProperty = new SimpleStringProperty();
                consoleArea.textProperty().bindBidirectional(stringProperty);
                CustomPackages.ObjectWriter obW = new CustomPackages.ObjectWriter(verwaltung, pfadVerwaltung, stringProperty);
                obW.start();
//                for(Fertigung localFertigung : verwaltung.getFertigungList()){
//                    FertigungSave localFertigungSave = new FertigungSave(localFertigung.getName(), localFertigung.getLinien().size());
//                    consoleArea.appendText(new Date().toString() + ": Sicherung Fertigung - " + localFertigung.getName() + " mit " + localFertigung.getLinien().size() + " Linien erstellt");
//                    for(Linie localLinie : localFertigung.getLinien()){
//                        for(Steuerung localSteuerung : localLinie.getSteuerungen()){
//                            for(Fehler localFehler : localSteuerung.getFehlerListe()){
//                                
//                            }
//                        }
//                    }
//                }
//                oos.writeObject(verwaltung);
//                oos.close();
//                fos.close();
            } catch (Exception e) {
                exceptionOutput(e);
            }
        }
    }
    // Sicher ab das nicht ungewollt eine Konfig geladen wird
    private boolean isAllreadyLoad = true;
    
    @FXML
    void objekteLaden(ActionEvent event){
        if(verwaltung.getCurrentUser() != 99){
            // keine Berechtigung
            MessageBox.MessageBox.Show("Keine Berechtigung für diese Aktion!!!", "Zugriffsfehler");
        } else {
            try {
                checkLoadConfig(pfadJSON);
                
                System.out.println("Load");
//                if (isAllreadyLoad) {
//                    Alert alert = new Alert(AlertType.CONFIRMATION);
//                    alert.setTitle("Konfig laden ???");
//                    alert.setHeaderText("Vorsicht, Config-Konflikt");
//                    alert.setContentText("Es wurde schon eine Konfiguration geladen. Sind Sie sicher, dass Sie diese überladen wollen ?");
//                    ButtonType buttonJa = new ButtonType("Ja");
//                    ButtonType buttonNein = new ButtonType("Nein");
//                    alert.getButtonTypes().setAll(buttonJa, buttonNein);
//                    Optional<ButtonType> result = alert.showAndWait();
//                    if (result.get() == buttonJa) {
//                        FileChooser fileChooser = new FileChooser();
//                        fileChooser.setTitle("Config laden");
//                        fileChooser.getExtensionFilters().addAll(
//                            new ExtensionFilter("Analyse Core", "*.ser"),
//                            new ExtensionFilter("Datenbankdatei", "*.dat"/*, "*.jpg", "*.gif"*/),
//                            //new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
//                            new ExtensionFilter("Alle Dateien", "*.*"));
//                        File selectedFile = fileChooser.showOpenDialog(borderPane.getScene().getWindow());
//                        if (selectedFile != null) {
//                            consoleArea.appendText(selectedFile.getAbsolutePath() + "\n");
//                        }
//                        FileInputStream fis = new FileInputStream(selectedFile.getAbsolutePath());
//                        ObjectInputStream ois = new ObjectInputStream(fis);
//                        VerwaltungSave verwaltungSave = (VerwaltungSave)ois.readObject();
//                        Verwaltung verwaltung = new Verwaltung();
//                        for(FertigungSave localFertigungSave : verwaltungSave.getFertigungList()){
//                            Fertigung localFertigung = new Fertigung(localFertigungSave.getName(), localFertigungSave.getLinien().size());
//                            verwaltung.addFertigung(localFertigung);
//                            consoleArea.appendText(new Date().toString() + ": neue Fertigung - " + localFertigung.getName() + "\n");
//                            Steuerung fSteuerung = new Steuerung(0, fertigungName.getText());
//                            TreeItem<Steuerung> newFertigung = new TreeItem<Steuerung>(fSteuerung);
//                            newFertigung.setExpanded(true);
//                            localFertigung.setFertigung(fSteuerung);
//                            localFertigung.setTreeItem(newFertigung);
//                            multiRoot.getChildren().add(newFertigung);
//                            for(Linie linie : localFertigung.getLinien()){
//                                Steuerung lSteuerung = new Steuerung(2, linie.toString());
//                                TreeItem<Steuerung> item = new TreeItem<Steuerung>(lSteuerung);
//                                newFertigung.getChildren().add(item);
//                                linie.setItem(item);
//                            }
//                            int counter = 0;
//                            for(LinieSave localLinieSave : localFertigungSave.getLinien()){
//                                for(SteuerungSave localSteuerungSave : localLinieSave.getSteuerungen()){
//                                    Steuerung steuerung = new Steuerung(localSteuerungSave.getIP(), Integer.parseInt(localSteuerungSave.getRack()), Integer.parseInt(localSteuerungSave.getSlot()), localSteuerungSave.getName(), localFertigung, localSteuerungSave.getDatenbaustein(), localSteuerungSave.getDbByte(), localSteuerungSave.getDbBit(), localFertigung.getLinien().get(counter));
//                                    consoleArea.appendText(new Date().toString() + ": Neue Steuerung - " + steuerung.toString() + ", " + steuerung.getName() + ", " + steuerung.getIP() + "\n");
//                                    verwaltung.addSteuerung(steuerung);
//                                    TreeItem<Steuerung> newSteuerung = new TreeItem<Steuerung>(steuerung);
//                                    steuerung.setListViewItem(newSteuerung);
//                                    steuerung.getFertigung().addSteuerung(steuerung);
//                                    steuerung.getLinie().addSteuerung(steuerung);
//                                    steuerung.getLinie().getTreeItem().setExpanded(true);
//                                    steuerung.getLinie().getTreeItem().getChildren().add(newSteuerung);
//                                    consoleArea.appendText(new Date().toString() + ": neue Steuerung - " + steuerung.getName() + "\n");
//                                    counter++;
//                                }
//                            }
//                        }
//                    } else if (result.get() == buttonNein) {
//                        setConsoleColorRed();
//                        writeConsole("Ladevorgang abgebrochen!!!");
//                        setConsoleColorBlack();
//                    }
//                } else {
//                    FileChooser fileChooser = new FileChooser();
//                    fileChooser.setTitle("Config laden");
//                    fileChooser.getExtensionFilters().addAll(
//                        new ExtensionFilter("Analyse Core", "*.ser"),
//                        new ExtensionFilter("Datenbankdatei", "*.dat"/*, "*.jpg", "*.gif"*/),
//                        //new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
//                        new ExtensionFilter("Alle Dateien", "*.*"));
//                    File selectedFile = fileChooser.showOpenDialog(borderPane.getScene().getWindow());
//                    if (selectedFile != null) {
//                        consoleArea.appendText(selectedFile.getAbsolutePath() + "\n");
//                    }
//                    FileInputStream fis = new FileInputStream(selectedFile.getAbsolutePath());
//                    ObjectInputStream ois = new ObjectInputStream(fis);
//                    VerwaltungSave verwaltungSave = (VerwaltungSave)ois.readObject();
//                    // fehlt etwas
//                }
            } catch (Exception e) {
                exceptionOutput(e);
            }
        }
    }
    
    private String pfadVerwaltung = "verwaltung.ser";
    /*--------------------------------------------------------------------------
    Bereich für Statistik
    --------------------------------------------------------------------------*/
    @FXML
    void statistikAnzeigen(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Center/Statistik.fxml"));
            loader.setController(this);
            AnchorPane paneStatistik = loader.load();
            addTab(paneStatistik, "Statistik");
            borderPane.setBottom(null);
            //borderPane.getBottom().setVisible(false);
            
            //consoleArea.setVisible(false);
        } catch (Exception e) {
            exceptionOutput(e);
        }
    }
    
    @FXML
    BorderPane borderPane;
    
    /*--------------------------------------------------------------------------
    Bereich für JSON-Lade-Speicher-Struktur
    --------------------------------------------------------------------------*/
    private String pfadJSON = "load.json";
    private boolean configIsLoaded = false;
    
    private void checkLoadConfig(String pfad){
        if (configIsLoaded) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Konfig laden ???");
            alert.setHeaderText("Vorsicht, Config-Konflikt");
            alert.setContentText("Es wurde schon eine Konfiguration geladen. Sind Sie sicher, dass Sie diese überladen wollen ?");
            ButtonType buttonJa = new ButtonType("Ja");
            ButtonType buttonNein = new ButtonType("Nein");
            alert.getButtonTypes().setAll(buttonJa, buttonNein);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonJa) {
                loadConfigJASON(pfad);
            }
        } else {
            loadConfigJASON(pfad);
        }
    }
    
    private void loadConfigJASON(String pfad){
        try {
            File file = new File(pfad);
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())), "UTF-8");
            JSONObject json = new JSONObject(content);
            JSONObject einstellungen = json.getJSONObject("Einstellung");
            writeConsole(Integer.toString(einstellungen.getInt("Anzahl Fertigungen")));
            int countFertiung = einstellungen.getInt("Anzahl Fertigungen");
            int countLinien = einstellungen.getInt("Anzahl Linien");
            int countSteuerungen = einstellungen.getInt("Anzahl Steuerungen");
            int countFehler = einstellungen.getInt("Anzahl Fehler");
            writeConsole("Es wurden folgende Objekte gefunden.");
            writeConsole("Anzahl Fertigungen: " + countFertiung);
            writeConsole("Anzahl Linien: " + countLinien);
            writeConsole("Anzahl Steuerungen: " + countSteuerungen);
            writeConsole("Anzahl Fehler: " + countFehler);
            JSONArray jSONArray = json.getJSONArray("Fertigung");
            Verwaltung localVerwaltung = new Verwaltung();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject object = jSONArray.getJSONObject(i);
                writeConsole("Neue Fertiung: " + object.getString("Name"));
                Fertigung localFertigung = new Fertigung(object.getString("Name"), object.getInt("Anzahl der Linien"));
                verwaltung.addFertigung(localFertigung);
                Steuerung fSteuerung = new Steuerung(0, localFertigung.getName());
                TreeItem<Steuerung> newFertigung = new TreeItem<Steuerung>(fSteuerung);
                newFertigung.setExpanded(true);
                localFertigung.setFertigung(fSteuerung);
                localFertigung.setTreeItem(newFertigung);
                multiRoot.getChildren().add(newFertigung);
                if(object.getInt("Anzahl der Linien") > 0){
                    for(Linie linie : localFertigung.getLinien()){
                        TreeItem<Steuerung> item = new TreeItem<Steuerung>(linie.getSteuerungItem());
                        newFertigung.getChildren().add(item);
                        linie.setItem(item);
                    }
                }
                JSONArray linien = object.getJSONArray("Linien");
                for (int j = 0; j < linien.length(); j++) {
                    JSONObject linieNObject = linien.getJSONObject(j);
                    JSONArray steuerungJSONArray = linieNObject.getJSONArray("Steuerungen");
                    for (int k = 0; k < steuerungJSONArray.length(); k++) {
                        JSONObject steuerungObject = steuerungJSONArray.getJSONObject(k);
                        Steuerung localSteuerung = new Steuerung(steuerungObject.getString("IP"), steuerungObject.getInt("Rack"), steuerungObject.getInt("Slot"), steuerungObject.getString("Name"), localFertigung, steuerungObject.getInt("Datenbaustein"), steuerungObject.getInt("DB Byte"), steuerungObject.getInt("DB Bit"), localFertigung.getLinien().get(k));
                        verwaltung.addSteuerung(localSteuerung);
                        TreeItem<Steuerung> newSteuerung = new TreeItem<Steuerung>(localSteuerung);
                        localSteuerung.setListViewItem(newSteuerung);
                        localSteuerung.getFertigung().addSteuerung(localSteuerung);
                        localSteuerung.getLinie().addSteuerung(localSteuerung);
                        localSteuerung.getLinie().getTreeItem().setExpanded(true);
                        localSteuerung.getLinie().getTreeItem().getChildren().add(newSteuerung);
                        JSONArray fehlerJsonArray = steuerungObject.getJSONArray("Fehler");
                        for (int l = 0; l < fehlerJsonArray.length(); l++) {
                            // Fehler
                        }
                    }
                }
            }
            //JSONObject object = (JSONObject) jSONArray.get(0);
            //writeConsole(object.getString("Name"));
        } catch (Exception e) {
            exceptionOutput(e);
        }
    }
    
    private void saveConfigFertigung(String pfad){
        try {
            JSONObject object = new JSONObject();
            
        } catch (Exception e) {
        }
    }
}
