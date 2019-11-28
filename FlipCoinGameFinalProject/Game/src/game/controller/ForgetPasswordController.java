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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ForhadShorkerBabu
 */
public class ForgetPasswordController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane deshbord;
    
    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private Label lavel;
    
    MailInfo mailinfo = new MailInfo();


    @FXML
    void deshbord(ActionEvent event) {
        ViewController vc = new ViewController();
        vc.setFxmlFile("Login.fxml");
        deshbord.getChildren().setAll(vc.getFxmlFile());
    }
    @FXML
    void onAdmin(ActionEvent event) {
        mailinfo.setCount(2);
        mailinfo.setSender(email.getText());
        mailinfo.setReciver("Admin");
        mailinfo.setSentmail("Set New Password : " + password.getText());
        mailinfo.setSubject("change Password Recqust.");
        lavel.setText("Password Requst sent Sucessful");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
