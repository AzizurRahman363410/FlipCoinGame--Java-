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

public class CashOutController implements Initializable {

    @FXML
    private AnchorPane deshbord;

    @FXML
    private Label label;

    @FXML
    private TextField account;

    @FXML
    private TextField amount;

    private int amt;
    private UpdateData up = new UpdateData();
    private AccountInfo ai = new AccountInfo();

    @FXML
    void onCashout(ActionEvent event) {
        if (!amount.getText().isEmpty() && Integer.parseInt(amount.getText()) <= ai.getAmount() && !account.getText().isEmpty()) {
            amt = Integer.parseInt(amount.getText());
            ai.setAmount(ai.getAmount() - amt);
            up.updateAmount(ai.getId(), ai.getAmount());
            label.setText("Cash Out Sucessful");
        }else{
            label.setText("Cash Out Unsucessful");
        }
    }

    @FXML
    void accountkey(KeyEvent event) {
        if (account.getText().length() > 11 || account.getText().length() < 11) {
            label.setText("invalid account!");
        } else {
            label.setText("");
        }
    }

    @FXML
    void amountkey(KeyEvent event) {
        if (amount.getText().length() > 3) {
            label.setText("invalid amount!");
        } else {
            label.setText("");
        }
    }

    @FXML
    void deshbord(ActionEvent event) {
        ViewController vc = new ViewController();
        vc.setFxmlFile("Deshbord.fxml");
        deshbord.getChildren().setAll(vc.getFxmlFile());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
