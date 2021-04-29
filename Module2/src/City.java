import java.io.Serializable;

public class City implements Serializable {
    private int id;
    private String cityName;
    private int foundationYear;
    private double area;

    public City() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(int foundationYear) {
        this.foundationYear = foundationYear;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public City(int id, String cityName, int foundationYear, double area) {
        this.id = id;
        this.cityName = cityName;
        this.foundationYear = foundationYear;
        this.area = area;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", foundationYear=" + foundationYear +
                ", area=" + area +
                '}';
    }
}
