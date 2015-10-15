package ba.tba.class1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.query.Condition;
import com.orm.query.Select;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle b = getIntent().getExtras();
        long dbID = 0;
        String s = null;
        if (b != null) {
            Log.i("MainActivity", "Bundle is not null");
            dbID = b.getLong("dbID");
            s = b.getString(Constants.test);
            Log.i("MainActivity", "String test value is:" + s);
        }

        EditText name = (EditText) findViewById(R.id.name);
        name.setText(s);

        //       Person pi = Person.findById(Person.class, dbID);
//        name.setText(pi.name);
//
//        EditText lName = (EditText) findViewById(R.id.lName);
//        lName.setText(pi.lastName);
//
//        TextView address = (TextView) findViewById(R.id.address);
//        address.setText(pi.address);
//
//        TextView city = (TextView) findViewById(R.id.city);
//        city.setText(pi.city);
//
//        TextView state = (TextView) findViewById(R.id.state);
//        state.setText(pi.country);
//
//        //DB Query example 3 ways to query database
// //       List<Person> findList1 = Person.find(Person.class, "city=?", "Luisville", "", "name", "2");
//        List<Person> findList2 = Person.findWithQuery(Person.class, "Select * from Person where name = ?", "John");
//        List<Person> findList3 = Select.from(Person.class)
//                .where(Condition.prop("name").eq("John"))
//                .list();

// this will cause runtime exception last_name
//        List<Person> findList4 = Select.from(Person.class)
//                .where(Condition.prop("lastName").eq("Do"))
//                .list();

        SQLiteDatabase db = openOrCreateDatabase("GOTO", MODE_PRIVATE, null);
        Cursor resultSet = db.rawQuery("Select * from User",null);
        resultSet.moveToFirst();
        String username = resultSet.getString(0);
        String password = resultSet.getString(1);

    }

    public void Save(View v)
    {
        EditText et = (EditText) findViewById(R.id.name);
        Toast.makeText(getBaseContext(), "Saving Perosnal Info for " + et.getText().toString(),Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
