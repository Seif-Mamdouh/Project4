package com.example.project4.RUpizza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Order {
    private static Order pizzaOrder = new Order();  // Singleton instance
    private ObservableList<Object> pizzas;
    public static final double TAX_RATE = 0.06625;
    private static int orderIDCounter = 1;
    private int orderID;

    public Order() {
        this.pizzas = FXCollections.observableArrayList();
        this.orderID = orderIDCounter++;
    }

    public static Order getPizzaOrder() {
        return pizzaOrder;
    }

    public boolean addPizza(SpecialityPizza pizza) {
        return pizzas.add(pizza);
    }

    public ObservableList<Object> getPizzas() {
        return pizzas;
    }

    public int getOrderID() {
        return orderID;
    }

//    private int calculateSubTotal() {
//        calculatePrice();
//    }
//
//    private int calculateSalesTax() {
//        return calculateSubTotal() * TAX_RATE;
//    }
//
//    public int calculateTotal(){
//        return calculateSalesTax() + calculateSubTotal();
//    }


//    @Override
//    public String toString()
//    {
//        return "Order " + this.orderID + " Total: $" + String.format("%.2f", this.calculateTotal());
//    }


}