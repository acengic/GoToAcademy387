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


public class SettingsApplication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_application);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ListView listView = (ListView) findViewById(R.id.listView4);

        String[] items = new String[]
                {       "Language",
                        "About",
                        ""
                };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,  android.R.id.text1, items);
        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(itemPosition);

                // Show Alert
                /*
                Toast.makeText(getApplicationContext(),
                        "Position: "+itemPosition+"  ListItem: " +itemValue , Toast.LENGTH_LONG)
                        .show();
                */

                switch (itemValue) {
                    case "Language":
                        OnClickLanguageItem();
                        break;
                    case "About":
                        OnClickAboutItem();
                        break;
                }

            }

        });
    }

    public void OnClickLanguageItem()
    {
        Intent i = new Intent(this, SettingsApplicationLanguage.class);
        startActivity(i);
    }

    public void OnClickAboutItem()
    {
        Intent i = new Intent(this, SettingsApplicationAbout.class);
        startActivity(i);
    }
}

