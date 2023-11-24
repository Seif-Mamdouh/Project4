package com.example.project4.RUpizza;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuildYourOwnPizzaTest {

    @Test
    void testCase1() {
        List<String> selectedToppings = new ArrayList<String>();
        selectedToppings.add("Random1");
        selectedToppings.add("Random2");
        selectedToppings.add("Random3");
        selectedToppings.add("SAUCE");
        Pizza buildYourOwnPizza = new BuildYourOwnPizza(Size.SMALL, false, false, selectedToppings);
        assertEquals(8.99, buildYourOwnPizza.calculatePrice());
    }

    @Test
    void testCase2() {
        List<String> selectedToppings = new ArrayList<String>();
        selectedToppings.add("Random1");
        selectedToppings.add("Random2");
        selectedToppings.add("Random3");
        selectedToppings.add("Random4");
        selectedToppings.add("Random5");
        selectedToppings.add("Random6");
        selectedToppings.add("SAUCE");
        Pizza buildYourOwnPizza = new BuildYourOwnPizza(Size.MEDIUM, true, true, selectedToppings);
        assertEquals(17.46, buildYourOwnPizza.calculatePrice());
    }

    @Test
    void testCase3() {
        List<String> selectedToppings = new ArrayList<String>();
        Pizza buildYourOwnPizza = new BuildYourOwnPizza(Size.LARGE, false, true, selectedToppings);
        assertEquals(13.99, buildYourOwnPizza.calculatePrice());
    }

    @Test
    void testCase4() {
        List<String> selectedToppings = new ArrayList<String>();
        selectedToppings.add("Random1");
        selectedToppings.add("Random2");
        selectedToppings.add("Random3");
        selectedToppings.add("Random4");
        selectedToppings.add("Random5");
        selectedToppings.add("SAUCE");
        Pizza buildYourOwnPizza = new BuildYourOwnPizza(Size.SMALL, true, false, selectedToppings);
        assertEquals(12.97, buildYourOwnPizza.calculatePrice());
    }

    @Test
    void testCase5() {
        List<String> selectedToppings = new ArrayList<String>();
        selectedToppings.add("Random1");
        selectedToppings.add("Random2");
        selectedToppings.add("Random3");
        selectedToppings.add("Random4");
        selectedToppings.add("Random5");
        selectedToppings.add("Random6");
        selectedToppings.add("Random7");
        selectedToppings.add("SAUCE");
        Pizza buildYourOwnPizza = new BuildYourOwnPizza(Size.MEDIUM, false, false, selectedToppings);
        assertEquals(16.95, buildYourOwnPizza.calculatePrice());
    }

}