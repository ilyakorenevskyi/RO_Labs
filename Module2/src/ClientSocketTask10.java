import java.io.*;
import java.net.Socket;
import com.google.gson.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClientSocketTask10 {
    private Socket socket = null;
    BufferedReader input = null;
    PrintWriter output = null;

    public ClientSocketTask10(String ip, int port) throws IOException {
        socket = new Socket(ip,port);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    public void run(){
        Scanner in = new Scanner(System.in);
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
        int op;
        String str;
        Gson gson = new Gson();
        do{
            op = Integer.parseInt(in.nextLine());
            switch (op){
                case (1):{
                    System.out.println("Enter city in format CityId CityName FoundationYear Area (use name without spaces)");
                    str = in.nextLine();
                    List<String> splited = Arrays.asList(str.split("\\s+"));
                    if(splited.size()<4){
                        System.out.println("Wrong input");
                    }
                    else{
                        City city  = new City();
                        city.setId(Integer.parseInt(splited.get(0)));
                        city.setCityName(splited.get(1));
                        city.setFoundationYear(Integer.parseInt(splited.get(2)));
                        city.setArea(Double.parseDouble(splited.get(3)));
                        output.println(op);
                        output.println(gson.toJson(city));
                        String res= null;
                        try {
                            res = input.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                            res = e.getMessage();
                        }
                        System.out.println(res);
                    }
                    break;
                }
                case (2):{
                    System.out.println("Enter city id and new data in format CityId CityName FoundationYear Area");
                    str = in.nextLine();
                    List<String> splited = Arrays.asList(str.split("\\s+"));
                    if(splited.size()<4){
                        System.out.println("Wrong input");
                    }
                    else{
                        City city  = new City();
                        city.setId(Integer.parseInt(splited.get(0)));
                        city.setCityName(splited.get(1));
                        city.setFoundationYear(Integer.parseInt(splited.get(2)));
                        city.setArea(Double.parseDouble(splited.get(3)));
                        output.println(op);
                        output.println(gson.toJson(city));
                        String res= null;
                        try {
                            res = input.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                            res = e.getMessage();
                        }
                        System.out.println(res);
                    }
                    break;
                }
                case (3):{
                    System.out.println("Enter city name to find data in format CityName ");
                    str = in.nextLine();
                    output.println(op);
                    output.println(str);
                    break;
                }
                case (4):{
                    System.out.println("Enter city id to delete data in format CityId ");
                    int id = in.nextInt();
                    output.println(op);
                    output.println(id);
                    String res= null;
                    try {
                        res = input.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                        res = e.getMessage();
                    }
                    System.out.println(res);
                    break;
                }
                case (5):{
                    System.out.println("Enter citizen type in format TypeId TypeName CityId Language Amount");
                    str = in.nextLine();
                    List<String> splited = Arrays.asList(str.split("\\s+"));
                    if(splited.size()<5){
                        System.out.println("Wrong input");
                    }
                    else{
                        CitizenType ct  = new CitizenType();
                        ct.setId(Integer.parseInt(splited.get(0)));
                        ct.setName(splited.get(1));
                        ct.setCityId(Integer.parseInt(splited.get(2)));
                        ct.setLanguage(splited.get(3));
                        ct.setSize(Integer.parseInt(splited.get(4)));
                        output.println(op);
                        output.println(gson.toJson(splited));
                        String res= null;
                        try {
                            res = input.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                            res = e.getMessage();
                        }
                        System.out.println(res);
                    }
                    break;
                }
                case (6):{
                    System.out.println("Enter citizen type id and new data in format TypeId TypeName CityId Language Amount");
                    str = in.nextLine();
                    List<String> splited = Arrays.asList(str.split("\\s+"));
                    if(splited.size()<5){
                        System.out.println("Wrong input");
                    }
                    else{
                        CitizenType ct  = new CitizenType();
                        ct.setId(Integer.parseInt(splited.get(0)));
                        ct.setName(splited.get(1));
                        ct.setCityId(Integer.parseInt(splited.get(2)));
                        ct.setLanguage(splited.get(3));
                        ct.setSize(Integer.parseInt(splited.get(4)));
                        output.println(op);
                        output.println(gson.toJson(splited));
                        String res= null;
                        try {
                            res = input.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                            res = e.getMessage();
                        }
                        System.out.println(res);
                    }
                    break;
                }
                case (7):{
                    System.out.println("Enter type name to find data in format CitizenTypeName ");
                    str = in.nextLine();
                    output.println(op);
                    output.println(str);
                    break;
                }
                case (8):{
                    System.out.println("Enter citizen type id to delete citizen type data in format CityId ");
                    int id = in.nextInt();
                    output.println(op);
                    output.println(id);
                    String res= null;
                    try {
                        res = input.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                        res = e.getMessage();
                    }
                    System.out.println(res);
                    break;
                }
                case(9):{
                    System.out.println("Enter city name and language ");
                    str = in.nextLine();
                    ArrayList<String> splited = (ArrayList<String>) Arrays.asList(str.split("\\s+"));
                    if(splited.size()<2){
                        System.out.println("Wrong input");
                    }
                    else{
                        output.println(op);
                        output.println(splited.get(0));
                        output.println(splited.get(0));
                    }
                    break;
                }
                case(10):{
                    System.out.println("Enter citizen type name");
                    str = in.nextLine();
                    output.println(op);
                    output.println(str);
                }
                case(11):{
                    output.println(op);
                }
                default:{
                    System.out.println("Unknown operation");
                    break;
                }
            }
        }while (op!=-1);
    }


    public void disconnect() throws IOException {
        output.flush();
        socket.close();
    }


    public static void main(String[] args) {
        try
        {
            ClientSocketTask10 client = new ClientSocketTask10("localhost",12345);
            client.run();
            client.disconnect();
        }
        catch(IOException e)
        {
            System.out.println("Возникла ошибка");
        }
    }
}
