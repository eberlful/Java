/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s7connector;

import CustomPackages.Verwaltung;
import com.pixelduke.control.Ribbon;
import com.pixelduke.control.ribbon.RibbonGroup;
import com.pixelduke.control.ribbon.RibbonTab;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Optional;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author marku
 */
public class S7Connector extends Application {
    private static final String RESOURCE = "FXMLDocument.fxml";
    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        
//        Scene scene = new Scene(root);
//        
//        stage.setScene(scene);
//        stage.show();
//        BorderPane rootNode = new BorderPane();
//        Ribbon ribbon = new Ribbon();
//        RibbonGroup ribbonGroup = new RibbonGroup();
//        RibbonTab ribbonTab = new RibbonTab("Test");
//
//        rootNode.setTop(ribbon);
//        Image image = new Image(new FileInputStream("C:\\Users\\marku\\OneDrive\\Desktop\\Java\\S7Connector\\src\\s7connector\\Radnabe.PNG"));
//        ImageView imageView = new ImageView(image);
//        Button iconButton = new Button("Bold", imageView);
//        iconButton.setContentDisplay(ContentDisplay.TOP);
//        ribbonGroup.getNodes().add(iconButton);
//        
//        ribbonTab.getRibbonGroups().add(ribbonGroup);
//        ribbon.getTabs().add(ribbonTab);
        //MenuButton number = new MenuButton("Number");

        //number.getItems().addAll(new MenuItem("test1"), new MenuItem("test2"), new MenuItem("test3"), new MenuItem("test4"));
        //ribbonGroup.getNodes().addAll(number);
        URL resource = S7Connector.class.getResource(RESOURCE);
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Center/ButtonStyle.css").toExternalForm());
        //setUserAgentStylesheet(STYLESHEET_MODENA);
        //new JMetro(JMetro.Style.LIGHT).applyTheme(scene);
//        ScenicView.show(scene);
        primaryStage.setScene(scene);
       // primaryStage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.setFullScreen(false);
        primaryStage.setOnCloseRequest((event) -> {
            if (event.getEventType() == WindowEvent.WINDOW_CLOSE_REQUEST) {
                MessageBox.MessageBox.Show("Die Anwendung kann nicht ohne weiteres beendet werden !!!", "Zugriffsfehler");
//                Alert alert = new Alert(AlertType.WARNING);
//                alert.setTitle("Anwendung beenden ?");
//                alert.setHeaderText("Sind Sie sicher, dass Sie diese Anwendung beenden wollen ?");
//                ButtonType buttonJa = new ButtonType("Ja");
//                ButtonType buttonNein = new ButtonType("Nein");
//                alert.getButtonTypes().setAll(buttonJa, buttonNein);
//                Optional<ButtonType> result = alert.showAndWait();
//                if (result.get() == buttonJa) {
//                    
//                }
                event.consume();
            }
            System.out.println(event.toString());
        });
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void stop(){
        System.out.println("Stopp");
    }
    
}
