package Client.View;

import Client.ViewModel.LoginVM;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LoginController {
    private LoginVM loginVM;
    private ViewHandler viewHandler;

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private RadioButton isCustomer;
    @FXML
    private RadioButton isAdministrator;


    public void init(LoginVM loginVM,ViewHandler viewHandler){
        this.loginVM=loginVM;
        this.viewHandler=viewHandler;
        usernameTextField.textProperty().bindBidirectional(loginVM.getUsername());
        passwordTextField.textProperty().bindBidirectional(loginVM.getPassword());
        isCustomer.selectedProperty().bindBidirectional(loginVM.getIsCustomer());
        isAdministrator.selectedProperty().bindBidirectional(loginVM.getIsAdmin());

        ToggleGroup toggleGroup=new ToggleGroup();
        isCustomer.setToggleGroup(toggleGroup);
        isAdministrator.setToggleGroup(toggleGroup);
    }

    public void setUsername() throws NotBoundException, RemoteException {
        loginVM.setClientName();
    }



}
