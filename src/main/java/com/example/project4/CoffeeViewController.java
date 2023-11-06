package com.example.project4;

import com.example.project4.model.Coffee;
import com.example.project4.model.AddIn;
import com.example.project4.model.Order;
import com.example.project4.model.Size;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;

/**
 * Class to handle interactions with the order coffee window. Includes handling
 * order price and adding to order.
 *
 * @author
 */
public class CoffeeViewController {

    @FXML
    private ComboBox<Size> sizeComboBox;
    @FXML
    private ComboBox<Integer> quantityComboBox;
    @FXML
    private CheckBox whippedCreamCheck;
    @FXML
    private CheckBox caramelCheck;
    @FXML
    private CheckBox creamCheck;
    @FXML
    private CheckBox syrupCheck;
    @FXML
    private CheckBox milkCheck;
    @FXML
    private Label coffeeSubTotalLabel;

    /**
     * Initializes combo boxes, and listeners.
     */
    public void initialize() {
        coffeeSubTotalLabel.setText("$1.69");

        sizeComboBox.getItems().removeAll(sizeComboBox.getItems());
        sizeComboBox.getItems().addAll(Size.values());
        sizeComboBox.getSelectionModel().selectFirst();

        quantityComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        quantityComboBox.getSelectionModel().selectFirst();

        creamCheck.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            double oldTotal = Double.parseDouble(coffeeSubTotalLabel.getText().substring(1));
            double Q = quantityComboBox.getSelectionModel().getSelectedItem();

            double newTotal = isSelected ? oldTotal + Q*AddIn.COST : oldTotal - Q*AddIn.COST;
            coffeeSubTotalLabel.setText("$" + String.format("%.2f", newTotal));
        });

        whippedCreamCheck.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            double oldTotal = Double.parseDouble(coffeeSubTotalLabel.getText().substring(1));
            double Q = quantityComboBox.getSelectionModel().getSelectedItem();

            double newTotal = isSelected ? oldTotal + Q*AddIn.COST : oldTotal - Q*AddIn.COST;
            coffeeSubTotalLabel.setText("$" + String.format("%.2f", newTotal));
        });

        milkCheck.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            double oldTotal = Double.parseDouble(coffeeSubTotalLabel.getText().substring(1));
            double Q = quantityComboBox.getSelectionModel().getSelectedItem();

            double newTotal = isSelected ? oldTotal + Q*AddIn.COST : oldTotal - Q*AddIn.COST;
            coffeeSubTotalLabel.setText("$" + String.format("%.2f", newTotal));
        });

        syrupCheck.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            double oldTotal = Double.parseDouble(coffeeSubTotalLabel.getText().substring(1));
            double Q = quantityComboBox.getSelectionModel().getSelectedItem();

            double newTotal = isSelected ? oldTotal + Q*AddIn.COST : oldTotal - Q*AddIn.COST;
            coffeeSubTotalLabel.setText("$" + String.format("%.2f", newTotal));
        });

        caramelCheck.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            double oldTotal = Double.parseDouble(coffeeSubTotalLabel.getText().substring(1));
            double Q = quantityComboBox.getSelectionModel().getSelectedItem();

            double newTotal = isSelected ? oldTotal + Q*AddIn.COST : oldTotal - Q*AddIn.COST;
            coffeeSubTotalLabel.setText("$" + String.format("%.2f", newTotal));
        });

        sizeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldSize, newSize) -> {
            double Q = quantityComboBox.getSelectionModel().getSelectedItem();
            double oldTotal = Double.parseDouble(coffeeSubTotalLabel.getText().substring(1));

            double newTotal = oldTotal - Q*oldSize.getPrice() + Q*newSize.getPrice();
            coffeeSubTotalLabel.setText("$" + String.format("%.2f", newTotal));
        });

        quantityComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldQ, newQ) -> {
            double oldTotal = Double.parseDouble(coffeeSubTotalLabel.getText().substring(1));
            double unitPrice = oldTotal / oldQ;
            double newTotal = unitPrice * newQ;
            coffeeSubTotalLabel.setText("$" + String.format("%.2f", newTotal));
        });
    }

    /**
     * Handles clicking add to order button. Adds to current order instance.
     */
    @FXML
    private void addToOrderButtonClicked(ActionEvent actionEvent) {
        Size size = sizeComboBox.getSelectionModel().getSelectedItem();
        ArrayList<AddIn> addins = new ArrayList<>();
        int Q = quantityComboBox.getSelectionModel().getSelectedItem();
        if (creamCheck.isSelected()) addins.add(AddIn.CREAM);
        if (whippedCreamCheck.isSelected()) addins.add(AddIn.WHIPPED_CREAM);
        if (syrupCheck.isSelected()) addins.add(AddIn.SYRUP);
        if (milkCheck.isSelected()) addins.add(AddIn.MILK);
        if (caramelCheck.isSelected()) addins.add(AddIn.CARAMEL);
        Coffee coffee = new Coffee(size, addins, Q);

        Order.getInstance().add(coffee);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Confirmation");
        alert.setHeaderText("Your coffee order has been added.");
        alert.showAndWait();
    }
}
