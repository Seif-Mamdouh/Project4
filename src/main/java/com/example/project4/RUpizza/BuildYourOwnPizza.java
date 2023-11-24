package com.example.project4.RUpizza;

import com.example.project4.RUpizza.Size;

import java.util.List;

public class BuildYourOwnPizza extends Pizza {
    private static final double BASE_PRICE_SMALL = 8.99;
    private static final double TOPPING_PRICE = 1.49;
    private static final int INCLUDED_TOPPINGS = 4;
    public static final double TAX_RATE = 0.06625;
    private int orderID;

    private List<String> toppings;

    public BuildYourOwnPizza(Size size, boolean extraSauce, boolean extraCheese, List<String> toppings) {
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
        this.toppings = toppings;
    }


    /**
     * Sets the order ID for the specialty pizza.
     *
     * @param orderID The unique order ID to be assigned to the pizza.
     */
    @Override
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Retrieves the order ID of the specialty pizza.
     *
     * @return The order ID of the pizza.
     */
    public Integer getPizzaID() {
        return orderID;
    }

    /**
     * Calculates the total cost of the specialty pizza, including tax.
     *
     * @return The total cost of the pizza.
     */
    public double total(){
        return calculateTax() + calculatePrice();
    }
    /**
     * Calculates the tax amount for the specialty pizza.
     *
     * @return The tax amount for the pizza.
     */
    public double calculateTax() {
        return calculatePrice() * TAX_RATE;
    }

    /**
     * Calculates the price without tax for the order.
     * The toppings include an extra item (sauce) which is not actually part of the toppings.
     * We add it to our "toppings" array for our specific implementation.
     * @return
     */
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
        pizzaDetails.append("OrderID: ").append(orderID).append("\n");
        pizzaDetails.append("Build Your Own Pizza").append("\n");
        pizzaDetails.append("Size: ").append(size).append("\n");
        if (extraCheese) {
            pizzaDetails.append("Extra Cheese: yes\n");
        } else {
            pizzaDetails.append("Extra Cheese: no\n");
        }

        if (extraSauce) {
            pizzaDetails.append("Extra Sauce: yes\n");
        } else {
            pizzaDetails.append("Extra Sauce: no\n");
        }
        pizzaDetails.append("Toppings: ").append(String.join(", ", toppings)).append("\n");
        pizzaDetails.append("Total Price: $").append(calculatePrice()).append("\n");
        pizzaDetails.append("Tax: $").append(calculateTax()).append("\n");
        pizzaDetails.append("Total: $").append(total()).append("\n");

        return pizzaDetails.toString();
    }
}
