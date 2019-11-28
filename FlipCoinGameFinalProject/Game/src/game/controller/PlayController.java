package game.controller;

import game.model.AccountInfo;
import game.model.BetInfo;
import game.model.InsertData;
import game.model.LogInfo;
import game.model.UpdateData;
import game.view.ViewController;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class PlayController implements Initializable {

    @FXML
    private Label username;

    @FXML
    private Label cash;

    @FXML
    private Label coin;

    @FXML
    private TableView<BetInfo> tabelView;

    @FXML
    private TableColumn<BetInfo, String> usertbl;

    @FXML
    private TableColumn<BetInfo, Integer> bettbl;

    @FXML
    private TableColumn<BetInfo, String> winlosstbl;

    @FXML
    private TextField betAmount;

    @FXML
    private ImageView imgview;

    @FXML
    private Label label;

    private ObservableList<BetInfo> data = FXCollections.observableArrayList();

    private UpdateData up = new UpdateData();
    private AccountInfo ai = new AccountInfo();
    private Random random = new Random();
    private InsertData insertData = new InsertData();
    private LogInfo li = new LogInfo();
    private int amt;

    @FXML
    void onKeyRelesased(KeyEvent event) {
        if (betAmount.getText().trim().isEmpty()) {
            label.setText("");
        }
        if (25 > Integer.parseInt(betAmount.getText())) {
            label.setText("Incriment Bet");
        } else {
            if (ai.getCoin() >= Integer.parseInt(betAmount.getText()) && Integer.parseInt(betAmount.getText()) >= 0) {
                amt = Integer.parseInt(betAmount.getText());
                label.setText("");

            } else {
                label.setText("insufficient balance");
            }
        }

    }

    @FXML
    void head(ActionEvent event) {
        if (ai.getCoin() >= Integer.parseInt(betAmount.getText()) && Integer.parseInt(betAmount.getText()) >= 25) {
            if (0 == random.nextInt(2)) {
                ai.setCoin(amt + ai.getCoin());
                up.updateCoin(ai.getId(), ai.getCoin());
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Win");
                alert.setHeaderText(null);
                alert.setContentText("Your Guess is Right!");
                alert.showAndWait();
                insertData.insertBetTbl(li.getUsername(), Integer.parseInt(betAmount.getText()), "win");
                data.clear();
                bet();
            } else {
                ai.setCoin(ai.getCoin() - amt);
                up.updateCoin(ai.getId(), ai.getCoin());
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Loss");
                alert.setHeaderText(null);
                alert.setContentText("Your Guess is Wrong!");
                alert.showAndWait();
                insertData.insertBetTbl(li.getUsername(), Integer.parseInt(betAmount.getText()), "loss");
                data.clear();
                bet();
            }
        } else {
            label.setText("insufficient balance");
        }
        cash.setText("Cash : " + ai.getAmount());
        coin.setText("Coin : " + ai.getCoin());
    }

    @FXML
    void tail(ActionEvent event) {
        if (ai.getCoin() >= Integer.parseInt(betAmount.getText()) && Integer.parseInt(betAmount.getText()) >= 25) {
            if (1 == random.nextInt(2)) {
                ai.setCoin(amt + ai.getCoin());
                up.updateCoin(ai.getId(), ai.getCoin());
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Win");
                alert.setHeaderText(null);
                alert.setContentText("Your Guess is Right!");
                alert.showAndWait();
                insertData.insertBetTbl(li.getUsername(), Integer.parseInt(betAmount.getText()), "win");
                data.clear();
                bet();
            } else {
                ai.setCoin(ai.getCoin() - amt);
                up.updateCoin(ai.getId(), ai.getCoin());
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Loss");
                alert.setHeaderText(null);
                alert.setContentText("Your Guess is Wrong!");
                alert.showAndWait();
                insertData.insertBetTbl(li.getUsername(), Integer.parseInt(betAmount.getText()), "loss");
                data.clear();
                bet();
            }
        } else {
            label.setText("insufficient balance");
        }
        cash.setText("Cash : " + ai.getAmount());
        coin.setText("Coin : " + ai.getCoin());
    }
    @FXML
    private AnchorPane button;

    @FXML
    void button(ActionEvent event) {
        ViewController vc = new ViewController();
        vc.setFxmlFile("Deshbord.fxml");
        button.getChildren().setAll(vc.getFxmlFile());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText(ai.getName());
        cash.setText("Cash : " + ai.getAmount());
        coin.setText("Coin : " + ai.getCoin());
        usertbl.setCellValueFactory(
                new PropertyValueFactory<BetInfo, String>("user")
        );
        bettbl.setCellValueFactory(
                new PropertyValueFactory<BetInfo, Integer>("bet")
        );
        winlosstbl.setCellValueFactory(
                new PropertyValueFactory<BetInfo, String>("winlose")
        );
        bet();
        tabelView.setItems(data);

    }

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:game.db";
        Connection conn = null;
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex) {
            }
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void bet() {
        String sql = "SELECT * FROM bet_tb2 ORDER BY id DESC LIMIT 10";
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                data.add(new BetInfo(rs.getString("username"), rs.getInt("coin"), rs.getString("win")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
