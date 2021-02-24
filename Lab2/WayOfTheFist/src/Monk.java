public class Monk {
    private int chi;
    private boolean monastery;                            // 0 - for Guan-In
                                                          // 1 - for Guan-Yan
    public Monk(int chi, boolean monastery){
        this.chi = chi;
        this.monastery = monastery;
    }

    public int getChi() {
        return chi;
    }

    public boolean isMonastery() {
        return monastery;
    }
    public String getMonastery(){
        if(monastery) return "Guan-Yan";
        else return "Guan-In";
    }
}
