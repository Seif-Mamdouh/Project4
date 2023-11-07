package com.example.project4;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpecialityPizzaController {
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private ImageView pizzaView;




    public void initialize() {

        typeComboBox.getItems().removeAll(typeComboBox.getItems());
        typeComboBox.getItems().addAll("Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni");
        typeComboBox.getSelectionModel().selectFirst();

        changePicture("src/main/resources/com/example/project4/images/pizza.jpeg");
        typeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldType, newType) -> {
            if(newType.equals("Deluxe"))
            {
                changePicture("src/main/resources/com/example/project4/images/deluxe.jpeg");
//                updateCost();
            }
            else if(newType.equals("Supreme"))
            {
//                type1Name.setText("STRAWBERRY");
//                type2Name.setText("COFFEE");
//                type3Name.setText("APPLE");
                changePicture("src/main/resources/com/example/project4/images/supreme.jpg");
//                updateCost();
            }
            else if(newType.equals("Meatzza"))
            {
                changePicture("src/main/resources/com/example/project4/images/meattza.jpeg");
//                updateCost();
            }
            else if(newType.equals("Seafood"))
            {
                changePicture("src/main/resources/com/example/project4/images/seaFood.jpg");
//                updateCost();
            }
            else if(newType.equals("Pepperoni"))
            {
                changePicture("src/main/resources/com/example/project4/images/pepperoni-pizza.jpeg");
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
