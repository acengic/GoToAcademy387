package ba.tba.class1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by Linux on 11/17/2015.
 */
public class TransportList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transport);

        Button bus = (Button) findViewById(R.id.bus);
        Button minibus = (Button) findViewById(R.id.minibus);
        Button taxi = (Button) findViewById(R.id.taxi);
        Button tram = (Button) findViewById(R.id.tram);
        Button trolleybus = (Button) findViewById(R.id.trolleybus);



    }
}