package view;

import controller.BudgetController;
import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard(BudgetController controller){

        setTitle("Dashboard");
        setSize(400,300);
        setLayout(null);

        double remaining = controller.getRemaining();
        double daily = controller.calculateDailyLimit();
        double percent = controller.getSpentPercentage();

        JLabel remainingLabel = new JLabel("Remaining: " + remaining);
        remainingLabel.setBounds(50,30,300,30);

        JLabel dailyLabel = new JLabel("Daily Limit: " + daily);
        dailyLabel.setBounds(50,60,300,30);

        JLabel warning = new JLabel();
        warning.setBounds(50,90,300,30);

        if (percent >= 100){
            warning.setText("❌ Budget Exceeded!");
            warning.setForeground(Color.RED);
        } else if (percent >= 80){
            warning.setText("⚠ Warning: " + percent + "% used");
            warning.setForeground(Color.ORANGE);
        } else {
            warning.setText("Budget Safe ✔");
            warning.setForeground(Color.GREEN);
        }

        JButton addExpense = new JButton("Add Expense");
        addExpense.setBounds(50,130,150,30);

        addExpense.addActionListener(e -> {
            new QuickExpenseScreen(controller);
            dispose();
        });

        JButton chart = new JButton("Chart");
        chart.setBounds(50,170,150,30);

        chart.addActionListener(e -> {
            new ChartScreen();
        });

        // 🟢 US7 button
        JButton history = new JButton("History");
        history.setBounds(220,130,120,30);

        history.addActionListener(e -> {
            new HistoryScreen();
        });

        add(remainingLabel);
        add(dailyLabel);
        add(warning);
        add(addExpense);
        add(chart);
        add(history);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}