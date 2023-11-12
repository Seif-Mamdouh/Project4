package com.example.project4.RUpizza;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class SpecialityPizza extends Pizza {

    private final PizzaType pizzaType;

    public SpecialityPizza(PizzaType pizzaType, Size size, Sauce sauce, boolean extraSauce, boolean extraCheese) {
        this.pizzaType = pizzaType;
        this.size = size;
        this.sauce = sauce;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    @Override
    public double calculatePrice() {
        double basePrice = calculateBasePrice();
        double sizePrice = calculateSizePrice();
        double extraSauceAndCheesePrice = calculateExtraSauceAndCheesePrice();

        return basePrice + sizePrice + extraSauceAndCheesePrice;
    }

    private double calculateBasePrice() {
        // Your implementation for base price calculation
        return 0.0;
    }

    private double calculateSizePrice() {
        // Your implementation for size price calculation
        return switch (size) {
            case SMALL -> 14.99;
            case MEDIUM -> 16.99;
            case LARGE -> 20.99;
        };
    }

    private double calculateExtraSauceAndCheesePrice() {
        double extraSaucePrice = extraSauce ? 1.0 : 0.0;
        double extraCheesePrice = extraCheese ? 1.0 : 0.0;

        return extraSaucePrice + extraCheesePrice;
    }

    @Override
    public String toString() {
        StringBuilder pizzaDetails = new StringBuilder();
        pizzaDetails.append("Pizza Type: ").append(pizzaType).append("\n");
        pizzaDetails.append("Size: ").append(size).append("\n");
        pizzaDetails.append("Sauce: ").append(sauce).append("\n");
        pizzaDetails.append("Extra Sauce: ").append(extraSauce).append("\n");
        pizzaDetails.append("Extra Cheese: ").append(extraCheese).append("\n");
        pizzaDetails.append("Total Price: $").append(calculatePrice()).append("\n");

        return pizzaDetails.toString();
    }
}

/**
 * Represents a specialty pizza with customizable options.
 *
 * <p>This class extends the Pizza class and provides additional functionality for
 * calculating the price and generating a string representation of the pizza details.
 *
 * @author Seifeldeen Mohamed
 */
//public class SpecialityPizza extends Pizza {
//
//
//    /**
//     * Constructs a new SpecialityPizza with the specified parameters.
//     *
//     * @param pizzaType     The type of the pizza (e.g., Deluxe).
//     * @param size          The size of the pizza (SMALL, MEDIUM, LARGE).
//     * @param sauce         The sauce of the pizza.
//     * @param extraSauce    Indicates whether extra sauce is added.
//     * @param extraCheese   Indicates whether extra cheese is added.
//     */
//    public SpecialityPizza() {
//        super();
//    }
//
//    @Override
//    public double calculatePrice() {
//        double basePrice = calculateBasePrice();
//        double sizePrice = calculateSizePrice();
//        double extraSauceAndCheesePrice = calculateExtraSauceAndCheesePrice();
//
//        return basePrice + sizePrice + extraSauceAndCheesePrice;
//    }
//
//    private double calculateBasePrice() {
//        return 0.0;
//    }
//
//    /**
//     * Calculates the total price of the pizza based on its attributes.
//     *
//     * @return The total price of the pizza.
//     */
//    private double calculateSizePrice() {
//        return switch (size) {
//            case SMALL -> 14.99;
//            case MEDIUM -> 16.99;
//            case LARGE -> 20.99;
//        };
//    }
//
//    /**
//     * Calculates the total price after adding extra cheese or not.
//     *
//     * @return The total price of the pizza.
//     */
//    private double calculateExtraSauceAndCheesePrice() {
//        double extraSaucePrice = extraSauce ? 1.0 : 0.0;
//        double extraCheesePrice = extraCheese ? 1.0 : 0.0;
//
//        return extraSaucePrice + extraCheesePrice;
//    }
//
//    /**
//     * Retrieves the type of the pizza.
//     *
//     * @return The pizza type.
//     */
//    public String getPizzaType() {
//        return pizzaType;
//    }
//
//    /**
//     * Generates a string representation of the pizza details.
//     *
//     * @return A string containing the pizza details.
//     */
//    public String toString() {
//        StringBuilder pizzaDetails = new StringBuilder();
//        pizzaDetails.append("Pizza Type: ").append(pizzaType).append("\n");
//        pizzaDetails.append("Size: ").append(size).append("\n");
//        pizzaDetails.append("Sauce: ").append(sauce).append("\n");
//        pizzaDetails.append("Extra Sauce: ").append(extraSauce).append("\n");
//        pizzaDetails.append("Extra Cheese: ").append(extraCheese).append("\n");
//        pizzaDetails.append("Total Price: $").append(calculatePrice()).append("\n");
//
//        return pizzaDetails.toString();
//    }
//
//}
