package com.example.bottledispenser;
import java.util.*;
public class BottleDispenser {
    private int bottles;
    private double money;
    ArrayList<Bottle> bottle_array;
    private static BottleDispenser bottleDispenser = null;

    public BottleDispenser() {
        bottles = 5;
        money = 0;
    //Add bottles to bottle array list
        bottle_array = new ArrayList<Bottle>();
        String bottle;
        String manf;
        double tot_E;
        double price;
        double size;
        for(int i = 0;i<bottles;i++) {
            if(i<2) {
                bottle = "Pepsi Max";
                manf = "Pepsi";
                tot_E = 0.3;
                if(i<1) {
                    size = 0.5;
                    price =1.8;
                }else {
                    size = 1.5;
                    price = 2.2;
                }
            }else if(i<4) {
                bottle = "Coca-Cola Zero";
                manf = "Coca-Cola";
                tot_E = 0.4;
                if(i<3) {
                    size = 0.5;
                    price = 2.0;
                }else {
                    size = 1.5;
                    price = 2.5;
                }
            }else {
                bottle = "Fanta Zero";
                manf = "Fanta";
                tot_E = 0.5;
                size = 0.5;
                price = 1.95;
            }
            bottle_array.add(i,new Bottle(bottle, manf, tot_E, price, size));
        }
    }
    //Singleton methodology
    public static BottleDispenser getInstance(){
        if(bottleDispenser == null){
            bottleDispenser = new BottleDispenser();
        }
        return bottleDispenser;
    }
    //Add money to machine
    public String addMoney(double amount) {
        money += amount;
        String moneyAdded = "Klink! Money was added into the machine!";
        return moneyAdded;
    }
    //buy a bottle
    public String buyBottle(int choice) {
        String buybottle = null;
        Bottle b = bottle_array.get(choice); //get the selected bottle from the bottle array
        if(money < b.getPrice()) { //Check whether enough money in machine
            buybottle="Add money first!";
        }else if(bottles <=0){ //Check whether enough bottles
            buybottle = "No more bottles!";
        }else { //if all is good, buy bottle, thus removing the selected bottle from the bottle array and deducting the price of the bottle
            bottles -= 1;
            money -= b.getPrice();
            buybottle = "Bottle bought." + b.getName() + " " + b.getSize() + " " + b.getPrice();
            bottle_array.remove(b);
        }
        return buybottle; //Returns a string which is printed to the textView
    }
    //Create a new string array of all the bottles in the machine
    public ArrayList<String> listBottles() {
        ArrayList<String> all_bottles = new ArrayList<>();
        for(int i = 0; i<bottles;i++) {
            Bottle b = bottle_array.get(i);
            String info_bottles = "Name: " + b.getName()+" Size: " + b.getSize() + " Price: " + b.getPrice();
            all_bottles.add(info_bottles);
        }
        return all_bottles;
    }
    //Return the money that didn't get used
    public String returnMoney() {
        String left = String.format("%.2f", this.money);
        left = left.replace(".", ",");
        String get = ("Klink klink. Money came out! You got " + left + "€ back");
        money = 0;
        return get;
    }
}
