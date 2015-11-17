package ba.tba.class1;

import com.orm.SugarRecord;

public class Destination extends SugarRecord<Destination> {
    private String name;
    private String address;
    private int numberOfVisits;
    private double geoLongitude;
    private double geoLatitude;

    //This is a missing part that caused Errors in todays class (Monday 19 October)
    // Class HAS TO HAVE Empty constructor to work as Sugar record
    public Destination(){
    }

    Destination(String name, String address) {
        this.name = name;
        this.address = address;
    }

    Destination (String name, String address, int numberOfVisits, double geoLatitude, double geoLongitude) {
        this.name = name;
        this.address = address;
        this.numberOfVisits = numberOfVisits;
        this.geoLatitude = geoLatitude;
        this.geoLongitude = geoLongitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public double getGeoLongitude() {
        return geoLongitude;
    }

    public void setGeoLongitude(long geoLongitude) {
        this.geoLongitude = geoLongitude;
    }

    public double getGeoLatitude() {
        return geoLatitude;
    }

    public void setGeoLatitude(long geoLatitude) {
        this.geoLatitude = geoLatitude;
    }
}


