import java.util.Random;
public class BeeManager {
    private int forestSize = 10;
    private boolean[][] forest;
    private int segmentsNumber = 5;
    private int teamsNumber = 3;
    private boolean found = false;
    private Object syncObj = new Object();
    public int freeBees = 0;

    public BeeManager(int size){
        this.forestSize = size;
        forest = new boolean[size][size];
        for(int i = 0; i<size; i++)
            for(int j = 0; j<size; j++)
                forest[i][j] = false;
        Random rand = new Random();
        forest[rand.nextInt(size)][rand.nextInt(size)] = true;
    }

    public boolean[][] getForest() {
        return forest;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public int getForestSize() {
        return forestSize;
    }
    public void findBear() throws InterruptedException {
        for(int i = 0; i<teamsNumber;i++){
            new Bees(i*forestSize/segmentsNumber, (i+1)*forestSize/segmentsNumber, this, syncObj).start();
        }
        int currSegment = teamsNumber;
        while(true){
            synchronized (syncObj){
                if(!found && (freeBees == 0)){
                    syncObj.wait();
                }
                if(!found)
                    while (freeBees!=0 && currSegment<segmentsNumber){
                        new Bees(currSegment*forestSize/segmentsNumber, (++currSegment*forestSize/segmentsNumber), this, syncObj).start();
                        freeBees--;
                    }
                else {
                    System.out.println("Bear was found");
                    break;
                }
            }
        }

    }
}
