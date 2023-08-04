package Client.View;

import Server.Shared.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class EmployeeView {
    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order, String> orderIdColumn;
    @FXML
    private TableColumn<Order, String> foodNameColumn;
    @FXML
    private TableColumn<Order, String> orderStatusColumn;
    @FXML
    private TableColumn<Order, Void> orderActionColumn;

    @FXML
    public void initialize() {
        // Set up the columns to display the right property of Order
        //orderIdColumn.setCellValueFactory(cellData -> cellData.getValue().orderIdProperty());
        //foodNameColumn.setCellValueFactory(cellData -> cellData.getValue().foodNameProperty());
        //orderStatusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        // Add the 'Complete' button to the action column
        orderActionColumn.setCellFactory(getButtonCellFactory());
    }

    private Callback<TableColumn<Order, Void>, TableCell<Order, Void>> getButtonCellFactory() {
        return new Callback<>() {
            @Override
            public TableCell<Order, Void> call(TableColumn<Order, Void> param) {
                return new TableCell<>() {
                    private final Button completeButton = new Button("Complete");

                    {
                        completeButton.setOnAction(event -> {
                            Order order = getTableView().getItems().get(getIndex());
                            // Your code to complete the order
                            completeOrder(order);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(completeButton);
                        }
                    }
                };
            }
        };
    }

    private void completeOrder(Order order) {
        // Your code here to mark the order as completed
    }

    @FXML
    public void showNewOrders() {
        // Your code here to fetch and display new orders
    }

    @FXML
    public void showCompletedOrders() {
        // Your code here to fetch and display completed orders
    }
}
