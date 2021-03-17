import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentCityGraph {
    private MyReentrantReadWriteLock lock = new MyReentrantReadWriteLock();
    private CityGraph cityGraph;

    public ConcurrentCityGraph(ArrayList<String > cities) {
        this.cityGraph = new CityGraph(cities);
    }

    public ConcurrentCityGraph(ArrayList<ArrayList<Integer>> routes, ArrayList<String > cities) {
        this.cityGraph = new CityGraph(routes, cities);
    }

    public void addCity(String newCity){
        new Thread(()->{
            lock.writeLock().lock();
            cityGraph.addCity(newCity);
            lock.writeLock().unlock();
        }).start();

    }

    public void deleteCity(String toDelete){
        new Thread(()->{
            lock.writeLock().lock();
            cityGraph.deleteCity(toDelete);
            lock.writeLock().unlock();
        }).start();
    }

    public void changeRouteCost(String city1, String city2, int newCost){
        new Thread(()->{
            lock.writeLock().lock();
            cityGraph.changeRouteCost(city1,city2,newCost);
            lock.writeLock().unlock();
        }).start();
    }

    public void deleteRouteCost(String city1, String city2){
        new Thread(()->{
            lock.writeLock().lock();
            cityGraph.deleteRouteCost(city1,city2);
            lock.writeLock().unlock();
        }).start();
    }

    public void findRoute(String city1, String city2){
        new Thread(()->{
            lock.readLock().lock();
            System.out.println(cityGraph.findRoute(city1,city2));
            lock.readLock().unlock();
        }).start();
    }
}
