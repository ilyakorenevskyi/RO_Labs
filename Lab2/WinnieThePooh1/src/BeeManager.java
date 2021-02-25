import java.util.Random;
public class BeeManager {
    private int forestSize = 10;
    private boolean[][] forest;
    private int segmentsNumber = 5;
    private int teamsNumber = 3;
    private boolean found = false;
    public int freeBees = 0;

    public BeeManager(int size,int segmentsNumber){
        this.forestSize = size;
        this.segmentsNumber = segmentsNumber;
        forest = new boolean[size][size];
        for(int i = 0; i<size; i++)
            for(int j = 0; j<size; j++)
                forest[i][j] = false;
        Random rand = new Random();
        int bearX = rand.nextInt(size);
        int bearY = rand.nextInt(size);
        forest[bearX][bearY] = true;
        System.out.println("Bear is in " + bearX + " " + bearY);
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
            new Bees(i*forestSize/segmentsNumber, (i+1)*forestSize/segmentsNumber, this).start();
        }
        int currSegment = teamsNumber;
        while(true){
            synchronized (this){
                if(!found && (freeBees == 0)){
                    this.wait();
                }
                if(!found)
                    while (freeBees!=0 && currSegment<segmentsNumber){
                        new Bees(currSegment*forestSize/segmentsNumber, (++currSegment*forestSize/segmentsNumber), this).start();
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
