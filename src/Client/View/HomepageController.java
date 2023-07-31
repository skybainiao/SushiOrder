package Client.View;

import Client.ViewModel.HomeVM;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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

    private HomeVM homepageVM;
    private ViewHandler viewHandler;

    private final Map<String, String[]> categoryToFoodMap = new HashMap<>() {{
        put("Sushi", new String[]{"Salmon Sushi", "Tuna Sushi", "Eel Sushi", "Shrimp Sushi", "Octopus Sushi", "Crab Sushi", "Scallop Sushi", "Squid Sushi", "Mackerel Sushi", "Sea Urchin Sushi"});
        put("Drinks", new String[]{"Green Tea", "Black Tea", "Oolong Tea", "Sake", "Plum Wine", "Beer", "Soda", "Water", "Lemonade", "Coffee"});
        put("Fried Skewers", new String[]{"Chicken Skewer", "Beef Skewer", "Pork Skewer", "Shrimp Skewer", "Salmon Skewer", "Vegetable Skewer", "Mushroom Skewer", "Cheese Skewer", "Sausage Skewer", "Eggplant Skewer"});
    }};

    public void init(HomeVM homepageVM, ViewHandler viewHandler) throws RemoteException, SQLException {
        this.homepageVM = homepageVM;
        this.viewHandler=viewHandler;


        categoryList.getItems().addAll(categoryToFoodMap.keySet());
        categoryList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateFoodGrid(newValue));


    }



    private void updateFoodGrid(String category) {
        foodGrid.getChildren().clear();
        String[] foods = categoryToFoodMap.get(category);
        for (int i = 0; i < foods.length; i++) {
            VBox vbox = new VBox(10);
            Label label = new Label(foods[i]);
            Button button = new Button("+");
            int finalI = i;
            button.setOnAction(event -> orderList.getItems().add(foods[finalI]));
            vbox.getChildren().addAll(label, button);
            foodGrid.add(vbox, i % 4, i / 4);
        }
    }

    @FXML
    private void submitOrder() {
        // Implement your order submission logic here
    }
}
