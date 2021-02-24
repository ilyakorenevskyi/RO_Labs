import java.util.concurrent.RecursiveTask;

public class Tournament extends  RecursiveTask<Monk>{
    private Monk monks[];
    private int beg, end;
    public Tournament(Monk monks[], int beg, int end){
        this.beg = beg;
        this.end = end;
        this.monks = monks;
    }
    @Override
    protected Monk compute() {
        if(end-beg == 0){
            //System.out.printf(monks[beg].getChi() + " returned \n");
            return monks[beg];
        }
        int mid  = (beg+end)/2;
        Tournament subTourn1 = new Tournament(monks,beg,mid);
        Tournament subTourn2 = new Tournament(monks,mid+1, end);
        subTourn1.fork();
        subTourn2.fork();
        Monk m1 = subTourn1.join();
        Monk m2 = subTourn2.join();
        //System.out.printf(m1.getChi() + " " + m2.getChi() + " goonna fight \n ");
        return fight(m1, m2);
    }

    private Monk fight(Monk a, Monk b) {
        if(a.getChi() > b.getChi()) {
            //System.out.printf(a.getChi() + " won " + b.getChi() + " loose \n");
            return a;
        }
        else {
            //System.out.printf(b.getChi() + " won " + a.getChi() + " loose \n" );
            return b;
        }
    }
}
