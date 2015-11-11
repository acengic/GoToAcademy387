package ba.tba.class1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RadioGroup;
import android.widget.Toast;

//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;

public class SettingsApplicationLanguage extends AppCompatActivity {

    RadioGroup radioGroupLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_application_language);
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        */

        radioGroupLanguage = (RadioGroup) findViewById(R.id.radioGroupLanguage);

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);

        radioGroupLanguage.check(sharedPreferences.getInt("radioGroupLanguage", R.id.radioEnglish));

    }

    public void onStop ()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("radioGroupLanguage", radioGroupLanguage.getCheckedRadioButtonId());

        editor.commit();

        Toast.makeText(this, "The changes have been saved.", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

}
