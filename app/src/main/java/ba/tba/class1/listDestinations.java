package ba.tba.class1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class listDestinations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_destinations);

        ListView lv = (ListView) findViewById(R.id.listView2);

        ArrayList<Destination> listDestination = new ArrayList<>();
        PopulateDestinationList(listDestination);

        DestinationAdapter da = new DestinationAdapter(listDestination, getBaseContext());
        lv.setAdapter(da);
    }

    // We can use DB or Web Service or JSON list to populate
    void PopulateDestinationList(ArrayList<Destination> listDestination){

        if (Destination.listAll(Destination.class).isEmpty()) {
            Destination d = new Destination("Home", "Alipasina 12, Sarajevo, 71000");
            d.save();

            d = new Destination("Work", "H Kresevljakovica 20, Sarajevo, 71000");
            d.save();

            d = new Destination("Gym", "Cengic Vila 2, Sarajevo, 71000");
            d.save();
        }

        List<Destination> ld = Destination.listAll(Destination.class);
        listDestination.addAll(ld);

//        listDestination.add(new Destination("Home", "Alipasina 12, Sarajevo, 71000"));
//        listDestination.add(new Destination("Work", "H Kresevljakovica 20, Sarajevo, 71000"));
//        listDestination.add(new Destination("Gym", "Cengic Vila 2, Sarajevo, 71000"));
    }

    public void AddNewDestinaton(View v){
        LinearLayout  ll = (LinearLayout) findViewById(R.id.add_new_layout);
        ll.setVisibility(LinearLayout.VISIBLE);

        ListView lv = (ListView) findViewById(R.id.listView2);
        lv.setVisibility(ListView.INVISIBLE);
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
}
