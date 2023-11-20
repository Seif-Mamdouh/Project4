package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class that acts as controller for main menu view.
 *
 * @author
 */
public class MainMenuController {
    @FXML
    public Button specialtyPizzaButton;
    @FXML
    private Button currentOrderButton;
    @FXML
    private Button storeOrdersButton;
    @FXML
    private Button buildYourOwnPizzaButton;

    /**
     * Handles clicking Build Your Own Pizza button. Disables button until new window is closed.
     */
    @FXML
    private void buildYourOwnPizzaButtonClicked(ActionEvent actionEvent) throws IOException {
        buildYourOwnPizzaButton.setDisable(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("build-your-own-pizza.fxml"));
        Scene scene = new Scene(loader.load());
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("RUPizza - Build Your Own Pizza");
        newStage.setResizable(false);
        newStage.setOnCloseRequest(e -> buildYourOwnPizzaButton.setDisable(false));
        newStage.show();
    }

    /**
     * Handles clicking Specialty Order  button. Disables button until new window is closed.
     */
    @FXML
    private void pizzaButtonClicked(ActionEvent actionEvent) throws IOException {
        specialtyPizzaButton.setDisable(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("speciality-pizza.fxml"));
        Scene scene = new Scene(loader.load());
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("RUPizza -Speciality Pizza");
        newStage.setResizable(false);
        newStage.setOnCloseRequest(e -> specialtyPizzaButton.setDisable(false));
        newStage.show();
    }


    /**
     * Handles clicking current orders button. Disables button until new window is closed.
     */
    @FXML
    private void currentOrderButtonClicked(ActionEvent actionEvent) throws IOException {
        currentOrderButton.setDisable(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("current-order-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("RUPizza - Current Order");
        newStage.setResizable(false);
        newStage.setOnCloseRequest(e -> currentOrderButton.setDisable(false));
        newStage.show();
    }

    /**
     * Handles clicking current orders button. Disables button until new window is closed.
     */
    @FXML
    private void storesOrdersButtonClicked(ActionEvent actionEvent) throws IOException {
        storeOrdersButton.setDisable(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("store-orders-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("RUPizza - Store Orders");
        newStage.setResizable(false);
        newStage.setOnCloseRequest(e -> storeOrdersButton.setDisable(false));
        newStage.show();
    }
}
