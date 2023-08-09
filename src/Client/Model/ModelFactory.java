package Client.Model;

import Client.Model.Customer.CustomerImpl;
import Client.Model.Customer.CustomerModel;
import Client.Model.Employee.EmployeeImpl;
import Client.Model.Employee.EmployeeModel;

import Client.Networking.Client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ModelFactory {



    private CustomerModel customerModel;

    private EmployeeModel employeeModel;
    private Client client;

    public ModelFactory(Client client){
        this.client=client;
    }



    public CustomerModel getCustomerModel() throws RemoteException, NotBoundException {
        if (customerModel==null){
            customerModel=new CustomerImpl(client);
        }
        return customerModel;
    }

    public EmployeeModel getEmployeeModel() throws RemoteException, NotBoundException {
        if (employeeModel==null){
            employeeModel=new EmployeeImpl(client);
        }
        return employeeModel;
    }
}
