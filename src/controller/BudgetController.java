package controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BudgetController {

    private double total;
    private double spent = 0;
    private int days;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate lastUpdate;

    public void createCycle(double t, int d){
        total = t;
        days = d;
        spent = 0;

        startDate = LocalDate.now();
        endDate = startDate.plusDays(d);

        lastUpdate = LocalDate.now();
    }

    public void addExpense(double amount){
        spent += amount;
    }

    public double getRemaining(){
        return total - spent;
    }

    // 🟢 daily limit بيتغير تلقائي
    public double calculateDailyLimit(){
        int remainingDays = (int) ChronoUnit.DAYS.between(LocalDate.now(), endDate);

        if (remainingDays <= 0) remainingDays = 1;

        return getRemaining() / remainingDays;
    }

    public int getCurrentDay(){
        return (int) ChronoUnit.DAYS.between(startDate, LocalDate.now()) + 1;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    public double getSpentPercentage(){
        if (total == 0) return 0;
        return (spent / total) * 100;
    }
}