public class Bees extends Thread {
    private int startI;
    private int endI;
    private volatile Forest forest;
    private Object syncObj;
    private boolean found = false;
    public Bees(int startI, int endI, Forest forest, Object syncObj){
        this.startI = startI;
        this.endI= endI;
        this.forest = forest;
        this.syncObj = syncObj;
    }
    @Override
    public void run(){
        for(int i = startI; i < endI; i++){
            for(int j = 0; j < forest.getForestSize();j++){
                if(forest.getForest()[i][j]){
                    forest.setFound(true);
                    System.out.println("Found in " + i + " " + j);
                }
            }
        }
        synchronized (syncObj) {
            syncObj.notify();
            forest.freeBees++;
        }
    }
}
