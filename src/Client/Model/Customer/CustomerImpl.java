package Client.Model.Customer;

import Client.Networking.Client;
import Server.PCS;
import Server.Shared.Order;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerImpl implements CustomerModel{
    private Client client;

    private PropertyChangeSupport support;

    public CustomerImpl(Client client) throws RemoteException, NotBoundException {
        this.client=client;
        support=new PropertyChangeSupport(this);
        client.addPCL("order",evt -> propertyChange(evt));
    }


    @Override
    public void addOrder(Order order) throws RemoteException {
        client.addOrder(order);
    }

    @Override
    public List<Client> getClients() throws RemoteException {
        return null;
    }

    @Override
    public void addPCL(String name, PropertyChangeListener listener) throws RemoteException {
        support.addPropertyChangeListener(name,listener);
    }

    @Override
    public void removePCL(String name, PropertyChangeListener listener) throws RemoteException {
        support.removePropertyChangeListener(name,listener);
    }

    private void propertyChange(PropertyChangeEvent evt)
    {
        support.firePropertyChange(evt);
        System.out.println("Model");
    }
}
