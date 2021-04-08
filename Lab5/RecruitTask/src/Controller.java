import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Controller extends Thread{
    private boolean[] recruits;   //false - left, true - right
    private CyclicBarrier cyclicBarrier;
    private Controller left = null;
    private Controller right = null;
    private int id;
    private Validator validator;

    public Controller(boolean[] recruits, CyclicBarrier cyclicBarrier, Validator validator, int id) {
        this.recruits = recruits;
        this.cyclicBarrier = cyclicBarrier;
        this.id = id;
        this.validator = validator;
    }

    public void setLeft(Controller left) {
        this.left = left;
    }

    public void setRight(Controller right) {
        this.right = right;
    }

    public boolean getBeg(){
        return recruits[0];
    }

    public boolean getEnd(){
        return recruits[recruits.length-1];
    }

    private void rotation(){
        boolean hasRotation = true;
        while(hasRotation){
            hasRotation = false;
            for (int i = 1; i < recruits.length - 1; i++) {
                if (recruits[i] && !recruits[i + 1]) {
                    recruits[i] = false;
                    recruits[i + 1] = true;
                    hasRotation = true;
                }
                if (recruits[i - 1] && !recruits[i]) {
                    recruits[i] = true;
                    recruits[i - 1] = false;
                    hasRotation = true;
                }
            }
        }
    }

    @Override
    public void run() {
        rotation();
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        while(true) {
            boolean finish = true;

            if (left != null) {
                if (left.getEnd() && !recruits[0]) {
                    finish = false;
                    recruits[0] = true;
                }
            }
            if (right != null) {
                if (!right.getBeg() && recruits[recruits.length - 1]) {
                    finish = false;
                    recruits[recruits.length - 1] = false;
                }
            }
            rotation();

            validator.send(finish);

            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            finish = validator.get();

            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            if(finish) {
                System.out.println(id + " " + Arrays.toString(recruits));
                break;
            }
        }
    }

}
