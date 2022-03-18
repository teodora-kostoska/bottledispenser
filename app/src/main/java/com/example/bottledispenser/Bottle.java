package com.example.bottledispenser;

public class Bottle {
    private String name;
    private String manufacturer;
    private double total_energy;
    private double price;
    private double size;
    //Default bottle
    public Bottle(){
        name = "Pepsi Max";
        manufacturer = "Pepsi";
        total_energy = 0.3;
        price = 1.8;
        size = 0.5;
    }
    //Create custom bottles
    public Bottle(String name, String manuf, double totE, double price, double size){
        this.name = name;
        manufacturer = manuf;
        total_energy = totE;
        this.price = price;
        this.size = size;
    }
    public double getPrice() {
        return price;
    }
    public double getSize() {
        return size;
    }
    public String getName(){
        return name;
    }
}

