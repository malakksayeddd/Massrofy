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

    public void insertTransaction(double amount, String category){
        try {
            String sql = "INSERT INTO transactions(amount, category) VALUES(?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, amount);
            ps.setString(2, category);

            ps.executeUpdate();
            ps.close();

            System.out.println("Inserted: " + amount + " - " + category);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

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