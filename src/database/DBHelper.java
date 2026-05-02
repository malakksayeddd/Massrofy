package database;

import java.sql.*;

public class DBHelper {

    private static Connection conn;

    public DBHelper(){
        try {

            if (conn == null){

                // 👈 مهم: نفس الملف دايمًا
                conn = DriverManager.getConnection("jdbc:sqlite:./masroofy.db");

                Statement stmt = conn.createStatement();

                // 👈 جدول مضبوط بالـ localtime
                stmt.execute(
                    "CREATE TABLE IF NOT EXISTS transactions (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "amount REAL," +
                    "category TEXT," +
                    "timestamp TEXT DEFAULT (datetime('now','localtime'))" +
                    ")"
                );

                stmt.close();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // 🟢 INSERT (بيسجل فعلاً)
    public void insertTransaction(double amount, String category){
        try {

            System.out.println("Saving expense..."); // debug

            String sql = "INSERT INTO transactions(amount, category) VALUES(?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, amount);
            ps.setString(2, category);

            ps.executeUpdate();
            ps.close();

            System.out.println("Saved ✔");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

   public double getTodaySpent(){

    double total = 0;

    try {

        // 👈 تاريخ النهارده
        String today = java.time.LocalDate.now().toString();

        String sql =
            "SELECT SUM(amount) as total " +
            "FROM transactions " +
            "WHERE timestamp LIKE ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, today + "%");

        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            total = rs.getDouble("total");
        }

        System.out.println("Today = " + today);
        System.out.println("Today Spent = " + total);

    } catch (Exception e){
        e.printStackTrace();
    }

    return total;
}

    // 🟢 HISTORY
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
}