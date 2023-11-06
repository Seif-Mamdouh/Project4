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
 * @author Victor Ochoa
 */
public class MainMenuController {

    @FXML
    private Button orderCoffeeButton;
    @FXML
    private Button currentOrderButton;
    @FXML
    private Button storeOrdersButton;
    @FXML
    private Button orderDonutsButton;

    /**
     * Handles clicking order coffee button. Disables button until new window is closed.
     */
    @FXML
    private void orderCoffeeButtonClicked(ActionEvent actionEvent) throws IOException {
        orderCoffeeButton.setDisable(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("coffee-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("RUCafe - Order Coffee");
        newStage.setResizable(false);
        newStage.setOnCloseRequest(e -> orderCoffeeButton.setDisable(false));
        newStage.show();
    }

    /**
     * Handles clicking order donuts button. Disables button until new window is closed.
     */
    @FXML
    private void orderDonutsButtonClicked(ActionEvent actionEvent) throws IOException {
        orderDonutsButton.setDisable(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("donuts-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("RUCafe - Order Donuts");
        newStage.setResizable(false);
        newStage.setOnCloseRequest(e -> orderDonutsButton.setDisable(false));
        newStage.show();
    }

    /**
     * Handles clicking current orders button. Disables button until new window is closed.
     */
    @FXML
    private void currentOrderButtonClicked(ActionEvent actionEvent) throws IOException {
        //TODO: Tweak so that not everytime a new instance is generated, keep list live updating if window open?
        currentOrderButton.setDisable(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("current-order-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("RUCafe - Current Order");
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
        newStage.setTitle("RUCafe - Store Orders");
        newStage.setResizable(false);
        newStage.setOnCloseRequest(e -> storeOrdersButton.setDisable(false));
        newStage.show();
    }
}
