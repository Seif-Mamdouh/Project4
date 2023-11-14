package com.example.project4.RUpizza;


public class SpecialityPizza extends Pizza {

    private PizzaType pizzaType;
    private int pizzaID;
    private static int pizzaCount = 0;

//    private CurrentOrderViewController currentOrderViewController;  // Add this field


    public SpecialityPizza(PizzaType pizzaType, Size size, boolean extraSauce, boolean extraCheese) {
        this.pizzaType = pizzaType;
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
        this.pizzaID = pizzaCount++;
    }

    @Override
    public double calculatePrice() {
        double basePrice = calculateBasePrice();
        double sizePrice = calculateSizePrice();
        double extraSauceAndCheesePrice = calculateExtraSauceAndCheesePrice();

        return basePrice + sizePrice + extraSauceAndCheesePrice;
    }

    private double calculateBasePrice() {
        return switch (pizzaType) {
            case DELUXE -> switch (size) {
                case SMALL, MEDIUM, LARGE -> 14.99;
            };
            case SUPREME -> switch (size) {
                case SMALL, MEDIUM, LARGE -> 15.99;
            };
            case MEATZZA-> switch (size) {
                case SMALL, MEDIUM, LARGE -> 16.99;
            };
            case SEAFOOD -> switch (size) {
                case SMALL, MEDIUM, LARGE -> 17.99;
            };
            case PEPPERONI -> switch (size) {
                case SMALL, MEDIUM, LARGE -> 10.99;
            };
            default -> 0.0;
        };
    }

    private double calculateSizePrice() {
        return switch (size) {
            case SMALL -> 0.0;  // No additional cost for small size
            case MEDIUM -> 2.0; // Additional $2 for medium size
            case LARGE -> 4.0;  // Additional $4 for large size
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
        pizzaDetails.append(pizzaID).append("\n");
        pizzaDetails.append("Pizza Type: ").append(pizzaType).append("\n");
        pizzaDetails.append("Size: ").append(size).append("\n");

        // Check if extraCheese is true and append the corresponding information
        if (extraCheese) {
            pizzaDetails.append("Extra Cheese: yes\n");
        } else {
            pizzaDetails.append("Extra Cheese: no\n");
        }

        // Check if extraSauce is true and append the corresponding information
        if (extraSauce) {
            pizzaDetails.append("Extra Sauce: yes\n");
        } else {
            pizzaDetails.append("Extra Sauce: no\n");
        }

        pizzaDetails.append("Total Price: $").append(calculatePrice()).append("\n");

        return pizzaDetails.toString();
    }

    public Integer getPizzaID() {
        return pizzaID;
    }


//    @Override
//    public String toString() {
//        StringBuilder pizzaDetails = new StringBuilder();
//        pizzaDetails.append(pizzaID).append("\n");
//        pizzaDetails.append(pizzaType).append("\n");
//        pizzaDetails.append(size).append("\n");
//        pizzaDetails.append(extraSauce).append("\n");
//        pizzaDetails.append(extraCheese).append("\n");
//        pizzaDetails.append(calculatePrice()).append("\n");
//
//        return pizzaDetails.toString();
//    }


}

