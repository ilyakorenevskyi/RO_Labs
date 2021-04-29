import java.io.Serializable;

public class CitizenType implements Serializable {
    private int id;
    private String name;
    private int size;
    private int cityId;
    private String language;

    public CitizenType() {
    }

    public CitizenType(int id, String name, int size, int cityId, String language) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.cityId = cityId;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "CitizenType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", cityId=" + cityId +
                ", language='" + language + '\'' +
                '}';
    }
}
