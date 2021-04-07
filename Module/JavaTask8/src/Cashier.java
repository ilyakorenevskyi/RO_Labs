import java.util.Queue;
public class Cashier extends Thread{

    private Cashbox cashbox;
    private Queue<Client> clientQueue;

    @Override
    public void run() {
        while(true){

        }
    }

    public void addClient(Client client){
        clientQueue.add(client);
    }
}
