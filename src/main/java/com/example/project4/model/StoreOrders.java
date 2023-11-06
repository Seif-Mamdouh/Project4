package com.example.project4.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class for modeling and holding all the store orders.
 *
 * @author
 */
public class StoreOrders implements Customizable {
    /**
     * Field for all the store orders.
     */
    private final static StoreOrders instance = new StoreOrders();

    /**
     * Method to get instance of store orders.
     */
    public static StoreOrders getInstance() {
        return instance;
    }

    /**
     * Field for list of orders in the store right now.
     */
    private ObservableList<Order> orders = FXCollections.observableArrayList();

    /**
     * Overriding add method from Customizable interface to allow adding instances
     * of Order.
     */
    @Override
    public boolean add(Object obj) {
        if (!(obj instanceof Order)) {
            return false;
        }

        Order O = (Order) obj;
        orders.add(O);
        return true;
    }

    /**
     * Overriding remove method from Customizable interface to allow removing instances
     * of Order.
     */
    @Override
    public boolean remove(Object obj) {
        if (!(obj instanceof Order)) {
            return false;
        }

        Order O = (Order) obj;
        orders.remove(O);
        return true;
    }

    /**
     * Gets orders in store right now.
     */
    public ObservableList<Order> getOrders() {
        return orders;
    }

    /**
     * Writes orders to file parameter.
     * @param file pointer to file to write to
     */
    public void exportTo(File file) {
        FileWriter exporter = null;
        try {
            exporter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(orders.isEmpty())
        {
            try {
                exporter.write("No Orders Currently In System");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            for(Order order:orders)
            {

                try {
                    String toWrite = order.toString() + "\n";
                    exporter.write(toWrite);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for(MenuItem m : order.getOrderItems())
                {
                    try {
                        String toWriteItem = "\t" + m.toString() + "\n";
                        exporter.write(toWriteItem);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
            exporter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
