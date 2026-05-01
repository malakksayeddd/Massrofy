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

        JLabel remainingLabel = new JLabel("Remaining: " + remaining);
        remainingLabel.setBounds(50,30,300,30);

        JLabel dailyLabel = new JLabel("Daily Limit: " + daily);
        dailyLabel.setBounds(50,60,300,30);

        JLabel status = new JLabel("System Updated ✔");
        status.setBounds(50,90,200,30);
        status.setForeground(Color.BLUE);

        JButton addExpense = new JButton("Add Expense");
        addExpense.setBounds(50,130,150,30);

        addExpense.addActionListener(e -> {
            new QuickExpenseScreen(controller);
            dispose();
        });

        JButton chart = new JButton("View Chart");
        chart.setBounds(50,170,150,30);

        chart.addActionListener(e -> {
            new ChartScreen();
        });

        add(remainingLabel);
        add(dailyLabel);
        add(status);
        add(addExpense);
        add(chart);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}