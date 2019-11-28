/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.controller;

import game.model.MailInfo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author ForhadShorkerBabu
 */
public class WebViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private WebView website;
    MailInfo mailinfo = new MailInfo();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine web = website.getEngine();
        web.load(mailinfo.getWebName());
    }    
    
}
