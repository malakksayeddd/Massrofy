package view;

import controller.BudgetController;
import database.DBHelper;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard(BudgetController controller){

        setTitle("Dashboard");
        setSize(400,400);
        setLayout(null);

        DBHelper db = new DBHelper();

        JLabel start = new JLabel("Start: " + controller.getStartDate());
        start.setBounds(20,10,200,20);

        JLabel end = new JLabel("End: " + controller.getEndDate());
        end.setBounds(200,10,200,20);

        JLabel day = new JLabel("Day: " + controller.getCurrentDay());
        day.setBounds(20,35,200,20);

        JLabel remaining = new JLabel("Remaining: " + controller.getRemaining());
        remaining.setBounds(20,60,300,30);

        JLabel daily = new JLabel("Daily Limit: " + controller.calculateDailyLimit());
        daily.setBounds(20,90,300,30);

        JLabel today = new JLabel("Today Spent: " + db.getTodaySpent());
        today.setBounds(20,120,300,30);

        // 🟢 Warning
        double percent = controller.getSpentPercentage();
        JLabel warning = new JLabel();
        warning.setBounds(20,150,300,30);

        if (percent >= 100){
            warning.setText("❌ Budget Exceeded");
            warning.setForeground(Color.RED);
        } else if (percent >= 80){
            warning.setText("⚠ 80% used!");
            warning.setForeground(Color.ORANGE);
        } else {
            warning.setText("✔ Safe");
            warning.setForeground(Color.GREEN);
        }

        JButton add = new JButton("Add Expense");
        add.setBounds(20,190,150,30);

        add.addActionListener(e -> {
            new QuickExpenseScreen(controller);
            dispose();
        });

        JButton history = new JButton("History");
        history.setBounds(200,190,150,30);

        history.addActionListener(e -> {
            new HistoryScreen();
        });

        add(start);
        add(end);
        add(day);
        add(remaining);
        add(daily);
        add(today);
        add(warning);
        add(add);
        add(history);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}