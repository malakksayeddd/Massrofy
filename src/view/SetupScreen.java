package view;

import controller.BudgetController;
import javax.swing.*;

public class SetupScreen extends JFrame {

    public SetupScreen(BudgetController controller){

        setTitle("Setup Budget");
        setSize(300,220);
        setLayout(null);

        JLabel l1 = new JLabel("Total Budget:");
        l1.setBounds(50,10,120,20);

        JTextField amount = new JTextField();
        amount.setBounds(50,30,150,25);

        JLabel l2 = new JLabel("Days:");
        l2.setBounds(50,70,100,20);

        JTextField days = new JTextField();
        days.setBounds(50,90,150,25);

        JButton start = new JButton("Start");
        start.setBounds(50,130,120,30);

        start.addActionListener(e -> {

            double t = Double.parseDouble(amount.getText());
            int d = Integer.parseInt(days.getText());

            controller.createCycle(t, d);

            new Dashboard(controller);
            dispose();
        });

        add(l1);
        add(amount);
        add(l2);
        add(days);
        add(start);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}