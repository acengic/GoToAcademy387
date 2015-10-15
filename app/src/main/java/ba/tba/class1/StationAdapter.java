package ba.tba.class1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jackblack on 10/11/15.
 */
public class StationAdapter extends BaseAdapter {

    String[] stations = null;
    Context ct;

    StationAdapter(String[] stations, Context ct){
        this.stations = stations;
        this.ct = ct;
    }
        @Override
    public int getCount() {
            return stations.length;
    }

    @Override
    public Object getItem(int position) {
        if (position >= 0 && position <= stations.length) return stations[position];
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (position >= 0 && position <= stations.length) return position;
        else return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent,false);
        }

        TextView stationName = (TextView)convertView.findViewById(R.id.textView);
        stationName.setText((String) this.getItem(position));
        return convertView;
    };

}
