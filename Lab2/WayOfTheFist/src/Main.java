import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        Monk[] monks = new Monk[20];
        for(int i = 0; i < 20; i++){
            int chi = rand.nextInt(100);
            boolean mon = rand.nextBoolean();
            monks[i] = new Monk(chi,mon);
        }
        ForkJoinPool fjp = new ForkJoinPool();
        Tournament tournament = new Tournament(monks, 0,9);
        Monk res = fjp.invoke(tournament);
        System.out.println("Monk from " + res.getMonastery() + " with Chi Energy " + res.getChi() );
        return;
    }
}
