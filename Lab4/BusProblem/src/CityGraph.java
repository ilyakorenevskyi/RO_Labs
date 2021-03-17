import java.util.*;

public class CityGraph {
    private ArrayList<ArrayList<Integer>> routes;
    private ArrayList<String> cities;
    private int size;
    //private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public CityGraph(ArrayList<String > cities) {
        this.cities = cities;
        this.routes = new ArrayList<>();
        for(int i = 0;i <cities.size();i++){
            routes.add(new ArrayList<Integer>(Collections.nCopies(cities.size(), null)));

        }
    }

    public CityGraph(ArrayList<ArrayList<Integer>> routes, ArrayList<String > cities) {
        this.routes = routes;
        this.cities = cities;
    }

    public void addCity(String newCity){
        cities.add(newCity);
        int prev_size = routes.size();
        routes.add(new ArrayList<Integer>(Collections.nCopies(cities.size(), null)));
        for (int i = 0;i<routes.size()-1;i++) {
            routes.get(i).add(null);
        }
    }

    public boolean deleteCity(String toDelete){
        int toDeleteInd = -1;
        for(int i = 0;i<cities.size();i++ ){
            if(cities.get(i).equals(toDelete)){
                toDeleteInd = i;
            }
        }
        if(toDeleteInd<0){
            return false;
        }
        cities.remove(toDeleteInd);
        routes.remove(toDeleteInd);
        for (ArrayList<Integer> rout : routes) {
            rout.remove(toDeleteInd);
        }
        return true;
    }

    public boolean changeRouteCost(String city1, String city2, int newCost){
        int ind1 = -1;
        int ind2 = -1;
        for(int i = 0;i<cities.size();i++ ){
            if(cities.get(i).equals(city1)){
                ind1 = i;
            }
            if(cities.get(i).equals(city2)){
                ind2 = i;
            }
        }
        if(ind1 < 0||ind2 < 0){
            return false;
        }
        (routes.get(ind1)).set(ind2, newCost);
        (routes.get(ind2)).set(ind1, newCost);
        return true;
    }

    public boolean deleteRouteCost(String city1, String city2){
        int ind1 = -1;
        int ind2 = -1;
        for(int i = 0;i<cities.size();i++ ){
            if(cities.get(i).equals(city1)){
                ind1 = i;
            }
            if(cities.get(i).equals(city2)){
                ind2 = i;
            }
        }
        if(ind1 < 0||ind2 < 0){
            return false;
        }
        (routes.get(ind1)).set(ind2, null);
        (routes.get(ind2)).set(ind1, null);
        return true;
    }

    public int findRoute(String city1, String city2){
        int ind1 = -1;
        int ind2 = -1;
        for(int i = 0;i<cities.size();i++ ){
            if(cities.get(i).equals(city1)){
                ind1 = i;
            }
            if(cities.get(i).equals(city2)){
                ind2 = i;
            }
        }
        if(ind1 < 0||ind2 < 0){
            return -1;
        }
        if((routes.get(ind1)).get(ind2) != null){
            return (routes.get(ind1)).get(ind2);
        }
        else{
            return routeSearch(ind1,ind2);
        }

    }

    private int routeSearch(int city1, int city2){
        boolean[] visited = new boolean[cities.size()];
        for(int i = 0; i<visited.length; i++){
            visited[i] = false;
        }
        Stack<Integer> currPath = new Stack<>();
        Integer[] result;
        result = DFS(city1,city2,visited,currPath);
        if(result == null) return -1;
        int totalCost = 0;
        for(int i =0; i<result.length-1;i++){
            totalCost += (routes.get(result[i])).get(result[i+1]);
        }
        return totalCost;
    }

    private Integer[] DFS(int u, int v, boolean[] visited, Stack<Integer> currPath) {
        if(visited[u]){
            return  null;
        }
        visited[u] = true;
        currPath.push(u);
        if(u==v){
            Integer[] result = new Integer[currPath.size()];
            result = currPath.toArray(result);
            currPath.pop();
            return result;
        }
        for(int i = 0;i<routes.size();i++){
            if((routes.get(u)).get(i) != null){
               Integer[] result =  DFS(i,v,visited,currPath);
               if(result!=null){
                   return result;
               }
            }
        }
        currPath.pop();
        visited[u] = false;
        return null;
    }


}
