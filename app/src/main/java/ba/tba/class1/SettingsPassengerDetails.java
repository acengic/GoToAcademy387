package ba.tba.class1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;

public class SettingsPassengerDetails extends AppCompatActivity {

    CheckBox chBoxLuggage;
    CheckBox chBoxTravelers;
    CheckBox chBoxPets;
    CheckBox chBoxDisabled;
    Switch swWalkingSpeed;
    Switch swGender;
    Spinner spinnerNoTravelers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_passenger_details);
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        */

        chBoxLuggage = (CheckBox) findViewById(R.id.luggage);
        chBoxTravelers = (CheckBox) findViewById(R.id.travelers);
        chBoxPets = (CheckBox) findViewById(R.id.pets);
        chBoxDisabled = (CheckBox) findViewById(R.id.disabled);
        swWalkingSpeed = (Switch) findViewById(R.id.walkingSpeed);
        swGender = (Switch) findViewById(R.id.gender);
        spinnerNoTravelers = (Spinner) findViewById(R.id.spinnerNoTravelers);

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);

        chBoxLuggage.setChecked(sharedPreferences.getBoolean("luggage", false));
        chBoxTravelers.setChecked(sharedPreferences.getBoolean("travelers", false));
        chBoxPets.setChecked(sharedPreferences.getBoolean("pets", false));
        chBoxDisabled.setChecked(sharedPreferences.getBoolean("disabled", false));
        swWalkingSpeed.setChecked(sharedPreferences.getBoolean("walkingSpeed", false));
        swGender.setChecked(sharedPreferences.getBoolean("gender", false));

        if(chBoxTravelers.isChecked())
        {
            spinnerNoTravelers.setEnabled(true);
            spinnerNoTravelers.setSelection(sharedPreferences.getInt("spinnerNoTravelers", 0));
        }
        else
            spinnerNoTravelers.setEnabled(false);
    }

    public void onStop ()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("luggage", chBoxLuggage.isChecked());
        editor.putBoolean("travelers", chBoxTravelers.isChecked());
        editor.putBoolean("pets", chBoxPets.isChecked());
        editor.putBoolean("disabled", chBoxDisabled.isChecked());
        editor.putBoolean("walkingSpeed", swWalkingSpeed.isChecked());
        editor.putBoolean("gender", swGender.isChecked());
        editor.putInt("spinnerNoTravelers", spinnerNoTravelers.getSelectedItemPosition());

        editor.commit();

        Toast.makeText(this, "The changes have been saved.", Toast.LENGTH_SHORT).show();
        super.onStop();
    }
}
