package ba.tba.class1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

public class BusStation extends AppCompatActivity {

    long dbId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_station);

        TimePicker tp = (TimePicker) findViewById(R.id.timePicker);
        tp.setCurrentHour(18);
        tp.setCurrentMinute(0);

        // Create DB and Insert records in Person table using Sugar ORM
        CreatePerson();
        CreateLine();
        Spinner s1 = (Spinner) findViewById(R.id.fromStation);
        Spinner s2 = (Spinner) findViewById(R.id.toStation);

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

    public void ShowListView(View v){
        Intent i = new Intent(this, list.class);
        startActivity(i);
    }

    public void Go(View v){
        Bundle b = new Bundle();
        b.putLong("dbID", 1);
        b.putCharSequence(Constants.test,"JOHN");
        Intent i = new Intent(this, MainActivity.class);
        i.putExtras(b);
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
