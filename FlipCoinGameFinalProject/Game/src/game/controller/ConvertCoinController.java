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

public class ConvertCoinController implements Initializable {

    @FXML
    private AnchorPane deshbord;

    @FXML
    private TextField coinText;

    @FXML
    private Label amountLabel;

    @FXML
    private Label label;

    private int amt;
    private UpdateData up = new UpdateData();
    private AccountInfo ai = new AccountInfo();

    @FXML
    private void deshbord(ActionEvent event) {
        ViewController vc = new ViewController();
        vc.setFxmlFile("Deshbord.fxml");
        deshbord.getChildren().setAll(vc.getFxmlFile());
    }

    @FXML
    void getAmount(ActionEvent event) {
        if (!coinText.getText().isEmpty() && Integer.parseInt(coinText.getText()) <= ai.getCoin()) {
            up.updateBuyCoin(ai.getId(), (int) (ai.getAmount() + amt), (int) (ai.getCoin() - (24 * amt)));
            ai.setAmount((int) (ai.getAmount() + amt));
            ai.setCoin((int) (ai.getCoin() - (24 * amt)));
            coinText.setText("");
            amountLabel.setText("");
            label.setText((amt*24) +" Convert Coin Sucessful");
        }else{
            coinText.setText("");
            amountLabel.setText("");
            label.setText("Convert Coin Unsucessful");
        }

    }

    @FXML
    void onkeyReleased(KeyEvent event) {
        if (ai.getCoin() >= Integer.parseInt(coinText.getText())) {
            amt = Integer.parseInt(coinText.getText());
            amt /= 24;
            amountLabel.setText("Amount : " + String.valueOf(amt));
            label.setText("");
        } else {
            label.setText("insufficient balance");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
