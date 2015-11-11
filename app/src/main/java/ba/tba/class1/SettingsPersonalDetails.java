package ba.tba.class1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;

public class SettingsPersonalDetails extends AppCompatActivity {

    EditText editTextName;
    EditText editTextSurname;
    EditText editTextAddress;
    EditText editTextCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_personal_details);
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        */

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSurname = (EditText) findViewById(R.id.editTextSurname);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextCity = (EditText) findViewById(R.id.editTextCity);

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);

        editTextName.setText(sharedPreferences.getString(Constants.name, ""));
        editTextSurname.setText(sharedPreferences.getString(Constants.surname, ""));
        editTextAddress.setText(sharedPreferences.getString(Constants.address, ""));
        editTextCity.setText(sharedPreferences.getString(Constants.city, ""));
    }

    public void onStop ()
    {
        //do your SaveSettinga... stuff here
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(Constants.name, editTextName.getText().toString());
        editor.putString(Constants.surname, editTextSurname.getText().toString());
        editor.putString(Constants.address, editTextAddress.getText().toString());
        editor.putString(Constants.city, editTextCity.getText().toString());

        editor.commit();

        Toast.makeText(this, "The changes have been saved.", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

}

