package view;

import database.DBHelper;
import javax.swing.*;
import java.sql.ResultSet;

public class HistoryScreen extends JFrame {

    public HistoryScreen(){

        setTitle("History");
        setSize(400,400);
        setLayout(null);

        JTextArea area = new JTextArea();
        area.setBounds(20,20,340,320);

        try {
            DBHelper db = new DBHelper();
            ResultSet rs = db.getAllTransactions();

            StringBuilder sb = new StringBuilder();

            while (rs != null && rs.next()){
                sb.append(rs.getDouble("amount"))
                  .append(" - ")
                  .append(rs.getString("category"))
                  .append(" - ")
                  .append(rs.getString("timestamp"))
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