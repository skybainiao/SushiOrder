package Client.Model;

import Client.Model.Login.AvailableState;
import Client.Model.Login.LoginState;
import Client.Networking.Client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ModelFactory {

    private LoginState loginState;
    private Client client;

    public ModelFactory(Client client){
        this.client=client;
    }

    public LoginState getLoginModel() throws RemoteException, NotBoundException {
        if (loginState ==null){
            loginState =new AvailableState(client);
        }
        return loginState;
    }
}
