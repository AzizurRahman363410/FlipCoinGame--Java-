/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.controller;

import game.model.MailInfo;
import game.view.ViewController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ForhadShorkerBabu
 */

public class LogoutPlayController implements Initializable {
        MailInfo mailinfo = new MailInfo();
        private Stage stage;

        private AnchorPane Pane;
     @FXML
    void cancel(ActionEvent event) {
        stage = mailinfo.getMailStage();
        stage.close();
    }

    @FXML
    void logout(ActionEvent event) {
        Pane = mailinfo.getTabPane();
        ViewController vc = new ViewController();
        vc.setFxmlFile("Game1.fxml");
        Pane.getChildren().setAll(vc.getFxmlFile());
         stage = mailinfo.getMailStage();
         stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
