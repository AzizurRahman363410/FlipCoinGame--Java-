package game.model;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetData {

     
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

    public boolean loging(String user, String pass) {
        boolean log = false;
        String sql = "SELECT * FROM user_tbl";

        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                if (user.equalsIgnoreCase(rs.getString("username")) && pass.equalsIgnoreCase(rs.getString("password"))) {
                    log = true;
                    new LogInfo(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("email"));
                    Account(rs.getInt("id"));
                    getGrpTbl(rs.getInt("id"));
                    break;
                }  
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return log;
    }

    public boolean user(String user) {
        boolean log = true;
        String sql = "SELECT username FROM user_tbl";

        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                if (user.equalsIgnoreCase(rs.getString("username"))) {
                    log = false;
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return log;
    }

    public boolean regMail(String email) {
        boolean reg = true;
        String sql = "SELECT email FROM user_tbl";

        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                if (email.equalsIgnoreCase(rs.getString("email"))) {
                    reg = false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reg;
    }

    public void bet() {
        String sql = "SELECT * FROM bet_tb2 ORDER BY id DESC LIMIT 10";
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getString("username")+"\n"+rs.getInt("coin"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Account(int id) {
        String sql = "SELECT * FROM account_tbl";
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                if (id == rs.getInt("id")) {
                    new AccountInfo(rs.getInt("id"), rs.getString("name"), rs.getInt("amount"), rs.getInt("coin"));
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void getGrpTbl(int id) {
        String sql = "SELECT * FROM grp_tbl";
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                if (id == rs.getInt("id")) {
                    new GrapData(rs.getInt("id"), rs.getString("date"), rs.getInt("coin"));
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        GetData p = new GetData();
        //p.loging("a", "u");
        p.bet();

    }
}
