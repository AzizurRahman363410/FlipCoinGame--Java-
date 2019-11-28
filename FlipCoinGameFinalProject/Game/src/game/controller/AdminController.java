package game.controller;

import game.model.MailInfo;
import game.model.UserInfo;
import game.view.ViewController;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminController implements Initializable {

    MailInfo mailinfo = new MailInfo();
    private ObservableList<UserInfo> data = FXCollections.observableArrayList();
    FilteredList<UserInfo> filteredData=new FilteredList<>(data,e->true);
    @FXML
    private TableView<UserInfo> adminTabel;

    @FXML
    private TableColumn<UserInfo, Integer> idCol;

    @FXML
    private TableColumn<UserInfo, String> userCol;

    @FXML
    private TableColumn<UserInfo, String> passCol;

    @FXML
    private TableColumn<UserInfo, String> emailCol;

    @FXML
    private TextField idField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField searchField;

    @FXML
    void searchReleased(KeyEvent event) {
        searchField.textProperty().addListener((observableValue,oldValue,newValue)->{
			filteredData.setPredicate((Predicate<? super UserInfo>)user->{
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				 if(user.getUser().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<UserInfo> sortedData=new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(adminTabel.comparatorProperty());
		adminTabel.setItems(sortedData);
    }

    @FXML
    void onDelete(ActionEvent event) {

        UserInfo userInfo = adminTabel.getSelectionModel().getSelectedItem();

        if (userInfo != null) {
            adminTabel.getItems().removeAll(adminTabel.getSelectionModel().getSelectedItem());
            delete(userInfo.getId());
            lavel.setText("Delete Sucessfull ");
        } else {
            lavel.setText(" ");
        }
    }
    @FXML
    private Label lavel;

    @FXML
    void onUpdate(ActionEvent event) {
        UserInfo userInfo = adminTabel.getSelectionModel().getSelectedItem();
        userInfo.setEmail(emailField.getText());
        userInfo.setId(Integer.parseInt(idField.getText()));
        userInfo.setPass(passwordField.getText());
        userInfo.setUser(usernameField.getText());
        if (userInfo != null) {
            updateUser(userInfo.getId(), userInfo.getUser(), userInfo.getPass(), userInfo.getEmail());
            lavel.setText("Update Sucessful ");

        } else {
            lavel.setText(" ");
        }
        data.clear();
        UserDataTabel();
    }

    @FXML
    void onGetdata(MouseEvent event) {
        UserInfo userInfo = adminTabel.getSelectionModel().getSelectedItem();
        if (userInfo != null) {
            idField.setText(String.valueOf(userInfo.getId()));
            usernameField.setText(userInfo.getUser());
            passwordField.setText(userInfo.getPass());
            emailField.setText(userInfo.getEmail());

        } else {
            lavel.setText(" ");
        }

    }

    @FXML
    void mail(ActionEvent event) {
        if (mailinfo.getReciver() != null && mailinfo.getCount() == 2) {
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
        idCol.setCellValueFactory(
                new PropertyValueFactory<UserInfo, Integer>("id")
        );
        userCol.setCellValueFactory(
                new PropertyValueFactory<UserInfo, String>("user")
        );

        passCol.setCellValueFactory(
                new PropertyValueFactory<UserInfo, String>("pass")
        );
        emailCol.setCellValueFactory(
                new PropertyValueFactory<UserInfo, String>("email")
        );
         
        UserDataTabel();
        adminTabel.setItems(data);    
        mailinfo.setPage(3);
    }

    private Connection connect() {
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

    public void UserDataTabel() {
        String sql = "SELECT * FROM user_tbl";
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                data.add(new UserInfo(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("email")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void delete(int id) {
        String sql = "DELETE FROM user_tbl WHERE id = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateUser(int id, String user, String pass, String email) {
        String sql = "UPDATE user_tbl SET username = ? ,"
                + "password = ?,"
                + "email = ?"
                + "WHERE id = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            pstmt.setString(3, email);
            pstmt.setInt(4, id);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

