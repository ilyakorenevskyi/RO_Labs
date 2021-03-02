import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Bear extends Thread {
    private Semaphore semaphore;
    private Pot pot;
    private CyclicBarrier cyclicBarrier;
    public Bear(Semaphore semaphore, Pot pot, CyclicBarrier cyclicBarrier){
        this.semaphore = semaphore;
        this.pot = pot;
        this.cyclicBarrier = cyclicBarrier;
    }
    @Override
    public void run() {
        while(true){
            try {
                System.out.println("The bear is sleeping");
                cyclicBarrier.await();
                System.out.println("The bear is awake");
                semaphore.acquire();
                pot.clearPot();
                System.out.println("The bear drank honey and went to bed");
                semaphore.release();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
