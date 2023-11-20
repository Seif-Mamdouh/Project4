package com.example.project4;

import com.example.project4.RUpizza.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;



import java.io.File;
import java.io.IOException;

/**
 * Class to handle interactions with current order view window.
 *
 * @author
 */
public class StoreOrdersViewController {

    @FXML
    private ListView<Object> orderDetail;
    @FXML
    private ComboBox<Integer> orderView;
    @FXML
    private Button removeItemButton;
    @FXML
    private Button exportOrdersButton;
    @FXML
    private Label currentOrderTotalLabel;

    /**
     * Initializes all the listeners and main List View.
     */
    public void initialize() {
        orderView.getItems().removeAll(orderView.getItems());
        orderView.setItems(StoreOrders.getInstance().getOrders());
        orderView.getSelectionModel().selectFirst();

        if (!orderView.getItems().isEmpty()) {
            Order mI = StoreOrders.getInstance().getMapping().get(orderView.getSelectionModel().getSelectedItem());
            orderDetail.getItems().removeAll(orderDetail.getItems());
            orderDetail.setItems(mI.getPizzas());
            currentOrderTotalLabel.setText(String.format("$%.2f", StoreOrders.getInstance().calculateTotal(mI)));
        }

        if (orderView.getSelectionModel().getSelectedItem() == null) removeItemButton.setDisable(true);
        if (orderView.getSelectionModel().getSelectedItem() == null) exportOrdersButton.setDisable(true);
        exportOrdersButton.setOnAction(this::exportButtonClicked);

        orderView.getSelectionModel().selectedItemProperty().addListener((obs, prev, curr) -> {
            removeItemButton.setDisable(curr == null);
            if(curr != null)
            {
                orderDetail.setItems(StoreOrders.getInstance().getMapping().get(curr).getPizzas());
                currentOrderTotalLabel.setText(String.format("$%.2f", StoreOrders.getInstance().calculateTotal(StoreOrders.getInstance().getMapping().get(curr))));
            }
            else {
                currentOrderTotalLabel.setText("$0.00");
                orderDetail.getItems().removeAll(orderDetail.getItems());
            }
        });

    }

    /**
     * Handles clicking of remove item button.
     */
    @FXML
    private void cancelOrderButtonClicked(ActionEvent actionEvent) {
        StoreOrders.getInstance().remove(orderView.getSelectionModel().getSelectedItem());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Cancellation");
        alert.setHeaderText("You've cancelled a customer order.");
        alert.showAndWait();
    }

    /**
     * Handles clicking of export orders button.
     */
    @FXML
    private void exportButtonClicked(ActionEvent actionEvent) {
        Alert alert;
        if (StoreOrders.getInstance().getOrders().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("The store order is empty.");
        } else {
            File file = new File("src/main/resources/com/example/project4/export.txt");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            file.setWritable(true);
            StoreOrders.getInstance().exportTo(file);

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Orders Exported");
            alert.setHeaderText("You've exported the orders to 'src/main/resources/com/example/project4/export.txt'.");
        }
        alert.showAndWait();
    }

}

