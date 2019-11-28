package game.model;

public class LogInfo {

    private static int id;
    private static String username;
    private static String password;
    private static String email;

    public LogInfo() {
    }

    public LogInfo(int id, String username, String password, String email) {
        LogInfo.id = id;
        LogInfo.username = username;
        LogInfo.password = password;
        LogInfo.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "LogInfo{" + "id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + '}';
    }

}
