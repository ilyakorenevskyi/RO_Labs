import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cashbox {
    private int cash;
    public ReentrantReadWriteLock lock;

    public int takeCash(int amount){

        if(cash>amount){
            cash -= amount;
            return amount;
        }
        else{
            cash = 0;
            return cash;
        }

    }

    public void addCash(int amount){

        cash += amount;

    }

    public int getCash(){

        return cash;

    }
}
