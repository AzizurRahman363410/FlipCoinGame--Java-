
package game.model;


public class AccountInfo {
    private static int id;
    private static String name;    
    private static int amount;
    private static int coin;

    public AccountInfo() {
    }

    public AccountInfo(int id, String name, int amount, int coin) {
        AccountInfo.id = id;
        AccountInfo.name = name;
        AccountInfo.amount = amount;
        AccountInfo.coin = coin;
    }

    public void setId(int id) {
        AccountInfo.id = id;
    }

    public  void setName(String name) {
        AccountInfo.name = name;
    }

    public void setAmount(int amount) {
        AccountInfo.amount = amount;
    }

    public void setCoin(int coin) {
        AccountInfo.coin = coin;
    }

    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getCoin() {
        return coin;
    }

    @Override
    public String toString() {
        return "AccountInfo{" + "id=" + id + ", name=" + name + ", amount=" + amount + ", coin=" + coin + '}';
    }
    
    
    
}
