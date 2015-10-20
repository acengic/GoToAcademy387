package ba.tba.class1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jackblack on 10/19/15.
 */
public class DestinationAdapter extends BaseAdapter {
    ArrayList<Destination> appInfoList = null;
    Context context = null;

    DestinationAdapter(ArrayList<Destination> appInfoList, Context context){
        this.appInfoList =  appInfoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return appInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return appInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_destination, parent, false);
        }
        //Display Destination object in activity_destination layout
        TextView name = (TextView) convertView.findViewById(R.id.locationName);
        TextView address =  (TextView) convertView.findViewById(R.id.locatonAddress);

        Destination di = (Destination) appInfoList.get(position);

        name.setText(di.getName());
        address.setText(di.getAddress());

        return convertView;
    }
}
