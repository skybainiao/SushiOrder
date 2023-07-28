package Server;

import Client.Networking.Client;
import Server.Database.JDBC;
import Server.Model.Movie;

import java.beans.PropertyChangeSupport;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
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

    }

   public String test(){
       return "server working";
   }


}
