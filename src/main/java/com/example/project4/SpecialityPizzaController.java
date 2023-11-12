package com.example.project4;

import com.example.project4.RUpizza.Size;
import com.example.project4.RUpizza.Order;
import com.example.project4.RUpizza.Pizza;
import com.example.project4.RUpizza.PizzaMaker;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import javafx.scene.control.CheckBox;


import javafx.collections.ObservableList;
import javafx.scene.control.ListView;


public class SpecialityPizzaController {
    @FXML
    private Order order;
    @FXML
    private ComboBox<String> pizzaTypeComboBox;
    @FXML
    private ComboBox<Size> sizeTypeComboBox;
    @FXML
    private ImageView pizzaView;
    @FXML
    private ListView<String> ingredientsListView;
    @FXML
    private ObservableList<String> ingredients;
    @FXML
    private Label pizzaSubTotalLabel;
    @FXML
    private CheckBox extraSauce;
    @FXML
    private CheckBox extraCheese;




    public void initialize() {
        pizzaSubTotalLabel.setText("$0.00");

        ingredients = FXCollections.observableArrayList();

        pizzaTypeComboBox.getItems().removeAll(pizzaTypeComboBox.getItems());
        pizzaTypeComboBox.getItems().addAll("Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni");
        pizzaTypeComboBox.getSelectionModel().selectFirst();

        sizeTypeComboBox.getItems().removeAll(sizeTypeComboBox.getItems());
        sizeTypeComboBox.getItems().addAll(Size.values());  // Use the Size enum values
        sizeTypeComboBox.getSelectionModel().selectFirst();

        extraSauce.setOnAction(event -> updateCost());
        extraCheese.setOnAction(event -> updateCost());


        changePicture("src/main/resources/com/example/project4/images/deluxe.jpeg");
        ingredientsListView.setItems(ingredients);

        sizeTypeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldSize, newSize) -> {
            updateCost();
        });

        pizzaTypeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldType, newType) -> {
            switch (newType) {
                case "Deluxe" -> {
                    changePicture("src/main/resources/com/example/project4/images/deluxe.jpeg");
                    ingredients.setAll("Tomato Sauce", "Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom");
                    updateCost();
                }
                case "Supreme" -> {
                    changePicture("src/main/resources/com/example/project4/images/supreme.jpg");
                    ingredients.setAll("Tomato Sauce",  "Sausage", "Pepperoni", "Green Pepper");
                    updateCost();
                }
                case "Meatzza" -> {
                    changePicture("src/main/resources/com/example/project4/images/meattza.jpeg");
                    ingredients.setAll("Tomato Sauce", "Meats");
                    updateCost();
                }
                case "Seafood" -> {
                    changePicture("src/main/resources/com/example/project4/images/seaFood.jpg");
                    ingredients.setAll("Alferado Sauce", "Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom");
                    updateCost();
                }
                case "Pepperoni" -> {
                    changePicture("src/main/resources/com/example/project4/images/pepperoni-pizza.jpeg");
                    ingredients.setAll("Pepperoni", "Tomato Sauce");
                    updateCost();
                }
            }
        });


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
        String selectedPizzaType = pizzaTypeComboBox.getValue();
        Size selectedSize = sizeTypeComboBox.getValue();

        double totalCost = calculateCost(selectedPizzaType, selectedSize);

        // Add $1 for each checked checkbox
        if (extraSauce.isSelected()) {
            totalCost += 1.0;
        }
        if (extraCheese.isSelected()) {
            totalCost += 1.0;
        }

        pizzaSubTotalLabel.setText(String.format("$%.2f", totalCost));
    }

    @FXML
    private void onAddOrderClicked() {
        // Parse the values
        String selectedPizzaType = pizzaTypeComboBox.getValue().toUpperCase();
        Size selectedSize = sizeTypeComboBox.getValue();
        boolean isExtraSauce = extraSauce.isSelected();
        boolean isExtraCheese = extraCheese.isSelected();


        Pizza specialityPizza = PizzaMaker.createPizza(Pizza.PizzaType.valueOf(selectedPizzaType), selectedSize, isExtraSauce, isExtraCheese);

        if (Order.getPizzaOrder().addPizza(specialityPizza)) {
            showSuccessAlert("Pizza Added", "The pizza has been added to the order.");

            System.out.println("Order Details:");
            for (Pizza pizza : Order.getPizzaOrder().getPizzas()) {
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



    private double calculateCost(String pizzaType, Size size) {
        double basePrice = getBasePrice(pizzaType, size);
        return basePrice;
    }


    private double getBasePrice(String pizzaType, Size size) {
        switch (pizzaType) {
            case "Deluxe":
                return switch (size) {
                    case SMALL -> 14.99;
                    case MEDIUM -> 16.99;
                    case LARGE -> 20.99;
                };
            case "Supreme":
                return switch (size) {
                    case SMALL -> 15.99;
                    case MEDIUM -> 17.99;
                    case LARGE -> 21.99;
                };
            case "Meatzza":
                return switch (size) {
                    case SMALL -> 16.99;
                    case MEDIUM -> 18.99;
                    case LARGE -> 22.99;
                };
            case "Seafood":
                return switch (size) {
                    case SMALL -> 17.99;
                    case MEDIUM -> 19.99;
                    case LARGE -> 23.99;
                };
            case "Pepperoni":
                return switch (size) {
                    case SMALL -> 10.99;
                    case MEDIUM -> 12.99;
                    case LARGE -> 16.99;
                };
            default:
                return 0.0;
        }
    }

}
