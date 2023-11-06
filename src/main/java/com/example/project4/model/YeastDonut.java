package com.example.project4.model;

/**
 * Class for modeling Yeast Donut Objects.
 *
 * @author
 */
public class YeastDonut extends Donut {

    /**
     * Field for price of a Yeast Donut.
     */
    public static final double PRICE = 1.59;

    /**
     * Constructor to create a YeastDonut object.
     */
    public YeastDonut(Flavor flavor, int Q)
    {
        setFlavor(flavor);
        setQuantity(Q);
    }

    /**
     * Overrides itemPrice to return the price of one yeast donut.
     */
    @Override
    public double itemPrice() {
        return PRICE;
    }

    /**
     * Overrides toString to include Yeast Donut information.
     */
    @Override
    public String toString()
    {
        return super.toString() + getFlavor().name() + " Yeast Donut";
    }
}
