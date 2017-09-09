package samsungkh.com.commute_moblie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jungyong on 2017-08-27.
 */

public class StopListAdapter extends ArrayAdapter<RouteVO> {

    Context context;
    ArrayList<StopVO> datas;
    int resId;


    public StopListAdapter(@NonNull Context context, int resId, ArrayList<StopVO> datas) {
        super(context, resId);
        this.context = context;
        this.resId = resId;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(resId, null);

            StopListWrapper wrapper = new StopListWrapper(convertView);

            convertView.setTag(wrapper);
        }

        StopListWrapper wrapper = (StopListWrapper) convertView.getTag();

        TextView descView = wrapper.descView;
        //ImageView mapIcon = wrapper.mapIcon;

        final StopVO vo = datas.get(position);

        descView.setText(vo.stop_desc);
        //mapIcon.setImageResource(R.drawable.map);

        return convertView;
    }
}
