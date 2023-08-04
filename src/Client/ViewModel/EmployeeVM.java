package Client.ViewModel;

import Client.Model.Customer.CustomerModel;
import Client.Model.Employee.EmployeeModel;

import java.beans.PropertyChangeSupport;

public class EmployeeVM {

    private EmployeeModel model;
    private PropertyChangeSupport support;

    public EmployeeVM(EmployeeModel model){
        this.model=model;
        support=new PropertyChangeSupport(this);
    }
}
