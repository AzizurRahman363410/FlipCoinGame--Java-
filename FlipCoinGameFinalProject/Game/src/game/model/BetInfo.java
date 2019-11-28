package game.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BetInfo {

    private final SimpleIntegerProperty id = null;
    private final SimpleStringProperty user;
    private final SimpleIntegerProperty bet;
    private final SimpleStringProperty winlose;

    public BetInfo(String user, int bet, String winlose) {
        this.user = new SimpleStringProperty(user);
        this.bet = new SimpleIntegerProperty(bet);
        this.winlose = new SimpleStringProperty(winlose);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public void setBet(int bet) {
        this.bet.set(bet);
    }

    public void setWinlose(String winlose) {
        this.winlose.set(winlose);
    }

    public int getId() {
        return id.get();
    }

    public String getUser() {
        return user.get();
    }

    public int getBet() {
        return bet.get();
    }

    public String getWinlose() {
        return winlose.get();
    }

    @Override
    public String toString() {
        return "BetInfo{" + "id=" + id + ", user=" + user + ", bet=" + bet + ", winlose=" + winlose + '}';
    }

}
