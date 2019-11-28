package game.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserInfo {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty user;
    private final SimpleStringProperty pass;
    private final SimpleStringProperty email;

  

    public UserInfo(int id, String user, String pass, String email) {
        this.id = new SimpleIntegerProperty(id);
        this.user = new SimpleStringProperty(user);
        this.pass = new SimpleStringProperty(pass);
        this.email = new SimpleStringProperty(email);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public void setPass(String pass) {
        this.pass.set(pass);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public int getId() {
        return id.get();
    }

    public String getUser() {
        return user.get();
    }

    public String getPass() {
        return pass.get();
    }

    public String getEmail() {
        return email.get();
    }

    @Override
    public String toString() {
        return "UserInfo{" + "id=" + id + ", user=" + user + ", pass=" + pass + ", email=" + email + '}';
    }

}
