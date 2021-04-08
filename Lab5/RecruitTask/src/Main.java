import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        Validator validator = new Validator(2);
        boolean[] recruits = new boolean[100];
        for(int i = 0; i<recruits.length;i++){
            recruits[i] = rand.nextBoolean();
        }

        Controller controller1 = new Controller(Arrays.copyOfRange(recruits,0,50),cyclicBarrier, validator, 1);
        Controller controller2 = new Controller(Arrays.copyOfRange(recruits,50,100),cyclicBarrier, validator, 2);
        controller1.setRight(controller2);
        controller2.setLeft(controller1);

        controller1.start();
        controller2.start();

        Thread.sleep(2000);
        return;
    }
}
