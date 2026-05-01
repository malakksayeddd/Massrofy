package controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BudgetController {

    private double total;
    private double spent = 0;
    private int days;

    private LocalDate lastUpdate = LocalDate.now();

    public void createCycle(double t, int d){
        total = t;
        days = d;
        spent = 0;
        lastUpdate = LocalDate.now();
    }

    public void addExpense(double amount){
        spent += amount;
    }

    public double getRemaining(){
        return total - spent;
    }

    public void applyRollover(){

        LocalDate today = LocalDate.now();

        long passedDays = ChronoUnit.DAYS.between(lastUpdate, today);

        if (passedDays <= 0) return;

        days = days - (int) passedDays;

        if (days <= 0) days = 1;

        lastUpdate = today;
    }

    public double calculateDailyLimit(){
        applyRollover();
        return getRemaining() / days;
    }

    public double getSpentPercentage(){
        if (total == 0) return 0;
        return (spent / total) * 100;
    }
}