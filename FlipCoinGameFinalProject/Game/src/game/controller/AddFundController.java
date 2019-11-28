package game.controller;

import game.model.AccountInfo;
import game.model.UpdateData;
import game.view.ViewController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class AddFundController implements Initializable {

    @FXML
    private AnchorPane deshbord;

    @FXML
    private Label fundLabel;

    @FXML
    private TextField account;

    @FXML
    private TextField amount;

    private int amt;
    private UpdateData up = new UpdateData();
    private AccountInfo ai = new AccountInfo();

    @FXML
    void deshbord(ActionEvent event) {
        ViewController vc = new ViewController();
        vc.setFxmlFile("Deshbord.fxml");
        deshbord.getChildren().setAll(vc.getFxmlFile());
    }

    @FXML
    void onAddfund(ActionEvent event) {
        if (!amount.getText().isEmpty() && !account.getText().isEmpty() && Integer.parseInt(amount.getText()) <= ai.getAmount()) {
            amt = Integer.parseInt(amount.getText());
            ai.setAmount(amt + ai.getAmount());
            up.updateAmount(ai.getId(), ai.getAmount());
            fundLabel.setText("Add Fund Sucessful");
        }else{
            fundLabel.setText("Empty Field");
        }

    }

    @FXML
    void accountkey(KeyEvent event) {
        if (account.getText().length() > 11 || account.getText().length() < 11) {
            fundLabel.setText("invalid account!");
        } else {
            fundLabel.setText("");
        }
    }

    @FXML
    void amountkey(KeyEvent event) {
        if (amount.getText().length() > 3) {
            fundLabel.setText("invalid amount!");
        } else {
            fundLabel.setText("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
