package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface Server extends Remote,PCS {
    String test() throws RemoteException, SQLException;
}
