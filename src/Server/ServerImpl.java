package Server;

import Client.Networking.Client;
import Server.Database.JDBC;
import Server.Model.Movie;
import Server.Model.User;

import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerImpl implements Server{

    private Movie movie;
    private JDBC jdbc;
    private ArrayList<Client> clients;
    private PropertyChangeSupport support;

    public ServerImpl() throws Exception {
        Registry registry= LocateRegistry.createRegistry(6666);
        registry.bind("Server",this);
        UnicastRemoteObject.exportObject(this,6666);

        System.out.println("Server Start");
        test();
        clients=new ArrayList<>();
        support=new PropertyChangeSupport(this);
        this.jdbc=new JDBC();
        movie=new Movie("",0,"","","","","",0,0);

        //addUser(new User("chen","123321"));
        System.out.println(getUsers().toString());
    }

   public String test(){
       return "server working";
   }

    @Override
    public void addUser(User user) throws RemoteException {
        try {
            jdbc.addUser(user);
            support.firePropertyChange("user",user,user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> getUsers() throws Exception {
        ResultSet rs = jdbc.getAllUsers();
        ArrayList<User> userList =new ArrayList<>();

        try{
            while(rs.next()){
                userList.add(new User(rs.getString("username"),rs.getString("password")));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return userList;
    }


}
