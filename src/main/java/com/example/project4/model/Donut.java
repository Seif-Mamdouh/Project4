package com.example.project4.model;

/**
 * Abstract class to encapsulate Donut methods and fields.
 *
 * @author
 */
public abstract class Donut extends MenuItem{
    /**
     * Field for the flavor of the donut.
     */
    private Flavor flavor;

    /**
     * Sets the flavor of the donut.
     * @param flavor Flavor enum type
     */
    public void setFlavor(Flavor flavor)
    {
        this.flavor = flavor;
    }

    /**
     * Gets the flavor of the donut.
     * @return Flavor enum of flavor
     */
    public Flavor getFlavor() {
        return flavor;
    }
}
