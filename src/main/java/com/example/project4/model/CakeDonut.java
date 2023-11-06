package com.example.project4.model;

/**
 * Class for modeling Cake Donut Objects.
 *
 * @author
 */
public class CakeDonut extends Donut{
    /**
     * Field for price of a Cake Donut.
     */
    public static final double PRICE = 1.79;

    /**
     * Constructor to create a CakeDonut object.
     */
    public CakeDonut(Flavor flavor, int Q)
    {
        setFlavor(flavor);
        setQuantity(Q);
    }

    /**
     * Overrides itemPrice to return the price of one cake donut.
     */
    @Override
    public double itemPrice() {
        return PRICE;
    }

    /**
     * Overrides toString to include Cake Donut information.
     */
    @Override
    public String toString()
    {
        return super.toString() + getFlavor().name() + " Cake Donut";
    }
}
