import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.CyclicBarrier;
public class Bee extends Thread {
    private static int beeCount = 0;
    private int beeNum;
    private Semaphore semaphore;
    private Pot pot;
    private CyclicBarrier cyclicBarrier;
    public Bee(Semaphore semaphore, Pot pot, CyclicBarrier cyclicBarrier){
        this.semaphore = semaphore;
        this.pot = pot;
        this.cyclicBarrier = cyclicBarrier;
        this.beeNum = beeCount;
        beeCount++;
    }
    @Override
    public void run() {
        while(true){
            try {
                semaphore.acquire();
                if(pot.getPotCurr()< pot.getPotMax()) {
                    pot.fillPot();
                    Thread.sleep(100);
                    System.out.println("Pot is currently filled " + pot.getPotCurr() + "/" + pot.getPotMax() + " by bee #" + beeNum);
                    if(pot.getPotCurr()== pot.getPotMax()){
                        cyclicBarrier.await();
                    }
                }
                semaphore.release();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }


}
