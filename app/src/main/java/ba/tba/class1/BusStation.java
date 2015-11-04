package ba.tba.class1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
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

        //Location Manager has Network WiDi provider, GPS provider
        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location locationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
//        try {
//            Typeface tf = Typeface.createFromAsset(getAssets(), "Arvo-Bold.ttf");
//            Button b = (Button) findViewById(R.id.showList);
//            b.setTypeface(tf);
//            //b.setGravity(Gravity.CENTER);
//            //b.setGravity(Gravity.CENTER_VERTICAL);
//
//        } catch (Exception e) {
//            Log.d("ApplyFonts", "Could not Apply Font Arvo-Bold.ttf");
//        }

        tp = (TimePicker) findViewById(R.id.timePicker);
        Calendar calendar = Calendar.getInstance();
        //calendar.setTime(new Date());
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        tp.setCurrentHour(hours);
        tp.setCurrentMinute(minutes);

        // Create DB and Insert records in Person table using Sugar ORM
        //CreatePerson();
        //CreateLine();
        s1 = (Spinner) findViewById(R.id.fromStation);
        s2 = (Spinner) findViewById(R.id.toStation);

        String[] slist = getResources().getStringArray(R.array.stations);
        StationAdapter adapter = new StationAdapter( slist, getBaseContext());

                //Creating ArrayAdapter from resource string array
                // ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.stations, android.R.layout.simple_spinner_item);
                // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //Using same addapter (same list of stations) for from and to spinner (drop down list)
                //s1.setPrompt("From"); //TODO setting initial default value, do it the right way
        s1.setAdapter(adapter);
        //s2.setPrompt("To"); //TODO setting initial default value, do it the right way
        s2.setAdapter(adapter);

//        Button btn = (Button) findViewById(R.id.showList);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(BusStation.this, list.class));
//            }
//        });

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


    public void ShowAlert(View v) {

//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
//
//        // set title
//        alertDialogBuilder.setTitle("Your Title");
//
//        // set dialog message
//        alertDialogBuilder
//                .setMessage("Click yes to exit!")
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // if this button is clicked, close
//                        // current activity
//                        BusStation.this.finish();
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // if this button is clicked, just close
//                        // the dialog box and do nothing
//                        dialog.cancel();
//                    }
//                });
//        // create alert dialog
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();

        //Make new folder to store Application photos (if it does not exist)

        //final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newdir = new File(dir);
        if (!newdir.exists()) newdir.mkdirs();

        //Create file where Photo from camera will be stored
        String file = dir+"GoToApp.jpg";
        File newfile = new File(file);
        try {
            newfile.createNewFile();
        } catch (IOException e) {}

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

    public void Go(View v){

        Intent i = new Intent(this, ArrivalListActivity.class);
        i.putExtra(Constants.start, s1.getSelectedItem().toString());
        i.putExtra(Constants.end, s2.getSelectedItem().toString());
        i.putExtra(Constants.time, "18:23");

        startActivity(i);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
