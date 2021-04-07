import java.util.Arrays;
import java.util.Random;

public class Manager {
    public static void main(String[] args) throws InterruptedException {
        int[] array = new int[12];
        Random random = new Random();

        for(int i = 0; i < array.length; i++)
            array[i] = random.nextInt(4);

        CustomCyclicBarrier cyclicBarrier = new CustomCyclicBarrier(3);
        ArrayChanger[] arrayChangers = new ArrayChanger[3];

        for(int i = 0; i < arrayChangers.length; i++)
            arrayChangers[i] =  new ArrayChanger(Arrays.copyOfRange(array, i*4, (i+1)*4), cyclicBarrier);

        arrayChangers[0].setArrayChangers(new ArrayChanger[]{arrayChangers[1], arrayChangers[2]});
        arrayChangers[1].setArrayChangers(new ArrayChanger[]{arrayChangers[0], arrayChangers[2]});
        arrayChangers[2].setArrayChangers(new ArrayChanger[]{arrayChangers[1], arrayChangers[0]});

        for(ArrayChanger arrayChanger : arrayChangers) {
            arrayChanger.outputArray();
            arrayChanger.start();
        }

        arrayChangers[0].join();
    }
}
