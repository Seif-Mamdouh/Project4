package com.example.project4;

import com.example.project4.RUpizza.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuildYourOwnPizzaController {
    @FXML
    private ComboBox<Size> sizeTypeComboBox;
    @FXML
    private CheckBox extraSauce;
    @FXML
    private CheckBox extraCheese;
    @FXML
    private CheckBox sauceSelection;
    @FXML
    private ListView<String> toppingsListView;
    @FXML
    private ListView<String> selectedToppingsListView;
    @FXML
    private Label pizzaSubTotalLabel;
    @FXML
    private ImageView pizzaView;


    private static final double EXTRA_TOPPING_COST = 1.0;
    private static final int MAX = 7;
    private static final int MIN = 3;

    private static final double SMALL = 8.99;
    private static final double TWO = 2.00;
    private static final double FOUR = 4.00;
    private static final double DEFAULT = 0.00;

    private static final double TOPPINGS_PRICE = 1.49;

    private ObservableList<String> toppings;
    private ObservableList<String> selectedToppings;
    /**
     * Initializes the Build Your Own Pizza controller.
     */
    public void initialize() {
        pizzaSubTotalLabel.setText("$0.00");

        toppings = FXCollections.observableArrayList();
        selectedToppings = FXCollections.observableArrayList();

        toppingsListView.setItems(toppings);
        selectedToppingsListView.setItems(selectedToppings);

        List<String> availableToppings = Arrays.asList("Pepperoni", "Mushrooms", "Green peppers", "Onions", "Sausage", "Black olives", "Bacon", "Pineapple", "Fresh tomatoes", "Spinach", "Jalapenos", "Feta cheese", "Blue cheese");
        toppings.addAll(availableToppings);

        sizeTypeComboBox.getItems().removeAll(sizeTypeComboBox.getItems());
        sizeTypeComboBox.getItems().addAll(Size.values());
        sizeTypeComboBox.getSelectionModel().selectFirst();
        sizeTypeComboBox.setOnAction(event -> updateCost());

        extraSauce.setOnAction(event -> updateCost());
        extraCheese.setOnAction(event -> updateCost());

        changePicture("src/main/resources/com/example/project4/images/deluxe.jpeg");

        toppingsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldTopping, newTopping) -> updateCost());
    }

    /**
     * Updates the image being displayed on the page
     */
    private void changePicture(String name) {
        try {
            FileInputStream path = new FileInputStream(name);
            Image newPic = new Image(path);
            pizzaView.setImage(newPic);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Updates the total cost of the pizza.
     */
    @FXML
    private void updateCost() {
        double totalCost = calculateCost();

        if (extraSauce.isSelected()) {
            totalCost += EXTRA_TOPPING_COST;
        }
        if (extraCheese.isSelected()) {
            totalCost += EXTRA_TOPPING_COST;
        }

        pizzaSubTotalLabel.setText(String.format("$%.2f", totalCost));
    }

    /**
     * Handles the "Add to Order" button click event.
     */
    @FXML
    private void onAddOrderClicked() {
        // Check if the number of toppings in the right box is between 3 and 7
        int selectedToppingsCount = selectedToppingsListView.getItems().size();
        if (selectedToppingsCount < MIN) {
            showErrorAlert("Error", "Select at least 3 toppings.");
            return;
        }

        Size selectedSize = sizeTypeComboBox.getValue();
        boolean isExtraSauce = extraSauce.isSelected();
        boolean isExtraCheese = extraCheese.isSelected();

        List<String> selectedToppings = new ArrayList<>(selectedToppingsListView.getItems());
        selectedToppings.add(sauceSelection.isSelected() ? "Alfredo sauce" : "Tomato sauce");

        // Create a BuildYourOwnPizza instance with the selected toppings
        Pizza buildYourOwnPizza = PizzaMaker.createPizza(
                Pizza.PizzaType.BUILD_YOUR_OWN,
                selectedSize,
                isExtraSauce,
                isExtraCheese
        );

        // Make sure the created pizza is of type BuildYourOwnPizza
        if (buildYourOwnPizza instanceof BuildYourOwnPizza) {
            // Set the selected toppings for the BuildYourOwnPizza instance
            ((BuildYourOwnPizza) buildYourOwnPizza).setToppings(selectedToppings);
        }

        // Add the pizza to the order
        if (Order.getPizzaOrder().addPizza(buildYourOwnPizza)) {
            showSuccessAlert("Pizza Added", "The pizza has been added to the order.");

            System.out.println("Order Details:");
            for (Object pizza : Order.getPizzaOrder().getPizzas()) {
                System.out.println(pizza.toString());
            }
        } else {
            showErrorAlert("Error", "Failed to add the pizza to the order.");
        }
        resetUI();
    }

    /**
     * Resets the user interface to its initial state.
     */
    private void resetUI() {
        extraSauce.setSelected(false);
        extraCheese.setSelected(false);
        sauceSelection.setSelected(false);
        sizeTypeComboBox.getSelectionModel().selectFirst();
        toppings.clear();
        selectedToppings.clear();
        List<String> availableToppings = Arrays.asList("Pepperoni", "Mushrooms", "Green peppers", "Onions", "Sausage", "Black olives", "Bacon", "Pineapple", "Fresh tomatoes", "Spinach", "Jalapenos", "Feta cheese", "Blue cheese");
        toppings.addAll(availableToppings);
        pizzaSubTotalLabel.setText("$0.00");
    }

    /**
     * Handles the "Add Topping" button click event.
     */
    @FXML
    private void onAddToppingClicked() {
        String selectedTopping = toppingsListView.getSelectionModel().getSelectedItem();
        // Check if the number of toppings in the right box is between 3 and 7
        int selectedToppingsCount = selectedToppingsListView.getItems().size();
        if (selectedToppingsCount == MAX) {
            showErrorAlert("Error", "Select maximum of 7 toppings.");
            return;
        }
        if (selectedTopping != null) {
            toppings.remove(selectedTopping);
            selectedToppings.add(selectedTopping);
        }
        updateCost();
    }


    /**
     * Handles the "Remove Topping" button click event.
     */
    @FXML
    private void onRemoveToppingClicked() {
        String selectedTopping = selectedToppingsListView.getSelectionModel().getSelectedItem();
        if (selectedTopping != null) {
            selectedToppings.remove(selectedTopping);
            toppings.add(selectedTopping);
        }
        updateCost();
    }


    /**
     * Calculates the total cost of the pizza.
     *
     * @return The total cost of the pizza.
     */
    private double calculateCost() {
        Size selectedSize = sizeTypeComboBox.getValue();
        int selectedToppingsCount = selectedToppingsListView.getItems().size();

        double basePrice = getBasePrice(selectedSize);
        double toppingsPrice = getToppingsPrice(selectedToppingsCount);

        return basePrice + toppingsPrice;
    }

    /**
     * Gets the base price of the pizza based on its size.
     *
     * @param size The size of the pizza.
     * @return The base price of the pizza.
     */

    private double getBasePrice(Size size) {
        return switch (size) {
            case SMALL -> SMALL;
            case MEDIUM -> SMALL + TWO;
            case LARGE -> SMALL+ FOUR;
            default -> DEFAULT;
        };
    }

    /**
     * Gets the price of the toppings based on the number of selected toppings.
     *
     * @param toppingsCount The number of selected toppings.
     * @return The price of the toppings.
     */
    private double getToppingsPrice(int toppingsCount) {
        int additionalToppings = Math.max(0, toppingsCount - 3);
        return additionalToppings * TOPPINGS_PRICE;
    }

    private void showSuccessAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private void showErrorAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
