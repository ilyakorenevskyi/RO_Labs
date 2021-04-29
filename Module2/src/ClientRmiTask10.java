
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClientRmiTask10 {
    public static void main(String[] args)
            throws NotBoundException, RemoteException,
            MalformedURLException {
        String url = "//localhost:12345/Operations";
        Operations Q = (Operations) Naming.lookup(url);
        System.out.println("RMI object found");
        System.out.println("1 - Create city \n" +
                "2 - Update city \n" +
                "3 - Read city by name \n"+
                "4 - Delete city by id\n" +
                "5 - Create citizen type\n"+
                "6 - Update citizen type\n"+
                "7 - Read citizen type by name\n"+
                "8 - Delete citizen type by id\n"+
                "9 - Query 1\n" +
                "10 - Query 2\n" +
                "11 - Query 3\n");
        Scanner in = new Scanner(System.in);
        int op;
        String str;

        do {
            op = in.nextInt();
            switch (op) {
                case (1): {
                    System.out.println("Enter city in format CityId CityName FoundationYear Area (use name without spaces)");
                    in.nextLine();
                    str = in.nextLine();
                    List<String> splited =  Arrays.asList(str.split(" "));
                    if (splited.size() < 4) {
                        System.out.println("Wrong input");
                    } else {
                        City city = new City();
                        city.setId(Integer.parseInt(splited.get(0)));
                        city.setCityName(splited.get(1));
                        city.setFoundationYear(Integer.parseInt(splited.get(2)));
                        city.setArea(Double.parseDouble(splited.get(3)));
                        if (Q.createCity(city) != 1) {
                            System.out.println("Error");
                        } else {
                            System.out.println("Success");
                        }
                    }
                    break;
                }
                case (2): {
                    System.out.println("Enter city id and new data in format CityId CityName FoundationYear Area");
                    in.nextLine();
                    str = in.nextLine();
                    List<String> splited = Arrays.asList(str.split(" "));
                    if (splited.size() < 4) {
                        System.out.println("Wrong input");
                    } else {
                        City city = new City();
                        city.setId(Integer.parseInt(splited.get(0)));
                        city.setCityName(splited.get(1));
                        city.setFoundationYear(Integer.parseInt(splited.get(2)));
                        city.setArea(Double.parseDouble(splited.get(3)));
                        if (Q.updateCity(city) != 1) {
                            System.out.println("Error");
                        } else {
                            System.out.println("Success");
                        }
                    }
                    break;
                }
                case (3): {
                    System.out.println("Enter city name to find data in format CityName ");
                    in.nextLine();
                    str = in.nextLine();
                    ArrayList<City> cities = (ArrayList<City>) Q.getCitiesByName(str);
                    if (cities != null) {
                        for (City city : cities) {
                            System.out.println(cities.toString());
                        }
                    } else {
                        System.out.println("Error");
                    }
                    break;
                }
                default:{
                    System.out.println("Unsupported operation");
                    break;
                }
            }

        }while (op != -1);
    }
}
