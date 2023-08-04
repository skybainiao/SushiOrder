package Client.Model.Customer;

import Client.Networking.Client;
import Server.PCS;
import Server.Shared.Order;


import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerModel extends PCS {
    void addOrder(Order order) throws RemoteException;

    List<Client> getClients() throws RemoteException;
}
