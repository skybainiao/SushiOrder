package Client.View;

import Client.ViewModel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ViewHandler {
    private ViewModelFactory viewModelFactory;
    private Stage mainStage;


    public ViewHandler(ViewModelFactory viewModelFactory)  {
        this.viewModelFactory=viewModelFactory;
        this.mainStage=new Stage();
        mainStage.setWidth(900);
        mainStage.setHeight(700);

    }


    private Scene overViewScene;
    public void openOverView(){
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("HomepageController.fxml"));
            Parent root=loader.load();
            HomepageController homepageController =loader.getController();
            homepageController.init(viewModelFactory.getOverViewVM(),this);
            mainStage.setTitle("OverView");
            overViewScene=new Scene(root);
        }
        catch (IOException | SQLException e){
            e.printStackTrace();
        }
        mainStage.setScene(overViewScene);
        mainStage.show();
    }

    private Scene employeeScene;
    public void openEmployeeView(){
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("EmployeeView.fxml"));
            Parent root=loader.load();
            EmployeeView employeeView =loader.getController();
            employeeView.initialize(viewModelFactory.getEmployeeVM(),this);
            mainStage.setTitle("OverView");
            overViewScene=new Scene(root);
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        mainStage.setScene(overViewScene);
        mainStage.show();
    }




}
