package Client.View;

import Client.ViewModel.EmployeeVM;
import Client.ViewModel.HomeVM;
import Server.Shared.Order;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.stream.Collectors;

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
    private EmployeeVM employeeVM;
    private ViewHandler viewHandler;

    @FXML
    public void initialize(EmployeeVM employeeVM, ViewHandler viewHandler) throws Exception {
        this.employeeVM = employeeVM;
        this.viewHandler=viewHandler;
        orderActionColumn.setCellFactory(getButtonCellFactory());
        populateOrderTable();
        showNewOrders();

        employeeVM.addPCL("order",evt -> { propertyChange(evt);}); ;

    }

    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            try {
                showNewOrders();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
    }

    private void populateOrderTable() {
        // set up the columns in the table
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderId"));
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("foodName"));
        orderStatusColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderStatus"));

        try {
            // add your data to the table here.
            ObservableList<Order> orders = FXCollections.observableArrayList(employeeVM.getOrders());
            orderTable.setItems(orders);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                            try {
                                completeOrder(order);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
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

    private void completeOrder(Order order) throws Exception {
        order.setOrderStatus("completed");
        employeeVM.updateOrderStatus(order.getOrderId(), "completed");

        showNewOrders();
    }

    @FXML
    public void showNewOrders() throws Exception {
        updateOrderTable(employeeVM.getOrders().stream()
                .filter(order -> order.getOrderStatus().equals("processing"))
                .collect(Collectors.toList()));
    }

    @FXML
    public void showCompletedOrders() throws Exception {
        updateOrderTable(employeeVM.getOrders().stream()
                .filter(order -> order.getOrderStatus().equals("completed"))
                .collect(Collectors.toList()));
    }

    public void showAllOrders() throws Exception {
        updateOrderTable(employeeVM.getOrders());
    }

    private void updateOrderTable(List<Order> orders) {
        ObservableList<Order> observableOrders = FXCollections.observableArrayList(orders);
        orderTable.setItems(observableOrders);
    }


}
