package com.example.project4.RUpizza;

import java.util.ArrayList;

public abstract class Pizza {
    protected static ArrayList<String> toppings;
    protected Size size;
    protected Sauce sauce;
    protected boolean extraSauce;
    protected boolean extraCheese;

    public Pizza() {

    }

    // Enum for Pizza Types
    public enum PizzaType {
        DELUXE, SUPREME, MEATZZA, SEAFOOD, PEPPERONI, BUILD_YOUR_OWN
    }

    // Constructor
    public Pizza(ArrayList<String> toppings, Size size, Sauce sauce, boolean extraSauce, boolean extraCheese) {
        this.toppings = toppings;
        this.size = size;
        this.sauce = sauce;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    public static Pizza createPizza(PizzaType pizzaType, Size size, Sauce sauce, boolean extraSauce, boolean extraCheese) {
        return switch (pizzaType) {
            case DELUXE, SUPREME, MEATZZA, SEAFOOD, PEPPERONI -> new SpecialityPizza(pizzaType, size, sauce, extraSauce, extraCheese);
            case BUILD_YOUR_OWN -> new BuildYourOwnPizza(size, sauce, extraSauce, extraCheese, toppings);
        };
    }

    // Abstract method to be implemented by subclasses
    public abstract double calculatePrice();
}