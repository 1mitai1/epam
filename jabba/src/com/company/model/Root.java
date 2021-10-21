package com.company.model;

import java.util.List;

public class Root {
    private String name;
    private int numberOfFish;
    private int numberOfReptiles;
    private double cost;
    private List<Fish> fish;
    private List<Reptile> reptile;


    public void countClass() {
        for (int i = 0; i < reptile.size(); i++) {
            this.numberOfReptiles += reptile.get(i).getNumber();
        }
        for (int i = 0; i < fish.size(); i++) {
            this.numberOfFish += fish.get(i).getNumber();
        }
    }

    public void countCost() {
        for (int i = 0; i < reptile.size(); i++) {
            this.cost += reptile.get(i).getCost() * reptile.get(i).getNumber();
        }
        for (int i = 0; i < fish.size(); i++) {
            this.cost += fish.get(i).getCost() * fish.get(i).getNumber();
        }

    }


    @Override
    public String toString() {
        return "name=" + name + '\n' +
                "cost=" + cost + '\n' +
                "numberOfFish=" + numberOfFish + '\n' +
                "numberOfReptiles=" + numberOfReptiles + '\n' +
                "fish=" + fish + '\n' +
                "reptile=" + reptile;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFish(List<Fish> fish) {
        this.fish = fish;
    }

    public void setReptile(List<Reptile> reptile) {
        this.reptile = reptile;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfFish() {
        return numberOfFish;
    }

    public int getNumberOfReptiles() {
        return numberOfReptiles;
    }

    public double getCost() {
        return cost;
    }
}
