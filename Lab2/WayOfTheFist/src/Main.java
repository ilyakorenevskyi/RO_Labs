import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Monk[] monks = new Monk[10];
        for(int i = 0; i<10; i++){
            int chi = scanner.nextInt();
            boolean mon = scanner.nextBoolean();
            monks[i] = new Monk(chi,mon);
        }
        ForkJoinPool fjp = new ForkJoinPool();
        Tournament tournament = new Tournament(monks, 0,9);
        Monk res = fjp.invoke(tournament);
        System.out.println("Monk from " + res.getMonastery() + " with Chi Energy " + res.getChi() );
    }
}
