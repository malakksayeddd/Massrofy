package controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BudgetController {

    private double total;
    private double spent = 0;
    private int days;

    private LocalDate lastUpdate = LocalDate.now();

    // create budget cycle
    public void createCycle(double t, int d){
        total = t;
        days = d;
        spent = 0;
        lastUpdate = LocalDate.now();
    }

    // add expense
    public void addExpense(double amount){
        spent += amount;
    }

    // remaining money
    public double getRemaining(){
        return total - spent;
    }

    // 🟢 US5: rollover logic
    public void applyRollover(){

        LocalDate today = LocalDate.now();

        long passedDays = ChronoUnit.DAYS.between(lastUpdate, today);

        if (passedDays <= 0) return;

        days = days - (int) passedDays;

        if (days <= 0) days = 1;

        lastUpdate = today;

        System.out.println("Rollover applied ✔");
    }

    // daily limit (calls US5 automatically)
    public double calculateDailyLimit(){
        applyRollover();
        return getRemaining() / days;
    }
}