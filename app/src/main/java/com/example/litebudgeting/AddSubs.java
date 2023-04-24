package com.example.litebudgeting;

public class AddSubs {
    private String subName;
    private float subCost;

    //  Constructor for Government or Salary pay
    public AddSubs (String subName, float subCost) {
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
