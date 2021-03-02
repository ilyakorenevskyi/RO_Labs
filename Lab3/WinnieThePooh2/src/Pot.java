public class Pot {
    private int potMax;
    private int potCurr;
    public Pot(int potMax){
        this.potMax = potMax;
    }
    public void fillPot(){
        potCurr++;
    }
    public void clearPot(){
        potCurr = 0;
    }

    public int getPotMax() {
        return potMax;
    }

    public int getPotCurr() {
        return potCurr;
    }
}
