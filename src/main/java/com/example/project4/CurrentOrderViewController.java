package com.example.project4;


import com.example.project4.RUpizza.BuildYourOwnPizza;
import com.example.project4.RUpizza.Order;
import com.example.project4.RUpizza.Pizza;
import com.example.project4.RUpizza.SpecialityPizza;
import com.example.project4.RUpizza.StoreOrders;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.util.ArrayList;
import java.util.List;



/**
 * Controller class for handling interactions with the current order view window.
 * Manages the display and manipulation of the current order, including adding and removing items.
 * This class is responsible for initializing the view components and handling user actions.
 *
 * @author Seifeldeen Mohamed
 */
public class CurrentOrderViewController {

    @FXML
    private ListView<Object> orderView;
    @FXML
    private ComboBox <Integer> pizzaIDComboBox;
    @FXML
    private Button removeItemButton ;
    @FXML
    private Button placeOrderButton;
    @FXML
    private Label currentOrderSubtotalLabel;
    @FXML
    private Label currentOrderSalesTaxLabel;
    @FXML
    private Label currentOrderTotalLabel;

    /**
     * Initializes the controller. Sets up event listeners, initializes UI components,
     * and populates the view with the current order information.
     */
    public void initialize() {

        // Set up the ListView for displaying the current order
        orderView.setItems(Order.getPizzaOrder().getPizzas());
        orderView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        Order pizzaOrder = Order.getPizzaOrder();

        // Set up an event listener for the ComboBox
        pizzaIDComboBox.setOnAction(event -> filterListViewByPizzaId(pizzaOrder));

        // Set initial selection in the ComboBox
        pizzaIDComboBox.getSelectionModel().selectFirst();

        // Initialize the ListView
        orderView.setItems(pizzaOrder.getPizzas());
        orderView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Populate the ComboBox with Pizza IDs
        pizzaIDComboBox.getItems().addAll(getPizzaIds(pizzaOrder));

        updateListViewAndLabels(pizzaOrder);

        removeItemButton.setOnAction(event -> removeSelectedPizza());
        placeOrderButton.setOnAction(event -> placeOrder());

    }

    private void placeOrder() {
        if(Order.getPizzaOrder().getPizzas().isEmpty()) {
            showErrorAlert("Error", "Order empty.");
            return;
        }
        
        if (StoreOrders.getInstance().add(Order.getPizzaOrder())) {
            showSuccessAlert("Order placed", "The order has been placed and added to store orders.");
            Order.getPizzaOrder().resetOrder();
            updateListViewAndLabels(Order.getPizzaOrder());
            placeOrderButton.setDisable(true);
            removeItemButton.setDisable(true);
        } else {
            showErrorAlert("Error", "Failed to place order.");
        }

    }

    /**
     * Removes the selected pizza from the current order and updates the view.
     * Displays an alert to inform the user about the removal.
     */
    private void removeSelectedPizza() {
        if (Order.getPizzaOrder().getPizzas().isEmpty()) {
            showErrorAlert("Error", "Order empty.");
            return;
        }

        int selectedPizzaId = pizzaIDComboBox.getValue();

        // Get the selected pizza from the order
        Pizza selectedPizza = null;
        for (Object pizzaObject : Order.getPizzaOrder().getPizzas()) {
            if (pizzaObject instanceof SpecialityPizza) {
                SpecialityPizza pizza = (SpecialityPizza) pizzaObject;
                if (pizza.getPizzaID() == selectedPizzaId) {
                    selectedPizza = pizza;
                    break;
                }
            }
            if (pizzaObject instanceof BuildYourOwnPizza) {
                BuildYourOwnPizza pizza = (BuildYourOwnPizza) pizzaObject;
                if (pizza.getPizzaID() == selectedPizzaId) {
                    selectedPizza = pizza;
                    break;
                }
            }
        }

        // Remove the selected pizza from the order
        if (selectedPizza != null) {
            Order.getPizzaOrder().getPizzas().remove(selectedPizza);
            updateListViewAndLabels(Order.getPizzaOrder());
            updateComboBox();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Removed");
            alert.setHeaderText("You've removed an item from your order.");
            alert.showAndWait();
        }
    }

    private void updateComboBox() {
        pizzaIDComboBox.getItems().clear();
        pizzaIDComboBox.getItems().addAll(getPizzaIds(Order.getPizzaOrder()));
        pizzaIDComboBox.getSelectionModel().selectFirst();
    }

    private List<Integer> getPizzaIds(Order pizzaOrder) {
        List<Integer> pizzaIds = new ArrayList<>();
        for (Object pizzaObject : pizzaOrder.getPizzas()) {
            if (pizzaObject instanceof SpecialityPizza) {
                pizzaIds.add(((SpecialityPizza) pizzaObject).getPizzaID());
            }
            if (pizzaObject instanceof BuildYourOwnPizza) {
                pizzaIds.add(((BuildYourOwnPizza) pizzaObject).getPizzaID());
            }
        }
        return pizzaIds;
    }

