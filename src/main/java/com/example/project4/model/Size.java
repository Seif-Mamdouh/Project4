package com.example.project4.model;

/**
 * Enum class for the different sizes available for Coffee instances.
 *
 * @author
 */
public enum Size {
    SHORT (1.69, "Short"),
    TALL (2.09, "Tall"),
    GRANDE(2.49, "Grande"),
    VENTI (2.89, "Venti");

    /**
     * Field to track price related to size of Coffee.
     */
    private final double price;

    /**
     * Field to track human readable size name for each size.
     */
    private final String sizeName;

    /**
     * Constructor to hold price and human readable size name for each enum value.
     */
    Size(double price, String sizeName) {
        this.price = price;
        this.sizeName = sizeName;
    }

    /**
     * Overriding toString method.
     */
    @Override
    public String toString() {
        return sizeName;
    }

    /**
     * Getting price associated with enum value.
     */
    public double getPrice() {
        return this.price;
    }
}
