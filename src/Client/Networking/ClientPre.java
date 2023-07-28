package Client.Networking;

import javafx.application.Application;
import javafx.stage.Stage;

public class ClientPre extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Client client=new ClientImpl("Chen");
    }
}
