package com.example.project4.RUpizza;

/**
 * Represents a specialty pizza in the pizza ordering system, extending the base Pizza class.
 * Manages details such as pizza type, size, and additional toppings like extra sauce and cheese.
 * Provides methods to calculate the total price, tax, and display pizza details.
 * Implements the setOrderID method to assign a unique order ID to each specialty pizza.
 *
 * @author Seifeldeen Mohamed
 */
public class SpecialityPizza extends Pizza {

    private PizzaType pizzaType;
    public static final double TAX_RATE = 0.06625;
    private int orderID;

    /**
     * Constructs a SpecialityPizza with the specified parameters.
     *
     * @param pizzaType   The type of specialty pizza.
     * @param size        The size of the pizza (small, medium, large).
     * @param extraSauce  Indicates whether extra sauce is added.
     * @param extraCheese Indicates whether extra cheese is added.
     */
    public SpecialityPizza(PizzaType pizzaType, Size size, boolean extraSauce, boolean extraCheese) {
        this.pizzaType = pizzaType;
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
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
     * Calculates the total price of the specialty pizza.
     *
     * @return The total price of the pizza.
     */
    @Override
    public double calculatePrice() {
        double basePrice = calculateBasePrice();
        double sizePrice = calculateSizePrice();
        double extraSauceAndCheesePrice = calculateExtraSauceAndCheesePrice();

        return basePrice + sizePrice + extraSauceAndCheesePrice;
    }
    /**
     * Initializes the base prices for each pizza type
     *
     * @return The total price of the pizza.
     */
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

    /**
     * Returns a string representation of the specialty pizza, including order details.
     *
     * @return A string representation of the pizza.
     */

    @Override
    public String toString() {
        StringBuilder pizzaDetails = new StringBuilder();
        pizzaDetails.append("Order ID: ").append(orderID).append("\n");
        pizzaDetails.append("Pizza Type: ").append(pizzaType).append("\n");
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

        pizzaDetails.append("Total Price: $").append(calculatePrice()).append("\n");
        pizzaDetails.append("Tax: $").append(calculateTax()).append("\n");
        pizzaDetails.append("Total: $").append(total()).append("\n");

        return pizzaDetails.toString();
    }



}

