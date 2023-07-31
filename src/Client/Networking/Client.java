package Client.Networking;

import Server.Model.User;
import Server.PCS;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Client  extends Remote, PCS {
    void addUser(User user) throws RemoteException;
    ArrayList<User> getUsers() throws Exception;
    void setClientName(String name) throws RemoteException;
    String getClientName() throws RemoteException;

}
