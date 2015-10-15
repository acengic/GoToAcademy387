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
 * Created by jackblack on 10/14/15.
 */
public class ApplicationInfoAdapter extends BaseAdapter {
    ArrayList<ApplicationInfo> appInfoList = null;
    Context context = null;

    ApplicationInfoAdapter(ArrayList<ApplicationInfo> appInfoList, Context context){
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
            convertView = inflater.inflate(R.layout.item, parent, false);
        }
        //Display ApplicationInfo object in Item layout
        TextView publisher = (TextView) convertView.findViewById(R.id.publisher);
        TextView post =  (TextView) convertView.findViewById(R.id.post);
        TextView location = (TextView) convertView.findViewById(R.id.location);
        ImageView image = (ImageView) convertView.findViewById(R.id.applicationImage);

        ApplicationInfo ai = (ApplicationInfo) appInfoList.get(position);

        publisher.setText(ai.getPublisher());
        post.setText(ai.getPost());
        location.setText(ai.getLocation());

        Resources res = context.getResources();

//        int resID = res.getIdentifier(ai.imageFileName, "drawable", context.getPackageName());
//        Drawable drawable = res.getDrawable(resID);
        Bitmap b = BitmapFactory.decodeResource(context.getResources(), ai.getImageFileNameId());
        //Drawable myDrawable = context.getResources().getDrawable(R.drawable.facebook);
        image.setImageBitmap(b);

        return convertView;
    }
}
