package com.example.project4;

import com.example.project4.model.MenuItem;
import com.example.project4.model.Order;
import com.example.project4.model.StoreOrders;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert;

/**
 * Class to handle interactions with current order view window.
 *
 * @author
 */
public class CurrentOrderViewController {

    @FXML
    private ListView<MenuItem> orderView;
    @FXML
    private Button removeItemButton;
    @FXML
    private Button placeOrderButton;
    @FXML
    private Label currentOrderSubtotalLabel;
    @FXML
    private Label currentOrderSalesTaxLabel;
    @FXML
    private Label currentOrderTotalLabel;

    /**
     * Initializes all the listeners and main List View.
     */
    public void initialize() {
        orderView.setItems(Order.getInstance().getOrderItems());
        orderView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        currentOrderSubtotalLabel.setText("Sub-Total $" + String.format("%.2f", Order.getInstance().calculateSubTotal()));
        currentOrderSalesTaxLabel.setText("Sales Tax $" + String.format("%.2f", Order.getInstance().calculateSalesTax()));
        currentOrderTotalLabel.setText("Total $" + String.format("%.2f", Order.getInstance().calculateTotal()));

        if (orderView.getSelectionModel().getSelectedItem() == null) removeItemButton.setDisable(true);
        orderView.getSelectionModel().selectedItemProperty().addListener((obs, prev, curr) -> {
            removeItemButton.setDisable(curr == null);
        });

        if (orderView.getItems().isEmpty()) placeOrderButton.setDisable(true);
        orderView.getItems().addListener((ListChangeListener<? super MenuItem>) c -> {
            placeOrderButton.setDisable(orderView.getItems().isEmpty());
            currentOrderSubtotalLabel.setText("Sub-Total $" + String.format("%.2f", Order.getInstance().calculateSubTotal()));
            currentOrderSalesTaxLabel.setText("Sales Tax $" + String.format("%.2f", Order.getInstance().calculateSalesTax()));
            currentOrderTotalLabel.setText("Total $" + String.format("%.2f", Order.getInstance().calculateTotal()));
        });
    }

    /**
     * Handles clicking of remove item button.
     */
    @FXML
    private void removeItemButtonClicked(ActionEvent actionEvent) {
        Order.getInstance().remove(orderView.getSelectionModel().getSelectedItem());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Removed");
        alert.setHeaderText("You've removed an item from your order.");
        alert.showAndWait();
    }

    /**
     * Handles clicking of place order button.
     */
    @FXML
    private void placeOrderButtonClicked(ActionEvent actionEvent) {
        StoreOrders.getInstance().add(Order.getInstance());
        Order.getInstance().resetOrder();
        initialize();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Confirmation");
        alert.setHeaderText("Your order has been added to the store's orders.");
        alert.showAndWait();
    }
}
