import java.util.ArrayList;
import java.util.*;

public class Main {
    //ArrayList<String> = new ArrayList(Arrays.asList("Херсон","Одесса","Киев","Львов"));
    public static void main(String[] args) {
        ConcurrentCityGraph cityGraph = new ConcurrentCityGraph(new ArrayList<>(Arrays.asList("Херсон","Одесса","Львов","Киев")));
        cityGraph.addCity("Днепр");
        cityGraph.changeRouteCost("Херсон","Одесса",200);
        cityGraph.changeRouteCost("Киев","Одесса",400);
        cityGraph.changeRouteCost("Киев","Днепр",300);
        cityGraph.changeRouteCost("Киев","Львов",400);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cityGraph.findRoute("Херсон","Одесса");
        cityGraph.findRoute("Херсон","Львов");

        return;
    }

}
