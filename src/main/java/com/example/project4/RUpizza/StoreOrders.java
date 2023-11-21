package com.example.project4.RUpizza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.HashMap;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StoreOrders {
    /**
     * Field for all the store orders.
     */
    private final static StoreOrders instance = new StoreOrders();
    private static int orderIDCounter = 0;

    /**
     * Method to get instance of store orders.
     */
    public static StoreOrders getInstance() {
        return instance;
    }

    /**
     * Field for list of orders in the store right now.
     */
    private ObservableList<Integer> displayOrderView = FXCollections.observableArrayList();

    private HashMap<Integer, Order> mapping = new HashMap<Integer, Order>();

    public HashMap<Integer, Order> getMapping() {
        return mapping;
    }

    public boolean add(Object obj) {
        if (!(obj instanceof Order)) {
            return false;
        }

        Order O = (Order) obj;
        displayOrderView.add(orderIDCounter);
        mapping.put(orderIDCounter++, O);
        return true;
    }

    public boolean remove(Integer obj) {
        if(!mapping.containsKey(obj)) {
            return false;
        }
        displayOrderView.remove(obj);
        mapping.remove(obj);
        return true;
    }

    /**
     * Gets orders numbers in store right now.
     */
    public ObservableList<Integer> getOrders() {
        return displayOrderView;
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
        if(mapping.isEmpty())
        {
            try {
                exporter.write("No Orders Currently In System");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            for(Order order:mapping.values())
            {

                try {
                    String toWrite = "Store ID: " + order.getStoreID() + "\n";
                    exporter.write(toWrite);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for(Object m : order.getPizzas())
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

    public double calculateTotal(Order order) {
        double tot = 0;
        for(Order x : mapping.values()) {
            if(x == order) {
            for(Object t : x.getPizzas()) {
                if (t instanceof SpecialityPizza) {
                    tot+=((SpecialityPizza) t).total();
                }
                if (t instanceof BuildYourOwnPizza) {
                    tot+=((BuildYourOwnPizza) t).total();
                }
            }
        }
        }
        return tot;
    }
}
