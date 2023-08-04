package Server;

import Client.Networking.Client;
import Server.Database.JDBC;

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


    private JDBC jdbc;
    private List<Client> clients;
    private Map<Client, PropertyChangeListener> listeners = new HashMap<>();


    public ServerImpl() throws Exception {
        Registry registry= LocateRegistry.createRegistry(6666);
        registry.bind("Server",this);
        UnicastRemoteObject.exportObject(this,6666);

        System.out.println("Server Start");

        clients=new ArrayList<>();
        this.jdbc=new JDBC();


        //addUser(new User("chen","123321"));
        System.out.println(getUsers().toString());
    }

   public String test(){
       return "server working";
   }

    @Override
    public void addUser(User user) throws RemoteException {
        try {
            jdbc.addUser(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> getUsers() throws Exception {
        ResultSet rs = jdbc.getAllUsers();
        ArrayList<User> userList =new ArrayList<>();

        try{
            while(rs.next()){
                userList.add(new User(rs.getString("username"),rs.getString("password")));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void addOrder(Order order) throws RemoteException {
        try {
            jdbc.addOrder(order);
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
