package com.example.project4.model;

/**
 * Class for modeling Donut Hole Objects.
 *
 * @author
 */
public class DonutHole extends Donut{
    /**
     * Field for price of a Donut Hole.
     */
    public static final double PRICE = 0.39;

    /**
     * Constructor to create a DonutHole object.
     */
    public DonutHole(Flavor flavor, int Q)
    {
        setFlavor(flavor);
        setQuantity(Q);
    }

    /**
     * Overrides itemPrice to return the price of one donut hole.
     */
    @Override
    public double itemPrice() {
        return PRICE;
    }

    /**
     * Overrides toString to include Donut Hole information.
     */
    @Override
    public String toString()
    {
        return super.toString() + getFlavor().name() + " Donut Hole";
    }
}
