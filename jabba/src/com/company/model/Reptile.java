package com.company.model;

public class Reptile extends Inmate {
    public String toString() {
        return  "\n" + "reptile:" +
                "[Cost:" + getCost() +
                " Name:" + getName() +
                " Number:" + getNumber()+"]";
    }
}
