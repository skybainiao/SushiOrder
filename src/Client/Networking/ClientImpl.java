package Client.Networking;

import Server.Model.User;
import Server.Server;

import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class ClientImpl implements Client{

    private String name;
    private Server server;
    private User user;
    private PropertyChangeSupport support;


    public ClientImpl(String name) throws RemoteException, NotBoundException, SQLException {
        this.name=name;
        support=new PropertyChangeSupport(this);
        UnicastRemoteObject.exportObject(this, 0);
        Registry registry = LocateRegistry.getRegistry("localhost", 6666);
        server = (Server) registry.lookup("Server");
        user=new User("","");
        test();
    }

    public void test() throws SQLException, RemoteException {
        System.out.println("client:"+server.test());
    }


}
