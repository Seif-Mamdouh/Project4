package com.example.project4.model;

import java.util.ArrayList;

/**
 * Class for modeling Coffee item at Cafe.
 *
 * @author
 */
public class Coffee extends MenuItem implements Customizable {
    /**
     * Field for size of the Coffee based on the Size enum class.
     */
    private Size size;

    /**
     * Field for storing all the add ins for the instance of the coffee.
     */
    private ArrayList<AddIn> addInList;

    /**
     * Constructor to create instance of Coffee
     * @param size size of coffee
     * @param addins addins for the coffee
     * @param Q quantity of the item
     */
    public Coffee (Size size, ArrayList<AddIn> addins, int Q) {
        this.size = size;
        this.addInList = addins;
        setQuantity(Q);
    }

    /**
     * Overrides add method from Customizable, to allow adding AddIn's.
     */
    @Override
    public boolean add(Object obj) {
        if (!(obj instanceof AddIn)) {
            return false;
        }

        AddIn addin = (AddIn) obj;

        addInList.add(addin);
        return true;
    }

    /**
     * Overrides remove method from Customizable, to allow removing AddIn's.
     */
    @Override
    public boolean remove(Object obj) {
        if (!(obj instanceof AddIn)) {
            return false;
        }

        AddIn addin = (AddIn) obj;

        addInList.remove(addin);
        return true;
    }

    /**
     * Overrides itemPrice method from MenuItem to calculate the price based on
     * the addins and size.
     */
    @Override
    public double itemPrice() {
        double price = 0;
        price += size.getPrice();

        for (AddIn a : addInList) { price += AddIn.COST; }

        return price;
    }

    /**
     * Overrides toString method.
     * @return Number of coffees, the unit price, size and addins for coffee.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (AddIn a : addInList) {
            sb.append(a.toString());
            sb.append(", ");
        }

        String addIns = sb.toString();

        if (addIns.isEmpty()) {
            return super.toString() + size + " Black Coffee";
        } else {
            return super.toString() + size + " Coffee " +
                    "with " + addIns;
        }
    }
}
