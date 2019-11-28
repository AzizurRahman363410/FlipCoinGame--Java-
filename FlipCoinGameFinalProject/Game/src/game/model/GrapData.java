
package game.model;

public class GrapData {
    private  static int id;
    private  static String date;
    private  static int coin;

    public GrapData() {
    }

    public GrapData(int id, String date, int coin) {
        GrapData.id = id;
        GrapData.date = date;
        GrapData.coin = coin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        GrapData.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        GrapData.date = date;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        GrapData.coin = coin;
    }

    @Override
    public String toString() {
        return "GrapData{" + "id=" + id + ", date=" + date + ", coin=" + coin + '}';
    }
    
}
