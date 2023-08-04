package Client.ViewModel;

import Client.Model.Customer.CustomerModel;
import Server.PCS;
import Server.Shared.Order;
import javafx.beans.property.SimpleStringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class HomeVM implements PCS {

    private CustomerModel model;
    private PropertyChangeSupport support;


    public HomeVM(CustomerModel model) throws RemoteException {
        this.model=model;
        support=new PropertyChangeSupport(this);
        model.addPCL("order",evt -> {
            try {
                propertyChange(evt);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    private void propertyChange(PropertyChangeEvent evt) throws RemoteException
    {
        support.firePropertyChange(evt);
        System.out.println("order");
    }

    public void addOrder(Order order) throws RemoteException {
        model.addOrder(order);
        support.firePropertyChange("addOrder",null,order);

    }

    @Override
    public void addPCL(String name, PropertyChangeListener listener) throws RemoteException {
        support.addPropertyChangeListener(name, listener);
    }

    @Override
    public void removePCL(String name, PropertyChangeListener listener) throws RemoteException {
        support.removePropertyChangeListener(name, listener);
    }
}
