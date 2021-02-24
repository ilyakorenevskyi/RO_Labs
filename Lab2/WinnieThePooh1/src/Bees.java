public class Bees extends Thread {
    private boolean isFound;
    private int startI;
    private int endI;
    private volatile BeeManager manager;
    private Object syncObj;
    private boolean found = false;
    public Bees(int startI, int endI, BeeManager forest, Object syncObj){
        this.startI = startI;
        this.endI= endI;
        this.manager = forest;
        this.syncObj = syncObj;
    }
    @Override
    public void run(){
        for(int i = startI; i < endI; i++){
            for(int j = 0; j < manager.getForestSize();j++){
                if(manager.getForest()[i][j]){
                    isFound = true;
                    System.out.println("Found in " + i + " " + j);
                }
            }
        }
        synchronized (syncObj) {
            if(isFound)
                manager.setFound(true);
            syncObj.notify();
            manager.freeBees++;
        }
    }
}
