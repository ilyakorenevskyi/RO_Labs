import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

class OperationsImpl extends UnicastRemoteObject implements Operations{

    protected OperationsImpl() throws RemoteException {
    }

    @Override
    public int createCity(City city) throws RemoteException{
        try {
            return  DAOTask10.createCity(city);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateCity(City city) throws RemoteException {
        try {
            return  DAOTask10.updateCity(city);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<City> getCitiesByName(String name) throws RemoteException {
        try {
            return  DAOTask10.readCitiesByName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteCity(int id) throws RemoteException {
        try {
            return  DAOTask10.deleteCity(id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int createCitizenType(CitizenType ct) throws RemoteException {
        try {
            return  DAOTask10.createCitizenType(ct);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateCitizenType(CitizenType ct) throws RemoteException {
        try {
            return  DAOTask10.updateCitizenType(ct);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<CitizenType> getCitizensTypeByName(String name) throws RemoteException {
        try {
            return  DAOTask10.readCitizenTypeByName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteCitizenType(int id) throws RemoteException {
        try {
            return  DAOTask10.deleteCity(id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<CitizenType> query1(String cityName, String language) throws RemoteException {
        try {
            return  DAOTask10.query1(cityName,language);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<City> query2(String typeName) throws RemoteException {
        try {
            return  DAOTask10.query2(typeName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CitizenType> query3() throws RemoteException {
        try {
            return  DAOTask10.query3();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}