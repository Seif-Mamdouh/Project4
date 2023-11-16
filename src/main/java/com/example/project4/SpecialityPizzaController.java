package com.example.project4;

import com.example.project4.RUpizza.*;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;


import javafx.collections.ObservableList;



/**
 * Controller class for handling interactions with the Speciality Pizza view.
 * Manages the display and selection of pizza types, sizes, and toppings.
 * Allows users to customize and add pizzas to the order.
 * This class is responsible for initializing the view components, handling user actions,
 * and updating the displayed information based on user selections.
 *
 * @Seifeldeen Mohamed
 **/
public class SpecialityPizzaController {
    @FXML
    private Button onAddOrderClicked;
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


    /**
     * Initializes the controller. Sets up event listeners, initializes UI components,
     * and populates the view with default pizza information.
     */
    public void initialize() {
        pizzaSubTotalLabel.setText("$14.99");

        ingredients = FXCollections.observableArrayList();

        pizzaTypeComboBox.getItems().removeAll(pizzaTypeComboBox.getItems());
        pizzaTypeComboBox.getItems().addAll("Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni");
        pizzaTypeComboBox.getSelectionModel().selectFirst();

        sizeTypeComboBox.getItems().removeAll(sizeTypeComboBox.getItems());
        sizeTypeComboBox.getItems().addAll(Size.values());
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

    /**
     * Updates the cost label based on the selected pizza type, size, and additional toppings.
     */
    @FXML
    private void updateCost() {
        String selectedPizzaType = pizzaTypeComboBox.getValue();
        Size selectedSize = sizeTypeComboBox.getValue();

        double totalCost = calculateCost(selectedPizzaType, selectedSize);

        if (extraSauce.isSelected()) {
            totalCost += 1.0;
        }
        if (extraCheese.isSelected()) {
            totalCost += 1.0;
        }

        pizzaSubTotalLabel.setText(String.format("$%.2f", totalCost));
    }

    /**
     * Handles the event when the "Add to Order" button is clicked.
     * Parses user selections and creates a speciality pizza, adding it to the order.
     * Displays success or error alerts based on the result.
     */
    @FXML
    private void onAddOrderClicked() {
        // Parse the values
        String selectedPizzaType = pizzaTypeComboBox.getValue().toUpperCase();
        Size selectedSize = sizeTypeComboBox.getValue();
        boolean isExtraSauce = extraSauce.isSelected();
        boolean isExtraCheese = extraCheese.isSelected();


        Pizza specialityPizza = PizzaMaker.createPizza(Pizza.PizzaType.valueOf(selectedPizzaType), selectedSize, isExtraSauce, isExtraCheese);

        if (Order.getPizzaOrder().addPizza((SpecialityPizza) specialityPizza)) {
            showSuccessAlert("Pizza Added", "The pizza has been added to the order.");

            System.out.println("Order Details:");
            for (Object pizza : Order.getPizzaOrder().getPizzas()) {
                System.out.println(pizza.toString());
            }

        } else {
            showErrorAlert("Error", "Failed to add the pizza to the order.");
        }


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


    /**
     * Calculates the total cost of the pizza based on the selected pizza type, size, and additional toppings.
     *
     * @param pizzaType The selected pizza type.
     * @param size      The selected pizza size.
     * @return The total cost of the pizza.
     */
    private double calculateCost(String pizzaType, Size size) {
        double basePrice = getBasePrice(pizzaType, size);
        return basePrice;
    }


    /**
     * Retrieves the base price of the pizza based on the selected pizza type and size.
     *
     * @param pizzaType The selected pizza type.
     * @param size      The selected pizza size.
     * @return The base price of the pizza.
     */
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
