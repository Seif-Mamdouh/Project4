package com.example.project4;

import com.example.project4.model.CakeDonut;
import com.example.project4.model.YeastDonut;
import com.example.project4.model.DonutHole;
import com.example.project4.model.Flavor;
import com.example.project4.model.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Class to handle interactions with the donut order window. Includes handling
 * order price and adding to order.
 *
 * @author
 */
public class DonutsViewController
{

    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private ImageView donutView;
    @FXML
    private Label type1Name;
    @FXML
    private Label type2Name;
    @FXML
    private Label type3Name;
    @FXML
    private Label quantity1;
    @FXML
    private Label quantity2;
    @FXML
    private Label quantity3;
    @FXML
    private Button quantity1ButtonDown;
    @FXML
    private Button quantity2ButtonDown;
    @FXML
    private Button quantity3ButtonDown;
    @FXML
    private Button addToOrderButton;
    @FXML
    private Label donutSubTotalLabel;

    /**
     * Initializes combo boxes, and listeners.
     */
    public void initialize() {
        donutSubTotalLabel.setText("$0.00");

        quantity1.setText("0");
        quantity2.setText("0");
        quantity3.setText("0");

        addToOrderButton.setDisable(true);

        typeComboBox.getItems().removeAll(typeComboBox.getItems());
        typeComboBox.getItems().addAll("Yeast Donut", "Cake Donut", "Donut Hole");
        typeComboBox.getSelectionModel().selectFirst();
        type1Name.setText("GLAZED");
        type2Name.setText("JELLY");
        type3Name.setText("CHOCOLATE");



        changePicture("src/main/resources/com/example/project4/donuts.jpeg");
        typeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldType, newType) -> {
            if(newType.equals("Yeast Donut"))
            {
                type1Name.setText("GLAZED");
                type2Name.setText("JELLY");
                type3Name.setText("CHOCOLATE");
                changePicture("src/main/resources/com/example/project4/yeast-donut.jpeg");
                updateCost();
            }
            else if(newType.equals("Cake Donut"))
            {
                type1Name.setText("STRAWBERRY");
                type2Name.setText("COFFEE");
                type3Name.setText("APPLE");
                changePicture("src/main/resources/com/example/project4/cake-donut.jpeg");
                updateCost();
            }
            else if(newType.equals("Donut Hole"))
            {
                type1Name.setText("PLAIN");
                type2Name.setText("POWDERED");
                type3Name.setText("CHOCOLATE");
                changePicture("src/main/resources/com/example/project4/hole-donut.jpeg");
                updateCost();
            }
            else
            {
                addToOrderButton.setDisable(true);
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
        donutView.setImage(newPic);
    }

    /**
     * Handles clicking + button for type 1.
     */
    @FXML
    private void addQuantity1ButtonClicked(ActionEvent actionEvent) {
        int Q1 = Integer.valueOf(quantity1.getText());
        Q1++;
        quantity1.setText(String.valueOf(Q1));
        quantity1ButtonDown.setDisable(false);
        addToOrderButton.setDisable(false);
        updateCost();
    }
    /**
     * Handles clicking + button for type 2.
     */
    @FXML
    private void addQuantity2ButtonClicked(ActionEvent actionEvent) {
        int Q2 = Integer.valueOf(quantity2.getText());
        Q2++;
        quantity2.setText(String.valueOf(Q2));
        quantity2ButtonDown.setDisable(false);
        addToOrderButton.setDisable(false);
        updateCost();
    }
    /**
     * Handles clicking + button for type 3.
     */
    @FXML
    private void addQuantity3ButtonClicked(ActionEvent actionEvent) {
        int Q3 = Integer.valueOf(quantity3.getText());
        Q3++;
        quantity3.setText(String.valueOf(Q3));
        quantity3ButtonDown.setDisable(false);
        addToOrderButton.setDisable(false);
        updateCost();
    }
    /**
     * Handles clicking - button for type 1.
     */
    @FXML
    private void lessQuantity1ButtonClicked(ActionEvent actionEvent) {
        int Q1 = Integer.valueOf(quantity1.getText());
        Q1--;
        quantity1.setText(String.valueOf(Q1));
        if(Q1 == 0)
        {
            quantity1ButtonDown.setDisable(true);
            int Q = Integer.valueOf(quantity1.getText())+Integer.valueOf(quantity2.getText())+Integer.valueOf(quantity3.getText());
            if(Q==0)
            {
                addToOrderButton.setDisable(true);
            }
        }
        updateCost();
    }
    /**
     * Handles clicking - button for type 2.
     */
    @FXML
    private void lessQuantity2ButtonClicked(ActionEvent actionEvent) {
        int Q2 = Integer.valueOf(quantity2.getText());
        Q2--;
        quantity2.setText(String.valueOf(Q2));
        if(Q2 == 0)
        {
            quantity2ButtonDown.setDisable(true);
            int Q = Integer.valueOf(quantity1.getText())+Integer.valueOf(quantity2.getText())+Integer.valueOf(quantity3.getText());
            if(Q==0)
            {
                addToOrderButton.setDisable(true);
            }
        }
        updateCost();
    }
    /**
     * Handles clicking - button for type 3.
     */
    @FXML
    private void lessQuantity3ButtonClicked(ActionEvent actionEvent) {
        int Q3 = Integer.valueOf(quantity3.getText());
        Q3--;
        quantity3.setText(String.valueOf(Q3));
        if(Q3 == 0)
        {
            quantity3ButtonDown.setDisable(true);
            int Q = Integer.valueOf(quantity1.getText())+Integer.valueOf(quantity2.getText())+Integer.valueOf(quantity3.getText());
            if(Q==0)
            {
                addToOrderButton.setDisable(true);
            }
        }
        updateCost();
    }

    /**
     * Helper method that updates the subtotal displayed on screen.
     */
    public void updateCost()
    {
        String type = typeComboBox.getSelectionModel().getSelectedItem();

        int Q1 = Integer.valueOf(quantity1.getText());
        int Q2 = Integer.valueOf(quantity2.getText());
        int Q3 = Integer.valueOf(quantity3.getText());
        int total = Q1 + Q2 + Q3;

        double cost = 0;

        if(type.equals("Yeast Donut")) {
            cost = total * YeastDonut.PRICE;
        }
        else if(type.equals("Cake Donut")) {
            cost = total * CakeDonut.PRICE;
        }
        else {
            cost = total * DonutHole.PRICE;
        }

        donutSubTotalLabel.setText("$" + String.format("%.2f", cost));
    }

    /**
     * Handles clicking add to order button. Adds to current order instance.
     */
    @FXML
    private void addToOrderButtonClicked(ActionEvent actionEvent) {
        String type = typeComboBox.getSelectionModel().getSelectedItem();

        int Q1 = Integer.valueOf(quantity1.getText());
        int Q2 = Integer.valueOf(quantity2.getText());
        int Q3 = Integer.valueOf(quantity3.getText());

        if(type.equals("Yeast Donut")) {
            addYeast(Q1, Q2, Q3);
        }
        else if(type.equals("Cake Donut")) {
            addCake(Q1, Q2, Q3);
        }
        else {
            addHole(Q1, Q2, Q3);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Confirmation");
        alert.setHeaderText("Your donut order has been added.");
        alert.showAndWait();
    }

    /**
     * Adds Yeast Donuts to the Current Order.
     */
    public void addYeast(int Q1, int Q2, int Q3){
        if(Q1 > 0) {
            YeastDonut donut1 = new YeastDonut(Flavor.GLAZED, Q1);
            Order.getInstance().add(donut1);
        }
        if(Q2 > 0) {
            YeastDonut donut2 = new YeastDonut(Flavor.JELLY, Q2);
            Order.getInstance().add(donut2);
        }
        if(Q3 > 0) {
            YeastDonut donut3 = new YeastDonut(Flavor.CHOCOLATE, Q3);
            Order.getInstance().add(donut3);
        }
    }
    /**
     * Adds Cake Donuts to the Current Order.
     */
    public void addCake(int Q1, int Q2, int Q3){
        if(Q1 > 0) {
            CakeDonut donut1 = new CakeDonut(Flavor.STRAWBERRY, Q1);
            Order.getInstance().add(donut1);
        }
        if(Q2 > 0) {
            CakeDonut donut2 = new CakeDonut(Flavor.COFFEE, Q2);
            Order.getInstance().add(donut2);
        }
        if(Q3 > 0) {
            CakeDonut donut3 = new CakeDonut(Flavor.APPLE, Q3);
            Order.getInstance().add(donut3);
        }
    }
    /**
     * Adds Donut Holes to the Current Order.
     */
    public void addHole(int Q1, int Q2, int Q3){
        if(Q1 > 0) {
            DonutHole donut1 = new DonutHole(Flavor.PLAIN, Q1);
            Order.getInstance().add(donut1);
        }
        if(Q2 > 0) {
            DonutHole donut2 = new DonutHole(Flavor.POWDERED, Q2);
            Order.getInstance().add(donut2);
        }
        if(Q3 > 0) {
            DonutHole donut3 = new DonutHole(Flavor.CHOCOLATE, Q3);
            Order.getInstance().add(donut3);
        }
    }

}
