package Client.View;

import Client.ViewModel.HomeVM;
import Server.Shared.Order;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HomepageController{

    @FXML
    private ListView<String> categoryList;

    @FXML
    private GridPane foodGrid;

    @FXML
    private ListView<String> orderList;

    @FXML
    private Button btnSubmitOrder;

    @FXML
    private Label totalPrice;
    @FXML
    private Button adminButton;

    private HomeVM homepageVM;
    private ViewHandler viewHandler;

    private Order order;



    private final Map<String, Map<String, Integer>> categoryToFoodMap = new HashMap<>() {{
        put("Sushi", new HashMap<>() {{
            put("Salmon Sushi", 80);
            put("Tuna Sushi", 120);
            put("Eel Sushi", 100);
            put("Shrimp Sushi", 80);
            put("Octopus Sushi", 120);
            put("Crab Sushi", 160);
            put("Scallop Sushi", 120);
            put("Squid Sushi", 90);
            put("Mackerel Sushi", 120);
            put("Sea Urchin Sushi", 100);
        }});
        put("Drinks", new HashMap<>() {{
            put("Green Tea", 30);
            put("Black Tea", 30);
            put("Oolong Tea", 40);
            put("Sake", 70);
            put("Plum Wine", 80);
            put("Beer", 50);
            put("Soda", 20);
            put("Water", 10);
            put("Lemonade", 20);
            put("Coffee", 30);
        }});
        put("Fried Skewers", new HashMap<>() {{
            put("Chicken Skewer", 50);
            put("Beef Skewer", 70);
            put("Pork Skewer", 60);
            put("Shrimp Skewer", 80);
            put("Salmon Skewer", 70);
            put("Vegetable Skewer", 40);
            put("Mushroom Skewer", 40);
            put("Cheese Skewer", 60);
            put("Sausage Skewer", 50);
            put("Eggplant Skewer", 40);
        }});
    }};

    private final Map<String, Integer> orderMap = new HashMap<>();
    private int totalCost = 0;

    public void init(HomeVM homepageVM, ViewHandler viewHandler) throws RemoteException, SQLException {
        this.homepageVM = homepageVM;
        this.viewHandler=viewHandler;

        categoryList.getItems().addAll(categoryToFoodMap.keySet());
        categoryList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateFoodGrid(newValue));
        order=new Order(0,orderMap,totalCost,"processing");

        homepageVM.addPCL("order",evt -> { propertyChange(evt);}); ;


    }

    private void updateFoodGrid(String category) {
        foodGrid.getChildren().clear();
        Map<String, Integer> foods = categoryToFoodMap.get(category);
        int vboxIndex = 0;
        for (String food : foods.keySet()) {
            VBox vbox = new VBox(10);
            Label label = new Label(food + " - kr" + foods.get(food));
            Button button = new Button("+");
            button.setOnAction(event -> addFoodToOrder(food, foods.get(food)));
            vbox.getChildren().addAll(label, button);
            foodGrid.add(vbox, vboxIndex % 4, vboxIndex / 4);
            vboxIndex++;
        }
    }

    private void addFoodToOrder(String food, int price) {
        orderMap.put(food, orderMap.getOrDefault(food, 0) + 1);
        totalCost += price;
        updateOrderList();
    }

    private void updateOrderList() {
        orderList.getItems().clear();
        for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
            orderList.getItems().add(entry.getKey() + " *" + entry.getValue());
        }
        totalPrice.setText("Total price: kr" + totalCost);
    }

    @FXML
    private void submitOrder() throws SQLException, RemoteException {
        Order newOrder = new Order(orderMap, totalCost, "processing");
        homepageVM.addOrder(newOrder);
        System.out.println(newOrder);
        System.out.println("Order submitted");
        // clear order data and order list
        orderMap.clear();
        totalCost = 0;
        orderList.getItems().clear();
        totalPrice.setText("Total price: kr" + totalCost);
    }






    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            System.out.println("success");

        });
    }

    @FXML
    private void openAdminPage() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Admin Page Login");
        dialog.setHeaderText("Enter the admin password:");
        dialog.setContentText("Password:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && result.get().equals("1234")) {
            try {
                viewHandler.openEmployeeView();
            } catch (Exception e) {
                // handle exception here
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect password. Please try again.");
            alert.showAndWait();
        }
    }

}
