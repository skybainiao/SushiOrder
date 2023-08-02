package Client.ViewModel;

import Client.Model.Customer.CustomerModel;
import Server.Shared.Order;
import javafx.beans.property.SimpleStringProperty;

import java.rmi.RemoteException;

public class HomeVM {

    private CustomerModel model;


    public HomeVM(CustomerModel model){
        this.model=model;

    }

    public void addOrder(Order order) throws RemoteException {
        model.addOrder(order);
    }

}
