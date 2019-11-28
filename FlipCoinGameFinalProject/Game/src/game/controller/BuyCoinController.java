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

public class BuyCoinController implements Initializable {

    @FXML
    private Label coin;

    @FXML
    private TextField amount;

    @FXML
    private Label label;

    @FXML
    private AnchorPane deshbord;

    private int amt;
    private UpdateData up = new UpdateData();
    private AccountInfo ai = new AccountInfo();

    @FXML
    void onkeyReleased(KeyEvent event) {
        if (ai.getAmount() >= Integer.parseInt(amount.getText())) {
            amt = Integer.parseInt(amount.getText());
            amt *= 24;
            coin.setText("Coin : " + String.valueOf(amt));
            label.setText("");
        } else {
            label.setText("insufficient balance");
        }

    }

    @FXML
    void getCoin(ActionEvent event) {
        if (!amount.getText().isEmpty() && Integer.parseInt(amount.getText()) <= ai.getAmount()) {
            up.updateBuyCoin(ai.getId(), (int) (ai.getAmount() - (amt / 24)), (int) (ai.getCoin() + amt));
            ai.setAmount((int) (ai.getAmount() - (amt / 24)));
            ai.setCoin((int) (ai.getCoin() + amt));
            amount.setText("");
            coin.setText("");
            label.setText(amt +" Coin buy sucessful");
        }else{
            label.setText("Empty Field");
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
