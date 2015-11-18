package ba.tba.class1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Parcelable;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrival_list);

        fromStation = getIntent().getStringExtra(Constants.start);
        toStation = getIntent().getStringExtra(Constants.end);
        time = getIntent().getStringExtra(Constants.time);

        this.setTitle(fromStation + '-' + toStation );
        //ArrayList<Arrival> arrivalsList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.arrivalsListView);
        pd = ProgressDialog.show(this, "Loading", "Wait while we get arrivals...");
        if (savedInstanceState == null) PopulateArrivalsListFromWeb();
        //PopulateArrivalsList(arrivalsList);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), "Selected item No:" + position, Toast.LENGTH_LONG);
                Arrival entry = (Arrival) parent.getItemAtPosition(position);
                String[] parts = entry.getArrival().split(":");
                int hour = 0 , minute = 0;
                try {
                    hour = Integer.parseInt(parts[0]);
                    minute = Integer.parseInt(parts[1]);
                }
                catch (Exception e){
                    e.printStackTrace();
                    //Return and Let user know
                }

                SetAlarm(hour, minute, "GoTo Alarm");

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        ArrivalAdapter aa = (ArrivalAdapter) lv.getAdapter();
        Arrival[] array = aa.getCollection().toArray(new Arrival[aa.getCollection().size()]);

        outState.putParcelableArray("arrivalsList", array);
    }

    void SetAlarm(int hour, int minute, String description){

        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_MESSAGE, description);
        i.putExtra(AlarmClock.EXTRA_HOUR, hour);
        i.putExtra(AlarmClock.EXTRA_MINUTES, minute);
        startActivity(i);
        return;
    }

    class LoggingInterceptor implements Interceptor {
        @Override public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
//            Log.i("OKHTTP",String.format("Sending request % s on % s % n % s",
//                    request.url(), chain.connection(), request.headers()));

            com.squareup.okhttp.Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Log.i("OKHTTP",String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.body()));

            return response;
        }
    }
    void PopulateArrivalsListFromWeb() {

     //   OkHttpClient client = new OkHttpClient();
     //   client.interceptors().add(new LoggingInterceptor());

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://sleepy-river-8228.herokuapp.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        WebService.GoToApiEndpointInterface apiService =
//                retrofit.create(WebService.GoToApiEndpointInterface.class);

        Call<List<Arrival>> nextCall = WebService.apiService.getNext(fromStation, toStation, "12:15");

        //Call<List<Arrival>> nextCall = WebService.apiService.getNext(fromStation, toStation, "12:15");

        nextCall.enqueue(new Callback<List<Arrival>>() {
            @Override
            public void onResponse(Response<List<Arrival>> response, Retrofit retrofit) {
                Log.i("Retrofit2", "sucess: " + response.toString());
                int statusCode = response.code();
                Log.i("OKHTTP ", response.body().toString());
                List<Arrival> ln = response.body();
                lv.setAdapter(new ArrivalAdapter(ln, getBaseContext()));
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

//        arrivalsList.add(new Arrival(3, "19:48","Centrotr","31B"));
//        arrivalsList.add(new Arrival(5, "19:50","GRAS","3"));
//        arrivalsList.add(new Arrival(8, "19:53","UBER","Van"));
//        arrivalsList.add(new Arrival(12, "19:54","GRAS","2"));
//        arrivalsList.add(new Arrival(17, "19:59","GRAS","3"));
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
