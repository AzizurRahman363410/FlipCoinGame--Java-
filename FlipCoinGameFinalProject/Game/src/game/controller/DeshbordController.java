package game.controller;

import game.model.AccountInfo;
import game.model.GrapData;
import game.model.InsertData;
import game.model.MailInfo;
import game.model.UpdateData;
import game.view.ViewController;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DeshbordController implements Initializable {

    private MailInfo mailinfo = new MailInfo();
    private AccountInfo accountInfo = new AccountInfo();
    private UpdateData updateData = new UpdateData();
    private int datacoin[] = new int[5];
    private GrapData grapdata = new GrapData();
    private Calendar now = Calendar.getInstance();
    private InsertData insertdata = new InsertData();

    @FXML
    private LineChart<?, ?> charts;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Label name;

    @FXML
    private Label cash;

    @FXML
    private Label coin;

    @FXML
    private AnchorPane deshbord;

    @FXML
    void play(ActionEvent event) {
        ViewController vc = new ViewController();
        vc.setFxmlFile("Play.fxml");
        deshbord.getChildren().setAll(vc.getFxmlFile());
    }

    @FXML
    void addfund(ActionEvent event) {
        ViewController vc = new ViewController();
        vc.setFxmlFile("AddFund.fxml");
        deshbord.getChildren().setAll(vc.getFxmlFile());
    }

    @FXML
    void buycoin(ActionEvent event) {
        ViewController vc = new ViewController();
        vc.setFxmlFile("BuyCoin.fxml");
        deshbord.getChildren().setAll(vc.getFxmlFile());
    }

    @FXML
    void cashout(ActionEvent event) {
        ViewController vc = new ViewController();
        vc.setFxmlFile("CashOut.fxml");
        deshbord.getChildren().setAll(vc.getFxmlFile());
    }

    @FXML
    void convartcoin(ActionEvent event) {
        ViewController vc = new ViewController();
        vc.setFxmlFile("ConvertCoin.fxml");
        deshbord.getChildren().setAll(vc.getFxmlFile());
    }

    @FXML
    void mail(ActionEvent event) {
        if (mailinfo.getReciver() != null && mailinfo.getCount() == 3) {
            Stage stage = new Stage();
            mailinfo.setMailStage(stage);
            ViewController getPane = new ViewController();
            getPane.setFxmlFile("Mail.fxml");
            AnchorPane root = getPane.getFxmlFile();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("No Message Here!");
            alert.showAndWait();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        chartData();
        getCoinDate();
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("1", datacoin[0]));
        series.getData().add(new XYChart.Data("2", datacoin[1]));
        series.getData().add(new XYChart.Data("3", datacoin[2]));
        series.getData().add(new XYChart.Data("4", datacoin[3]));
        series.getData().add(new XYChart.Data("5", datacoin[4]));

        charts.getData().add(series);
        name.setText("User : " + accountInfo.getName());
        cash.setText("Cash : " + String.valueOf(accountInfo.getAmount()));
        coin.setText("Coin : " + String.valueOf(accountInfo.getCoin()));
        mailinfo.setPage(3);
    }

    public void chartData() {

        int date = now.get(Calendar.DATE);
        if (grapdata.getDate()==null) {
            grapdata.setDate(String.valueOf(date));
            insertdata.insertGrpTbl(accountInfo.getId(), String.valueOf(date), accountInfo.getCoin());
        }
        if (Integer.parseInt(grapdata.getDate()) != date) {
            insertdata.insertGrpTbl(accountInfo.getId(), String.valueOf(date), accountInfo.getCoin());
        } else {
            updateData.updateDateCoin(accountInfo.getId(), accountInfo.getCoin());
        }
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

    public void getCoinDate() {
        String sql = "SELECT coin FROM grp_tbl ORDER BY id DESC LIMIT 5";
        int id = 0;
        datacoin[0]=0;
        datacoin[1]=0;
        datacoin[2]=0;
        datacoin[3]=0;
        datacoin[4]=0;
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                datacoin[id] = rs.getInt("coin");
                id++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
