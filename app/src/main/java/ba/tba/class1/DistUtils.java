package ba.tba.class1;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;

import java.util.List;

/**
 * Created by nedim on 11/16/15.
 */
public final class DistUtils {

    public static double distance (double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = (double) (earthRadius * c);

        return distance;
    }

    public static double getLatitudeFromAddress(String findAddress, Context context) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;

        try {
            address = coder.getFromLocationName(findAddress, 5);
            if (address == null) {
                return 0;
            }
            Address location = address.get(0);
            return location.getLatitude();

        } catch (Exception e) {
            Toast.makeText(context, "Error occured finding latitude and latitude for " + findAddress, Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    public static double getLongitudeFromAddress(String findAddress, Context context) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;

        try {
            address = coder.getFromLocationName(findAddress, 5);
            if (address == null) {
                return 0;
            }
            Address location = address.get(0);
            return location.getLongitude();

        } catch (Exception e) {
            Toast.makeText(context, "Error occured finding latitude and longitude for " + findAddress, Toast.LENGTH_SHORT).show();
            return 0;
        }
    }
}
