package com.example.project4.RUpizza;

import java.util.ArrayList;

public abstract class Pizza {
    protected ArrayList<Toppings> toppings;
    protected Size size;
    protected Sauce sauce;
    protected boolean extraSauce;
    protected boolean extraCheese;
    protected String pizzaType;

    // Enum for Pizza Types
    public enum PizzaType {
        DELUXE, SUPREME, MEATZZA, SEAFOOD, PEPPERONI, BUILD_YOUR_OWN
    }

    public static Pizza createPizza(String pizzaType) {
        return switch (PizzaType.valueOf(pizzaType.toUpperCase())) {
            case DELUXE, SUPREME, MEATZZA, SEAFOOD, PEPPERONI, BUILD_YOUR_OWN -> new Pizza() {
                @Override
                public double calculatePrice() {
                    return 0.0;
                }
            };
            default -> throw new IllegalArgumentException("Invalid pizza type: " + pizzaType);
        };
    }

    public abstract double calculatePrice();

}

