package view;

import database.DBHelper;
import javax.swing.*;
import java.sql.ResultSet;

public class HistoryScreen extends JFrame {

    public HistoryScreen(){

        setTitle("Expense History");
        setSize(400,400);
        setLayout(null);

        JTextArea area = new JTextArea();
        area.setBounds(20,20,340,320);

        try {
            DBHelper db = new DBHelper();
            ResultSet rs = db.getAllTransactions();

            StringBuilder sb = new StringBuilder();

            while (rs != null && rs.next()){

                double amount = rs.getDouble("amount");
                String category = rs.getString("category");
                String time = rs.getString("timestamp");

                sb.append(amount)
                  .append(" - ")
                  .append(category)
                  .append(" - ")
                  .append(time)
                  .append("\n");
            }

            area.setText(sb.toString());

        } catch (Exception e){
            e.printStackTrace();
        }

        add(area);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}