public class CustomCyclicBarrier {
    private int currentThreads;
    private int initialThreads;
    private Runnable endEvent;
    public CustomCyclicBarrier(int initialThreads){
        this.initialThreads = initialThreads;
        currentThreads = initialThreads;
        endEvent = null;
    }
    public CustomCyclicBarrier(int initialThreads, Runnable endEvent){
        this.initialThreads = initialThreads;
        currentThreads = initialThreads;
        this.endEvent = endEvent;
    }
    public synchronized void await(){
        currentThreads--;
        if(currentThreads>0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else{
            currentThreads = initialThreads;
            notifyAll();
            if(endEvent!=null){
                endEvent.run();
            }
        }
    }
}
