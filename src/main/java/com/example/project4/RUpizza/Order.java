package com.example.project4.RUpizza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Order {
    private static Order pizzaOrder = new Order();  // Singleton instance
    private ObservableList<Pizza> pizzas;
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

    public ObservableList<Pizza> getPizzas() {
        return pizzas;
    }

    public int getOrderID() {
        return orderID;
    }


}
