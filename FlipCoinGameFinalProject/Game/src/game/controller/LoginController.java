package game.controller;

import game.model.GetData;
import game.view.ViewController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane loginPane;

    @FXML
    private TextField username;

     @FXML
    private Label logLabel;

    @FXML
    private PasswordField password;

    @FXML
    void login(ActionEvent event) {
        if (username.getText().equalsIgnoreCase("ADMIN") && password.getText().equalsIgnoreCase("ADMIN")) {
            ViewController vc = new ViewController();
            vc.setFxmlFile("Admin.fxml");
            loginPane.getChildren().setAll(vc.getFxmlFile());
        } else {
            GetData gd = new GetData();
            if (gd.loging(username.getText(), password.getText())) {
                ViewController vc = new ViewController();
                vc.setFxmlFile("Deshbord.fxml");
                loginPane.getChildren().setAll(vc.getFxmlFile());
            }else{
                logLabel.setText("Login Fail");
            }
            

        }

    }

    @FXML
    void onMouse(MouseEvent event) {
        ViewController vc = new ViewController();
        vc.setFxmlFile("ForgetPassword.fxml");
        loginPane.getChildren().setAll(vc.getFxmlFile());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
