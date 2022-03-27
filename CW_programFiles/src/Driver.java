import java.io.Serializable;

public abstract class Driver implements Serializable {

    private String driverName;
    private String driverCountry;
    private String driverTeam;

    //Default constructor
    public Driver() {
        this.driverName = "";
        this.driverCountry = "";
        this.driverTeam = "";
    }

    //Getters
    public String getDriverName() {
        return driverName;
    }
    public String getDriverCountry() {
        return driverCountry;
    }
    public String getDriverTeam() {
        return driverTeam;
    }

    //Setters
    public void setDriverName(String dName) {
        this.driverName = dName;
    }
    public void setDriverCountry(String dCountry) {
        this.driverCountry = dCountry;
    }
    public void setDriverTeam(String dTeam) {
        this.driverTeam = dTeam;
    }
}
