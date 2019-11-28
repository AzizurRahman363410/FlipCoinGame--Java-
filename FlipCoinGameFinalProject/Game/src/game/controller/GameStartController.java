package game.controller;

import game.view.ViewController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class GameStartController implements Initializable {

    @FXML
    private AnchorPane strtingPane;
    @FXML
    private ProgressBar pb;
    Task copyWorker;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progressBarLoad();
    }

    public void progressBarLoad() {
        pb.setProgress(0);
        copyWorker = createWorker();
        pb.progressProperty().bind(copyWorker.progressProperty());
        new Thread(copyWorker).start();
    }

    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(500);
                    updateProgress(i + 1, 10);
                    if (i == 9) {
                        fade();
                    }
                }
                return true;
            }
        };
    }

    public void fade() {
        FadeTransition fadOut = new FadeTransition(Duration.seconds(3), strtingPane);
        fadOut.setFromValue(1);
        fadOut.setToValue(0);
        fadOut.setCycleCount(1);
        fadOut.play();

        fadOut.setOnFinished((e) -> {

            ViewController getPane = new ViewController();
            getPane.setFxmlFile("Game1.fxml");
            AnchorPane root = getPane.getFxmlFile();
            strtingPane.getChildren().setAll(root);
            FadeTransition fadIn = new FadeTransition(Duration.seconds(3), strtingPane);
            fadIn.setToValue(1);
            fadIn.setFromValue(0);
            fadIn.setCycleCount(1);
            fadIn.play();
        });
    }
}
