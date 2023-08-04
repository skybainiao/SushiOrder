package Client.ViewModel;

import Client.Model.Employee.EmployeeModel;
import Client.Model.ModelFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ViewModelFactory {
    private LoginVM loginVM;
    private HomeVM homepageVM;

    private EmployeeVM employeeVM;

    public ViewModelFactory(ModelFactory modelFactory) throws RemoteException, NotBoundException {

        loginVM=new LoginVM(modelFactory.getLoginModel());
        homepageVM=new HomeVM(modelFactory.getCustomerModel());
        employeeVM=new EmployeeVM(modelFactory.getEmployeeModel());
    }

    public HomeVM getOverViewVM() {
        return homepageVM;
    }
    public LoginVM getLoginVM() {
        return loginVM;
    }

    public EmployeeVM getEmployeeVM() {
        return employeeVM;
    }
}
