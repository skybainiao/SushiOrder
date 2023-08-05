package Client.ViewModel;

import Client.Model.Customer.CustomerModel;
import Client.Model.Employee.EmployeeModel;
import Server.PCS;
import Server.Shared.Order;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeVM implements PCS {

    private EmployeeModel model;
    private PropertyChangeSupport support;

    public EmployeeVM(EmployeeModel model) throws RemoteException {
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
        System.out.println("EmployeeOrder");
    }
    public ArrayList<Order> getOrders() throws Exception {
        return model.getOrders();
    }

    public int updateOrderStatus(int orderId, String newStatus) throws SQLException, RemoteException {
        return model.updateOrderStatus(orderId,newStatus);
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
