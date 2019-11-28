package game;

import game.view.ViewController;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author LAB_518_139
 */
public class Game extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        ViewController getPane = new ViewController();
        getPane.setFxmlFile("GameStart.fxml");
        AnchorPane root = getPane.getFxmlFile();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        

    }

    public static void main(String[] args) {
        launch(args);
    }

}
