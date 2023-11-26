package com.example.project4;

import com.example.project4.RUpizza.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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


    private static final double BASE_PRICE_DELUXE_SMALL = 14.99;
    private static final double BASE_PRICE_DELUXE_MEDIUM = 16.99;
    private static final double BASE_PRICE_DELUXE_LARGE = 20.99;

    private static final double BASE_PRICE_SUPREME_SMALL = 15.99;
    private static final double BASE_PRICE_SUPREME_MEDIUM = 17.99;
    private static final double BASE_PRICE_SUPREME_LARGE = 21.99;

    private static final double BASE_PRICE_MEATZZA_SMALL = 16.99;
    private static final double BASE_PRICE_MEATZZA_MEDIUM = 18.99;
    private static final double BASE_PRICE_MEATZZA_LARGE = 22.99;

    private static final double BASE_PRICE_SEAFOOD_SMALL = 17.99;
    private static final double BASE_PRICE_SEAFOOD_MEDIUM = 19.99;
    private static final double BASE_PRICE_SEAFOOD_LARGE = 23.99;

    private static final double BASE_PRICE_PEPPERONI_SMALL = 10.99;
    private static final double BASE_PRICE_PEPPERONI_MEDIUM = 12.99;
    private static final double BASE_PRICE_PEPPERONI_LARGE = 16.99;

    private static final double DEFAULT_BASE_PRICE = 0.0;

    private static final double DEFAULT = 0.0;


    /**
     * Initializes the controller. Sets up event listeners, initializes UI components,
     * and populates the view with default pizza information.
     */
    public void initialize() {
        pizzaSubTotalLabel.setText("$00.00");
        ingredients = FXCollections.observableArrayList();
        pizzaTypeComboBox.getItems().removeAll(pizzaTypeComboBox.getItems());
        pizzaTypeComboBox.getItems().addAll("Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni");
        pizzaTypeComboBox.getSelectionModel();
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
            updatePizzaType(newType);
        });
    }


    private void updatePizzaType(String newType) {
        switch (newType) {
            case "Deluxe" -> {
                changePicture("src/main/resources/com/example/project4/images/deluxe.jpeg");
                List<String> newIngredients = new ArrayList<>(List.of("Tomato Sauce", "Sausage", "pepperoni", "green pepper", "onion", "mushroom"));
                updateCost();
            }
            case "Supreme" -> {
                changePicture("src/main/resources/com/example/project4/images/supreme.jpg");
                List<String> newIngredients = new ArrayList<>(List.of("Tomato Sauce", "Sausage", "Pepperoni", "Green Pepper", "Ham", "Onion", "Black Olive", "Mushroom"));
                updateCost();
            }
            case "Meatzza" -> {
                changePicture("src/main/resources/com/example/project4/images/meattza.jpeg");
                List<String> newIngredients = new ArrayList<>(List.of("Tomato Sauce", "Meats", "Sausage", "Pepperoni", "Beef", "Ham"));
                updateCost();
            }
            case "Seafood" -> {
                changePicture("src/main/resources/com/example/project4/images/seaFood.jpg");
                List<String> newIngredients = new ArrayList<>(List.of("Alferado Sauce", "Shrimp", "Squid", "Crab Meats"));
                updateCost();
            }
            case "Pepperoni" -> {
                changePicture("src/main/resources/com/example/project4/images/pepperoni-pizza.jpeg");
                List<String> newIngredients = new ArrayList<>(List.of("Tomato Sauce", "Pepperoni"));
                updateCost();
            }
        }
    }

    /**
     * Handles the event when the "Add to Order" button is clicked.
     * Parses user selections and creates a speciality pizza, adding it to the order.
     * Displays success or error alerts based on the result.
     */
    @FXML
    private void onAddOrderClicked() {
        if (pizzaTypeComboBox.getSelectionModel().isEmpty()) {
            showErrorAlert("Error", "Please select a pizza type before adding to the order.");
            return;
        }
        // Parse the values
        String selectedPizzaType = pizzaTypeComboBox.getValue().toUpperCase();
        Size selectedSize = sizeTypeComboBox.getValue();
        boolean isExtraSauce = extraSauce.isSelected();
        boolean isExtraCheese = extraCheese.isSelected();

        // Check if either extraSauce or extraCheese is selected without selecting a pizza type
        if ((isExtraSauce || isExtraCheese) && pizzaTypeComboBox.getSelectionModel().isEmpty()) {
            showErrorAlert("Error", "Please select a pizza type before adding extra sauce or extra cheese.");
            return;
        }

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
     * Updates the image being displayed on the page
     */
    private void changePicture(String name) {
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
        if (pizzaType == null || size == null) {
            return DEFAULT;
        }
        double basePrice = getBasePrice(pizzaType, size);
        return basePrice;
    }



    private double getBasePrice(String pizzaType, Size size) {
        switch (pizzaType) {
            case "Deluxe":
                return switch (size) {
                    case SMALL -> BASE_PRICE_DELUXE_SMALL;
                    case MEDIUM -> BASE_PRICE_DELUXE_MEDIUM;
                    case LARGE -> BASE_PRICE_DELUXE_LARGE;
                };
            case "Supreme":
                return switch (size) {
                    case SMALL -> BASE_PRICE_SUPREME_SMALL;
                    case MEDIUM -> BASE_PRICE_SUPREME_MEDIUM;
                    case LARGE -> BASE_PRICE_SUPREME_LARGE;
                };
            case "Meatzza":
                return switch (size) {
                    case SMALL -> BASE_PRICE_MEATZZA_SMALL;
                    case MEDIUM -> BASE_PRICE_MEATZZA_MEDIUM;
                    case LARGE -> BASE_PRICE_MEATZZA_LARGE;
                };
            case "Seafood":
                return switch (size) {
                    case SMALL -> BASE_PRICE_SEAFOOD_SMALL;
                    case MEDIUM -> BASE_PRICE_SEAFOOD_MEDIUM;
                    case LARGE -> BASE_PRICE_SEAFOOD_LARGE;
                };
            case "Pepperoni":
                return switch (size) {
                    case SMALL -> BASE_PRICE_PEPPERONI_SMALL;
                    case MEDIUM -> BASE_PRICE_PEPPERONI_MEDIUM;
                    case LARGE -> BASE_PRICE_PEPPERONI_LARGE;
                };
            default:
                return DEFAULT_BASE_PRICE;
        }
    }


}
