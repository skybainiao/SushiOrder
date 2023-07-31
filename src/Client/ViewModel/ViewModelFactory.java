package Client.ViewModel;

import Client.Model.ModelFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ViewModelFactory {
    private LoginVM loginVM;
    private HomeVM homepageVM;

    public ViewModelFactory(ModelFactory modelFactory) throws RemoteException, NotBoundException {

        loginVM=new LoginVM(modelFactory.getLoginModel());

    }

    public HomeVM getOverViewVM() {
        return homepageVM;
    }
    public LoginVM getLoginVM() {
        return loginVM;
    }
}
