package ba.tba.class1;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by nedim on 11/16/15.
 */
public class LocationProvider implements LocationListener{

    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location myLocation;


    LocationProvider(Context context) {
        findMyLocation(context);
    }
    @Override
    public void onLocationChanged(Location location) {
        myLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Provider", "enabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Provider", "disabled");
    }

    protected void findMyLocation (Context mContext) {

        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        Boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        Boolean isPassiveEnabled = locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER);

        Log.v("GPS enabled", "=" + isGPSEnabled);
        Log.v("Network Enabled", "=" + isNetworkEnabled);

        if (isGPSEnabled) {
            myLocation=null;
            if (myLocation == null) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                if (locationManager != null) {
                    myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (myLocation!= null) {
                        Log.v("location", "Location set using GPS");
                    }
                }
            }
        }
        if(isNetworkEnabled && myLocation == null) {
            if (myLocation == null) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
                if (locationManager != null) {
                    myLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (myLocation!= null) {
                        Log.v("location", "Location set using Network");
                    }
                }
            }
        }
        if (isPassiveEnabled && myLocation == null) {
            if (myLocation == null) {
                locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, this);
                if (locationManager != null) {
                    myLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                    if (myLocation!= null) {
                        Log.v("location", "Location set using Passive provider");
                    }
                }
            }
        }
    }

    public Location getMyLocation() {return myLocation; }
}
