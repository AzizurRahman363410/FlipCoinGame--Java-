package game.controller;

import game.model.MailInfo;
import game.view.ViewController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Game1Controller implements Initializable {

    @FXML
    private TextField conEmail;

     @FXML
    private AnchorPane gameInfoPane;

    @FXML
    private TextField conSubject;

    @FXML
    private TextArea conTextarea;

    @FXML
    private AnchorPane gamePane;

    @FXML
    private AnchorPane tabPane;

    @FXML
    private Tab loadpane;

    MailInfo mailinfo = new MailInfo();

    @FXML
    void conButton(ActionEvent event) {
        mailinfo.setCount(2);
        mailinfo.setSender(conEmail.getText());
        mailinfo.setReciver("Admin");
        mailinfo.setSentmail(conTextarea.getText());
    }

    @FXML
    void login(ActionEvent event) {
        ViewController vc = new ViewController();
        vc.setFxmlFile("Login.fxml");
        tabPane.getChildren().setAll(vc.getFxmlFile());
    }

    @FXML
    void play(ActionEvent event) {

        if (mailinfo.getPage() > 2) {
            Stage stage = new Stage();
            ViewController getPane = new ViewController();
            getPane.setFxmlFile("LogoutPlay.fxml");
            AnchorPane root = getPane.getFxmlFile();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            mailinfo.setMailStage(stage);
        } else {
            ViewController vc = new ViewController();
            vc.setFxmlFile("Game1.fxml");
            gamePane.getChildren().setAll(vc.getFxmlFile());
        }

    }

    @FXML
    void registration(ActionEvent event) {
        ViewController vc = new ViewController();
        vc.setFxmlFile("Registration.fxml");
        tabPane.getChildren().setAll(vc.getFxmlFile());
    }

    @FXML
    void facebook(ActionEvent event) {
        ViewController vc = new ViewController();
        vc.setFxmlFile("WebView.fxml");
        gameInfoPane.getChildren().setAll(vc.getFxmlFile());
        mailinfo.setWebName("https://www.facebook.com/");
    }

    @FXML
    void gmail(ActionEvent event) {
        ViewController vc = new ViewController();
        vc.setFxmlFile("WebView.fxml");
        gameInfoPane.getChildren().setAll(vc.getFxmlFile());
        mailinfo.setWebName("https://www.google.com/");
    }

    @FXML
    void tutter(ActionEvent event) {
        ViewController vc = new ViewController();
        vc.setFxmlFile("WebView.fxml");
        gameInfoPane.getChildren().setAll(vc.getFxmlFile());
        mailinfo.setWebName("https://twitter.com/?lang=en");
    }

    @FXML
    void youtube(ActionEvent event) {
        ViewController vc = new ViewController();
        vc.setFxmlFile("WebView.fxml");
        gameInfoPane.getChildren().setAll(vc.getFxmlFile());
        mailinfo.setWebName("https://www.youtube.com/");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mailinfo.setTabPane(gamePane);
         mailinfo.setPage(2);
    }

}
