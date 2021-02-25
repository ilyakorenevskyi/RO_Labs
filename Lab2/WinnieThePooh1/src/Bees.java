public class Bees extends Thread {
    private boolean isFound = false;
    private int startI;
    private int endI;
    private volatile BeeManager manager;
    public Bees(int startI, int endI, BeeManager forest){
        this.startI = startI;
        this.endI= endI;
        this.manager = forest;
    }
    @Override
    public void run(){
        for(int i = startI; i < endI; i++){
            for(int j = 0; j < manager.getForestSize();j++){
                if(manager.getForest()[i][j]){
                    isFound = true;
                }
            }
        }
        synchronized (manager) {
            if(isFound) {
                manager.setFound(true);
                System.out.println("Found in rows form " + startI + " to " + endI);
            }
            manager.notify();
            manager.freeBees++;
        }
    }
}
