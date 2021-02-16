import java.util.Random;
public class Forest {
    private int size = 10;
    private boolean[][] forest;
    public Forest(int size){
        this.size = size;
        forest = new boolean[size][size];
        for(int i = 0; i<size; i++)
            for(int j = 0; j<size; j++)
                forest[i][j] = false;
        Random rand = new Random();
        forest[rand.nextInt(size)][rand.nextInt(size)] = true;
    }
}
