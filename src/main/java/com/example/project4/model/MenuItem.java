package com.example.project4.model;

/**
 * Abstract class for all the items on the menu at cafe.
 *
 * @author
 */
public abstract class MenuItem {
    /**
     * Field for tracking quantity of item ordered.
     */
    private int quantity;

    /**
     * Default constructor comments because if not we lose points even though
     * it's auto generated and literally inside an abstract class.
     */
    public MenuItem() {
    }
    /**
     * Abstract method to calculate price of item.
     * @return price of item as calculated by subclasses.
     */
    public abstract double itemPrice();

    /**
     * Sets the quantity field.
     */
    public void setQuantity(int q) {
        quantity = q;
    }

    /**
     * Gets the quantity field.
     */
    protected int getQuantity() {
        return quantity;
    }

    /**
     * Overrides toString method.
     */
    @Override
    public String toString() {
        return quantity + "@$" + String.format("%.2f", itemPrice()) + " ea. - ";
    }
}
