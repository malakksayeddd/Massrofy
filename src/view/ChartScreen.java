package view;

import database.DBHelper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.sql.ResultSet;

public class ChartScreen extends JFrame {

    public ChartScreen(){

        setTitle("Spending Chart");
        setSize(400,400);

        DefaultPieDataset dataset = new DefaultPieDataset();

        try {
            DBHelper db = new DBHelper();
            ResultSet rs = db.getCategoryTotals();

            boolean hasData = false;

            while (rs != null && rs.next()){

                hasData = true;

                dataset.setValue(
                        rs.getString("category"),
                        rs.getDouble("total")
                );
            }

            if (!hasData){
                dataset.setValue("No Data", 1);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Expenses Breakdown",
                dataset,
                true,
                true,
                false
        );

        setContentPane(new ChartPanel(chart));

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}