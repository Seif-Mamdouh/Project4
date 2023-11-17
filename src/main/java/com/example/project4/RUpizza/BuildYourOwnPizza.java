package com.example.project4.RUpizza;

import com.example.project4.RUpizza.Size;

import java.util.List;

public class BuildYourOwnPizza extends Pizza {
    private static final double BASE_PRICE_SMALL = 8.99;
    private static final double TOPPING_PRICE = 1.49;
    private static final int INCLUDED_TOPPINGS = 4;

    private List<String> toppings;

    public BuildYourOwnPizza(Size size, boolean extraSauce, boolean extraCheese, List<String> toppings) {
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
        this.toppings = toppings;
    }


    @Override
    public void setOrderID(int orderID) {

    }

    @Override
    public double calculatePrice() {
        double basePrice = calculateBasePrice();
        double sizePrice = calculateSizePrice();
        double extraSauceAndCheesePrice = calculateExtraSauceAndCheesePrice();
        double toppingsPrice = calculateToppingsPrice();

        return basePrice + sizePrice + extraSauceAndCheesePrice + toppingsPrice;
    }

    private double calculateBasePrice() {
        switch (size) {
            case SMALL, MEDIUM, LARGE -> {
                return BASE_PRICE_SMALL;
            }
        }
        return 0.0;
    }

    private double calculateSizePrice() {
        switch (size) {
            case SMALL -> {
                return 0.0;
            }
            case MEDIUM -> {
                return 2.0;
            }
            case LARGE -> {
                return 4.0;
            }
        }
        return 0.0;
    }

    private double calculateExtraSauceAndCheesePrice() {
        double extraSaucePrice = extraSauce ? 1.0 : 0.0;
        double extraCheesePrice = extraCheese ? 1.0 : 0.0;

        return extraSaucePrice + extraCheesePrice;
    }

    private double calculateToppingsPrice() {
        int additionalToppings = Math.max(0, toppings.size() - INCLUDED_TOPPINGS);
        return additionalToppings * TOPPING_PRICE;
    }

    @Override
    public String toString() {
        StringBuilder pizzaDetails = new StringBuilder();
        pizzaDetails.append("Build Your Own Pizza").append("\n");
        pizzaDetails.append(size).append("\n");
        pizzaDetails.append(extraSauce).append("\n");
        pizzaDetails.append(extraCheese).append("\n");
        pizzaDetails.append("Toppings: ").append(String.join(", ", toppings)).append("\n");
        pizzaDetails.append(calculatePrice()).append("\n");

        return pizzaDetails.toString();
    }
}
