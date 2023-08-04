package Client.Model.Employee;

import Client.Networking.Client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class EmployeeImpl implements EmployeeModel{

    private Client client;


    public EmployeeImpl(Client client) throws RemoteException, NotBoundException {
        this.client=client;
    }


}
