package com.example.project4.RUpizza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Order {
    private static Order pizzaOrder = new Order();  // Singleton instance
    private ObservableList<Object> pizzas;
    private static int orderIDCounter = 1;

    private Order() {
        this.pizzas = FXCollections.observableArrayList();
    }

    public static Order getPizzaOrder() {
        return pizzaOrder;
    }

    public boolean addPizza(Pizza pizza) {
        if (pizza instanceof SpecialityPizza) {
            ((SpecialityPizza) pizza).setOrderID(orderIDCounter++);
        }
        return pizzas.add(pizza);
    }

    public ObservableList<Object> getPizzas() {
        return pizzas;
    }

    public int getOrderIDCounter() {
        return orderIDCounter;
    }


}