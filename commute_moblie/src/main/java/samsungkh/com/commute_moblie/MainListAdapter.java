package samsungkh.com.commute_moblie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;

/**
 * Created by Sangwon on 2017-08-15.
 */

public class MainListAdapter extends ArrayAdapter<RouteVO> {

    Context context;
    ArrayList<RouteVO> datas;
    int resId;


    public MainListAdapter(@NonNull Context context,  int resId, ArrayList<RouteVO> datas) {
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

            MainListWrapper wrapper = new MainListWrapper(convertView);

            convertView.setTag(wrapper);
        }

        MainListWrapper wrapper = (MainListWrapper) convertView.getTag();

        ImageView InitialView = wrapper.initialView;
        TextView nameView = wrapper.nameView;
        TextView descView = wrapper.descView;

        final RouteVO vo = datas.get(position);

        nameView.setText(vo.rt_nm);
        descView.setText(vo.gugan);

        //이니셜글자로 이미지 생성
        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        // generate random color
        int color1 = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder().buildRound((vo.rt_nm).substring(0,1), color1);
        InitialView.setImageDrawable(drawable);

//        forwardView.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                //상세정보 연결부분
//                Toast toast = Toast.makeText(context, "상세연결", Toast.LENGTH_SHORT);
//                toast.show();
//            }
//        });

        return convertView;

    }
}
