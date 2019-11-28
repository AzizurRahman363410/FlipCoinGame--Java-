package game.controller;

import game.model.GetData;
import game.model.InsertData;
import game.model.ValidetionCheck;
import game.view.ViewController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class RegistrationController implements Initializable {

    private boolean sentData = false;
    private String name, fnam, lnam;

    @FXML
    private AnchorPane startingPane;
    @FXML
    private CheckBox chbox;

    @FXML
    private Label reglabel;

    @FXML
    private TextField username;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private PasswordField password;

    @FXML
    private TextField email;

    GetData gd = new GetData();

    @FXML
    void onKeyReleasedEmail(KeyEvent event) {
        if (email.getText().trim().isEmpty()) {
            reglabel.setText("");
            sentData = false;
        } else if (!gd.regMail(email.getText())) {
            reglabel.setText("Email alreay use");
        } else {
            ValidetionCheck vc = new ValidetionCheck();
            if (vc.validMail(email.getText())) {
                reglabel.setText("");
                sentData = true;
            } else {
                sentData = false;
                reglabel.setText("Invalid email");
            }
        }

    }

    @FXML
    void onKeyReleasedFname(KeyEvent event) {
        if (fname.getText().trim().isEmpty()) {
            reglabel.setText("");
            sentData = false;
        } else {
            if (fname.getText().length() > 10 && fname.getText().length() < 3) {
                reglabel.setText("Invalid name length");
                sentData = false;
            } else {
                ValidetionCheck vc = new ValidetionCheck();
                if (vc.validName(fname.getText())) {
                    reglabel.setText("");
                    fnam = fname.getText();
                    sentData = true;
                } else {
                    sentData = false;
                    reglabel.setText("Invalid name");
                }
            }
        }

    }

    @FXML
    void onKeyReleasedLname(KeyEvent event) {
        if (lname.getText().trim().isEmpty()) {
            reglabel.setText("");
            sentData = false;
        } else {
            if (lname.getText().length() > 10 && lname.getText().length() < 3) {
                reglabel.setText("Invalid name length");
                sentData = false;
            } else {
                ValidetionCheck vc = new ValidetionCheck();
                if (vc.validName(lname.getText())) {
                    reglabel.setText("");
                    lnam = lname.getText();
                    sentData = true;
                } else {
                    sentData = false;
                    reglabel.setText("Invalid name");
                }
            }
        }

    }

    @FXML
    void onKeyReleasedPass(KeyEvent event) {
        if (password.getText().trim().isEmpty()) {
            reglabel.setText("");
            sentData = false;
        } else {
            if (password.getText().length() > 10 && password.getText().length() < 6) {
                reglabel.setText("Invalid pass length");
                sentData = false;
            } else {
                sentData = true;
            }
        }

    }

    @FXML
    void onKeyReleasedUser(KeyEvent event) {
        if (username.getText().trim().isEmpty()) {
            reglabel.setText("");
            sentData = false;
        } else {
            if (username.getText().length() > 10 && username.getText().length() < 4) {
                reglabel.setText("Invalid user length");
                sentData = false;
            } else {
                ValidetionCheck vc = new ValidetionCheck();
                if (vc.validUser(username.getText()) && gd.user(username.getText())) {
                    reglabel.setText("");
                    sentData = true;
                } else {
                    reglabel.setText("Invalid user");
                    sentData = false;
                }
            }
        }

    }

    @FXML
    void regbutton(ActionEvent event) {
        if (username.getText().isEmpty()) {
            reglabel.setText("username field is Empty");
        } else if (password.getText().isEmpty()) {
            reglabel.setText("password field is Empty");
        } else if (fname.getText().isEmpty()) {
            reglabel.setText("frist name field is Empty");
        } else if (lname.getText().isEmpty()) {
            reglabel.setText("last name field is Empty");
        } else if (email.getText().isEmpty()) {
            reglabel.setText("email field is Empty");
        } else if (!chbox.isSelected()) {
            reglabel.setText("check box field is uncheck");
        } else if (!gd.regMail(email.getText())) {
            reglabel.setText("Email alreay use");
        } else {
            if (sentData) {
                reglabel.setText("Registration Sucessful");
                InsertData data = new InsertData();
                name = fnam + " " + lnam;
                data.insertUserTbl(username.getText(), password.getText(), email.getText());
                data.insertAccountTbl(name, 100, 24);
                fade();
            } else {
                reglabel.setText("Registration not Sucessful");
            }
        }

    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void fade() {
        FadeTransition fadOut = new FadeTransition(Duration.seconds(3), startingPane);
        fadOut.setFromValue(1);
        fadOut.setToValue(0);
        fadOut.setCycleCount(1);
        fadOut.play();

        fadOut.setOnFinished((e) -> {
             GetData gd = new GetData();
             gd.loging(username.getText(), password.getText());
            ViewController getPane = new ViewController();
            getPane.setFxmlFile("Deshbord.fxml");
            AnchorPane root = getPane.getFxmlFile();
            startingPane.getChildren().setAll(root);
            FadeTransition fadIn = new FadeTransition(Duration.seconds(3), startingPane);
            fadIn.setToValue(1);
            fadIn.setFromValue(0);
            fadIn.setCycleCount(1);
            fadIn.play();
        });
    }
}
