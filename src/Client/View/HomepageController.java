package Client.View;

import Client.ViewModel.HomeVM;
import Server.Shared.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class HomepageController {

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

    private HomeVM homepageVM;
    private ViewHandler viewHandler;

    private final Map<String, Map<String, Integer>> categoryToFoodMap = new HashMap<>() {{
        put("Sushi", new HashMap<>() {{
            put("Salmon Sushi", 10);
            put("Tuna Sushi", 15);
            put("Eel Sushi", 20);
            put("Shrimp Sushi", 15);
            put("Octopus Sushi", 12);
            put("Crab Sushi", 16);
            put("Scallop Sushi", 18);
            put("Squid Sushi", 14);
            put("Mackerel Sushi", 12);
            put("Sea Urchin Sushi", 20);
        }});
        put("Drinks", new HashMap<>() {{
            put("Green Tea", 3);
            put("Black Tea", 3);
            put("Oolong Tea", 4);
            put("Sake", 7);
            put("Plum Wine", 8);
            put("Beer", 5);
            put("Soda", 2);
            put("Water", 1);
            put("Lemonade", 2);
            put("Coffee", 3);
        }});
        put("Fried Skewers", new HashMap<>() {{
            put("Chicken Skewer", 5);
            put("Beef Skewer", 7);
            put("Pork Skewer", 6);
            put("Shrimp Skewer", 8);
            put("Salmon Skewer", 7);
            put("Vegetable Skewer", 4);
            put("Mushroom Skewer", 4);
            put("Cheese Skewer", 6);
            put("Sausage Skewer", 5);
            put("Eggplant Skewer", 4);
        }});
    }};

    private final Map<String, Integer> orderMap = new HashMap<>();
    private int totalCost = 0;

    public void init(HomeVM homepageVM, ViewHandler viewHandler) throws RemoteException, SQLException {
        this.homepageVM = homepageVM;
        this.viewHandler=viewHandler;

        categoryList.getItems().addAll(categoryToFoodMap.keySet());
        categoryList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateFoodGrid(newValue));
    }

    private void updateFoodGrid(String category) {
        foodGrid.getChildren().clear();
        Map<String, Integer> foods = categoryToFoodMap.get(category);
        int vboxIndex = 0;
        for (String food : foods.keySet()) {
            VBox vbox = new VBox(10);
            Label label = new Label(food + " - $" + foods.get(food));
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
            orderList.getItems().add(entry.getKey() + " x" + entry.getValue());
        }
        totalPrice.setText("Total price: $" + totalCost);
    }

    @FXML
    private void submitOrder() throws SQLException, RemoteException {
        Order order = new Order(new HashMap<>(orderMap), totalCost);
        homepageVM.addOrder(order);
        System.out.println(order);
        System.out.println("Order submitted");
    }


}
