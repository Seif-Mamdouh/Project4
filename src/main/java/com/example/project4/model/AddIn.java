package com.example.project4.model;

/**
 * Enum class to model different add ins for coffees in Cafe.
 *
 * @author
 */
public enum AddIn {
    CREAM("Cream"), SYRUP("Syrup"), MILK("Milk"), CARAMEL("Caramel"), WHIPPED_CREAM("Whipped Cream");

    /**
     * Constant defining the cost of each add in.
     */
    public static final double COST = 0.30;

    /**
     * For storing the human-readable name of the add in when displaying in the GUI.
     */
    private final String addinName;

    /**
     * Constructor that sets the human-readable name for the add in.
     * @param addinName human-readable add in name.
     */
    AddIn(String addinName) {
        this.addinName = addinName;
    }

    /**
     * Overriding toString method.
     * @return human-readable addin name.
     */
    @Override
    public String toString() {
        return addinName;
    }
}
