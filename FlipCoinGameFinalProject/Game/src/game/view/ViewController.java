package game.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ViewController {

    private  String fxmlFile ;
    private   AnchorPane pane ;

    public void setFxmlFile(String fxmlFile) {
        this.fxmlFile = fxmlFile;
    }

    public AnchorPane getFxmlFile() {
        try {
             pane =  FXMLLoader.load(getClass().getResource(fxmlFile));
        } catch (IOException ex) {
            return null;
        }
        return pane;
    }
   
}
