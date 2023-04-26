package com.example.litebudgeting;

public class Subscription {
    private String subName;
    private float subCost;

    //  Constructor for Government or Salary pay
    public Subscription (String subName, float subCost) {
        this.subName = subName;
        this.subCost = subCost;

    }

    public String getSubName() {
        return subName;
    }
    public float getSubCost() {
        return subCost;
    }

    public void setSubCost(float subCost) {
        this.subCost = subCost;
    }

}