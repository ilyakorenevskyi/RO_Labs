import javax.xml.namespace.QName;
import java.sql.*;
import java.util.*;

public class DAOTask10 {

    public static City getCityFromRS(ResultSet rs) throws SQLException {
        City city = new City();
        city.setId(rs.getInt(1));
        city.setCityName(rs.getString(2));
        city.setFoundationYear(rs.getInt(3));
        city.setArea(rs.getDouble(4));
        return city;
    }

    public static int createCity(City city) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/cities", "postgres", "admin")) {
            PreparedStatement st = con.prepareStatement("INSERT INTO cities(city_id, city_name , found_year ,area) VALUES( ?, ?, ?, ?)");
            st.setInt(1,city.getId());
            st.setString(2,city.getCityName());
            st.setInt(3,city.getFoundationYear());
            st.setDouble(4,city.getArea());
            st.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return 1;
    }

    public static List<City> readCitiesByName(String cityName) throws ClassNotFoundException {
        List<City> cities = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/cities", "postgres", "admin")) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM cities WHERE city_name = " + cityName);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                cities.add(getCityFromRS(rs));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return cities;
    }

    public static int updateCity(City city) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/cities", "postgres", "admin")) {
            PreparedStatement st = con.prepareStatement("UPDATE cities SET city_name = ?, found_year = ?, area = ? WHERE city_id = ?");
            st.setString(1,city.getCityName());
            st.setInt(2,city.getFoundationYear());
            st.setDouble(3,city.getArea());
            st.setInt(4,city.getId());
            st.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return 1;
    }

    public static int deleteCity(int id) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/cities", "postgres", "admin")) {
            Statement st =  con.createStatement();
            st.executeUpdate("DELETE FROM cities WHERE city_id = " + id);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return 1;
    }


   public static CitizenType getCitizenTypeFromRS(ResultSet rs) throws SQLException {
       CitizenType ct = new CitizenType();
       ct.setId(rs.getInt(1));
       ct.setName(rs.getString(2));
       ct.setCityId(rs.getInt(3));
       ct.setLanguage(rs.getString(4));
       ct.setSize(rs.getInt(5));
       return ct;
   }

    public static int createCitizenType(CitizenType citizenType) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/cities", "postgres", "admin")) {
            PreparedStatement st = con.prepareStatement("INSERT INTO \"citizenTypes\"(type_id, type_name , city_d ,lang, size) VALUES( ?, ?, ?, ?,?)");
            st.setInt(1,citizenType.getId());
            st.setString(2,citizenType.getName());
            st.setInt(3,citizenType.getCityId());
            st.setString(4,citizenType.getLanguage());
            st.setInt(5,citizenType.getSize());
            st.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return 1;
    }

    public static List<CitizenType> readCitizenTypeByName(String typeName) throws ClassNotFoundException {
        List<CitizenType> citizenTypes = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/cities", "postgres", "admin")) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM \"citizenTypes\" WHERE type_name = " + typeName);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                citizenTypes.add(getCitizenTypeFromRS(rs));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return citizenTypes;
    }

    public static int updateCitizenType(CitizenType citizenType) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/cities", "postgres", "admin")) {
            PreparedStatement st = con.prepareStatement("UPDATE \"citizenTypes\" SET type_name = ?, city_id = ?, lang = ?, size = ?  WHERE type_id = ?");
            st.setInt(5,citizenType.getId());
            st.setString(1,citizenType.getName());
            st.setInt(2,citizenType.getCityId());
            st.setString(3,citizenType.getLanguage());
            st.setInt(4,citizenType.getSize());
            st.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return 1;
    }

    public static int deleteCitizenType(int id) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/cities", "postgres", "admin")) {
            Statement st =  con.createStatement();
            st.executeUpdate("DELETE FROM \"citizenTypes\" WHERE type_id = " + id);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return 1;
    }

    public static List<CitizenType> query1(String cityName, String language) throws ClassNotFoundException {
        List<CitizenType> citizenTypes = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/cities", "postgres", "admin")) {
            PreparedStatement st = con.prepareStatement("SELECT \"citizenTypes\".* FROM \"citizenTypes\" JOIN cities ON cities.city_id = \"citizenTypes\".city_id WHERE cities.city_name = ? AND \"citizenTypes\".lang = ? ");
            st.setString(1, cityName);
            st.setString(2, language);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                citizenTypes.add(getCitizenTypeFromRS(rs));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return citizenTypes;
    }

    public static List<City> query2(String typeName) throws ClassNotFoundException {
        List<City> cities = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/cities", "postgres", "admin")) {
            PreparedStatement st = con.prepareStatement("SELECT cities.* FROM cities JOIN \"citizenTypes\" ON cities.city_id = \"citizenTypes\".city_id WHERE \"citizenTypes\".type_name = ?");
            st.setString(1, typeName);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                cities.add(getCityFromRS(rs));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return cities;
    }

    /*public static AbstractMap.SimpleEntry<City, List<CitizenType>> query3(String typeName) throws ClassNotFoundException {
        List<City> cities = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/cities", "postgres", "admin")) {
            PreparedStatement st = con.prepareStatement("SELECT cities.* FROM cities  WHERE citizenType.typeName = ?");
            st.setString(1, typeName);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                cities.add(getCityFromRS(rs));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return cities;
    }*/

    public static List<CitizenType> query3() throws ClassNotFoundException {
        List<CitizenType> citizenTypes = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/cities", "postgres", "admin")) {
            PreparedStatement st = con.prepareStatement("SELECT cities.* FROM cities ORDER BY cities.found_year ASC");
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                City city = getCityFromRS(rs);
                st = con.prepareStatement("SELECT \"citizenTypes\".* FROM \"citizenTypes\" WHERE city_id = ?");
                st.setInt(1,city.getId());
                rs = st.executeQuery();
                while(rs.next()){
                    citizenTypes.add(getCitizenTypeFromRS(rs));
                }
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return citizenTypes;
    }

}
