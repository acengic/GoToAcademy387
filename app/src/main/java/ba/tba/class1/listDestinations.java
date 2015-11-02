package ba.tba.class1;

import android.content.ContentValues;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import android.database.DatabaseUtils;

import java.util.ArrayList;
import java.util.List;

public class listDestinations extends AppCompatActivity {
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_destinations);

        db = openOrCreateDatabase("FavouriteDestinations", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Destinations(Name VARCHAR,Address VARCHAR, Visits INTEGER, GeoLongitude REAL, GeoLatitude REAL);");

        ListView lv = (ListView) findViewById(R.id.listView2);

        ArrayList<Destination> listDestination = new ArrayList<>();
        PopulateDestinationList(listDestination);

        DestinationAdapter da = new DestinationAdapter(listDestination, getBaseContext());
        lv.setAdapter(da);
    }

    // We can use DB or Web Service or JSON list to populate
    void PopulateDestinationList(ArrayList<Destination> listDestination){

        /*
        if (Destination.listAll(Destination.class).isEmpty()) {
            Destination d = new Destination("Home", "Alipasina 12, Sarajevo, 71000");
            d.save();

            d = new Destination("Work", "H Kresevljakovica 20, Sarajevo, 71000");
            d.save();

            d = new Destination("Gym", "Cengic Vila 2, Sarajevo, 71000");
            d.save();
        }
*/
        Cursor crs = db.rawQuery("SELECT * FROM Destinations", null);
        Destination dest = new Destination();

        List<String> array = new ArrayList<String>();
        while(crs.moveToNext())
        {
            if (crs.getInt(crs.getColumnIndex("Visits")) >= 3) {
                dest = new Destination(crs.getString(crs.getColumnIndex("Name")), crs.getString(crs.getColumnIndex("Address")));
                dest.save();
            }
        }
        List<Destination> ld = Destination.listAll(Destination.class);
        listDestination.addAll(ld);

        crs.close();
    }

    public void AddNewDestinaton(View v){
        LinearLayout  ll = (LinearLayout) findViewById(R.id.add_new_layout);
        ll.setVisibility(LinearLayout.VISIBLE);

        ListView lv = (ListView) findViewById(R.id.listView2);
        lv.setVisibility(ListView.GONE);
    }

    public void FindNew (View v) {
        EditText newAddressEdit = (EditText) findViewById(R.id.location_address_edit);
        String newAddress = newAddressEdit.getText().toString();
        EditText newLocationEdit =(EditText) findViewById(R.id.location_name_edit);
        String newLocation = newLocationEdit.getText().toString();

        System.out.println("Location: " + getLatitudeFromAddress(newAddress) + "; " + getLongitudeFromAddress(newAddress));

        long number = DatabaseUtils.queryNumEntries(db, "Destinations", "Name = ?", new String[] {newLocation});
        System.out.println("number: " + number);

        if(number < 1) {
            ContentValues insertValues = new ContentValues();
            insertValues.put("Name", newLocation);
            insertValues.put("Address", newAddress);
            insertValues.put("Visits", 1);
            insertValues.put("GeoLongitude", getLongitudeFromAddress(newAddress));
            insertValues.put("GeoLatitude", getLatitudeFromAddress(newAddress));
            db.insert("Destinations", null, insertValues);
        }
        else {
            Cursor cursor = db.rawQuery("SELECT * FROM Destinations WHERE Name = '" + newLocation + "'", null);
            Integer numOfVisits;
            if (cursor.moveToFirst()) { // data?
                numOfVisits = cursor.getInt(cursor.getColumnIndex("Visits"));
                System.out.println("Vis: " + numOfVisits);
                numOfVisits++;
                String strSQL = "UPDATE Destinations SET Visits = " + numOfVisits + " WHERE Name = '" + newLocation + "'";
                db.execSQL(strSQL);
            }
            cursor.close();
        }

        Toast.makeText(getBaseContext(), "Searching for available vehicles to get you " + newAddress, Toast.LENGTH_SHORT).show();
        LinearLayout  ll = (LinearLayout) findViewById(R.id.add_new_layout);
        ll.setVisibility(LinearLayout.GONE);

        ListView lv = (ListView) findViewById(R.id.listView2);
        lv.setVisibility(ListView.VISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_destinations, menu);
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

    public double getLatitudeFromAddress(String findAddress) {

        Geocoder coder = new Geocoder(this);
        List<Address> address;

        try {
            address = coder.getFromLocationName(findAddress, 5);
            if (address == null) {
                return 0;
            }
            Address location = address.get(0);
            return location.getLatitude();

        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Error occured finding latitude and latitude for " + findAddress, Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    public double getLongitudeFromAddress(String findAddress) {

        Geocoder coder = new Geocoder(this);
        List<Address> address;

        try {
            address = coder.getFromLocationName(findAddress, 5);
            if (address == null) {
                return 0;
            }
            Address location = address.get(0);
            return location.getLongitude();

        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Error occured finding latitude and longitude for " + findAddress, Toast.LENGTH_SHORT).show();
            return 0;
        }
    }
}
