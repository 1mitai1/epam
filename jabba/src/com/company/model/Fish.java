package com.company.model;

public class Fish extends Inmate {

    private double accessoriesCost;

    public void setAccessoriesCost(double accessoriesCost) {
        this.accessoriesCost = accessoriesCost;
    }

    public double getCost() {
        return super.getCost() + accessoriesCost;
    }

    public String toString() {
        return  "\n" + "Fish:" +
                "[Cost:" + getCost() +
                " Name:" + getName() +
                " Number:" + getNumber()+"]";
    }

}
