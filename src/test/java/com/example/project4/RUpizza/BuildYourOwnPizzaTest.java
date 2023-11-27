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
        Pizza buildYourOwnPizza = PizzaMaker.createPizza(
                Pizza.PizzaType.BUILD_YOUR_OWN,
                Size.SMALL,
                false,
                false
        );
        ((BuildYourOwnPizza) buildYourOwnPizza).setToppings(selectedToppings);
        assertEquals(8.99, ((BuildYourOwnPizza) buildYourOwnPizza).calculatePrice());
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
        Pizza buildYourOwnPizza = PizzaMaker.createPizza(
                Pizza.PizzaType.BUILD_YOUR_OWN,
                Size.MEDIUM,
                true,
                true
        );
        ((BuildYourOwnPizza) buildYourOwnPizza).setToppings(selectedToppings);
        assertEquals(17.46, ((BuildYourOwnPizza) buildYourOwnPizza).calculatePrice());
    }

    @Test
    void testCase3() {
        List<String> selectedToppings = new ArrayList<String>();
        Pizza buildYourOwnPizza = PizzaMaker.createPizza(
                Pizza.PizzaType.BUILD_YOUR_OWN,
                Size.LARGE,
                false,
                true
        );
        ((BuildYourOwnPizza) buildYourOwnPizza).setToppings(selectedToppings);
        assertEquals(13.99, ((BuildYourOwnPizza) buildYourOwnPizza).calculatePrice());
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
        Pizza buildYourOwnPizza = PizzaMaker.createPizza(
                Pizza.PizzaType.BUILD_YOUR_OWN,
                Size.SMALL,
                true,
                false
        );
        ((BuildYourOwnPizza) buildYourOwnPizza).setToppings(selectedToppings);
        assertEquals(12.97, ((BuildYourOwnPizza) buildYourOwnPizza).calculatePrice());
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
        Pizza buildYourOwnPizza = PizzaMaker.createPizza(
                Pizza.PizzaType.BUILD_YOUR_OWN,
                Size.MEDIUM,
                false,
                false
        );
        ((BuildYourOwnPizza) buildYourOwnPizza).setToppings(selectedToppings);
        assertEquals(16.95, ((BuildYourOwnPizza) buildYourOwnPizza).calculatePrice());
    }

}