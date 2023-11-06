package com.example.project4.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class to handle the Order at the Cafe.
 *
 * @author
 */
public class Order implements Customizable {
    /**
     * Constant for the tax percentage.
     */
    public static final double TAX_RATE = 0.06625;
    /**
     * Static field of the current order.
     */
    private static Order instance = new Order();

    /**
     * Returns the current order instance.
     */
    public static Order getInstance() {
        return instance;
    }

    /**
     * Field for generating the unique ID for each order.
     */
    public static int orderIDCounter = 1;

    /**
     * List for all the  items in the order.
     */
    private ObservableList<MenuItem> orderItems;

    /**
     * Field for the orderID for each instance.
     */
    private int orderID;

    /**
     * Default constructor to set the unique ID.
     */
    private Order() {
        orderItems = FXCollections.observableArrayList();
        this.orderID = Order.orderIDCounter++;
    }

    /**
     * Overriding add method from customizable interface to allow adding instances
     * of MenuItem to order.
     */
    @Override
    public boolean add(Object obj) {
        if (!(obj instanceof MenuItem)) {
            return false;
        }

        MenuItem m = (MenuItem) obj;
        orderItems.add(m);
        return true;
    }

    /**
     * Overriding remove method from customizable interface to allow removing instances
     * of MenuItem to order.
     */
    @Override
    public boolean remove(Object obj) {
        if (!(obj instanceof MenuItem)) {
            return false;
        }

        MenuItem m = (MenuItem) obj;
        orderItems.remove(m);
        return true;
    }

    /**
     * Resets the currentOrder instance to a fresh order.
     */
    public void resetOrder() {
        instance = new Order();
    }

    /**
     * Calculates the subtotal for the order.
     * @return subtotal
     */
    public double calculateSubTotal() {
        double subtotal = 0;
        for (MenuItem m : getOrderItems()) {
            subtotal += m.itemPrice() * m.getQuantity();
        }
        return subtotal;
    }

    /**
     * Calculates the sales tax for the order. (6.625%)
     * @return sales tax
     */
    public double calculateSalesTax() {
        return calculateSubTotal() * TAX_RATE;
    }

    /**
     * Calculates the total for the order including taxes.
     * @return total
     */
    public double calculateTotal() {
        return calculateSalesTax() + calculateSubTotal();
    }

    /**
     * Gets the list of items in the order.
     */
    public ObservableList<MenuItem> getOrderItems() {
        return orderItems;
    }

    /**
     * Overrides toString so .
     */
    @Override
    public String toString()
    {
        return "Order " + this.orderID + " Total: $" + String.format("%.2f", this.calculateTotal());
    }
}
