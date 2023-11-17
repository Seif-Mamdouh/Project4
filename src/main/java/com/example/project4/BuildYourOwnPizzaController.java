package com.example.project4;

import com.example.project4.RUpizza.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
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
    private ListView<String> toppingsListView;
    @FXML
    private ObservableList<String> toppings;
    @FXML
    private Label pizzaSubTotalLabel;
    @FXML
    private ImageView pizzaView;
    @FXML
    private CheckBox sauceSelection;

    public void initialize() {
        pizzaSubTotalLabel.setText("$0.00");

        toppings = FXCollections.observableArrayList();
        toppingsListView.setItems(toppings);

        List<String> availableToppings = Arrays.asList("Pepperoni", "Mushrooms", "Green peppers", "Onions", "Sausage", "Black olives", "Bacon", "Pineapple", "Fresh tomatoes", "Spinach", "Jalapenos", "Feta cheese", "Blue cheese");
        toppings.addAll(availableToppings);

        toppingsListView.setItems(toppings);
        toppingsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

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
    private void changePicture(String name)
    {
        FileInputStream path = null;
        try {
            path = new FileInputStream( name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image newPic = new Image(path);
        pizzaView.setImage(newPic);
    }

    @FXML
    private void updateCost() {
        double totalCost = calculateCost();

        if (extraSauce.isSelected()) {
            totalCost += 1.0;
        }
        if (extraCheese.isSelected()) {
            totalCost += 1.0;
        }

        pizzaSubTotalLabel.setText(String.format("$%.2f", totalCost));
    }

    private double calculateCost() {
        Size selectedSize = sizeTypeComboBox.getValue();
        int selectedToppingsCount = toppingsListView.getSelectionModel().getSelectedItems().size();

        double basePrice = getBasePrice(selectedSize);
        double toppingsPrice = getToppingsPrice(selectedToppingsCount);

        return basePrice + toppingsPrice;
    }

    private double getBasePrice(Size size) {
        return switch (size) {
            case SMALL -> 8.99;
            case MEDIUM -> 8.99 + 2.0;
            case LARGE -> 8.99 + 4.0;
            default -> 0.0;
        };
    }

    private double getToppingsPrice(int toppingsCount) {
        int additionalToppings = Math.max(0, toppingsCount - 4);
        return additionalToppings * 1.49;
    }

    @FXML
    private void onAddOrderClicked() {
        Size selectedSize = sizeTypeComboBox.getValue();
        boolean isExtraSauce = extraSauce.isSelected();
        boolean isExtraCheese = extraCheese.isSelected();

        List<String> selectedToppings = new ArrayList<>(toppings);
        // Create an instance of BuildYourOwnPizza
        Pizza buildYourOwnPizza = new BuildYourOwnPizza(selectedSize, isExtraSauce, isExtraCheese, selectedToppings);

        // Add the pizza to the order (you might need to modify this part based on your application structure)
        if (Order.getPizzaOrder().addPizza(buildYourOwnPizza)) {
            showSuccessAlert("Pizza Added", "The pizza has been added to the order.");

            System.out.println("Order Details:");
            for (Object pizza : Order.getPizzaOrder().getPizzas()) {
                System.out.println(pizza.toString());
            }
        } else {
            showErrorAlert("Error", "Failed to add the pizza to the order.");
        }
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
