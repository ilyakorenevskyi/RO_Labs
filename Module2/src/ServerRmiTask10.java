import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;




public class ServerRmiTask10 {
    public static void main(String[] args)
            throws RemoteException, MalformedURLException {
        OperationsImpl impl = new OperationsImpl();
        Registry registry = LocateRegistry.createRegistry(12345);
        registry.rebind("Operations",impl);
        System.out.println("Server started!");
    }

}


