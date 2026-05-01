package database;

import java.sql.*;

public class DBHelper {

    private Connection conn;

    public DBHelper(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:masroofy.db");

            Statement stmt = conn.createStatement();

            stmt.execute(
                "CREATE TABLE IF NOT EXISTS transactions (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "amount REAL," +
                "category TEXT," +
                "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)"
            );

            stmt.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // insert expense
    public void insertTransaction(double amount, String category){
        try {
            String sql = "INSERT INTO transactions(amount, category) VALUES(?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, amount);
            ps.setString(2, category);

            ps.executeUpdate();
            ps.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // 🟢 US7: get all history
    public ResultSet getAllTransactions(){
        try {
            String sql = "SELECT * FROM transactions ORDER BY timestamp DESC";
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // chart helper (optional)
    public ResultSet getCategoryTotals(){
        try {
            String sql =
                "SELECT category, SUM(amount) as total " +
                "FROM transactions GROUP BY category";

            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}