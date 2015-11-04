package ba.tba.class1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        String[] stations = getResources().getStringArray(R.array.stations);
        ArrayAdapter<String> stationsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,stations );


        ArrayList<ApplicationInfo> appInfoList = new ArrayList<>();
        PopulateApplicationInfoList(appInfoList);

        ApplicationInfoAdapter appInfoAdapter = new ApplicationInfoAdapter(appInfoList, getBaseContext());

        ListView lw = (ListView) findViewById(R.id.listView);
        lw.setAdapter(appInfoAdapter);
    }


    void PopulateApplicationInfoList(ArrayList<ApplicationInfo> appInfoList){


//     jsonObject =
//        {"Publisher": "Viber",
//            "Post":"Get New Viber App",
//            "Location":"Japan",
//            "imageFileNameId": 0 }";
        String jsonString = loadJSONFromAsset("AppInfo.json");
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            int size = jsonArray.length();
            for(int i=0; i < size; i++){
                JSONObject jo = jsonArray.getJSONObject(i);

                appInfoList.add(new ApplicationInfo(
                        jo.getString("Publisher"),
                        jo.getString("Post"),
                        jo.getString("Location"),
                        jo.getInt("imageFileNameId")));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
//        <Publisher> Viber </Publisher>
//        <Post> Get New Viber App </Post>
//        <Location> Japan </Location>
//        <imageFileNameId> R.drawable.viber </imageFileNameId>

//        appInfoList.add(new ApplicationInfo("Viber", "Get New Viber App", "Japan", R.drawable.viber));
//        appInfoList.add(new ApplicationInfo("Facebook", "FB  App", "California", R.drawable.facebook));
//        appInfoList.add(new ApplicationInfo("Klix", "Most popular BH App", "BH", R.drawable.klix));
    }

    public String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = this.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
