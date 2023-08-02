package Client.Model.Customer;

import Server.Shared.Order;


import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerModel {
    void addOrder(Order order) throws RemoteException;
}
