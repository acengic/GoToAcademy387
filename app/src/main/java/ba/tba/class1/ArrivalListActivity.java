package ba.tba.class1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public class ArrivalListActivity extends AppCompatActivity {

    ProgressDialog pd;
    String fromStation, toStation, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrival_list);

        fromStation = getIntent().getStringExtra(Constants.start);
        toStation = getIntent().getStringExtra(Constants.end);
        time = getIntent().getStringExtra(Constants.time);

        ArrayList<Arrival> arrivalsList = new ArrayList<>();


        pd = ProgressDialog.show(this, "Loading", "Wait while we get arrivals...");
        PopulateArrivalsListFromWeb(arrivalsList);
        PopulateArrivalsList(arrivalsList);


        ListView lv = (ListView) findViewById(R.id.arrivalsListView);
        lv.setAdapter(new ArrivalAdapter(arrivalsList, getBaseContext()));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), "Selected item No:" + position, Toast.LENGTH_LONG);
                //Arrival entry = (Arrival) parent.getItemAtPosition(position);
                view.setBackgroundColor(Color.DKGRAY);

            }
        });
    }

    void PopulateArrivalsListFromWeb(ArrayList<Arrival> arrivalsList) {
        Call<List<Next>> nextCall = WebService.apiService.getNext(fromStation, toStation, "12:15");

        nextCall.enqueue(new Callback<List<Next>>() {
            @Override
            public void onResponse(Response<List<Next>> response, Retrofit retrofit) {
                Log.i("Retrofit2", "sucess: " + response.toString());
                int statusCode = response.code();
                List<Next> ln = response.body();
                pd.cancel();
                // Get result Repo from response.body()
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Retrofit2", "Error: " + t.toString());
                pd.cancel();
            }
        });
    }


    void PopulateArrivalsList(ArrayList<Arrival> arrivalsList){

        arrivalsList.add(new Arrival(3, "19:48","Centrotr","31B"));
        arrivalsList.add(new Arrival(5, "19:50","GRAS","3"));
        arrivalsList.add(new Arrival(8, "19:53","UBER","Van"));
        arrivalsList.add(new Arrival(12, "19:54","GRAS","2"));
        arrivalsList.add(new Arrival(17, "19:59","GRAS","3"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_arrival_list, menu);
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
