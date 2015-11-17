package ba.tba.class1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;


public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for Up Navigation (left-facing caret)

        final ListView listView = (ListView) findViewById(R.id.listView);

        String[] items = new String[]
                { "Personal details",
                        "Passenger details",
                        "Favorite locations",
                        "Alert tone",
                        "Application",
                        ""
                };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,  android.R.id.text1, items);
        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(itemPosition);

                // Show Alert
                /*
                Toast.makeText(getApplicationContext(),
                        "Position: "+itemPosition+"  ListItem: " +itemValue , Toast.LENGTH_LONG)
                        .show();
                */

                switch (itemValue)
                {
                    case "Personal details": OnClickPersonalDetailsItem();
                        break;
                    case "Passenger details": OnClickPassengerDetailsItem();
                        break;
                    case "Favorite locations": OnClickFavoriteLocationsItem();
                        break;
                    //case "Alerts": OnClickAlertsItem();
                    //    break;
                    case "Alert tone": OnClickAlertToneItem();
                        break;
                    case "Application": OnClickApplicationItem();
                        break;
                }

            }

        });
    }

    public void OnClickPersonalDetailsItem()
    {
        Intent i = new Intent(this, SettingsPersonalDetails.class);
        startActivity(i);
    }

    public void OnClickPassengerDetailsItem()
    {
        Intent i = new Intent(this, SettingsPassengerDetails.class);
        startActivity(i);
    }

    public void OnClickFavoriteLocationsItem()
    {
        Intent i = new Intent(this, listDestinations.class);
        startActivity(i);
    }

    public void OnClickAlertToneItem()
    {
        /*
        Intent i = new Intent(this, SettingsAlerts.class);
        startActivity(i);
        */
    }

    public void OnClickApplicationItem()
    {
        Intent i = new Intent(this, SettingsApplication.class);
        startActivity(i);
    }

}

