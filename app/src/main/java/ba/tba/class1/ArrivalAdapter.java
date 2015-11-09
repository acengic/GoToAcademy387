package ba.tba.class1;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jackblack on 10/26/15.
 */
public class ArrivalAdapter extends BaseAdapter {

    List<Arrival> arrivals = null;
    Context context = null;

    ArrivalAdapter(List<Arrival> arrivals, Context context){
        this.arrivals =  arrivals;
        this.context = context;
    }


    @Override
    public int getCount() {
        return arrivals.size();
    }

    @Override
    public Object getItem(int position) {
       return arrivals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.arrival, parent, false);
        }

        TextView minutesToArrival = (TextView) convertView.findViewById(R.id.minutesToArrival);
        TextView arrivalTime = (TextView) convertView.findViewById(R.id.arrivalTime);
        TextView operator = (TextView) convertView.findViewById(R.id.operator);
        ImageButton setAlarm = (ImageButton) convertView.findViewById(R.id.setAlarm);
        ImageButton viewDetails = (ImageButton) convertView.findViewById(R.id.viewDetails);

        Arrival a = arrivals.get(position);

        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Oxygen.otf");
        minutesToArrival.setText(Integer.toString(a.getEta().intValue()));
        arrivalTime.setText(a.getArrival());
        minutesToArrival.setTypeface(tf);
        arrivalTime.setTypeface(tf);

        operator.setText(a.getOperatorName());
        operator.setTypeface(Typeface.createFromAsset(context.getAssets(), "NewsCycle-Regular.ttf"));
        //operator.setTextSize(30);
        return convertView;
    }
}
