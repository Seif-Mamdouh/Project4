package com.example.project4;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;


public class SpecialityPizzaController {
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private ImageView pizzaView;
    @FXML
    private ListView<String> ingredientsListView;
    @FXML
    private ObservableList<String> ingredients;
//    @FXML
//    private Label ingredient1;
//    @FXML
//    private Label ingredient2;
//    @FXML
//    private Label ingredient3;
//    @FXML
//    private Label ingredient4;
//    @FXML
//    private Label ingredient5;
//    @FXML
//    private Label ingredient6;

//    public void initialize() {
//
//        ingredients = FXCollections.observableArrayList();
//
//        typeComboBox.getItems().removeAll(typeComboBox.getItems());
//        typeComboBox.getItems().addAll("Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni");
//        typeComboBox.getSelectionModel().selectFirst();
//
//        changePicture("src/main/resources/com/example/project4/images/pizza.jpeg");
//
//        // Bind the ListView to the ingredients list
//        ingredientsListView.setItems(ingredients);
//
//        typeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldType, newType) -> {
//            if (newType.equals("Deluxe")) {
//                changePicture("src/main/resources/com/example/project4/images/deluxe.jpeg");
//                ingredients.setAll("Tomato Sauce", "Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom");
//            } else if (newType.equals("Supreme")) {
//                changePicture("src/main/resources/com/example/project4/images/supreme.jpg");
//                ingredients.setAll("Tomato Sauce", "Pepperoni", "Sausage", "Ham", "Green Pepper", "Onion");
//            }
//        });
//    }


    public void initialize() {

        ingredients = FXCollections.observableArrayList();

        typeComboBox.getItems().removeAll(typeComboBox.getItems());
        typeComboBox.getItems().addAll("Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni");
        typeComboBox.getSelectionModel().selectFirst();


        changePicture("src/main/resources/com/example/project4/images/pizza.jpeg");

        // Bind the ListView to the ingredients list
        ingredientsListView.setItems(ingredients);

        typeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldType, newType) -> {
            if(newType.equals("Deluxe"))
            {
                changePicture("src/main/resources/com/example/project4/images/deluxe.jpeg");
                ingredients.setAll("Tomato Sauce", "Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom");
            }
            else if(newType.equals("Supreme"))
            {
                changePicture("src/main/resources/com/example/project4/images/supreme.jpg");
                ingredients.setAll("Tomato Sauce", "Dont care");

//                updateCost();
            }
            else if(newType.equals("Meatzza"))
            {
                changePicture("src/main/resources/com/example/project4/images/meattza.jpeg");
                ingredients.setAll("Tomato Sauce", "Meats");
//                updateCost();
            }
            else if(newType.equals("Seafood"))
            {
                changePicture("src/main/resources/com/example/project4/images/seaFood.jpg");
                ingredients.setAll("Tomato Sauce", "Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom");
//                updateCost();
            }
            else if(newType.equals("Pepperoni"))
            {
                changePicture("src/main/resources/com/example/project4/images/pepperoni-pizza.jpeg");
                ingredients.setAll("Pepperoni", "Tomato Sauce");
//                updateCost();
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
}
