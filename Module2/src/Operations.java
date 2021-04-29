import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Operations extends Remote {
    int createCity(City city) throws RemoteException;
    int updateCity(City city) throws RemoteException;
    List<City> getCitiesByName(String name) throws RemoteException;
    int deleteCity(int id) throws RemoteException;
    int createCitizenType(CitizenType ct) throws RemoteException;
    int updateCitizenType(CitizenType ct) throws RemoteException;
    List<CitizenType> getCitizensTypeByName(String name) throws RemoteException;
    int deleteCitizenType(int id) throws RemoteException;
    List<CitizenType> query1(String cityName, String language) throws RemoteException;
    List<City> query2(String typeName) throws RemoteException;
    List<CitizenType> query3() throws RemoteException;
}