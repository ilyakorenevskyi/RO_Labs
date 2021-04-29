import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocketTask10 {
        private ServerSocket serverSocket = null;
        private ExecutorService executorService = Executors.newFixedThreadPool(10);


        public void start(int port) throws IOException
        {
            serverSocket = new ServerSocket(port);
            System.out.println("Waiting for request");
            while(true) {
                try {
                    Socket s = serverSocket.accept();
                    System.out.println("Processing request");
                    executorService.submit(new ServiceRequest(s));
                }
                catch (IOException io){
                    io.printStackTrace();
                }
            }
        }

        public static void main(String[] args)
        {
            try
            {
                ServerSocketTask10 srv = new ServerSocketTask10();
                srv.start(12345);
            }
            catch(IOException e)
            {
                System.out.println("Возникла ошибка");
            }
        }

}

class ServiceRequest implements Runnable {

    private final Socket clientSocket;

    public ServiceRequest(Socket connection) {
        this.clientSocket = connection;
    }

    public void run() {
        try {
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            int op = -2;
            System.out.println("I'm here");
            while(op!=-1){

                op = Integer.parseInt(input.readLine());
                System.out.println(op);
            switch (op) {
                case (1): {
                    String json = input.readLine();
                    Gson gson = new Gson();
                    City city = gson.fromJson(json, City.class);

                    if (DAOTask10.createCity(city) == 1) {
                        output.write("Success");
                    } else {
                        output.write("Error");
                    }
                    break;
                }
                case (2): {
                    String json = input.readLine();
                    Gson gson = new Gson();
                    City city = gson.fromJson(json, City.class);
                    if (DAOTask10.updateCity(city) == 1) {
                        output.write("Success");
                    } else {
                        output.write("Error");
                    }
                    break;
                }
                case (3): {
                    String name = input.readLine();
                    List<City> cities = DAOTask10.readCitiesByName(name);
                    Gson gson = new Gson();
                    String json = gson.toJson(cities);
                    output.write(json);
                    break;
                }
                case (4): {
                    int id = Integer.parseInt(input.readLine());
                    if (DAOTask10.deleteCity(id) == 1) {
                        output.write("Success");
                    } else {
                        output.write("Error");
                    }
                    break;
                }
                case (5): {
                    String json = input.readLine();
                    Gson gson = new Gson();
                    CitizenType ct = gson.fromJson(json, CitizenType.class);
                    if (DAOTask10.createCitizenType(ct) == 1) {
                        output.write("Success");
                    } else {
                        output.write("Error");
                    }
                    break;
                }
                case (6): {
                    String json = input.readLine();
                    Gson gson = new Gson();
                    CitizenType ct = gson.fromJson(json, CitizenType.class);
                    if (DAOTask10.updateCitizenType(ct) == 1) {
                        output.write("Success");
                    } else {
                        output.write("Error");
                    }
                    break;
                }
                case (7): {
                    String name = input.readLine();
                    List<CitizenType> cts = DAOTask10.readCitizenTypeByName(name);
                    Gson gson = new Gson();
                    String json = gson.toJson(cts);
                    output.write(json);
                    break;

                }
                case (8): {
                    int id = Integer.parseInt(input.readLine());
                    if (DAOTask10.deleteCitizenType(id) == 1) {
                        output.write("Success");
                    } else {
                        output.write("Error");
                    }
                    break;
                }
                case (9): {
                    String name = input.readLine();
                    String language = input.readLine();
                    List<CitizenType> cts = DAOTask10.query1(name,language);
                    Gson gson = new Gson();
                    String json = gson.toJson(cts);
                    output.write(json);
                    break;
                }
                case (10): {
                    String name = input.readLine();
                    List<City> cts = DAOTask10.query2(name);
                    Gson gson = new Gson();
                    String json = gson.toJson(cts);
                    output.write(json);
                    break;
                }
                case (11): {
                    List<CitizenType> cts = DAOTask10.query3();
                    Gson gson = new Gson();
                    String json = gson.toJson(cts);
                    output.write(json);
                    break;
                }
                default: {
                    break;
                }
            }
            }
            clientSocket.close();
        }catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

