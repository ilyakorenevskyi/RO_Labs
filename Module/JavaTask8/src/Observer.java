import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Observer extends Thread {

    private Cashbox[] cashboxes;
    private Cashbox storage;
    private int size;
    private int max_limit = 10000;
    private int min_limit = 100;

    public Observer(Cashbox[] cashboxes, Cashbox storage) {
        this.cashboxes = new Cashbox[cashboxes.length];
        this.storage = storage;
        this.cashboxes = cashboxes;
        this.size = cashboxes.length;
    }

    @Override
    public void run() {
        while(true){
            for(int i = 0; i<size ;i++){
                cashboxes[i].lock.writeLock().lock();
                if(cashboxes[i].getCash() > max_limit){
                    storage.addCash( 500);
                    cashboxes[i].takeCash(500);
                }
                else if(cashboxes[i].getCash() < min_limit){
                    storage.takeCash(300);
                    cashboxes[i].addCash(300);
                }
                cashboxes[i].lock.writeLock().unlock();
            }
        }
    }
}
