package Server;

import Client.Networking.Client;
import Server.Database.CustomerDatabaseOperations;
import Server.Database.EmployeeDatabaseOperations;


import Server.Shared.Order;
import Server.Shared.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerImpl implements Server{
    private PropertyChangeSupport support=new PropertyChangeSupport(this);


   // private JDBC jdbc;
    private List<Client> clients;
    private Map<Client, PropertyChangeListener> listeners = new HashMap<>();
    private CustomerDatabaseOperations customerDatabaseOperations;
    private EmployeeDatabaseOperations employeeDatabaseOperations;


    public ServerImpl() throws Exception {
        Registry registry= LocateRegistry.createRegistry(6666);
        registry.bind("Server",this);
        UnicastRemoteObject.exportObject(this,6666);

        System.out.println("Server Start");

        clients=new ArrayList<>();
        this.customerDatabaseOperations = new CustomerDatabaseOperations();
        this.employeeDatabaseOperations = new EmployeeDatabaseOperations();
        //this.jdbc=new JDBC();


    }

   public String test(){
       return "server working";
   }



    @Override
    public void addOrder(Order order) throws RemoteException {
        try {
            customerDatabaseOperations.addOrder(order);
            support.firePropertyChange("order",null,order);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addClient(Client client) throws RemoteException {
        clients.add(client);

    }

    @Override
    public void addClientCallBack(Client client) throws RemoteException {
        PropertyChangeListener listener = new PropertyChangeListener() {
            @Override public void propertyChange(PropertyChangeEvent evt) {
                try{
                    System.out.println("server1");
                    Order order = (Order) evt.getNewValue();
                    client.update(order);
                }
                catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        };
        System.out.println("server2");
        listeners.put(client, listener);
        addPCL("order", listener);

    }

    public ArrayList<Order> getOrders() throws Exception {
        ResultSet rs = employeeDatabaseOperations.getAllOrdersResultSet();
        ArrayList<Order> orderList = new ArrayList<>();
        try {
            while(rs.next()) {
                Map<String, Integer> food = convertStringToMap(rs.getString("food_name"));
                orderList.add(new Order(rs.getInt("order_id"), food, rs.getInt("total_price"), rs.getString("order_status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public int updateOrderStatus(int orderId, String newStatus) throws RemoteException {
        return employeeDatabaseOperations.updateOrderStatus(orderId,newStatus);
    }

    public Map<String, Integer> convertStringToMap(String foodString) {
        Map<String, Integer> foodMap = new HashMap<>();
        String[] foods = foodString.split(", ");

        for(String food : foods) {
            String[] foodDetail = food.split(" \\*");
            foodMap.put(foodDetail[0], Integer.parseInt(foodDetail[1]));
        }
        return foodMap;
    }


    @Override
    public List<Client> getClients() throws RemoteException {
        return clients;
    }


    @Override
    public void addPCL(String name, PropertyChangeListener listener) throws RemoteException {
        support.addPropertyChangeListener(name,listener);
    }

    @Override
    public void removePCL(String name, PropertyChangeListener listener) throws RemoteException {
        support.removePropertyChangeListener(name,listener);
    }
}
