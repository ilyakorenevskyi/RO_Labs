import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Forest {
    private Pot pot;
    private int bees;
    Semaphore semaphore;
    public Forest(int potMax, int bees){
        this.bees = bees;
        this.pot = new Pot(potMax);
        semaphore = new Semaphore(1);
    }
    public void simulate(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        (new Bear(semaphore,pot,cyclicBarrier)).start();
        for(int i = 0; i<bees;i++){

            (new Bee(semaphore,pot,cyclicBarrier)).start();
        }

    }
}
