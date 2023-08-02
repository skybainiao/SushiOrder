package Client.Model;

import Client.Model.Customer.CustomerImpl;
import Client.Model.Customer.CustomerModel;
import Client.Model.Login.AvailableState;
import Client.Model.Login.LoginState;
import Client.Networking.Client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ModelFactory {

    private LoginState loginState;

    private CustomerModel customerModel;
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

    public CustomerModel getCustomerModel() throws RemoteException, NotBoundException {
        if (customerModel==null){
            customerModel=new CustomerImpl(client);
        }
        return customerModel;
    }
}
