package ba.tba.class1;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;



import java.io.File;
import java.io.IOException;

import java.util.Calendar;
import java.util.List;


import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public class BusStation extends AppCompatActivity {

    long dbId = 0;
    Spinner s1;
    Spinner s2;
    TimePicker tp;
    final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/GoToPhotoFolder/";
    final int TAKE_PHOTO_CODE = 100; //Activity ID take camera photo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_station);

        tp = (TimePicker) findViewById(R.id.timePicker);
        Calendar calendar = Calendar.getInstance();
        //calendar.setTime(new Date());
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        tp.setCurrentHour(hours);
        tp.setCurrentMinute(minutes);


        s1 = (Spinner) findViewById(R.id.fromStation);
        s2 = (Spinner) findViewById(R.id.toStation);

        String[] slist = getResources().getStringArray(R.array.stations);
        StationAdapter adapter = new StationAdapter( slist, getBaseContext());

        s1.setAdapter(adapter);
        s2.setAdapter(adapter);

        // Gets list of stations from web service
        // PopulateStations();
    }

    public void Go(View v){

        String arrivalTime = tp.getCurrentHour().toString() + ":" + tp.getCurrentMinute().toString();
        Intent i = new Intent(this, ArrivalListActivity.class);
        i.putExtra(Constants.start, s1.getSelectedItem().toString());
        i.putExtra(Constants.end, s2.getSelectedItem().toString());
        i.putExtra(Constants.time, arrivalTime);

        startActivity(i);
    }

    public void ShowNotification(View v) {

        String tittle="GoTo Notification";
        String subject="Bus Late";
        String body="Line 42 bus will be 5 minutes late in arrival";

//        if (currentapiVersion < android.os.Build.VERSION_CODES.HONEYCOMB) {
//
//            notification = new Notification(icon, text, time);
//            notification.setLatestEventInfo(this, title, text, contentIntent);
//            notification.flags |= Notification.FLAG_AUTO_CANCEL;
//            mNM.notify(NOTIFICATION, notification);
//        } else {
        NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pending=PendingIntent.getActivity(getApplicationContext(), 0, new Intent(),0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                this);

        Notification notification = builder.setContentIntent(pending)
                .setSmallIcon(R.drawable.viber)
                .setTicker("GoTo Notification")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Title")
                .setContentText("Bus line 47 will be late 5 min ")
                .build();

        nm.notify(0, notification);
//        }


    }

    void PopulateStations(){

        Call<List<Station>> call = WebService.apiService.getStationList();

        call.enqueue(new Callback<List<Station>>() {
            @Override
            public void onResponse(Response<List<Station>> response, Retrofit retrofit) {
                int statusCode = response.code();
                response.body();
            }

            @Override
            public void onFailure(Throwable t) {

                // Log error here since request failed
            }
        });
    }

    private void GetLastLocation(){
        //Location Manager has Network WiDi provider, GPS provider
        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location locationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
    }


    //create Line table using SQLite
    private void CreateLine(){
        SQLiteDatabase db = openOrCreateDatabase("GOTO", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS User(Username VARCHAR,Password VARCHAR);");
        db.execSQL("INSERT INTO User VALUES('admin','admin');");
        db.execSQL("INSERT INTO User VALUES('John','JohnDo');");
    }


    //create new Person record using SugarRDM
    private void CreatePerson(){
        Person.deleteAll(Person.class);
        if (Person.listAll(Person.class).isEmpty()) {
            Person pi = new Person("Jack", "Black", "1400 S 6st St","Luisville", "KY", "40208");
            pi.save();
            pi = new Person("Jane", "Do", "342 21st St","Washington", "DC", "20008");
            pi.save();
            pi = new Person("John", "Do", "450 96st St","Manhattan", "NY", "10008");
            pi.save();
            dbId = pi.getId();
        }
    }


    public void ShowAlertDialog(View v) {

        AlertDialog alertDialog = new AlertDialog.Builder(BusStation.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Alert message to be shown");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }


    public void TakePhoto(String fileName){
        //Make new folder to store Application photos (if it does not exist)
        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newdir = new File(dir);
        if (!newdir.exists()) newdir.mkdirs();

        //Create file where Photo from camera will be stored
        String file = dir + fileName;
        File newfile = new File(file);
        try {
            newfile.createNewFile();
        } catch (IOException e) {
        }

        Uri outputFileUri = Uri.fromFile(newfile);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
            Log.d("CameraDemo", "Photo saved to folder " + dir);
        }
        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_CANCELED) {
            Log.d("CameraDemo", "Camera activity Canceled. No photo taken.");
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bus_station, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //return true;
            Intent i = new Intent(this, Settings.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void findNearestStation (View v) {
        GetLastLocation();
        LocationProvider lp = new LocationProvider(v.getContext());
        Location location = lp.getMyLocation();
        if (location == null) {
            Toast.makeText(getBaseContext(), "Can't find your location, please check your GPS and NETWORK settings ", Toast.LENGTH_SHORT).show();
        }
        else {
            Log.d("Location", "Latitude " + location.getLatitude() + "; Longitude: " + location.getLongitude());
        }
    }

    public void chooseTransportMode (View v)
        Get

}