    /**
     * Filters the ListView by the selected Pizza ID and updates the view accordingly.
     *
     * @param pizzaOrder The current order.
     */
    private void filterListViewByPizzaId(Order pizzaOrder) {
        Integer selectedPizzaId = pizzaIDComboBox.getValue();

        // Filter the ListView based on the selected Pizza ID
        ObservableList<Object> filteredPizzas = FXCollections.observableArrayList();
        for (Object pizzaObject : pizzaOrder.getPizzas()) {
            if (pizzaObject instanceof SpecialityPizza) {
                if (((SpecialityPizza) pizzaObject).getPizzaID().equals(selectedPizzaId)) {
                    filteredPizzas.add(pizzaObject);
                }
            }
            if (pizzaObject instanceof BuildYourOwnPizza) {
                if (((BuildYourOwnPizza) pizzaObject).getPizzaID().equals(selectedPizzaId)) {
                    filteredPizzas.add(pizzaObject);
                }
            }
        }

        // Update the ListView with the filtered pizzas
        orderView.setItems(filteredPizzas);
        updateLabels(filteredPizzas);
    }

    /**
     * Updates the labels displaying subtotal, sales tax, and total based on the provided list of pizzas.
     *
     * @param pizzas The list of pizzas for which to calculate and display the labels.
     */
    private void updateLabels(ObservableList<Object> pizzas) {
        double subtotal = 0;
        double salesTax = 0;
        double total = 0;

        for (Object pizzaObject : pizzas) {
            if (pizzaObject instanceof SpecialityPizza) {
                SpecialityPizza pizza = (SpecialityPizza) pizzaObject;
                subtotal += pizza.calculatePrice();
                salesTax += pizza.calculateTax();
                total += pizza.total();
            }
            if (pizzaObject instanceof BuildYourOwnPizza) {
                BuildYourOwnPizza pizza = (BuildYourOwnPizza) pizzaObject;
                subtotal += pizza.calculatePrice();
                salesTax += pizza.calculateTax();
                total += pizza.total();
            }
        }

        currentOrderSubtotalLabel.setText(String.format("Subtotal: $%.2f", subtotal));
        currentOrderSalesTaxLabel.setText(String.format("Sales Tax: $%.2f", salesTax));
        currentOrderTotalLabel.setText(String.format("Total: $%.2f", total));
    }

    /**
     * Updates the ListView and labels based on the provided order.
     *
     * @param pizzaOrder The order to be displayed in the view.
     */
    private void updateListViewAndLabels(Order pizzaOrder) {
        orderView.setItems(pizzaOrder.getPizzas());
        orderView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        updateLabels(pizzaOrder.getPizzas());
    }

    /**
     * Displays a success alert with the given title and content text.
     *
     * @param title       The title of the success alert.
     * @param contentText The content text of the success alert.
     */
    private void showSuccessAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    /**
     * Displays an error alert with the given title and content text.
     *
     * @param title       The title of the error alert.
     * @param contentText The content text of the error alert.
     */
    private void showErrorAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

};


//    /**
//     * Initializes all the listeners and main List View.
//     */
//    public void initialize() {
//        orderView.setItems(Order.getInstance().getOrderItems());
//        orderView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//
//        currentOrderSubtotalLabel.setText("Sub-Total $" + String.format("%.2f", Order.getInstance().calculateSubTotal()));
//        currentOrderSalesTaxLabel.setText("Sales Tax $" + String.format("%.2f", Order.getInstance().calculateSalesTax()));
//        currentOrderTotalLabel.setText("Total $" + String.format("%.2f", Order.getInstance().calculateTotal()));
//
//        if (orderView.getSelectionModel().getSelectedItem() == null) removeItemButton.setDisable(true);
//        orderView.getSelectionModel().selectedItemProperty().addListener((obs, prev, curr) -> {
//            removeItemButton.setDisable(curr == null);
//        });
//
//        if (orderView.getItems().isEmpty()) placeOrderButton.setDisable(true);
//        orderView.getItems().addListener((ListChangeListener<? super MenuItem>) c -> {
//            placeOrderButton.setDisable(orderView.getItems().isEmpty());
//            currentOrderSubtotalLabel.setText("Sub-Total $" + String.format("%.2f", Order.getInstance().calculateSubTotal()));
//            currentOrderSalesTaxLabel.setText("Sales Tax $" + String.format("%.2f", Order.getInstance().calculateSalesTax()));
//            currentOrderTotalLabel.setText("Total $" + String.format("%.2f", Order.getInstance().calculateTotal()));
//        });
//    }
//
//    /**
//     * Handles clicking of remove item button.
//     */
//    @FXML
//    private void removeItemButtonClicked(ActionEvent actionEvent) {
//        Order.getInstance().remove(orderView.getSelectionModel().getSelectedItem());
//
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Order Removed");
//        alert.setHeaderText("You've removed an item from your order.");
//        alert.showAndWait();
//    }
//
//    /**
//     * Handles clicking of place order button.
//     */
//    @FXML
//    private void placeOrderButtonClicked(ActionEvent actionEvent) {
//        StoreOrders.getInstance().add(Order.getInstance());
//        Order.getInstance().resetOrder();
//        initialize();
//
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Order Confirmation");
//        alert.setHeaderText("Your order has been added to the store's orders.");
//        alert.showAndWait();
//    }
