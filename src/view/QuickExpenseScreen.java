package view;

import controller.BudgetController;
import database.DBHelper;
import javax.swing.*;

public class QuickExpenseScreen extends JFrame {

    private String category = "";

    public QuickExpenseScreen(BudgetController controller){

        setTitle("Add Expense");
        setSize(350,250);
        setLayout(null);

        JTextField amount = new JTextField();
        amount.setBounds(50,30,200,25);

        JButton food = new JButton("Food");
        food.setBounds(50,70,80,30);

        JButton transport = new JButton("Transport");
        transport.setBounds(140,70,100,30);

        JButton save = new JButton("Save");
        save.setBounds(50,120,100,30);

        // اختيار الكاتيجوري
        food.addActionListener(e -> category = "Food");
        transport.addActionListener(e -> category = "Transport");

        // 🟢 SAVE (هنا أهم جزء)
        save.addActionListener(e -> {

            try {
                double a = Double.parseDouble(amount.getText());

                if (category.equals("")) {
                    JOptionPane.showMessageDialog(null, "Select category first");
                    return;
                }

                // 1️⃣ Save in DB (ده اللي بيخلي History يظهر)
                DBHelper db = new DBHelper();
                db.insertTransaction(a, category);

                // 2️⃣ Update budget
                controller.addExpense(a);

                JOptionPane.showMessageDialog(null, "Saved Successfully ✔");

                // 3️⃣ back to dashboard
                new Dashboard(controller);
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid number");
            }
        });

        add(amount);
        add(food);
        add(transport);
        add(save);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}