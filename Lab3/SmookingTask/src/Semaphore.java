import java.util.concurrent.atomic.AtomicInteger;

public class Semaphore {
    private int maxPermits = 1;
    private int permits = 0;
    public Semaphore(){

    }
    public Semaphore(int permits){
        if(permits>0) {
            this.maxPermits = permits;
        }
    }

    public synchronized void acquire() throws InterruptedException {
        while(permits==maxPermits){
            this.wait();
        }
        permits++;
    }

    public synchronized void release(){
        permits--;
        this.notify();
    }
}
