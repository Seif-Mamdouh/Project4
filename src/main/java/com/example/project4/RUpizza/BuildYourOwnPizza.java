package com.example.project4.RUpizza;

import java.util.ArrayList;

public class BuildYourOwnPizza extends Pizza {
    private static final double BASE_PRICE = 8.99;  // Base price for build your own pizza
    private ArrayList<String> selectedToppings;

    public BuildYourOwnPizza(Size size, boolean extraSauce, boolean extraCheese, ArrayList<String> selectedToppings) {
        this.size = size;
        this.sauce = sauce;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
        this.selectedToppings = selectedToppings;
    }

//    @Override
//    public void setOrderID(int orderID) {
//
//    }

    @Override
    public void setOrderID(int orderID) {

    }

    @Override
    public double calculatePrice() {
        return 0;
    }
}
