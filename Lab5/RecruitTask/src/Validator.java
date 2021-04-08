public class Validator {
    private boolean isEnd = true;
    private int parties = 2;
    private int count = 0;

    public Validator(int parties){
        this.parties = parties;
    }

    public synchronized void send(boolean val){
        if(!val)
            isEnd = false;
    }
    public synchronized boolean get(){
        boolean return_val = isEnd;
        count++;
        if(count==parties) {
            isEnd = true;
            count = 0;
        }
        return return_val;
    }
}
