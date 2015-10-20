package ba.tba.class1;

import com.orm.SugarRecord;

public class Destination extends SugarRecord<Destination> {
        private String name;
        private String address;
        private int numberOfVisits;
        private long geoLongitude;
        private long geoLatitude;

        //This is a missing part that caused Errors in todays class (Monday 19 October)
        // Class HAS TO HAVE Empty constructor to work as Sugar record
        public Destination(){
        }

    Destination(String name, String address) {
            this.name = name;
            this.address = address;
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

        public long getGeoLongitude() {
            return geoLongitude;
        }

        public void setGeoLongitude(long geoLongitude) {
            this.geoLongitude = geoLongitude;
        }

        public long getGeoLatitude() {
            return geoLatitude;
        }

        public void setGeoLatitude(long geoLatitude) {
            this.geoLatitude = geoLatitude;
        }
    }


