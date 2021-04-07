import java.util.Arrays;
import java.util.Random;

public class ArrayChanger extends Thread {
    private final int[] array;
    private int currSum = -1;
    private int lowerBound = 0;
    private int upperBound = 10;
    private final CustomCyclicBarrier cyclicBarrier;
    private ArrayChanger[] arrayChangers;

    public ArrayChanger(int[] array, CustomCyclicBarrier cyclicBarrier) {
        this.array = array;
        this.cyclicBarrier = cyclicBarrier;
    }

    public int getCurrSum() {
        return currSum;
    }

    @Override
    public void run() {

        boolean equals = false;

        while (!equals) {

            Random rand = new Random();
            int ind = rand.nextInt(array.length);

            if (rand.nextBoolean())
                if(array[ind] < upperBound)
                 array[ind] += 1;
            else
                if(array[ind] > lowerBound)
                    array[ind] -= 1;

            currSum = 0;

            for (int j : array)
                currSum += j;


            cyclicBarrier.await();

            equals = true;
            for (ArrayChanger curr : arrayChangers)
                if (curr.getCurrSum() != currSum) {
                    equals = false;
                    break;
                }

            cyclicBarrier.await();

        }
        outputArray();
    }

    public void outputArray(){
        System.out.println(Arrays.toString(array) +" " +currSum + " " + this.getId());
    }

    public void setArrayChangers(ArrayChanger[] arrayChangers){
        this.arrayChangers = arrayChangers;
    }
}
