package com.example.litebudgeting;

public class Income {
    private String workName;
    private boolean fixed_income;
    private float salary_pay;
    private float hourly_pay;
    private float hours_worked;
    private int pay_period;

//  Constructor for Government or Salary pay
    public Income (String workName, boolean fixed_income, float salary_pay, int pay_period) {
        this.workName = workName;
        this.fixed_income = fixed_income;
        this.salary_pay = salary_pay;
        this.pay_period = pay_period;
    }

    public Income (String workName,boolean fixed_income, float hourly_pay, float hours_worked, int pay_period){
        this.workName = workName;
        this.fixed_income = fixed_income;
        this.hourly_pay = hourly_pay;
        this.hours_worked = hours_worked;
        this.pay_period = pay_period;
    }

    public String getWorkName() {
        return workName;
    }

    public boolean isFixed_income() {
        return fixed_income;
    }

    public float getHours_worked() {
        return hours_worked;
    }

    public int getPay_period() {
        return pay_period;
    }

    public float getPay(){
        if (fixed_income){
            return salary_pay;
        }
        else {
            return hourly_pay * hours_worked;
        }
    }

    public void setSalary_pay(float salary_pay) {
        this.salary_pay = salary_pay;
    }

    public void setHourly_pay(float hourly_pay) {
        this.hourly_pay = hourly_pay;
    }

    public void setHours_worked(float hours_worked) {
        this.hours_worked = hours_worked;
    }

}
