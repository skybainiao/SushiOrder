package Client.Model.Customer;

import Client.Networking.Client;
import Server.Shared.Order;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerImpl implements CustomerModel{
    private Client client;

    public CustomerImpl(Client client) throws RemoteException, NotBoundException {
        this.client=client;
    }


    @Override
    public void addOrder(Order order) throws RemoteException {
        client.addOrder(order);
    }
}
