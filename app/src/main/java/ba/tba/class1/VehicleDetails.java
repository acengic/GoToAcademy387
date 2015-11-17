package ba.tba.class1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

public class VehicleDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);

        ImageView companyNameImageView = (ImageView) findViewById(R.id.companyNameImageView);
        ImageView driverImageView = (ImageView) findViewById(R.id.driverImageView);
        ImageView vehicleTypeImageView = (ImageView) findViewById(R.id.vehicleTypeImageView);

        TextView companyNameTextView = (TextView) findViewById(R.id.companyNameTextView);
        TextView driverNameTextView = (TextView) findViewById(R.id.driverNameTextView);
        TextView vehicleTypeTextView = (TextView) findViewById(R.id.vehicleTypeTextView);
        TextView vehicleSeatsTextView = (TextView) findViewById(R.id.vehicleSeatsTextView);

        ProgressBar vehicleSeatsProgressBar = (ProgressBar) findViewById(R.id.vehicleSeatsProgressBar);

        RatingBar driverRatingBar = (RatingBar) findViewById(R.id.driverRatingBar);


        //TODO get data from ArrivalListActivity


        //Sample data to be shown

        companyNameImageView.setImageResource(R.mipmap.gras);
        driverImageView.setImageResource(R.drawable.dark_user);
        vehicleTypeImageView.setImageResource(R.drawable.vehicle_type_bus);
        companyNameTextView.setText("GRAS");
        driverNameTextView.setText("Miško");
        vehicleTypeTextView.setText("Minibus");
        vehicleSeatsTextView.setText("40/100");
        vehicleSeatsProgressBar.setProgress(40);
        driverRatingBar.setRating(3);


    }

}
